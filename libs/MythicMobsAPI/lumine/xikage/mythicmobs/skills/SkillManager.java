/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.IOHandler;
/*     */ import io.lumine.xikage.mythicmobs.io.IOLoader;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.legacy.LegacyMythicSkill;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.AuraManager;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ModifyScoreMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.ParticleRingEffect;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.SendResourcePackMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.SetBlockTypeMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.SetGlidingMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.SetOwnerMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.SmokeEffect;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.SpringMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.VariableSubtractMechanic;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*     */ import io.lumine.xikage.mythicmobs.util.reflections.VersionCompliantReflections;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class SkillManager implements Terminable {
/*  46 */   private static final Map<String, Class<? extends SkillCondition>> CONDITIONS = new ConcurrentHashMap<>();
/*  47 */   private static final Map<String, Class<? extends SkillMechanic>> MECHANICS = new ConcurrentHashMap<>();
/*  48 */   private static final Map<String, Class<? extends SkillTargeter>> TARGETERS = new ConcurrentHashMap<>();
/*     */   
/*     */   protected final MythicMobs core;
/*     */   
/*     */   private List<File> skillFiles;
/*     */   
/*     */   private IOLoader<MythicMobs> defaultSkills;
/*     */   private List<IOLoader<MythicMobs>> skillLoaders;
/*  56 */   private ConcurrentHashMap<String, Skill> skills = new ConcurrentHashMap<>();
/*  57 */   public List<LegacyMythicSkill> legacySkills = new ArrayList<>(); public List<LegacyMythicSkill> getLegacySkills() { return this.legacySkills; }
/*  58 */    private List<Runnable> secondPass = new ArrayList<>(); private final AuraManager auraManager;
/*     */   public AuraManager getAuraManager() {
/*  60 */     return this.auraManager;
/*     */   }
/*     */   public SkillManager(MythicMobs core) {
/*  63 */     this.core = core;
/*  64 */     this.auraManager = new AuraManager(core, this);
/*     */     
/*  66 */     loadMechanics();
/*     */   }
/*     */   
/*     */   public boolean terminate() {
/*  70 */     Projectile.BULLET_ENTITIES.forEach(AbstractEntity::remove);
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadSkills() {
/*  76 */     this.defaultSkills = new IOLoader((JavaPlugin)MythicMobs.inst(), "ExampleSkills.yml", "Skills");
/*  77 */     this.skillFiles = IOHandler.getAllFiles(this.defaultSkills.getFile().getParent());
/*     */     
/*  79 */     this.skillLoaders = IOHandler.getSaveLoad((JavaPlugin)MythicMobs.inst(), this.skillFiles, "Skills");
/*  80 */     this.skills.clear();
/*     */     
/*  82 */     for (IOLoader<MythicMobs> sl : this.skillLoaders) {
/*  83 */       for (String name : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
/*  84 */         String file = sl.getFile().getPath();
/*  85 */         MythicConfig mc = new MythicConfig(name, sl.getCustomConfig());
/*  86 */         Skill ms = new Skill(file, name, mc);
/*  87 */         this.skills.put(name, ms);
/*     */         
/*  89 */         if (sl.getCustomConfig().getStringList(name + ".LegacySkills") != null) {
/*  90 */           double cooldown = sl.getCustomConfig().getDouble(name + ".Cooldown");
/*  91 */           List<String> list = sl.getCustomConfig().getStringList(name + ".LegacySkills");
/*  92 */           List<String> conditions = sl.getCustomConfig().getStringList(name + ".Conditions");
/*  93 */           this.legacySkills.add(new LegacyMythicSkill(name, file, list, cooldown, conditions));
/*     */         } 
/*     */       } 
/*     */     } 
/*  97 */     runSecondPass();
/*     */   }
/*     */ 
/*     */   
/*     */   public void runSecondPass() {
/* 102 */     while (this.secondPass.size() != 0) {
/* 103 */       MythicLogger.debug(MythicLogger.DebugLevel.INFO, "Doing second pass on " + this.secondPass.size() + " skills.", new Object[0]);
/* 104 */       List<Runnable> run = Lists.newArrayList(this.secondPass);
/* 105 */       this.secondPass.clear();
/*     */       
/* 107 */       run.forEach(r -> {
/*     */             if (r != null)
/*     */               r.run(); 
/*     */           });
/*     */     } 
/*     */   } public void queueSecondPass(Runnable r) {
/* 113 */     this.secondPass.add(r);
/*     */   }
/*     */   
/*     */   public void queueAfterLoad(Runnable r) {
/* 117 */     Scheduler.runLaterSync(r, 5L);
/*     */   }
/*     */   
/*     */   public void registerSkill(String internalName, Skill skill) {
/* 121 */     this.skills.put(internalName, skill);
/*     */   }
/*     */   
/*     */   public Optional<Skill> getSkill(String internalName) {
/* 125 */     if (internalName == null) {
/* 126 */       return Optional.empty();
/*     */     }
/* 128 */     if (internalName.startsWith("[") && internalName.endsWith("]")) {
/* 129 */       internalName = internalName.substring(1, internalName.length() - 2);
/*     */       
/* 131 */       internalName = MythicLineConfig.unparseBlock(internalName);
/*     */       
/* 133 */       String[] split = internalName.split("-");
/*     */       
/* 135 */       List<String> elements = new ArrayList<>();
/* 136 */       for (String e : split) {
/* 137 */         if (e.trim().length() != 0)
/* 138 */           elements.add(e.trim()); 
/*     */       } 
/* 140 */       Skill skill = new Skill(elements);
/* 141 */       String skillName = skill.getInternalName();
/*     */       
/* 143 */       this.skills.put(skillName, skill);
/* 144 */       return Optional.of(skill);
/* 145 */     }  if (this.skills.containsKey(internalName)) {
/* 146 */       return Optional.of(this.skills.get(internalName));
/*     */     }
/* 148 */     return Optional.empty();
/*     */   }
/*     */   
/*     */   public Collection<String> getSkillNames() {
/* 152 */     return this.skills.keySet();
/*     */   }
/*     */   
/*     */   public Collection<Skill> getSkills() {
/* 156 */     return this.skills.values();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadMechanics() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ImmutableMap<String, Class<? extends SkillCondition>> getConditions() {
/* 166 */     return ImmutableMap.copyOf(CONDITIONS);
/*     */   }
/*     */   
/*     */   public ImmutableMap<String, Class<? extends SkillMechanic>> getMechanics() {
/* 170 */     return ImmutableMap.copyOf(MECHANICS);
/*     */   }
/*     */   
/*     */   public ImmutableMap<String, Class<? extends SkillTargeter>> getTargeters() {
/* 174 */     return ImmutableMap.copyOf(TARGETERS);
/*     */   }
/*     */   
/*     */   public SkillMechanic getSkillMechanic(String skill) {
/* 178 */     MythicLogger.debug(MythicLogger.DebugLevel.INFO, "Matching mechanic to string: {0}", new Object[] { skill });
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
/* 200 */     String[] s = skill.split(" ");
/*     */     
/* 202 */     String name = null;
/*     */     
/* 204 */     MythicLineConfig mlc = new MythicLineConfig(s[0]);
/*     */     
/* 206 */     if (s[0].contains("{")) {
/* 207 */       name = s[0].substring(0, s[0].indexOf("{"));
/*     */     } else {
/* 209 */       name = s[0];
/*     */     } 
/* 211 */     MythicLogger.debug(MythicLogger.DebugLevel.INFO, "-- Matching MythicSkill type to {0}", new Object[] { name.toUpperCase() });
/*     */     
/* 213 */     if (MECHANICS.containsKey(name.toUpperCase())) {
/* 214 */       Class<? extends SkillMechanic> clazz = MECHANICS.get(name.toUpperCase());
/*     */       
/*     */       try {
/* 217 */         return clazz.getConstructor(new Class[] { String.class, MythicLineConfig.class }).newInstance(new Object[] { skill, mlc });
/* 218 */       } catch (Exception e) {
/* 219 */         MythicLogger.error("Failed to construct mechanic {0}", new Object[] { skill });
/* 220 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 227 */       if (name.toUpperCase().startsWith("SKILL:") || name.toUpperCase().startsWith("META:")) {
/* 228 */         String skillName = name.split(":")[1];
/* 229 */         return (SkillMechanic)new MetaSkillMechanic(skill, skillName, mlc);
/*     */       } 
/*     */       
/* 232 */       switch (name.toUpperCase()) { case "SENDTITLE":
/*     */         case "TITLE":
/* 234 */           return (SkillMechanic)new SendTitleMechanic(skill, mlc);
/*     */         case "SENDRESOURCEPACK": case "RESOURCEPACK":
/* 236 */           return (SkillMechanic)new SendResourcePackMechanic(skill, mlc);
/*     */         case "SETHEALTH": case "SETHP":
/* 238 */           return (SkillMechanic)new SetHealthMechanic(skill, mlc);
/*     */         case "SETMAXHEALTH": case "SETMAXHP":
/* 240 */           return (SkillMechanic)new SetMaxHealthMechanic(skill, mlc);
/*     */         case "SCORE": case "MODIFYSCORE":
/* 242 */           return (SkillMechanic)new ModifyScoreMechanic(skill, mlc);
/*     */         case "SETBLOCKTYPE": case "SETBLOCK":
/* 244 */           return (SkillMechanic)new SetBlockTypeMechanic(skill, mlc);
/*     */         case "SETGLIDING":
/* 246 */           return (SkillMechanic)new SetGlidingMechanic(skill, mlc);
/*     */         case "SETGLOBALSCORE": case "SGS":
/* 248 */           return (SkillMechanic)new SetGlobalScoreMechanic(skill, mlc);
/*     */         case "SETMOBSCORE": case "SMS":
/* 250 */           return (SkillMechanic)new SetMobScoreMechanic(skill, mlc);
/*     */         case "SETOWNER":
/* 252 */           return (SkillMechanic)new SetOwnerMechanic(skill, mlc);
/*     */         case "SETTARGETSCORE": case "STS":
/* 254 */           return (SkillMechanic)new SetTargetScoreMechanic(skill, mlc);
/*     */         case "SETSTANCE": case "STANCE":
/* 256 */           return (SkillMechanic)new SetStanceMechanic(skill, mlc);
/*     */         case "SETUSEGRAVITY": case "SETGRAVITY":
/* 258 */           return (SkillMechanic)new SetUseGravityMechanic(skill, mlc);
/*     */         case "SHIELD":
/* 260 */           return (SkillMechanic)new ShieldMechanic(skill, mlc);
/*     */         case "SHIELDPERCENT":
/* 262 */           return (SkillMechanic)new ShieldPercentMechanic(skill, mlc);
/*     */         case "SHOOTFIREBALL": case "FIREBALL":
/* 264 */           return (SkillMechanic)new ShootFireballMechanic(skill, mlc);
/*     */         case "SHOOTSKULL": case "SKULL": case "WITHERSKULL":
/* 266 */           return (SkillMechanic)new ShootSkullMechanic(skill, mlc);
/*     */         case "SIGNAL": case "SENDSIGNAL":
/* 268 */           return (SkillMechanic)new SignalMechanic(skill, mlc);
/*     */         case "SPRING": case "WATER":
/* 270 */           return (SkillMechanic)new SpringMechanic(skill, mlc);
/*     */         case "SUMMON": case "SPAWNMOBS":
/* 272 */           return (SkillMechanic)new SummonMechanic(skill, mlc);
/*     */         case "SUMMONPASSENGER": case "PASSENGER": case "SUMMONRIDER": case "RIDER":
/* 274 */           return (SkillMechanic)new SummonPassengerMechanic(skill, mlc);
/*     */         case "VARIABLESET":
/*     */         case "SETVARIABLE":
/*     */         case "SETVAR":
/* 278 */           return (SkillMechanic)new VariableSetMechanic(skill, mlc);
/*     */         case "VARIABLEADD": case "ADDVARIABLE": case "ADDVAR":
/*     */         case "INCREMENTVARIABLE":
/* 281 */           return (SkillMechanic)new VariableAddMechanic(skill, mlc);
/*     */         case "VARIABLESUBTRACT": case "VARIABLESUB": case "SUBVARIABLE": case "SUBVAR":
/*     */         case "DECREMENTVARIABLE":
/* 284 */           return (SkillMechanic)new VariableSubtractMechanic(skill, mlc);
/*     */         case "EFFECT:PARTICLES": case "PARTICLES":
/*     */         case "E:PARTICLES":
/*     */         case "E:P":
/* 288 */           return (SkillMechanic)new ParticleEffect(skill, mlc);
/*     */         case "EFFECT:PARTICLEBOX": case "PARTICLEBOX": case "E:PB": case "PB":
/* 290 */           return (SkillMechanic)new ParticleBoxEffect(skill, mlc);
/*     */         case "EFFECT:PARTICLELINE": case "PARTICLELINE": case "E:PL": case "PL":
/* 292 */           return (SkillMechanic)new ParticleLineEffect(skill, mlc);
/*     */         case "EFFECT:PARTICLERING": case "PARTICLERING": case "E:PR": case "PR":
/* 294 */           return (SkillMechanic)new ParticleRingEffect(skill, mlc);
/*     */         case "EFFECT:PARTICLESPHERE": case "PARTICLESPHERE": case "E:PS": case "PS":
/* 296 */           return (SkillMechanic)new ParticleSphereEffect(skill, mlc);
/*     */         case "EFFECT:PARTICLETORNADO": case "PARTICLETORNADO": case "E:PT":
/* 298 */           return (SkillMechanic)new ParticleTornadoEffect(skill, mlc);
/*     */         case "EFFECT:SMOKE": case "E:SMOKE": case "SMOKE":
/* 300 */           return (SkillMechanic)new SmokeEffect(skill, mlc);
/*     */         case "EFFECT:SMOKESWIRL": case "E:SMOKESWIRL": case "SMOKESWIRL":
/* 302 */           return (SkillMechanic)new SmokeSwirlEffect(skill, mlc);
/*     */         case "EFFECT:ATOM":
/*     */         case "ATOM":
/*     */         case "E:ATOM":
/* 306 */           return (SkillMechanic)new ParticleAtomEffect(skill, mlc);
/*     */         case "EFFECT:PARTICLEORBITAL": case "E:PARTICLEORBITAL": case "PARTICLEORBITAL": case "EFFECT:PARTICLECIRCLE": case "PARTICLECIRCLE": case "E:PARTICLECIRCLE":
/* 308 */           return (SkillMechanic)new ParticleOrbitalEffect(skill, mlc);
/*     */         case "EFFECT:DNA": case "DNA": case "E:DNA":
/* 310 */           return (SkillMechanic)new DNAEffect(skill, mlc); }
/*     */ 
/*     */       
/* 313 */       return (SkillMechanic)new CustomMechanic(name.toUpperCase(), skill, mlc);
/*     */     
/*     */     }
/* 316 */     catch (Exception ex) {
/* 317 */       MythicLogger.error("Failed to load skill line due to bad syntax: {0} ", new Object[] { skill });
/*     */       
/* 319 */       if (ConfigManager.debugLevel > 0) {
/* 320 */         ex.printStackTrace();
/*     */       }
/* 322 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void runTimerSkills(long timer) {
/* 327 */     for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
/* 328 */       if (am == null || am.getEntity() == null)
/*     */         continue; 
/* 330 */       am.tick(ConfigManager.ClockInterval);
/*     */       
/* 332 */       if (!am.isDead() && am.getEntity() != null && am.getEntity().isValid() && (am.getType()).usingTimers == true) {
/* 333 */         executeMobTimerSkills(am, timer);
/* 334 */         MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "---- Executed TIMER skills", new Object[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executeMobTimerSkills(ActiveMob am, long timer) {
/* 342 */     SkillMetadata data = new SkillMetadata(SkillTrigger.TIMER, (SkillCaster)am, null);
/* 343 */     data.setPower(am.getPower());
/*     */     
/* 345 */     for (SkillMechanic skill : am.getType().getTimerSkills()) {
/*     */ 
/*     */       
/* 348 */       this.core.getTimingsHandler().markSkillNew(am.getType().getInternalName() + ":" + skill.line);
/* 349 */       if (timer % skill.interval / ConfigManager.ClockInterval == 0.0D && 
/* 350 */         skill.isUsableFromCaster(data)) {
/* 351 */         skill.execute(data);
/*     */       }
/*     */       
/* 354 */       this.core.getTimingsHandler().markSkillComplete(am.getType().getInternalName() + ":" + skill.line);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void runLegacyTimerSkills(long timer) {
/* 360 */     if (ConfigManager.EnableLegacySkills == true) {
/* 361 */       for (ActiveMob am : this.core.getMobManager().getActiveMobs()) {
/*     */         
/* 363 */         if (am.getType() != null && 
/* 364 */           (am.getType()).usingTimers == true && !am.isDead() && am.getEntity() != null && am.getEntity().isValid() && am.getLocation().getWorld().isLoaded()) {
/* 365 */           LegacySkillHandler.ExecuteTimerSkills((am.getType()).legacyTimerSkills, am, timer);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public List<SkillCondition> getConditions(List<String> block) {
/* 372 */     List<SkillCondition> conditions = null;
/*     */     
/* 374 */     for (String s : block) {
/* 375 */       s = MythicLineConfig.unparseBlock(s);
/* 376 */       SkillCondition sc = SkillCondition.getCondition(s);
/*     */       
/* 378 */       if (!(sc instanceof io.lumine.xikage.mythicmobs.skills.conditions.InvalidCondition)) {
/* 379 */         if (conditions == null) {
/* 380 */           conditions = new ArrayList<>();
/*     */         }
/* 382 */         conditions.add(sc);
/*     */       } 
/*     */     } 
/* 385 */     return conditions;
/*     */   }
/*     */   
/*     */   public List<SkillCondition> getConditions(String block) {
/* 389 */     if (block.startsWith("[") && block.endsWith("]")) {
/* 390 */       block = block.substring(1, block.length() - 2);
/*     */       
/* 392 */       block = MythicLineConfig.unparseBlock(block);
/*     */       
/* 394 */       String[] split = block.split("-");
/*     */       
/* 396 */       List<String> elements = new ArrayList<>();
/* 397 */       for (String e : split) {
/* 398 */         if (e.trim().length() != 0)
/* 399 */           elements.add(e.trim()); 
/*     */       } 
/* 401 */       return getConditions(elements);
/*     */     } 
/* 403 */     return null;
/*     */   }
/*     */   
/*     */   static {
/* 407 */     Set<Class<?>> conditionsClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.skills.conditions.all")).getTypesAnnotatedWith(MythicCondition.class);
/*     */     
/* 409 */     for (Class<?> clazz : conditionsClasses) {
/*     */       try {
/* 411 */         String name = ((MythicCondition)clazz.<MythicCondition>getAnnotation(MythicCondition.class)).name();
/* 412 */         String[] aliases = ((MythicCondition)clazz.<MythicCondition>getAnnotation(MythicCondition.class)).aliases();
/*     */         
/* 414 */         if (SkillCondition.class.isAssignableFrom(clazz)) {
/* 415 */           CONDITIONS.put(name.toUpperCase(), clazz);
/*     */           
/* 417 */           for (String alias : aliases) {
/* 418 */             CONDITIONS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/* 421 */       } catch (Exception ex) {
/* 422 */         MythicLogger.error("Failed to load condition {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */     
/* 426 */     Set<Class<?>> mechanicsClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.skills.mechanics")).getTypesAnnotatedWith(MythicMechanic.class);
/*     */     
/* 428 */     for (Class<?> clazz : mechanicsClasses) {
/*     */       try {
/* 430 */         String name = ((MythicMechanic)clazz.<MythicMechanic>getAnnotation(MythicMechanic.class)).name();
/* 431 */         String[] aliases = ((MythicMechanic)clazz.<MythicMechanic>getAnnotation(MythicMechanic.class)).aliases();
/*     */         
/* 433 */         if (SkillMechanic.class.isAssignableFrom(clazz)) {
/* 434 */           MECHANICS.put(name.toUpperCase(), clazz);
/*     */           
/* 436 */           for (String alias : aliases) {
/* 437 */             MECHANICS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/* 440 */       } catch (Exception ex) {
/* 441 */         MythicLogger.error("Failed to load mechanic {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */     
/* 445 */     Set<Class<?>> targeterClasses = (new VersionCompliantReflections("io.lumine.xikage.mythicmobs.skills.targeters")).getTypesAnnotatedWith(MythicTargeter.class);
/*     */     
/* 447 */     for (Class<?> clazz : targeterClasses) {
/*     */       try {
/* 449 */         String name = ((MythicTargeter)clazz.<MythicTargeter>getAnnotation(MythicTargeter.class)).name();
/* 450 */         String[] aliases = ((MythicTargeter)clazz.<MythicTargeter>getAnnotation(MythicTargeter.class)).aliases();
/*     */         
/* 452 */         if (SkillTargeter.class.isAssignableFrom(clazz)) {
/* 453 */           TARGETERS.put(name.toUpperCase(), clazz);
/*     */           
/* 455 */           for (String alias : aliases) {
/* 456 */             TARGETERS.put(alias.toUpperCase(), clazz);
/*     */           }
/*     */         } 
/* 459 */       } catch (Exception ex) {
/* 460 */         MythicLogger.error("Failed to load targeter {0}", new Object[] { clazz.getCanonicalName() });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\SkillManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */