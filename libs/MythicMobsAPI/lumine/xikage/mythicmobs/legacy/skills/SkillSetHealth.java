/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillSetHealth
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
/* 19 */       MythicMobs.error("Value of SetHealth AbstractSkill is set incorrectly. AbstractSkill=" + skill);
/*    */       
/*    */       return;
/*    */     } 
/* 23 */     if (health > l.getMaxHealth()) {
/* 24 */       l.setMaxHealth(health);
/*    */     }
/* 26 */     l.setHealth(health);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSetHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */