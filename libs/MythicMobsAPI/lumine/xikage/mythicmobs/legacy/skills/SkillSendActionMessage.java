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
/*    */ public class SkillSendActionMessage
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 15 */     String[] base = skill.split(" ");
/* 16 */     String[] data = base[1].split(":");
/*    */     
/* 18 */     int radius = Integer.parseInt(data[0]);
/*    */     
/* 20 */     String[] temp1 = data[1].split("\"");
/* 21 */     String title = SkillString.parseMobString(temp1[1], l, target);
/*    */     
/* 23 */     if (radius != 0) {
/* 24 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/* 25 */         MythicMobs.inst().getVolatileCodeHandler().sendActionBarMessageToPlayer(p, title);
/*    */       }
/*    */     }
/* 28 */     else if (target instanceof Player) {
/* 29 */       MythicMobs.inst().getVolatileCodeHandler().sendActionBarMessageToPlayer((Player)target, title);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSendActionMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */