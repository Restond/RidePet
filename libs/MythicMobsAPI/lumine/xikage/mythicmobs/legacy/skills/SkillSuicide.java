/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillSuicide
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 11 */     l.setHealth(0.0D);
/* 12 */     l.damage(10.0D);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSuicide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */