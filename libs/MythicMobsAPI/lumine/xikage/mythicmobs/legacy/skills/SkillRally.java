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
/*    */ 
/*    */ 
/*    */ public class SkillRally
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 19 */     String[] base = skill.split(" ");
/* 20 */     String[] data = base[1].split(":");
/*    */     
/* 22 */     int radius_xz = Integer.parseInt(data[0]);
/* 23 */     int radius_y = Integer.parseInt(data[1]);
/*    */     
/* 25 */     String[] mobtypes = base[2].split(",");
/*    */     
/* 27 */     List<Entity> moblist = l.getNearbyEntities(radius_xz, radius_y, radius_xz);
/*    */     
/* 29 */     for (Entity e : moblist) {
/* 30 */       if (e instanceof LivingEntity)
/* 31 */         for (String mob : mobtypes) {
/* 32 */           if (e.getType() == EntityType.fromName(mob)) {
/* 33 */             MythicMobs.inst().getVolatileCodeHandler().getAIHandler().setTarget((LivingEntity)e, target);
/*    */           }
/* 35 */           else if (MythicMobs.inst().getMobManager().isActiveMob(e.getUniqueId())) {
/* 36 */             ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(e);
/*    */             
/* 38 */             if (mob.equals(am.getType().getInternalName()))
/* 39 */               MythicMobs.inst().getVolatileCodeHandler().getAIHandler().setTarget((LivingEntity)e, target); 
/*    */           } 
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillRally.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */