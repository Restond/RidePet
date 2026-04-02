/*     */ package lumine.xikage.mythicmobs.util.jnbt;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteArrayTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.DoubleTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.FloatTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.IntArrayTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.IntTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.LongTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.NBTUtils;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ShortTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.StringTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public final class ListTag
/*     */   extends Tag
/*     */ {
/*     */   private final Class<? extends Tag> type;
/*     */   private final List<Tag> value;
/*     */   
/*     */   public ListTag(Class<? extends Tag> type, List<? extends Tag> value) {
/*  42 */     checkNotNull(value);
/*  43 */     this.type = type;
/*  44 */     this.value = Collections.unmodifiableList(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<? extends Tag> getType() {
/*  53 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Tag> getValue() {
/*  58 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.ListTag setValue(List<Tag> list) {
/*  68 */     return new io.lumine.xikage.mythicmobs.util.jnbt.ListTag(getType(), list);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tag getIfExists(int index) {
/*     */     try {
/*  79 */       return this.value.get(index);
/*     */     }
/*  81 */     catch (NoSuchElementException e) {
/*  82 */       return null;
/*     */     } 
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
/*     */   public byte[] getByteArray(int index) {
/*  96 */     Tag tag = getIfExists(index);
/*  97 */     if (tag instanceof ByteArrayTag) {
/*  98 */       return ((ByteArrayTag)tag).getValue();
/*     */     }
/*     */     
/* 101 */     return new byte[0];
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
/*     */   
/*     */   public byte getByte(int index) {
/* 115 */     Tag tag = getIfExists(index);
/* 116 */     if (tag instanceof ByteTag) {
/* 117 */       return ((ByteTag)tag).getValue().byteValue();
/*     */     }
/*     */     
/* 120 */     return 0;
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
/*     */   
/*     */   public double getDouble(int index) {
/* 134 */     Tag tag = getIfExists(index);
/* 135 */     if (tag instanceof DoubleTag) {
/* 136 */       return ((DoubleTag)tag).getValue().doubleValue();
/*     */     }
/*     */     
/* 139 */     return 0.0D;
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
/*     */ 
/*     */   
/*     */   public double asDouble(int index) {
/* 154 */     Tag tag = getIfExists(index);
/* 155 */     if (tag instanceof ByteTag) {
/* 156 */       return ((ByteTag)tag).getValue().byteValue();
/*     */     }
/*     */     
/* 159 */     if (tag instanceof ShortTag) {
/* 160 */       return ((ShortTag)tag).getValue().shortValue();
/*     */     }
/*     */     
/* 163 */     if (tag instanceof IntTag) {
/* 164 */       return ((IntTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 167 */     if (tag instanceof LongTag) {
/* 168 */       return ((LongTag)tag).getValue().longValue();
/*     */     }
/*     */     
/* 171 */     if (tag instanceof FloatTag) {
/* 172 */       return ((FloatTag)tag).getValue().floatValue();
/*     */     }
/*     */     
/* 175 */     if (tag instanceof DoubleTag) {
/* 176 */       return ((DoubleTag)tag).getValue().doubleValue();
/*     */     }
/*     */ 
/*     */     
/* 180 */     return 0.0D;
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
/*     */   
/*     */   public float getFloat(int index) {
/* 194 */     Tag tag = getIfExists(index);
/* 195 */     if (tag instanceof FloatTag) {
/* 196 */       return ((FloatTag)tag).getValue().floatValue();
/*     */     }
/*     */     
/* 199 */     return 0.0F;
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
/*     */   
/*     */   public int[] getIntArray(int index) {
/* 213 */     Tag tag = getIfExists(index);
/* 214 */     if (tag instanceof IntArrayTag) {
/* 215 */       return ((IntArrayTag)tag).getValue();
/*     */     }
/*     */     
/* 218 */     return new int[0];
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
/*     */   
/*     */   public int getInt(int index) {
/* 232 */     Tag tag = getIfExists(index);
/* 233 */     if (tag instanceof IntTag) {
/* 234 */       return ((IntTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 237 */     return 0;
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
/*     */ 
/*     */   
/*     */   public int asInt(int index) {
/* 252 */     Tag tag = getIfExists(index);
/* 253 */     if (tag instanceof ByteTag) {
/* 254 */       return ((ByteTag)tag).getValue().byteValue();
/*     */     }
/*     */     
/* 257 */     if (tag instanceof ShortTag) {
/* 258 */       return ((ShortTag)tag).getValue().shortValue();
/*     */     }
/*     */     
/* 261 */     if (tag instanceof IntTag) {
/* 262 */       return ((IntTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 265 */     if (tag instanceof LongTag) {
/* 266 */       return ((LongTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 269 */     if (tag instanceof FloatTag) {
/* 270 */       return ((FloatTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 273 */     if (tag instanceof DoubleTag) {
/* 274 */       return ((DoubleTag)tag).getValue().intValue();
/*     */     }
/*     */ 
/*     */     
/* 278 */     return 0;
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
/*     */   
/*     */   public List<Tag> getList(int index) {
/* 292 */     Tag tag = getIfExists(index);
/* 293 */     if (tag instanceof io.lumine.xikage.mythicmobs.util.jnbt.ListTag) {
/* 294 */       return ((io.lumine.xikage.mythicmobs.util.jnbt.ListTag)tag).getValue();
/*     */     }
/*     */     
/* 297 */     return Collections.emptyList();
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
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.ListTag getListTag(int index) {
/* 311 */     Tag tag = getIfExists(index);
/* 312 */     if (tag instanceof io.lumine.xikage.mythicmobs.util.jnbt.ListTag) {
/* 313 */       return (io.lumine.xikage.mythicmobs.util.jnbt.ListTag)tag;
/*     */     }
/*     */     
/* 316 */     return new io.lumine.xikage.mythicmobs.util.jnbt.ListTag((Class)StringTag.class, Collections.emptyList());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends Tag> List<T> getList(int index, Class<T> listType) {
/* 335 */     Tag tag = getIfExists(index);
/* 336 */     if (tag instanceof io.lumine.xikage.mythicmobs.util.jnbt.ListTag) {
/* 337 */       io.lumine.xikage.mythicmobs.util.jnbt.ListTag listTag = (io.lumine.xikage.mythicmobs.util.jnbt.ListTag)tag;
/* 338 */       if (listTag.getType().equals(listType)) {
/* 339 */         return (List)listTag.getValue();
/*     */       }
/*     */       
/* 342 */       return Collections.emptyList();
/*     */     } 
/*     */ 
/*     */     
/* 346 */     return Collections.emptyList();
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
/*     */   
/*     */   public long getLong(int index) {
/* 360 */     Tag tag = getIfExists(index);
/* 361 */     if (tag instanceof LongTag) {
/* 362 */       return ((LongTag)tag).getValue().longValue();
/*     */     }
/*     */     
/* 365 */     return 0L;
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
/*     */ 
/*     */   
/*     */   public long asLong(int index) {
/* 380 */     Tag tag = getIfExists(index);
/* 381 */     if (tag instanceof ByteTag) {
/* 382 */       return ((ByteTag)tag).getValue().byteValue();
/*     */     }
/*     */     
/* 385 */     if (tag instanceof ShortTag) {
/* 386 */       return ((ShortTag)tag).getValue().shortValue();
/*     */     }
/*     */     
/* 389 */     if (tag instanceof IntTag) {
/* 390 */       return ((IntTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 393 */     if (tag instanceof LongTag) {
/* 394 */       return ((LongTag)tag).getValue().longValue();
/*     */     }
/*     */     
/* 397 */     if (tag instanceof FloatTag) {
/* 398 */       return ((FloatTag)tag).getValue().longValue();
/*     */     }
/*     */     
/* 401 */     if (tag instanceof DoubleTag) {
/* 402 */       return ((DoubleTag)tag).getValue().longValue();
/*     */     }
/*     */ 
/*     */     
/* 406 */     return 0L;
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
/*     */   
/*     */   public short getShort(int index) {
/* 420 */     Tag tag = getIfExists(index);
/* 421 */     if (tag instanceof ShortTag) {
/* 422 */       return ((ShortTag)tag).getValue().shortValue();
/*     */     }
/*     */     
/* 425 */     return 0;
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
/*     */   
/*     */   public String getString(int index) {
/* 439 */     Tag tag = getIfExists(index);
/* 440 */     if (tag instanceof StringTag) {
/* 441 */       return ((StringTag)tag).getValue();
/*     */     }
/*     */     
/* 444 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 450 */     StringBuilder bldr = new StringBuilder();
/* 451 */     bldr.append("TAG_List").append(": ").append(this.value.size()).append(" entries of type ").append(NBTUtils.getTypeName(this.type)).append("\r\n{\r\n");
/* 452 */     for (Tag t : this.value) {
/* 453 */       bldr.append("   ").append(t.toString().replaceAll("\r\n", "\r\n   ")).append("\r\n");
/*     */     }
/* 455 */     bldr.append("}");
/* 456 */     return bldr.toString();
/*     */   }
/*     */   
/*     */   private static void checkNotNull(Object object) {
/* 460 */     if (object == null)
/* 461 */       throw new NullPointerException(); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\ListTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */