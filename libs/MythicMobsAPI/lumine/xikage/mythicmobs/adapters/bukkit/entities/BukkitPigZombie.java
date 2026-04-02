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
/*    */ public class BukkitPigZombie
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
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
/* 27 */     e.setBaby(false);
/* 28 */     if (MythicMobs.inst().getMinecraftVersion() < 11) {
/* 29 */       e.setVillager(false);
/*    */     }
/*    */     
/* 32 */     e = (PigZombie)applyOptions((Entity)e);
/*    */     
/* 34 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 40 */     PigZombie e = (PigZombie)location.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
/* 41 */     e.setBaby(false);
/* 42 */     if (MythicMobs.inst().getMinecraftVersion() < 11) {
/* 43 */       e.setVillager(false);
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
/* 66 */       if (MythicMobs.inst().getMinecraftVersion() >= 11 && ((PigZombie)e).isBaby())
/* 67 */         return false; 
/* 68 */       if (((PigZombie)e).isBaby() || ((PigZombie)e).isVillager()) {
/* 69 */         return false;
/*    */       }
/* 71 */       return true;
/*    */     } 
/* 73 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 78 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitPigZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */