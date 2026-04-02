/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillRemoveMobs
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/*    */     int radius_y;
/* 18 */     String[] base = skill.split(" ");
/* 19 */     String[] data = base[1].split(":");
/*    */     
/* 21 */     int radius_xz = Integer.parseInt(data[0]);
/*    */     
/*    */     try {
/* 24 */       radius_y = Integer.parseInt(data[1]);
/* 25 */     } catch (Exception e) {
/* 26 */       radius_y = radius_xz;
/*    */     } 
/*    */     
/* 29 */     String[] mobtypes = base[2].split(",");
/*    */     
/* 31 */     List<Entity> moblist = l.getNearbyEntities(radius_xz, radius_y, radius_xz);
/*    */     
/* 33 */     for (Entity e : moblist) {
/* 34 */       if (e instanceof LivingEntity)
/* 35 */         for (String mob : mobtypes) {
/* 36 */           if (e.getType() == EntityType.fromName(mob)) {
/* 37 */             e.remove();
/*    */           }
/* 39 */           else if (MythicMobs.inst().getMobManager().isActiveMob(e.getUniqueId())) {
/* 40 */             ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(e);
/*    */             
/* 42 */             if (mob.equals(am.getType().getInternalName())) {
/* 43 */               am.setDespawned();
/* 44 */               MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/* 45 */               e.remove();
/*    */             } 
/*    */           } 
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillRemoveMobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */