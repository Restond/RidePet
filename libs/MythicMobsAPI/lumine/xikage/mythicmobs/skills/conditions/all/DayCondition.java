/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "day", aliases = {}, description = "If the time is day, from 2000 to 10000 in-game time")
/*    */ public class DayCondition
/*    */   extends SkillCondition implements ILocationCondition {
/*    */   public DayCondition(String line, MythicLineConfig mlc) {
/* 14 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 19 */     long time = BukkitAdapter.adapt(l.getWorld()).getTime();
/*    */     
/* 21 */     if (time >= 2000L && time <= 10000L) {
/* 22 */       return true;
/*    */     }
/* 24 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\DayCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */