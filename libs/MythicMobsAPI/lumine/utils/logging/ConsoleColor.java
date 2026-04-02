/*    */ package lumine.utils.logging;
/*    */ 
/*    */ public enum ConsoleColor
/*    */ {
/*  5 */   BLACK("\033[30m"),
/*  6 */   RED("\033[31m"),
/*  7 */   GREEN("\033[32m"),
/*  8 */   YELLOW("\033[33m"),
/*  9 */   BLUE("\033[34m"),
/* 10 */   PURPLE("\033[35m"),
/* 11 */   CYAN("\033[36m"),
/* 12 */   WHITE("\033[37m"),
/* 13 */   RESET("\033[0m"),
/*    */   
/* 15 */   BOLD("\033[1m"),
/* 16 */   ITALICS("\033[2m"),
/* 17 */   UNDERLINE("\033[4m"),
/*    */   
/* 19 */   CHECK_MARK("✓"),
/* 20 */   ERROR_MARK("✗");
/*    */   
/*    */   private String ansiColor;
/*    */ 
/*    */   
/*    */   ConsoleColor(String ansiColor) {
/* 26 */     this.ansiColor = ansiColor;
/*    */   }
/*    */   
/*    */   public String getAnsiColor() {
/* 30 */     return this.ansiColor;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 34 */     return this.ansiColor;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\logging\ConsoleColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */