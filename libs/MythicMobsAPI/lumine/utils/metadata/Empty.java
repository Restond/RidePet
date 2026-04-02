/*    */ package lumine.utils.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Empty
/*    */ {
/* 12 */   private static final io.lumine.utils.metadata.Empty INSTANCE = new io.lumine.utils.metadata.Empty();
/*    */   
/*    */   public static io.lumine.utils.metadata.Empty instance() {
/* 15 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 24 */     return (obj == this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 29 */     return "Empty";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\metadata\Empty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */