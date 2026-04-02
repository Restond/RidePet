/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "heightAbove", aliases = {}, description = "Checks if the target's Y location is above a value")
/*    */ public class HeightAboveCondition
/*    */   extends SkillCondition implements ILocationCondition {
/*    */   @MythicField(name = "height", aliases = {"h"}, description = "The height they must be above")
/*    */   private int data;
/*    */   
/*    */   public HeightAboveCondition(String line, MythicLineConfig mlc) {
/* 17 */     super(line);
/*    */     
/* 19 */     this.data = mlc.getInteger(new String[] { "height", "h" }, Integer.valueOf(this.conditionVar).intValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 24 */     if (l.getBlockY() >= this.data) {
/* 25 */       return true;
/*    */     }
/* 27 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HeightAboveCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */