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
/*    */ import org.bukkit.entity.ZombieHorse;
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
/*    */ 
/*    */ public class BukkitZombieHorse
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   private AgeableProperty ageableProperty;
/*    */   private boolean horseSaddled;
/*    */   private boolean horseTamed;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 33 */     this.ageableProperty = new AgeableProperty(mc);
/*    */     
/* 35 */     this.horseSaddled = mc.getBoolean("Options.HorseSaddled", false);
/* 36 */     this.horseSaddled = mc.getBoolean("Options.Saddled", this.horseSaddled);
/*    */     
/* 38 */     this.horseTamed = mc.getBoolean("Options.HorseTamed", false);
/* 39 */     this.horseTamed = mc.getBoolean("Options.Tamed", this.horseTamed);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 44 */     Entity e = location.getWorld().spawnEntity(location, EntityType.ZOMBIE_HORSE);
/*    */     
/* 46 */     e = applyOptions(e);
/*    */     
/* 48 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 53 */     Entity e = location.getWorld().spawnEntity(location, EntityType.ZOMBIE_HORSE);
/*    */     
/* 55 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 60 */     ZombieHorse e = (ZombieHorse)entity;
/*    */     
/* 62 */     this.ageableProperty.applyProperties(entity);
/*    */     
/* 64 */     AbstractHorseInventory hi = e.getInventory();
/*    */     
/* 66 */     if (this.horseTamed) {
/* 67 */       e.setTamed(true);
/*    */     }
/* 69 */     if (this.horseSaddled) {
/* 70 */       hi.setSaddle(new ItemStack(Material.SADDLE, 1, (short)0));
/*    */     }
/*    */     
/* 73 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 78 */     return e instanceof ZombieHorse;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 83 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitZombieHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */