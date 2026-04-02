/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractSkillAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.damage.DamageMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ShootMechanic;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.EntityEffect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Creature;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Fireball;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.entity.SmallFireball;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.Button;
/*     */ import org.bukkit.material.Lever;
/*     */ import org.bukkit.metadata.FixedMetadataValue;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BukkitSkillAdapter
/*     */   implements AbstractSkillAdapter
/*     */ {
/*     */   public void doDamage(DamageMetadata meta, AbstractEntity aTarget) {
/*  57 */     SkillCaster am = meta.getDamager();
/*  58 */     double damage = meta.getAmount();
/*     */     
/*  60 */     am.setUsingDamageSkill(true);
/*  61 */     am.getEntity().setMetadata("doing-skill-damage", Boolean.valueOf(true));
/*  62 */     aTarget.setMetadata("skill-damage", meta);
/*     */     
/*  64 */     if (am instanceof ActiveMob) {
/*  65 */       ((ActiveMob)am).setLastDamageSkillAmount(damage);
/*     */     }
/*     */     
/*  68 */     LivingEntity source = (LivingEntity)BukkitAdapter.adapt(am.getEntity());
/*  69 */     LivingEntity target = (LivingEntity)BukkitAdapter.adapt(aTarget);
/*     */     
/*     */     try {
/*  72 */       if (meta.getIgnoresArmor().booleanValue() == true) {
/*     */         
/*  74 */         EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(BukkitAdapter.adapt(am.getEntity()), (Entity)target, EntityDamageEvent.DamageCause.CUSTOM, damage);
/*  75 */         Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/*  77 */         if (event.isCancelled()) {
/*  78 */           MythicMobs.debug(3, "Damage event cancelled???");
/*  79 */           am.setUsingDamageSkill(false);
/*     */           return;
/*     */         } 
/*  82 */         if (target.getHealth() - damage < 1.0D) {
/*     */           
/*  84 */           target.setHealth(0.0D);
/*  85 */           if (meta.getPreventsKnockback().booleanValue()) {
/*  86 */             target.damage(10.0D);
/*     */           } else {
/*  88 */             target.damage(10.0D, (Entity)source);
/*     */           } 
/*     */         } else {
/*  91 */           target.setHealth(target.getHealth() - damage);
/*  92 */           target.playEffect(EntityEffect.HURT);
/*     */         }
/*     */       
/*  95 */       } else if (meta.getPreventsKnockback().booleanValue()) {
/*  96 */         target.damage(damage);
/*     */       } else {
/*  98 */         target.damage(damage, (Entity)source);
/*     */       }
/*     */     
/* 101 */     } catch (Exception ex) {
/* 102 */       if (ConfigManager.debugLevel > 0) {
/* 103 */         ex.printStackTrace();
/*     */       }
/*     */     } finally {
/* 106 */       am.getEntity().removeMetadata("doing-skill-damage");
/* 107 */       am.setUsingDamageSkill(false);
/* 108 */       aTarget.removeMetadata("skill-damage");
/*     */     } 
/*     */     
/* 111 */     if (meta.getPreventsImmunity().booleanValue()) {
/* 112 */       target.setNoDamageTicks(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void strikeLightning(AbstractLocation source) {
/* 118 */     BukkitAdapter.adapt(source.getWorld()).strikeLightning(BukkitAdapter.adapt(source));
/*     */   }
/*     */   
/*     */   public void strikeLightningEffect(AbstractLocation source) {
/* 122 */     BukkitAdapter.adapt(source.getWorld()).strikeLightningEffect(BukkitAdapter.adapt(source));
/*     */   }
/*     */   
/*     */   public void shootFireball(SkillCaster am, AbstractLocation target, float velocity, float yield, boolean incendiary, int fireTicks, boolean small, boolean playsound) {
/*     */     Fireball fireball;
/* 127 */     Location t = BukkitAdapter.adapt(target);
/* 128 */     Entity l = BukkitAdapter.adapt(am.getEntity());
/* 129 */     Location loc = l.getLocation().clone().add(0.0D, 1.0D, 0.0D);
/*     */ 
/*     */     
/* 132 */     if (l instanceof Creature && ((Creature)l).getTarget() == target) {
/* 133 */       if (small == true) {
/* 134 */         fireball = (Fireball)((LivingEntity)l).launchProjectile(SmallFireball.class);
/*     */       } else {
/* 136 */         fireball = (Fireball)((LivingEntity)l).launchProjectile(Fireball.class);
/*     */       } 
/*     */     } else {
/* 139 */       Vector vector = t.toVector().subtract(loc.toVector()).normalize();
/*     */       
/* 141 */       double yaw = Math.toDegrees(Math.atan2(-vector.getX(), vector.getZ()));
/* 142 */       double pitch = Math.toDegrees(-Math.asin(vector.getY()));
/* 143 */       loc.setYaw((float)yaw);
/* 144 */       loc.setPitch((float)pitch);
/*     */       
/* 146 */       loc.add(vector.multiply(2));
/*     */       
/* 148 */       if (small == true) {
/* 149 */         fireball = (Fireball)loc.getWorld().spawn(loc, SmallFireball.class);
/*     */       } else {
/* 151 */         fireball = (Fireball)loc.getWorld().spawn(loc, Fireball.class);
/*     */       } 
/*     */     } 
/*     */     
/* 155 */     if (playsound == true) {
/* 156 */       l.getWorld().playEffect(l.getLocation(), Effect.GHAST_SHOOT, 0);
/*     */     }
/*     */     
/* 159 */     Vector facing = t.toVector().subtract(loc.toVector()).normalize().multiply(velocity);
/* 160 */     fireball.setVelocity(facing);
/*     */     
/* 162 */     fireball.setBounce(false);
/* 163 */     fireball.setIsIncendiary(incendiary);
/* 164 */     fireball.setFireTicks(fireTicks);
/* 165 */     fireball.setYield(yield);
/* 166 */     if (l instanceof LivingEntity) {
/* 167 */       fireball.setShooter((ProjectileSource)l);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEntity shootProjectile(SkillCaster caster, AbstractLocation target, AbstractLocation origin, Class<? extends Projectile> projectileClass, float velocity, ShootMechanic.ProjectileData pd, boolean fromOrigin) {
/*     */     Projectile projectile;
/* 178 */     LivingEntity l = (LivingEntity)caster.getEntity().getBukkitEntity();
/* 179 */     Location fTarget = BukkitAdapter.adapt(target);
/*     */     
/* 181 */     if (fromOrigin == true) {
/* 182 */       Location fOrigin = BukkitAdapter.adapt(origin);
/* 183 */       projectile = (Projectile)fTarget.getWorld().spawn(fOrigin, projectileClass);
/* 184 */       projectile.setVelocity(fTarget.toVector().subtract(fOrigin.toVector()).normalize().multiply(velocity));
/*     */     } else {
/* 186 */       projectile = l.launchProjectile(projectileClass);
/* 187 */       Vector v = fTarget.toVector().subtract(l.getLocation().toVector()).normalize().multiply(velocity);
/* 188 */       projectile.setVelocity(v);
/*     */     } 
/*     */ 
/*     */     
/* 192 */     projectile.setBounce(false);
/* 193 */     projectile.setShooter((ProjectileSource)l);
/* 194 */     projectile.setMetadata("MythicMobsProjectile", (MetadataValue)new FixedMetadataValue((Plugin)MythicMobs.inst(), pd));
/*     */     
/* 196 */     return BukkitAdapter.adapt((Entity)projectile);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEntity shootArcProjectile(SkillCaster caster, AbstractLocation target, AbstractLocation origin, Class<? extends Projectile> projectileClass, float velocity, ShootMechanic.ProjectileData pd, boolean fromOrigin) {
/*     */     Projectile projectile;
/*     */     Location from;
/* 204 */     int gravity = 20;
/*     */ 
/*     */     
/* 207 */     LivingEntity l = (LivingEntity)caster.getEntity().getBukkitEntity();
/* 208 */     Location to = BukkitAdapter.adapt(target);
/*     */ 
/*     */     
/* 211 */     if (fromOrigin == true) {
/* 212 */       Location fOrigin = BukkitAdapter.adapt(origin);
/* 213 */       from = fOrigin;
/* 214 */       projectile = (Projectile)from.getWorld().spawn(fOrigin, projectileClass);
/*     */     } else {
/* 216 */       from = l.getLocation();
/* 217 */       projectile = l.launchProjectile(projectileClass);
/*     */     } 
/*     */ 
/*     */     
/* 221 */     projectile.setBounce(false);
/* 222 */     projectile.setShooter((ProjectileSource)l);
/* 223 */     projectile.setMetadata("MythicMobsProjectile", (MetadataValue)new FixedMetadataValue((Plugin)MythicMobs.inst(), pd));
/*     */     
/* 225 */     Vector test = to.clone().subtract(from).toVector();
/* 226 */     Double elevation = Double.valueOf(test.getY());
/*     */     
/* 228 */     Double launchAngle = MythicUtil.calculateLaunchAngle(from, to, velocity, elevation.doubleValue(), 20.0D);
/* 229 */     Double distance = Double.valueOf(Math.sqrt(Math.pow(test.getX(), 2.0D) + Math.pow(test.getZ(), 2.0D)));
/*     */     
/* 231 */     if (launchAngle == null) {
/* 232 */       launchAngle = Double.valueOf(Math.atan((40.0D * elevation.doubleValue() + Math.pow(velocity, 2.0D)) / (40.0D * elevation.doubleValue() + 2.0D * Math.pow(velocity, 2.0D))));
/*     */     }
/*     */     
/* 235 */     Double hangtime = Double.valueOf(MythicUtil.calculateHangtime(launchAngle.doubleValue(), velocity, elevation.doubleValue(), 20.0D));
/*     */     
/* 237 */     test.setY(Math.tan(launchAngle.doubleValue()) * distance.doubleValue());
/* 238 */     test = MythicUtil.normalizeVector(test);
/*     */     
/* 240 */     velocity = (float)(velocity + 1.188D * Math.pow(hangtime.doubleValue(), 2.0D) + (MythicMobs.r.nextDouble() - 0.8D) / 2.0D);
/* 241 */     test = test.multiply(velocity / 20.0D);
/*     */     
/* 243 */     projectile.setVelocity(test);
/*     */     
/* 245 */     return BukkitAdapter.adapt((Entity)projectile);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEntity rainProjectile(SkillCaster caster, AbstractLocation source, Class<? extends Projectile> projectileClass, float velocity, ShootMechanic.ProjectileData pd) {
/* 255 */     LivingEntity l = (LivingEntity)caster.getEntity().getBukkitEntity();
/* 256 */     Location s = BukkitAdapter.adapt(source);
/*     */     
/* 258 */     Projectile projectile = (Projectile)l.getWorld().spawn(s, projectileClass);
/* 259 */     Vector v = s.clone().add(0.0D, -1.0D, 0.0D).toVector().subtract(s.toVector()).normalize().multiply(velocity);
/* 260 */     projectile.setVelocity(v);
/* 261 */     projectile.setBounce(false);
/* 262 */     projectile.setShooter((ProjectileSource)l);
/* 263 */     projectile.setMetadata("MythicMobsProjectile", (MetadataValue)new FixedMetadataValue((Plugin)MythicMobs.inst(), pd));
/*     */     
/* 265 */     return BukkitAdapter.adapt((Entity)projectile);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void throwSkill(AbstractLocation source, AbstractEntity target, float velocity, float velocityY) {
/* 271 */     Vector V = BukkitAdapter.adapt(target.getLocation()).toVector().subtract(BukkitAdapter.adapt(source).toVector()).normalize().multiply(velocity);
/*     */     
/* 273 */     if (velocity == 0.0F) {
/* 274 */       V.setY(velocityY);
/*     */     } else {
/* 276 */       V.setY(velocityY + V.getY());
/*     */     } 
/*     */     
/* 279 */     if (V.length() > 4.0D) {
/* 280 */       V = V.normalize().multiply(4);
/*     */     }
/*     */     
/* 283 */     if (Double.isNaN(V.getX())) V.setX(0); 
/* 284 */     if (Double.isNaN(V.getY())) V.setY(0); 
/* 285 */     if (Double.isNaN(V.getZ())) V.setZ(0);
/*     */     
/* 287 */     target.getBukkitEntity().setVelocity(V);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void itemSprayEffect(AbstractLocation source, ItemStack item, int amount, int duration, double force, double yForce, double radius, double yOffset, boolean allowPickup) {
/* 294 */     Location l = BukkitAdapter.adapt(source);
/* 295 */     ItemStack is = item;
/* 296 */     is.setAmount(1);
/*     */     
/* 298 */     Location loc = l.clone().add(0.0D, yOffset, 0.0D);
/* 299 */     Item[] items = new Item[amount];
/*     */     
/* 301 */     if (radius <= 0.0D) {
/* 302 */       for (int i = 0; i < amount; i++) {
/* 303 */         items[i] = loc.getWorld().dropItem(loc, is);
/* 304 */         items[i].setVelocity(new Vector((MythicMobs.r.nextDouble() - 0.5D) * force, (MythicMobs.r.nextDouble() - 0.5D) * yForce, (MythicMobs.r.nextDouble() - 0.5D) * force));
/*     */         try {
/* 306 */           if (!allowPickup) {
/* 307 */             items[i].setPickupDelay(32767);
/* 308 */             items[i].setTicksLived(5800);
/*     */           } 
/* 310 */         } catch (Exception exception) {}
/*     */       } 
/*     */     } else {
/*     */       
/* 314 */       for (int i = 0; i < amount; i++) {
/* 315 */         Location lx = loc.clone();
/* 316 */         lx.setX(lx.getX() - radius + MythicMobs.r.nextDouble() * radius * 2.0D);
/* 317 */         lx.setZ(lx.getZ() - radius + MythicMobs.r.nextDouble() * radius * 2.0D);
/* 318 */         items[i] = lx.getWorld().dropItem(lx, is);
/* 319 */         items[i].setVelocity(new Vector((MythicMobs.r.nextDouble() - 0.5D) * force, (MythicMobs.r.nextDouble() - 0.5D) * yForce, (MythicMobs.r.nextDouble() - 0.5D) * force));
/*     */         try {
/* 321 */           if (!allowPickup) {
/* 322 */             items[i].setPickupDelay(32767);
/* 323 */             items[i].setTicksLived(5800);
/*     */           } 
/* 325 */         } catch (Exception exception) {}
/*     */       } 
/*     */     } 
/*     */     
/* 329 */     Schedulers.sync().runLater(() -> { for (int i = 0; i < paramArrayOfItem.length; i++) paramArrayOfItem[i].remove();  }duration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSmokeEffect(AbstractLocation source, int direction) {
/* 339 */     BukkitAdapter.adapt(source).getWorld().playEffect(BukkitAdapter.adapt(source), Effect.SMOKE, direction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pushButton(SkillCaster am, AbstractLocation location) {
/* 347 */     Block block = BukkitAdapter.adapt(location).getWorld().getBlockAt(BukkitAdapter.adapt(location));
/*     */     
/* 349 */     MythicMobs.debug(2, "Executing pushbutton skill @ " + location.toString() + "");
/*     */     try {
/* 351 */       if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/* 352 */         MythicMobs.inst().getVolatileCodeHandler().getBlockHandler().togglePowerable(location, 20L);
/*     */       } else {
/* 354 */         Button button = new Button(Material.STONE_BUTTON, block.getData());
/*     */         
/* 356 */         button.setPowered(true);
/* 357 */         BlockRedstoneEvent toggle = new BlockRedstoneEvent(block, 0, 1);
/* 358 */         Bukkit.getPluginManager().callEvent((Event)toggle);
/*     */ 
/*     */         
/* 361 */         MythicMobs.inst().getVolatileCodeHandler().applyPhysics(block);
/* 362 */         MythicMobs.inst().getVolatileCodeHandler().applyPhysics(block.getRelative(button.getAttachedFace()));
/*     */         
/* 364 */         Scheduler.runLaterSync(() -> {
/*     */               try {
/*     */                 paramBlock.getChunk().load();
/*     */                 paramButton.setPowered(false);
/*     */                 BlockRedstoneEvent toggle2 = new BlockRedstoneEvent(paramBlock, 0, 1);
/*     */                 Bukkit.getPluginManager().callEvent((Event)toggle2);
/*     */                 MythicMobs.inst().getVolatileCodeHandler().applyPhysics(paramBlock);
/*     */                 MythicMobs.inst().getVolatileCodeHandler().applyPhysics(paramBlock.getRelative(paramButton.getAttachedFace()));
/* 372 */               } catch (Exception e) {
/*     */                 e.printStackTrace();
/*     */               } 
/*     */             }20L);
/*     */       } 
/* 377 */     } catch (Exception e) {
/* 378 */       MythicMobs.error("A pushbutton skill is improperly configured: block is not a button.");
/* 379 */       e.printStackTrace();
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toggleLever(SkillCaster am, AbstractLocation location, int duration) {
/* 389 */     Block block = BukkitAdapter.adapt(location).getWorld().getBlockAt(BukkitAdapter.adapt(location));
/*     */     
/* 391 */     MythicMobs.debug(2, "Executing togglelever skill @ " + location.toString() + " duration=" + duration);
/*     */     try {
/* 393 */       if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/* 394 */         MythicMobs.inst().getVolatileCodeHandler().getBlockHandler().togglePowerable(location, duration);
/*     */       } else {
/* 396 */         Lever button = new Lever(Material.LEVER, block.getData());
/* 397 */         button.setPowered(true);
/* 398 */         BlockRedstoneEvent toggle = new BlockRedstoneEvent(block, 0, 1);
/* 399 */         Bukkit.getPluginManager().callEvent((Event)toggle);
/*     */ 
/*     */         
/* 402 */         LeverUnswitcher BU = new LeverUnswitcher(block);
/* 403 */         Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)BU, duration);
/*     */       } 
/* 405 */     } catch (Exception e) {
/* 406 */       MythicMobs.error("A ToggleLever skill is improperly configured: block is not a button.");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void executeVolley(SkillCaster am, AbstractLocation t, int amount, float velocity, float spread, int fireTicks, int removeDelay) {
/*     */     Vector v;
/* 435 */     if (!(am.getEntity().getBukkitEntity() instanceof ProjectileSource)) {
/*     */       return;
/*     */     }
/*     */     
/* 439 */     ProjectileSource source = (ProjectileSource)am.getEntity().getBukkitEntity();
/* 440 */     Location target = BukkitAdapter.adapt(t);
/* 441 */     Location spawn = BukkitAdapter.adapt(am.getLocation()).clone();
/* 442 */     spawn.setY(spawn.getY() + 3.0D);
/*     */ 
/*     */     
/* 445 */     if (target == null) {
/* 446 */       v = spawn.getDirection();
/*     */     } else {
/* 448 */       v = target.toVector().subtract(spawn.toVector()).normalize();
/*     */     } 
/*     */     
/* 451 */     ArrayList<Arrow> arrowList = new ArrayList<>();
/*     */     
/* 453 */     for (int i = 0; i < amount; i++) {
/* 454 */       Arrow a = spawn.getWorld().spawnArrow(spawn, v, velocity, spread / 10.0F);
/* 455 */       a.setVelocity(a.getVelocity());
/* 456 */       if (am.getEntity() != null) {
/* 457 */         a.setShooter(source);
/*     */       }
/*     */       
/* 460 */       if (fireTicks > 0) {
/* 461 */         a.setFireTicks(fireTicks);
/*     */       }
/* 463 */       arrowList.add(a);
/*     */     } 
/*     */     
/* 466 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)new Object(this, arrowList), removeDelay);
/*     */   }
/*     */   
/*     */   public void sendToastNotification(SkillCaster caster, String message) {}
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitSkillAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */