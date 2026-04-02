/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.SkillAdapter;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Egg;
/*     */ import org.bukkit.entity.EnderPearl;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.entity.Snowball;
/*     */ import org.bukkit.entity.ThrownPotion;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "shoot", aliases = {"shootprojetile"}, description = "Shoots a projectile at the target location")
/*     */ public class ShootMechanic
/*     */   extends SkillMechanic
/*     */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*     */ {
/*  42 */   protected ProjectileType projectileType = ProjectileType.REGULAR;
/*     */   
/*     */   protected int damage;
/*     */   
/*     */   protected int maxdistance;
/*     */   protected float velocity;
/*     */   protected double hSpread;
/*     */   protected double vSpread;
/*     */   protected boolean powerAffectsVelocity;
/*     */   protected float startYOffset;
/*     */   protected Class<? extends Projectile> projectileClass;
/*     */   protected ItemStack projectileItem;
/*  54 */   protected Optional<Skill> onTickSkill = Optional.empty();
/*  55 */   protected Optional<Skill> onHitSkill = Optional.empty();
/*  56 */   protected Optional<Skill> onEndSkill = Optional.empty();
/*     */   
/*     */   protected int tickInterval;
/*     */   protected String onTickSkillName;
/*     */   protected String onHitSkillName;
/*     */   protected String onEndSkillName;
/*     */   protected String onStartSkillName;
/*     */   protected boolean bounce;
/*     */   protected boolean gravity;
/*     */   protected boolean calculateFiringAngle;
/*     */   protected boolean fromOrigin;
/*  67 */   protected Optional<String> disguise = Optional.empty();
/*     */   
/*     */   public ShootMechanic(String line, MythicLineConfig mlc) {
/*  70 */     super(line, mlc);
/*  71 */     this.ASYNC_SAFE = false;
/*     */     
/*  73 */     String strProjectile = mlc.getString(new String[] { "type", "t" }, "arrow", new String[0]);
/*  74 */     this.damage = mlc.getInteger(new String[] { "damage", "d" }, 5);
/*  75 */     this.maxdistance = mlc.getInteger(new String[] { "maxdistance", "md" }, 64);
/*  76 */     this.velocity = mlc.getFloat(new String[] { "velocity", "v" }, 1.0F);
/*  77 */     this.vSpread = mlc.getDouble(new String[] { "vspread", "vs" }, 0.0D);
/*  78 */     this.hSpread = mlc.getDouble(new String[] { "hspread", "hs" }, 0.0D);
/*  79 */     this.powerAffectsVelocity = mlc.getBoolean(new String[] { "poweraffectsvelocity", "pav" }, true);
/*  80 */     this.gravity = mlc.getBoolean(new String[] { "gravity", "g" }, true);
/*     */     
/*  82 */     this.tickInterval = mlc.getInteger(new String[] { "interval", "int", "i" }, 4);
/*     */     
/*  84 */     this.startYOffset = mlc.getFloat("startyoffset", 0.0F);
/*  85 */     this.startYOffset = mlc.getFloat("syo", this.startYOffset);
/*     */     
/*  87 */     this.fromOrigin = mlc.getBoolean(new String[] { "fromorigin", "fo" }, false);
/*  88 */     this.calculateFiringAngle = mlc.getBoolean(new String[] { "calculatefiringangle", "cfa" }, false);
/*  89 */     this.disguise = Optional.ofNullable(mlc.getString(new String[] { "disguise", "d" }, null, new String[0]));
/*     */     
/*  91 */     switch (strProjectile.toUpperCase()) {
/*     */       case "ARROW":
/*  93 */         this.projectileClass = (Class)Arrow.class;
/*     */         break;
/*     */       case "SNOWBALL":
/*  96 */         this.projectileClass = (Class)Snowball.class;
/*     */         break;
/*     */       case "EGG":
/*  99 */         this.projectileClass = (Class)Egg.class;
/*     */         break;
/*     */       case "ENDERPEARL":
/* 102 */         this.projectileClass = (Class)EnderPearl.class;
/*     */         break;
/*     */       case "POTION":
/* 105 */         this.projectileClass = (Class)ThrownPotion.class;
/*     */         break;
/*     */       case "ITEM":
/* 108 */         this.projectileType = ProjectileType.ITEM; break;
/*     */       case "BLOCK":
/*     */       case "FALLING_BLOCK":
/* 111 */         this.projectileType = ProjectileType.BLOCK;
/*     */         break;
/*     */       default:
/* 114 */         this.projectileClass = (Class)Arrow.class;
/*     */         break;
/*     */     } 
/*     */     
/* 118 */     this.onTickSkillName = mlc.getString(new String[] { "ontickskill", "ontick", "ot", "skill", "s", "meta", "m" });
/* 119 */     this.onHitSkillName = mlc.getString(new String[] { "onhitskill", "onhit", "oh" });
/* 120 */     this.onEndSkillName = mlc.getString(new String[] { "onendskill", "onend", "oe" });
/*     */     
/* 122 */     Schedulers.async().runLater(() -> { if (this.onTickSkillName != null) this.onTickSkill = MythicMobs.inst().getSkillManager().getSkill(this.onTickSkillName);  MythicMobs.debug(3, "-- Loaded SkillSkill pointing at " + this.onTickSkillName); if (this.onHitSkillName != null) this.onHitSkill = MythicMobs.inst().getSkillManager().getSkill(this.onHitSkillName);  MythicMobs.debug(3, "-- Loaded SkillSkill pointing at " + this.onHitSkillName); if (this.onEndSkillName != null) this.onEndSkill = MythicMobs.inst().getSkillManager().getSkill(this.onEndSkillName);  MythicMobs.debug(3, "-- Loaded SkillSkill pointing at " + this.onEndSkillName); }200L);
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
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 134 */     return shoot(data, target.getLocation());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 139 */     return shoot(data, target);
/*     */   }
/*     */   public boolean shoot(SkillMetadata data, AbstractLocation target) {
/*     */     AbstractEntity projectile;
/* 143 */     SkillCaster caster = data.getCaster();
/* 144 */     AbstractLocation origin = data.getOrigin();
/* 145 */     float power = data.getPower();
/*     */     
/* 147 */     ProjectileData pd = new ProjectileData(caster, this.damage, this.onHitSkill, this.onEndSkill, power);
/*     */     
/* 149 */     if (!this.onTickSkill.isPresent() && this.onTickSkillName != null) {
/* 150 */       this.onTickSkill = MythicMobs.inst().getSkillManager().getSkill(this.onTickSkillName);
/*     */     }
/* 152 */     if (!this.onHitSkill.isPresent() && this.onHitSkillName != null) {
/* 153 */       this.onHitSkill = MythicMobs.inst().getSkillManager().getSkill(this.onHitSkillName);
/*     */     }
/* 155 */     if (!this.onEndSkill.isPresent() && this.onEndSkillName != null) {
/* 156 */       this.onEndSkill = MythicMobs.inst().getSkillManager().getSkill(this.onEndSkillName);
/*     */     }
/*     */     
/* 159 */     float v = this.velocity;
/*     */     
/* 161 */     if (this.powerAffectsVelocity) {
/* 162 */       v *= power;
/*     */     }
/*     */     
/* 165 */     if (this.startYOffset > 0.0F) {
/* 166 */       origin = origin.clone().add(0.0D, this.startYOffset, 0.0D);
/*     */     }
/*     */ 
/*     */     
/* 170 */     if (this.projectileType.equals(ProjectileType.ITEM)) {
/* 171 */       projectile = SkillAdapter.get().shootProjectile(caster, target, origin, this.projectileClass, v, pd, this.fromOrigin);
/* 172 */     } else if (this.projectileType.equals(ProjectileType.BLOCK)) {
/* 173 */       projectile = SkillAdapter.get().shootProjectile(caster, target, origin, this.projectileClass, v, pd, this.fromOrigin);
/*     */     }
/* 175 */     else if (this.calculateFiringAngle && this.gravity) {
/* 176 */       projectile = SkillAdapter.get().shootArcProjectile(caster, target, origin, this.projectileClass, v, pd, this.fromOrigin);
/*     */     } else {
/* 178 */       projectile = SkillAdapter.get().shootProjectile(caster, target, origin, this.projectileClass, v, pd, this.fromOrigin);
/*     */     } 
/*     */     
/* 181 */     if (!this.gravity) {
/* 182 */       projectile.setGravity(false);
/*     */     }
/* 184 */     if (this.disguise.isPresent()) {
/* 185 */       MythicMobs.inst().getCompatibility(); if (CompatibilityManager.LibsDisguises.enabled);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 190 */     new ProjectileTracker(this, data, projectile, power);
/*     */     
/* 192 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ShootMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */