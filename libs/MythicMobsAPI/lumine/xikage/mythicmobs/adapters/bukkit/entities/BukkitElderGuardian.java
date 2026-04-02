/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.ElderGuardian;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Guardian;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitElderGuardian
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 23 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 24 */       ElderGuardian elderGuardian = (ElderGuardian)location.getWorld().spawnEntity(location, EntityType.ELDER_GUARDIAN);
/* 25 */       return (Entity)elderGuardian;
/*    */     } 
/* 27 */     Guardian e = (Guardian)location.getWorld().spawnEntity(location, EntityType.GUARDIAN);
/* 28 */     e.setElder(true);
/* 29 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 36 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 37 */       ElderGuardian elderGuardian = (ElderGuardian)location.getWorld().spawnEntity(location, EntityType.ELDER_GUARDIAN);
/* 38 */       return (Entity)elderGuardian;
/*    */     } 
/* 40 */     Guardian e = (Guardian)location.getWorld().spawnEntity(location, EntityType.GUARDIAN);
/* 41 */     e.setElder(true);
/* 42 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity e) {
/* 48 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 53 */     return isInstanceOf(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 58 */     if (MythicMobs.inst().getMinecraftVersion() >= 11)
/* 59 */       return e instanceof ElderGuardian; 
/* 60 */     if (e instanceof Guardian) {
/* 61 */       return ((Guardian)e).isElder();
/*    */     }
/* 63 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 68 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitElderGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */