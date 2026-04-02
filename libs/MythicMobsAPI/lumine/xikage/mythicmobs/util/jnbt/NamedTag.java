/*    */ package lumine.xikage.mythicmobs.util.jnbt;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
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
/*    */ public class NamedTag
/*    */ {
/*    */   private final String name;
/*    */   private final Tag tag;
/*    */   
/*    */   public NamedTag(String name, Tag tag) {
/* 37 */     checkNotNull(name);
/* 38 */     checkNotNull(tag);
/* 39 */     this.name = name;
/* 40 */     this.tag = tag;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 49 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Tag getTag() {
/* 58 */     return this.tag;
/*    */   }
/*    */   
/*    */   private static void checkNotNull(Object object) {
/* 62 */     if (object == null)
/* 63 */       throw new NullPointerException(); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\NamedTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */