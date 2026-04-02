/*     */ package lumine.xikage.mythicmobs.skills.variables;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.types.AbstractVariable;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.types.FloatVariable;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.types.IntegerVariable;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.types.StringVariable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VariableRegistry
/*     */ {
/*     */   private final Map<String, Variable> entries;
/*  19 */   private final transient ReentrantLock lock = new ReentrantLock();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VariableRegistry() {
/*  25 */     this.entries = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VariableRegistry(Map<String, Variable> value) {
/*  34 */     Preconditions.checkNotNull(value);
/*  35 */     this.entries = value;
/*  36 */     clean();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void put(String key, Variable value) {
/*  47 */     Preconditions.checkNotNull(key);
/*  48 */     Preconditions.checkNotNull(value);
/*     */     
/*  50 */     this.lock.lock();
/*     */     try {
/*  52 */       this.entries.put(key, value);
/*     */     } finally {
/*  54 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putAll(Map<String, ? extends Variable> value) {
/*  65 */     Preconditions.checkNotNull(value);
/*     */     
/*  67 */     this.lock.lock();
/*     */     try {
/*  69 */       for (Map.Entry<String, ? extends Variable> entry : value.entrySet()) {
/*  70 */         put(entry.getKey(), entry.getValue());
/*     */       }
/*     */     } finally {
/*  73 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(String key) {
/*  82 */     Preconditions.checkNotNull(key);
/*     */     
/*  84 */     this.lock.lock();
/*     */     try {
/*  86 */       this.entries.remove(key);
/*     */     } finally {
/*  88 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean has(String key) {
/*  93 */     Preconditions.checkNotNull(key);
/*     */     
/*  95 */     this.lock.lock();
/*     */     try {
/*  97 */       Variable var = this.entries.getOrDefault(key, null);
/*     */       
/*  99 */       if (var == null || var.shouldExpire()) {
/* 100 */         this.entries.remove(key);
/* 101 */         return false;
/*     */       } 
/* 103 */       return true;
/*     */     } finally {
/* 105 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Variable get(String key) {
/* 110 */     Preconditions.checkNotNull(key);
/*     */     
/* 112 */     this.lock.lock();
/*     */     try {
/* 114 */       Variable var = this.entries.getOrDefault(key, null);
/*     */       
/* 116 */       if (var == null || var.shouldExpire()) {
/* 117 */         this.entries.remove(key);
/* 118 */         return null;
/*     */       } 
/* 120 */       return var;
/*     */     } finally {
/* 122 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Variable getOrDefault(String key, Variable def) {
/* 127 */     Preconditions.checkNotNull(key);
/*     */     
/* 129 */     this.lock.lock();
/*     */     try {
/* 131 */       Variable var = this.entries.getOrDefault(key, null);
/*     */       
/* 133 */       if (var == null || var.shouldExpire()) {
/* 134 */         this.entries.remove(key);
/* 135 */         return def;
/*     */       } 
/* 137 */       return var;
/*     */     } finally {
/* 139 */       this.lock.unlock();
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
/*     */   public void putInt(String key, int value) {
/* 151 */     put(key, (Variable)new IntegerVariable(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(String key) {
/* 160 */     int def = 0;
/*     */     
/* 162 */     if (key.contains("|")) {
/* 163 */       String[] split = key.split("\\|");
/* 164 */       key = split[0];
/*     */       
/*     */       try {
/* 167 */         def = Integer.valueOf(split[1]).intValue();
/* 168 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*     */     } 
/*     */     
/* 171 */     Variable var = get(key);
/*     */     
/* 173 */     if (var == null) {
/* 174 */       return def;
/*     */     }
/*     */     
/* 177 */     if (var instanceof IntegerVariable) {
/* 178 */       return ((IntegerVariable)var).getValue();
/*     */     }
/* 180 */     if (var instanceof FloatVariable) {
/* 181 */       return (int)((FloatVariable)var).getValue();
/*     */     }
/* 183 */     if (var instanceof StringVariable) {
/*     */       try {
/* 185 */         String value = ((StringVariable)var).getValue();
/* 186 */         return Integer.valueOf(value).intValue();
/* 187 */       } catch (Exception exception) {}
/*     */     }
/*     */     
/* 190 */     return def;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putString(String key, String value) {
/* 201 */     put(key, (Variable)new StringVariable(value));
/*     */   }
/*     */   
/*     */   public String getString(String key) {
/* 205 */     String def = null;
/*     */     
/* 207 */     if (key.contains("|")) {
/* 208 */       String[] split = key.split("\\|");
/* 209 */       key = split[0];
/* 210 */       def = split[1];
/*     */     } 
/*     */     
/* 213 */     Variable var = get(key);
/*     */     
/* 215 */     if (var == null) {
/* 216 */       return def;
/*     */     }
/*     */     
/* 219 */     if (var instanceof StringVariable)
/* 220 */       return ((StringVariable)var).getValue(); 
/* 221 */     if (var instanceof IntegerVariable)
/* 222 */       return String.valueOf(((IntegerVariable)var).getValue()); 
/* 223 */     if (var instanceof FloatVariable) {
/* 224 */       return String.valueOf(((FloatVariable)var).getValue());
/*     */     }
/*     */     
/* 227 */     return var.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putFloat(String key, float value) {
/* 238 */     put(key, (Variable)new FloatVariable(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat(String key) {
/* 247 */     float def = 0.0F;
/*     */     
/* 249 */     if (key.contains("|")) {
/* 250 */       String[] split = key.split("\\|");
/* 251 */       key = split[0];
/*     */       
/*     */       try {
/* 254 */         def = Float.valueOf(split[1]).floatValue();
/* 255 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*     */     } 
/*     */     
/* 258 */     Variable var = get(key);
/*     */     
/* 260 */     if (var == null) {
/* 261 */       return def;
/*     */     }
/*     */     
/* 264 */     if (var instanceof IntegerVariable) {
/* 265 */       return ((IntegerVariable)var).getValue();
/*     */     }
/* 267 */     if (var instanceof FloatVariable) {
/* 268 */       return ((FloatVariable)var).getValue();
/*     */     }
/* 270 */     if (var instanceof StringVariable) {
/*     */       try {
/* 272 */         String value = ((StringVariable)var).getValue();
/* 273 */         return Float.valueOf(value).floatValue();
/* 274 */       } catch (Exception exception) {}
/*     */     }
/*     */     
/* 277 */     return def;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putObject(String key, Object value) {
/* 288 */     put(key, (Variable)new AbstractVariable(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImmutableMap<String, Variable> asMap() {
/* 296 */     this.lock.lock();
/*     */     try {
/* 298 */       return ImmutableMap.copyOf(this.entries);
/*     */     } finally {
/* 300 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clean() {
/* 305 */     this.lock.lock();
/*     */     try {
/* 307 */       this.entries.values().removeIf(o -> o.shouldExpire());
/*     */     } finally {
/* 309 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unload() {
/* 314 */     this.lock.lock();
/*     */     try {
/* 316 */       this.entries.values().removeIf(o -> !o.shouldSave());
/*     */     } finally {
/* 318 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\VariableRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */