/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillHealSelf
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 11 */     String[] base = skill.split(" ");
/*    */     
/* 13 */     double amount = Float.parseFloat(base[1]);
/* 14 */     double health = l.getHealth() + amount;
/* 15 */     if (health >= l.getMaxHealth()) {
/* 16 */       l.setHealth(l.getMaxHealth());
/*    */     } else {
/* 18 */       l.setHealth(health);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillHealSelf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */