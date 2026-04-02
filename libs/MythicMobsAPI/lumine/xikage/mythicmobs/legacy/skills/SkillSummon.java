/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillSummon
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/*    */     int radius;
/* 17 */     String[] base = skill.split(" ");
/* 18 */     String[] data = base[1].split(":");
/*    */     
/* 20 */     int amount = Integer.parseInt(data[1]);
/*    */ 
/*    */     
/* 23 */     if (data.length > 1) {
/* 24 */       radius = Integer.parseInt(data[2]);
/*    */     } else {
/* 26 */       radius = 1;
/*    */     } 
/*    */     
/* 29 */     if (data[0].contains("$")) {
/* 30 */       String s = data[0].replace("$", "");
/* 31 */       MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(s);
/*    */ 
/*    */       
/* 34 */       if (mm != null) {
/* 35 */         for (int i = 1; i <= amount; i++) {
/* 36 */           MythicMobs.inst().getMobManager(); Location loc = BukkitAdapter.adapt(MobManager.findSafeSpawnLocation(BukkitAdapter.adapt(l.getLocation()), radius, 2));
/*    */           
/* 38 */           MythicMobs.inst().getMobManager(); MythicMobs.inst().getMobManager().spawnMob(s, loc, MobManager.getMythicMobLevel(l));
/*    */         }
/*    */       
/*    */       }
/*    */     } else {
/*    */       
/* 44 */       for (int i = 0; i <= amount; i++) {
/* 45 */         MythicMobs.inst().getMobManager(); Location loc = BukkitAdapter.adapt(MobManager.findSafeSpawnLocation(BukkitAdapter.adapt(l.getLocation()), radius, 2));
/* 46 */         loc.getWorld().spawnEntity(loc, EntityType.valueOf(data[0].toUpperCase()));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSummon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */