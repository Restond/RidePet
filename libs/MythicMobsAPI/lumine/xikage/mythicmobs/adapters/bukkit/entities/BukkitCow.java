/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Cow;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ 
/*    */ public class BukkitCow
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   private AgeableProperty ageableProperty;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 20 */     this.ageableProperty = new AgeableProperty(mc);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 25 */     Entity e = location.getWorld().spawnEntity(location, EntityType.COW);
/*    */     
/* 27 */     e = applyOptions(e);
/*    */     
/* 29 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 34 */     Entity e = location.getWorld().spawnEntity(location, EntityType.COW);
/*    */     
/* 36 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 41 */     Cow e = (Cow)entity;
/*    */     
/* 43 */     this.ageableProperty.applyProperties(entity);
/*    */     
/* 45 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 50 */     return e instanceof Cow;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 55 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */