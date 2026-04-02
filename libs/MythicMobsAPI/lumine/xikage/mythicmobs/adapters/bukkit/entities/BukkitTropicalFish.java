/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.TropicalFish;
/*    */ 
/*    */ public class BukkitTropicalFish
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/* 17 */   private TropicalFish.Pattern pattern = null;
/* 18 */   private DyeColor bodyColor = null;
/* 19 */   private DyeColor patternColor = null;
/*    */ 
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 23 */     String strPattern = mc.getString("Options.Pattern", null);
/* 24 */     String strBodyColor = mc.getString("Options.BodyColor", null);
/* 25 */     String strPatternColor = mc.getString("Options.PatternColor", null);
/*    */     
/* 27 */     if (strPattern != null) {
/*    */       try {
/* 29 */         this.pattern = TropicalFish.Pattern.valueOf(strPattern.toUpperCase());
/* 30 */       } catch (Exception ex) {
/* 31 */         MythicLogger.errorEntityConfig(this, mc, "Invalid Pattern specified");
/*    */       } 
/*    */     }
/* 34 */     if (strBodyColor != null) {
/*    */       try {
/* 36 */         this.bodyColor = DyeColor.valueOf(strBodyColor.toUpperCase());
/* 37 */       } catch (Exception ex) {
/* 38 */         MythicLogger.errorEntityConfig(this, mc, "Invalid BodyColor specified");
/*    */       } 
/*    */     }
/* 41 */     if (strPatternColor != null) {
/*    */       try {
/* 43 */         this.patternColor = DyeColor.valueOf(strPatternColor.toUpperCase());
/* 44 */       } catch (Exception ex) {
/* 45 */         MythicLogger.errorEntityConfig(this, mc, "Invalid PatternColor specified");
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 52 */     Entity e = location.getWorld().spawnEntity(location, EntityType.TROPICAL_FISH);
/*    */     
/* 54 */     e = applyOptions(e);
/*    */     
/* 56 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 61 */     Entity e = location.getWorld().spawnEntity(location, EntityType.TROPICAL_FISH);
/*    */     
/* 63 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 68 */     TropicalFish e = (TropicalFish)entity;
/*    */     
/* 70 */     if (this.pattern != null) {
/* 71 */       e.setPattern(this.pattern);
/*    */     }
/* 73 */     if (this.bodyColor != null) {
/* 74 */       e.setBodyColor(this.bodyColor);
/*    */     }
/* 76 */     if (this.patternColor != null) {
/* 77 */       e.setPatternColor(this.patternColor);
/*    */     }
/*    */     
/* 80 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 85 */     return e instanceof TropicalFish;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 90 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitTropicalFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */