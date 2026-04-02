/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Slime;
/*    */ 
/*    */ public class BukkitSlime
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/* 15 */   private int size = -1;
/*    */   
/*    */   private boolean preventSplit = false;
/*    */ 
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 21 */     this.size = mc.getInt("Options.Size", -1);
/*    */     
/* 23 */     this.preventSplit = mc.getBoolean(".Options.PreventSplit", false);
/* 24 */     this.preventSplit = mc.getBoolean(".Options.PreventSlimeSplit", this.preventSplit);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 29 */     Entity e = location.getWorld().spawnEntity(location, EntityType.SLIME);
/*    */     
/* 31 */     e = applyOptions(e);
/*    */     
/* 33 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 38 */     Entity e = location.getWorld().spawnEntity(location, EntityType.SLIME);
/*    */     
/* 40 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 45 */     Slime e = (Slime)entity;
/*    */     
/* 47 */     if (this.size > 0) {
/* 48 */       e.setSize(this.size);
/*    */     }
/*    */     
/* 51 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 56 */     return e instanceof Slime;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 61 */     return 2;
/*    */   }
/*    */   
/*    */   public boolean canSplit() {
/* 65 */     return !this.preventSplit;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */