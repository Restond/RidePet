/*     */ package lumine.xikage.mythicmobs.io;
/*     */ 
/*     */ import io.lumine.utils.serialize.Locus;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.io.GenericConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ public class MythicLineConfig
/*     */   implements GenericConfig
/*     */ {
/*     */   private String key;
/*     */   private String line;
/*  26 */   private String fileName = "Unknown"; public String getFileName() { return this.fileName; } public String getKey() {
/*  27 */     return this.key;
/*     */   }
/*  29 */   private HashMap<String, String> config = new HashMap<>();
/*     */   
/*     */   public MythicLineConfig(String fileName, String line) {
/*  32 */     this(line);
/*  33 */     this.fileName = fileName;
/*     */   }
/*     */   
/*     */   public MythicLineConfig(File file, String line) {
/*  37 */     this(line);
/*  38 */     this.fileName = file.getName();
/*     */   }
/*     */   
/*     */   public MythicLineConfig(String s) {
/*  42 */     s = parseBlock(s);
/*  43 */     this.line = s;
/*  44 */     MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "LOADING LineConfig FOR LINE: {0}", new Object[] { s });
/*     */     
/*  46 */     if (s.contains("{") && s.contains("}")) {
/*     */       
/*  48 */       this.key = s.substring(0, s.indexOf("{"));
/*     */       
/*  50 */       int startPos = s.indexOf('{') + 1;
/*  51 */       int lastPos = s.lastIndexOf('}');
/*  52 */       int count = 0;
/*     */       
/*  54 */       s = s.substring(startPos, lastPos);
/*     */ 
/*     */       
/*  57 */       for (char c : s.toCharArray()) {
/*  58 */         if (c == '{') {
/*  59 */           count++;
/*     */         }
/*  61 */         if (c == '}') {
/*  62 */           count--;
/*     */         }
/*     */       } 
/*     */       
/*  66 */       if (count != 0) {
/*  67 */         MythicLogger.errorGenericConfig(this, "Could not load line due to unbalanced braces.");
/*     */         
/*     */         return;
/*     */       } 
/*  71 */       MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "| Normalized Line to: {0}", new Object[] { s });
/*     */       
/*  73 */       if (s.length() == 0) {
/*     */         return;
/*     */       }
/*     */       
/*  77 */       int start = 0;
/*  78 */       int pos = 0;
/*  79 */       int depth = 0;
/*  80 */       String lastKey = "";
/*  81 */       String lastVal = "";
/*  82 */       boolean inb = false;
/*     */       
/*  84 */       s = s + "}";
/*  85 */       for (char c : s.toCharArray()) {
/*  86 */         if (c == '{' || c == '[') {
/*  87 */           depth++;
/*     */         }
/*  89 */         if (c == '}' || c == ']') {
/*  90 */           depth--;
/*     */         }
/*  92 */         if ((c == ';' && depth == 0) || (c == '}' && depth < 0)) {
/*     */           try {
/*  94 */             String element = s.substring(start, pos);
/*     */             
/*  96 */             if (pos - start > 0 && element.length() > 0) {
/*  97 */               String key = element.substring(0, element.indexOf('=')).trim().toLowerCase();
/*  98 */               String val = element.substring(element.indexOf('=') + 1).trim();
/*     */               
/* 100 */               this.config.put(key, val);
/* 101 */               MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "+ LOADED ELEMENT " + key + " == " + val, new Object[0]);
/*     */             } 
/* 103 */           } catch (Exception ex) {
/* 104 */             MythicLogger.debug(MythicLogger.DebugLevel.EVENT, "! Failed to load element due to bad syntax.", new Object[0]);
/*     */           } finally {
/*     */             
/* 107 */             start = pos + 1;
/*     */           } 
/*     */         }
/* 110 */         pos++;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 124 */     else if (s.contains("[") && s.contains("]")) {
/*     */       try {
/* 126 */         String[] split = s.split("\\[");
/* 127 */         s = split[1];
/*     */         
/* 129 */         String[] split2 = s.split("\\]");
/* 130 */         s = split2[0];
/* 131 */       } catch (ArrayIndexOutOfBoundsException ex) {
/* 132 */         MythicMobs.throwSevere("error-config-load-badbrackets", "Could not load LineConfig: Invalid syntax. String = {0}", new Object[] { s });
/*     */         return;
/*     */       } 
/*     */     } else {
/* 136 */       this.key = s;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getLine() {
/* 141 */     return this.line;
/*     */   }
/*     */   
/*     */   public int size() {
/* 145 */     return this.config.size();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<String, String>> entrySet() {
/* 149 */     return this.config.entrySet();
/*     */   }
/*     */   
/*     */   public static String getKey(String s) {
/* 153 */     String key = null;
/*     */     
/* 155 */     if (s.contains("{")) {
/* 156 */       key = s.substring(0, s.indexOf("{"));
/* 157 */     } else if (s.contains("[")) {
/* 158 */       key = s.substring(0, s.indexOf("["));
/*     */     } else {
/* 160 */       key = s;
/*     */     } 
/* 162 */     return key;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String key) {
/* 167 */     return getBoolean(key, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String key, boolean def) {
/* 172 */     key = key.toLowerCase();
/* 173 */     String s = this.config.get(key);
/*     */     
/* 175 */     if (s == null) return def;
/*     */     
/*     */     try {
/* 178 */       return Boolean.parseBoolean(s);
/* 179 */     } catch (Exception ex) {
/* 180 */       return def;
/*     */     } 
/*     */   }
/*     */   public boolean getBoolean(String[] key, boolean def) {
/* 184 */     String s = null;
/*     */     
/* 186 */     for (String k : key) {
/* 187 */       s = this.config.get(k.toLowerCase());
/* 188 */       if (s != null)
/*     */         break; 
/* 190 */     }  if (s == null) return def; 
/*     */     try {
/* 192 */       return Boolean.parseBoolean(s);
/* 193 */     } catch (Exception ex) {
/* 194 */       return def;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString(String key) {
/* 200 */     return getString(key, null);
/*     */   }
/*     */   
/*     */   public String getString(String[] key) {
/* 204 */     return getString(key, null, new String[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString(String key, String def) {
/* 209 */     String s = this.config.get(key.toLowerCase());
/*     */     
/* 211 */     if (s == null) {
/* 212 */       return def;
/*     */     }
/*     */     
/* 215 */     return s;
/*     */   }
/*     */   
/*     */   public String getString(String[] key, String def, String... args) {
/* 219 */     String s = null;
/* 220 */     for (String k : key) {
/* 221 */       s = this.config.get(k.toLowerCase());
/* 222 */       if (s != null) return s;
/*     */     
/*     */     } 
/* 225 */     for (String a : args) {
/* 226 */       if (a != null) return a;
/*     */     
/*     */     } 
/* 229 */     return def;
/*     */   }
/*     */   
/*     */   public PlaceholderString getPlaceholderString(String key, String def) {
/* 233 */     String s = getString(key, def);
/*     */     
/* 235 */     if (s == null) {
/* 236 */       return null;
/*     */     }
/* 238 */     return PlaceholderString.of(s);
/*     */   }
/*     */   
/*     */   public PlaceholderString getPlaceholderString(String[] key, String def, String... args) {
/* 242 */     String s = getString(key, def, args);
/*     */     
/* 244 */     if (s == null) {
/* 245 */       return null;
/*     */     }
/* 247 */     return PlaceholderString.of(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInteger(String key) {
/* 252 */     return getInteger(key, 0);
/*     */   }
/*     */   
/*     */   public int getInteger(String[] key) {
/* 256 */     return getInteger(key, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInteger(String key, int def) {
/* 261 */     String s = this.config.get(key.toLowerCase());
/*     */     
/* 263 */     if (s == null) return def;
/*     */     
/*     */     try {
/* 266 */       return Integer.parseInt(s);
/* 267 */     } catch (Exception ex) {
/* 268 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getInteger(String[] key, int def) {
/* 273 */     String s = null;
/*     */     
/* 275 */     for (String k : key) {
/* 276 */       s = this.config.get(k.toLowerCase());
/* 277 */       if (s != null)
/*     */         break; 
/* 279 */     }  if (s == null) return def; 
/*     */     try {
/* 281 */       return Integer.parseInt(s);
/* 282 */     } catch (Exception ex) {
/* 283 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PlaceholderInt getPlaceholderInteger(String key, String def) {
/* 288 */     String s = getString(key, def);
/*     */     
/* 290 */     if (s == null) {
/* 291 */       return null;
/*     */     }
/* 293 */     return PlaceholderInt.of(s);
/*     */   }
/*     */   
/*     */   public PlaceholderInt getPlaceholderInteger(String[] key, String def, String... args) {
/* 297 */     String s = getString(key, def, args);
/*     */     
/* 299 */     if (s == null) {
/* 300 */       return null;
/*     */     }
/* 302 */     return PlaceholderInt.of(s);
/*     */   }
/*     */   
/*     */   public PlaceholderInt getPlaceholderInteger(String key, int def) {
/* 306 */     return getPlaceholderInteger(key, String.valueOf(def));
/*     */   }
/*     */   
/*     */   public PlaceholderInt getPlaceholderInteger(String[] key, int def, String... args) {
/* 310 */     return getPlaceholderInteger(key, String.valueOf(def), args);
/*     */   }
/*     */   
/*     */   public double getDouble(String key) {
/* 314 */     return getDouble(key, 0.0D);
/*     */   }
/*     */   
/*     */   public double getDouble(String[] key) {
/* 318 */     return getDouble(key, 0.0D);
/*     */   }
/*     */   
/*     */   public double getDouble(String key, double def) {
/* 322 */     String s = this.config.get(key.toLowerCase());
/*     */     
/* 324 */     if (s == null) return def;
/*     */     
/*     */     try {
/* 327 */       return Double.parseDouble(s);
/* 328 */     } catch (Exception ex) {
/* 329 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getDouble(String[] key, double def) {
/* 334 */     String s = null;
/*     */     
/* 336 */     for (String k : key) {
/* 337 */       s = this.config.get(k.toLowerCase());
/* 338 */       if (s != null)
/*     */         break; 
/* 340 */     }  if (s == null) return def; 
/*     */     try {
/* 342 */       return Double.parseDouble(s);
/* 343 */     } catch (Exception ex) {
/* 344 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PlaceholderDouble getPlaceholderDouble(String key, String def) {
/* 349 */     String s = getString(key, def);
/*     */     
/* 351 */     if (s == null) {
/* 352 */       return null;
/*     */     }
/* 354 */     return PlaceholderDouble.of(s);
/*     */   }
/*     */   
/*     */   public PlaceholderDouble getPlaceholderDouble(String[] key, String def, String... args) {
/* 358 */     String s = getString(key, def, args);
/*     */     
/* 360 */     if (s == null) {
/* 361 */       return null;
/*     */     }
/* 363 */     return PlaceholderDouble.of(s);
/*     */   }
/*     */   
/*     */   public PlaceholderDouble getPlaceholderDouble(String key, double def) {
/* 367 */     return getPlaceholderDouble(key, String.valueOf(def));
/*     */   }
/*     */   
/*     */   public PlaceholderDouble getPlaceholderDouble(String[] key, double def, String... args) {
/* 371 */     return getPlaceholderDouble(key, String.valueOf(def), args);
/*     */   }
/*     */   
/*     */   public float getFloat(String key) {
/* 375 */     return getFloat(key, 0.0F);
/*     */   }
/*     */   
/*     */   public float getFloat(String[] key) {
/* 379 */     return getFloat(key, 0.0F);
/*     */   }
/*     */   
/*     */   public float getFloat(String key, float def) {
/* 383 */     String s = this.config.get(key.toLowerCase());
/*     */     
/* 385 */     if (s == null) return def;
/*     */     
/*     */     try {
/* 388 */       return Float.parseFloat(s);
/* 389 */     } catch (Exception ex) {
/* 390 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getFloat(String[] key, float def) {
/* 395 */     String s = null;
/*     */     
/* 397 */     for (String k : key) {
/* 398 */       s = this.config.get(k.toLowerCase());
/* 399 */       if (s != null)
/*     */         break; 
/* 401 */     }  if (s == null) return def; 
/*     */     try {
/* 403 */       return Float.parseFloat(s);
/* 404 */     } catch (Exception ex) {
/* 405 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PlaceholderFloat getPlaceholderFloat(String key, String def) {
/* 410 */     String s = getString(key, def);
/*     */     
/* 412 */     if (s == null) {
/* 413 */       return null;
/*     */     }
/* 415 */     return PlaceholderFloat.of(s);
/*     */   }
/*     */   
/*     */   public PlaceholderFloat getPlaceholderFloat(String[] key, String def, String... args) {
/* 419 */     String s = getString(key, def, args);
/*     */     
/* 421 */     if (s == null) {
/* 422 */       return null;
/*     */     }
/* 424 */     return PlaceholderFloat.of(s);
/*     */   }
/*     */   
/*     */   public PlaceholderFloat getPlaceholderFloat(String key, float def) {
/* 428 */     return getPlaceholderFloat(key, String.valueOf(def));
/*     */   }
/*     */   
/*     */   public PlaceholderFloat getPlaceholderFloat(String[] key, float def, String... args) {
/* 432 */     return getPlaceholderFloat(key, String.valueOf(def), args);
/*     */   }
/*     */   
/*     */   public long getLong(String key) {
/* 436 */     return getLong(key, 0L);
/*     */   }
/*     */   public long getLong(String[] key) {
/* 439 */     return getLong(key, 0L);
/*     */   }
/*     */   public long getLong(String key, long def) {
/* 442 */     String s = this.config.get(key.toLowerCase());
/*     */     
/* 444 */     if (s == null) return def;
/*     */     
/*     */     try {
/* 447 */       return Long.parseLong(s);
/* 448 */     } catch (Exception ex) {
/* 449 */       return def;
/*     */     } 
/*     */   }
/*     */   public long getLong(String[] key, long def) {
/* 453 */     String s = null;
/*     */     
/* 455 */     for (String k : key) {
/* 456 */       s = this.config.get(k.toLowerCase());
/* 457 */       if (s != null)
/*     */         break; 
/* 459 */     }  if (s == null) return def; 
/*     */     try {
/* 461 */       return Long.parseLong(s);
/* 462 */     } catch (Exception ex) {
/* 463 */       return def;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Color getColor(String[] key, String def) {
/* 468 */     String s = null;
/*     */     
/* 470 */     for (String k : key) {
/* 471 */       s = this.config.get(k.toLowerCase());
/* 472 */       if (s != null)
/*     */         break; 
/* 474 */     }  return getColor(s, def);
/*     */   }
/*     */   public Color getColor(String key, String def) {
/* 477 */     String c = (key == null) ? def : getString(key, def);
/*     */     
/* 479 */     if (c == null) {
/* 480 */       return null;
/*     */     }
/*     */     
/* 483 */     if (c.startsWith("#") && c.length() == 7) {
/* 484 */       return Color.decode(c);
/*     */     }
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
/* 499 */     MythicLogger.errorGenericConfig(this, "Couldn't parse color '" + c + "': must be in Hex or R,G,B format");
/* 500 */     return Color.RED;
/*     */   }
/*     */   
/*     */   public List<Locus> getLocationList(String[] key, List<Locus> def) {
/* 504 */     String s = null;
/*     */     
/* 506 */     for (String k : key) {
/* 507 */       s = this.config.get(k.toLowerCase());
/* 508 */       if (s != null) {
/* 509 */         return getLocationList(k, def);
/*     */       }
/*     */     } 
/* 512 */     return def;
/*     */   }
/*     */   public List<Locus> getLocationList(String key, List<Locus> def) {
/* 515 */     List<Locus> result = new ArrayList<>();
/*     */     
/* 517 */     String internalName = getString(key);
/*     */     
/* 519 */     internalName = unparseBlock(internalName);
/* 520 */     if (internalName == null) {
/* 521 */       return def;
/*     */     }
/*     */     try {
/* 524 */       if (internalName.startsWith("[") && internalName.endsWith("]")) {
/* 525 */         internalName = internalName.substring(1, internalName.length() - 1);
/*     */         
/* 527 */         internalName = parseBlock(internalName);
/* 528 */         internalName = internalName.replace("- ", ";");
/* 529 */         String[] split = internalName.split(";");
/*     */         
/* 531 */         List<String> elements = new ArrayList<>();
/* 532 */         for (String e : split) {
/* 533 */           String[] sp = e.split(",");
/* 534 */           double x = Double.valueOf(sp[0]).doubleValue();
/* 535 */           double y = Double.valueOf(sp[1]).doubleValue();
/* 536 */           double z = Double.valueOf(sp[2]).doubleValue();
/*     */           
/* 538 */           result.add(Locus.of(x, y, z));
/*     */         } 
/*     */       } else {
/* 541 */         String[] sp = internalName.split(",");
/* 542 */         double x = Double.valueOf(sp[0]).doubleValue();
/* 543 */         double y = Double.valueOf(sp[1]).doubleValue();
/* 544 */         double z = Double.valueOf(sp[2]).doubleValue();
/*     */         
/* 546 */         result.add(Locus.of(x, y, z));
/*     */       } 
/* 548 */     } catch (Exception|Error ex) {
/* 549 */       MythicLogger.errorGenericConfig(this, "LocationList is improperly configured");
/* 550 */       ex.printStackTrace();
/*     */     } 
/* 552 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String unparseBlock(String s) {
/* 557 */     if (s.contains("\"")) {
/* 558 */       String[] split = s.split("\"");
/*     */       
/* 560 */       int i = 0;
/* 561 */       String ns = "";
/* 562 */       for (String str : split) {
/* 563 */         if (i % 2 == 1) {
/* 564 */           ns = ns.concat("\"" + SkillString.unparseMessageSpecialChars(str) + "\"");
/*     */         } else {
/* 566 */           ns = ns.concat(str);
/*     */         } 
/* 568 */         i++;
/*     */       } 
/* 570 */       s = ns;
/*     */     } 
/* 572 */     if (s.contains("'")) {
/* 573 */       String[] split = s.split("'");
/*     */       
/* 575 */       int i = 0;
/* 576 */       String ns = "";
/* 577 */       for (String str : split) {
/* 578 */         if (i % 2 == 1) {
/* 579 */           ns = ns.concat("'" + SkillString.unparseMessageSpecialChars(str) + "'");
/*     */         } else {
/* 581 */           ns = ns.concat(str);
/*     */         } 
/* 583 */         i++;
/*     */       } 
/* 585 */       s = ns;
/*     */     } 
/*     */     
/* 588 */     int pos = 0;
/* 589 */     int count = 0;
/* 590 */     int ss = 0;
/* 591 */     int sc = 0;
/* 592 */     int ec = 0;
/* 593 */     String parsed = "";
/*     */     
/* 595 */     for (char c : s.toCharArray()) {
/* 596 */       if (c == '{') {
/* 597 */         if (count == 0) {
/* 598 */           sc = pos;
/*     */         }
/* 600 */         count++;
/*     */       } 
/*     */       
/* 603 */       count--;
/*     */       
/* 605 */       if (c == '}' && count == 0) {
/* 606 */         ec = pos;
/* 607 */         String f = s.substring(ss, sc);
/* 608 */         String m = s.substring(sc, ec).replace(" ", "<&csp>").replace("-", "<&da>");
/* 609 */         String e = s.substring(ec);
/*     */ 
/*     */ 
/*     */         
/* 613 */         parsed = parsed + f + m;
/* 614 */         ss = pos;
/*     */       } 
/*     */ 
/*     */       
/* 618 */       pos++;
/*     */     } 
/* 620 */     parsed = parsed + s.substring(ss, pos);
/*     */     
/* 622 */     return parsed;
/*     */   }
/*     */   
/*     */   public static String parseBlock(String s) {
/* 626 */     return s.replace("<&csp>", " ").replace("<&da>", "-").trim();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\io\MythicLineConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */