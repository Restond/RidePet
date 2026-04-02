/*     */ package lumine.xikage.mythicmobs.skills.placeholders;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.Placeholder;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderManager;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.types.EntityPlaceholder;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.types.MetaPlaceholder;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ 
/*     */ 
/*     */ public class PlaceholderParser
/*     */ {
/*     */   protected final String strValue;
/*     */   protected final String strUnparsedValue;
/*     */   protected final boolean papi;
/*     */   private Map<String, PlaceholderManager.PlaceholderEntry> variables;
/*     */   protected boolean doMath = false;
/*     */   
/*     */   public boolean isDoMath() {
/*  26 */     return this.doMath; } public void setDoMath(boolean doMath) { this.doMath = doMath; }
/*     */   
/*     */   public PlaceholderParser(String string) {
/*  29 */     if (string == null) {
/*  30 */       this.strValue = null;
/*  31 */       this.strUnparsedValue = null;
/*  32 */       this.variables = null;
/*  33 */       this.papi = false;
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/*  38 */       if (string.startsWith("\"") && string.endsWith("\"")) {
/*  39 */         string = string.substring(1, string.length() - 1);
/*     */       }
/*  41 */     } catch (Exception ex) {
/*  42 */       MythicLogger.error("Message '" + string + "' is incorrectly configured.");
/*  43 */       string = "INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD.";
/*     */     } 
/*     */     try {
/*  46 */       if (string.startsWith("'") && string.endsWith("'")) {
/*  47 */         string = string.substring(1, string.length() - 1);
/*     */       }
/*  49 */     } catch (Exception ex) {
/*  50 */       MythicLogger.error("Message '" + string + "' is incorrectly configured.");
/*  51 */       string = "INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD.";
/*     */     } 
/*     */     
/*  54 */     this.strUnparsedValue = SkillString.convertLegacyVariables(string);
/*  55 */     this.strValue = SkillString.parseMessageSpecialChars(this.strUnparsedValue);
/*     */     
/*  57 */     if (this.strValue.startsWith("eval ")) {
/*  58 */       this.doMath = true;
/*  59 */       string = string.substring(5);
/*  60 */     } else if (this.strValue.contains("+") || this.strValue.contains("-") || this.strValue.contains("*") || this.strValue.contains("/") || this.strValue.contains("^")) {
/*  61 */       this.doMath = true;
/*     */     } 
/*     */     
/*  64 */     this.papi = (MythicMobs.inst().getCompatibility().getPlaceholderAPI().isPresent() && this.strUnparsedValue.contains("%"));
/*     */     
/*  66 */     getPlaceholderManager().registerParser(this);
/*  67 */     checkForVariables();
/*     */   }
/*     */   
/*     */   public void checkForVariables() {
/*  71 */     if (getPlaceholderManager().checkForVariables(this.strValue)) {
/*  72 */       this.variables = new HashMap<>();
/*  73 */       Matcher matcher = getPlaceholderManager().matcher(this.strUnparsedValue);
/*     */       
/*  75 */       while (matcher.find()) {
/*  76 */         String var = matcher.group(1).substring(1, matcher.group(1).length() - 1);
/*  77 */         PlaceholderManager.PlaceholderEntry transformer = getPlaceholderManager().getPlaceholder(var);
/*     */         
/*  79 */         if (transformer != null) {
/*  80 */           this.variables.put(var, transformer);
/*     */         }
/*     */       } 
/*     */     } else {
/*  84 */       this.variables = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String parse(PlaceholderMeta data, AbstractEntity target) {
/*  89 */     if (this.variables == null || this.variables.size() == 0) return this.strValue;
/*     */     
/*  91 */     String string = this.strValue;
/*     */     
/*  93 */     for (Map.Entry<String, PlaceholderManager.PlaceholderEntry> entry : this.variables.entrySet()) {
/*  94 */       String key = "<" + (String)entry.getKey() + ">";
/*  95 */       Placeholder pl = ((PlaceholderManager.PlaceholderEntry)entry.getValue()).getPlaceholder();
/*  96 */       String arg = ((PlaceholderManager.PlaceholderEntry)entry.getValue()).getArg();
/*     */       
/*     */       try {
/*  99 */         if (pl instanceof MetaPlaceholder) {
/* 100 */           string = string.replace(key, ((MetaPlaceholder)pl).apply(data, arg)); continue;
/* 101 */         }  if (pl instanceof EntityPlaceholder) {
/* 102 */           string = string.replace(key, ((EntityPlaceholder)pl).apply(target, arg));
/*     */         }
/* 104 */       } catch (NullPointerException ex) {
/* 105 */         MythicLogger.error("Failed to process placeholder " + key + " in xPlaceholderString{'" + string + "'}");
/*     */ 
/*     */         
/* 108 */         ex.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 112 */     return string;
/*     */   }
/*     */   
/*     */   protected PlaceholderManager getPlaceholderManager() {
/* 116 */     return MythicMobs.inst().getPlaceholderManager();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\PlaceholderParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */