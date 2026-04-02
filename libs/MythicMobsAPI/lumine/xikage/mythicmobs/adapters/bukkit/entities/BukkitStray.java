/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Skeleton;
/*    */ import org.bukkit.entity.Stray;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitStray
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 28 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 29 */       Stray stray = (Stray)location.getWorld().spawnEntity(location, EntityType.STRAY);
/* 30 */       return (Entity)stray;
/*    */     } 
/* 32 */     Skeleton e = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON);
/* 33 */     e.setSkeletonType(Skeleton.SkeletonType.STRAY);
/* 34 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 41 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 42 */       Stray stray = (Stray)location.getWorld().spawnEntity(location, EntityType.STRAY);
/* 43 */       return (Entity)stray;
/*    */     } 
/* 45 */     Skeleton e = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON);
/* 46 */     e.setSkeletonType(Skeleton.SkeletonType.STRAY);
/* 47 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity e) {
/* 53 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 58 */     return isInstanceOf(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 63 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 64 */       if (e instanceof Stray) return true;
/*    */     
/* 66 */     } else if (e instanceof Skeleton && (
/* 67 */       (Skeleton)e).getSkeletonType() == Skeleton.SkeletonType.STRAY) {
/* 68 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 72 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 77 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitStray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */