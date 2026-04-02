/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.PigZombie;
/*    */ 
/*    */ 
/*    */ public class BukkitBabyPigZombie
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   private boolean angry = false;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 20 */     this.angry = mc.getBoolean("Options.Angry", false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 26 */     PigZombie e = (PigZombie)location.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
/* 27 */     e.setBaby(true);
/*    */     
/* 29 */     if (MythicMobs.inst().getMinecraftVersion() < 11) {
/* 30 */       e.setVillager(false);
/*    */     }
/*    */     
/* 33 */     e = (PigZombie)applyOptions((Entity)e);
/*    */     
/* 35 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 41 */     PigZombie e = (PigZombie)location.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
/* 42 */     e.setBaby(true);
/* 43 */     if (MythicMobs.inst().getMinecraftVersion() < 11) {
/* 44 */       e.setVillager(false);
/*    */     }
/*    */     
/* 47 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 52 */     PigZombie e = (PigZombie)entity;
/*    */     
/* 54 */     e.setAngry(this.angry);
/*    */     
/* 56 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 61 */     return isInstanceOf(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 66 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 67 */       if (e instanceof PigZombie) {
/* 68 */         return ((PigZombie)e).isBaby();
/*    */       }
/* 70 */     } else if (e instanceof PigZombie) {
/* 71 */       return (((PigZombie)e).isBaby() && !((PigZombie)e).isVillager());
/*    */     } 
/* 73 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 78 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitBabyPigZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */