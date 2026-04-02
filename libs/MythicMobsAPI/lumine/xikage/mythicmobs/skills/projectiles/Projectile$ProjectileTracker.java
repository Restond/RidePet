/*     */ package lumine.xikage.mythicmobs.skills.projectiles;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.events.Events;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.utils.terminable.TerminableRegistry;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.projectiles.Projectile;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.FallingBlock;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.entity.EntityChangeBlockEvent;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.EulerAngle;
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
/*     */ public abstract class ProjectileTracker
/*     */   implements IParentSkill, Runnable, Terminable
/*     */ {
/* 213 */   protected final TerminableRegistry components = TerminableRegistry.create();
/*     */   protected SkillMetadata data; protected int chargesRemaining; protected int ticksRemaining;
/*     */   
/* 216 */   public int getChargesRemaining() { return this.chargesRemaining; } public int getTicksRemaining() {
/* 217 */     return this.ticksRemaining;
/*     */   }
/* 219 */   protected AbstractEntity bullet = null;
/*     */   
/*     */   protected float power;
/*     */   
/*     */   protected AbstractLocation startLocation;
/*     */   protected AbstractLocation previousLocation;
/*     */   protected AbstractLocation currentLocation;
/*     */   protected AbstractVector currentVelocity;
/* 227 */   protected Set<AbstractEntity> inRange = ConcurrentHashMap.newKeySet();
/* 228 */   protected HashSet<AbstractEntity> targets = new HashSet<>();
/* 229 */   protected HashMap<AbstractEntity, Long> immune = new HashMap<>();
/*     */   
/*     */   public ProjectileTracker(SkillMetadata data, AbstractLocation target) {
/* 232 */     this.data = data.deepClone();
/* 233 */     this.data.setCallingEvent(this);
/* 234 */     this.power = data.getPower();
/* 235 */     this.ticksRemaining = Projectile.this.duration.get((PlaceholderMeta)data);
/*     */     
/* 237 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "++ Projectile fired by Entity {0}: skill = {1}", new Object[] { data.getCaster().getEntity().getName(), Projectile.access$000(this$0) });
/*     */   }
/*     */   
/*     */   private void evaluatePotentialTargets() {
/* 241 */     this.inRange.clear();
/*     */     
/* 243 */     if (Projectile.this.hitSelf || Projectile.this.hitPlayers || Projectile.this.hitNonPlayers) {
/* 244 */       this.inRange.addAll(AbstractSkill.getPlugin().getEntityManager().getLivingEntities(this.currentLocation.getWorld()));
/* 245 */       this.inRange.removeIf(e -> (e == null) ? true : (
/*     */ 
/*     */           
/* 248 */           (!e.isLiving() || e.isCitizensNPC()) ? true : (
/*     */           
/* 250 */           (!Projectile.this.hitSelf && e.getUniqueId().equals(this.data.getCaster().getEntity().getUniqueId())) ? true : (
/*     */           
/* 252 */           (!Projectile.this.hitPlayers && e.isPlayer()) ? true : (
/*     */           
/* 254 */           (!Projectile.this.hitNonPlayers && !e.isPlayer()))))));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean executeProjectileSkill(Optional<Skill> skill, SkillMetadata data, boolean atCaster) {
/* 263 */     if (skill == null) return true; 
/* 264 */     if (!skill.isPresent()) return true;
/*     */     
/* 266 */     data = data.deepClone();
/*     */     
/* 268 */     if (atCaster) {
/* 269 */       data.setEntityTarget(data.getCaster().getEntity());
/*     */     }
/*     */     
/* 272 */     if (((Skill)skill.get()).isUsable(data)) {
/*     */       
/* 274 */       VariableRegistry vars = data.getVariables();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 280 */       ((Skill)skill.get()).execute(data);
/* 281 */       return true;
/*     */     } 
/* 283 */     return false;
/*     */   }
/*     */   
/*     */   public void setPower(float p) {
/* 287 */     this.power = p;
/*     */   }
/*     */   
/*     */   public void modifyPower(float p) {
/* 291 */     this.power *= p;
/*     */   }
/*     */   
/*     */   public boolean start() {
/* 295 */     projectileStart();
/* 296 */     evaluatePotentialTargets();
/*     */     
/* 298 */     if (Projectile.this.onStartSkill.isPresent() && (
/* 299 */       (Skill)Projectile.this.onStartSkill.get()).isUsable(this.data)) {
/* 300 */       SkillMetadata sData = this.data.deepClone();
/*     */       
/* 302 */       HashSet<AbstractLocation> targets = new HashSet<>();
/* 303 */       targets.add(this.startLocation);
/* 304 */       sData.setLocationTargets(targets);
/* 305 */       sData.setOrigin(this.currentLocation.clone());
/*     */       
/* 307 */       ((Skill)Projectile.this.onStartSkill.get()).execute(sData);
/*     */     } 
/*     */     
/* 310 */     this.components.accept(Schedulers.sync().runRepeating(this, 0L, Projectile.this.tickInterval));
/* 311 */     if (Projectile.this.bulletType != Projectile.BulletType.NONE) {
/* 312 */       spawnBullet();
/*     */       
/* 314 */       if (Projectile.this.bulletType == Projectile.BulletType.BLOCK) {
/* 315 */         this.components.accept(Events.subscribe(EntityChangeBlockEvent.class)
/* 316 */             .filter(event -> event.getEntity().getUniqueId().equals(this.bullet.getUniqueId()))
/* 317 */             .handler(event -> event.setCancelled(true)));
/*     */       
/*     */       }
/* 320 */       else if (Projectile.this.bulletType == Projectile.BulletType.MOB) {
/* 321 */         this.components.accept(Events.subscribe(PlayerInteractEntityEvent.class)
/* 322 */             .filter(event -> event.getRightClicked().getUniqueId().equals(this.bullet.getUniqueId()))
/* 323 */             .handler(event -> event.setCancelled(true)));
/* 324 */       } else if (Projectile.this.bulletType == Projectile.BulletType.ARROW) {
/* 325 */         this.components.accept(Events.subscribe(EntityDamageByEntityEvent.class)
/* 326 */             .filter(event -> event.getDamager().getUniqueId().equals(this.bullet.getUniqueId()))
/* 327 */             .handler(event -> event.setCancelled(true)));
/*     */       } 
/*     */     } 
/* 330 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 335 */     this.ticksRemaining -= Projectile.this.tickInterval;
/*     */     
/* 337 */     if (this.data.getCaster() != null && this.data.getCaster().getEntity().isDead()) {
/* 338 */       terminate();
/*     */       return;
/*     */     } 
/* 341 */     if (this.currentLocation.distanceSquared(this.startLocation) >= Projectile.this.maxDistanceSquared) {
/* 342 */       terminate();
/*     */       return;
/*     */     } 
/* 345 */     if (this.ticksRemaining <= 0) {
/* 346 */       terminate();
/*     */       
/*     */       return;
/*     */     } 
/* 350 */     projectileTick();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void projectileEnd() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean terminate() {
/* 361 */     if (!this.components.hasTerminated()) {
/* 362 */       projectileEnd();
/*     */       
/* 364 */       if (Projectile.this.bulletType != Projectile.BulletType.NONE) {
/* 365 */         Schedulers.sync().runLater(() -> { if (this.bullet != null) { this.bullet.remove(); Projectile.BULLET_ENTITIES.remove(this.bullet); }  }2L);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 372 */       if (Projectile.this.onEndSkill.isPresent() && (
/* 373 */         (Skill)Projectile.this.onEndSkill.get()).isUsable(this.data)) {
/* 374 */         SkillMetadata sData = this.data.deepClone();
/* 375 */         ((Skill)Projectile.this.onEndSkill.get()).execute(sData.setOrigin(this.currentLocation).setLocationTarget(this.currentLocation));
/*     */       } 
/*     */       
/* 378 */       if (this.inRange != null) {
/* 379 */         this.inRange.clear();
/*     */       }
/*     */     } 
/* 382 */     return this.components.terminate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled() {
/* 387 */     terminate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCancelled() {
/* 392 */     return this.components.hasTerminated();
/*     */   }
/*     */   
/*     */   private void spawnBullet() {
/* 396 */     if (Projectile.this.bulletType == Projectile.BulletType.BLOCK) {
/* 397 */       Scheduler.runSync(() -> {
/*     */             FallingBlock block;
/*     */             
/*     */             if (hasTerminated()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             AbstractLocation l = this.currentLocation.clone().subtract(0.0D, 0.5D, 0.0D);
/*     */             
/*     */             if (AbstractSkill.getPlugin().getMinecraftVersion() >= 13) {
/*     */               block = BukkitAdapter.adapt(l).getWorld().spawnFallingBlock(BukkitAdapter.adapt(l), Projectile.this.bulletMaterial.createBlockData());
/*     */             } else {
/*     */               block = BukkitAdapter.adapt(l).getWorld().spawnFallingBlock(BukkitAdapter.adapt(l), Projectile.this.bulletMaterial, (byte)0);
/*     */             } 
/*     */             
/*     */             block.setHurtEntities(false);
/*     */             
/*     */             block.setDropItem(false);
/*     */             
/*     */             block.setTicksLived(2147483647);
/*     */             
/*     */             block.setInvulnerable(true);
/*     */             block.setGravity(false);
/*     */             this.bullet = BukkitAdapter.adapt((Entity)block);
/*     */             Projectile.BULLET_ENTITIES.add(this.bullet);
/*     */             AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setHitBox(this.bullet, 0.0D, 0.0D, 0.0D);
/*     */             if (hasTerminated()) {
/*     */               block.remove();
/*     */             }
/*     */           });
/* 427 */     } else if (Projectile.this.bulletType == Projectile.BulletType.SMALLBLOCK) {
/* 428 */       Scheduler.runSync(() -> {
/*     */             AbstractLocation l = this.currentLocation.clone();
/*     */             
/*     */             ArmorStand block = (ArmorStand)BukkitAdapter.adapt(l).getWorld().spawnEntity(BukkitAdapter.adapt(l), EntityType.ARMOR_STAND);
/*     */             
/*     */             block.setCustomName("Dinnerbone");
/*     */             
/*     */             block.setCustomNameVisible(false);
/*     */             
/*     */             block.setHeadPose(new EulerAngle(0.0D, 0.0D, 0.0D));
/*     */             
/*     */             block.getEquipment().setHelmet(new ItemStack(Projectile.this.bulletMaterial));
/*     */             
/*     */             block.setArms(false);
/*     */             block.setBasePlate(false);
/*     */             block.setVisible(false);
/*     */             block.setTicksLived(2147483647);
/*     */             block.setInvulnerable(true);
/*     */             this.bullet = BukkitAdapter.adapt((Entity)block);
/*     */             AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setArmorStandNoGravity(this.bullet);
/*     */             Projectile.BULLET_ENTITIES.add(this.bullet);
/*     */             AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setHitBox(this.bullet, 0.0D, 0.0D, 0.0D);
/*     */             if (hasTerminated()) {
/*     */               block.remove();
/*     */             }
/*     */           });
/* 454 */     } else if (Projectile.this.bulletType == Projectile.BulletType.ITEM) {
/* 455 */       Scheduler.runSync(() -> {
/*     */             ItemStack i = new ItemStack(Projectile.this.bulletMaterial);
/*     */             
/*     */             AbstractLocation l = this.currentLocation.clone().subtract(0.0D, 0.35D, 0.0D);
/*     */             
/*     */             Item item = BukkitAdapter.adapt(l).getWorld().dropItem(BukkitAdapter.adapt(l), i);
/*     */             
/*     */             item.setTicksLived(2147483647);
/*     */             
/*     */             item.setInvulnerable(true);
/*     */             
/*     */             item.setGravity(false);
/*     */             
/*     */             item.setPickupDelay(2147483647);
/*     */             this.bullet = BukkitAdapter.adapt((Entity)item);
/*     */             Projectile.BULLET_ENTITIES.add(this.bullet);
/*     */             AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setItemPosition(this.bullet, l);
/*     */             AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().sendEntityTeleportPacket(this.bullet);
/*     */             AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setHitBox(this.bullet, 0.0D, 0.0D, 0.0D);
/*     */             if (hasTerminated()) {
/*     */               item.remove();
/*     */             }
/*     */           });
/* 478 */     } else if (Projectile.this.bulletType == Projectile.BulletType.MOB && Projectile.this.bulletMob != null) {
/* 479 */       Scheduler.runLaterSync(() -> { AbstractLocation l = this.previousLocation.clone().subtract(0.0D, 1.35D, 0.0D); ActiveMob am = Projectile.this.bulletMob.spawn(l, 1); Entity entity = am.getEntity().getBukkitEntity(); entity.setTicksLived(2147483647); entity.setInvulnerable(true); this.bullet = BukkitAdapter.adapt(entity); Projectile.BULLET_ENTITIES.add(this.bullet); if (entity.getType().equals(EntityType.ARMOR_STAND)) { AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setArmorStandNoGravity(this.bullet); this.bullet.setAI(true); ((ArmorStand)entity).setRemoveWhenFarAway(true); } else { entity.setGravity(false); if (entity instanceof LivingEntity) ((LivingEntity)entity).setRemoveWhenFarAway(true);  }  AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setHitBox(this.bullet, 0.0D, 0.0D, 0.0D); if (hasTerminated()) this.bullet.remove();  }2L);
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
/* 508 */     else if (Projectile.this.bulletType == Projectile.BulletType.ARROW) {
/* 509 */       Scheduler.runLaterSync(() -> { AbstractLocation l = this.currentLocation.clone(); Location loc = BukkitAdapter.adapt(l); Arrow arrow = (Arrow)loc.getWorld().spawn(loc, Arrow.class); arrow.setTicksLived(2147483647); arrow.setInvulnerable(true); arrow.setGravity(false); arrow.setVelocity(BukkitAdapter.adapt(this.currentVelocity).multiply(0.25D)); this.bullet = BukkitAdapter.adapt((Entity)arrow); Projectile.BULLET_ENTITIES.add(this.bullet); AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setHitBox(this.bullet, 0.0D, 0.0D, 0.0D); if (hasTerminated()) arrow.remove();  }2L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract void projectileStart();
/*     */   
/*     */   public abstract void projectileTick();
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\projectiles\Projectile$ProjectileTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */