/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Skeleton;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillPotionMobs
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 20 */     String[] base = skill.split(" ");
/* 21 */     String[] data = base[1].split(":");
/*    */     
/* 23 */     int radius = Integer.parseInt(data[0]);
/* 24 */     String pType = data[1];
/* 25 */     int pDuration = Integer.parseInt(data[2]);
/* 26 */     int pLevel = Integer.parseInt(data[3]) - 1;
/* 27 */     String[] mobtypes = base[2].split(",");
/*    */     
/* 29 */     PotionEffect pe = new PotionEffect(PotionEffectType.getByName(pType), pDuration, pLevel);
/*    */     
/* 31 */     if (pe != null)
/* 32 */       for (Entity e : l.getNearbyEntities(radius, radius, radius)) {
/* 33 */         if (e instanceof LivingEntity)
/* 34 */           for (String mob : mobtypes) {
/* 35 */             if (e.getType() == EntityType.fromName(mob.toUpperCase())) {
/* 36 */               ((LivingEntity)e).addPotionEffect(pe);
/* 37 */             } else if (MythicMobs.inst().getMobManager().isActiveMob(e.getUniqueId())) {
/* 38 */               ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(e);
/*    */               
/* 40 */               if (mob.equals(am.getType().getInternalName())) {
/* 41 */                 ((LivingEntity)e).addPotionEffect(pe);
/*    */               }
/*    */             }
/* 44 */             else if (mob == "witherskeleton" && 
/* 45 */               e instanceof Skeleton && (
/* 46 */               (Skeleton)e).getSkeletonType() == Skeleton.SkeletonType.WITHER) {
/* 47 */               ((LivingEntity)e).addPotionEffect(pe);
/*    */             } 
/*    */           }  
/*    */       }  
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillPotionMobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */