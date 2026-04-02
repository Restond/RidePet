/*    */ package lumine.utils.gson.converters;
/*    */ 
/*    */ import io.lumine.utils.gson.converters.GsonConverter;
/*    */ import io.lumine.utils.gson.converters.ImmutableGsonConverter;
/*    */ import io.lumine.utils.gson.converters.MutableGsonConverter;
/*    */ import javax.annotation.Nonnull;
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
/*    */ public class GsonConverters
/*    */ {
/*    */   @Nonnull
/* 41 */   public static final GsonConverter IMMUTABLE = (GsonConverter)ImmutableGsonConverter.INSTANCE;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nonnull
/* 47 */   public static final GsonConverter MUTABLE = (GsonConverter)MutableGsonConverter.INSTANCE;
/*    */   
/*    */   private GsonConverters() {
/* 50 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\gson\converters\GsonConverters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */