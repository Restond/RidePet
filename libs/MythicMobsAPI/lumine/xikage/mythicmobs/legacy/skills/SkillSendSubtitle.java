/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillSendSubtitle
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 15 */     String[] base = skill.split(" ");
/* 16 */     String[] data = base[1].split(":");
/* 17 */     String[] temp = skill.split("\"");
/* 18 */     String msg = temp[1];
/*    */     
/* 20 */     int radius = Integer.parseInt(data[0]);
/* 21 */     int timeIn = Integer.parseInt(data[1]);
/* 22 */     int timeSt = Integer.parseInt(data[2]);
/* 23 */     int timeOt = Integer.parseInt(data[3]);
/*    */     
/* 25 */     msg = SkillString.parseMobString(msg, l, target);
/*    */     
/* 27 */     if (radius != 0) {
/* 28 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/* 29 */         MythicMobs.inst().getVolatileCodeHandler().sendTitleToPlayer(p, null, msg, timeIn, timeSt, timeOt);
/*    */       }
/*    */     }
/* 32 */     else if (target instanceof Player) {
/* 33 */       MythicMobs.inst().getVolatileCodeHandler().sendTitleToPlayer((Player)target, null, msg, timeIn, timeSt, timeOt);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSendSubtitle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */