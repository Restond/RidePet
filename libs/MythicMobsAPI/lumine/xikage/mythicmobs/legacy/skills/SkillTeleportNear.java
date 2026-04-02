/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillTeleportNear
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 13 */     if (target == null)
/* 14 */       return;  String[] base = skill.split(" ");
/* 15 */     String[] data = base[1].split(":");
/*    */     
/* 17 */     double radius_xz = Double.parseDouble(data[0]);
/* 18 */     double radius_y = Double.parseDouble(data[1]);
/*    */     
/* 20 */     if (data.length > 2) {
/* 21 */       int max_distance = Integer.parseInt(data[2]);
/*    */       
/* 23 */       if (l.getLocation().distanceSquared(target.getLocation()) > (max_distance * max_distance)) {
/*    */         return;
/*    */       }
/*    */     } 
/*    */     
/* 28 */     MythicMobs.inst().getMobManager(); l.teleport(BukkitAdapter.adapt(MobManager.findSafeSpawnLocation(BukkitAdapter.adapt(target.getLocation()), (int)radius_xz, (int)radius_y, (int)l.getEyeHeight() + 1)));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillTeleportNear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */