/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.projectiles.Projectile;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "projectile", aliases = {"p"}, description = "Launches a custom projectile at the target")
/*    */ public class ProjectileMechanic
/*    */   extends Projectile
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*    */ {
/*    */   protected ProjectileType type;
/*    */   protected float projectileGravity;
/*    */   protected boolean hugSurface;
/*    */   protected float heightFromSurface;
/*    */   
/*    */   public ProjectileMechanic(String skill, MythicLineConfig mlc) {
/* 47 */     super(skill, mlc);
/*    */     
/* 49 */     String type = mlc.getString("type", "NORMAL");
/* 50 */     this.type = ProjectileType.valueOf(type.toUpperCase());
/*    */     
/* 52 */     this.projectileGravity = mlc.getFloat("gravity", 0.0F);
/* 53 */     this.projectileGravity = mlc.getFloat("g", this.projectileGravity);
/*    */     
/* 55 */     this.hugSurface = mlc.getBoolean("hugsurface", false);
/* 56 */     this.hugSurface = mlc.getBoolean("hs", this.hugSurface);
/*    */     
/* 58 */     this.heightFromSurface = mlc.getFloat("heightfromsurface", 0.5F);
/* 59 */     this.heightFromSurface = mlc.getFloat("hfs", this.heightFromSurface);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*    */     try {
/* 66 */       new ProjectileMechanicTracker(this, data, target.clone().add(0.0D, this.targetYOffset, 0.0D));
/* 67 */       return true;
/* 68 */     } catch (Exception ex) {
/* 69 */       MythicMobs.inst().handleException(ex);
/* 70 */       return false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 76 */     return castAtLocation(data, target.getLocation().add(0.0D, target.getEyeHeight() / 2.0D, 0.0D));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ProjectileMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */