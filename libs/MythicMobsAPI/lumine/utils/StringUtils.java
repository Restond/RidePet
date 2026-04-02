/*    */ package lumine.utils;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class StringUtils
/*    */ {
/*    */   public static List<String> split(String str, char c) {
/*  9 */     List<String> strings = new ArrayList<>();
/* 10 */     int start = 0;
/* 11 */     for (int i = 0; i < str.length(); i++) {
/* 12 */       if (str.charAt(i) == c) {
/* 13 */         strings.add(str.substring(start, i));
/* 14 */         start = i + 1;
/*    */       } 
/*    */     } 
/* 17 */     strings.add(str.substring(start, str.length()));
/* 18 */     return strings;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\StringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */