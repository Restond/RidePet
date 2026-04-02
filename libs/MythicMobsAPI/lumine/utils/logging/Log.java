/*    */ package lumine.utils.logging;
/*    */ 
/*    */ import io.lumine.utils.logging.ConsoleColor;
/*    */ import io.lumine.utils.plugin.LoaderUtils;
/*    */ import java.util.logging.Level;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Log
/*    */ {
/*    */   public static void info(String s) {
/* 13 */     LoaderUtils.getPlugin().getLogger().log(Level.INFO, s);
/*    */   }
/*    */   
/*    */   public static void info(String s, Object... params) {
/* 17 */     LoaderUtils.getPlugin().getLogger().log(Level.INFO, s, params);
/*    */   }
/*    */   
/*    */   public static void info(ConsoleColor color, String s) {
/* 21 */     LoaderUtils.getPlugin().getLogger().log(Level.INFO, color + s + ConsoleColor.WHITE);
/*    */   }
/*    */   
/*    */   public static void info(ConsoleColor color, String s, Object... params) {
/* 25 */     LoaderUtils.getPlugin().getLogger().log(Level.INFO, color + s + ConsoleColor.WHITE, params);
/*    */   }
/*    */   
/*    */   public static void warn(String s) {
/* 29 */     LoaderUtils.getPlugin().getLogger().log(Level.WARNING, ConsoleColor.YELLOW + s + ConsoleColor.WHITE);
/*    */   }
/*    */   
/*    */   public static void warn(String s, Object... params) {
/* 33 */     LoaderUtils.getPlugin().getLogger().log(Level.WARNING, ConsoleColor.YELLOW + s + ConsoleColor.WHITE);
/*    */   }
/*    */   
/*    */   public static void severe(String s) {
/* 37 */     LoaderUtils.getPlugin().getLogger().log(Level.SEVERE, ConsoleColor.RED + s + ConsoleColor.WHITE);
/*    */   }
/*    */   
/*    */   public static void severe(String s, Exception ex) {
/* 41 */     LoaderUtils.getPlugin().getLogger().log(Level.SEVERE, ConsoleColor.RED + s + ConsoleColor.WHITE);
/* 42 */     ex.printStackTrace();
/*    */   }
/*    */   
/*    */   public static void error(String s) {
/* 46 */     LoaderUtils.getPlugin().getLogger().warning(ConsoleColor.RED + s + ConsoleColor.WHITE);
/*    */   }
/*    */   
/*    */   public static void good(String s) {
/* 50 */     LoaderUtils.getPlugin().getLogger().warning(ConsoleColor.GREEN + s + ConsoleColor.WHITE);
/*    */   }
/*    */   
/*    */   private Log() {
/* 54 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\logging\Log.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */