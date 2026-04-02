/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class SkillCommand
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 11 */     String[] temp = skill.split("'");
/* 12 */     String msg = temp[1];
/*    */     
/* 14 */     msg = SkillString.parseMobString(msg, l, target);
/*    */     
/* 16 */     Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), msg);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */