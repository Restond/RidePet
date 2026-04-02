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
/*    */ public class SkillCommandRadius
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 14 */     String[] base = skill.split(" ");
/* 15 */     String[] data = base[1].split(":");
/*    */     
/* 17 */     int radius = Integer.parseInt(data[0]);
/*    */     
/* 19 */     if (radius > 0)
/* 20 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius))
/* 21 */         SkillCommand.ExecuteSkill(l, skill, (LivingEntity)p);  
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillCommandRadius.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */