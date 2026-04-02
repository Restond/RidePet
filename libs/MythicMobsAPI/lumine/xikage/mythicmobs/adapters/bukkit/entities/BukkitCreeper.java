/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Creeper;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class BukkitCreeper
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/*    */   private boolean powered = false;
/*    */   private boolean preventSuicide = false;
/* 18 */   private int explosionFuseTicks = -1;
/* 19 */   private int explosionRadius = -1;
/*    */ 
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 23 */     this.powered = mc.getBoolean("Options.SuperCharged", false);
/* 24 */     this.preventSuicide = mc.getBoolean("Options.PreventSuicide", false);
/* 25 */     this.explosionFuseTicks = mc.getInteger("Options.FuseTicks", -1);
/* 26 */     this.explosionRadius = mc.getInteger("Options.ExplosionRadius", -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 31 */     Entity e = location.getWorld().spawnEntity(location, EntityType.CREEPER);
/*    */     
/* 33 */     e = applyOptions(e);
/*    */     
/* 35 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 40 */     Entity e = location.getWorld().spawnEntity(location, EntityType.CREEPER);
/*    */     
/* 42 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 47 */     Creeper e = (Creeper)entity;
/*    */     
/* 49 */     if (this.powered) {
/* 50 */       e.setPowered(true);
/*    */     }
/*    */     
/* 53 */     if (this.explosionFuseTicks >= 0) {
/* 54 */       if (MythicMobs.inst().getMinecraftVersion() >= 12) {
/* 55 */         e.setMaxFuseTicks(this.explosionFuseTicks);
/*    */       } else {
/* 57 */         MythicMobs.inst().getVolatileCodeHandler().setCreeperFuseTicks(e, this.explosionFuseTicks);
/*    */       } 
/*    */     }
/*    */     
/* 61 */     if (this.explosionRadius >= 0) {
/* 62 */       if (MythicMobs.inst().getMinecraftVersion() >= 12) {
/* 63 */         e.setExplosionRadius(this.explosionRadius);
/*    */       } else {
/* 65 */         MythicMobs.inst().getVolatileCodeHandler().setCreeperExplosionRadius(e, this.explosionRadius);
/*    */       } 
/*    */     }
/*    */     
/* 69 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 74 */     return e instanceof Creeper;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 79 */     return 2;
/*    */   }
/*    */   
/*    */   public boolean preventSuicide() {
/* 83 */     return this.preventSuicide;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */