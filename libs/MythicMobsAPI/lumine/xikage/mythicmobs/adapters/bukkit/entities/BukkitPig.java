/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Pig;
/*    */ 
/*    */ 
/*    */ public class BukkitPig
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   private AgeableProperty ageableProperty;
/*    */   private boolean saddled = false;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 21 */     this.ageableProperty = new AgeableProperty(mc);
/* 22 */     this.saddled = mc.getBoolean("Options.Saddled", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 27 */     Entity e = location.getWorld().spawnEntity(location, EntityType.PIG);
/*    */     
/* 29 */     e = applyOptions(e);
/*    */     
/* 31 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 36 */     Entity e = location.getWorld().spawnEntity(location, EntityType.PIG);
/*    */     
/* 38 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 43 */     Pig e = (Pig)entity;
/*    */     
/* 45 */     this.ageableProperty.applyProperties(entity);
/* 46 */     e.setSaddle(this.saddled);
/*    */     
/* 48 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 53 */     return e instanceof Pig;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 58 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitPig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */