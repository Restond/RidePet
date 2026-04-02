/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public abstract class ILocationSelector
/*    */   extends SkillTargeter
/*    */ {
/*    */   public ILocationSelector(MythicLineConfig mlc) {
/* 13 */     super(mlc);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void filter(SkillMetadata data) {
/* 20 */     data.getLocationTargets().forEach(t -> {
/*    */         
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractLocation mutate(AbstractLocation location) {
/* 29 */     return location;
/*    */   }
/*    */   
/*    */   public abstract HashSet<AbstractLocation> getLocations(SkillMetadata paramSkillMetadata);
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\ILocationSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */