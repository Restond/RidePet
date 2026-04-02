/*    */ package lumine.xikage.mythicmobs.util;
/*    */ 
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ public class Patterns
/*    */ {
/*    */   public static Pattern MSMobX;
/*    */   public static Pattern MSMobY;
/*    */   public static Pattern MSMobZ;
/*    */   public static Pattern MSTargetX;
/*    */   public static Pattern MSTargetY;
/*    */   
/*    */   public static void CompilePatterns() {
/* 15 */     LegacyBossX = Pattern.compile("boss_x%([0-9]+)");
/* 16 */     LegacyBossY = Pattern.compile("boss_y%([0-9]+)");
/* 17 */     LegacyBossZ = Pattern.compile("boss_z%([0-9]+)");
/* 18 */     LegacyPlayerX = Pattern.compile("player_x%([0-9]+)");
/* 19 */     LegacyPlayerY = Pattern.compile("player_y%([0-9]+)");
/* 20 */     LegacyPlayerYC = Pattern.compile("player_yc%([0-9]+)");
/* 21 */     LegacyPlayerZ = Pattern.compile("player_z%([0-9]+)");
/*    */     
/* 23 */     MSMobX = Pattern.compile("<mob.l.x%([0-9]+)>");
/* 24 */     MSMobY = Pattern.compile("<mob.l.y%([0-9]+)>");
/* 25 */     MSMobZ = Pattern.compile("<mob.l.z%([0-9]+)>");
/*    */     
/* 27 */     MSTargetX = Pattern.compile("<target.l.x%([0-9]+)>");
/* 28 */     MSTargetY = Pattern.compile("<target.l.y%([0-9]+)>");
/* 29 */     MSTargetZ = Pattern.compile("<target.l.z%([0-9]+)>");
/*    */     
/* 31 */     MSTriggerX = Pattern.compile("<trigger.l.x%([0-9]+)>");
/* 32 */     MSTriggerY = Pattern.compile("<trigger.l.y%([0-9]+)>");
/* 33 */     MSTriggerZ = Pattern.compile("<trigger.l.z%([0-9]+)>");
/*    */     
/* 35 */     VariableRanges = Pattern.compile("<random.([0-9]*)-([0-9]*)>");
/* 36 */     LoreRanges = Pattern.compile("\\{([0-9]*)-([0-9]*)\\}");
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     MobScore = Pattern.compile("<mob.score.([a-zA-Z0-9]+)>");
/*    */     
/* 43 */     GlobalScore = Pattern.compile("<global.score.([a-zA-Z0-9]+)>");
/*    */     
/* 45 */     TargetScore = Pattern.compile("<target.score.([a-zA-Z0-9]+)>");
/*    */     
/* 47 */     TriggerScore = Pattern.compile("<trigger.score.([a-zA-Z0-9]+)>");
/*    */     
/* 49 */     GenericScore = Pattern.compile("<score.([-a-zA-Z0-9]+).([a-zA-Z0-9]+)>");
/*    */   }
/*    */   
/*    */   public static Pattern MSTargetZ;
/*    */   public static Pattern MSTriggerX;
/*    */   public static Pattern MSTriggerY;
/*    */   public static Pattern MSTriggerZ;
/*    */   public static Pattern VariableRanges;
/*    */   public static Pattern MobScore;
/*    */   public static Pattern GlobalScore;
/*    */   public static Pattern TargetScore;
/*    */   public static Pattern TriggerScore;
/*    */   public static Pattern GenericScore;
/*    */   public static Pattern LegacyBossX;
/*    */   public static Pattern LegacyBossY;
/*    */   public static Pattern LegacyBossZ;
/*    */   public static Pattern LegacyPlayerX;
/*    */   public static Pattern LegacyPlayerY;
/*    */   public static Pattern LegacyPlayerYC;
/*    */   public static Pattern LegacyPlayerZ;
/*    */   public static Pattern LoreRanges;
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\Patterns.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */