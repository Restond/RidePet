/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillLightningAll
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 12 */     String[] base = skill.split(" ");
/* 13 */     String[] data = base[1].split(":");
/*    */     
/* 15 */     int radius = Integer.parseInt(data[0]);
/* 16 */     int damage = Integer.parseInt(data[1]);
/*    */     
/* 18 */     if (radius > 0)
/* 19 */       for (LivingEntity p : SkillHelper.getLivingEntitiesInRadius(l, radius)) {
/* 20 */         p.getLocation().getWorld().strikeLightningEffect(p.getLocation());
/* 21 */         p.damage(damage);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillLightningAll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */