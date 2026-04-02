/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ProjectileMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.projectiles.Projectile;
/*     */ import io.lumine.xikage.mythicmobs.util.BlockUtil;
/*     */ import io.lumine.xikage.mythicmobs.util.HitBox;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProjectileMechanicTracker
/*     */   extends Projectile.ProjectileTracker
/*     */ {
/*  81 */   private float gravity = 0.0F;
/*     */   
/*     */   private AbstractLocation target;
/*     */   private int currentX;
/*     */   private int currentZ;
/*     */   
/*     */   public ProjectileMechanicTracker(SkillMetadata data, AbstractLocation target) {
/*  88 */     super((Projectile)ProjectileMechanic.this, data, target);
/*  89 */     this.target = target;
/*     */     
/*  91 */     start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void projectileStart() {
/*     */     float velocity;
/*  97 */     if (ProjectileMechanic.this.type == ProjectileMechanic.ProjectileType.METEOR) {
/*  98 */       this.startLocation = this.target.clone();
/*  99 */       this.startLocation.add(0.0D, ProjectileMechanic.this.heightFromSurface, 0.0D);
/* 100 */       if (ProjectileMechanic.this.projectileGravity <= 0.0F) {
/* 101 */         this.gravity = ProjectileMechanic.access$000(ProjectileMechanic.this);
/* 102 */         this.gravity = (this.gravity > 0.0F) ? (this.gravity / ProjectileMechanic.access$100(ProjectileMechanic.this)) : 0.0F;
/*     */       } else {
/* 104 */         this.gravity = (ProjectileMechanic.this.projectileGravity > 0.0F) ? (ProjectileMechanic.this.projectileGravity / ProjectileMechanic.access$200(ProjectileMechanic.this)) : 0.0F;
/*     */       } 
/* 106 */       velocity = 0.0F;
/*     */     } else {
/* 108 */       if (ProjectileMechanic.access$300(ProjectileMechanic.this)) {
/* 109 */         this.startLocation = this.data.getOrigin().clone();
/*     */       } else {
/* 111 */         this.startLocation = this.data.getCaster().getEntity().getLocation().clone();
/*     */       } 
/* 113 */       velocity = ProjectileMechanic.access$400(ProjectileMechanic.this) / ProjectileMechanic.access$500(ProjectileMechanic.this);
/* 114 */       this.gravity = (ProjectileMechanic.this.projectileGravity > 0.0F) ? (ProjectileMechanic.this.projectileGravity / ProjectileMechanic.access$600(ProjectileMechanic.this)) : 0.0F;
/*     */       
/* 116 */       if (ProjectileMechanic.access$700(ProjectileMechanic.this) != 0.0F) {
/* 117 */         this.startLocation.setY(this.startLocation.getY() + ProjectileMechanic.access$800(ProjectileMechanic.this));
/*     */       }
/* 119 */       if (ProjectileMechanic.access$900(ProjectileMechanic.this) != 0.0F) {
/* 120 */         this.startLocation = MythicUtil.move(this.startLocation, ProjectileMechanic.access$1000(ProjectileMechanic.this), 0.0D, 0.0D);
/*     */       }
/* 122 */       if (ProjectileMechanic.access$1100(ProjectileMechanic.this) != 0.0F) {
/* 123 */         this.startLocation = MythicUtil.move(this.startLocation, 0.0D, 0.0D, ProjectileMechanic.access$1200(ProjectileMechanic.this));
/*     */       }
/*     */     } 
/*     */     
/* 127 */     this.previousLocation = this.startLocation.clone();
/* 128 */     this.currentLocation = this.startLocation.clone();
/*     */     
/* 130 */     if (this.currentLocation == null) {
/*     */       return;
/*     */     }
/*     */     
/* 134 */     this.currentVelocity = this.target.toVector().subtract(this.currentLocation.toVector()).normalize();
/*     */     
/* 136 */     if (ProjectileMechanic.access$1300(ProjectileMechanic.this) != 0.0F || ProjectileMechanic.access$1400(ProjectileMechanic.this) > 0.0F) {
/* 137 */       float noise = 0.0F;
/* 138 */       if (ProjectileMechanic.access$1500(ProjectileMechanic.this) > 0.0F) {
/* 139 */         noise = ProjectileMechanic.access$1600(ProjectileMechanic.this) + MythicMobs.r.nextFloat() * ProjectileMechanic.access$1700(ProjectileMechanic.this);
/*     */       }
/* 141 */       this.currentVelocity.rotate(ProjectileMechanic.access$1800(ProjectileMechanic.this) + noise);
/*     */     } 
/* 143 */     if (ProjectileMechanic.access$1900(ProjectileMechanic.this) != 0.0F || ProjectileMechanic.access$2000(ProjectileMechanic.this) > 0.0F) {
/* 144 */       float noise = 0.0F;
/* 145 */       if (ProjectileMechanic.access$2100(ProjectileMechanic.this) > 0.0F) {
/* 146 */         noise = ProjectileMechanic.access$2200(ProjectileMechanic.this) + MythicMobs.r.nextFloat() * ProjectileMechanic.access$2300(ProjectileMechanic.this);
/*     */       }
/* 148 */       this.currentVelocity.add(new AbstractVector(0.0F, ProjectileMechanic.access$2400(ProjectileMechanic.this) + noise, 0.0F)).normalize();
/*     */     } 
/* 150 */     if (ProjectileMechanic.this.hugSurface) {
/* 151 */       this.currentLocation.setY(((int)this.currentLocation.getY() + ProjectileMechanic.this.heightFromSurface));
/* 152 */       this.currentVelocity.setY(0).normalize();
/*     */     } 
/* 154 */     if (ProjectileMechanic.access$2500(ProjectileMechanic.this)) {
/* 155 */       this.currentVelocity.multiply(this.power);
/*     */     }
/* 157 */     this.currentVelocity.multiply(velocity);
/*     */     
/* 159 */     if (ProjectileMechanic.this.projectileGravity > 0.0F) {
/* 160 */       this.currentVelocity.setY(this.currentVelocity.getY() - this.gravity);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setVelocity(double value) {
/* 165 */     this.currentVelocity = this.currentVelocity.normalize().multiply(value);
/*     */   }
/*     */   
/*     */   public void modifyVelocity(double v) {
/* 169 */     this.currentVelocity = this.currentVelocity.multiply(v);
/*     */   }
/*     */   
/*     */   public void setGravity(float p) {
/* 173 */     this.gravity = p;
/*     */   }
/*     */   
/*     */   public void modifyGravity(float p) {
/* 177 */     this.gravity *= p;
/*     */   }
/*     */ 
/*     */   
/*     */   public void projectileTick() {
/* 182 */     this.previousLocation = this.currentLocation.clone();
/* 183 */     this.currentLocation.add(this.currentVelocity);
/*     */     
/* 185 */     if (ProjectileMechanic.this.hugSurface) {
/* 186 */       if (this.currentLocation.getBlockX() != this.currentX || this.currentLocation.getBlockZ() != this.currentZ) {
/* 187 */         Block b = BukkitAdapter.adapt(this.currentLocation.subtract(0.0D, ProjectileMechanic.this.heightFromSurface, 0.0D)).getBlock();
/*     */         
/* 189 */         if (BlockUtil.isPathable(b)) {
/* 190 */           int attempts = 0;
/* 191 */           boolean ok = false;
/*     */           
/* 193 */           while (attempts++ < 10) {
/* 194 */             b = b.getRelative(BlockFace.DOWN);
/* 195 */             if (BlockUtil.isPathable(b)) {
/* 196 */               this.currentLocation.add(0.0D, -1.0D, 0.0D); continue;
/*     */             } 
/* 198 */             ok = true;
/*     */           } 
/*     */ 
/*     */           
/* 202 */           if (!ok) {
/* 203 */             terminate();
/*     */             return;
/*     */           } 
/*     */         } else {
/* 207 */           int attempts = 0;
/* 208 */           boolean ok = false;
/* 209 */           while (attempts++ < 10) {
/* 210 */             b = b.getRelative(BlockFace.UP);
/* 211 */             this.currentLocation.add(0.0D, 1.0D, 0.0D);
/* 212 */             if (BlockUtil.isPathable(b)) {
/* 213 */               ok = true;
/*     */               break;
/*     */             } 
/*     */           } 
/* 217 */           if (!ok) {
/* 218 */             terminate();
/*     */             return;
/*     */           } 
/*     */         } 
/* 222 */         this.currentLocation.setY(((int)this.currentLocation.getY() + ProjectileMechanic.this.heightFromSurface));
/* 223 */         this.currentX = this.currentLocation.getBlockX();
/* 224 */         this.currentZ = this.currentLocation.getBlockZ();
/*     */       } 
/* 226 */     } else if (ProjectileMechanic.this.projectileGravity != 0.0F) {
/* 227 */       this.currentVelocity.setY(this.currentVelocity.getY() - (ProjectileMechanic.this.projectileGravity / ProjectileMechanic.access$2600(ProjectileMechanic.this)));
/*     */     } 
/*     */     
/* 230 */     if (ProjectileMechanic.access$2700(ProjectileMechanic.this) && !BlockUtil.isPathable(BukkitAdapter.adapt(this.currentLocation).getBlock())) {
/* 231 */       terminate();
/*     */       
/*     */       return;
/*     */     } 
/* 235 */     if (this.bullet != null) {
/* 236 */       if (ProjectileMechanic.access$2800(ProjectileMechanic.this) == Projectile.BulletType.ITEM) {
/* 237 */         AbstractLocation ol = this.previousLocation.clone().subtract(0.0D, 0.35D, 0.0D);
/*     */         
/* 239 */         AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setItemPosition(this.bullet, ol);
/* 240 */         this.bullet.setVelocity(this.currentLocation.toVector().subtract(this.previousLocation.toVector()));
/* 241 */       } else if (ProjectileMechanic.access$2900(ProjectileMechanic.this) == Projectile.BulletType.ARROW) {
/* 242 */         this.bullet.setVelocity(this.currentLocation.toVector().subtract(this.bullet.getLocation().clone().toVector()).multiply(0.25D));
/* 243 */       } else if (ProjectileMechanic.access$3000(ProjectileMechanic.this) == Projectile.BulletType.MOB) {
/* 244 */         AbstractLocation ol = this.previousLocation.clone().subtract(0.0D, 1.35D, 0.0D);
/*     */         
/* 246 */         if (ProjectileMechanic.access$3100(ProjectileMechanic.this) != 0.0F) {
/* 247 */           float newSpin = this.bullet.getLocation().getYaw() + ProjectileMechanic.access$3200(ProjectileMechanic.this);
/* 248 */           ol.setYaw(newSpin);
/*     */         } 
/* 250 */         this.bullet.teleport(ol);
/*     */       } else {
/* 252 */         this.bullet.setVelocity(this.currentLocation.toVector().subtract(this.bullet.getLocation().clone().toVector()).multiply(1));
/*     */         
/* 254 */         if (ProjectileMechanic.access$3300(ProjectileMechanic.this) > 0.0F) {
/* 255 */           float newSpin = this.bullet.getLocation().getYaw() + ProjectileMechanic.access$3400(ProjectileMechanic.this);
/* 256 */           AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setEntityRotation(this.bullet, newSpin, newSpin);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 262 */     if (this.inRange != null) {
/* 263 */       HitBox hitBox = new HitBox(this.currentLocation, ProjectileMechanic.access$3500(ProjectileMechanic.this), ProjectileMechanic.access$3600(ProjectileMechanic.this));
/*     */       
/* 265 */       for (AbstractEntity e : this.inRange) {
/* 266 */         if (!e.isDead() && hitBox.contains(e.getLocation().add(0.0D, 0.6D, 0.0D))) {
/* 267 */           this.targets.add(e);
/* 268 */           this.immune.put(e, Long.valueOf(System.currentTimeMillis()));
/*     */           break;
/*     */         } 
/*     */       } 
/* 272 */       this.immune.entrySet().removeIf(entry -> (((Long)entry.getValue()).longValue() < System.currentTimeMillis() - 2000L));
/*     */     } 
/*     */     
/* 275 */     if (ProjectileMechanic.access$3700(ProjectileMechanic.this).isPresent() && (
/* 276 */       (Skill)ProjectileMechanic.access$3800(ProjectileMechanic.this).get()).isUsable(this.data)) {
/* 277 */       AbstractLocation location; SkillMetadata sData = this.data.deepClone();
/*     */       
/* 279 */       if (ProjectileMechanic.access$3900(ProjectileMechanic.this) == Projectile.BulletType.ARROW) {
/* 280 */         location = this.previousLocation.clone();
/*     */       } else {
/* 282 */         location = this.currentLocation.clone();
/*     */       } 
/* 284 */       HashSet<AbstractLocation> targets = new HashSet<>();
/* 285 */       targets.add(location);
/* 286 */       sData.setLocationTargets(targets);
/* 287 */       sData.setOrigin(location);
/*     */       
/* 289 */       ((Skill)ProjectileMechanic.access$4000(ProjectileMechanic.this).get()).execute(sData);
/*     */     } 
/*     */ 
/*     */     
/* 293 */     if (this.targets.size() > 0) {
/* 294 */       doHit((HashSet<AbstractEntity>)this.targets.clone());
/*     */       
/* 296 */       if (ProjectileMechanic.access$4100(ProjectileMechanic.this)) {
/* 297 */         terminate();
/*     */       }
/*     */     } 
/* 300 */     this.targets.clear();
/*     */   }
/*     */   
/*     */   private void doHit(HashSet<AbstractEntity> targets) {
/* 304 */     if (ProjectileMechanic.access$4200(ProjectileMechanic.this).isPresent()) {
/* 305 */       SkillMetadata sData = this.data.deepClone();
/* 306 */       sData.setEntityTargets(targets);
/* 307 */       sData.setOrigin(this.currentLocation.clone());
/*     */       
/* 309 */       if (((Skill)ProjectileMechanic.access$4300(ProjectileMechanic.this).get()).isUsable(sData)) {
/* 310 */         ((Skill)ProjectileMechanic.access$4400(ProjectileMechanic.this).get()).execute(sData);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled() {
/* 317 */     terminate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCancelled() {
/* 322 */     return this.components.hasTerminated();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ProjectileMechanic$ProjectileMechanicTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */