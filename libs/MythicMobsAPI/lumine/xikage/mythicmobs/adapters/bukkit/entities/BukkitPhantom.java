/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Phantom;
/*    */ 
/*    */ public class BukkitPhantom
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/* 15 */   private int size = -1;
/*    */ 
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 19 */     this.size = mc.getInteger("Options.Size", -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 24 */     Entity e = location.getWorld().spawnEntity(location, EntityType.PHANTOM);
/*    */     
/* 26 */     e = applyOptions(e);
/*    */     
/* 28 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 33 */     Entity e = location.getWorld().spawnEntity(location, EntityType.PHANTOM);
/*    */     
/* 35 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 40 */     Phantom e = (Phantom)entity;
/*    */     
/* 42 */     if (this.size > 0) {
/* 43 */       e.setSize(this.size);
/*    */     }
/* 45 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 50 */     return e instanceof Phantom;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 55 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitPhantom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */