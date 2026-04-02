/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "moving", aliases = {"ismoving"}, description = "If the target has a velocity greater than zero")
/*    */ public class MovingCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition
/*    */ {
/*    */   public MovingCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 24 */     boolean isMoving = getPlugin().getVolatileCodeHandler().getEntityHandler().isEntityInMotion(entity);
/* 25 */     MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "Is Entity Moving? {0}", new Object[] { String.valueOf(isMoving) });
/* 26 */     return isMoving;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\MovingCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */