/*     */ package lumine.xikage.mythicmobs.util.jnbt;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteArrayTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.DoubleTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.EndTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.FloatTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.IntArrayTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.IntTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ListTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.LongTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ShortTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.StringTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
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
/*     */ public final class NBTUtils
/*     */ {
/*     */   public static String getTypeName(Class<? extends Tag> clazz) {
/*  40 */     if (clazz.equals(ByteArrayTag.class)) {
/*  41 */       return "TAG_Byte_Array";
/*     */     }
/*  43 */     if (clazz.equals(ByteTag.class)) {
/*  44 */       return "TAG_Byte";
/*     */     }
/*  46 */     if (CompoundTag.class.isAssignableFrom(clazz)) {
/*  47 */       return "TAG_Compound";
/*     */     }
/*  49 */     if (clazz.equals(DoubleTag.class)) {
/*  50 */       return "TAG_Double";
/*     */     }
/*  52 */     if (clazz.equals(EndTag.class)) {
/*  53 */       return "TAG_End";
/*     */     }
/*  55 */     if (clazz.equals(FloatTag.class)) {
/*  56 */       return "TAG_Float";
/*     */     }
/*  58 */     if (clazz.equals(IntTag.class)) {
/*  59 */       return "TAG_Int";
/*     */     }
/*  61 */     if (clazz.equals(ListTag.class)) {
/*  62 */       return "TAG_List";
/*     */     }
/*  64 */     if (clazz.equals(LongTag.class)) {
/*  65 */       return "TAG_Long";
/*     */     }
/*  67 */     if (clazz.equals(ShortTag.class)) {
/*  68 */       return "TAG_Short";
/*     */     }
/*  70 */     if (clazz.equals(StringTag.class)) {
/*  71 */       return "TAG_String";
/*     */     }
/*  73 */     if (clazz.equals(IntArrayTag.class)) {
/*  74 */       return "TAG_Int_Array";
/*     */     }
/*     */     
/*  77 */     throw new IllegalArgumentException("Invalid tag class (" + clazz
/*  78 */         .getName() + ").");
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
/*     */   public static int getTypeCode(Class<? extends Tag> clazz) {
/*  90 */     if (clazz.equals(ByteArrayTag.class)) {
/*  91 */       return 7;
/*     */     }
/*  93 */     if (clazz.equals(ByteTag.class)) {
/*  94 */       return 1;
/*     */     }
/*  96 */     if (CompoundTag.class.isAssignableFrom(clazz)) {
/*  97 */       return 10;
/*     */     }
/*  99 */     if (clazz.equals(DoubleTag.class)) {
/* 100 */       return 6;
/*     */     }
/* 102 */     if (clazz.equals(EndTag.class)) {
/* 103 */       return 0;
/*     */     }
/* 105 */     if (clazz.equals(FloatTag.class)) {
/* 106 */       return 5;
/*     */     }
/* 108 */     if (clazz.equals(IntTag.class)) {
/* 109 */       return 3;
/*     */     }
/* 111 */     if (clazz.equals(ListTag.class)) {
/* 112 */       return 9;
/*     */     }
/* 114 */     if (clazz.equals(LongTag.class)) {
/* 115 */       return 4;
/*     */     }
/* 117 */     if (clazz.equals(ShortTag.class)) {
/* 118 */       return 2;
/*     */     }
/* 120 */     if (clazz.equals(StringTag.class)) {
/* 121 */       return 8;
/*     */     }
/* 123 */     if (clazz.equals(IntArrayTag.class)) {
/* 124 */       return 11;
/*     */     }
/*     */     
/* 127 */     throw new IllegalArgumentException("Invalid tag class (" + clazz
/* 128 */         .getName() + ").");
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
/*     */   public static Class<? extends Tag> getTypeClass(int type) {
/* 140 */     switch (type) {
/*     */       case 0:
/* 142 */         return (Class)EndTag.class;
/*     */       case 1:
/* 144 */         return (Class)ByteTag.class;
/*     */       case 2:
/* 146 */         return (Class)ShortTag.class;
/*     */       case 3:
/* 148 */         return (Class)IntTag.class;
/*     */       case 4:
/* 150 */         return (Class)LongTag.class;
/*     */       case 5:
/* 152 */         return (Class)FloatTag.class;
/*     */       case 6:
/* 154 */         return (Class)DoubleTag.class;
/*     */       case 7:
/* 156 */         return (Class)ByteArrayTag.class;
/*     */       case 8:
/* 158 */         return (Class)StringTag.class;
/*     */       case 9:
/* 160 */         return (Class)ListTag.class;
/*     */       case 10:
/* 162 */         return (Class)CompoundTag.class;
/*     */       case 11:
/* 164 */         return (Class)IntArrayTag.class;
/*     */     } 
/* 166 */     throw new IllegalArgumentException("Invalid tag type : " + type + ".");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\NBTUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */