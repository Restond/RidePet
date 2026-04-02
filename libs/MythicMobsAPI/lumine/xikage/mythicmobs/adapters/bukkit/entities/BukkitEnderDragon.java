/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitEnderDragon
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 6;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {}
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 24 */     Entity e = location.getWorld().spawnEntity(location, EntityType.ENDER_DRAGON);
/*    */     
/* 26 */     if (MythicMobs.inst().getMinecraftVersion() > 8) {
/* 27 */       MythicMobs.inst().getVolatileCodeHandler().setEnderDragonAI(e);
/*    */     }
/*    */     
/* 30 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 35 */     Entity e = location.getWorld().spawnEntity(location, EntityType.ENDER_DRAGON);
/*    */     
/* 37 */     if (MythicMobs.inst().getMinecraftVersion() > 8) {
/* 38 */       MythicMobs.inst().getVolatileCodeHandler().setEnderDragonAI(e);
/*    */     }
/*    */     
/* 41 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 46 */     return entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 51 */     return e instanceof org.bukkit.entity.EnderDragon;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 56 */     return 6;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitEnderDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */