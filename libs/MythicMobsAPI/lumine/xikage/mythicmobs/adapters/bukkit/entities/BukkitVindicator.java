/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Vindicator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitVindicator
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 23 */     Vindicator e = (Vindicator)location.getWorld().spawnEntity(location, EntityType.VINDICATOR);
/* 24 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 29 */     Vindicator e = (Vindicator)location.getWorld().spawnEntity(location, EntityType.VINDICATOR);
/* 30 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 40 */     return isInstanceOf(e);
/*    */   }
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 44 */     return e instanceof Vindicator;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 49 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitVindicator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */