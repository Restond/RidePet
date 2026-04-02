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
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.MissileMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.projectiles.Projectile;
/*     */ import io.lumine.xikage.mythicmobs.util.BlockUtil;
/*     */ import io.lumine.xikage.mythicmobs.util.HitBox;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MissileTracker
/*     */   extends Projectile.ProjectileTracker
/*     */ {
/*     */   private float inertia;
/*     */   private AbstractEntity target;
/*     */   private float velocity;
/*     */   private double targetYOffset;
/*     */   
/*     */   public MissileTracker(SkillMetadata data, AbstractEntity target) {
/*  51 */     super((Projectile)MissileMechanic.this, data, target.getLocation());
/*  52 */     this.target = target;
/*  53 */     this.inertia = MissileMechanic.this.projectileInertia;
/*     */     
/*  55 */     start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void projectileStart() {
/*  60 */     this.velocity = MissileMechanic.access$000(MissileMechanic.this) / MissileMechanic.access$100(MissileMechanic.this);
/*     */     
/*  62 */     if (MissileMechanic.access$200(MissileMechanic.this) == true) {
/*  63 */       this.startLocation = this.data.getOrigin().clone();
/*     */     } else {
/*  65 */       this.startLocation = this.data.getCaster().getEntity().getLocation().clone();
/*     */     } 
/*  67 */     this.targetYOffset = (this.targetYOffset == 0.0D) ? (this.target.getEyeLocation().getY() - this.target.getLocation().getY()) : this.targetYOffset;
/*     */     
/*  69 */     if (MissileMechanic.access$300(MissileMechanic.this) != 0.0F) {
/*  70 */       this.startLocation.setY(this.startLocation.getY() + MissileMechanic.access$400(MissileMechanic.this));
/*     */     }
/*  72 */     if (MissileMechanic.access$500(MissileMechanic.this) != 0.0F) {
/*  73 */       this.startLocation = MythicUtil.move(this.startLocation, MissileMechanic.access$600(MissileMechanic.this), 0.0D, 0.0D);
/*     */     }
/*  75 */     if (MissileMechanic.access$700(MissileMechanic.this) != 0.0F) {
/*  76 */       this.startLocation = MythicUtil.move(this.startLocation, 0.0D, 0.0D, MissileMechanic.access$800(MissileMechanic.this));
/*     */     }
/*     */     
/*  79 */     this.previousLocation = this.startLocation.clone();
/*  80 */     this.currentLocation = this.startLocation.clone();
/*  81 */     this.currentVelocity = this.target.getLocation().toVector().subtract(this.currentLocation.toVector()).normalize();
/*     */     
/*  83 */     if (MissileMechanic.access$900(MissileMechanic.this) != 0.0F || MissileMechanic.access$1000(MissileMechanic.this) > 0.0F) {
/*  84 */       float noise = 0.0F;
/*  85 */       if (MissileMechanic.access$1100(MissileMechanic.this) > 0.0F) {
/*  86 */         noise = MissileMechanic.access$1200(MissileMechanic.this) + MythicMobs.r.nextFloat() * MissileMechanic.access$1300(MissileMechanic.this);
/*     */       }
/*  88 */       this.currentVelocity.rotate(MissileMechanic.access$1400(MissileMechanic.this) + noise);
/*     */     } 
/*  90 */     if (MissileMechanic.access$1500(MissileMechanic.this) != 0.0F || MissileMechanic.access$1600(MissileMechanic.this) > 0.0F) {
/*  91 */       float noise = 0.0F;
/*  92 */       if (MissileMechanic.access$1700(MissileMechanic.this) > 0.0F) {
/*  93 */         noise = MissileMechanic.access$1800(MissileMechanic.this) + MythicMobs.r.nextFloat() * MissileMechanic.access$1900(MissileMechanic.this);
/*     */       }
/*  95 */       this.currentVelocity.add(new AbstractVector(0.0F, MissileMechanic.access$2000(MissileMechanic.this) + noise, 0.0F)).normalize();
/*     */     } 
/*     */     
/*  98 */     if (MissileMechanic.access$2100(MissileMechanic.this)) {
/*  99 */       this.velocity *= this.power;
/*     */     }
/* 101 */     this.currentVelocity.multiply(this.velocity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void projectileTick() {
/* 106 */     this.previousLocation = this.currentLocation.clone();
/*     */ 
/*     */     
/* 109 */     this.currentLocation.add(this.currentVelocity);
/* 110 */     this.currentVelocity.multiply(this.inertia);
/* 111 */     this.currentVelocity.add(this.target.getLocation().add(0.0D, this.targetYOffset, 0.0D).subtract(this.currentLocation.getX(), this.currentLocation.getY(), this.currentLocation.getZ()).toVector().normalize());
/* 112 */     this.currentVelocity.normalize().multiply(this.velocity);
/*     */     
/* 114 */     if (MissileMechanic.access$2200(MissileMechanic.this) && !BlockUtil.isPathable(BukkitAdapter.adapt(this.currentLocation).getBlock())) {
/* 115 */       terminate();
/*     */       
/*     */       return;
/*     */     } 
/* 119 */     if (this.bullet != null) {
/* 120 */       if (MissileMechanic.access$2300(MissileMechanic.this) == Projectile.BulletType.ITEM) {
/* 121 */         AbstractLocation ol = this.previousLocation.clone().subtract(0.0D, 0.35D, 0.0D);
/*     */         
/* 123 */         AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setItemPosition(this.bullet, ol);
/* 124 */         this.bullet.setVelocity(this.currentLocation.toVector().subtract(this.previousLocation.toVector()));
/* 125 */       } else if (MissileMechanic.access$2400(MissileMechanic.this) == Projectile.BulletType.ARROW) {
/* 126 */         this.bullet.setVelocity(this.currentLocation.toVector().subtract(this.bullet.getLocation().clone().toVector()).multiply(0.25D));
/* 127 */       } else if (MissileMechanic.access$2500(MissileMechanic.this) == Projectile.BulletType.MOB) {
/* 128 */         AbstractLocation ol = this.previousLocation.clone().subtract(0.0D, 1.35D, 0.0D);
/*     */         
/* 130 */         if (MissileMechanic.access$2600(MissileMechanic.this) != 0.0F) {
/* 131 */           float newSpin = this.bullet.getLocation().getYaw() + MissileMechanic.access$2700(MissileMechanic.this);
/* 132 */           ol.setYaw(newSpin);
/*     */         } 
/* 134 */         this.bullet.teleport(ol);
/*     */       } else {
/* 136 */         this.bullet.setVelocity(this.currentLocation.toVector().subtract(this.bullet.getLocation().clone().toVector()).multiply(1));
/*     */         
/* 138 */         if (MissileMechanic.access$2800(MissileMechanic.this) > 0.0F) {
/* 139 */           float newSpin = this.bullet.getLocation().getYaw() + MissileMechanic.access$2900(MissileMechanic.this);
/* 140 */           AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setEntityRotation(this.bullet, newSpin, newSpin);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 145 */     if (this.inRange != null) {
/* 146 */       HitBox hitBox = new HitBox(this.currentLocation, MissileMechanic.access$3000(MissileMechanic.this), MissileMechanic.access$3100(MissileMechanic.this));
/* 147 */       for (AbstractEntity e : this.inRange) {
/* 148 */         if (!e.isDead() && hitBox.contains(e.getLocation().add(0.0D, 0.6D, 0.0D))) {
/* 149 */           this.targets.add(e);
/*     */           
/* 151 */           if (MissileMechanic.access$3200(MissileMechanic.this)) {
/* 152 */             doHit(this.targets);
/* 153 */             terminate();
/*     */             return;
/*     */           } 
/* 156 */           this.immune.put(e, Long.valueOf(System.currentTimeMillis()));
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 161 */       Iterator<Map.Entry<AbstractEntity, Long>> iter = this.immune.entrySet().iterator();
/* 162 */       while (iter.hasNext()) {
/* 163 */         Map.Entry<AbstractEntity, Long> entry = iter.next();
/* 164 */         if (((Long)entry.getValue()).longValue() < System.currentTimeMillis() - 2000L) {
/* 165 */           iter.remove();
/* 166 */           this.inRange.add(entry.getKey());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 171 */     if (MissileMechanic.access$3300(MissileMechanic.this).isPresent() && (
/* 172 */       (Skill)MissileMechanic.access$3400(MissileMechanic.this).get()).usable(this.data, null)) {
/* 173 */       AbstractLocation location; SkillMetadata sData = this.data.deepClone();
/*     */       
/* 175 */       if (MissileMechanic.access$3500(MissileMechanic.this) == Projectile.BulletType.ARROW) {
/* 176 */         location = this.previousLocation.clone();
/*     */       } else {
/* 178 */         location = this.currentLocation.clone();
/*     */       } 
/* 180 */       ((Skill)MissileMechanic.access$3600(MissileMechanic.this).get()).execute(sData.setOrigin(location).setLocationTarget(location));
/*     */     } 
/*     */     
/* 183 */     if (this.targets.size() > 0) {
/* 184 */       doHit((HashSet<AbstractEntity>)this.targets.clone());
/*     */     }
/* 186 */     this.targets.clear();
/*     */   }
/*     */   
/*     */   public void setVelocity(double value) {
/* 190 */     this.currentVelocity = this.currentVelocity.normalize().multiply(value);
/*     */   }
/*     */   
/*     */   public void modifyVelocity(double v) {
/* 194 */     this.currentVelocity = this.currentVelocity.multiply(v);
/*     */   }
/*     */   
/*     */   public void setInertia(float p) {
/* 198 */     this.inertia = p;
/*     */   }
/*     */   
/*     */   public void modifyInertia(float p) {
/* 202 */     this.inertia *= p;
/*     */   }
/*     */   
/*     */   public void doHit(HashSet<AbstractEntity> targets) {
/* 206 */     if (MissileMechanic.access$3700(MissileMechanic.this).isPresent()) {
/* 207 */       SkillMetadata sData = this.data.deepClone();
/* 208 */       sData.setEntityTargets(targets);
/* 209 */       sData.setOrigin(this.currentLocation.clone());
/* 210 */       if (((Skill)MissileMechanic.access$3800(MissileMechanic.this).get()).usable(sData, null))
/* 211 */         ((Skill)MissileMechanic.access$3900(MissileMechanic.this).get()).execute(sData); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\MissileMechanic$MissileTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */