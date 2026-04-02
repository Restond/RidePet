/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Snowman;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BukkitSnowman
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   private boolean preventSnowFormation = false;
/*    */   private boolean isDerp = false;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 22 */     this.preventSnowFormation = mc.getBoolean("Options.PreventSnowFormation", false);
/* 23 */     this.isDerp = mc.getBoolean("Options.Derp", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 28 */     Entity e = location.getWorld().spawnEntity(location, EntityType.SNOWMAN);
/* 29 */     if (MythicMobs.inst().getMinecraftVersion() >= 10) {
/* 30 */       e = applyOptions(e);
/*    */     }
/* 32 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 37 */     Entity e = location.getWorld().spawnEntity(location, EntityType.SNOWMAN);
/*    */     
/* 39 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 44 */     Snowman e = (Snowman)entity;
/* 45 */     e.setDerp(this.isDerp);
/* 46 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 51 */     return e instanceof Snowman;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 56 */     return 2;
/*    */   }
/*    */   
/*    */   public boolean getPreventSnowFormation() {
/* 60 */     return this.preventSnowFormation;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitSnowman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */