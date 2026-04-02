/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Chicken;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ 
/*    */ public class BukkitChicken
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   private AgeableProperty ageableProperty;
/*    */   private boolean jockey = false;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 22 */     this.ageableProperty = new AgeableProperty(mc);
/* 23 */     this.jockey = mc.getBoolean("Options.Jockey", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 28 */     Entity e = location.getWorld().spawnEntity(location, EntityType.CHICKEN);
/*    */     
/* 30 */     e = applyOptions(e);
/*    */     
/* 32 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 37 */     Entity e = location.getWorld().spawnEntity(location, EntityType.CHICKEN);
/*    */     
/* 39 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 44 */     Chicken e = (Chicken)entity;
/*    */     
/* 46 */     this.ageableProperty.applyProperties(entity);
/*    */     
/* 48 */     if (this.jockey) {
/* 49 */       MythicMobs.inst().getVolatileCodeHandler().setChickenHostile(e);
/*    */     }
/*    */     
/* 52 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 57 */     return e instanceof Chicken;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 62 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */