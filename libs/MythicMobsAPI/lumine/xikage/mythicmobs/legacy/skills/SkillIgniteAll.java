/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillIgniteAll
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 12 */     String[] base = skill.split(" ");
/* 13 */     String[] data = base[1].split(":");
/*    */     
/* 15 */     int radius = Integer.parseInt(data[0]);
/* 16 */     int ticks = Integer.parseInt(data[1]);
/*    */     
/* 18 */     for (LivingEntity p : SkillHelper.getLivingEntitiesInRadius(l, radius))
/* 19 */       p.setFireTicks(ticks); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillIgniteAll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */