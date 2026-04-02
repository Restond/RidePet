/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "playerWithin", aliases = {"playerswithin"}, description = "Checks if any players are within a radius of the target")
/*    */ public class PlayerWithinCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition
/*    */ {
/*    */   @MythicField(name = "distance", aliases = {"d"}, description = "The radius to check in")
/*    */   private double distance;
/*    */   
/*    */   public PlayerWithinCondition(String line, MythicLineConfig mlc) {
/* 22 */     super(line);
/* 23 */     this.distance = Math.pow(Double.valueOf(mlc.getString(new String[] { "distance", "d" }, "0", new String[] { this.conditionVar })).doubleValue(), 2.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation location) {
/* 28 */     Location l = BukkitAdapter.adapt(location);
/*    */     
/* 30 */     for (Player p : l.getWorld().getPlayers()) {
/* 31 */       if (l.distanceSquared(p.getLocation()) <= this.distance) return true; 
/*    */     } 
/* 33 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\PlayerWithinCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */