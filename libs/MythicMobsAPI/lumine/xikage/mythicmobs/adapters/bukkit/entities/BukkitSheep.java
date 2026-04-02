/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Sheep;
/*    */ 
/*    */ 
/*    */ public class BukkitSheep
/*    */   extends MythicEntity
/*    */ {
/*    */   protected static final int height = 2;
/*    */   private AgeableProperty ageableProperty;
/*    */   protected DyeColor color;
/*    */   private boolean isSheared = false;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 23 */     this.ageableProperty = new AgeableProperty(mc);
/* 24 */     String strcolor = mc.getString("Options.Color", "WHITE");
/* 25 */     this.color = DyeColor.valueOf(strcolor);
/* 26 */     this.isSheared = mc.getBoolean("Options.Sheared", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 31 */     Entity e = location.getWorld().spawnEntity(location, EntityType.SHEEP);
/*    */     
/* 33 */     e = applyOptions(e);
/*    */     
/* 35 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 40 */     Entity e = location.getWorld().spawnEntity(location, EntityType.SHEEP);
/*    */     
/* 42 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 47 */     Sheep e = (Sheep)entity;
/*    */     
/* 49 */     this.ageableProperty.applyProperties(entity);
/* 50 */     e.setColor(this.color);
/* 51 */     e.setSheared(this.isSheared);
/*    */     
/* 53 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 58 */     return e instanceof Sheep;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 63 */     return 2;
/*    */   }
/*    */   
/*    */   public DyeColor getColor() {
/* 67 */     return this.color;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitSheep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */