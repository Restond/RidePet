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
/*    */ public class BukkitGiant
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 12;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 23 */     Entity e = location.getWorld().spawnEntity(location, EntityType.GIANT);
/*    */     
/* 25 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 30 */     Entity e = location.getWorld().spawnEntity(location, EntityType.GIANT);
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
/* 42 */     return e instanceof org.bukkit.entity.Giant;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 47 */     return 12;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitGiant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */