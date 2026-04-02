/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "origin", aliases = {"source"}, description = "Targets the origin of the current skill")
/*    */ public class OriginTargeter
/*    */   extends SkillTargeter
/*    */ {
/*    */   public OriginTargeter(MythicLineConfig mlc) {
/* 14 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractLocation> getLocation(AbstractLocation origin) {
/* 19 */     HashSet<AbstractLocation> targets = new HashSet<>();
/*    */     
/* 21 */     targets.add(origin.clone());
/*    */     
/* 23 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\OriginTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */