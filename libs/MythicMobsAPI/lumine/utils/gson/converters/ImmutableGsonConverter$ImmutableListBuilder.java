/*    */ package lumine.utils.gson.converters;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import io.lumine.utils.gson.converters.AbstractGsonConverter;
/*    */ import io.lumine.utils.gson.converters.ImmutableGsonConverter;
/*    */ import java.util.List;
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
/*    */ final class ImmutableListBuilder<E>
/*    */   implements AbstractGsonConverter.ListBuilder<ImmutableList<E>, E>
/*    */ {
/* 77 */   private final ImmutableList.Builder<E> builder = ImmutableList.builder();
/*    */ 
/*    */   
/*    */   public void add(@Nullable E element) {
/* 81 */     if (element == null) {
/*    */       return;
/*    */     }
/* 84 */     this.builder.add(element);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImmutableList<E> build() {
/* 89 */     return this.builder.build();
/*    */   }
/*    */   
/*    */   private ImmutableListBuilder() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\ImmutableGsonConverter$ImmutableListBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */