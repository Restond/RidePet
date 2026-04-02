/*     */ package lumine.xikage.mythicmobs.skills.placeholders;
/*     */ 
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.Placeholder;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderParser;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.VariableScope;
/*     */ import io.lumine.xikage.mythicmobs.util.types.RandomInt;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.lang.WordUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.scoreboard.Objective;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlaceholderManager
/*     */   implements Terminable
/*     */ {
/*     */   protected final MythicMobs core;
/*  30 */   private static final Pattern VARIABLE_PATTERN = Pattern.compile("([<][^<>]+[>])");
/*     */   
/*  32 */   private List<PlaceholderParser> parsers = new ArrayList<>();
/*  33 */   private PlaceholderTree placeholders = new PlaceholderTree(this);
/*     */   
/*     */   private boolean initialized = false;
/*     */   
/*     */   public PlaceholderManager(MythicMobs core) {
/*  38 */     this.core = core;
/*     */     
/*  40 */     initialize();
/*     */   }
/*     */   
/*     */   public boolean terminate() {
/*  44 */     return true;
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  48 */     this.initialized = false;
/*  49 */     this.placeholders = new PlaceholderTree(this);
/*     */     
/*  51 */     register("echo", (Placeholder)Placeholder.meta((meta, arg) -> arg));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     register("caster.name", (Placeholder)Placeholder.meta((meta, arg) -> {
/*     */             SkillCaster caster = meta.getCaster();
/*     */ 
/*     */             
/*     */             if (caster instanceof ActiveMob) {
/*     */               if (((ActiveMob)caster).getDisplayName() != null) {
/*     */                 return ((ActiveMob)caster).getDisplayName();
/*     */               }
/*     */ 
/*     */               
/*     */               name = ((ActiveMob)caster).getEntity().getBukkitEntity().getType().getName();
/*     */               
/*     */               return WordUtils.capitalize(name);
/*     */             } 
/*     */             
/*     */             return caster.getEntity().isPlayer() ? caster.getEntity().asPlayer().getName() : caster.getEntity().getName();
/*     */           }));
/*     */     
/*  74 */     register("caster.uuid", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getCaster().getEntity().getUniqueId())));
/*  75 */     register("caster.l.w", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getCaster().getLocation().getWorld().getName())));
/*  76 */     register("caster.l.x", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getCaster().getLocation().getBlockX())));
/*  77 */     register("caster.l.y", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getCaster().getLocation().getBlockY())));
/*  78 */     register("caster.l.z", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getCaster().getLocation().getBlockZ())));
/*  79 */     register("caster.hp", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf((int)meta.getCaster().getEntity().getHealth())));
/*  80 */     register("caster.thp", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getCaster().getEntity().getHealth())));
/*  81 */     register("caster.mhp", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf((int)meta.getCaster().getEntity().getMaxHealth())));
/*  82 */     register("caster.php", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(100.0D * meta.getCaster().getEntity().getHealth() / meta.getCaster().getEntity().getMaxHealth())));
/*  83 */     register("caster.level", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getCaster().getLevel())));
/*  84 */     register("caster.stance", (Placeholder)Placeholder.meta((meta, arg) -> (meta.getCaster() instanceof ActiveMob) ? String.valueOf(((ActiveMob)meta.getCaster()).getStance()) : "None"));
/*  85 */     register("caster.var", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(this.core.getVariableManager().getRegistry(VariableScope.CASTER, (SkillMetadata)meta, null).getString(arg))));
/*  86 */     register("caster.luck", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getCaster().getEntity().getLuck())));
/*  87 */     register("caster.enchantlevel", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getCaster().getEntity().getEnchantmentLevel(arg))));
/*  88 */     register("caster.heldenchantlevel", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getCaster().getEntity().getEnchantmentLevelHeld(arg))));
/*  89 */     register("caster.score", (Placeholder)Placeholder.meta((meta, arg) -> {
/*     */             AbstractEntity target = meta.getCaster().getEntity();
/*     */             
/*     */             Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(arg);
/*     */             int score = 0;
/*     */             if (obj != null) {
/*     */               if (target.isPlayer()) {
/*     */                 score = obj.getScore(target.asPlayer().getName()).getScore();
/*     */               } else {
/*     */                 score = obj.getScore(target.getUniqueId().toString()).getScore();
/*     */               } 
/*     */             }
/*     */             return String.valueOf(score);
/*     */           }));
/* 103 */     register("caster.tt.top", (Placeholder)Placeholder.meta((meta, arg) -> {
/*     */             if (meta.getCaster() instanceof ActiveMob) {
/*     */               ActiveMob am = (ActiveMob)meta.getCaster();
/*     */ 
/*     */               
/*     */               if (am.hasThreatTable() && am.getThreatTable().inCombat()) {
/*     */                 return am.getThreatTable().getTopThreatHolder().getName();
/*     */               }
/*     */             } 
/*     */             
/*     */             return "Unknown";
/*     */           }));
/*     */     
/* 116 */     register("skill.var", (Placeholder)Placeholder.meta((meta, arg) -> {
/*     */             if (meta instanceof SkillMetadata) {
/*     */               SkillMetadata data = (SkillMetadata)meta;
/*     */ 
/*     */               
/*     */               return data.getVariables().getString(arg);
/*     */             } 
/*     */             
/*     */             return null;
/*     */           }));
/*     */     
/* 127 */     register("trigger.name", (Placeholder)Placeholder.meta((meta, arg) -> {
/*     */             AbstractEntity trigger = meta.getTrigger();
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             return (trigger == null) ? "Unknown" : (trigger.isPlayer() ? trigger.asPlayer().getName() : trigger.getName());
/*     */           }));
/*     */ 
/*     */ 
/*     */     
/* 138 */     register("trigger.uuid", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getTrigger().getUniqueId())));
/* 139 */     register("trigger.l.w", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getTrigger().getLocation().getWorld().getName())));
/* 140 */     register("trigger.l.x", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getTrigger().getLocation().getBlockX())));
/* 141 */     register("trigger.l.y", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getTrigger().getLocation().getBlockY())));
/* 142 */     register("trigger.l.z", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getTrigger().getLocation().getBlockZ())));
/* 143 */     register("trigger.hp", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf((int)meta.getTrigger().getHealth())));
/* 144 */     register("trigger.thp", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getTrigger().getHealth())));
/* 145 */     register("trigger.mhp", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf((int)meta.getTrigger().getMaxHealth())));
/* 146 */     register("trigger.php", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(100.0D * meta.getTrigger().getHealth() / meta.getTrigger().getMaxHealth())));
/* 147 */     register("trigger.var", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(this.core.getVariableManager().getRegistry(VariableScope.TARGET, meta.getTrigger()).getString(arg))));
/* 148 */     register("trigger.luck", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(meta.getTrigger().getLuck())));
/* 149 */     register("trigger.score", (Placeholder)Placeholder.meta((meta, arg) -> {
/*     */             AbstractEntity target = meta.getTrigger();
/*     */             
/*     */             Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(arg);
/*     */             int score = 0;
/*     */             if (obj != null) {
/*     */               if (target.isPlayer()) {
/*     */                 score = obj.getScore(target.asPlayer().getName()).getScore();
/*     */               } else {
/*     */                 score = obj.getScore(target.getUniqueId().toString()).getScore();
/*     */               } 
/*     */             }
/*     */             return String.valueOf(score);
/*     */           }));
/* 163 */     register("trigger.threat", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(((ActiveMob)meta.getCaster()).getThreatTable().getThreat(meta.getTrigger()))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     register("target.name", (Placeholder)Placeholder.entity((entity, arg) -> (entity == null) ? "Unknown" : (entity.isPlayer() ? entity.asPlayer().getName() : entity.getName())));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     register("target.uuid", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf(entity.getUniqueId())));
/* 182 */     register("target.l.w", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf(entity.getLocation().getWorld().getName())));
/* 183 */     register("target.l.x", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf(entity.getLocation().getBlockX())));
/* 184 */     register("target.l.y", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf(entity.getLocation().getBlockY())));
/* 185 */     register("target.l.z", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf(entity.getLocation().getBlockZ())));
/* 186 */     register("target.hp", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf((int)entity.getHealth())));
/* 187 */     register("target.thp", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf(entity.getHealth())));
/* 188 */     register("target.mhp", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf((int)entity.getMaxHealth())));
/* 189 */     register("target.php", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf(100.0D * entity.getHealth() / entity.getMaxHealth())));
/* 190 */     register("target.var", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf(this.core.getVariableManager().getRegistry(VariableScope.TARGET, entity).getString(arg))));
/* 191 */     register("target.luck", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf(entity.getLuck())));
/* 192 */     register("target.score", (Placeholder)Placeholder.entity((entity, arg) -> {
/*     */             AbstractEntity target = entity;
/*     */             
/*     */             Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(arg);
/*     */             
/*     */             int score = 0;
/*     */             
/*     */             if (obj != null) {
/*     */               if (target.isPlayer()) {
/*     */                 score = obj.getScore(target.asPlayer().getName()).getScore();
/*     */               } else {
/*     */                 score = obj.getScore(target.getUniqueId().toString()).getScore();
/*     */               } 
/*     */             }
/*     */             
/*     */             return String.valueOf(score);
/*     */           }));
/*     */     
/* 210 */     register("random", (Placeholder)Placeholder.entity((entity, arg) -> String.valueOf((new RandomInt(arg)).get())));
/*     */     
/* 212 */     register("score", (Placeholder)Placeholder.entity((entity, arg) -> {
/*     */             String[] split = arg.split("\\.");
/*     */             
/*     */             if (split.length < 2) {
/*     */               return null;
/*     */             }
/*     */             
/*     */             String objective = split[0];
/*     */             
/*     */             String entry = split[1];
/*     */             
/*     */             Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objective);
/*     */             
/*     */             int score = 0;
/*     */             if (obj != null) {
/*     */               score = obj.getScore(entry).getScore();
/*     */             }
/*     */             return String.valueOf(score);
/*     */           }));
/* 231 */     register("global.var", (Placeholder)Placeholder.meta((meta, arg) -> String.valueOf(this.core.getVariableManager().getRegistry(VariableScope.GLOBAL, null, null).getString(arg))));
/* 232 */     register("global.score", (Placeholder)Placeholder.entity((entity, arg) -> {
/*     */             Objective obj = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(arg);
/*     */             
/*     */             int score = 0;
/*     */             if (obj != null) {
/*     */               score = obj.getScore("__GLOBAL__").getScore();
/*     */             }
/*     */             return String.valueOf(score);
/*     */           }));
/* 241 */     this.initialized = true;
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
/*     */   public void register(String key, Placeholder transformer) {
/* 262 */     String[] split = key.split("\\.");
/*     */     
/* 264 */     PlaceholderTree ref = this.placeholders;
/*     */     
/* 266 */     for (int i = 0; i <= split.length; i++) {
/* 267 */       if (i == split.length) {
/* 268 */         ref.setPlaceholder(transformer);
/*     */       } else {
/* 270 */         if (!ref.getSubPlaceholders().containsKey(split[i])) {
/* 271 */           ref.getSubPlaceholders().put(split[i], new PlaceholderTree(this));
/*     */         }
/* 273 */         ref = (PlaceholderTree)ref.getSubPlaceholders().get(split[i]);
/*     */       } 
/*     */     } 
/*     */     
/* 277 */     if (this.initialized) {
/* 278 */       recheckForPlaceholders();
/*     */     }
/*     */   }
/*     */   
/*     */   public PlaceholderEntry getPlaceholder(String key) {
/* 283 */     if (key.startsWith("&")) return null;
/*     */     
/* 285 */     String[] split = key.split("\\.");
/*     */     
/* 287 */     PlaceholderTree ref = this.placeholders;
/* 288 */     String arg = null;
/*     */     
/* 290 */     for (int i = 0; i < split.length; i++) {
/* 291 */       if (ref.getSubPlaceholders().containsKey(split[i])) {
/* 292 */         ref = (PlaceholderTree)ref.getSubPlaceholders().get(split[i]);
/*     */       } else {
/* 294 */         arg = (arg == null) ? split[i] : (arg + "." + split[i]);
/*     */       } 
/*     */     } 
/* 297 */     return new PlaceholderEntry(this, ref.getPlaceholder(), arg);
/*     */   }
/*     */   
/*     */   public boolean checkForVariables(String string) {
/* 301 */     return VARIABLE_PATTERN.matcher(string).find();
/*     */   }
/*     */   
/*     */   public Matcher matcher(String string) {
/* 305 */     return VARIABLE_PATTERN.matcher(string);
/*     */   }
/*     */   
/*     */   public void registerParser(PlaceholderParser placeholderParser) {
/* 309 */     this.parsers.add(placeholderParser);
/*     */   }
/*     */   
/*     */   public void recheckForPlaceholders() {
/* 313 */     this.parsers.forEach(parser -> parser.checkForVariables());
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\PlaceholderManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */