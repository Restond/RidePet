/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "selfLocation", aliases = {"casterLocation", "bossLocation", "mobLocation"}, description = "Targets the location of the caster")
/*    */ public class SelfLocationTargeter
/*    */   extends ILocationSelector {
/*    */   double yoffset;
/*    */   
/*    */   public SelfLocationTargeter(MythicLineConfig mlc) {
/* 17 */     super(mlc);
/*    */     
/* 19 */     this.yoffset = mlc.getDouble("yoffset", 0.0D);
/* 20 */     this.yoffset = mlc.getDouble("y", this.yoffset);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
/* 25 */     SkillCaster am = data.getCaster();
/* 26 */     HashSet<AbstractLocation> targets = new HashSet<>();
/*    */     
/* 28 */     targets.add(am.getEntity().getLocation().add(0.0D, this.yoffset, 0.0D));
/*    */     
/* 30 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\SelfLocationTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */