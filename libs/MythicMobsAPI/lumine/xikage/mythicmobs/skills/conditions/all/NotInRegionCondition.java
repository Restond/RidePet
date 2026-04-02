/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.WorldGuardSupport;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "notInRegion", aliases = {}, description = "If the target location is not within the given WorldGuard region")
/*    */ public class NotInRegionCondition
/*    */   extends SkillCondition implements ILocationCondition {
/*    */   @MythicField(name = "region", aliases = {"r"}, description = "The region name")
/*    */   private String regionName;
/*    */   
/*    */   public NotInRegionCondition(String line, MythicLineConfig mlc) {
/* 19 */     super(line);
/*    */     
/* 21 */     this.regionName = mlc.getString(new String[] { "region", "r", "name", "n" }, this.conditionVar, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation target) {
/* 26 */     if (!MythicMobs.inst().getCompatibility().getWorldGuard().isPresent()) return true;
/*    */     
/* 28 */     return !((WorldGuardSupport)MythicMobs.inst().getCompatibility().getWorldGuard().get()).isLocationInRegions(target, this.regionName);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\NotInRegionCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */