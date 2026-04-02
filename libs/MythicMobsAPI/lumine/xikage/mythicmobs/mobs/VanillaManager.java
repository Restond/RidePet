/*    */ package lumine.xikage.mythicmobs.mobs;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitBabyPigZombie;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitBabyPigZombieVillager;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitBabyZombie;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitBabyZombieVillager;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitElderGuardian;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitPigZombie;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitPigZombieVillager;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitWitherSkeleton;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitZombie;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitZombieVillager;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntityType;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ 
/*    */ public class VanillaManager
/*    */ {
/*    */   public static MythicEntityType getMythicEntityType(Entity e) {
/* 20 */     String type = e.getType().toString();
/*    */     
/* 22 */     switch (type) {
/*    */       case "GUARDIAN":
/* 24 */         if (BukkitElderGuardian.isInstanceOf(e)) type = "ELDER_GUARDIAN"; 
/*    */         break;
/*    */       case "PIG_ZOMBIE":
/* 27 */         if (BukkitPigZombie.isInstanceOf(e)) {
/*    */           break;
/*    */         }
/* 30 */         if (BukkitBabyPigZombie.isInstanceOf(e)) {
/* 31 */           type = "BABY_PIG_ZOMBIE";
/*    */           break;
/*    */         } 
/* 34 */         if (BukkitPigZombieVillager.isInstanceOf(e)) {
/* 35 */           type = "PIG_ZOMBIE_VILLAGER";
/*    */           break;
/*    */         } 
/* 38 */         if (BukkitBabyPigZombieVillager.isInstanceOf(e)) {
/* 39 */           type = "BABY_PIG_ZOMBIE_VILLAGER";
/*    */         }
/*    */         break;
/*    */       
/*    */       case "SKELETON":
/* 44 */         if (BukkitWitherSkeleton.isInstanceOf(e)) type = "WITHER_SKELETON"; 
/*    */         break;
/*    */       case "ZOMBIE":
/* 47 */         if (BukkitZombie.isInstanceOf(e)) {
/*    */           break;
/*    */         }
/* 50 */         if (BukkitBabyZombie.isInstanceOf(e)) {
/* 51 */           type = "BABY_ZOMBIE";
/*    */           break;
/*    */         } 
/* 54 */         if (BukkitZombieVillager.isInstanceOf(e)) {
/* 55 */           type = "ZOMBIE_VILLAGER";
/*    */           break;
/*    */         } 
/* 58 */         if (BukkitBabyZombieVillager.isInstanceOf(e)) {
/* 59 */           type = "BABY_ZOMBIE_VILLAGER";
/*    */         }
/*    */         break;
/*    */     } 
/* 63 */     return MythicEntityType.get(type);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\VanillaManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */