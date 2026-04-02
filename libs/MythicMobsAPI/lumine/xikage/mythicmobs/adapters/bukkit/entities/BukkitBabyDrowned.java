/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.entity.Drowned;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class BukkitBabyDrowned
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 2;
/* 16 */   private double reinforcementChance = -1.0D;
/*    */ 
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 20 */     this.reinforcementChance = mc.getDouble("Options.ReinforcementsChance", -1.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 26 */     Drowned e = (Drowned)location.getWorld().spawnEntity(location, EntityType.DROWNED);
/*    */     
/* 28 */     e.setBaby(true);
/* 29 */     e = (Drowned)applyOptions((Entity)e);
/*    */     
/* 31 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 36 */     Drowned e = (Drowned)location.getWorld().spawnEntity(location, EntityType.DROWNED);
/*    */     
/* 38 */     e.setBaby(true);
/*    */     
/* 40 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 45 */     Drowned e = (Drowned)entity;
/*    */     
/* 47 */     if (this.reinforcementChance >= 0.0D) {
/* 48 */       e.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(this.reinforcementChance);
/*    */     }
/*    */     
/* 51 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 56 */     return isInstanceOf(e);
/*    */   }
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 60 */     if (e instanceof Drowned && e.getType().equals(EntityType.DROWNED)) {
/* 61 */       return ((Drowned)e).isBaby();
/*    */     }
/* 63 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 68 */     return 2;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitBabyDrowned.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */