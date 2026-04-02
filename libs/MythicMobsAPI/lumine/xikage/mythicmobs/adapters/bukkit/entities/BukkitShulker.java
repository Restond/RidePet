/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Shulker;
/*    */ 
/*    */ 
/*    */ public class BukkitShulker
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   private DyeColor color;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 20 */     String strcolor = mc.getString("Options.Color", "WHITE");
/*    */     
/* 22 */     this.color = DyeColor.valueOf(strcolor);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 27 */     Shulker e = (Shulker)location.getWorld().spawnEntity(location, EntityType.SHULKER);
/*    */     
/* 29 */     e = (Shulker)applyOptions((Entity)e);
/*    */     
/* 31 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 36 */     Shulker e = (Shulker)location.getWorld().spawnEntity(location, EntityType.SHULKER);
/*    */     
/* 38 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 43 */     Shulker e = (Shulker)entity;
/*    */     
/* 45 */     e.setColor(this.color);
/*    */     
/* 47 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 52 */     return isInstanceOf(e);
/*    */   }
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 56 */     return e instanceof Shulker;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 61 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitShulker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */