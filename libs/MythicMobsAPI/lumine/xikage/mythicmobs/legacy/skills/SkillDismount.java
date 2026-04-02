/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillDismount
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/*    */     try {
/* 12 */       l.getVehicle().eject();
/* 13 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillDismount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */