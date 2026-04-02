/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.PigZombie;
/*    */ 
/*    */ 
/*    */ public class BukkitPigZombieVillager
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   private boolean angry = false;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 19 */     this.angry = mc.getBoolean("Options.Angry", false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 25 */     PigZombie e = (PigZombie)location.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
/* 26 */     e.setBaby(false);
/* 27 */     e.setVillager(true);
/*    */     
/* 29 */     e = (PigZombie)applyOptions((Entity)e);
/*    */     
/* 31 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 37 */     PigZombie e = (PigZombie)location.getWorld().spawnEntity(location, EntityType.PIG_ZOMBIE);
/* 38 */     e.setBaby(false);
/* 39 */     e.setVillager(true);
/*    */     
/* 41 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 46 */     PigZombie e = (PigZombie)entity;
/*    */     
/* 48 */     e.setAngry(this.angry);
/*    */     
/* 50 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 55 */     return isInstanceOf(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 60 */     if (e instanceof PigZombie && 
/* 61 */       !((PigZombie)e).isBaby() && ((PigZombie)e).isVillager()) {
/* 62 */       return true;
/*    */     }
/*    */     
/* 65 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 70 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitPigZombieVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */