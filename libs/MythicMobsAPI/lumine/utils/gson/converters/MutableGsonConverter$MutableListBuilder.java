/*    */ package lumine.utils.gson.converters;
/*    */ 
/*    */ import io.lumine.utils.gson.converters.AbstractGsonConverter;
/*    */ import io.lumine.utils.gson.converters.MutableGsonConverter;
/*    */ import java.util.ArrayList;
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
/*    */ final class MutableListBuilder<E>
/*    */   implements AbstractGsonConverter.ListBuilder<ArrayList<E>, E>
/*    */ {
/* 74 */   private final ArrayList<E> builder = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void add(@Nullable E element) {
/* 78 */     this.builder.add(element);
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<E> build() {
/* 83 */     return this.builder;
/*    */   }
/*    */   
/*    */   private MutableListBuilder() {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\MutableGsonConverter$MutableListBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */