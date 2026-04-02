/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.CustomTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.OriginTargeter;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public abstract class AbstractSkill
/*     */ {
/*     */   public static MythicMobs getPlugin() {
/*  29 */     return MythicMobs.inst();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean ASYNC_SAFE = true;
/*  34 */   public static long cooldownTimer = 0L;
/*     */   
/*     */   protected UUID uuid;
/*     */   
/*  38 */   protected float cooldown = 0.0F;
/*  39 */   protected float power = 0.0F;
/*  40 */   protected float powerScale = 1.0F;
/*  41 */   protected int delay = 0;
/*  42 */   protected int repeat = 0; protected int repeatInterval = 0;
/*     */   
/*  44 */   protected float chance = 1.0F;
/*  45 */   protected String healthMod = null;
/*     */   
/*     */   protected boolean powerSplitBetweenTargets = false;
/*  48 */   protected SkillTrigger trigger = SkillTrigger.COMBAT; public SkillTrigger getTrigger() { return this.trigger; }
/*     */ 
/*     */   
/*     */   protected boolean sourceIsOrigin = false;
/*  52 */   protected HashMap<UUID, Long> cooldowns = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   protected Optional<SkillTargeter> targeter = Optional.empty(); public Optional<SkillTargeter> getTargeter() { return this.targeter; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean target_creative = false;
/*     */   
/*  63 */   protected List<SkillCondition> conditions = null; public List<SkillCondition> getConditions() { return this.conditions; }
/*  64 */    protected List<SkillCondition> conditionsTarget = null; public List<SkillCondition> getConditionsTarget() { return this.conditionsTarget; }
/*  65 */    protected List<SkillCondition> conditionsTrigger = null; public List<SkillCondition> getConditionsTrigger() { return this.conditionsTrigger; }
/*     */   
/*     */   public AbstractSkill() {
/*  68 */     this.uuid = UUID.randomUUID();
/*     */   }
/*     */   
/*     */   public boolean onCooldown(SkillCaster skillCaster) {
/*  72 */     if (this.cooldown == 0.0F) return false; 
/*  73 */     long next = ((Long)this.cooldowns.getOrDefault(skillCaster.getEntity().getUniqueId(), Long.valueOf(0L))).longValue();
/*  74 */     if (next >= cooldownTimer) {
/*  75 */       return true;
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public float getCooldown(SkillCaster skillCaster) {
/*  81 */     if (this.cooldown == 0.0F) return 0.0F; 
/*  82 */     long next = ((Long)this.cooldowns.getOrDefault(skillCaster.getEntity().getUniqueId(), Long.valueOf(0L))).longValue();
/*  83 */     if (next < cooldownTimer) {
/*  84 */       return 0.0F;
/*     */     }
/*  86 */     return (float)((next - cooldownTimer) / 20L);
/*     */   }
/*     */   
/*     */   public void setCooldown(SkillCaster skillCaster, float cooldown) {
/*  90 */     if (cooldown > 0.0F) {
/*  91 */       this.cooldowns.put(skillCaster.getEntity().getUniqueId(), Long.valueOf((long)((float)cooldownTimer + cooldown * 20.0F - 2.0F)));
/*     */     } else {
/*  93 */       this.cooldowns.remove(skillCaster.getEntity().getUniqueId());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean rollChance() {
/*  98 */     if (this.chance == 1.0F)
/*  99 */       return true; 
/* 100 */     if (MythicMobs.r.nextFloat() > this.chance) {
/* 101 */       return false;
/*     */     }
/* 103 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean checkHealth(SkillCaster skillCaster) {
/* 107 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "? Performing Health Check", new Object[0]);
/* 108 */     if (this.healthMod == null) return true;
/*     */     
/* 110 */     String[] healthparts = this.healthMod.split(",");
/*     */     
/* 112 */     for (String health : healthparts) {
/*     */       double d1, bosshp;
/*     */       
/* 115 */       String healthT = health;
/* 116 */       String skillstr = skillCaster.getEntity().getUniqueId() + this.uuid.toString() + "#" + health;
/*     */       
/* 118 */       healthT = healthT.replace("%", "");
/* 119 */       healthT = healthT.replace("<", "");
/* 120 */       healthT = healthT.replace(">", "");
/* 121 */       healthT = healthT.replace("=", "");
/* 122 */       healthT = healthT.replace("-", "");
/*     */       
/* 124 */       if (healthT.matches("[-+]?[0-9]*.?[0-9]+")) {
/* 125 */         d1 = Double.parseDouble(healthT);
/*     */       } else {
/* 127 */         return true;
/*     */       } 
/*     */       
/* 130 */       if (health.endsWith("%")) {
/* 131 */         bosshp = skillCaster.getEntity().getHealth() / skillCaster.getEntity().getMaxHealth();
/* 132 */         d1 = Double.parseDouble(healthT) / 100.0D;
/*     */       } else {
/* 134 */         bosshp = skillCaster.getEntity().getHealth() / skillCaster.getEntity().getMaxHealth();
/* 135 */         d1 = Double.parseDouble(healthT) / skillCaster.getEntity().getMaxHealth();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 142 */       if (health.startsWith(">")) {
/*     */         
/* 144 */         health = health.replace(">", "");
/*     */         
/* 146 */         if (bosshp > d1) {
/* 147 */           return true;
/*     */         
/*     */         }
/*     */       }
/* 151 */       else if (health.startsWith("<")) {
/*     */         
/* 153 */         health = health.replace("<", "");
/*     */         
/* 155 */         if (bosshp < d1) {
/* 156 */           return true;
/*     */         
/*     */         }
/*     */       }
/* 160 */       else if (health.startsWith("=")) {
/*     */ 
/*     */         
/* 163 */         health = health.replace("=", "");
/*     */         
/* 165 */         if (health.contains("-")) {
/*     */           double hp1, hp2;
/* 167 */           String[] hps = health.split("-");
/*     */ 
/*     */ 
/*     */           
/* 171 */           if (hps[0].endsWith("%")) {
/* 172 */             hp1 = Double.parseDouble(hps[0].substring(0, hps[0].length() - 1)) / 100.0D;
/*     */           } else {
/* 174 */             hp1 = Double.parseDouble(hps[0].substring(0, hps[0].length())) / skillCaster.getEntity().getMaxHealth();
/*     */           } 
/* 176 */           if (hps[1].endsWith("%")) {
/* 177 */             hp2 = Double.parseDouble(hps[1].substring(0, hps[1].length() - 1)) / 100.0D;
/*     */           } else {
/* 179 */             hp2 = Double.parseDouble(hps[1].substring(0, hps[1].length())) / skillCaster.getEntity().getMaxHealth();
/*     */           } 
/* 181 */           if (hp2 > hp1) {
/* 182 */             double hp3 = hp1;
/* 183 */             hp1 = hp2;
/* 184 */             hp2 = hp3;
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 189 */           if (bosshp > hp2 && bosshp < hp1) {
/* 190 */             return true;
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 195 */         else if (bosshp <= d1 && skillCaster.getEntity().getHealth() > d1 && !SkillHelper.hasUsedSkill(skillstr, skillCaster.getEntity())) {
/* 196 */           if (skillCaster instanceof ActiveMob && !((ActiveMob)skillCaster).getType().getRepeatAllSkills().booleanValue()) {
/* 197 */             MythicMobs.inst().getMobManager(); MobManager.setMetaData(skillCaster.getEntity(), skillstr, skillstr);
/*     */           } 
/* 199 */           return true;
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 206 */         return true;
/*     */       } 
/*     */     } 
/* 209 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean checkSkillTrigger(SkillTrigger trigger) {
/* 213 */     if (trigger == null) {
/* 214 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "+ Provided SkillTrigger is null", new Object[0]);
/* 215 */       return true;
/*     */     } 
/* 217 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "? Checking SkillTrigger {0} == {1}", new Object[] { trigger, this.trigger });
/* 218 */     if (trigger.equals(SkillTrigger.SIGNAL)) return true; 
/* 219 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$SkillTrigger[this.trigger.ordinal()]) {
/*     */       case 1:
/* 221 */         return true;
/*     */       case 2:
/* 223 */         return true;
/*     */       case 3:
/* 225 */         return true;
/*     */       case 4:
/* 227 */         return (trigger == SkillTrigger.DAMAGED || trigger == SkillTrigger.ATTACK);
/*     */       case 5:
/* 229 */         return (trigger == SkillTrigger.KILL || trigger == SkillTrigger.KILLPLAYER);
/*     */       case 6:
/* 231 */         return (trigger == SkillTrigger.DAMAGED || trigger == SkillTrigger.ATTACK || trigger == SkillTrigger.SPAWN || trigger == SkillTrigger.DEATH || trigger == SkillTrigger.TIMER);
/*     */     } 
/* 233 */     return this.trigger.equals(trigger);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean checkSkillTrigger(SkillMetadata meta) {
/* 238 */     SkillTrigger trigger = meta.getCause();
/* 239 */     if (trigger == null) {
/* 240 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "+ Provided SkillTrigger is null", new Object[0]);
/* 241 */       return true;
/*     */     } 
/* 243 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "? Checking SkillTrigger {0} == {1}", new Object[] { trigger, this.trigger });
/* 244 */     if (trigger.equals(SkillTrigger.SIGNAL)) return true; 
/* 245 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$SkillTrigger[this.trigger.ordinal()]) {
/*     */       case 1:
/* 247 */         return true;
/*     */       case 2:
/* 249 */         return true;
/*     */       case 3:
/* 251 */         return true;
/*     */       case 4:
/* 253 */         return (trigger == SkillTrigger.DAMAGED || trigger == SkillTrigger.ATTACK);
/*     */       case 5:
/* 255 */         return (trigger == SkillTrigger.KILL || trigger == SkillTrigger.KILLPLAYER);
/*     */       case 6:
/* 257 */         return (trigger == SkillTrigger.DAMAGED || trigger == SkillTrigger.ATTACK || trigger == SkillTrigger.SPAWN || trigger == SkillTrigger.DEATH || trigger == SkillTrigger.TIMER);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 262 */     return this.trigger.equals(trigger);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SkillTrigger parseSkillTrigger(String strTrigger) {
/* 268 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, ": Parsing SkillTrigger {0}", new Object[] { strTrigger });
/*     */     
/* 270 */     String search = strTrigger.substring(3).toUpperCase();
/*     */     
/* 272 */     if (search.contains("TIMER")) return SkillTrigger.TIMER;
/*     */     
/* 274 */     switch (search) {
/*     */       case "SPAWN":
/* 276 */         return SkillTrigger.SPAWN;
/*     */       case "DEATH":
/* 278 */         return SkillTrigger.DEATH;
/*     */       case "DESPAWN": case "DESPAWNED":
/* 280 */         return SkillTrigger.DESPAWNED;
/*     */       case "COMBAT":
/* 282 */         return SkillTrigger.COMBAT;
/*     */       case "DAMAGED": case "HURT":
/* 284 */         return SkillTrigger.DAMAGED;
/*     */       case "ATTACK": case "HIT":
/* 286 */         return SkillTrigger.ATTACK;
/*     */       case "EXPLODE":
/* 288 */         return SkillTrigger.EXPLODE;
/*     */       case "TELEPORT":
/* 290 */         return SkillTrigger.TELEPORT;
/*     */       case "KILL":
/* 292 */         return SkillTrigger.KILL;
/*     */       case "KILLPLAYER": case "PLAYERKILL":
/* 294 */         return SkillTrigger.KILLPLAYER;
/*     */       case "PLAYERDEATH": case "PLAYERDIE":
/* 296 */         return SkillTrigger.PLAYERDEATH;
/*     */       case "ENTERCOMBAT":
/* 298 */         return SkillTrigger.ENTERCOMBAT;
/*     */       case "COMBATDROP": case "DROPCOMBAT": case "LEAVECOMBAT":
/* 300 */         return SkillTrigger.DROPCOMBAT;
/*     */       case "CHANGETARGET": case "TARGETCHANGE":
/* 302 */         return SkillTrigger.TARGETCHANGE;
/*     */       case "INTERACT":
/* 304 */         return SkillTrigger.INTERACT;
/*     */       case "READY": case "FIRSTSPAWN":
/* 306 */         return SkillTrigger.READY;
/*     */       case "SWING":
/*     */       case "LEFTCLICK":
/* 309 */         return SkillTrigger.SWING;
/*     */       case "RIGHTCLICK":
/* 311 */         return SkillTrigger.RIGHTCLICK;
/*     */       case "USE":
/* 313 */         return SkillTrigger.USE;
/*     */       case "SHOOT": case "BOWSHOOT": case "SHOOTBOW":
/* 315 */         return SkillTrigger.SHOOT;
/*     */       case "BOWHIT":
/* 317 */         return SkillTrigger.BOW_HIT;
/*     */       case "CONSUME": case "EAT":
/* 319 */         return SkillTrigger.CONSUME;
/*     */       case "POTIONSPLASH": case "SPLASHPOTION":
/* 321 */         return SkillTrigger.SPLASH_POTION;
/*     */       case "CROUCH":
/* 323 */         return SkillTrigger.CROUCH;
/*     */       case "UNCROUCH":
/* 325 */         return SkillTrigger.UNCROUCH;
/*     */       case "BLOCK":
/* 327 */         return SkillTrigger.BLOCK;
/*     */       case "BLOCKBREAK": case "BREAKBLOCK":
/* 329 */         return SkillTrigger.BLOCK_BREAK;
/*     */       case "BLOCKPLACE": case "PLACEBLOCK":
/* 331 */         return SkillTrigger.BLOCK_PLACE;
/*     */       case "FISH": case "FISHING": case "FISHINGCAST":
/* 333 */         return SkillTrigger.FISH;
/*     */       case "FISHBITE": case "FISHINGBITE":
/* 335 */         return SkillTrigger.FISH_BITE;
/*     */       case "FISHCATCH": case "FISHCAUGHT": case "FISHINGCATCH": case "FISHINGCAUGHT": case "CATCHFISH": case "CAUGHTFISH":
/* 337 */         return SkillTrigger.FISH_CATCH_FISH;
/*     */       case "FISHGRAB": case "FISHINGGRAB": case "FISHENTITY": case "FISHINGENTITY":
/* 339 */         return SkillTrigger.FISH_CATCH_ENTITY;
/*     */       case "FISHGROUND": case "FISHINGGROUND":
/* 341 */         return SkillTrigger.FISH_GROUND;
/*     */       case "FISHREEL": case "FISHINGREEL":
/* 343 */         return SkillTrigger.FISH_REEL;
/*     */       case "FISHFAIL": case "FISHINGFAIL":
/* 345 */         return SkillTrigger.FISH_FAIL;
/*     */       case "TRIDENTHIT":
/* 347 */         return SkillTrigger.TRIDENT_HIT;
/*     */       case "THROWTRIDENT": case "TRIDENTTHROW":
/* 349 */         return SkillTrigger.TRIDENT_THROW;
/*     */     } 
/* 351 */     return SkillTrigger.DEFAULT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SkillTargeter parseSkillTargeter(String strTarget) {
/* 356 */     String name, search = strTarget.substring(1);
/*     */ 
/*     */     
/* 359 */     MythicLineConfig mlc = new MythicLineConfig(search);
/*     */     
/* 361 */     if (search.contains("{")) {
/* 362 */       name = search.substring(0, search.indexOf("{"));
/*     */     } else {
/* 364 */       name = search;
/*     */     } 
/*     */     
/* 367 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, ": Parsing SkillTargeter {0}", new Object[] { search });
/*     */     
/* 369 */     return SkillTargeter.getMythicTargeter(name, mlc);
/*     */   }
/*     */   
/*     */   protected SkillCondition parseSkillCondition(String strCondition) {
/* 373 */     String search = strCondition.substring(1);
/*     */     
/* 375 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, ": Parsing SkillCondition {0}", new Object[] { search });
/*     */     
/* 377 */     return SkillCondition.getCondition(search);
/*     */   }
/*     */   
/*     */   public void setTargetsCreativePlayers(boolean b) {
/* 381 */     this.target_creative = b;
/*     */   }
/*     */   
/*     */   public boolean targetsCreativePlayers() {
/* 385 */     return this.target_creative;
/*     */   }
/*     */   
/*     */   public SkillMetadata evaluateTargets(SkillMetadata data) {
/* 389 */     if (this.targeter.isPresent()) {
/* 390 */       SkillTargeter targeter = this.targeter.get();
/*     */       
/* 392 */       if (targeter instanceof CustomTargeter && ((CustomTargeter)targeter).getTargeter().isPresent()) {
/* 393 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": SkillTargeter is a CUSTOM targeter", new Object[0]);
/* 394 */         targeter = ((CustomTargeter)targeter).getTargeter().get();
/*     */       } 
/* 396 */       if (targeter instanceof IEntitySelector) {
/*     */         try {
/* 398 */           data.setEntityTargets(((IEntitySelector)targeter).getEntities(data));
/* 399 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": EntityTargeter found {0} targets", new Object[] { Integer.valueOf(data.getEntityTargets().size()) });
/* 400 */           ((IEntitySelector)targeter).filter(data, targetsCreativePlayers());
/* 401 */         } catch (IllegalArgumentException ex) {
/* 402 */           data.setEntityTargets(Sets.newHashSet());
/*     */         } 
/*     */       }
/* 405 */       if (targeter instanceof ILocationSelector) {
/* 406 */         data.setLocationTargets(((ILocationSelector)targeter).getLocations(data));
/* 407 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": LocationTargeter found {0} targets", new Object[] { Integer.valueOf(data.getLocationTargets().size()) });
/* 408 */         ((ILocationSelector)targeter).filter(data);
/* 409 */       } else if (targeter instanceof OriginTargeter) {
/* 410 */         data.setLocationTargets(((OriginTargeter)targeter).getLocation(data.getOrigin()));
/* 411 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": Targeting origin", new Object[0]);
/* 412 */       } else if (targeter instanceof io.lumine.xikage.mythicmobs.skills.targeters.MTTriggerLocation) {
/* 413 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": Targeting trigger location", new Object[0]);
/* 414 */         HashSet<AbstractLocation> lTargets = new HashSet<>();
/* 415 */         lTargets.add(data.getTrigger().getLocation());
/* 416 */         data.setLocationTargets(lTargets);
/*     */       } 
/* 418 */       if (targeter instanceof io.lumine.xikage.mythicmobs.skills.targeters.ConsoleTargeter) {
/* 419 */         MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, ": Targeting console", new Object[0]);
/* 420 */         data.setEntityTargets(null);
/* 421 */         data.setLocationTargets(null);
/*     */       } 
/*     */     } 
/*     */     
/* 425 */     if (data.getEntityTargets() != null && 
/* 426 */       data.getEntityTargets().size() > 0 && 
/* 427 */       this.powerSplitBetweenTargets) {
/* 428 */       data.setPower(data.getPower() / data.getEntityTargets().size());
/*     */     }
/*     */ 
/*     */     
/* 432 */     if (data.getLocationTargets() != null && 
/* 433 */       data.getLocationTargets().size() > 0 && 
/* 434 */       this.powerSplitBetweenTargets) {
/* 435 */       data.setPower(data.getPower() / data.getLocationTargets().size());
/*     */     }
/*     */ 
/*     */     
/* 439 */     return data;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\AbstractSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */