/*    */ package lumine.xikage.mythicmobs.util.types;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ public class RandomLong
/*    */ {
/*  9 */   private static Pattern PATTERN = Pattern.compile("^-?[0-9]*(to|-)?[0-9]\\d*");
/*    */   
/*    */   final boolean isStatic;
/*    */   final long min;
/*    */   final long max;
/*    */   
/*    */   public RandomLong(long i) {
/* 16 */     this.min = i;
/* 17 */     this.max = i;
/* 18 */     this.isStatic = true;
/*    */   }
/*    */   
/*    */   public RandomLong(long i, long j) {
/* 22 */     this.min = i;
/* 23 */     this.max = j;
/* 24 */     this.isStatic = false;
/*    */   }
/*    */   
/*    */   public RandomLong(String value) {
/*    */     try {
/* 29 */       if (value.contains("to")) {
/* 30 */         String[] split = value.split("to");
/* 31 */         this.min = Integer.valueOf(split[0]).intValue();
/* 32 */         this.max = Integer.valueOf(split[1]).intValue();
/* 33 */         this.isStatic = false;
/* 34 */       } else if (!value.startsWith("-") && value.contains("-")) {
/* 35 */         String[] split = value.split("-");
/* 36 */         this.min = Integer.valueOf(split[0]).intValue();
/* 37 */         this.max = Integer.valueOf(split[1]).intValue();
/* 38 */         this.isStatic = false;
/*    */       } else {
/* 40 */         this.min = Integer.valueOf(value).intValue();
/* 41 */         this.max = Integer.valueOf(value).intValue();
/* 42 */         this.isStatic = true;
/*    */       } 
/* 44 */     } catch (Exception ex) {
/* 45 */       throw new IllegalArgumentException();
/*    */     } 
/*    */   }
/*    */   
/*    */   public long get() {
/* 50 */     if (this.isStatic) {
/* 51 */       return this.min;
/*    */     }
/* 53 */     long rand = this.min + MythicMobs.r.nextInt((int)(this.max - this.min + 1L));
/* 54 */     return rand;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     if (this.isStatic) {
/* 61 */       return String.valueOf(this.min);
/*    */     }
/* 63 */     return this.min + "to" + this.max;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean matches(String value) {
/* 68 */     return PATTERN.matcher(value).find();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\types\RandomLong.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */