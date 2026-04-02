/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillSetMaxHealth
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/*    */     double health;
/* 12 */     String[] base = skill.split(" ");
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 17 */       health = Double.parseDouble(base[1]);
/* 18 */     } catch (Exception e) {
/* 19 */       MythicMobs.error("Value of SetMaxHealth AbstractSkill is set incorrectly. AbstractSkill=" + skill);
/*    */       return;
/*    */     } 
/* 22 */     l.setMaxHealth(health);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSetMaxHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */