/*    */ package lumine.utils.serialization;
/*    */ 
/*    */ import io.lumine.utils.serialization.SerializingModule;
/*    */ import java.io.File;
/*    */ 
/*    */ public class WrappedJsonFile<T> {
/*    */   private final SerializingModule parent;
/*    */   private final File file;
/*    */   private T value;
/*    */   
/*    */   public WrappedJsonFile(SerializingModule parent, File file, T value) {
/* 12 */     this.parent = parent;
/* 13 */     this.file = file;
/* 14 */     this.value = value;
/*    */   }
/*    */   
/*    */   public void set(T value) {
/* 18 */     this.value = value;
/*    */   }
/*    */   
/*    */   public T get() {
/* 22 */     return this.value;
/*    */   }
/*    */   
/*    */   public void save() {
/* 26 */     if (this.value == null) {
/*    */       return;
/*    */     }
/* 29 */     this.parent.saveJson(this.file, this.value);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\serialization\WrappedJsonFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */