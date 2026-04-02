/*    */ package lumine.xikage.mythicmobs.util.jnbt;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteArrayTag;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteTag;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.DoubleTag;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.EndTag;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.ShortTag;
/*    */ import java.nio.charset.Charset;
/*    */ 
/*    */ public final class NBTConstants {
/* 10 */   public static final Charset CHARSET = Charset.forName("UTF-8");
/*    */   
/*    */   public static final int TYPE_END = 0;
/*    */   
/*    */   public static final int TYPE_BYTE = 1;
/*    */   
/*    */   public static final int TYPE_SHORT = 2;
/*    */   
/*    */   public static final int TYPE_INT = 3;
/*    */   
/*    */   public static final int TYPE_LONG = 4;
/*    */   
/*    */   public static final int TYPE_FLOAT = 5;
/*    */   
/*    */   public static final int TYPE_DOUBLE = 6;
/*    */   public static final int TYPE_BYTE_ARRAY = 7;
/*    */   public static final int TYPE_STRING = 8;
/*    */   public static final int TYPE_LIST = 9;
/*    */   public static final int TYPE_COMPOUND = 10;
/*    */   public static final int TYPE_INT_ARRAY = 11;
/*    */   
/*    */   public static Class<? extends Tag> getClassFromType(int id) {
/* 32 */     switch (id) {
/*    */       case 0:
/* 34 */         return (Class)EndTag.class;
/*    */       case 1:
/* 36 */         return (Class)ByteTag.class;
/*    */       case 2:
/* 38 */         return (Class)ShortTag.class;
/*    */       case 3:
/* 40 */         return (Class)IntTag.class;
/*    */       case 4:
/* 42 */         return (Class)LongTag.class;
/*    */       case 5:
/* 44 */         return (Class)FloatTag.class;
/*    */       case 6:
/* 46 */         return (Class)DoubleTag.class;
/*    */       case 7:
/* 48 */         return (Class)ByteArrayTag.class;
/*    */       case 8:
/* 50 */         return (Class)StringTag.class;
/*    */       case 9:
/* 52 */         return (Class)ListTag.class;
/*    */       case 10:
/* 54 */         return (Class)CompoundTag.class;
/*    */       case 11:
/* 56 */         return (Class)IntArrayTag.class;
/*    */     } 
/* 58 */     throw new IllegalArgumentException("Unknown tag type ID of " + id);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\NBTConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */