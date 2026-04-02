/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "dawn", aliases = {}, description = "If the time is dawn, from 22000 to 2000 in-game time")
/*    */ public class DawnCondition
/*    */   extends SkillCondition implements ILocationCondition {
/*    */   public DawnCondition(String line, MythicLineConfig mlc) {
/* 14 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 19 */     long time = BukkitAdapter.adapt(l.getWorld()).getTime();
/*    */     
/* 21 */     if (time > 22000L && time < 2000L) {
/* 22 */       return true;
/*    */     }
/* 24 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\DawnCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */