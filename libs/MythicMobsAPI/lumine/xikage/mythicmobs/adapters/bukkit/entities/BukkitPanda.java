/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Panda;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitPanda
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   private AgeableProperty ageableProperty;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 23 */     this.ageableProperty = new AgeableProperty(mc);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 28 */     Entity e = location.getWorld().spawnEntity(location, EntityType.PANDA);
/*    */     
/* 30 */     e = applyOptions(e);
/*    */     
/* 32 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 37 */     Entity e = location.getWorld().spawnEntity(location, EntityType.PANDA);
/*    */     
/* 39 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 44 */     Panda e = (Panda)entity;
/*    */     
/* 46 */     this.ageableProperty.applyProperties(entity);
/*    */     
/* 48 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 53 */     return e instanceof Panda;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 58 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitPanda.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */