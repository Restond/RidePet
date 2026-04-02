/*    */ package lumine.utils.gson.converters;
/*    */ 
/*    */ import io.lumine.utils.gson.converters.AbstractGsonConverter;
/*    */ import io.lumine.utils.gson.converters.MutableGsonConverter;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import javax.annotation.Nullable;
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
/*    */ final class MutableSetBuilder<E>
/*    */   implements AbstractGsonConverter.SetBuilder<HashSet<E>, E>
/*    */ {
/* 88 */   private final HashSet<E> builder = new HashSet<>();
/*    */ 
/*    */   
/*    */   public void add(@Nullable E element) {
/* 92 */     this.builder.add(element);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<E> build() {
/* 97 */     return this.builder;
/*    */   }
/*    */   
/*    */   private MutableSetBuilder() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\MutableGsonConverter$MutableSetBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */