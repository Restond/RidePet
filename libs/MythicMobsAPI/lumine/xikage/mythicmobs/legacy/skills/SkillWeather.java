/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillWeather
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 12 */     String[] base = skill.split(" ");
/* 13 */     String[] data = base[1].split(":");
/*    */     
/* 15 */     String type = data[0];
/*    */     
/* 17 */     if (type == "rain") {
/* 18 */       l.getWorld().setStorm(true);
/* 19 */     } else if (type == "thunder") {
/* 20 */       l.getWorld().setStorm(true);
/* 21 */       l.getWorld().setThundering(true);
/*    */     } else {
/* 23 */       l.getWorld().setStorm(false);
/*    */     } 
/*    */     
/* 26 */     if (data.length >= 2)
/*    */       try {
/* 28 */         l.getWorld().setWeatherDuration(Integer.parseInt(data[1]));
/* 29 */       } catch (Exception e) {
/* 30 */         MythicMobs.error("Weather skill duration must be an integer.");
/*    */       }  
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillWeather.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */