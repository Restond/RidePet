/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_12_R1;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteArrayTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ByteTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.DoubleTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.FloatTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.IntArrayTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ListTagBuilder;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.LongTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.ShortTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_12_R1.NBTBase;
/*     */ import net.minecraft.server.v1_12_R1.NBTTagCompound;
/*     */ import net.minecraft.server.v1_12_R1.NBTTagList;
/*     */ 
/*     */ public class CompoundTag_v1_12_R1 extends CompoundTag {
/*     */   public CompoundTag_v1_12_R1(Map<String, Tag> value) {
/*  20 */     super(value);
/*     */   }
/*     */   
/*     */   public NBTTagCompound toNMSTag() {
/*  24 */     NBTTagCompound tag = new NBTTagCompound();
/*  25 */     for (Map.Entry<String, Tag> entry : (Iterable<Map.Entry<String, Tag>>)this.value.entrySet()) {
/*  26 */       if (entry.getValue() instanceof IntTag) {
/*  27 */         tag.setInt(entry.getKey(), ((IntTag)entry.getValue()).getValue().intValue()); continue;
/*     */       } 
/*  29 */       if (entry.getValue() instanceof ByteTag) {
/*  30 */         tag.setByte(entry.getKey(), ((ByteTag)entry.getValue()).getValue().byteValue()); continue;
/*     */       } 
/*  32 */       if (entry.getValue() instanceof ByteArrayTag) {
/*  33 */         tag.setByteArray(entry.getKey(), ((ByteArrayTag)entry.getValue()).getValue()); continue;
/*     */       } 
/*  35 */       if (entry.getValue() instanceof CompoundTag) {
/*  36 */         tag.set(entry.getKey(), (NBTBase)((io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.CompoundTag_v1_12_R1)entry.getValue()).toNMSTag()); continue;
/*     */       } 
/*  38 */       if (entry.getValue() instanceof DoubleTag) {
/*  39 */         tag.setDouble(entry.getKey(), ((DoubleTag)entry.getValue()).getValue().doubleValue()); continue;
/*     */       } 
/*  41 */       if (entry.getValue() instanceof FloatTag) {
/*  42 */         tag.setFloat(entry.getKey(), ((FloatTag)entry.getValue()).getValue().floatValue()); continue;
/*     */       } 
/*  44 */       if (entry.getValue() instanceof IntArrayTag) {
/*  45 */         tag.setIntArray(entry.getKey(), ((IntArrayTag)entry.getValue()).getValue()); continue;
/*     */       } 
/*  47 */       if (entry.getValue() instanceof ListTag) {
/*  48 */         NBTTagList list = new NBTTagList();
/*  49 */         List<Tag> tags = ((ListTag)entry.getValue()).getValue();
/*  50 */         for (Tag btag : tags) {
/*  51 */           HashMap<String, Tag> btags = new HashMap<>();
/*  52 */           btags.put("test", btag);
/*  53 */           io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.CompoundTag_v1_12_R1 comp = new io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.CompoundTag_v1_12_R1(btags);
/*  54 */           list.add(comp.toNMSTag().get("test"));
/*     */         } 
/*  56 */         tag.set(entry.getKey(), (NBTBase)list); continue;
/*     */       } 
/*  58 */       if (entry.getValue() instanceof LongTag) {
/*  59 */         tag.setLong(entry.getKey(), ((LongTag)entry.getValue()).getValue().longValue()); continue;
/*     */       } 
/*  61 */       if (entry.getValue() instanceof ShortTag) {
/*  62 */         tag.setShort(entry.getKey(), ((ShortTag)entry.getValue()).getValue().shortValue()); continue;
/*     */       } 
/*  64 */       if (entry.getValue() instanceof StringTag) {
/*  65 */         tag.setString(entry.getKey(), ((StringTag)entry.getValue()).getValue());
/*     */       }
/*     */     } 
/*  68 */     return tag;
/*     */   }
/*     */   
/*     */   public static CompoundTag fromNMSTag(NBTTagCompound tag) {
/*  72 */     HashMap<String, Tag> tags = new HashMap<>();
/*  73 */     for (String key : tag.c()) {
/*  74 */       NBTBase base = tag.get(key);
/*  75 */       if (base instanceof NBTTagInt) {
/*  76 */         tags.put(key, new IntTag(((NBTTagInt)base).e())); continue;
/*     */       } 
/*  78 */       if (base instanceof NBTTagByte) {
/*  79 */         tags.put(key, new ByteTag(((NBTTagByte)base).g())); continue;
/*     */       } 
/*  81 */       if (base instanceof NBTTagFloat) {
/*  82 */         tags.put(key, new FloatTag(((NBTTagFloat)base).i())); continue;
/*     */       } 
/*  84 */       if (base instanceof NBTTagDouble) {
/*  85 */         tags.put(key, new DoubleTag(((NBTTagDouble)base).asDouble())); continue;
/*     */       } 
/*  87 */       if (base instanceof NBTTagByteArray) {
/*  88 */         tags.put(key, new ByteArrayTag(((NBTTagByteArray)base).c())); continue;
/*     */       } 
/*  90 */       if (base instanceof NBTTagIntArray) {
/*  91 */         tags.put(key, new IntArrayTag(((NBTTagIntArray)base).d())); continue;
/*     */       } 
/*  93 */       if (base instanceof NBTTagCompound) {
/*  94 */         tags.put(key, fromNMSTag((NBTTagCompound)base)); continue;
/*     */       } 
/*  96 */       if (base instanceof net.minecraft.server.v1_12_R1.NBTTagEnd) {
/*  97 */         tags.put(key, new EndTag()); continue;
/*     */       } 
/*  99 */       if (base instanceof NBTTagLong) {
/* 100 */         tags.put(key, new LongTag(((NBTTagLong)base).d())); continue;
/*     */       } 
/* 102 */       if (base instanceof NBTTagShort) {
/* 103 */         tags.put(key, new ShortTag(((NBTTagShort)base).f())); continue;
/*     */       } 
/* 105 */       if (base instanceof NBTTagString) {
/* 106 */         tags.put(key, new StringTag(((NBTTagString)base).c_())); continue;
/*     */       } 
/* 108 */       if (base instanceof NBTTagList) {
/* 109 */         NBTTagList list = (NBTTagList)base;
/* 110 */         if (list.size() > 0) {
/* 111 */           NBTTagCompound nBTTagCompound1 = list.get(0);
/* 112 */           NBTTagCompound comp = new NBTTagCompound();
/* 113 */           comp.set("test", (NBTBase)nBTTagCompound1);
/* 114 */           ListTagBuilder ltb = new ListTagBuilder(((Tag)fromNMSTag(comp).getValue().get("test")).getClass());
/* 115 */           for (int i = 0; i < list.size(); i++) {
/* 116 */             NBTTagCompound nBTTagCompound2 = list.get(i);
/* 117 */             NBTTagCompound comp2 = new NBTTagCompound();
/* 118 */             comp2.set("test", (NBTBase)nBTTagCompound2);
/* 119 */             ltb.add((Tag)fromNMSTag(comp2).getValue().get("test"));
/*     */           } 
/* 121 */           tags.put(key, ltb.build());
/*     */         } 
/*     */       } 
/*     */     } 
/* 125 */     return new io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.CompoundTag_v1_12_R1(tags);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_12_R1\CompoundTag_v1_12_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */