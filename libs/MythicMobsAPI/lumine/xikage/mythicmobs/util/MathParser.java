/*     */ package lumine.xikage.mythicmobs.util;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class MathParser
/*     */ {
/*   7 */   public static Random r = new Random();
/*     */   
/*     */   public static double evalRange(String strValue) {
/*  10 */     double amount = 0.0D;
/*     */     
/*  12 */     if (strValue.contains("to")) {
/*  13 */       String[] split = strValue.split("to");
/*  14 */       double min = Double.parseDouble(split[0]);
/*  15 */       double max = Double.parseDouble(split[1]);
/*  16 */       amount = r.nextDouble() * (max - min) + min;
/*  17 */     } else if (strValue.contains("-")) {
/*  18 */       String[] split = strValue.split("-");
/*  19 */       double min = Double.parseDouble(split[0]);
/*  20 */       double max = Double.parseDouble(split[1]);
/*  21 */       amount = r.nextDouble() * (max - min) + min;
/*     */     } else {
/*  23 */       amount = Double.valueOf(strValue).doubleValue();
/*     */     } 
/*     */     
/*  26 */     return amount;
/*     */   }
/*     */   
/*     */   public static double evalChance(String strValue) {
/*  30 */     double amount = 0.0D;
/*     */     
/*  32 */     if (strValue.endsWith("%")) {
/*  33 */       String a = strValue.substring(0, strValue.length() - 1);
/*  34 */       amount = Double.valueOf(a).doubleValue() / 100.0D;
/*     */     } else {
/*  36 */       amount = Double.valueOf(strValue).doubleValue();
/*     */     } 
/*     */     
/*  39 */     return amount;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static double eval(String str) {
/* 130 */     return (new Parser(str)).parse();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\MathParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */