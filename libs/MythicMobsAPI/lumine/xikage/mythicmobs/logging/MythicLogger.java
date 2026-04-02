/*     */ package lumine.xikage.mythicmobs.logging;
/*     */ 
/*     */ import io.lumine.utils.logging.ConsoleColor;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*     */ import io.lumine.xikage.mythicmobs.drops.DropTable;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawner;
/*     */ import java.util.logging.Level;
/*     */ 
/*     */ 
/*     */ public class MythicLogger
/*     */ {
/*     */   public static void log(String message) {
/*  23 */     MythicMobs.inst().getLogger().log(Level.INFO, message + ConsoleColor.RESET);
/*     */   }
/*     */   
/*     */   public static void log(String message, Object... params) {
/*  27 */     MythicMobs.inst().getLogger().log(Level.INFO, message + ConsoleColor.RESET, params);
/*     */   }
/*     */   
/*     */   public static void log(Level level, String message) {
/*  31 */     MythicMobs.inst().getLogger().log(level, message + ConsoleColor.RESET);
/*     */   }
/*     */   
/*     */   public static void log(Level level, String message, Object... params) {
/*  35 */     MythicMobs.inst().getLogger().log(level, message + ConsoleColor.RESET, params);
/*     */   }
/*     */   
/*     */   public static void error(String message) {
/*  39 */     log(Level.WARNING, message + ConsoleColor.RESET);
/*     */   }
/*     */   
/*     */   public static void error(String message, Object... params) {
/*  43 */     log(Level.WARNING, message + ConsoleColor.RESET, params);
/*     */   }
/*     */   
/*     */   public static void errorGenericConfig(String error) {
/*  47 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Config Error: " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorGenericConfig(MythicLineConfig config, String error) {
/*  51 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " LineConfig Error: " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorConditionConfig(SkillCondition condition, MythicLineConfig config, String error) {
/*  55 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Config Error for Condition line '" + ConsoleColor.UNDERLINE + config.getLine() + ConsoleColor.RESET + ConsoleColor.YELLOW + "': " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorDropConfig(Drop drop, MythicLineConfig config, String error) {
/*  59 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Config Error for Drop '" + ConsoleColor.UNDERLINE + drop.getLine() + ConsoleColor.RESET + ConsoleColor.YELLOW + "' in '" + config.getFileName() + "': " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorDropTableConfig(DropTable drop, MythicLineConfig config, String error) {
/*  63 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Config Error for DropTable '" + ConsoleColor.UNDERLINE + drop.getInternalName() + ConsoleColor.RESET + ConsoleColor.YELLOW + "' in '" + config.getFileName() + "': " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorEntityConfig(MythicEntity entity, MythicConfig config, String error) {
/*  67 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Config Error for Mob '" + ConsoleColor.UNDERLINE + config.getKey() + ConsoleColor.RESET + ConsoleColor.YELLOW + "' in '" + config.getFile().getName() + "': " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorItemConfig(MythicItem item, MythicConfig config, String error) {
/*  71 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Config Error for Item '" + ConsoleColor.UNDERLINE + config.getKey() + ConsoleColor.RESET + ConsoleColor.YELLOW + "' in '" + config.getFile().getName() + "': " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorMechanicConfig(SkillMechanic mechanic, MythicLineConfig config, String error) {
/*  75 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Config Error for Mechanic line '" + ConsoleColor.UNDERLINE + config.getLine() + ConsoleColor.RESET + ConsoleColor.YELLOW + "' in '" + config.getFileName() + "': " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorMobConfig(MythicMob mob, MythicConfig config, String error) {
/*  79 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Config Error for Mob '" + ConsoleColor.UNDERLINE + config.getKey() + ConsoleColor.RESET + ConsoleColor.YELLOW + "' in '" + config.getFile().getName() + "': " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorTargeterConfig(SkillTargeter targeter, MythicLineConfig config, String error) {
/*  83 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Config Error for Targeter line '" + ConsoleColor.UNDERLINE + config.getLine() + ConsoleColor.RESET + ConsoleColor.YELLOW + "': " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorSpawnConfig(RandomSpawner spawner, MythicConfig config, String error) {
/*  87 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Config Error for RandomSpawn '" + ConsoleColor.UNDERLINE + config.getKey() + ConsoleColor.RESET + ConsoleColor.YELLOW + "' in '" + config.getFile().getName() + "': " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void errorCompatibility(String plugin, String error) {
/*  91 */     error("" + ConsoleColor.ERROR_MARK + ConsoleColor.YELLOW + " Compatibility Error for '" + ConsoleColor.UNDERLINE + plugin + ConsoleColor.RESET + ConsoleColor.YELLOW + "': " + ConsoleColor.WHITE + error);
/*     */   }
/*     */   
/*     */   public static void debug(DebugLevel level, String message, Object... params) {
/*  95 */     if (ConfigManager.debugLevel == 0) {
/*     */       return;
/*     */     }
/*  98 */     if (ConfigManager.debugLevel < level.ordinal()) {
/*     */       return;
/*     */     }
/*     */     
/* 102 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$logging$MythicLogger$DebugLevel[level.ordinal()]) {
/*     */       case 1:
/* 104 */         log(Level.INFO, ConsoleColor.CYAN + "[i] " + message, params);
/*     */         return;
/*     */       
/*     */       case 2:
/* 108 */         log(Level.INFO, ConsoleColor.PURPLE + "+--- " + ConsoleColor.CYAN + message, params);
/*     */         return;
/*     */       
/*     */       case 3:
/* 112 */         log(Level.INFO, ConsoleColor.PURPLE + "|----- " + ConsoleColor.CYAN + message, params);
/*     */         return;
/*     */       
/*     */       case 4:
/* 116 */         log(Level.INFO, ConsoleColor.PURPLE + "|------- " + ConsoleColor.CYAN + message, params);
/*     */         return;
/*     */       
/*     */       case 5:
/* 120 */         log(Level.INFO, ConsoleColor.PURPLE + "~----------- " + ConsoleColor.CYAN + message, params);
/*     */         return;
/*     */       
/*     */       case 6:
/* 124 */         log(Level.INFO, ConsoleColor.PURPLE + "|--------? " + ConsoleColor.CYAN + message, params);
/*     */         return;
/*     */       
/*     */       case 7:
/* 128 */         log(Level.INFO, ConsoleColor.PURPLE + "#- " + ConsoleColor.CYAN + message, params);
/*     */         return;
/*     */       
/*     */       case 8:
/* 132 */         log(Level.INFO, ConsoleColor.CYAN + "[e] " + message, params);
/*     */         return;
/*     */       
/*     */       case 9:
/* 136 */         log(Level.INFO, ConsoleColor.CYAN + "[c] " + message, params);
/*     */         return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MythicLogger() {
/* 158 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\logging\MythicLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */