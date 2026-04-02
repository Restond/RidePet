/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitEnderCrystal
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 6;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 23 */     Entity e = location.getWorld().spawnEntity(location, EntityType.ENDER_CRYSTAL);
/*    */     
/* 25 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 30 */     Entity e = location.getWorld().spawnEntity(location, EntityType.ENDER_CRYSTAL);
/*    */     
/* 32 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 37 */     return entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 42 */     return e instanceof org.bukkit.entity.EnderCrystal;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 47 */     return 6;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitEnderCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */