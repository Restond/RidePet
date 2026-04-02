/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillTeleport
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 11 */     if (target == null)
/* 12 */       return;  String[] base = skill.split(" ");
/*    */ 
/*    */     
/* 15 */     if (base.length > 3) {
/* 16 */       int max_distance = Integer.parseInt(base[1]);
/*    */       
/* 18 */       if (l.getLocation().distanceSquared(target.getLocation()) > (max_distance * max_distance)) {
/*    */         return;
/*    */       }
/*    */     } 
/*    */     
/* 23 */     l.teleport(target.getLocation());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */