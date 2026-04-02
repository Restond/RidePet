/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityComparisonCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityLocationComparisonCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "fieldOfView", aliases = {"infieldofview", "fov"}, version = "4.3", description = "Tests if the target is within the given angle from where the caster is looking")
/*    */ public class FieldOfViewCondition
/*    */   extends SkillCondition
/*    */   implements IEntityComparisonCondition, IEntityLocationComparisonCondition {
/*    */   @MythicField(name = "angle", aliases = {"a"}, description = "The angle of the FOV to check in")
/*    */   private double angle;
/*    */   @MythicField(name = "rotation", aliases = {"r"}, description = "Rotates the FOV to check in")
/*    */   private double rotation;
/*    */   
/*    */   public FieldOfViewCondition(String line, MythicLineConfig mlc) {
/* 23 */     super(line);
/*    */     
/* 25 */     double a = mlc.getDouble(new String[] { "angle", "a" }, (this.conditionVar == null) ? 90.0D : Double.valueOf(this.conditionVar).doubleValue());
/* 26 */     this.angle = a / 2.0D;
/*    */     
/* 28 */     this.rotation = mlc.getDouble(new String[] { "rotation", "r" }, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity, AbstractEntity target) {
/* 33 */     AbstractVector entityVector = entity.getEyeLocation().toVector();
/* 34 */     AbstractVector targetVector = target.getEyeLocation().toVector();
/* 35 */     AbstractVector headDirection = entity.getEyeLocation().getDirection();
/*    */     
/* 37 */     AbstractVector targetDirection = targetVector.subtract(entityVector).normalize();
/* 38 */     double targetAngle = Math.toDegrees(targetDirection.angle(headDirection));
/*    */ 
/*    */ 
/*    */     
/* 42 */     return (targetAngle <= this.angle);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity, AbstractLocation target) {
/* 47 */     AbstractVector entityVector = entity.getEyeLocation().toVector();
/* 48 */     AbstractVector targetVector = target.toVector();
/* 49 */     AbstractVector headDirection = entity.getEyeLocation().getDirection();
/*    */     
/* 51 */     AbstractVector targetDirection = targetVector.subtract(entityVector).normalize();
/* 52 */     double targetAngle = Math.toDegrees(targetDirection.angle(headDirection));
/*    */ 
/*    */ 
/*    */     
/* 56 */     return (targetAngle <= this.angle);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\FieldOfViewCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */