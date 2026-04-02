/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillMessage
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 15 */     String[] base = skill.split(" ");
/* 16 */     String[] data = base[1].split(":");
/* 17 */     String[] temp = skill.split("'");
/* 18 */     String msg = temp[1];
/*    */     
/* 20 */     int radius = Integer.parseInt(data[0]);
/*    */     
/* 22 */     msg = SkillString.parseMobString(msg, l, target);
/*    */     
/* 24 */     if (radius != 0) {
/* 25 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/* 26 */         p.sendMessage(msg);
/*    */       }
/*    */     } else {
/* 29 */       Bukkit.broadcastMessage(msg);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */