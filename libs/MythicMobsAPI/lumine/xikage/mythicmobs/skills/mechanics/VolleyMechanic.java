/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.SkillAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ShootMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ 
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "volley", aliases = {"shootvolley"}, description = "Fires a volley of projectiles")
/*     */ public class VolleyMechanic
/*     */   extends ShootMechanic
/*     */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*     */ {
/*     */   protected VolleyType type;
/*     */   protected PlaceholderInt amount;
/*     */   protected double radius;
/*     */   protected double yOffset;
/*     */   
/*     */   public VolleyMechanic(String line, MythicLineConfig mlc) {
/*  29 */     super(line, mlc);
/*     */     
/*  31 */     String strType = mlc.getString(new String[] { "source", "s" }, "REGULAR", new String[0]).toUpperCase();
/*     */     
/*  33 */     if (strType.equals("RAIN")) {
/*  34 */       this.type = VolleyType.RAIN;
/*     */     } else {
/*  36 */       this.type = VolleyType.REGULAR;
/*     */     } 
/*     */     
/*  39 */     this.amount = mlc.getPlaceholderInteger(new String[] { "amount", "a" }, 10, new String[0]);
/*  40 */     this.radius = mlc.getDouble(new String[] { "radius", "r" }, 0.0D);
/*     */     
/*  42 */     this.yOffset = mlc.getDouble("yoffset", 1.0D);
/*  43 */     this.yOffset = mlc.getDouble("y", this.yOffset);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*  48 */     return shoot(data, target.getLocation());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*  53 */     return shoot(data, target);
/*     */   }
/*     */   
/*     */   public boolean shoot(SkillMetadata data, AbstractLocation target) {
/*  57 */     SkillCaster caster = data.getCaster();
/*     */     
/*  59 */     ShootMechanic.ProjectileData pd = new ShootMechanic.ProjectileData(caster, this.damage, this.onHitSkill, this.onEndSkill, data.getPower());
/*     */     
/*  61 */     AbstractLocation primeSource = null;
/*     */     
/*  63 */     if (this.type.equals(VolleyType.RAIN)) {
/*  64 */       primeSource = target.clone().add(0.0D, this.yOffset, 0.0D);
/*     */     }
/*     */     
/*  67 */     float velocity = this.velocity;
/*     */     
/*  69 */     if (this.powerAffectsVelocity) {
/*  70 */       velocity *= this.power;
/*     */     }
/*  72 */     int amount = this.amount.get((PlaceholderMeta)data);
/*  73 */     for (int i = 0; i < amount; i++) {
/*     */       AbstractEntity projectile;
/*     */       
/*  76 */       if (this.type.equals(VolleyType.RAIN)) {
/*     */         
/*  78 */         AbstractLocation source = primeSource.clone();
/*  79 */         source.setX(source.getX() - this.radius + MythicMobs.r.nextDouble() * this.radius * 2.0D);
/*  80 */         source.setZ(source.getZ() - this.radius + MythicMobs.r.nextDouble() * this.radius * 2.0D);
/*     */         
/*  82 */         if (this.projectileType.equals(ShootMechanic.ProjectileType.ITEM)) {
/*  83 */           projectile = SkillAdapter.get().rainProjectile(caster, source, this.projectileClass, velocity, pd);
/*  84 */         } else if (this.projectileType.equals(ShootMechanic.ProjectileType.BLOCK)) {
/*  85 */           projectile = SkillAdapter.get().rainProjectile(caster, source, this.projectileClass, velocity, pd);
/*     */         } else {
/*  87 */           projectile = SkillAdapter.get().rainProjectile(caster, source, this.projectileClass, velocity, pd);
/*     */         }
/*     */       
/*  90 */       } else if (this.projectileType.equals(ShootMechanic.ProjectileType.ITEM)) {
/*  91 */         projectile = SkillAdapter.get().shootProjectile(caster, target, data.getOrigin(), this.projectileClass, velocity, pd, this.fromOrigin);
/*  92 */       } else if (this.projectileType.equals(ShootMechanic.ProjectileType.BLOCK)) {
/*  93 */         projectile = SkillAdapter.get().shootProjectile(caster, target, data.getOrigin(), this.projectileClass, velocity, pd, this.fromOrigin);
/*     */       } else {
/*  95 */         projectile = SkillAdapter.get().shootProjectile(caster, target, data.getOrigin(), this.projectileClass, velocity, pd, this.fromOrigin);
/*     */       } 
/*     */       
/*  98 */       new ShootMechanic.ProjectileTracker(this, data, projectile, this.power);
/*     */     } 
/* 100 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\VolleyMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */