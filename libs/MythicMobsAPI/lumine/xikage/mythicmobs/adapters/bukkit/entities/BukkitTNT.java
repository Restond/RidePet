/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.TNTPrimed;
/*    */ 
/*    */ public class BukkitTNT
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/* 15 */   private int explosionFuseTicks = -1;
/* 16 */   private int explosionYield = -1;
/*    */   
/*    */   private boolean explosionIncendiary = false;
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 21 */     this.explosionFuseTicks = mc.getInt("Options.FuseTicks", -1);
/* 22 */     this.explosionYield = mc.getInt("Options.ExplosionYield", -1);
/* 23 */     this.explosionIncendiary = mc.getBoolean("Options.Incendiary", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 28 */     Entity e = location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
/*    */     
/* 30 */     e = applyOptions(e);
/*    */     
/* 32 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 37 */     Entity e = location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
/*    */     
/* 39 */     return e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 47 */     TNTPrimed tnt = (TNTPrimed)entity;
/*    */     
/* 49 */     if (this.explosionFuseTicks > -1) {
/* 50 */       tnt.setFuseTicks(this.explosionFuseTicks);
/*    */     }
/* 52 */     if (this.explosionYield > -1) {
/* 53 */       tnt.setYield(this.explosionYield);
/*    */     }
/* 55 */     tnt.setIsIncendiary(this.explosionIncendiary);
/*    */     
/* 57 */     return (Entity)tnt;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 62 */     return e instanceof TNTPrimed;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 67 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */