/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillIgnite
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 13 */     String[] base = skill.split(" ");
/* 14 */     String[] data = base[1].split(":");
/*    */     
/* 16 */     int radius = Integer.parseInt(data[0]);
/* 17 */     int ticks = Integer.parseInt(data[1]);
/*    */     
/* 19 */     if (radius > 0) {
/* 20 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/* 21 */         p.setFireTicks(ticks);
/*    */       }
/*    */     } else {
/* 24 */       if (target == null)
/* 25 */         return;  target.setFireTicks(ticks);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillIgnite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */