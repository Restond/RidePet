/*    */ package saukiya.sxattribute.util;
/*    */ 
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleDateFormatUtils
/*    */ {
/* 12 */   private ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(Config.getConfig().getString("Condition.ExpiryTime.Format")));
/*    */   
/*    */   public Date parse(String dateStr) throws ParseException {
/* 15 */     return ((SimpleDateFormat)this.threadLocal.get()).parse(dateStr);
/*    */   }
/*    */   
/*    */   public String format(Long date) {
/* 19 */     return ((SimpleDateFormat)this.threadLocal.get()).format(date);
/*    */   }
/*    */   
/*    */   public void reload() {
/* 23 */     this.threadLocal.remove();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribut\\util\SimpleDateFormatUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */