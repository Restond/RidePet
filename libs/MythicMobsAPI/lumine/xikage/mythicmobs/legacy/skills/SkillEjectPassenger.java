/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillEjectPassenger
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/*    */     try {
/* 11 */       l.eject();
/* 12 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillEjectPassenger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */