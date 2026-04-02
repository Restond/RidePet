/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IPathSelector;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.SelectorType;
/*    */ 
/*    */ public class MPLocation extends SkillTargeter implements IPathSelector {
/*    */   public MPLocation(MythicLineConfig mlc) {
/*  9 */     super(mlc);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SelectorType getType() {
/* 15 */     return SelectorType.LOCATION;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MPLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */