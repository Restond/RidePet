/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
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
/*    */ @MythicMechanic(author = "Ashijin", name = "missile", aliases = {"mi"}, description = "Shoots a homing missile at the target entity")
/*    */ public class MissileMechanic
/*    */   extends Projectile
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   protected float projectileInertia;
/*    */   
/*    */   public MissileMechanic(String skill, MythicLineConfig mlc) {
/* 27 */     super(skill, mlc);
/*    */     
/* 29 */     this.projectileInertia = mlc.getFloat(new String[] { "intertia", "inertia", "in" }, 1.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*    */     try {
/* 35 */       new MissileTracker(this, data, target);
/* 36 */       return true;
/* 37 */     } catch (Exception ex) {
/* 38 */       MythicMobs.inst().handleException(ex);
/* 39 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\MissileMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */