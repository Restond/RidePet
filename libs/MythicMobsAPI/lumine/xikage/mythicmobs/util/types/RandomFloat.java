/*    */ package lumine.xikage.mythicmobs.util.types;
/*    */ 
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class RandomFloat
/*    */ {
/*  7 */   private static Pattern PATTERN = Pattern.compile("^-?[0-9]*(\\.\\d+)?(to|-)?[0-9]\\d*(\\.\\d+)?");
/*    */   
/*    */   private boolean isStatic;
/*    */   private float min;
/*    */   private float max;
/*    */   
/*    */   public RandomFloat(float i) {
/* 14 */     this.min = i;
/* 15 */     this.max = i;
/* 16 */     this.isStatic = true;
/*    */   }
/*    */   
/*    */   public RandomFloat(float i, float j) {
/* 20 */     this.min = i;
/* 21 */     this.max = j;
/* 22 */     this.isStatic = false;
/*    */   }
/*    */   
/*    */   public RandomFloat(String value) {
/*    */     try {
/* 27 */       if (value.contains("to")) {
/* 28 */         String[] split = value.split("to");
/* 29 */         this.min = Float.valueOf(split[0]).floatValue();
/* 30 */         this.max = Float.valueOf(split[1]).floatValue();
/* 31 */         this.isStatic = false;
/* 32 */       } else if (!value.startsWith("-") && value.contains("-")) {
/* 33 */         String[] split = value.split("-");
/* 34 */         this.min = Integer.valueOf(split[0]).intValue();
/* 35 */         this.max = Integer.valueOf(split[1]).intValue();
/* 36 */         this.isStatic = false;
/*    */       } else {
/* 38 */         this.min = Float.valueOf(value).floatValue();
/* 39 */         this.max = Float.valueOf(value).floatValue();
/* 40 */         this.isStatic = true;
/*    */       } 
/* 42 */     } catch (Exception ex) {
/* 43 */       this.min = 1.0F;
/* 44 */       this.max = 1.0F;
/* 45 */       this.isStatic = true;
/* 46 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public float get() {
/* 51 */     if (this.isStatic) {
/* 52 */       return this.min;
/*    */     }
/* 54 */     return this.min + (float)Math.random() * (this.max - this.min);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     return "RandomFloat{" + this.min + " to " + this.max + "}";
/*    */   }
/*    */   
/*    */   public static boolean matches(String value) {
/* 64 */     return PATTERN.matcher(value).find();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\types\RandomFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */