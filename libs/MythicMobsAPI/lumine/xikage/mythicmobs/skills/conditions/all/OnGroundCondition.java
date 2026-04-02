/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "onGround", aliases = {"grounded"}, description = "If the target entity is standing on solid ground")
/*    */ public class OnGroundCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   public OnGroundCondition(String line, MythicLineConfig mlc) {
/* 13 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 18 */     return e.getBukkitEntity().isOnGround();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\OnGroundCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */