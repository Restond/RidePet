/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "playerNotWithin", aliases = {"playersnotwithin"}, description = "Checks if any players are within a radius of the target")
/*    */ public class PlayerNotWithinCondition
/*    */   extends SkillCondition implements ILocationCondition {
/*    */   @MythicField(name = "distance", aliases = {"d"}, description = "The radius to check in")
/*    */   private double distance;
/*    */   
/*    */   public PlayerNotWithinCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/* 19 */     this.distance = Math.pow(Double.valueOf(mlc.getString(new String[] { "distance", "d" }, "0", new String[] { this.conditionVar })).doubleValue(), 2.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation location) {
/* 24 */     for (AbstractPlayer p : location.getWorld().getPlayers()) {
/* 25 */       if (p.getWorld().equals(location.getWorld()) && 
/* 26 */         location.distanceSquared(p.getLocation()) <= this.distance) return false; 
/*    */     } 
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\PlayerNotWithinCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */