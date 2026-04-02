/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitSilverfish
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/*    */   private boolean preventBlockInfection = false;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 19 */     this.preventBlockInfection = mc.getBoolean("Options.PreventBlockInfection", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 24 */     Entity e = location.getWorld().spawnEntity(location, EntityType.SILVERFISH);
/*    */     
/* 26 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 31 */     Entity e = location.getWorld().spawnEntity(location, EntityType.SILVERFISH);
/*    */     
/* 33 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 38 */     return entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 43 */     return e instanceof org.bukkit.entity.Silverfish;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 48 */     return 1;
/*    */   }
/*    */   
/*    */   public boolean getPreventBlockInfection() {
/* 52 */     return this.preventBlockInfection;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitSilverfish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */