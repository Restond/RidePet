/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillSpawnPassenger
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 12 */     String[] base = skill.split(" ");
/*    */     
/* 14 */     String rider = base[1];
/*    */     
/* 16 */     if (MythicMobs.inst().getMobManager().getMythicMob(rider) != null) {
/* 17 */       Entity mm = MythicMobs.inst().getMobManager().spawnMob(rider, l.getLocation()).getEntity().getBukkitEntity();
/*    */       
/* 19 */       l.setPassenger(mm);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSpawnPassenger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */