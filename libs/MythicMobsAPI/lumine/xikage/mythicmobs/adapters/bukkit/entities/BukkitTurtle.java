/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Turtle;
/*    */ 
/*    */ 
/*    */ public class BukkitTurtle
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   private AgeableProperty ageableProperty;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 20 */     this.ageableProperty = new AgeableProperty(mc);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 25 */     Entity e = location.getWorld().spawnEntity(location, EntityType.TURTLE);
/*    */     
/* 27 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 32 */     Entity e = location.getWorld().spawnEntity(location, EntityType.TURTLE);
/*    */     
/* 34 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 39 */     Turtle e = (Turtle)entity;
/*    */     
/* 41 */     this.ageableProperty.applyProperties(entity);
/*    */     
/* 43 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 48 */     return e instanceof Turtle;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 53 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */