/*    */ package lumine.xikage.mythicmobs.util;
/*    */ 
/*    */ 
/*    */ public final class NumberConversions
/*    */ {
/*    */   public static int floor(double num) {
/*  7 */     int floor = (int)num;
/*  8 */     return (floor == num) ? floor : (floor - (int)(Double.doubleToRawLongBits(num) >>> 63L));
/*    */   }
/*    */   
/*    */   public static int ceil(double num) {
/* 12 */     int floor = (int)num;
/* 13 */     return (floor == num) ? floor : (floor + (int)((Double.doubleToRawLongBits(num) ^ 0xFFFFFFFFFFFFFFFFL) >>> 63L));
/*    */   }
/*    */   
/*    */   public static int round(double num) {
/* 17 */     return floor(num + 0.5D);
/*    */   }
/*    */   
/*    */   public static double square(double num) {
/* 21 */     return num * num;
/*    */   }
/*    */   
/*    */   public static int toInt(Object object) {
/* 25 */     if (object instanceof Number) {
/* 26 */       return ((Number)object).intValue();
/*    */     }
/*    */ 
/*    */     
/* 30 */     try { return Integer.valueOf(object.toString()).intValue(); }
/* 31 */     catch (NumberFormatException numberFormatException) {  }
/* 32 */     catch (NullPointerException nullPointerException) {}
/*    */     
/* 34 */     return 0;
/*    */   }
/*    */   
/*    */   public static float toFloat(Object object) {
/* 38 */     if (object instanceof Number) {
/* 39 */       return ((Number)object).floatValue();
/*    */     }
/*    */ 
/*    */     
/* 43 */     try { return Float.valueOf(object.toString()).floatValue(); }
/* 44 */     catch (NumberFormatException numberFormatException) {  }
/* 45 */     catch (NullPointerException nullPointerException) {}
/*    */     
/* 47 */     return 0.0F;
/*    */   }
/*    */   
/*    */   public static double toDouble(Object object) {
/* 51 */     if (object instanceof Number) {
/* 52 */       return ((Number)object).doubleValue();
/*    */     }
/*    */ 
/*    */     
/* 56 */     try { return Double.valueOf(object.toString()).doubleValue(); }
/* 57 */     catch (NumberFormatException numberFormatException) {  }
/* 58 */     catch (NullPointerException nullPointerException) {}
/*    */     
/* 60 */     return 0.0D;
/*    */   }
/*    */   
/*    */   public static long toLong(Object object) {
/* 64 */     if (object instanceof Number) {
/* 65 */       return ((Number)object).longValue();
/*    */     }
/*    */ 
/*    */     
/* 69 */     try { return Long.valueOf(object.toString()).longValue(); }
/* 70 */     catch (NumberFormatException numberFormatException) {  }
/* 71 */     catch (NullPointerException nullPointerException) {}
/*    */     
/* 73 */     return 0L;
/*    */   }
/*    */   
/*    */   public static short toShort(Object object) {
/* 77 */     if (object instanceof Number) {
/* 78 */       return ((Number)object).shortValue();
/*    */     }
/*    */ 
/*    */     
/* 82 */     try { return Short.valueOf(object.toString()).shortValue(); }
/* 83 */     catch (NumberFormatException numberFormatException) {  }
/* 84 */     catch (NullPointerException nullPointerException) {}
/*    */     
/* 86 */     return 0;
/*    */   }
/*    */   
/*    */   public static byte toByte(Object object) {
/* 90 */     if (object instanceof Number) {
/* 91 */       return ((Number)object).byteValue();
/*    */     }
/*    */ 
/*    */     
/* 95 */     try { return Byte.valueOf(object.toString()).byteValue(); }
/* 96 */     catch (NumberFormatException numberFormatException) {  }
/* 97 */     catch (NullPointerException nullPointerException) {}
/*    */     
/* 99 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\NumberConversions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */