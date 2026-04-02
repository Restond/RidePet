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
/*    */ @MythicTargeter(author = "Ashijin", name = "forward", aliases = {}, description = "Targets a point in front of the caster")
/*    */ public class ForwardTargeter
/*    */   extends ILocationSelector {
/*    */   protected double forward;
/*    */   protected double yoffset;
/*    */   
/*    */   public ForwardTargeter(MythicLineConfig mlc) {
/* 18 */     super(mlc);
/*    */     
/* 20 */     this.forward = mlc.getDouble(new String[] { "forward", "f", "amount", "a" }, 5.0D);
/* 21 */     this.yoffset = mlc.getDouble(new String[] { "yoffset", "y" }, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
/* 26 */     SkillCaster am = data.getCaster();
/* 27 */     HashSet<AbstractLocation> targets = new HashSet<>();
/*    */ 
/*    */ 
/*    */     
/* 31 */     AbstractLocation location = am.getEntity().getLocation().add(am.getEntity().getLocation().getDirection().normalize().multiply(this.forward)).add(0.0D, this.yoffset, 0.0D);
/*    */     
/* 33 */     targets.add(location);
/*    */     
/* 35 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\ForwardTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */