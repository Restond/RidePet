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
/*    */ public class SkillConsume
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 18 */     String[] base = skill.split(" ");
/* 19 */     String[] data = base[1].split(":");
/*    */     
/* 21 */     int radius_xz = Integer.parseInt(data[0]);
/* 22 */     int radius_y = Integer.parseInt(data[1]);
/* 23 */     int damage = Integer.parseInt(data[2]);
/* 24 */     int heal = Integer.parseInt(data[3]);
/*    */     
/* 26 */     String[] mobtypes = base[2].split(",");
/*    */     
/* 28 */     List<Entity> moblist = l.getNearbyEntities(radius_xz, radius_y, radius_xz);
/*    */     
/* 30 */     for (Entity e : moblist) {
/* 31 */       if (e instanceof LivingEntity) {
/* 32 */         for (String mob : mobtypes) {
/* 33 */           if (e.getType() == EntityType.fromName(mob)) {
/* 34 */             ConsumeMob(l, (LivingEntity)e, damage, heal);
/*    */           }
/* 36 */           else if (MythicMobs.inst().getMobManager().isActiveMob(e.getUniqueId())) {
/* 37 */             ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(e);
/*    */             
/* 39 */             if (mob.equals(am.getType().getInternalName())) {
/* 40 */               ConsumeMob(l, (LivingEntity)e, damage, heal);
/*    */             }
/*    */           } 
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void ConsumeMob(LivingEntity consumer, LivingEntity consumed, int damage, int heal) {
/* 49 */     consumed.damage(damage);
/*    */     
/* 51 */     if (consumer.getHealth() + heal >= consumer.getMaxHealth()) {
/* 52 */       consumer.setHealth(consumer.getMaxHealth());
/*    */     } else {
/* 54 */       consumer.setHealth(consumer.getHealth() + heal);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillConsume.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */