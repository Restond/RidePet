/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitTriggerMetadata;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitCreeper;
/*     */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
/*     */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobLootDropEvent;
/*     */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*     */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*     */ import io.lumine.xikage.mythicmobs.drops.IIntangibleDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.IItemDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.IMessagingDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.LootBag;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import io.lumine.xikage.mythicmobs.skills.TriggeredSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.damage.DamageMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ShootMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.entity.Villager;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.player.PlayerChangedWorldEvent;
/*     */ import org.bukkit.event.player.PlayerInteractAtEntityEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BukkitSkillTriggers
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*     */   public void onCombatTrigger(EntityDamageByEntityEvent event) {
/*     */     AbstractEntity damaged, damager;
/*  74 */     MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "EntityDamageByEntityEvent fired for {0}", new Object[] { Double.valueOf(event.getFinalDamage()) });
/*     */     
/*  76 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     if (!(event.getEntity() instanceof LivingEntity)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  88 */     if (event.getDamager() instanceof LivingEntity) {
/*  89 */       damager = BukkitAdapter.adapt(event.getDamager());
/*  90 */     } else if (event.getDamager() instanceof Projectile) {
/*  91 */       ProjectileSource ps = ((Projectile)event.getDamager()).getShooter();
/*     */       
/*  93 */       if (ps instanceof LivingEntity) {
/*  94 */         damager = BukkitAdapter.adapt((Entity)ps);
/*     */       } else {
/*  96 */         damager = null;
/*     */       } 
/*     */     } else {
/*  99 */       damager = null;
/*     */     } 
/* 101 */     if (event.getEntity() instanceof LivingEntity) {
/* 102 */       damaged = BukkitAdapter.adapt(event.getEntity());
/*     */     } else {
/* 104 */       damaged = null;
/*     */     } 
/*     */     
/* 107 */     if (damaged != null && MythicMobs.inst().getMobManager().isActiveMob(damaged.getUniqueId())) {
/* 108 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(damaged);
/*     */       
/* 110 */       if (damager != null && MythicMobs.inst().getMobManager().isActiveMob(damager.getUniqueId())) {
/* 111 */         ActiveMob am2 = MythicMobs.inst().getMobManager().getMythicMobInstance(damager);
/*     */         
/* 113 */         if (am.getFaction() != null && am2.getFaction() != null && am.getFaction().equals(am2.getFaction())) {
/* 114 */           event.setCancelled(true);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 119 */       if (am.getType().getMaxAttackableRange() > 0 && damager != null) {
/* 120 */         if (damaged.getLocation().distanceSquared(damager.getLocation()) > Math.pow(am.getType().getMaxAttackableRange(), 2.0D)) {
/* 121 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "Damager is out of MaxCombatRange, cancelling damage.", new Object[0]);
/* 122 */           event.setCancelled(true);
/*     */           return;
/*     */         } 
/* 125 */       } else if (am.getType().getMaxAttackableRange() == 0) {
/* 126 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "MythicMob is not attackable, cancelling damage.", new Object[0]);
/* 127 */         event.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 131 */       if (am.hasImmunityTable()) {
/* 132 */         if (am.getImmunityTable().onCooldown(damager)) {
/* 133 */           event.setCancelled(true);
/*     */           return;
/*     */         } 
/* 136 */         am.getImmunityTable().setCooldown(damager);
/* 137 */         Schedulers.sync().runLater(() -> paramAbstractEntity.setNoDamageTicks(0), 1L);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       if (am.getType().getShowHealthInChat() == true) {
/* 144 */         MythicMobs.inst().getMobManager(); MobManager.showHealth(am);
/*     */       } 
/*     */       
/* 147 */       TriggeredSkill ts = new TriggeredSkill(SkillTrigger.DAMAGED, am, damager, false, meta -> BukkitTriggerMetadata.apply(meta, paramEntityDamageByEntityEvent), new org.apache.commons.lang3.tuple.Pair[0]);
/* 148 */       if (ts.getCancelled()) {
/* 149 */         event.setCancelled(true);
/*     */       }
/*     */       
/* 152 */       if (am.getType().getShowNameOnDamaged()) {
/* 153 */         event.getEntity().setCustomNameVisible(true);
/*     */       }
/*     */       
/* 156 */       if (am.getType().usesThreatTable() == true && 
/* 157 */         damager != null && !am.getEntity().getUniqueId().equals(damager.getUniqueId()) && am.getType().getThreatTableUseDamageTaken()) {
/* 158 */         am.getThreatTable().threatGain(damager, event.getDamage());
/*     */       }
/*     */ 
/*     */       
/* 162 */       if (am.getType().getEntityDamageModifiers() != null) {
/* 163 */         double damage = event.getDamage();
/* 164 */         double mod = ((Double)am.getType().getEntityDamageModifiers().getOrDefault(event.getDamager().getType().toString(), Double.valueOf(1.0D))).doubleValue();
/*     */         
/* 166 */         if (mod != 1.0D) {
/* 167 */           damage *= mod;
/*     */           
/* 169 */           if (damage > 0.0D) {
/* 170 */             event.setDamage(damage);
/* 171 */           } else if (damage == 0.0D) {
/* 172 */             event.setDamage(0.0D);
/* 173 */           } else if (damage < 0.0D) {
/* 174 */             event.setDamage(0.0D);
/* 175 */             event.setCancelled(true);
/*     */             
/* 177 */             if (damaged.getHealth() - damage > damaged.getMaxHealth()) {
/* 178 */               damaged.setHealth(damaged.getMaxHealth());
/*     */             } else {
/* 180 */               damaged.setHealth(damaged.getHealth() - damage);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 187 */     if (damager != null && MythicMobs.inst().getMobManager().isActiveMob(damager.getUniqueId())) {
/* 188 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(damager);
/*     */       
/* 190 */       if (am.getOwner().isPresent() && (
/* 191 */         (UUID)am.getOwner().get()).equals(damaged.getUniqueId())) {
/* 192 */         event.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 197 */       if (am.isUsingDamageSkill()) {
/* 198 */         if (MythicMobs.inst().getMinecraftVersion() < 12) {
/* 199 */           event.setDamage(EntityDamageEvent.DamageModifier.BASE, am.getLastDamageSkillAmount());
/*     */         } else {
/* 201 */           event.setDamage(am.getLastDamageSkillAmount());
/*     */         } 
/* 203 */       } else if (event.getDamager() instanceof org.bukkit.entity.Creeper) {
/* 204 */         if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
/* 205 */           MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Setting Creeper Custom Damage", new Object[0]);
/* 206 */           if (am.getDamage() != -1.0D) {
/* 207 */             event.setDamage(am.getDamage());
/*     */           }
/*     */         }
/*     */       
/* 211 */       } else if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
/* 212 */         MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Setting Mob Custom Damage to " + am.getDamage(), new Object[0]);
/* 213 */         if (am.getDamage() > 0.0D && 
/* 214 */           MythicMobs.inst().getMinecraftVersion() < 9) {
/* 215 */           event.setDamage(am.getDamage());
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 221 */       TriggeredSkill ts = new TriggeredSkill(SkillTrigger.ATTACK, am, damaged, new org.apache.commons.lang3.tuple.Pair[0]);
/* 222 */       if (ts.getCancelled()) {
/* 223 */         event.setCancelled(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
/*     */   public void onDamagedTrigger(EntityDamageEvent event) {
/*     */     ActiveMob am;
/* 233 */     if (!(event.getEntity() instanceof LivingEntity) || event.getEntity() instanceof Player)
/* 234 */       return;  if (MythicMobs.inst().getMobManager().isIgnoredEntity(event.getEntity().getUniqueId()))
/*     */       return; 
/* 236 */     LivingEntity damaged = (LivingEntity)event.getEntity();
/*     */ 
/*     */     
/* 239 */     if (!MythicMobs.inst().getMobManager().isActiveMob(damaged.getUniqueId())) {
/* 240 */       am = MythicMobs.inst().getMobManager().registerActiveMob(BukkitAdapter.adapt((Entity)damaged));
/*     */     } else {
/* 242 */       am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)damaged);
/*     */     } 
/* 244 */     if (am == null)
/*     */       return; 
/* 246 */     MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "MythicMob " + am.getType().getInternalName() + " took damage!", new Object[0]);
/*     */     
/* 248 */     am.signalDamaged();
/*     */     
/* 250 */     if (am.hasImmunityTable() && 
/* 251 */       !(event instanceof EntityDamageByEntityEvent)) {
/* 252 */       if (am.getImmunityTable().onCooldown(null)) {
/* 253 */         event.setCancelled(true);
/* 254 */         MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "MythicMob is currently immune to damage from non-player sources!", new Object[0]);
/*     */         return;
/*     */       } 
/* 257 */       am.getImmunityTable().setCooldown(null);
/* 258 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Setting MythicMob immune to damage from non-player sources!", new Object[0]);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 263 */     if (am.getType().getIsInvincible() == true) {
/* 264 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "MythicMob is Invincible, canceling damage.", new Object[0]);
/* 265 */       event.setCancelled(true);
/*     */       
/*     */       return;
/*     */     } 
/* 269 */     double damage = event.getDamage();
/* 270 */     if (am.getArmor() > 0.0D) {
/* 271 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Modifying damage based on armor: " + am.getArmor(), new Object[0]);
/* 272 */       damage -= am.getArmor();
/* 273 */       if (damage < 1.0D) damage = 1.0D; 
/* 274 */       event.setDamage(damage);
/*     */     } 
/*     */     
/* 277 */     if (am.getType().getDamageModifiers() != null) {
/*     */       String damageType;
/*     */ 
/*     */       
/* 281 */       Optional<Object> maybeData = am.getEntity().getMetadata("skill-damage");
/* 282 */       if (maybeData.isPresent()) {
/* 283 */         DamageMetadata data = (DamageMetadata)maybeData.get();
/* 284 */         damageType = (data.getElement() == null) ? "SKILL" : data.getElement();
/*     */       } else {
/* 286 */         damageType = event.getCause().toString();
/*     */       } 
/*     */       
/* 289 */       double mod = ((Double)am.getType().getDamageModifiers().getOrDefault(damageType.toUpperCase(), Double.valueOf(1.0D))).doubleValue();
/*     */       
/* 291 */       if (mod != 1.0D) {
/* 292 */         damage *= mod;
/*     */         
/* 294 */         if (damage > 0.0D) {
/* 295 */           event.setDamage(damage);
/* 296 */         } else if (damage == 0.0D) {
/* 297 */           event.setDamage(0.0D);
/* 298 */         } else if (damage < 0.0D) {
/* 299 */           event.setDamage(0.0D);
/* 300 */           event.setCancelled(true);
/*     */           
/* 302 */           if (damaged.getHealth() - damage > damaged.getMaxHealth()) {
/* 303 */             damaged.setHealth(damaged.getMaxHealth());
/*     */           } else {
/* 305 */             damaged.setHealth(damaged.getHealth() - damage);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 310 */     if (am.hasImmunityTable()) {
/* 311 */       Schedulers.sync().runLater(() -> paramLivingEntity.setNoDamageTicks(0), 1L);
/*     */ 
/*     */     
/*     */     }
/* 315 */     else if (am.getNoDamageTicks() != 20) {
/* 316 */       Schedulers.sync().runLater(() -> paramLivingEntity.setNoDamageTicks(paramActiveMob.getNoDamageTicks()), 1L);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 322 */     if (am.getType().getDigOutOfGround().booleanValue() == true && 
/* 323 */       event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
/*     */       
/* 325 */       Location Loc = damaged.getLocation().add(0.0D, 2.0D, 0.0D);
/* 326 */       damaged.teleport(Loc);
/*     */       
/* 328 */       event.setCancelled(true);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onInteractTrigger(PlayerInteractAtEntityEvent e) {
/* 341 */     MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "PlayerInteractAtEntityEvent fired", new Object[0]);
/* 342 */     if (MythicMobs.inst().getMinecraftVersion() >= 9 && e.getHand() != EquipmentSlot.HAND) {
/*     */       return;
/*     */     }
/* 345 */     Entity l = e.getRightClicked();
/* 346 */     if (!MythicMobs.inst().getMobManager().isActiveMob(l.getUniqueId())) {
/*     */       return;
/*     */     }
/*     */     
/* 350 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt(l));
/*     */ 
/*     */     
/* 353 */     TriggeredSkill ts = new TriggeredSkill(SkillTrigger.INTERACT, am, (AbstractEntity)BukkitAdapter.adapt(e.getPlayer()), true, new org.apache.commons.lang3.tuple.Pair[0]);
/* 354 */     if (ts.getCancelled() || !am.getType().getIsInteractable()) {
/* 355 */       e.setCancelled(true);
/*     */       
/* 357 */       if (e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
/* 358 */         Player p = e.getPlayer();
/* 359 */         Villager v = (Villager)e.getRightClicked();
/* 360 */         if (p.getOpenInventory().getTopInventory().equals(v.getInventory())) {
/* 361 */           p.getOpenInventory().close();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGHEST)
/*     */   public void onDeathTrigger(EntityDeathEvent event) {
/* 372 */     boolean dropLoot = true;
/*     */     
/* 374 */     AbstractEntity killedEntity = BukkitAdapter.adapt((Entity)event.getEntity());
/* 375 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(killedEntity);
/*     */     
/* 377 */     if (am != null) {
/* 378 */       MythicMob mm = am.getType();
/* 379 */       LivingEntity killer = getKiller(event);
/* 380 */       AbstractEntity aKiller = (killer == null) ? null : BukkitAdapter.adapt((Entity)killer);
/*     */       
/* 382 */       if (killer != null && !(killer instanceof Player) && 
/* 383 */         MythicMobs.inst().getMobManager().isActiveMob(killer.getUniqueId())) {
/* 384 */         ActiveMob amkiller = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt((Entity)killer));
/*     */         
/* 386 */         if (!killedEntity.isPlayer() && amkiller.getType().getPreventMobKillDrops().booleanValue() == true) {
/* 387 */           event.getDrops().clear();
/* 388 */           dropLoot = false;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 394 */       TriggeredSkill ts = new TriggeredSkill(SkillTrigger.DEATH, am, aKiller, new org.apache.commons.lang3.tuple.Pair[0]);
/*     */       
/* 396 */       if (mm.getPreventOtherDrops().booleanValue() == true) {
/* 397 */         event.getDrops().clear();
/* 398 */         event.setDroppedExp(0);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 405 */       DropMetadata metadata = new DropMetadata((SkillCaster)am, aKiller);
/* 406 */       LootBag lootBag = mm.getDropTable().generate(metadata);
/*     */       
/* 408 */       MythicMobLootDropEvent mythicLootEvent = new MythicMobLootDropEvent(am, killer, lootBag);
/* 409 */       Bukkit.getServer().getPluginManager().callEvent((Event)mythicLootEvent);
/*     */       
/* 411 */       int experience = 0;
/*     */       
/* 413 */       if (dropLoot) {
/*     */         
/* 415 */         Map<IMessagingDrop, Double> messagingDrops = new HashMap<>();
/*     */         
/* 417 */         for (Drop type : lootBag.getDrops()) {
/* 418 */           if (type instanceof IItemDrop) {
/* 419 */             event.getDrops().add(BukkitAdapter.adapt(((IItemDrop)type).getDrop(metadata)));
/* 420 */           } else if (type instanceof io.lumine.xikage.mythicmobs.drops.droppables.ExperienceDrop) {
/* 421 */             experience = (int)(experience + type.getAmount());
/* 422 */           } else if (type instanceof IIntangibleDrop && 
/* 423 */             aKiller != null && aKiller.isPlayer()) {
/* 424 */             ((IIntangibleDrop)type).giveDrop(aKiller.asPlayer(), metadata);
/*     */           } 
/*     */           
/* 427 */           if (type instanceof IMessagingDrop) {
/* 428 */             messagingDrops.merge((IMessagingDrop)type, Double.valueOf(type.getAmount()), (n, o) -> Double.valueOf(n.doubleValue() + o.doubleValue()));
/*     */           }
/*     */         } 
/* 431 */         if (aKiller != null && aKiller.isPlayer() && messagingDrops.size() > 0) {
/* 432 */           for (Map.Entry<IMessagingDrop, Double> m : messagingDrops.entrySet()) {
/* 433 */             String message = ((IMessagingDrop)m.getKey()).getRewardMessage(metadata, ((Double)m.getValue()).doubleValue());
/* 434 */             if (message != null) {
/* 435 */               aKiller.asPlayer().sendMessage(message);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 440 */         if (experience > 0) {
/* 441 */           event.setDroppedExp(experience);
/*     */         }
/*     */       } else {
/* 444 */         event.setDroppedExp(0);
/* 445 */         event.getDrops().clear();
/*     */       } 
/*     */       
/* 448 */       MythicMobDeathEvent mythicDeathEvent = new MythicMobDeathEvent(am, killer, event.getDrops());
/* 449 */       Bukkit.getServer().getPluginManager().callEvent((Event)mythicDeathEvent);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 462 */       am.setDead();
/* 463 */       MobManager.QueuedMobCleanup mclup = new MobManager.QueuedMobCleanup(am);
/* 464 */       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)mclup, 200L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 609 */       LivingEntity killerLE = getKiller(event);
/*     */       
/* 611 */       if (killerLE != null && 
/* 612 */         MythicMobs.inst().getMobManager().isActiveMob(killerLE.getUniqueId())) {
/* 613 */         ActiveMob amkiller = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt((Entity)killerLE));
/*     */         
/* 615 */         if (!killedEntity.isPlayer() && amkiller.getType().getPreventMobKillDrops().booleanValue() == true) {
/* 616 */           event.getDrops().clear();
/* 617 */           event.setDroppedExp(0);
/* 618 */           MythicMobs.debug(2, "A mob was killed by MythicMob with PreventKillDrops, cancelling drops!");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 624 */     MythicMobs.inst().getMobManager().getActiveMobsInCombat().forEach(mob -> {
/*     */           if (mob.getEntity().isValid() && mob.getType().usesThreatTable()) {
/*     */             mob.getThreatTable().observeDeath(paramAbstractEntity);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onChangeWorld(PlayerChangedWorldEvent event) {
/* 646 */     AbstractPlayer abstractPlayer = BukkitAdapter.adapt(event.getPlayer());
/* 647 */     MythicMobs.inst().getMobManager().getActiveMobsInCombat().forEach(mob -> {
/*     */           if (mob.getEntity().isValid() && mob.getType().usesThreatTable()) {
/*     */             mob.getThreatTable().observeChangeWorld(paramAbstractEntity);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onPlayerKillTrigger(PlayerDeathEvent event) {
/*     */     Entity entity;
/* 659 */     MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "PlayerDeathEvent fired", new Object[0]);
/*     */     
/* 661 */     Player p = event.getEntity();
/* 662 */     Player player1 = p.getKiller();
/*     */     
/* 664 */     AbstractPlayer player = BukkitAdapter.adapt(p);
/*     */     
/* 666 */     if (ConfigManager.KillMessagePrefix != null && event.getDeathMessage() != null) {
/* 667 */       event.setDeathMessage(ConfigManager.KillMessagePrefix + event.getDeathMessage());
/*     */     }
/*     */     
/* 670 */     if (player1 == null) {
/* 671 */       EntityDamageEvent entityDamageEvent = event.getEntity().getLastDamageCause();
/* 672 */       if (entityDamageEvent != null && !entityDamageEvent.isCancelled() && entityDamageEvent instanceof EntityDamageByEntityEvent) {
/* 673 */         entity = ((EntityDamageByEntityEvent)entityDamageEvent).getDamager();
/*     */       }
/*     */     } 
/*     */     
/* 677 */     if (entity instanceof LivingEntity) {
/*     */ 
/*     */       
/* 680 */       LivingEntity killer = (LivingEntity)entity;
/*     */       
/* 682 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt((Entity)killer));
/*     */       
/* 684 */       if (am != null) {
/*     */ 
/*     */ 
/*     */         
/* 688 */         TriggeredSkill ts = new TriggeredSkill(SkillTrigger.KILLPLAYER, am, (AbstractEntity)player, new org.apache.commons.lang3.tuple.Pair[0]);
/* 689 */         am.incrementPlayerKills();
/*     */         
/* 691 */         if (am.getType().hasKillMessages()) {
/* 692 */           PlaceholderString km = am.getType().getKillMessage();
/* 693 */           event.setDeathMessage(km.get((PlaceholderMeta)ts.getData(), (AbstractEntity)player));
/*     */         } 
/*     */       } 
/* 696 */     } else if (entity instanceof Projectile) {
/*     */       
/* 698 */       ProjectileSource ps = ((Projectile)entity).getShooter();
/*     */       
/* 700 */       if (ps instanceof LivingEntity) {
/* 701 */         LivingEntity shooter = (LivingEntity)ps;
/*     */         
/* 703 */         ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt((Entity)shooter));
/*     */         
/* 705 */         if (am != null) {
/*     */ 
/*     */           
/* 708 */           TriggeredSkill ts = new TriggeredSkill(SkillTrigger.KILLPLAYER, am, (AbstractEntity)player, new org.apache.commons.lang3.tuple.Pair[0]);
/* 709 */           am.incrementPlayerKills();
/*     */           
/* 711 */           if (am.getType().hasKillMessages()) {
/* 712 */             PlaceholderString km = am.getType().getKillMessage();
/* 713 */             event.setDeathMessage(km.get((PlaceholderMeta)ts.getData(), (AbstractEntity)player));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityExplode(EntityExplodeEvent e) {
/* 727 */     MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "EntityExplodeEvent fired", new Object[0]);
/* 728 */     if (e.getEntity() == null)
/* 729 */       return;  if (MythicMobs.inst().getMobManager().isActiveMob(e.getEntity().getUniqueId())) {
/* 730 */       MythicMobs.debug(2, "MythicMob exploded!");
/* 731 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt(e.getEntity()));
/*     */       
/* 733 */       if (am.getType().getMythicEntity() instanceof BukkitCreeper) {
/* 734 */         MythicMobs.debug(2, "-- Mob type was MythicCreeper");
/* 735 */         LivingEntity l = (LivingEntity)e.getEntity();
/*     */         
/* 737 */         if (((BukkitCreeper)am.getType().getMythicEntity()).preventSuicide() == true) {
/* 738 */           MythicMobs.debug(2, "-- MythicCreeper has preventSuicide == true, cloning creeper...");
/*     */           
/* 740 */           ActiveMob replacement = am.getType().spawn(am.getEntity().getLocation(), am.getLevel());
/*     */           
/* 742 */           LivingEntity ll = (LivingEntity)BukkitAdapter.adapt(replacement.getEntity());
/*     */           
/* 744 */           ll.setMaxHealth(l.getMaxHealth());
/* 745 */           ll.setHealth(l.getHealth());
/*     */           
/* 747 */           if (l.getPassenger() != null) {
/* 748 */             ll.setPassenger(l.getPassenger());
/*     */           }
/* 750 */           if (l.getVehicle() != null) {
/* 751 */             l.getVehicle().setPassenger((Entity)ll);
/*     */           }
/*     */           
/* 754 */           Collection<PotionEffect> pe = l.getActivePotionEffects();
/*     */           
/* 756 */           for (PotionEffect p : pe) {
/* 757 */             ll.addPotionEffect(p);
/*     */           }
/*     */           
/* 760 */           ActiveMob am2 = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt((Entity)ll));
/*     */           
/* 762 */           if (am2 == null) {
/* 763 */             MythicMobs.debug(2, "-- Something prevented Creeper from cloning! PreventSuicide failed :(");
/*     */             
/*     */             return;
/*     */           } 
/* 767 */           am2.setStance(am.getStance());
/* 768 */           am2.importPlayerKills(am.getPlayerKills());
/* 769 */           am2.importThreatTable(am.getThreatTable());
/*     */ 
/*     */           
/* 772 */           TriggeredSkill ts = new TriggeredSkill(SkillTrigger.EXPLODE, am, null, new org.apache.commons.lang3.tuple.Pair[0]);
/* 773 */           if (ts.getCancelled()) {
/* 774 */             e.setCancelled(true);
/*     */           }
/*     */         } else {
/*     */           
/* 778 */           TriggeredSkill ts = new TriggeredSkill(SkillTrigger.EXPLODE, am, null, new org.apache.commons.lang3.tuple.Pair[0]);
/* 779 */           if (ts.getCancelled()) {
/* 780 */             e.setCancelled(true);
/*     */           }
/*     */         } 
/* 783 */       } else if (am.getType().getMythicEntity() instanceof io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitTNT) {
/*     */         
/* 785 */         TriggeredSkill ts = new TriggeredSkill(SkillTrigger.EXPLODE, am, null, new org.apache.commons.lang3.tuple.Pair[0]);
/* 786 */         if (ts.getCancelled()) {
/* 787 */           e.setCancelled(true);
/*     */         }
/* 789 */         am.setDead();
/* 790 */         MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private LivingEntity getKiller(EntityDeathEvent event) {
/* 813 */     EntityDamageEvent entityDamageEvent = event.getEntity().getLastDamageCause();
/* 814 */     if (entityDamageEvent != null && !entityDamageEvent.isCancelled() && entityDamageEvent instanceof EntityDamageByEntityEvent) {
/* 815 */       Entity damager = ((EntityDamageByEntityEvent)entityDamageEvent).getDamager();
/*     */       
/* 817 */       if (damager instanceof Projectile) {
/* 818 */         if (((Projectile)damager).getShooter() instanceof LivingEntity) {
/* 819 */           LivingEntity shooter = (LivingEntity)((Projectile)damager).getShooter();
/* 820 */           if (shooter != null && 
/* 821 */             shooter instanceof LivingEntity) {
/* 822 */             return shooter;
/*     */           }
/*     */         } else {
/* 825 */           return (LivingEntity)event.getEntity().getKiller();
/*     */         } 
/*     */       }
/* 828 */       if (damager instanceof LivingEntity) {
/* 829 */         return (LivingEntity)damager;
/*     */       }
/*     */     } 
/* 832 */     return (LivingEntity)event.getEntity().getKiller();
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onProjectileHit(EntityDamageByEntityEvent event) {
/* 837 */     if (!(event.getDamager() instanceof Projectile))
/* 838 */       return;  if (!(event.getEntity() instanceof LivingEntity))
/*     */       return; 
/* 840 */     Projectile projectile = (Projectile)event.getDamager();
/*     */     
/* 842 */     List<MetadataValue> metas = projectile.getMetadata("MythicMobsProjectile");
/* 843 */     if (metas == null || metas.size() == 0)
/*     */       return; 
/* 845 */     for (MetadataValue meta : metas) {
/* 846 */       if (meta.value() instanceof ShootMechanic.ProjectileData) {
/* 847 */         ShootMechanic.ProjectileData data = (ShootMechanic.ProjectileData)meta.value();
/* 848 */         if (data.getDamage() == 0) {
/* 849 */           event.setCancelled(true);
/*     */         } else {
/* 851 */           event.setDamage(data.getDamage());
/*     */         } 
/* 853 */         data.executeHitSkill(BukkitAdapter.adapt((Entity)projectile), BukkitAdapter.adapt(event.getEntity()));
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 858 */     projectile.removeMetadata("MythicMobsProjectile", (Plugin)MythicMobs.inst());
/* 859 */     projectile.remove();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitSkillTriggers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */