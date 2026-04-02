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
/*    */ public class BukkitCaveSpider
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public double getHealthbarOffset() {
/* 23 */     return -0.4D;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 28 */     Entity e = location.getWorld().spawnEntity(location, EntityType.CAVE_SPIDER);
/*    */     
/* 30 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 35 */     Entity e = location.getWorld().spawnEntity(location, EntityType.CAVE_SPIDER);
/*    */     
/* 37 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 42 */     return entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 47 */     return e instanceof org.bukkit.entity.CaveSpider;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 52 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitCaveSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */