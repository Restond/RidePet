/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "height", aliases = {}, description = "Checks if the target's Y location is within a range")
/*    */ public class HeightCondition
/*    */   extends SkillCondition implements ILocationCondition {
/*    */   private String data;
/*    */   
/*    */   public HeightCondition(String line, MythicLineConfig mlc) {
/* 16 */     super(line);
/* 17 */     this.data = this.conditionVar;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 22 */     double height = l.getY();
/*    */     
/* 24 */     if (MythicUtil.matchNumber(this.data, height)) {
/* 25 */       return true;
/*    */     }
/* 27 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HeightCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */