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
/*    */ public class SkillSendTitle
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 15 */     String[] base = skill.split(" ");
/* 16 */     String[] data = base[1].split(":");
/*    */     
/* 18 */     int radius = Integer.parseInt(data[0]);
/* 19 */     int timeIn = Integer.parseInt(data[1]);
/* 20 */     int timeSt = Integer.parseInt(data[2]);
/* 21 */     int timeOt = Integer.parseInt(data[3]);
/* 22 */     String title = null;
/* 23 */     String subtitle = null;
/*    */     
/* 25 */     if (data.length > 4) {
/* 26 */       String[] temp1 = data[4].split("\"");
/* 27 */       title = SkillString.parseMobString(temp1[1], l, target);
/*    */     } 
/*    */     
/* 30 */     if (data.length > 5) {
/* 31 */       String[] temp2 = data[5].split("\"");
/* 32 */       subtitle = SkillString.parseMobString(temp2[1], l, target);
/*    */     } 
/*    */     
/* 35 */     if (radius != 0) {
/* 36 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/* 37 */         MythicMobs.inst().getVolatileCodeHandler().sendTitleToPlayer(p, title, subtitle, timeIn, timeSt, timeOt);
/*    */       }
/*    */     }
/* 40 */     else if (target instanceof Player) {
/* 41 */       MythicMobs.inst().getVolatileCodeHandler().sendTitleToPlayer((Player)target, title, subtitle, timeIn, timeSt, timeOt);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSendTitle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */