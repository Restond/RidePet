/*     */ package lumine.utils.config;
/*     */ 
/*     */ import io.lumine.utils.config.Configuration;
/*     */ import io.lumine.utils.config.ConfigurationSection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.NumberConversions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MemorySection
/*     */   implements ConfigurationSection
/*     */ {
/*  22 */   protected final Map<String, Object> map = new LinkedHashMap<>();
/*     */ 
/*     */   
/*     */   private final Configuration root;
/*     */ 
/*     */   
/*     */   private final ConfigurationSection parent;
/*     */ 
/*     */   
/*     */   private final String path;
/*     */ 
/*     */   
/*     */   private final String fullPath;
/*     */ 
/*     */ 
/*     */   
/*     */   protected MemorySection() {
/*  39 */     if (!(this instanceof Configuration)) {
/*  40 */       throw new IllegalStateException("Cannot construct a root MemorySection when not a Configuration");
/*     */     }
/*     */     
/*  43 */     this.path = "";
/*  44 */     this.fullPath = "";
/*  45 */     this.parent = null;
/*  46 */     this.root = (Configuration)this;
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
/*     */   protected MemorySection(ConfigurationSection parent, String path) {
/*  59 */     Validate.notNull(parent, "Parent cannot be null");
/*  60 */     Validate.notNull(path, "Path cannot be null");
/*     */     
/*  62 */     this.path = path;
/*  63 */     this.parent = parent;
/*  64 */     this.root = parent.getRoot();
/*     */     
/*  66 */     Validate.notNull(this.root, "Path cannot be orphaned");
/*     */     
/*  68 */     this.fullPath = createPath(parent, path);
/*     */   }
/*     */   
/*     */   public Set<String> getKeys(boolean deep) {
/*  72 */     Set<String> result = new LinkedHashSet<>();
/*     */     
/*  74 */     Configuration root = getRoot();
/*  75 */     if (root != null && root.options().copyDefaults()) {
/*  76 */       ConfigurationSection defaults = getDefaultSection();
/*     */       
/*  78 */       if (defaults != null) {
/*  79 */         result.addAll(defaults.getKeys(deep));
/*     */       }
/*     */     } 
/*     */     
/*  83 */     mapChildrenKeys(result, this, deep);
/*     */     
/*  85 */     return result;
/*     */   }
/*     */   
/*     */   public Map<String, Object> getValues(boolean deep) {
/*  89 */     Map<String, Object> result = new LinkedHashMap<>();
/*     */     
/*  91 */     Configuration root = getRoot();
/*  92 */     if (root != null && root.options().copyDefaults()) {
/*  93 */       ConfigurationSection defaults = getDefaultSection();
/*     */       
/*  95 */       if (defaults != null) {
/*  96 */         result.putAll(defaults.getValues(deep));
/*     */       }
/*     */     } 
/*     */     
/* 100 */     mapChildrenValues(result, this, deep);
/*     */     
/* 102 */     return result;
/*     */   }
/*     */   
/*     */   public boolean contains(String path) {
/* 106 */     return contains(path, false);
/*     */   }
/*     */   
/*     */   public boolean contains(String path, boolean ignoreDefault) {
/* 110 */     return ((ignoreDefault ? get(path, null) : get(path)) != null);
/*     */   }
/*     */   
/*     */   public boolean isSet(String path) {
/* 114 */     Configuration root = getRoot();
/* 115 */     if (root == null) {
/* 116 */       return false;
/*     */     }
/* 118 */     if (root.options().copyDefaults()) {
/* 119 */       return contains(path);
/*     */     }
/* 121 */     return (get(path, null) != null);
/*     */   }
/*     */   
/*     */   public String getCurrentPath() {
/* 125 */     return this.fullPath;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 129 */     return this.path;
/*     */   }
/*     */   
/*     */   public Configuration getRoot() {
/* 133 */     return this.root;
/*     */   }
/*     */   
/*     */   public ConfigurationSection getParent() {
/* 137 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void addDefault(String path, Object value) {
/* 141 */     Validate.notNull(path, "Path cannot be null");
/*     */     
/* 143 */     Configuration root = getRoot();
/* 144 */     if (root == null) {
/* 145 */       throw new IllegalStateException("Cannot add default without root");
/*     */     }
/* 147 */     if (root == this) {
/* 148 */       throw new UnsupportedOperationException("Unsupported addDefault(String, Object) implementation");
/*     */     }
/* 150 */     root.addDefault(createPath(this, path), value);
/*     */   }
/*     */   
/*     */   public ConfigurationSection getDefaultSection() {
/* 154 */     Configuration root = getRoot();
/* 155 */     Configuration defaults = (root == null) ? null : root.getDefaults();
/*     */     
/* 157 */     if (defaults != null && 
/* 158 */       defaults.isConfigurationSection(getCurrentPath())) {
/* 159 */       return defaults.getConfigurationSection(getCurrentPath());
/*     */     }
/*     */ 
/*     */     
/* 163 */     return null;
/*     */   }
/*     */   
/*     */   public void set(String path, Object value) {
/* 167 */     Validate.notEmpty(path, "Cannot set to an empty path");
/*     */     
/* 169 */     Configuration root = getRoot();
/* 170 */     if (root == null) {
/* 171 */       throw new IllegalStateException("Cannot use section without a root");
/*     */     }
/*     */     
/* 174 */     char separator = root.options().pathSeparator();
/*     */ 
/*     */     
/* 177 */     int i1 = -1;
/* 178 */     ConfigurationSection section = this; int i2;
/* 179 */     while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
/* 180 */       String node = path.substring(i2, i1);
/* 181 */       ConfigurationSection subSection = section.getConfigurationSection(node);
/* 182 */       if (subSection == null) {
/* 183 */         if (value == null) {
/*     */           return;
/*     */         }
/*     */         
/* 187 */         section = section.createSection(node); continue;
/*     */       } 
/* 189 */       section = subSection;
/*     */     } 
/*     */ 
/*     */     
/* 193 */     String key = path.substring(i2);
/* 194 */     if (section == this) {
/* 195 */       if (value == null) {
/* 196 */         this.map.remove(key);
/*     */       } else {
/* 198 */         this.map.put(key, value);
/*     */       } 
/*     */     } else {
/* 201 */       section.set(key, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get(String path) {
/* 206 */     return get(path, getDefault(path));
/*     */   }
/*     */   
/*     */   public Object get(String path, Object def) {
/* 210 */     Validate.notNull(path, "Path cannot be null");
/*     */     
/* 212 */     if (path.length() == 0) {
/* 213 */       return this;
/*     */     }
/*     */     
/* 216 */     Configuration root = getRoot();
/* 217 */     if (root == null) {
/* 218 */       throw new IllegalStateException("Cannot access section without a root");
/*     */     }
/*     */     
/* 221 */     char separator = root.options().pathSeparator();
/*     */ 
/*     */     
/* 224 */     int i1 = -1;
/* 225 */     ConfigurationSection section = this; int i2;
/* 226 */     while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
/* 227 */       section = section.getConfigurationSection(path.substring(i2, i1));
/* 228 */       if (section == null) {
/* 229 */         return def;
/*     */       }
/*     */     } 
/*     */     
/* 233 */     String key = path.substring(i2);
/* 234 */     if (section == this) {
/* 235 */       Object result = this.map.get(key);
/* 236 */       return (result == null) ? def : result;
/*     */     } 
/* 238 */     return section.get(key, def);
/*     */   }
/*     */   
/*     */   public ConfigurationSection createSection(String path) {
/* 242 */     Validate.notEmpty(path, "Cannot create section at empty path");
/* 243 */     Configuration root = getRoot();
/* 244 */     if (root == null) {
/* 245 */       throw new IllegalStateException("Cannot create section without a root");
/*     */     }
/*     */     
/* 248 */     char separator = root.options().pathSeparator();
/*     */ 
/*     */     
/* 251 */     int i1 = -1;
/* 252 */     ConfigurationSection section = this; int i2;
/* 253 */     while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
/* 254 */       String node = path.substring(i2, i1);
/* 255 */       ConfigurationSection subSection = section.getConfigurationSection(node);
/* 256 */       if (subSection == null) {
/* 257 */         section = section.createSection(node); continue;
/*     */       } 
/* 259 */       section = subSection;
/*     */     } 
/*     */ 
/*     */     
/* 263 */     String key = path.substring(i2);
/* 264 */     if (section == this) {
/* 265 */       ConfigurationSection result = new io.lumine.utils.config.MemorySection(this, key);
/* 266 */       this.map.put(key, result);
/* 267 */       return result;
/*     */     } 
/* 269 */     return section.createSection(key);
/*     */   }
/*     */   
/*     */   public ConfigurationSection createSection(String path, Map<?, ?> map) {
/* 273 */     ConfigurationSection section = createSection(path);
/*     */     
/* 275 */     for (Map.Entry<?, ?> entry : map.entrySet()) {
/* 276 */       if (entry.getValue() instanceof Map) {
/* 277 */         section.createSection(entry.getKey().toString(), (Map)entry.getValue()); continue;
/*     */       } 
/* 279 */       section.set(entry.getKey().toString(), entry.getValue());
/*     */     } 
/*     */ 
/*     */     
/* 283 */     return section;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString(String path) {
/* 288 */     Object def = getDefault(path);
/* 289 */     return getString(path, (def != null) ? def.toString() : null);
/*     */   }
/*     */   
/*     */   public String getString(String path, String def) {
/* 293 */     Object val = get(path, def);
/* 294 */     return (val != null) ? val.toString() : def;
/*     */   }
/*     */   
/*     */   public boolean isString(String path) {
/* 298 */     Object val = get(path);
/* 299 */     return val instanceof String;
/*     */   }
/*     */   
/*     */   public int getInt(String path) {
/* 303 */     Object def = getDefault(path);
/* 304 */     return getInt(path, (def instanceof Number) ? NumberConversions.toInt(def) : 0);
/*     */   }
/*     */   
/*     */   public int getInt(String path, int def) {
/* 308 */     Object val = get(path, Integer.valueOf(def));
/* 309 */     return (val instanceof Number) ? NumberConversions.toInt(val) : def;
/*     */   }
/*     */   
/*     */   public boolean isInt(String path) {
/* 313 */     Object val = get(path);
/* 314 */     return val instanceof Integer;
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String path) {
/* 318 */     Object def = getDefault(path);
/* 319 */     return getBoolean(path, (def instanceof Boolean) ? ((Boolean)def).booleanValue() : false);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String path, boolean def) {
/* 323 */     Object val = get(path, Boolean.valueOf(def));
/* 324 */     return (val instanceof Boolean) ? ((Boolean)val).booleanValue() : def;
/*     */   }
/*     */   
/*     */   public boolean isBoolean(String path) {
/* 328 */     Object val = get(path);
/* 329 */     return val instanceof Boolean;
/*     */   }
/*     */   
/*     */   public double getDouble(String path) {
/* 333 */     Object def = getDefault(path);
/* 334 */     return getDouble(path, (def instanceof Number) ? NumberConversions.toDouble(def) : 0.0D);
/*     */   }
/*     */   
/*     */   public double getDouble(String path, double def) {
/* 338 */     Object val = get(path, Double.valueOf(def));
/* 339 */     return (val instanceof Number) ? NumberConversions.toDouble(val) : def;
/*     */   }
/*     */   
/*     */   public boolean isDouble(String path) {
/* 343 */     Object val = get(path);
/* 344 */     return val instanceof Double;
/*     */   }
/*     */   
/*     */   public long getLong(String path) {
/* 348 */     Object def = getDefault(path);
/* 349 */     return getLong(path, (def instanceof Number) ? NumberConversions.toLong(def) : 0L);
/*     */   }
/*     */   
/*     */   public long getLong(String path, long def) {
/* 353 */     Object val = get(path, Long.valueOf(def));
/* 354 */     return (val instanceof Number) ? NumberConversions.toLong(val) : def;
/*     */   }
/*     */   
/*     */   public boolean isLong(String path) {
/* 358 */     Object val = get(path);
/* 359 */     return val instanceof Long;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<?> getList(String path) {
/* 364 */     Object def = getDefault(path);
/* 365 */     return getList(path, (def instanceof List) ? (List)def : null);
/*     */   }
/*     */   
/*     */   public List<?> getList(String path, List<?> def) {
/* 369 */     Object val = get(path, def);
/* 370 */     return (val instanceof List) ? (List)val : def;
/*     */   }
/*     */   
/*     */   public boolean isList(String path) {
/* 374 */     Object val = get(path);
/* 375 */     return val instanceof List;
/*     */   }
/*     */   
/*     */   public List<String> getStringList(String path) {
/* 379 */     List<?> list = getList(path);
/*     */     
/* 381 */     if (list == null) {
/* 382 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 385 */     List<String> result = new ArrayList<>();
/*     */     
/* 387 */     for (Object object : list) {
/* 388 */       if (object instanceof String || isPrimitiveWrapper(object)) {
/* 389 */         result.add(String.valueOf(object));
/*     */       }
/*     */     } 
/*     */     
/* 393 */     return result;
/*     */   }
/*     */   
/*     */   public List<Integer> getIntegerList(String path) {
/* 397 */     List<?> list = getList(path);
/*     */     
/* 399 */     if (list == null) {
/* 400 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 403 */     List<Integer> result = new ArrayList<>();
/*     */     
/* 405 */     for (Object object : list) {
/* 406 */       if (object instanceof Integer) {
/* 407 */         result.add((Integer)object); continue;
/* 408 */       }  if (object instanceof String) {
/*     */         try {
/* 410 */           result.add(Integer.valueOf((String)object));
/* 411 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 413 */       if (object instanceof Character) {
/* 414 */         result.add(Integer.valueOf(((Character)object).charValue())); continue;
/* 415 */       }  if (object instanceof Number) {
/* 416 */         result.add(Integer.valueOf(((Number)object).intValue()));
/*     */       }
/*     */     } 
/*     */     
/* 420 */     return result;
/*     */   }
/*     */   
/*     */   public List<Boolean> getBooleanList(String path) {
/* 424 */     List<?> list = getList(path);
/*     */     
/* 426 */     if (list == null) {
/* 427 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 430 */     List<Boolean> result = new ArrayList<>();
/*     */     
/* 432 */     for (Object object : list) {
/* 433 */       if (object instanceof Boolean) {
/* 434 */         result.add((Boolean)object); continue;
/* 435 */       }  if (object instanceof String) {
/* 436 */         if (Boolean.TRUE.toString().equals(object)) {
/* 437 */           result.add(Boolean.valueOf(true)); continue;
/* 438 */         }  if (Boolean.FALSE.toString().equals(object)) {
/* 439 */           result.add(Boolean.valueOf(false));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 444 */     return result;
/*     */   }
/*     */   
/*     */   public List<Double> getDoubleList(String path) {
/* 448 */     List<?> list = getList(path);
/*     */     
/* 450 */     if (list == null) {
/* 451 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 454 */     List<Double> result = new ArrayList<>();
/*     */     
/* 456 */     for (Object object : list) {
/* 457 */       if (object instanceof Double) {
/* 458 */         result.add((Double)object); continue;
/* 459 */       }  if (object instanceof String) {
/*     */         try {
/* 461 */           result.add(Double.valueOf((String)object));
/* 462 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 464 */       if (object instanceof Character) {
/* 465 */         result.add(Double.valueOf(((Character)object).charValue())); continue;
/* 466 */       }  if (object instanceof Number) {
/* 467 */         result.add(Double.valueOf(((Number)object).doubleValue()));
/*     */       }
/*     */     } 
/*     */     
/* 471 */     return result;
/*     */   }
/*     */   
/*     */   public List<Float> getFloatList(String path) {
/* 475 */     List<?> list = getList(path);
/*     */     
/* 477 */     if (list == null) {
/* 478 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 481 */     List<Float> result = new ArrayList<>();
/*     */     
/* 483 */     for (Object object : list) {
/* 484 */       if (object instanceof Float) {
/* 485 */         result.add((Float)object); continue;
/* 486 */       }  if (object instanceof String) {
/*     */         try {
/* 488 */           result.add(Float.valueOf((String)object));
/* 489 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 491 */       if (object instanceof Character) {
/* 492 */         result.add(Float.valueOf(((Character)object).charValue())); continue;
/* 493 */       }  if (object instanceof Number) {
/* 494 */         result.add(Float.valueOf(((Number)object).floatValue()));
/*     */       }
/*     */     } 
/*     */     
/* 498 */     return result;
/*     */   }
/*     */   
/*     */   public List<Long> getLongList(String path) {
/* 502 */     List<?> list = getList(path);
/*     */     
/* 504 */     if (list == null) {
/* 505 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 508 */     List<Long> result = new ArrayList<>();
/*     */     
/* 510 */     for (Object object : list) {
/* 511 */       if (object instanceof Long) {
/* 512 */         result.add((Long)object); continue;
/* 513 */       }  if (object instanceof String) {
/*     */         try {
/* 515 */           result.add(Long.valueOf((String)object));
/* 516 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 518 */       if (object instanceof Character) {
/* 519 */         result.add(Long.valueOf(((Character)object).charValue())); continue;
/* 520 */       }  if (object instanceof Number) {
/* 521 */         result.add(Long.valueOf(((Number)object).longValue()));
/*     */       }
/*     */     } 
/*     */     
/* 525 */     return result;
/*     */   }
/*     */   
/*     */   public List<Byte> getByteList(String path) {
/* 529 */     List<?> list = getList(path);
/*     */     
/* 531 */     if (list == null) {
/* 532 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 535 */     List<Byte> result = new ArrayList<>();
/*     */     
/* 537 */     for (Object object : list) {
/* 538 */       if (object instanceof Byte) {
/* 539 */         result.add((Byte)object); continue;
/* 540 */       }  if (object instanceof String) {
/*     */         try {
/* 542 */           result.add(Byte.valueOf((String)object));
/* 543 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 545 */       if (object instanceof Character) {
/* 546 */         result.add(Byte.valueOf((byte)((Character)object).charValue())); continue;
/* 547 */       }  if (object instanceof Number) {
/* 548 */         result.add(Byte.valueOf(((Number)object).byteValue()));
/*     */       }
/*     */     } 
/*     */     
/* 552 */     return result;
/*     */   }
/*     */   
/*     */   public List<Character> getCharacterList(String path) {
/* 556 */     List<?> list = getList(path);
/*     */     
/* 558 */     if (list == null) {
/* 559 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 562 */     List<Character> result = new ArrayList<>();
/*     */     
/* 564 */     for (Object object : list) {
/* 565 */       if (object instanceof Character) {
/* 566 */         result.add((Character)object); continue;
/* 567 */       }  if (object instanceof String) {
/* 568 */         String str = (String)object;
/*     */         
/* 570 */         if (str.length() == 1)
/* 571 */           result.add(Character.valueOf(str.charAt(0)));  continue;
/*     */       } 
/* 573 */       if (object instanceof Number) {
/* 574 */         result.add(Character.valueOf((char)((Number)object).intValue()));
/*     */       }
/*     */     } 
/*     */     
/* 578 */     return result;
/*     */   }
/*     */   
/*     */   public List<Short> getShortList(String path) {
/* 582 */     List<?> list = getList(path);
/*     */     
/* 584 */     if (list == null) {
/* 585 */       return new ArrayList<>(0);
/*     */     }
/*     */     
/* 588 */     List<Short> result = new ArrayList<>();
/*     */     
/* 590 */     for (Object object : list) {
/* 591 */       if (object instanceof Short) {
/* 592 */         result.add((Short)object); continue;
/* 593 */       }  if (object instanceof String) {
/*     */         try {
/* 595 */           result.add(Short.valueOf((String)object));
/* 596 */         } catch (Exception exception) {} continue;
/*     */       } 
/* 598 */       if (object instanceof Character) {
/* 599 */         result.add(Short.valueOf((short)((Character)object).charValue())); continue;
/* 600 */       }  if (object instanceof Number) {
/* 601 */         result.add(Short.valueOf(((Number)object).shortValue()));
/*     */       }
/*     */     } 
/*     */     
/* 605 */     return result;
/*     */   }
/*     */   
/*     */   public List<Map<?, ?>> getMapList(String path) {
/* 609 */     List<?> list = getList(path);
/* 610 */     List<Map<?, ?>> result = new ArrayList<>();
/*     */     
/* 612 */     if (list == null) {
/* 613 */       return result;
/*     */     }
/*     */     
/* 616 */     for (Object object : list) {
/* 617 */       if (object instanceof Map) {
/* 618 */         result.add((Map<?, ?>)object);
/*     */       }
/*     */     } 
/*     */     
/* 622 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.configuration.serialization.ConfigurationSerializable> T getSerializable(String path, Class<T> clazz) {
/* 628 */     Validate.notNull(clazz, "ConfigurationSerializable class cannot be null");
/* 629 */     Object def = getDefault(path);
/* 630 */     return getSerializable(path, clazz, (def != null && clazz.isInstance(def)) ? clazz.cast(def) : null);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.configuration.serialization.ConfigurationSerializable> T getSerializable(String path, Class<T> clazz, T def) {
/* 635 */     Validate.notNull(clazz, "ConfigurationSerializable class cannot be null");
/* 636 */     Object val = get(path);
/* 637 */     return (val != null && clazz.isInstance(val)) ? clazz.cast(val) : def;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getItemStack(String path) {
/* 665 */     return getSerializable(path, ItemStack.class);
/*     */   }
/*     */   
/*     */   public ItemStack getItemStack(String path, ItemStack def) {
/* 669 */     return getSerializable(path, ItemStack.class, def);
/*     */   }
/*     */   
/*     */   public boolean isItemStack(String path) {
/* 673 */     return (getSerializable(path, ItemStack.class) != null);
/*     */   }
/*     */   
/*     */   public Color getColor(String path) {
/* 677 */     return getSerializable(path, Color.class);
/*     */   }
/*     */   
/*     */   public Color getColor(String path, Color def) {
/* 681 */     return getSerializable(path, Color.class, def);
/*     */   }
/*     */   
/*     */   public boolean isColor(String path) {
/* 685 */     return (getSerializable(path, Color.class) != null);
/*     */   }
/*     */   
/*     */   public ConfigurationSection getConfigurationSection(String path) {
/* 689 */     Object val = get(path, null);
/* 690 */     if (val != null) {
/* 691 */       return (val instanceof ConfigurationSection) ? (ConfigurationSection)val : null;
/*     */     }
/*     */     
/* 694 */     val = get(path, getDefault(path));
/* 695 */     return (val instanceof ConfigurationSection) ? createSection(path) : null;
/*     */   }
/*     */   
/*     */   public boolean isConfigurationSection(String path) {
/* 699 */     Object val = get(path);
/* 700 */     return val instanceof ConfigurationSection;
/*     */   }
/*     */   
/*     */   protected boolean isPrimitiveWrapper(Object input) {
/* 704 */     return (input instanceof Integer || input instanceof Boolean || input instanceof Character || input instanceof Byte || input instanceof Short || input instanceof Double || input instanceof Long || input instanceof Float);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object getDefault(String path) {
/* 711 */     Validate.notNull(path, "Path cannot be null");
/*     */     
/* 713 */     Configuration root = getRoot();
/* 714 */     Configuration defaults = (root == null) ? null : root.getDefaults();
/* 715 */     return (defaults == null) ? null : defaults.get(createPath(this, path));
/*     */   }
/*     */   
/*     */   protected void mapChildrenKeys(Set<String> output, ConfigurationSection section, boolean deep) {
/* 719 */     if (section instanceof io.lumine.utils.config.MemorySection) {
/* 720 */       io.lumine.utils.config.MemorySection sec = (io.lumine.utils.config.MemorySection)section;
/*     */       
/* 722 */       for (Map.Entry<String, Object> entry : sec.map.entrySet()) {
/* 723 */         output.add(createPath(section, entry.getKey(), this));
/*     */         
/* 725 */         if (deep && entry.getValue() instanceof ConfigurationSection) {
/* 726 */           ConfigurationSection subsection = (ConfigurationSection)entry.getValue();
/* 727 */           mapChildrenKeys(output, subsection, deep);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 731 */       Set<String> keys = section.getKeys(deep);
/*     */       
/* 733 */       for (String key : keys) {
/* 734 */         output.add(createPath(section, key, this));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void mapChildrenValues(Map<String, Object> output, ConfigurationSection section, boolean deep) {
/* 740 */     if (section instanceof io.lumine.utils.config.MemorySection) {
/* 741 */       io.lumine.utils.config.MemorySection sec = (io.lumine.utils.config.MemorySection)section;
/*     */       
/* 743 */       for (Map.Entry<String, Object> entry : sec.map.entrySet()) {
/*     */ 
/*     */ 
/*     */         
/* 747 */         String childPath = createPath(section, entry.getKey(), this);
/* 748 */         output.remove(childPath);
/* 749 */         output.put(childPath, entry.getValue());
/*     */         
/* 751 */         if (entry.getValue() instanceof ConfigurationSection && 
/* 752 */           deep) {
/* 753 */           mapChildrenValues(output, (ConfigurationSection)entry.getValue(), deep);
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 758 */       Map<String, Object> values = section.getValues(deep);
/*     */       
/* 760 */       for (Map.Entry<String, Object> entry : values.entrySet()) {
/* 761 */         output.put(createPath(section, entry.getKey(), this), entry.getValue());
/*     */       }
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
/*     */ 
/*     */   
/*     */   public static String createPath(ConfigurationSection section, String key) {
/* 778 */     return createPath(section, key, (section == null) ? null : (ConfigurationSection)section.getRoot());
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
/*     */   public static String createPath(ConfigurationSection section, String key, ConfigurationSection relativeTo) {
/* 794 */     Validate.notNull(section, "Cannot create path without a section");
/* 795 */     Configuration root = section.getRoot();
/* 796 */     if (root == null) {
/* 797 */       throw new IllegalStateException("Cannot create path without a root");
/*     */     }
/* 799 */     char separator = root.options().pathSeparator();
/*     */     
/* 801 */     StringBuilder builder = new StringBuilder();
/* 802 */     if (section != null) {
/* 803 */       for (ConfigurationSection parent = section; parent != null && parent != relativeTo; parent = parent.getParent()) {
/* 804 */         if (builder.length() > 0) {
/* 805 */           builder.insert(0, separator);
/*     */         }
/*     */         
/* 808 */         builder.insert(0, parent.getName());
/*     */       } 
/*     */     }
/*     */     
/* 812 */     if (key != null && key.length() > 0) {
/* 813 */       if (builder.length() > 0) {
/* 814 */         builder.append(separator);
/*     */       }
/*     */       
/* 817 */       builder.append(key);
/*     */     } 
/*     */     
/* 820 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 825 */     Configuration root = getRoot();
/*     */     
/* 827 */     return getClass().getSimpleName() + "[path='" + 
/*     */       
/* 829 */       getCurrentPath() + "', root='" + (
/* 830 */       (root == null) ? null : root
/* 831 */       .getClass().getSimpleName()) + "']";
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\config\MemorySection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */