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
/*    */ import org.bukkit.entity.SkeletonHorse;
/*    */ import org.bukkit.inventory.AbstractHorseInventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitSkeletonHorse
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   private AgeableProperty ageableProperty;
/*    */   private boolean horseSaddled;
/*    */   private boolean horseTamed;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 32 */     this.ageableProperty = new AgeableProperty(mc);
/*    */     
/* 34 */     this.horseSaddled = mc.getBoolean("Options.HorseSaddled", false);
/* 35 */     this.horseSaddled = mc.getBoolean("Options.Saddled", this.horseSaddled);
/*    */     
/* 37 */     this.horseTamed = mc.getBoolean("Options.HorseTamed", false);
/* 38 */     this.horseTamed = mc.getBoolean("Options.Tamed", this.horseTamed);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 43 */     Entity e = location.getWorld().spawnEntity(location, EntityType.SKELETON_HORSE);
/*    */     
/* 45 */     e = applyOptions(e);
/*    */     
/* 47 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 52 */     Entity e = location.getWorld().spawnEntity(location, EntityType.SKELETON_HORSE);
/*    */     
/* 54 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 59 */     SkeletonHorse e = (SkeletonHorse)entity;
/*    */     
/* 61 */     this.ageableProperty.applyProperties(entity);
/*    */     
/* 63 */     AbstractHorseInventory hi = e.getInventory();
/*    */     
/* 65 */     if (this.horseTamed) {
/* 66 */       e.setTamed(true);
/*    */     }
/* 68 */     if (this.horseSaddled) {
/* 69 */       hi.setSaddle(new ItemStack(Material.SADDLE, 1, (short)0));
/*    */     }
/*    */     
/* 72 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 77 */     return e instanceof SkeletonHorse;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 82 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitSkeletonHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */