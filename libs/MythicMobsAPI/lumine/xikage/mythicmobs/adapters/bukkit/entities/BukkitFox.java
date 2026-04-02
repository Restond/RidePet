/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Fox;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitFox
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   private AgeableProperty ageableProperty;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 22 */     this.ageableProperty = new AgeableProperty(mc);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 27 */     Entity e = location.getWorld().spawnEntity(location, EntityType.FOX);
/*    */     
/* 29 */     e = applyOptions(e);
/*    */     
/* 31 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 36 */     Entity e = location.getWorld().spawnEntity(location, EntityType.FOX);
/*    */     
/* 38 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 43 */     Fox e = (Fox)entity;
/*    */     
/* 45 */     this.ageableProperty.applyProperties(entity);
/*    */     
/* 47 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 52 */     return e instanceof Fox;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 57 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitFox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */