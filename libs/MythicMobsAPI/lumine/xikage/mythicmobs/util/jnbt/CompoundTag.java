/*     */ package lumine.xikage.mythicmobs.util.jnbt;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteArrayTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.DoubleTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.FloatTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.IntArrayTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.IntTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ListTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.LongTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ShortTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.StringTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class CompoundTag
/*     */   extends Tag {
/*     */   public CompoundTag(Map<String, Tag> value) {
/*  24 */     this.value = Collections.unmodifiableMap(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Map<String, Tag> value;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsKey(String key) {
/*  34 */     return this.value.containsKey(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Tag> getValue() {
/*  39 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag setValue(Map<String, Tag> value) {
/*  49 */     return MythicMobs.inst().getVolatileCodeHandler().createCompoundTag(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTagBuilder createBuilder() {
/*  58 */     return new CompoundTagBuilder(new HashMap<>(this.value));
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
/*     */   public byte[] getByteArray(String key) {
/*  71 */     Tag tag = this.value.get(key);
/*  72 */     if (tag instanceof ByteArrayTag) {
/*  73 */       return ((ByteArrayTag)tag).getValue();
/*     */     }
/*     */     
/*  76 */     return new byte[0];
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
/*     */   public byte getByte(String key) {
/*  90 */     Tag tag = this.value.get(key);
/*  91 */     if (tag instanceof ByteTag) {
/*  92 */       return ((ByteTag)tag).getValue().byteValue();
/*     */     }
/*     */     
/*  95 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String key) {
/* 100 */     return (getByte(key) != 0);
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
/*     */   public double getDouble(String key) {
/* 113 */     Tag tag = this.value.get(key);
/* 114 */     if (tag instanceof DoubleTag) {
/* 115 */       return ((DoubleTag)tag).getValue().doubleValue();
/*     */     }
/*     */     
/* 118 */     return 0.0D;
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
/*     */   public double asDouble(String key) {
/* 133 */     Tag tag = this.value.get(key);
/* 134 */     if (tag instanceof ByteTag) {
/* 135 */       return ((ByteTag)tag).getValue().byteValue();
/*     */     }
/*     */     
/* 138 */     if (tag instanceof ShortTag) {
/* 139 */       return ((ShortTag)tag).getValue().shortValue();
/*     */     }
/*     */     
/* 142 */     if (tag instanceof IntTag) {
/* 143 */       return ((IntTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 146 */     if (tag instanceof LongTag) {
/* 147 */       return ((LongTag)tag).getValue().longValue();
/*     */     }
/*     */     
/* 150 */     if (tag instanceof FloatTag) {
/* 151 */       return ((FloatTag)tag).getValue().floatValue();
/*     */     }
/*     */     
/* 154 */     if (tag instanceof DoubleTag) {
/* 155 */       return ((DoubleTag)tag).getValue().doubleValue();
/*     */     }
/*     */ 
/*     */     
/* 159 */     return 0.0D;
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
/*     */   public float getFloat(String key) {
/* 173 */     Tag tag = this.value.get(key);
/* 174 */     if (tag instanceof FloatTag) {
/* 175 */       return ((FloatTag)tag).getValue().floatValue();
/*     */     }
/*     */     
/* 178 */     return 0.0F;
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
/*     */   public int[] getIntArray(String key) {
/* 192 */     Tag tag = this.value.get(key);
/* 193 */     if (tag instanceof IntArrayTag) {
/* 194 */       return ((IntArrayTag)tag).getValue();
/*     */     }
/*     */     
/* 197 */     return new int[0];
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
/*     */   public int getInt(String key) {
/* 211 */     Tag tag = this.value.get(key);
/* 212 */     if (tag instanceof IntTag) {
/* 213 */       return ((IntTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 216 */     return 0;
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
/*     */   public int asInt(String key) {
/* 231 */     Tag tag = this.value.get(key);
/* 232 */     if (tag instanceof ByteTag) {
/* 233 */       return ((ByteTag)tag).getValue().byteValue();
/*     */     }
/*     */     
/* 236 */     if (tag instanceof ShortTag) {
/* 237 */       return ((ShortTag)tag).getValue().shortValue();
/*     */     }
/*     */     
/* 240 */     if (tag instanceof IntTag) {
/* 241 */       return ((IntTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 244 */     if (tag instanceof LongTag) {
/* 245 */       return ((LongTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 248 */     if (tag instanceof FloatTag) {
/* 249 */       return ((FloatTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 252 */     if (tag instanceof DoubleTag) {
/* 253 */       return ((DoubleTag)tag).getValue().intValue();
/*     */     }
/*     */ 
/*     */     
/* 257 */     return 0;
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
/*     */   public List<Tag> getList(String key) {
/* 271 */     Tag tag = this.value.get(key);
/* 272 */     if (tag instanceof ListTag) {
/* 273 */       return ((ListTag)tag).getValue();
/*     */     }
/*     */     
/* 276 */     return Collections.emptyList();
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
/*     */   public ListTag getListTag(String key) {
/* 290 */     Tag tag = this.value.get(key);
/* 291 */     if (tag instanceof ListTag) {
/* 292 */       return (ListTag)tag;
/*     */     }
/*     */     
/* 295 */     return new ListTag(StringTag.class, Collections.emptyList());
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
/*     */   public <T extends Tag> List<T> getList(String key, Class<T> listType) {
/* 314 */     Tag tag = this.value.get(key);
/* 315 */     if (tag instanceof ListTag) {
/* 316 */       ListTag listTag = (ListTag)tag;
/* 317 */       if (listTag.getType().equals(listType)) {
/* 318 */         return listTag.getValue();
/*     */       }
/*     */       
/* 321 */       return Collections.emptyList();
/*     */     } 
/*     */ 
/*     */     
/* 325 */     return Collections.emptyList();
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
/*     */   public long getLong(String key) {
/* 339 */     Tag tag = this.value.get(key);
/* 340 */     if (tag instanceof LongTag) {
/* 341 */       return ((LongTag)tag).getValue().longValue();
/*     */     }
/*     */     
/* 344 */     return 0L;
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
/*     */   public long asLong(String key) {
/* 359 */     Tag tag = this.value.get(key);
/* 360 */     if (tag instanceof ByteTag) {
/* 361 */       return ((ByteTag)tag).getValue().byteValue();
/*     */     }
/*     */     
/* 364 */     if (tag instanceof ShortTag) {
/* 365 */       return ((ShortTag)tag).getValue().shortValue();
/*     */     }
/*     */     
/* 368 */     if (tag instanceof IntTag) {
/* 369 */       return ((IntTag)tag).getValue().intValue();
/*     */     }
/*     */     
/* 372 */     if (tag instanceof LongTag) {
/* 373 */       return ((LongTag)tag).getValue().longValue();
/*     */     }
/*     */     
/* 376 */     if (tag instanceof FloatTag) {
/* 377 */       return ((FloatTag)tag).getValue().longValue();
/*     */     }
/*     */     
/* 380 */     if (tag instanceof DoubleTag) {
/* 381 */       return ((DoubleTag)tag).getValue().longValue();
/*     */     }
/*     */ 
/*     */     
/* 385 */     return 0L;
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
/*     */   public short getShort(String key) {
/* 399 */     Tag tag = this.value.get(key);
/* 400 */     if (tag instanceof ShortTag) {
/* 401 */       return ((ShortTag)tag).getValue().shortValue();
/*     */     }
/*     */     
/* 404 */     return 0;
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
/*     */   public String getString(String key) {
/* 418 */     Tag tag = this.value.get(key);
/* 419 */     if (tag instanceof StringTag) {
/* 420 */       return ((StringTag)tag).getValue();
/*     */     }
/*     */     
/* 423 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 429 */     StringBuilder bldr = new StringBuilder();
/* 430 */     bldr.append("TAG_Compound").append(": ").append(this.value.size()).append(" entries\r\n{\r\n");
/* 431 */     for (Map.Entry<String, Tag> entry : this.value.entrySet()) {
/* 432 */       bldr.append("   ").append(((Tag)entry.getValue()).toString().replaceAll("\r\n", "\r\n   ")).append("\r\n");
/*     */     }
/* 434 */     bldr.append("}");
/* 435 */     return bldr.toString();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\CompoundTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */