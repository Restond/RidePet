/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Pillager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitPillager
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 24 */     Pillager e = (Pillager)location.getWorld().spawnEntity(location, EntityType.PILLAGER);
/* 25 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 30 */     Pillager e = (Pillager)location.getWorld().spawnEntity(location, EntityType.PILLAGER);
/* 31 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 36 */     return entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 41 */     return isInstanceOf(e);
/*    */   }
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 45 */     return e instanceof Pillager;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 50 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitPillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */