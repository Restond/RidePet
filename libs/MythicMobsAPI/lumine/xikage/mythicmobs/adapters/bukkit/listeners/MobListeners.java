/*     */ package lumine.xikage.mythicmobs.adapters.bukkit.listeners;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitMagmaCube;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitSilverfish;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitSlime;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitSnowman;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitWolf;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.WorldGuardSupport;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.VanillaManager;
/*     */ import io.lumine.xikage.mythicmobs.mobs.WorldScaling;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntityType;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import io.lumine.xikage.mythicmobs.skills.TriggeredSkill;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnPoint;
/*     */ import java.util.Collection;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Damageable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.EntityBlockFormEvent;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityChangeBlockEvent;
/*     */ import org.bukkit.event.entity.EntityCombustEvent;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityTameEvent;
/*     */ import org.bukkit.event.entity.EntityTeleportEvent;
/*     */ import org.bukkit.event.entity.EntityTransformEvent;
/*     */ import org.bukkit.event.entity.PlayerLeashEntityEvent;
/*     */ import org.bukkit.event.entity.SlimeSplitEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MobListeners
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
/*     */   public void damagePassthroughEvent(EntityDamageEvent event) {
/*  62 */     if (!(event.getEntity() instanceof Damageable))
/*     */       return; 
/*  64 */     Damageable damaged = (Damageable)event.getEntity();
/*     */     
/*  66 */     if (damaged == null || !MythicMobs.inst().getMobManager().isActiveMob(damaged.getUniqueId())) {
/*     */       return;
/*     */     }
/*     */     
/*  70 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)damaged);
/*     */     
/*  72 */     if (!am.getType().getPassthroughDamage().booleanValue() || am.getParent() == null) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     event.setCancelled(true);
/*  77 */     if (event instanceof EntityDamageByEntityEvent) {
/*  78 */       EntityDamageByEntityEvent ee = (EntityDamageByEntityEvent)event;
/*  79 */       ((Damageable)am.getParent().getEntity().getBukkitEntity()).damage(ee.getDamage(), ee.getDamager());
/*     */     } else {
/*  81 */       ((Damageable)am.getParent().getEntity().getBukkitEntity()).damage(event.getDamage());
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGHEST)
/*     */   public void MobSpawnEvent(CreatureSpawnEvent event) {
/*  87 */     if (event.getEntity() == null)
/*  88 */       return;  if (ConfigManager.debugMode == true) {
/*     */       return;
/*     */     }
/*  91 */     if (ConfigManager.debugSpawners == true) {
/*     */       return;
/*     */     }
/*  94 */     if (MythicMobs.inst().getCompatibility().getWorldGuard().isPresent() && 
/*  95 */       !((WorldGuardSupport)MythicMobs.inst().getCompatibility().getWorldGuard().get()).LocationAllowsMobSpawning(event.getLocation()))
/*     */       return; 
/*  97 */     MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "CreatureSpawnEvent fired", new Object[0]);
/*     */     
/*  99 */     AbstractEntity spawn = BukkitAdapter.adapt((Entity)event.getEntity());
/*     */     
/* 101 */     if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
/* 102 */       if (ConfigManager.isVanillaSpawnsDisabled()) {
/* 103 */         event.setCancelled(true);
/*     */       }
/* 105 */       int mobsInChunk = 0;
/*     */       try {
/* 107 */         AbstractWorld world = spawn.getLocation().getWorld();
/* 108 */         int x = spawn.getLocation().getBlockX() >> 4;
/* 109 */         int z = spawn.getLocation().getBlockZ() >> 4;
/* 110 */         mobsInChunk = MythicMobs.inst().getWorldManager().getEntitiesInChunk(world, x, z);
/* 111 */         MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "| Mobs in chunk calculated to be {0}", new Object[] { Integer.valueOf(mobsInChunk) });
/* 112 */       } catch (Exception exception) {}
/*     */       
/* 114 */       if (mobsInChunk >= ConfigManager.getRSMaxMobsPerChunk()) {
/* 115 */         MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "! Too many entities in chunk, cancelling", new Object[0]);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 120 */     RandomSpawnPoint rsp = new RandomSpawnPoint(BukkitAdapter.adapt((Entity)event.getEntity()), BukkitAdapter.adapt(event.getLocation()), event.getSpawnReason());
/*     */ 
/*     */     
/* 123 */     Entity ee = BukkitAdapter.adapt(MythicMobs.inst().getRandomSpawningManager().handleSpawnEvent(rsp));
/*     */     
/* 125 */     if (ee != null) {
/* 126 */       event.setCancelled(true);
/*     */     }
/* 128 */     else if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.BREEDING) {
/* 129 */       Scheduler.runLaterSync(() -> { if (!MythicMobs.inst().getMobManager().isActiveMob(paramAbstractEntity.getUniqueId())) { MythicEntityType met = VanillaManager.getMythicEntityType((Entity)paramCreatureSpawnEvent.getEntity()); Optional<MythicMob> maybeType = MythicMobs.inst().getMobManager().getVanillaType(met); if (maybeType.isPresent()) { MythicMob mm = maybeType.get(); int level = 1 + WorldScaling.getLevelBonus(paramAbstractEntity.getLocation()); ActiveMob am = MythicMobs.inst().getMobManager().registerActiveMob(paramAbstractEntity, mm, level); mm.getMythicEntity().applyOptions((Entity)paramCreatureSpawnEvent.getEntity()); mm.applyMobOptions(am, level); mm.applyMobVolatileOptions(am); mm.applySpawnModifiers(am); }  }  }5L);
/*     */     } 
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
/* 150 */     if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM) {
/* 151 */       if (ConfigManager.UseCompatibilityMode == true) {
/* 152 */         LivingEntity l = event.getEntity();
/*     */         
/* 154 */         Scheduler.runLaterSync(() -> { if (!MythicMobs.inst().getMobManager().isActiveMob(paramAbstractEntity.getUniqueId())) { MythicMobs.debug(3, "Compatibility mode enabled and found custom mob spawn! Checking for MythicMob '" + paramLivingEntity.getCustomName() + "'..."); MythicMob mm = MythicMobs.inst().getMobManager().getMythicMobByDisplayCompat(paramAbstractEntity); if (mm != null) { MythicMobs.inst().getMobManager().SetupMythicMobCompat(paramLivingEntity, mm); } else { MythicMobs.inst().getMobManager().setIgnoreEntity(paramLivingEntity.getUniqueId()); }  }  }10L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 169 */         LivingEntity l = event.getEntity();
/*     */         
/* 171 */         Scheduler.runLaterSync(() -> { if (!MythicMobs.inst().getMobManager().isActiveMob(paramLivingEntity.getUniqueId())) MythicMobs.inst().getMobManager().setIgnoreEntity(paramLivingEntity.getUniqueId());  }20L);
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
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onMobTame(EntityTameEvent event) {
/* 184 */     LivingEntity livingEntity = event.getEntity();
/*     */     
/* 186 */     if (MythicMobs.inst().getMobManager().isActiveMob(livingEntity.getUniqueId())) {
/* 187 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)livingEntity);
/*     */       
/* 189 */       Schedulers.sync().runLater(() -> { paramActiveMob.getType().applyMobOptions(paramActiveMob, paramActiveMob.getLevel()); paramActiveMob.getType().applyMobVolatileOptions(paramActiveMob); }5L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void TagEntity(PlayerInteractEntityEvent e) {
/* 198 */     if (!(e.getRightClicked() instanceof LivingEntity)) {
/*     */       return;
/*     */     }
/* 201 */     LivingEntity l = (LivingEntity)e.getRightClicked();
/* 202 */     if (!MythicMobs.inst().getMobManager().isActiveMob(l.getUniqueId())) {
/*     */       return;
/*     */     }
/* 205 */     if (e.getPlayer().getItemInHand().getType() != Material.NAME_TAG) {
/*     */       return;
/*     */     }
/*     */     
/* 209 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/*     */     
/* 211 */     if (am.getType().getPreventRename().booleanValue() == true) {
/* 212 */       e.setCancelled(true);
/* 213 */       e.getPlayer().sendMessage(ChatColor.RED + "That mob can't be renamed!");
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onCombust(EntityCombustEvent event) {
/* 219 */     if (event instanceof org.bukkit.event.entity.EntityCombustByEntityEvent || event instanceof org.bukkit.event.entity.EntityCombustByBlockEvent) {
/*     */       return;
/*     */     }
/*     */     
/* 223 */     World world = event.getEntity().getWorld();
/* 224 */     if (world.getEnvironment() != World.Environment.NORMAL) {
/*     */       return;
/*     */     }
/*     */     
/* 228 */     Entity entity = event.getEntity();
/* 229 */     if (MythicMobs.inst().getMobManager().isActiveMob(entity.getUniqueId())) {
/* 230 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(entity);
/*     */       
/* 232 */       if (am.getType().getPreventSunburn().booleanValue()) {
/* 233 */         event.setCancelled(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onTameEntity(PlayerInteractEntityEvent e) {
/* 240 */     if (!(e.getRightClicked() instanceof LivingEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 244 */     if (e.getPlayer().getItemInHand().getType() != Material.BONE) {
/*     */       return;
/*     */     }
/* 247 */     LivingEntity l = (LivingEntity)e.getRightClicked();
/* 248 */     if (!MythicMobs.inst().getMobManager().isActiveMob(l.getUniqueId())) {
/*     */       return;
/*     */     }
/*     */     
/* 252 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/*     */     
/* 254 */     if (am.getType().getMythicEntity() instanceof BukkitWolf && 
/* 255 */       !((BukkitWolf)am.getType().getMythicEntity()).isTameable()) {
/* 256 */       e.setCancelled(true);
/* 257 */       e.getPlayer().sendMessage(ChatColor.RED + "That mob can't be tamed!");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onLeashEntity(PlayerLeashEntityEvent e) {
/* 264 */     if (!(e.getEntity() instanceof LivingEntity)) {
/*     */       return;
/*     */     }
/* 267 */     LivingEntity l = (LivingEntity)e.getEntity();
/*     */     
/* 269 */     if (!MythicMobs.inst().getMobManager().isActiveMob(l.getUniqueId())) {
/*     */       return;
/*     */     }
/*     */     
/* 273 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/*     */     
/* 275 */     if (am.getType().getPreventLeashing().booleanValue() == true) {
/* 276 */       e.setCancelled(true);
/* 277 */       e.getPlayer().sendMessage(ChatColor.RED + "That mob can't be leashed!");
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onBlockForm(EntityBlockFormEvent e) {
/* 283 */     if (MythicMobs.inst().getMobManager().isActiveMob(e.getEntity().getUniqueId())) {
/* 284 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(e.getEntity());
/*     */       
/* 286 */       if (am.getType().getMythicEntity() instanceof BukkitSnowman && (
/* 287 */         (BukkitSnowman)am.getType().getMythicEntity()).getPreventSnowFormation() == true) {
/* 288 */         e.setCancelled(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onSplitEvent(SlimeSplitEvent e) {
/* 296 */     MythicMobs.debug(3, "EntitySplitEvent fired");
/* 297 */     if (MythicMobs.inst().getMobManager().isActiveMob(e.getEntity().getUniqueId())) {
/* 298 */       MythicMobs.debug(2, "MythicMob split!");
/* 299 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)e.getEntity());
/*     */       
/* 301 */       if (am.getType().getMythicEntity() instanceof BukkitSlime) {
/* 302 */         MythicMobs.debug(2, "-- Mob was a MythicSlime");
/* 303 */         if (!((BukkitSlime)am.getType().getMythicEntity()).canSplit()) {
/* 304 */           MythicMobs.debug(2, "---- PreventSplit == true, cancelling split");
/* 305 */           e.setCount(0);
/* 306 */           e.setCancelled(true);
/*     */         } 
/* 308 */       } else if (am.getType().getMythicEntity() instanceof BukkitMagmaCube) {
/* 309 */         MythicMobs.debug(2, "-- Mob was a MythicMagmaCube");
/* 310 */         if (!((BukkitMagmaCube)am.getType().getMythicEntity()).canSplit()) {
/* 311 */           MythicMobs.debug(2, "---- PreventSplit == true, cancelling split");
/* 312 */           e.setCount(0);
/* 313 */           e.setCancelled(true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onTeleportEvent(EntityTeleportEvent e) {
/* 321 */     if (!(e.getEntity() instanceof LivingEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 325 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(e.getEntity());
/*     */     
/* 327 */     if (am == null) {
/*     */       return;
/*     */     }
/*     */     
/* 331 */     if (am.getType().getPreventEndermanTeleport().booleanValue() == true) {
/* 332 */       e.getEntity().teleport(e.getFrom());
/* 333 */       e.setCancelled(true);
/*     */     } else {
/*     */       
/* 336 */       TriggeredSkill ts = new TriggeredSkill(SkillTrigger.TELEPORT, am, null, new org.apache.commons.lang3.tuple.Pair[0]);
/* 337 */       if (ts.getCancelled()) {
/* 338 */         e.setCancelled(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onChangeBlockEvent(EntityChangeBlockEvent e) {
/* 345 */     if (MythicMobs.inst().getMobManager().isActiveMob(e.getEntity().getUniqueId())) {
/* 346 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(e.getEntity());
/*     */       
/* 348 */       if (am.getType().getMythicEntity() instanceof BukkitSilverfish && (
/* 349 */         (BukkitSilverfish)am.getType().getMythicEntity()).getPreventBlockInfection() == true) {
/* 350 */         e.setCancelled(true);
/*     */         
/* 352 */         LivingEntity l = (LivingEntity)e.getEntity();
/* 353 */         LivingEntity ll = (LivingEntity)am.getType().spawn(am.getEntity().getLocation(), am.getLevel());
/*     */         
/* 355 */         ll.setMaxHealth(l.getMaxHealth());
/* 356 */         ll.setHealth(l.getHealth());
/*     */         
/* 358 */         if (l.getPassenger() != null) {
/* 359 */           ll.setPassenger(l.getPassenger());
/*     */         }
/* 361 */         if (l.getVehicle() != null) {
/* 362 */           l.getVehicle().setPassenger((Entity)ll);
/*     */         }
/*     */         
/* 365 */         Collection<PotionEffect> pe = l.getActivePotionEffects();
/*     */         
/* 367 */         for (PotionEffect p : pe) {
/* 368 */           ll.addPotionEffect(p);
/*     */         }
/*     */         
/* 371 */         ActiveMob am2 = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt((Entity)ll));
/*     */         
/* 373 */         if (am2 == null) {
/* 374 */           MythicMobs.debug(2, "-- Something prevented Silverfish from respawning! PreventBlockInfection failed :(");
/*     */           
/*     */           return;
/*     */         } 
/* 378 */         am2.setStance(am.getStance());
/* 379 */         am2.importPlayerKills(am.getPlayerKills());
/* 380 */         am2.importThreatTable(am.getThreatTable());
/* 381 */         l.remove();
/* 382 */         am.setDead();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTransform(EntityTransformEvent event) {
/* 389 */     if (MythicMobs.inst().getMobManager().isActiveMob(event.getEntity().getUniqueId())) {
/* 390 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(event.getEntity());
/*     */       
/* 392 */       if (am.getType().getPreventTransformation().booleanValue())
/* 393 */         event.setCancelled(true); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\listeners\MobListeners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */