/*    */ package lumine.xikage.mythicmobs.mobs;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldScaling
/*    */ {
/*    */   private boolean enabled;
/*    */   private String world;
/*    */   private double scaleFactor_perBlocksFromSpawn;
/* 16 */   public static ConcurrentHashMap<String, io.lumine.xikage.mythicmobs.mobs.WorldScaling> worldSettings = new ConcurrentHashMap<>();
/*    */   
/*    */   public WorldScaling(String world, MythicConfig mc) {
/* 19 */     this.world = world;
/* 20 */     this.enabled = mc.getBoolean("Enabled", true);
/* 21 */     this.scaleFactor_perBlocksFromSpawn = mc.getDouble("PerBlocksFromSpawn", -1.0D);
/* 22 */     MythicMobs.debug(2, "**** WorldScaling for world Enabled: " + this.enabled);
/* 23 */     MythicMobs.debug(2, "**** WorldScaling for world perBlocksFromSpawn: " + this.scaleFactor_perBlocksFromSpawn);
/*    */   }
/*    */   
/*    */   public WorldScaling(String world) {
/* 27 */     this.world = world;
/* 28 */     this.enabled = false;
/*    */   }
/*    */   
/*    */   public boolean isEnabled() {
/* 32 */     return this.enabled;
/*    */   }
/*    */   public String getWorldName() {
/* 35 */     return this.world;
/*    */   }
/*    */   public double getScaleFactor_BlocksFromSpawn() {
/* 38 */     return this.scaleFactor_perBlocksFromSpawn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void initialize(String world, MythicConfig mc) {
/* 45 */     io.lumine.xikage.mythicmobs.mobs.WorldScaling ws = new io.lumine.xikage.mythicmobs.mobs.WorldScaling(world, mc);
/* 46 */     worldSettings.put(world, ws);
/*    */   }
/*    */   public static io.lumine.xikage.mythicmobs.mobs.WorldScaling get(String world) {
/* 49 */     if (worldSettings.containsKey(world)) {
/* 50 */       return worldSettings.get(world);
/*    */     }
/* 52 */     if (worldSettings.contains("Default")) {
/* 53 */       return worldSettings.get("Default");
/*    */     }
/* 55 */     io.lumine.xikage.mythicmobs.mobs.WorldScaling ws = new io.lumine.xikage.mythicmobs.mobs.WorldScaling(world);
/* 56 */     worldSettings.put(world, ws);
/* 57 */     return ws;
/*    */   }
/*    */   
/*    */   public static void reset() {
/* 61 */     worldSettings.clear();
/*    */   }
/*    */   public static int getLevelBonus(AbstractLocation l) {
/* 64 */     io.lumine.xikage.mythicmobs.mobs.WorldScaling ws = get(l.getWorld().getName());
/*    */     
/* 66 */     if (!ws.isEnabled()) return 0;
/*    */     
/* 68 */     int lvl = 0;
/*    */     
/* 70 */     if (ws.getScaleFactor_BlocksFromSpawn() > 0.0D) {
/* 71 */       double d = l.distance2D(l.getWorld().getSpawnLocation());
/* 72 */       lvl = (int)(lvl + Math.floor(d / ws.getScaleFactor_BlocksFromSpawn()));
/*    */     } 
/*    */ 
/*    */     
/* 76 */     return lvl;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\WorldScaling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */