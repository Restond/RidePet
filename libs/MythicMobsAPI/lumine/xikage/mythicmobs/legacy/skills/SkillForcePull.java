/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillForcePull
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 14 */     if (target == null)
/* 15 */       return;  String[] base = skill.split(" ");
/* 16 */     String[] data = base[1].split(":");
/*    */ 
/*    */     
/* 19 */     int radius = Integer.parseInt(data[0]);
/*    */     
/* 21 */     if (radius > 0) {
/*    */       
/* 23 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius))
/*    */       {
/* 25 */         p.teleport(l.getLocation());
/*    */       }
/*    */     } else {
/* 28 */       target.teleport(l.getLocation());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillForcePull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */