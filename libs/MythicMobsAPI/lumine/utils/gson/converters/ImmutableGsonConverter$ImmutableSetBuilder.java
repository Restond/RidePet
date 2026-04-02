/*     */ package lumine.utils.gson.converters;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import io.lumine.utils.gson.converters.AbstractGsonConverter;
/*     */ import io.lumine.utils.gson.converters.ImmutableGsonConverter;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
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
/*     */ final class ImmutableSetBuilder<E>
/*     */   implements AbstractGsonConverter.SetBuilder<ImmutableSet<E>, E>
/*     */ {
/*  94 */   private final ImmutableSet.Builder<E> builder = ImmutableSet.builder();
/*     */ 
/*     */   
/*     */   public void add(@Nullable E element) {
/*  98 */     if (element == null) {
/*     */       return;
/*     */     }
/* 101 */     this.builder.add(element);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableSet<E> build() {
/* 106 */     return this.builder.build();
/*     */   }
/*     */   
/*     */   private ImmutableSetBuilder() {}
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\ImmutableGsonConverter$ImmutableSetBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */