/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillMountPlayer
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity p) {
/* 11 */     if (p instanceof org.bukkit.entity.Player)
/* 12 */       p.setPassenger((Entity)l); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillMountPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */