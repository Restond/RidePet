/*     */ package lumine.xikage.mythicmobs.skills.projectiles;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.bukkit.Material;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Projectile
/*     */   extends SkillMechanic
/*     */ {
/*  54 */   public static final Set<AbstractEntity> BULLET_ENTITIES = ConcurrentHashMap.newKeySet();
/*     */   
/*     */   protected Optional<Skill> onTickSkill;
/*     */   
/*     */   protected Optional<Skill> onHitSkill;
/*     */   
/*     */   protected Optional<Skill> onEndSkill;
/*     */   
/*     */   protected Optional<Skill> onStartSkill;
/*     */   
/*     */   protected String onTickSkillName;
/*     */   
/*     */   protected String onHitSkillName;
/*     */   
/*     */   protected String onEndSkillName;
/*     */   
/*     */   protected String onStartSkillName;
/*     */   
/*     */   protected ProjectileType type;
/*     */   
/*     */   protected boolean fromOrigin;
/*     */   protected BulletType bulletType;
/*     */   protected Material bulletMaterial;
/*     */   protected io.lumine.xikage.mythicmobs.skills.projectiles.Projectile bulletProjectile;
/*     */   protected MythicMob bulletMob;
/*     */   protected float bulletSpin;
/*     */   protected PlaceholderInt duration;
/*     */   protected int tickInterval;
/*     */   protected float ticksPerSecond;
/*     */   protected float hitRadius;
/*     */   protected float verticalHitRadius;
/*     */   protected float maxDistanceSquared;
/*     */   protected float startYOffset;
/*     */   protected float startForwardOffset;
/*     */   protected float startSideOffset;
/*     */   protected float targetYOffset;
/*     */   protected float projectileVelocity;
/*     */   protected float projectileVelocityVertOffset;
/*     */   protected float projectileVelocityHorizOffset;
/*     */   protected float projectileVelocityAccuracy;
/*     */   protected float projectileVelocityVertNoise;
/*     */   protected float projectileVelocityHorizNoise;
/*     */   protected float projectileVelocityVertNoiseBase;
/*     */   protected float projectileVelocityHorizNoiseBase;
/*     */   protected boolean stopOnHitEntity;
/*     */   protected boolean stopOnHitGround;
/*     */   protected boolean powerAffectsVelocity;
/*     */   protected boolean powerAffectsRange;
/*     */   protected boolean hitSelf;
/*     */   protected boolean hitTarget;
/*     */   protected boolean hitPlayers;
/*     */   protected boolean hitNonPlayers;
/*     */   protected boolean hitTargetOnly;
/*     */   
/*     */   public Projectile(String skill, MythicLineConfig mlc) {
/* 109 */     super(skill, mlc); String strBulletMaterial; this.onTickSkill = Optional.empty(); this.onHitSkill = Optional.empty(); this.onEndSkill = Optional.empty(); this.onStartSkill = Optional.empty(); this.bulletMaterial = null; this.bulletProjectile = null; this.bulletMob = null; this.bulletSpin = 0.0F; this.powerAffectsVelocity = true; this.powerAffectsRange = true; this.hitSelf = false; this.hitTarget = true; this.hitPlayers = true; this.hitNonPlayers = false;
/*     */     this.hitTargetOnly = false;
/* 111 */     this.onTickSkillName = mlc.getString(new String[] { "ontickskill", "ontick", "ot", "skill", "s", "meta", "m" });
/* 112 */     this.onHitSkillName = mlc.getString(new String[] { "onhitskill", "onhit", "oh" });
/* 113 */     this.onEndSkillName = mlc.getString(new String[] { "onendskill", "onend", "oe" });
/* 114 */     this.onStartSkillName = mlc.getString(new String[] { "onstartskill", "onstart", "os" });
/*     */     
/* 116 */     String type = mlc.getString("type", "NORMAL");
/*     */     try {
/* 118 */       this.type = ProjectileType.valueOf(type.toUpperCase());
/* 119 */     } catch (Exception ex) {
/* 120 */       MythicLogger.errorMechanicConfig(this, mlc, "Invalid projectile type specified");
/* 121 */       this.type = ProjectileType.NORMAL;
/*     */     } 
/*     */     
/* 124 */     String bulletType = mlc.getString(new String[] { "bullettype", "bullet", "b" }, "NONE", new String[0]);
/*     */     try {
/* 126 */       this.bulletType = BulletType.valueOf(bulletType.toUpperCase());
/* 127 */     } catch (Exception ex) {
/* 128 */       MythicLogger.errorMechanicConfig(this, mlc, "Invalid bullet type specified");
/* 129 */       this.bulletType = BulletType.NONE;
/*     */     } 
/*     */     
/* 132 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$projectiles$Projectile$BulletType[this.bulletType.ordinal()]) { case 1: case 2:
/*     */       case 3:
/* 134 */         strBulletMaterial = mlc.getString(new String[] { "bulletmaterial", "material", "mat" }, "STONE", new String[0]);
/*     */         try {
/* 136 */           this.bulletMaterial = Material.valueOf(strBulletMaterial);
/* 137 */         } catch (Exception ex) {
/* 138 */           MythicLogger.errorMechanicConfig(this, mlc, "Invalid bullet material specified");
/* 139 */           this.bulletMaterial = Material.STONE;
/*     */         } 
/*     */         break; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     this.bulletSpin = mlc.getFloat(new String[] { "bulletspin", "bspin" }, 0.0F);
/*     */     
/* 153 */     this.tickInterval = mlc.getInteger(new String[] { "interval", "int", "i" }, 4);
/* 154 */     this.ticksPerSecond = 20.0F / this.tickInterval;
/*     */     
/* 156 */     this.hitRadius = mlc.getFloat(new String[] { "horizontalradius", "hradius", "hr", "r" }, 1.25F);
/* 157 */     this.duration = mlc.getPlaceholderInteger(new String[] { "maxduration", "maxduration", "md" }, 400, new String[0]);
/* 158 */     float range = mlc.getFloat(new String[] { "maxrange", "mr" }, 40.0F);
/* 159 */     this.maxDistanceSquared = range * range;
/*     */     
/* 161 */     this.verticalHitRadius = mlc.getFloat(new String[] { "verticalradius", "vradius", "vr" }, this.hitRadius);
/* 162 */     this.startYOffset = mlc.getFloat(new String[] { "startyoffset", "syo" }, 1.0F);
/* 163 */     this.startForwardOffset = mlc.getFloat(new String[] { "forwardoffset", "startfoffset", "sfo", "fo" }, 1.0F) * -1.0F;
/* 164 */     this.startSideOffset = mlc.getFloat(new String[] { "sideoffset", "soffset", "sso", "so" }, 0.0F);
/* 165 */     this.targetYOffset = mlc.getFloat(new String[] { "targetyoffset", "targety", "tyo" }, 0.0F);
/*     */     
/* 167 */     this.projectileVelocity = mlc.getFloat(new String[] { "velocity", "v" }, 5.0F);
/* 168 */     this.projectileVelocityVertOffset = mlc.getFloat(new String[] { "verticaloffset", "vo" }, 0.0F);
/* 169 */     this.projectileVelocityHorizOffset = mlc.getFloat(new String[] { "horizontaloffset", "ho" }, 0.0F);
/*     */     
/* 171 */     this.projectileVelocityAccuracy = mlc.getFloat(new String[] { "accuracy", "ac", "a" }, 1.0F);
/*     */     
/* 173 */     float defNoise = (1.0F - this.projectileVelocityAccuracy) * 45.0F;
/*     */     
/* 175 */     this.projectileVelocityVertNoise = mlc.getFloat(new String[] { "verticalnoise", "vn" }, defNoise) / 10.0F;
/* 176 */     this.projectileVelocityHorizNoise = mlc.getFloat(new String[] { "horizontalnoise", "hn" }, defNoise);
/* 177 */     this.projectileVelocityVertNoiseBase = 0.0F - this.projectileVelocityVertNoise / 2.0F;
/* 178 */     this.projectileVelocityHorizNoiseBase = 0.0F - this.projectileVelocityHorizNoise / 2.0F;
/*     */     
/* 180 */     this.stopOnHitEntity = mlc.getBoolean(new String[] { "stopatentity", "se" }, true);
/* 181 */     this.stopOnHitGround = mlc.getBoolean(new String[] { "stopatblock", "sb" }, true);
/* 182 */     this.powerAffectsVelocity = mlc.getBoolean(new String[] { "poweraffectsvelocity", "pav" }, true);
/* 183 */     this.powerAffectsRange = mlc.getBoolean(new String[] { "poweraffectsrange", "par" }, true);
/*     */     
/* 185 */     this.hitSelf = mlc.getBoolean(new String[] { "hitself" }, false);
/* 186 */     this.hitPlayers = mlc.getBoolean(new String[] { "hitplayers", "hp" }, true);
/* 187 */     this.hitNonPlayers = mlc.getBoolean(new String[] { "hitnonplayers", "hnp" }, false);
/* 188 */     this.hitTarget = mlc.getBoolean(new String[] { "hittarget", "ht" }, true);
/* 189 */     this.hitTargetOnly = mlc.getBoolean(new String[] { "hittargetonly", "hto" }, false);
/*     */     
/* 191 */     this.fromOrigin = mlc.getBoolean(new String[] { "fromorigin", "fo" }, false);
/*     */     
/* 193 */     getPlugin().getSkillManager().queueSecondPass(() -> {
/*     */           if (this.onTickSkillName != null)
/*     */             this.onTickSkill = MythicMobs.inst().getSkillManager().getSkill(this.onTickSkillName); 
/*     */           if (this.onHitSkillName != null)
/*     */             this.onHitSkill = MythicMobs.inst().getSkillManager().getSkill(this.onHitSkillName); 
/*     */           if (this.onEndSkillName != null)
/*     */             this.onEndSkill = MythicMobs.inst().getSkillManager().getSkill(this.onEndSkillName); 
/*     */           if (this.onStartSkillName != null)
/*     */             this.onStartSkill = MythicMobs.inst().getSkillManager().getSkill(this.onStartSkillName); 
/*     */           if (this.bulletType == BulletType.MOB) {
/*     */             String mobType = paramMythicLineConfig.getString(new String[] { "mob", "mobtype", "mm" }, "SkeletalKnight", new String[0]);
/*     */             this.bulletMob = getPlugin().getMobManager().getMythicMob(mobType);
/*     */             if (this.bulletMob == null) {
/*     */               MythicLogger.errorMechanicConfig(this, paramMythicLineConfig, "Invalid bullet mob type specified");
/*     */               this.bulletType = BulletType.NONE;
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\projectiles\Projectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */