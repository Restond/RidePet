/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "worldtime", aliases = {}, description = "Matches a range against the target location's world's time.")
/*    */ public class WorldTimeCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition {
/*    */   @MythicField(name = "time", aliases = {"t"}, description = "A range of time to check")
/*    */   private PlaceholderInt time;
/*    */   
/*    */   public WorldTimeCondition(String line, MythicLineConfig mlc) {
/* 21 */     super(line);
/* 22 */     this.time = mlc.getPlaceholderInteger(new String[] { "time", "t" }, 0, new String[] { this.conditionVar });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 28 */     long time = BukkitAdapter.adapt(l.getWorld()).getTime();
/* 29 */     MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "Checking Worldtime: {0} vs {1}", new Object[] { Long.valueOf(time), this.time.toString() });
/* 30 */     return this.time.equals(Long.valueOf(time));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\WorldTimeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */