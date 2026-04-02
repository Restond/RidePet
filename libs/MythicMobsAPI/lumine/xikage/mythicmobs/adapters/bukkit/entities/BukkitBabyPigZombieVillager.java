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
/*    */ public class BukkitBabyPigZombieVillager
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
/* 30 */       e.setVillager(true);
/*    */     }
/* 32 */     e = (PigZombie)applyOptions((Entity)e);
/*    */     
/* 34 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 40 */     PigZombie e = (PigZombie)location.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
/* 41 */     e.setBaby(true);
/* 42 */     if (MythicMobs.inst().getMinecraftVersion() < 11) {
/* 43 */       e.setVillager(true);
/*    */     }
/*    */     
/* 46 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 51 */     PigZombie e = (PigZombie)entity;
/*    */     
/* 53 */     e.setAngry(this.angry);
/*    */     
/* 55 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 60 */     return isInstanceOf(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 65 */     if (e instanceof PigZombie) {
/* 66 */       return (((PigZombie)e).isBaby() && ((PigZombie)e).isVillager());
/*    */     }
/* 68 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 73 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitBabyPigZombieVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */