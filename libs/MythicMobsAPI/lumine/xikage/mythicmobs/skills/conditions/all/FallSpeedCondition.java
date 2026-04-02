/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "fallSpeed", aliases = {"fallingspeed"}, description = "If the fall speed of the target is within the given range")
/*    */ public class FallSpeedCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "speed", aliases = {"s"}, description = "The velocity to match")
/*    */   private String speed;
/*    */   
/*    */   public FallSpeedCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/*    */     
/* 20 */     this.speed = mlc.getString(new String[] { "speed", "s" }, this.conditionVar, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 25 */     double magnitude = e.getBukkitEntity().getVelocity().length() * 10.0D;
/* 26 */     return MythicUtil.matchNumber(this.speed, magnitude);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\FallSpeedCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */