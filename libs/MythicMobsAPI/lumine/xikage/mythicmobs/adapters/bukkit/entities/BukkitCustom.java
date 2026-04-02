/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ 
/*    */ public class BukkitCustom
/*    */   extends MythicEntity
/*    */ {
/* 15 */   private String strMobType = null;
/*    */ 
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 19 */     this.strMobType = mc.getString("Type");
/* 20 */     this.strMobType = mc.getString("MobType", this.strMobType);
/* 21 */     this.strMobType = mc.getString("Mobtype", this.strMobType);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 27 */     Entity e = location.getWorld().spawnEntity(location, EntityType.fromName(this.strMobType));
/* 28 */     if (e == null) {
/*    */       try {
/* 30 */         e = location.getWorld().spawnEntity(location, EntityType.fromId(Integer.parseInt(this.strMobType)));
/* 31 */       } catch (Exception ex) {
/* 32 */         MythicLogger.error("Could not spawn MythicMob of type {0}! Unknown Type specified.", new Object[] { this.strMobType });
/* 33 */         return null;
/*    */       } 
/*    */     }
/*    */     
/* 37 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 42 */     return spawn(null, location);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity e) {
/* 47 */     return e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 53 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitCustom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */