/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Mule;
/*    */ import org.bukkit.inventory.AbstractHorseInventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class BukkitMule
/*    */   extends MythicEntity {
/*    */   private static final int height = 2;
/*    */   private AgeableProperty ageableProperty;
/*    */   private boolean horseChest;
/*    */   private boolean horseSaddled;
/*    */   private boolean horseTamed;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 24 */     this.ageableProperty = new AgeableProperty(mc);
/*    */     
/* 26 */     this.horseChest = mc.getBoolean("Options.HorseCarryingChest", false);
/* 27 */     this.horseChest = mc.getBoolean("Options.CarryingChest", this.horseChest);
/*    */     
/* 29 */     this.horseSaddled = mc.getBoolean("Options.HorseSaddled", false);
/* 30 */     this.horseSaddled = mc.getBoolean("Options.Saddled", this.horseSaddled);
/*    */     
/* 32 */     this.horseTamed = mc.getBoolean("Options.HorseTamed", false);
/* 33 */     this.horseTamed = mc.getBoolean("Options.Tamed", this.horseTamed);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 38 */     Entity e = location.getWorld().spawnEntity(location, EntityType.MULE);
/*    */     
/* 40 */     e = applyOptions(e);
/*    */     
/* 42 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 47 */     Entity e = location.getWorld().spawnEntity(location, EntityType.MULE);
/*    */     
/* 49 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 54 */     Mule e = (Mule)entity;
/*    */     
/* 56 */     this.ageableProperty.applyProperties(entity);
/*    */     
/* 58 */     AbstractHorseInventory hi = e.getInventory();
/*    */     
/* 60 */     if (this.horseTamed) {
/* 61 */       e.setTamed(true);
/*    */     }
/* 63 */     if (this.horseSaddled) {
/* 64 */       hi.setSaddle(new ItemStack(Material.SADDLE, 1, (short)0));
/*    */     }
/* 66 */     if (this.horseChest) {
/* 67 */       e.setCarryingChest(true);
/*    */     }
/*    */     
/* 70 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 75 */     return e instanceof Mule;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 80 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitMule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */