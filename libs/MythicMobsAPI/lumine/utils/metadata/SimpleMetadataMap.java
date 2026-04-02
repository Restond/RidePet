/*     */ package lumine.utils.metadata;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import io.lumine.utils.metadata.MetadataKey;
/*     */ import io.lumine.utils.metadata.MetadataMap;
/*     */ import io.lumine.utils.metadata.TransientValue;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ final class SimpleMetadataMap implements MetadataMap {
/*  15 */   private final Map<MetadataKey<?>, Object> map = new HashMap<>();
/*  16 */   private final ReentrantLock lock = new ReentrantLock();
/*     */ 
/*     */   
/*     */   public <T> void put(MetadataKey<T> key, T value) {
/*  20 */     internalPut(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void put(MetadataKey<T> key, TransientValue<T> value) {
/*  25 */     internalPut(key, value);
/*     */   }
/*     */   
/*     */   private void internalPut(MetadataKey<?> key, Object value) {
/*  29 */     Preconditions.checkNotNull(key, "key");
/*  30 */     Preconditions.checkNotNull(value, "value");
/*     */     
/*  32 */     this.lock.lock();
/*     */     try {
/*  34 */       MetadataKey<?> existing = null;
/*  35 */       for (MetadataKey<?> k : this.map.keySet()) {
/*  36 */         if (k.equals(key)) {
/*  37 */           existing = k;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  42 */       if (existing != null && !existing.getType().equals(key.getType())) {
/*  43 */         throw new ClassCastException("Cannot cast key with id " + key.getId() + " with type " + key.getType().getRawType() + " to existing stored type " + existing.getType().getRawType());
/*     */       }
/*     */       
/*  46 */       this.map.put(key, value);
/*     */     } finally {
/*     */       
/*  49 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void forcePut(MetadataKey<T> key, T value) {
/*  55 */     internalForcePut(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void forcePut(MetadataKey<T> key, TransientValue<T> value) {
/*  60 */     internalForcePut(key, value);
/*     */   }
/*     */   
/*     */   private void internalForcePut(MetadataKey<?> key, Object value) {
/*  64 */     Preconditions.checkNotNull(key, "key");
/*  65 */     Preconditions.checkNotNull(value, "value");
/*     */     
/*  67 */     this.lock.lock();
/*     */     try {
/*  69 */       this.map.put(key, value);
/*     */     } finally {
/*  71 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> boolean putIfAbsent(MetadataKey<T> key, T value) {
/*  77 */     return internalPutIfAbsent(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> boolean putIfAbsent(MetadataKey<T> key, TransientValue<T> value) {
/*  82 */     return internalPutIfAbsent(key, value);
/*     */   }
/*     */   
/*     */   private boolean internalPutIfAbsent(MetadataKey<?> key, Object value) {
/*  86 */     Preconditions.checkNotNull(key, "key");
/*  87 */     Preconditions.checkNotNull(value, "value");
/*     */     
/*  89 */     this.lock.lock();
/*     */     try {
/*  91 */       doExpire();
/*  92 */       return (this.map.putIfAbsent(key, value) == null);
/*     */     } finally {
/*  94 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> Optional<T> get(MetadataKey<T> key) {
/* 100 */     Preconditions.checkNotNull(key, "key");
/*     */     
/* 102 */     this.lock.lock();
/*     */     try {
/* 104 */       Map.Entry<MetadataKey<?>, Object> existing = null;
/*     */ 
/*     */       
/* 107 */       Iterator<Map.Entry<MetadataKey<?>, Object>> it = this.map.entrySet().iterator();
/* 108 */       while (it.hasNext()) {
/* 109 */         Map.Entry<MetadataKey<?>, Object> kv = it.next();
/*     */         
/* 111 */         if (kv.getValue() instanceof TransientValue) {
/* 112 */           TransientValue<?> transientValue = (TransientValue)kv.getValue();
/*     */           
/* 114 */           Object unboxed = transientValue.getOrNull();
/*     */ 
/*     */           
/* 117 */           if (unboxed == null) {
/* 118 */             it.remove();
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 123 */           if (((MetadataKey)kv.getKey()).equals(key)) {
/* 124 */             existing = Maps.immutableEntry(kv.getKey(), unboxed);
/*     */             break;
/*     */           } 
/*     */           continue;
/*     */         } 
/* 129 */         if (((MetadataKey)kv.getKey()).equals(key)) {
/* 130 */           existing = kv;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 136 */       if (existing == null) {
/* 137 */         return (Optional)Optional.empty();
/*     */       }
/*     */       
/* 140 */       if (!((MetadataKey)existing.getKey()).getType().equals(key.getType())) {
/* 141 */         throw new ClassCastException("Cannot cast key with id " + key.getId() + " with type " + key.getType().getRawType() + " to existing stored type " + ((MetadataKey)existing.getKey()).getType().getRawType());
/*     */       }
/*     */       
/* 144 */       return Optional.of(key.cast(existing.getValue()));
/*     */     } finally {
/* 146 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T getOrNull(MetadataKey<T> key) {
/* 152 */     Preconditions.checkNotNull(key, "key");
/* 153 */     return get(key).orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T getOrDefault(MetadataKey<T> key, T def) {
/* 158 */     Preconditions.checkNotNull(key, "key");
/* 159 */     return get(key).orElse(def);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T getOrPut(MetadataKey<T> key, Supplier<T> def) {
/* 164 */     Preconditions.checkNotNull(key, "key");
/* 165 */     Preconditions.checkNotNull(def, "def");
/*     */     
/* 167 */     this.lock.lock();
/*     */     try {
/* 169 */       Map.Entry<MetadataKey<?>, Object> existing = null;
/*     */ 
/*     */       
/* 172 */       Iterator<Map.Entry<MetadataKey<?>, Object>> it = this.map.entrySet().iterator();
/* 173 */       while (it.hasNext()) {
/* 174 */         Map.Entry<MetadataKey<?>, Object> kv = it.next();
/*     */         
/* 176 */         if (kv.getValue() instanceof TransientValue) {
/* 177 */           TransientValue<?> transientValue = (TransientValue)kv.getValue();
/*     */           
/* 179 */           Object unboxed = transientValue.getOrNull();
/*     */ 
/*     */           
/* 182 */           if (unboxed == null) {
/* 183 */             it.remove();
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 188 */           if (((MetadataKey)kv.getKey()).equals(key)) {
/* 189 */             existing = Maps.immutableEntry(kv.getKey(), unboxed);
/*     */             break;
/*     */           } 
/*     */           continue;
/*     */         } 
/* 194 */         if (((MetadataKey)kv.getKey()).equals(key)) {
/* 195 */           existing = kv;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 201 */       if (existing == null) {
/* 202 */         T t = def.get();
/* 203 */         Preconditions.checkNotNull(t, "supplied def");
/*     */         
/* 205 */         this.map.put(key, t);
/* 206 */         return t;
/*     */       } 
/*     */       
/* 209 */       if (!((MetadataKey)existing.getKey()).getType().equals(key.getType())) {
/* 210 */         throw new ClassCastException("Cannot cast key with id " + key.getId() + " with type " + key.getType().getRawType() + " to existing stored type " + ((MetadataKey)existing.getKey()).getType().getRawType());
/*     */       }
/*     */       
/* 213 */       return (T)key.cast(existing.getValue());
/*     */     } finally {
/* 215 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T getOrPutExpiring(MetadataKey<T> key, Supplier<TransientValue<T>> def) {
/* 221 */     Preconditions.checkNotNull(key, "key");
/* 222 */     Preconditions.checkNotNull(def, "def");
/*     */     
/* 224 */     this.lock.lock();
/*     */     try {
/* 226 */       Map.Entry<MetadataKey<?>, Object> existing = null;
/*     */ 
/*     */       
/* 229 */       Iterator<Map.Entry<MetadataKey<?>, Object>> it = this.map.entrySet().iterator();
/* 230 */       while (it.hasNext()) {
/* 231 */         Map.Entry<MetadataKey<?>, Object> kv = it.next();
/*     */         
/* 233 */         if (kv.getValue() instanceof TransientValue) {
/* 234 */           TransientValue<?> transientValue = (TransientValue)kv.getValue();
/*     */           
/* 236 */           Object unboxed = transientValue.getOrNull();
/*     */ 
/*     */           
/* 239 */           if (unboxed == null) {
/* 240 */             it.remove();
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 245 */           if (((MetadataKey)kv.getKey()).equals(key)) {
/* 246 */             existing = Maps.immutableEntry(kv.getKey(), unboxed);
/*     */             break;
/*     */           } 
/*     */           continue;
/*     */         } 
/* 251 */         if (((MetadataKey)kv.getKey()).equals(key)) {
/* 252 */           existing = kv;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 258 */       if (existing == null) {
/* 259 */         TransientValue<T> t = def.get();
/* 260 */         Preconditions.checkNotNull(t, "supplied def");
/*     */         
/* 262 */         T value = (T)t.getOrNull();
/* 263 */         if (value == null) {
/* 264 */           throw new IllegalArgumentException("Transient value already expired: " + t);
/*     */         }
/*     */         
/* 267 */         this.map.put(key, t);
/* 268 */         return value;
/*     */       } 
/*     */       
/* 271 */       if (!((MetadataKey)existing.getKey()).getType().equals(key.getType())) {
/* 272 */         throw new ClassCastException("Cannot cast key with id " + key.getId() + " with type " + key.getType().getRawType() + " to existing stored type " + ((MetadataKey)existing.getKey()).getType().getRawType());
/*     */       }
/*     */       
/* 275 */       return (T)key.cast(existing.getValue());
/*     */     } finally {
/* 277 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean has(MetadataKey<?> key) {
/* 283 */     Preconditions.checkNotNull(key, "key");
/*     */     
/* 285 */     this.lock.lock();
/*     */     try {
/* 287 */       Map.Entry<MetadataKey<?>, Object> existing = null;
/*     */ 
/*     */       
/* 290 */       Iterator<Map.Entry<MetadataKey<?>, Object>> it = this.map.entrySet().iterator();
/* 291 */       while (it.hasNext()) {
/* 292 */         Map.Entry<MetadataKey<?>, Object> kv = it.next();
/*     */         
/* 294 */         if (kv.getValue() instanceof TransientValue) {
/* 295 */           TransientValue<?> transientValue = (TransientValue)kv.getValue();
/*     */           
/* 297 */           if (transientValue.shouldExpire()) {
/* 298 */             it.remove();
/*     */             
/*     */             continue;
/*     */           } 
/*     */         } 
/* 303 */         if (((MetadataKey)kv.getKey()).equals(key)) {
/* 304 */           existing = kv;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 309 */       return (existing != null && ((MetadataKey)existing.getKey()).getType().equals(key.getType()));
/*     */     } finally {
/* 311 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(MetadataKey<?> key) {
/* 317 */     Preconditions.checkNotNull(key, "key");
/*     */     
/* 319 */     this.lock.lock();
/*     */     try {
/* 321 */       return (this.map.remove(key) != null);
/*     */     } finally {
/* 323 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 329 */     this.lock.lock();
/*     */     try {
/* 331 */       this.map.clear();
/*     */     } finally {
/* 333 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableMap<MetadataKey<?>, Object> asMap() {
/* 339 */     this.lock.lock();
/*     */     try {
/* 341 */       return ImmutableMap.copyOf(this.map);
/*     */     } finally {
/* 343 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 349 */     this.lock.lock();
/*     */     try {
/* 351 */       doExpire();
/* 352 */       return this.map.isEmpty();
/*     */     } finally {
/* 354 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doExpire() {
/* 359 */     this.lock.lock();
/*     */     try {
/* 361 */       this.map.values().removeIf(o -> (o instanceof TransientValue && ((TransientValue)o).shouldExpire()));
/*     */     } finally {
/* 363 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\metadata\SimpleMetadataMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */