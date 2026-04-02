/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Guardian;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitGuardian
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 24 */     Guardian e = (Guardian)location.getWorld().spawnEntity(location, EntityType.GUARDIAN);
/*    */     
/* 26 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 31 */     Guardian e = (Guardian)location.getWorld().spawnEntity(location, EntityType.GUARDIAN);
/*    */     
/* 33 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity e) {
/* 38 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 43 */     return isInstanceOf(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 48 */     if (MythicMobs.inst().getMinecraftVersion() >= 11)
/* 49 */       return e instanceof Guardian; 
/* 50 */     if (e instanceof Guardian) {
/* 51 */       return !((Guardian)e).isElder();
/*    */     }
/* 53 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 58 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */