/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillCommand;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillCommandRing
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 14 */     String[] base = skill.split(" ");
/* 15 */     String[] data = base[1].split(":");
/*    */     
/* 17 */     int min_radius = Integer.parseInt(data[0]);
/* 18 */     int max_radius = Integer.parseInt(data[1]);
/*    */     
/* 20 */     if (min_radius > 0 && max_radius > 0)
/* 21 */       for (Player p : SkillHelper.getPlayersInRadius(l, max_radius)) {
/* 22 */         if (l.getLocation().distance(p.getLocation()) > min_radius)
/* 23 */           SkillCommand.ExecuteSkill(l, skill, (LivingEntity)p); 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillCommandRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */