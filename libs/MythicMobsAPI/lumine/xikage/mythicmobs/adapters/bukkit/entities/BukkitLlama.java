/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Llama;
/*    */ 
/*    */ public class BukkitLlama
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   private AgeableProperty ageableProperty;
/*    */   private String horseColor;
/*    */   private boolean horseChest;
/*    */   private boolean horseSaddled;
/*    */   private boolean horseTamed;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 24 */     this.ageableProperty = new AgeableProperty(mc);
/*    */     
/* 26 */     this.horseChest = mc.getBoolean("Options.CarryingChest", false);
/* 27 */     this.horseColor = mc.getString("Options.Color", "WHITE");
/* 28 */     this.horseTamed = mc.getBoolean("Options.Tamed", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 33 */     Llama e = (Llama)location.getWorld().spawnEntity(location, EntityType.LLAMA);
/*    */     
/* 35 */     e = (Llama)applyOptions((Entity)e);
/*    */     
/* 37 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 42 */     Llama e = (Llama)location.getWorld().spawnEntity(location, EntityType.LLAMA);
/*    */     
/* 44 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 49 */     Llama e = (Llama)entity;
/*    */     
/* 51 */     this.ageableProperty.applyProperties(entity);
/*    */     try {
/* 53 */       e.setColor(Llama.Color.valueOf(this.horseColor));
/* 54 */     } catch (Exception ex) {
/* 55 */       MythicMobs.error("Invalid llama color specified" + this.horseColor);
/*    */     } 
/*    */ 
/*    */     
/* 59 */     if (this.horseChest) {
/* 60 */       e.setCarryingChest(true);
/*    */     }
/* 62 */     if (this.horseTamed) {
/* 63 */       e.setTamed(true);
/*    */     }
/*    */     
/* 66 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 71 */     return e instanceof Llama;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 76 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitLlama.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */