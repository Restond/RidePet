/*    */ package lumine.xikage.mythicmobs.util.reflections.util;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import io.lumine.xikage.mythicmobs.util.reflections.util.FilterBuilder;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Matcher
/*    */   implements Predicate<String>
/*    */ {
/*    */   final Pattern pattern;
/*    */   
/*    */   public Matcher(String regex) {
/* 75 */     this.pattern = Pattern.compile(regex);
/*    */   } public String toString() {
/* 77 */     return this.pattern.pattern();
/*    */   }
/*    */   
/*    */   public abstract boolean apply(String paramString);
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\reflection\\util\FilterBuilder$Matcher.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */