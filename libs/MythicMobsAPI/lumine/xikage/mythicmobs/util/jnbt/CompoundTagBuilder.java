/*     */ package lumine.xikage.mythicmobs.util.jnbt;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteArrayTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.DoubleTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.FloatTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.IntArrayTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.IntTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.LongTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ShortTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.StringTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CompoundTagBuilder {
/*     */   CompoundTagBuilder() {
/*  20 */     this.entries = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map<String, Tag> entries;
/*     */ 
/*     */   
/*     */   CompoundTagBuilder(Map<String, Tag> value) {
/*  29 */     checkNotNull(value);
/*  30 */     this.entries = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder put(String key, Tag value) {
/*  41 */     checkNotNull(key);
/*  42 */     checkNotNull(value);
/*  43 */     this.entries.put(key, value);
/*  44 */     return this;
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
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putByteArray(String key, byte[] value) {
/*  56 */     return put(key, (Tag)new ByteArrayTag(value));
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
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putByte(String key, byte value) {
/*  68 */     return put(key, (Tag)new ByteTag(value));
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putBoolean(String key, boolean value) {
/*  72 */     return putByte(key, (byte)(value ? 1 : 0));
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
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putDouble(String key, double value) {
/*  84 */     return put(key, (Tag)new DoubleTag(value));
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
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putFloat(String key, float value) {
/*  96 */     return put(key, (Tag)new FloatTag(value));
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
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putIntArray(String key, int[] value) {
/* 108 */     return put(key, (Tag)new IntArrayTag(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putInt(String key, int value) {
/* 119 */     return put(key, (Tag)new IntTag(value));
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
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putLong(String key, long value) {
/* 131 */     return put(key, (Tag)new LongTag(value));
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
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putShort(String key, short value) {
/* 143 */     return put(key, (Tag)new ShortTag(value));
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
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putString(String key, String value) {
/* 155 */     return put(key, (Tag)new StringTag(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder putAll(Map<String, ? extends Tag> value) {
/* 165 */     checkNotNull(value);
/* 166 */     for (Map.Entry<String, ? extends Tag> entry : value.entrySet()) {
/* 167 */       put(entry.getKey(), entry.getValue());
/*     */     }
/* 169 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder remove(String key) {
/* 173 */     this.entries.remove(key);
/* 174 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag build() {
/*     */     try {
/* 184 */       return MythicMobs.inst().getVolatileCodeHandler().createCompoundTag(new HashMap<>(this.entries));
/* 185 */     } catch (Exception ex) {
/* 186 */       MythicLogger.error("This version of Minecraft is not supported for NBT/Data");
/* 187 */       return null;
/* 188 */     } catch (Error ex) {
/* 189 */       MythicLogger.error("This version of Minecraft is not supported for NBT/Data");
/* 190 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder create() {
/* 200 */     return new io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder();
/*     */   }
/*     */   
/*     */   private static void checkNotNull(Object object) {
/* 204 */     if (object == null)
/* 205 */       throw new NullPointerException(); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\jnbt\CompoundTagBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */