/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IPathSelector;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.SelectorType;
/*    */ 
/*    */ public class MPEntity extends SkillTargeter implements IPathSelector {
/*    */   public MPEntity(MythicLineConfig mlc) {
/*  9 */     super(mlc);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SelectorType getType() {
/* 15 */     return SelectorType.ENTITY;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MPEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */