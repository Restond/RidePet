/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillExplosion
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 13 */     String[] base = skill.split(" ");
/* 14 */     String[] data = base[1].split(":");
/*    */     
/* 16 */     int radius = Integer.parseInt(data[0]);
/* 17 */     int power = Integer.parseInt(data[1]);
/*    */     
/* 19 */     boolean fire = false;
/* 20 */     if (data.length >= 3) {
/* 21 */       fire = Boolean.valueOf(data[3]).booleanValue();
/*    */     }
/*    */     
/* 24 */     boolean destroyblocks = false;
/* 25 */     if (data.length >= 4) {
/* 26 */       destroyblocks = Boolean.valueOf(data[3]).booleanValue();
/*    */     }
/*    */     
/* 29 */     if (radius > 0) {
/* 30 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/* 31 */         p.getLocation().getWorld().createExplosion(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), power, fire, destroyblocks);
/*    */       }
/*    */     } else {
/* 34 */       if (target == null)
/* 35 */         return;  target.getLocation().getWorld().createExplosion(target.getLocation().getX(), target.getLocation().getY(), target.getLocation().getZ(), power, fire, destroyblocks);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillExplosion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */