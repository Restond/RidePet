/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ public class SkillTeleportLocation
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 10 */     String[] base = skill.split(" ");
/* 11 */     String[] data = base[1].split(":");
/*    */     
/* 13 */     double x = Double.parseDouble(data[0]);
/* 14 */     double y = Double.parseDouble(data[1]);
/* 15 */     double z = Double.parseDouble(data[2]);
/*    */     
/* 17 */     Location loc = new Location(l.getWorld(), x, y, z);
/*    */     
/* 19 */     l.teleport(loc);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillTeleportLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */