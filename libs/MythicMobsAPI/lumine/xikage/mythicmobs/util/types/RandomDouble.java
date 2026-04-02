/*    */ package lumine.xikage.mythicmobs.util.types;
/*    */ 
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class RandomDouble
/*    */ {
/*  7 */   private static Pattern PATTERN = Pattern.compile("^-?[0-9]*(\\.\\d+)?(to|-)?[0-9]\\d*(\\.\\d+)?");
/*    */   
/*    */   private boolean isStatic;
/*    */   private double min;
/*    */   private double max;
/*    */   
/*    */   public RandomDouble(double i) {
/* 14 */     this.min = i;
/* 15 */     this.max = i;
/* 16 */     this.isStatic = true;
/*    */   }
/*    */   
/*    */   public RandomDouble(double i, double j) {
/* 20 */     this.min = i;
/* 21 */     this.max = j;
/* 22 */     this.isStatic = false;
/*    */   }
/*    */   
/*    */   public RandomDouble(String value) {
/*    */     try {
/* 27 */       if (value.contains("to")) {
/* 28 */         String[] split = value.split("to");
/* 29 */         this.min = Double.valueOf(split[0]).doubleValue();
/* 30 */         this.max = Double.valueOf(split[1]).doubleValue();
/* 31 */         this.isStatic = false;
/* 32 */       } else if (!value.startsWith("-") && value.contains("-")) {
/* 33 */         String[] split = value.split("-");
/* 34 */         this.min = Integer.valueOf(split[0]).intValue();
/* 35 */         this.max = Integer.valueOf(split[1]).intValue();
/* 36 */         this.isStatic = false;
/*    */       } else {
/* 38 */         this.min = Double.valueOf(value).doubleValue();
/* 39 */         this.max = Double.valueOf(value).doubleValue();
/* 40 */         this.isStatic = true;
/*    */       } 
/* 42 */     } catch (Exception ex) {
/* 43 */       this.min = 1.0D;
/* 44 */       this.max = 1.0D;
/* 45 */       this.isStatic = true;
/* 46 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public double get() {
/* 51 */     if (this.isStatic) {
/* 52 */       return this.min;
/*    */     }
/* 54 */     return this.min + Math.random() * (this.max - this.min);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     return "RandomDouble{" + this.min + " to " + this.max + "}";
/*    */   }
/*    */   
/*    */   public static boolean matches(String value) {
/* 64 */     return PATTERN.matcher(value).find();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\types\RandomDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */