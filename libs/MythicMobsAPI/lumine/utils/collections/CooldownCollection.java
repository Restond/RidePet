/*     */ package lumine.utils.collections;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import io.lumine.utils.Cooldown;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.function.Function;
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
/*     */ public class CooldownCollection<T>
/*     */ {
/*     */   private final LoadingCache<String, Cooldown> cache;
/*     */   private final Function<T, String> mappingFunc;
/*     */   
/*     */   public static io.lumine.utils.collections.CooldownCollection<String> create(Cooldown base) {
/*  26 */     Preconditions.checkNotNull(base, "base");
/*  27 */     return new io.lumine.utils.collections.CooldownCollection<>(s -> s, base);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> io.lumine.utils.collections.CooldownCollection<T> create(Function<T, String> mappingFunc, Cooldown base) {
/*  39 */     Preconditions.checkNotNull(mappingFunc, "mappingFunc");
/*  40 */     Preconditions.checkNotNull(base, "base");
/*  41 */     return new io.lumine.utils.collections.CooldownCollection<>(mappingFunc, base);
/*     */   }
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
/*     */   public static <T> io.lumine.utils.collections.CooldownCollection<T> createWithToString(Cooldown base) {
/*  54 */     Preconditions.checkNotNull(base, "base");
/*  55 */     return new io.lumine.utils.collections.CooldownCollection<>(Object::toString, base);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CooldownCollection(Function<T, String> mappingFunc, Cooldown base) {
/*  62 */     this
/*     */ 
/*     */       
/*  65 */       .cache = CacheBuilder.newBuilder().expireAfterAccess(base.getTimeout() + 10000L, TimeUnit.MILLISECONDS).build((CacheLoader)new Object(this, base));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     this.mappingFunc = mappingFunc;
/*     */   }
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
/*     */   public Cooldown get(T t) {
/*  84 */     return (Cooldown)this.cache.getUnchecked(this.mappingFunc.apply(t));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean test(T t) {
/*  90 */     return get(t).test();
/*     */   }
/*     */   
/*     */   public boolean testSilently(T t) {
/*  94 */     return get(t).testSilently();
/*     */   }
/*     */   
/*     */   public long elapsed(T t) {
/*  98 */     return get(t).elapsed();
/*     */   }
/*     */   
/*     */   public void reset(T t) {
/* 102 */     get(t).reset();
/*     */   }
/*     */   
/*     */   public long remainingMillis(T t) {
/* 106 */     return get(t).remainingMillis();
/*     */   }
/*     */   
/*     */   public long remainingTime(T t, TimeUnit unit) {
/* 110 */     return get(t).remainingTime(unit);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\collections\CooldownCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */