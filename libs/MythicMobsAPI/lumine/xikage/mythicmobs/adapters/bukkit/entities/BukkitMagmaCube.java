/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.MagmaCube;
/*    */ 
/*    */ public class BukkitMagmaCube
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/* 15 */   private int size = -1;
/*    */   
/*    */   private boolean preventSplit = false;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 20 */     this.size = mc.getInt("Options.Size", -1);
/*    */     
/* 22 */     this.preventSplit = mc.getBoolean(".Options.PreventSplit", false);
/* 23 */     this.preventSplit = mc.getBoolean(".Options.PreventSlimeSplit", this.preventSplit);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 28 */     Entity e = location.getWorld().spawnEntity(location, EntityType.MAGMA_CUBE);
/*    */     
/* 30 */     e = applyOptions(e);
/*    */     
/* 32 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 37 */     Entity e = location.getWorld().spawnEntity(location, EntityType.MAGMA_CUBE);
/*    */     
/* 39 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 44 */     MagmaCube e = (MagmaCube)entity;
/*    */     
/* 46 */     if (this.size > 0) {
/* 47 */       e.setSize(this.size);
/*    */     }
/*    */     
/* 50 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 55 */     return e instanceof MagmaCube;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 60 */     return 2;
/*    */   }
/*    */   
/*    */   public boolean canSplit() {
/* 64 */     return !this.preventSplit;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitMagmaCube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */