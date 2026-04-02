/*     */ package lumine.xikage.mythicmobs.util.jnbt;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ListTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
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
/*     */ public class ListTagBuilder
/*     */ {
/*     */   private final Class<? extends Tag> type;
/*     */   private final List<Tag> entries;
/*     */   
/*     */   public ListTagBuilder(Class<? extends Tag> type) {
/*  41 */     checkNotNull(type);
/*  42 */     this.type = type;
/*  43 */     this.entries = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.ListTagBuilder add(Tag value) {
/*  53 */     checkNotNull(value);
/*  54 */     if (!this.type.isInstance(value)) {
/*  55 */       throw new IllegalArgumentException(value.getClass().getCanonicalName() + " is not of expected type " + this.type.getCanonicalName());
/*     */     }
/*  57 */     this.entries.add(value);
/*  58 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.ListTagBuilder addAll(Collection<? extends Tag> value) {
/*  68 */     checkNotNull(value);
/*  69 */     for (Tag v : value) {
/*  70 */       add(v);
/*     */     }
/*  72 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListTag build() {
/*  81 */     return new ListTag(this.type, new ArrayList<>(this.entries));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.util.jnbt.ListTagBuilder create(Class<? extends Tag> type) {
/*  90 */     return new io.lumine.xikage.mythicmobs.util.jnbt.ListTagBuilder(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T extends Tag> io.lumine.xikage.mythicmobs.util.jnbt.ListTagBuilder createWith(T... entries) {
/*  99 */     checkNotNull(entries);
/*     */     
/* 101 */     if (entries.length == 0) {
/* 102 */       throw new IllegalArgumentException("This method needs an array of at least one entry");
/*     */     }
/*     */     
/* 105 */     Class<? extends Tag> type = (Class)entries[0].getClass();
/* 106 */     for (int i = 1; i < entries.length; i++) {
/* 107 */       if (!type.isInstance(entries[i])) {
/* 108 */         throw new IllegalArgumentException("An array of different tag types was provided");
/*     */       }
/*     */     } 
/*     */     
/* 112 */     io.lumine.xikage.mythicmobs.util.jnbt.ListTagBuilder builder = new io.lumine.xikage.mythicmobs.util.jnbt.ListTagBuilder(type);
/* 113 */     builder.addAll(Arrays.asList((Tag[])entries));
/* 114 */     return builder;
/*     */   }
/*     */   
/*     */   private static void checkNotNull(Object object) {
/* 118 */     if (object == null)
/* 119 */       throw new NullPointerException(); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\ListTagBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */