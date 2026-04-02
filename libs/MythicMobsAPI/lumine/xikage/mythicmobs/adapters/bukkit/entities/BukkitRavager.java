/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Ravager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitRavager
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 26 */     Ravager e = (Ravager)location.getWorld().spawnEntity(location, EntityType.RAVAGER);
/*    */     
/* 28 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 33 */     Ravager e = (Ravager)location.getWorld().spawnEntity(location, EntityType.RAVAGER);
/* 34 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 39 */     return entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 44 */     return isInstanceOf(e);
/*    */   }
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 48 */     return e instanceof Ravager;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 53 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitRavager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */