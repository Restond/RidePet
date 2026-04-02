/*    */ package lumine.utils;
/*    */ 
/*    */ import java.time.Instant;
/*    */ import java.time.LocalDateTime;
/*    */ 
/*    */ public final class Time
/*    */ {
/*    */   public static long now() {
/*  9 */     return Instant.now().toEpochMilli();
/*    */   }
/*    */   
/*    */   public static long nowUnix() {
/* 13 */     return Instant.now().getEpochSecond();
/*    */   }
/*    */   
/*    */   public static long getEpochDay() {
/* 17 */     return LocalDateTime.now().toLocalDate().toEpochDay();
/*    */   }
/*    */   
/*    */   public static String toShortForm(long seconds) {
/* 21 */     if (seconds == 0L) {
/* 22 */       return "0s";
/*    */     }
/*    */     
/* 25 */     long minute = seconds / 60L;
/* 26 */     seconds %= 60L;
/* 27 */     long hour = minute / 60L;
/* 28 */     minute %= 60L;
/* 29 */     long day = hour / 24L;
/* 30 */     hour %= 24L;
/*    */     
/* 32 */     StringBuilder time = new StringBuilder();
/* 33 */     if (day != 0L) {
/* 34 */       time.append(day).append("d ");
/*    */     }
/* 36 */     if (hour != 0L) {
/* 37 */       time.append(hour).append("h ");
/*    */     }
/* 39 */     if (minute != 0L) {
/* 40 */       time.append(minute).append("m ");
/*    */     }
/* 42 */     if (seconds != 0L) {
/* 43 */       time.append(seconds).append("s");
/*    */     }
/*    */     
/* 46 */     return time.toString().trim();
/*    */   }
/*    */   
/*    */   public static String toLongForm(long seconds) {
/* 50 */     if (seconds == 0L) {
/* 51 */       return "0 seconds";
/*    */     }
/*    */     
/* 54 */     long minute = seconds / 60L;
/* 55 */     seconds %= 60L;
/* 56 */     long hour = minute / 60L;
/* 57 */     minute %= 60L;
/* 58 */     long day = hour / 24L;
/* 59 */     hour %= 24L;
/*    */     
/* 61 */     StringBuilder time = new StringBuilder();
/* 62 */     if (day != 0L) {
/* 63 */       time.append(day);
/*    */     }
/* 65 */     if (day == 1L) {
/* 66 */       time.append(" day ");
/* 67 */     } else if (day > 1L) {
/* 68 */       time.append(" days ");
/*    */     } 
/* 70 */     if (hour != 0L) {
/* 71 */       time.append(hour);
/*    */     }
/* 73 */     if (hour == 1L) {
/* 74 */       time.append(" hour ");
/* 75 */     } else if (hour > 1L) {
/* 76 */       time.append(" hours ");
/*    */     } 
/* 78 */     if (minute != 0L) {
/* 79 */       time.append(minute);
/*    */     }
/* 81 */     if (minute == 1L) {
/* 82 */       time.append(" minute ");
/* 83 */     } else if (minute > 1L) {
/* 84 */       time.append(" minutes ");
/*    */     } 
/* 86 */     if (seconds != 0L) {
/* 87 */       time.append(seconds);
/*    */     }
/* 89 */     if (seconds == 1L) {
/* 90 */       time.append(" second");
/* 91 */     } else if (seconds > 1L) {
/* 92 */       time.append(" seconds");
/*    */     } 
/*    */     
/* 95 */     return time.toString().trim();
/*    */   }
/*    */   
/*    */   private Time() {
/* 99 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Time.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */