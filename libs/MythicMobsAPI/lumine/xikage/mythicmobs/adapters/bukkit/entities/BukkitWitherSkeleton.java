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
/*    */ import org.bukkit.entity.WitherSkeleton;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitWitherSkeleton
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 3;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 28 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 29 */       WitherSkeleton witherSkeleton = (WitherSkeleton)location.getWorld().spawnEntity(location, EntityType.WITHER_SKELETON);
/* 30 */       return (Entity)witherSkeleton;
/*    */     } 
/* 32 */     Skeleton e = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON);
/* 33 */     e.setSkeletonType(Skeleton.SkeletonType.WITHER);
/* 34 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 42 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 43 */       WitherSkeleton witherSkeleton = (WitherSkeleton)location.getWorld().spawnEntity(location, EntityType.WITHER_SKELETON);
/* 44 */       return (Entity)witherSkeleton;
/*    */     } 
/* 46 */     Skeleton e = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON);
/* 47 */     e.setSkeletonType(Skeleton.SkeletonType.WITHER);
/* 48 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity e) {
/* 54 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 59 */     return isInstanceOf(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 64 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 65 */       return e instanceof WitherSkeleton;
/*    */     }
/* 67 */     return (e instanceof Skeleton && ((Skeleton)e).getSkeletonType() == Skeleton.SkeletonType.WITHER);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 72 */     return 3;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitWitherSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */