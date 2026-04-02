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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitSkeleton
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 27 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 28 */       Skeleton skeleton = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON);
/* 29 */       return (Entity)skeleton;
/*    */     } 
/* 31 */     Skeleton e = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON);
/* 32 */     e.setSkeletonType(Skeleton.SkeletonType.NORMAL);
/* 33 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 41 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 42 */       Skeleton skeleton = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON);
/* 43 */       return (Entity)skeleton;
/*    */     } 
/* 45 */     Skeleton e = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON);
/* 46 */     e.setSkeletonType(Skeleton.SkeletonType.NORMAL);
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
/* 64 */       return e instanceof Skeleton;
/*    */     }
/* 66 */     return (e instanceof Skeleton && ((Skeleton)e).getSkeletonType() == Skeleton.SkeletonType.NORMAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 71 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */