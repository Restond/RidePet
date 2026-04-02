/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.IMetaSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.CastMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.CustomMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.SudoSkillMechanic;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkillMechanic
/*     */   extends AbstractSkill
/*     */ {
/*     */   protected final MythicLineConfig config;
/*  33 */   protected int interval = 0;
/*  34 */   protected long clock = 0L;
/*     */   
/*     */   protected String line;
/*     */   protected boolean forceSync = false;
/*     */   protected boolean targetIsOrigin = false;
/*     */   protected Optional<AbstractLocation> originOverride;
/*     */   
/*     */   public SkillMechanic(String skill, MythicLineConfig mlc, int interval) {
/*  42 */     this.config = mlc;
/*  43 */     this.interval = interval + ConfigManager.ClockInterval - interval % ConfigManager.ClockInterval;
/*     */     
/*  45 */     init(skill, mlc);
/*     */   }
/*     */   
/*     */   public SkillMechanic(String skill, MythicLineConfig mlc) {
/*  49 */     this.config = mlc;
/*  50 */     init(skill, mlc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(String skill, MythicLineConfig mlc) {
/*  57 */     this.line = skill;
/*  58 */     this.cooldown = mlc.getInteger("cooldown", 0);
/*  59 */     this.cooldown = (float)mlc.getDouble("cd", this.cooldown);
/*     */     
/*  61 */     this.delay = mlc.getInteger("delay", 0);
/*     */     
/*  63 */     this.repeat = mlc.getInteger("repeat", 0);
/*  64 */     this.repeatInterval = mlc.getInteger(new String[] { "repeatinterval", "repeati" }, 0);
/*     */     
/*  66 */     this.power = mlc.getFloat("power", 1.0F);
/*  67 */     this.powerSplitBetweenTargets = mlc.getBoolean(new String[] { "powersplitbetweentargets", "powersplit", "splitpower" }, false);
/*     */     
/*  69 */     this.forceSync = mlc.getBoolean(new String[] { "forcesync", "sync" }, false);
/*     */     
/*  71 */     this.targetIsOrigin = mlc.getBoolean("targetisorigin", false);
/*     */     
/*  73 */     this.sourceIsOrigin = mlc.getBoolean(new String[] { "sourceisorigin", "castfromorigin", "fromorigin", "fo" }, false);
/*     */     
/*  75 */     String originOverride = mlc.getString("origin", null);
/*     */     
/*  77 */     if (originOverride != null) {
/*  78 */       String[] arrayOfString = originOverride.split(",");
/*     */       
/*  80 */       int x = Integer.valueOf(arrayOfString[0]).intValue();
/*  81 */       int y = Integer.valueOf(arrayOfString[1]).intValue();
/*  82 */       int z = Integer.valueOf(arrayOfString[2]).intValue();
/*     */       
/*  84 */       AbstractLocation o = new AbstractLocation(null, x, y, z);
/*     */       
/*  86 */       this.originOverride = Optional.of(o);
/*     */     } else {
/*  88 */       this.originOverride = Optional.empty();
/*     */     } 
/*     */     
/*  91 */     this.target_creative = mlc.getBoolean("targetcreative", this.target_creative);
/*     */     
/*  93 */     String[] split = skill.split(" ");
/*     */     
/*  95 */     for (int i = 1; i < split.length; i++) {
/*  96 */       if (split[i].startsWith("@")) {
/*  97 */         this.targeter = Optional.of(parseSkillTargeter(split[i]));
/*     */       }
/*  99 */       else if (split[i].startsWith("~")) {
/* 100 */         this.trigger = parseSkillTrigger(split[i]);
/*     */       }
/* 102 */       else if (split[i].startsWith("=") || split[i].startsWith(">") || split[i].startsWith("<")) {
/* 103 */         this.healthMod = split[i];
/*     */       }
/* 105 */       else if (split[i].startsWith("?")) {
/* 106 */         if (this.conditions == null) {
/* 107 */           this.conditions = new ArrayList();
/*     */         }
/* 109 */         this.conditions.add(parseSkillCondition(split[i]));
/*     */       }
/* 111 */       else if (split[i].matches("[0-9]*[.]?[0-9]+")) {
/* 112 */         this.chance = Float.parseFloat(split[i]);
/*     */       } 
/*     */     } 
/* 115 */     if (this.trigger == null) this.trigger = SkillTrigger.COMBAT; 
/*     */   }
/*     */   
/*     */   public String getConfigLine() {
/* 119 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setAsyncSafe(boolean bool) {
/* 123 */     this.ASYNC_SAFE = bool;
/*     */   }
/*     */   
/*     */   public boolean isAsyncSafe() {
/* 127 */     return this.ASYNC_SAFE;
/*     */   }
/*     */   
/*     */   public void setTimerInterval(int interval) {
/* 131 */     this.interval = interval;
/*     */   }
/*     */   
/*     */   public int getTimerInterval() {
/* 135 */     return this.interval;
/*     */   }
/*     */   
/*     */   public void resetClock() {
/* 139 */     this.clock = 0L;
/*     */   }
/*     */   
/*     */   public void tickClock() {
/* 143 */     this.clock += ConfigManager.ClockInterval;
/*     */   }
/*     */   
/*     */   public long getClock() {
/* 147 */     return this.clock;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUsableFromSkill(SkillMetadata meta) {
/* 152 */     SkillCaster skillCaster = meta.getCaster();
/*     */     
/* 154 */     if (onCooldown(skillCaster) == true) {
/* 155 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Cooldown check failed.", new Object[0]);
/* 156 */       return false;
/*     */     } 
/* 158 */     if (skillCaster instanceof ActiveMob && !checkHealth(skillCaster)) {
/* 159 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Health check failed.", new Object[0]);
/* 160 */       return false;
/*     */     } 
/* 162 */     if (!rollChance()) {
/* 163 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Roll failed.", new Object[0]);
/* 164 */       return false;
/*     */     } 
/* 166 */     if (this.conditionsTrigger != null) {
/* 167 */       for (SkillCondition mc : this.conditionsTrigger) {
/* 168 */         if (!mc.evaluateTrigger(meta)) {
/* 169 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: TriggerConditions failed", new Object[0]);
/* 170 */           return false;
/*     */         } 
/*     */       } 
/*     */     }
/* 174 */     if (this.conditions != null) {
/* 175 */       for (SkillCondition mc : this.conditions) {
/* 176 */         if (!mc.evaluateCaster(meta)) {
/* 177 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Conditions failed", new Object[0]);
/* 178 */           return false;
/*     */         } 
/*     */       } 
/*     */     }
/* 182 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "+ SkillMechanic usable!", new Object[0]);
/*     */     
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUsableFromCaster(SkillMetadata meta) {
/* 189 */     SkillCaster skillCaster = meta.getCaster();
/*     */ 
/*     */     
/* 192 */     if (!checkSkillTrigger(meta)) {
/* 193 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: SkillTrigger check failed.", new Object[0]);
/* 194 */       return false;
/*     */     } 
/* 196 */     if (onCooldown(skillCaster) == true) {
/* 197 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Cooldown check failed.", new Object[0]);
/* 198 */       return false;
/*     */     } 
/* 200 */     if (skillCaster instanceof ActiveMob && !checkHealth(skillCaster)) {
/* 201 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Health check failed.", new Object[0]);
/* 202 */       return false;
/*     */     } 
/* 204 */     if (!rollChance()) {
/* 205 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Roll failed.", new Object[0]);
/* 206 */       return false;
/*     */     } 
/*     */     
/* 209 */     if (this.conditionsTrigger != null) {
/* 210 */       for (SkillCondition mc : this.conditionsTrigger) {
/* 211 */         if (!mc.evaluateTrigger(meta)) {
/* 212 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: TriggerConditions failed", new Object[0]);
/* 213 */           return false;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 218 */     if (this.conditions != null) {
/* 219 */       for (SkillCondition mc : this.conditions) {
/* 220 */         if (!mc.evaluateCaster(meta)) {
/* 221 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Conditions failed", new Object[0]);
/* 222 */           return false;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 227 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "+ SkillMechanic usable!", new Object[0]);
/*     */     
/* 229 */     return true;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean usable(SkillMetadata meta) {
/* 234 */     return isUsableFromCaster(meta);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean usable(SkillCaster skillCaster, SkillTrigger trigger) {
/* 239 */     if (!checkSkillTrigger(trigger)) {
/* 240 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: SkillTrigger check failed.", new Object[0]);
/* 241 */       return false;
/*     */     } 
/* 243 */     if (onCooldown(skillCaster) == true) {
/* 244 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Cooldown check failed.", new Object[0]);
/* 245 */       return false;
/*     */     } 
/* 247 */     if (skillCaster instanceof ActiveMob && !checkHealth(skillCaster)) {
/* 248 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Health check failed.", new Object[0]);
/* 249 */       return false;
/*     */     } 
/* 251 */     if (!rollChance()) {
/* 252 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Roll failed.", new Object[0]);
/* 253 */       return false;
/*     */     } 
/* 255 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "+ SkillMechanic usable!", new Object[0]);
/*     */     
/* 257 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean usable(SkillCaster am) {
/* 264 */     if (!rollChance()) {
/* 265 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Roll failed.", new Object[0]);
/* 266 */       return false;
/*     */     } 
/* 268 */     if (onCooldown(am) == true) {
/* 269 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Cooldown check failed.", new Object[0]);
/* 270 */       return false;
/*     */     } 
/* 272 */     if (am instanceof ActiveMob && !checkHealth(am)) {
/* 273 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! SkillMechanic not usable: Health check failed.", new Object[0]);
/* 274 */       return false;
/*     */     } 
/* 276 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "+ SkillMechanic usable!", new Object[0]);
/*     */     
/* 278 */     return true;
/*     */   }
/*     */   
/*     */   public boolean execute(SkillMetadata oData) {
/* 282 */     SkillMetadata data = oData.deepClone();
/*     */     
/* 284 */     if (this.originOverride.isPresent()) {
/* 285 */       AbstractLocation o = this.originOverride.get();
/* 286 */       data.setOrigin(new AbstractLocation(data.getCaster().getLocation().getWorld(), o.getX(), o.getY(), o.getZ()));
/*     */     } 
/*     */     
/* 289 */     if (this.repeat > 0 && this.repeatInterval > 0) {
/*     */       
/* 291 */       io.lumine.xikage.mythicmobs.skills.SkillMechanic skillMechanic = this;
/* 292 */       int repeatz = this.repeat;
/*     */       
/* 294 */       new Object(this, repeatz, skillMechanic, data);
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
/* 307 */       return true;
/* 308 */     }  if (this.delay == 0) {
/* 309 */       return executeSkills(data);
/*     */     }
/* 311 */     io.lumine.xikage.mythicmobs.skills.SkillMechanic skill = this;
/* 312 */     Schedulers.sync().runLater(() -> paramSkillMechanic.executeSkills(paramSkillMetadata), this.delay);
/*     */ 
/*     */     
/* 315 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean executeSkills(SkillTrigger skilltrigger, ActiveMob am, AbstractEntity trigger, AbstractLocation origin, HashSet<AbstractEntity> eTargets, HashSet<AbstractLocation> lTargets, float power) {
/* 320 */     SkillMetadata data = new SkillMetadata(skilltrigger, (SkillCaster)am, trigger, origin, eTargets, lTargets, power);
/*     */     
/* 322 */     return executeSkills(data);
/*     */   }
/*     */   
/*     */   public boolean executeSkills(SkillMetadata data) {
/*     */     io.lumine.xikage.mythicmobs.skills.SkillMechanic mechanic;
/* 327 */     if (data.getOrigin() == null) {
/* 328 */       data.setOrigin(data.getCaster().getEntity().getLocation());
/*     */     }
/* 330 */     data.setPower(data.getPower() * this.power);
/*     */     
/* 332 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "Executing SkillMechanic with power {0} (line: {1})", new Object[] { Float.valueOf(data.getPower()), this.line });
/*     */     
/* 334 */     if (this instanceof SudoSkillMechanic) {
/* 335 */       ((SudoSkillMechanic)this).cast(data);
/* 336 */       setCooldown(data.getCaster(), this.cooldown);
/* 337 */       return true;
/*     */     } 
/*     */     
/* 340 */     evaluateTargets(data);
/*     */ 
/*     */     
/* 343 */     if (this instanceof CustomMechanic && ((CustomMechanic)this).getMechanic().isPresent()) {
/* 344 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": SkillMechanic is a CUSTOM mechanic", new Object[0]);
/* 345 */       mechanic = ((CustomMechanic)this).getMechanic().get();
/*     */     } else {
/* 347 */       mechanic = this;
/*     */     } 
/* 349 */     if (mechanic instanceof IMetaSkill) {
/* 350 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": SkillMechanic is a META mechanic. Executing...", new Object[0]);
/* 351 */       ((IMetaSkill)mechanic).cast(data);
/* 352 */       setCooldown(data.getCaster(), this.cooldown);
/* 353 */       return true;
/*     */     } 
/* 355 */     if (mechanic instanceof ITargetedEntitySkill && mechanic instanceof ITargetedLocationSkill) {
/* 356 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": SkillMechanic accepts multiple types...", new Object[0]);
/*     */       
/* 358 */       if (this.targeter.isPresent() && 
/* 359 */         this.targeter.get() instanceof io.lumine.xikage.mythicmobs.skills.targeters.IPathSelector) {
/* 360 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Targeter is PATH SELECTOR", new Object[0]);
/* 361 */         if (this.targeter.get() instanceof io.lumine.xikage.mythicmobs.skills.targeters.MPEntity) {
/* 362 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": SkillMechanic set as ENTITY skill. Executing...", new Object[0]);
/* 363 */           executeTargetedEntitySkill(mechanic, data);
/* 364 */           setCooldown(data.getCaster(), this.cooldown);
/* 365 */           return true;
/*     */         } 
/* 367 */         if (this.targeter.get() instanceof io.lumine.xikage.mythicmobs.skills.targeters.MPLocation) {
/* 368 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": SkillMechanic set as LOCATION skill. Executing...", new Object[0]);
/* 369 */           executeTargetedLocationSkill(mechanic, data);
/* 370 */           setCooldown(data.getCaster(), this.cooldown);
/* 371 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 377 */     if (mechanic instanceof ITargetedEntitySkill && data.getEntityTargets() != null && data.getEntityTargets().size() > 0) {
/* 378 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": SkillMechanic is an ENTITY skill. Executing...", new Object[0]);
/*     */       
/* 380 */       if (data.getIsAsync() && !this.ASYNC_SAFE) {
/* 381 */         Scheduler.runSync(() -> executeTargetedEntitySkill(paramSkillMechanic, paramSkillMetadata));
/*     */       } else {
/* 383 */         executeTargetedEntitySkill(mechanic, data);
/*     */       } 
/*     */       
/* 386 */       setCooldown(data.getCaster(), this.cooldown);
/* 387 */       return true;
/* 388 */     }  if (mechanic instanceof ITargetedLocationSkill) {
/* 389 */       if (data.getLocationTargets() != null && data.getLocationTargets().size() > 0) {
/* 390 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": Executing SkillMechanic as LOCATION skill", new Object[0]);
/*     */         
/* 392 */         if (data.getIsAsync() && !this.ASYNC_SAFE) {
/* 393 */           Scheduler.runSync(() -> executeTargetedLocationSkill(paramSkillMechanic, paramSkillMetadata));
/*     */         } else {
/* 395 */           executeTargetedLocationSkill(mechanic, data);
/*     */         } 
/* 397 */         setCooldown(data.getCaster(), this.cooldown);
/* 398 */         return true;
/* 399 */       }  if (data.getEntityTargets() != null && data.getEntityTargets().size() > 0) {
/* 400 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": Executing SkillMechanic as ENTITY_LOCATION skill", new Object[0]);
/*     */         
/* 402 */         if (data.getIsAsync() && !this.ASYNC_SAFE) {
/* 403 */           Scheduler.runSync(() -> executeTargetedLocationSkill(paramSkillMechanic, paramSkillMetadata));
/*     */         } else {
/* 405 */           executeTargetedLocationSkill(mechanic, data);
/*     */         } 
/* 407 */         setCooldown(data.getCaster(), this.cooldown);
/* 408 */         return true;
/*     */       } 
/*     */     } 
/* 411 */     if (mechanic instanceof INoTargetSkill) {
/* 412 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": SkillMechanic runnable with no targets. Executing...", new Object[0]);
/* 413 */       if (data.getIsAsync() && !this.ASYNC_SAFE) {
/* 414 */         Scheduler.runSync(() -> executeNoTargetSkill(paramSkillMechanic, paramSkillMetadata));
/*     */       } else {
/* 416 */         executeNoTargetSkill(mechanic, data);
/*     */       } 
/*     */       
/* 419 */       setCooldown(data.getCaster(), this.cooldown);
/* 420 */       return true;
/* 421 */     }  if (mechanic instanceof CastMechanic) {
/* 422 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "- SkillMechanic is a CAST mechanic. Executing NoTargets fallback.", new Object[0]);
/* 423 */       ((CastMechanic)mechanic).failNoTargets(data);
/*     */     } 
/* 425 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "! No targets available. Cancelling.", new Object[0]);
/* 426 */     return false;
/*     */   }
/*     */   
/*     */   protected static void executeTargetedEntitySkill(io.lumine.xikage.mythicmobs.skills.SkillMechanic mechanic, SkillMetadata data) {
/* 430 */     data.getEntityTargets().forEach(target -> {
/*     */           if (paramSkillMechanic.targetIsOrigin == true) {
/*     */             ((ITargetedEntitySkill)paramSkillMechanic).castAtEntity(paramSkillMetadata.deepClone().setOrigin(target.getLocation()), target);
/*     */           } else {
/*     */             ((ITargetedEntitySkill)paramSkillMechanic).castAtEntity(paramSkillMetadata, target);
/*     */           } 
/*     */         });
/*     */   }
/*     */   protected static void executeTargetedLocationSkill(io.lumine.xikage.mythicmobs.skills.SkillMechanic mechanic, SkillMetadata data) {
/* 439 */     data.getLocationTargets().forEach(target -> {
/*     */           if (paramSkillMechanic.targetIsOrigin == true) {
/*     */             ((ITargetedLocationSkill)paramSkillMechanic).castAtLocation(paramSkillMetadata.deepClone().setOrigin(target), target);
/*     */           } else {
/*     */             ((ITargetedLocationSkill)paramSkillMechanic).castAtLocation(paramSkillMetadata, target);
/*     */           } 
/*     */         });
/*     */   }
/*     */   protected static void executeNoTargetSkill(io.lumine.xikage.mythicmobs.skills.SkillMechanic mechanic, SkillMetadata data) {
/* 448 */     ((INoTargetSkill)mechanic).cast(data);
/*     */   }
/*     */   
/*     */   public boolean getRunAsync() {
/* 452 */     return !this.forceSync;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\SkillMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */