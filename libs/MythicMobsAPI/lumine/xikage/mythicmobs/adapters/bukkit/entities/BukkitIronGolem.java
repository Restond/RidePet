/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.IronGolem;
/*    */ 
/*    */ 
/*    */ public class BukkitIronGolem
/*    */   extends MythicEntity
/*    */ {
/*    */   private boolean isPlayerCreated = false;
/*    */   private static final int height = 1;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 20 */     this.isPlayerCreated = mc.getBoolean("Options.PlayerCreated", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 25 */     Entity e = location.getWorld().spawnEntity(location, EntityType.IRON_GOLEM);
/* 26 */     e = applyOptions(e);
/* 27 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 32 */     Entity e = location.getWorld().spawnEntity(location, EntityType.IRON_GOLEM);
/* 33 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 38 */     IronGolem e = (IronGolem)entity;
/*    */     
/* 40 */     if (this.isPlayerCreated && 
/* 41 */       MythicMobs.inst().getMinecraftVersion() >= 12) {
/* 42 */       e.setPlayerCreated(true);
/*    */     }
/*    */ 
/*    */     
/* 46 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 51 */     return e instanceof IronGolem;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 56 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitIronGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */