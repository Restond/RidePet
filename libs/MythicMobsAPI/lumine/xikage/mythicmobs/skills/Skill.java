/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.DelaySkill;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class Skill extends AbstractSkill {
/*     */   protected final String file;
/*     */   
/*     */   public String getFile() {
/*  34 */     return this.file;
/*     */   }
/*     */   protected final String internalName; protected final MythicConfig config;
/*  37 */   protected LinkedList<SkillMechanic> skills = new LinkedList<>();
/*     */   
/*  39 */   protected List<String> conditionsLegacy = new ArrayList<>();
/*     */   
/*     */   protected List<String> killMessages;
/*     */   
/*     */   public Skill(String file, String name, MythicConfig mc) {
/*  44 */     this.file = file;
/*  45 */     this.internalName = name;
/*  46 */     this.config = mc;
/*  47 */     this.cooldown = (float)mc.getDouble("Cooldown", 0.0D);
/*  48 */     this.conditionsLegacy = mc.getStringList("LegacyConditions");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     List<String> nTConditions = mc.getStringList("Conditions");
/*  54 */     for (String s : nTConditions) {
/*  55 */       if (s.contains("\"")) {
/*  56 */         String[] split = s.split("\"");
/*     */         
/*  58 */         int i = 0;
/*  59 */         String ns = "";
/*  60 */         for (String ss : split) {
/*  61 */           if (i % 2 == 1) {
/*  62 */             ns = ns.concat("\"" + SkillString.unparseMessageSpecialChars(ss) + "\"");
/*     */           } else {
/*  64 */             ns = ns.concat(ss);
/*     */           } 
/*  66 */           i++;
/*     */         } 
/*  68 */         s = ns;
/*     */       } 
/*  70 */       SkillCondition sc = SkillCondition.getCondition(s);
/*  71 */       if (!(sc instanceof io.lumine.xikage.mythicmobs.skills.conditions.InvalidCondition)) {
/*  72 */         if (this.conditions == null) {
/*  73 */           this.conditions = new ArrayList();
/*     */         }
/*  75 */         this.conditions.add(sc);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     nTConditions = mc.getStringList("TargetConditions");
/*  83 */     for (String s : nTConditions) {
/*  84 */       if (s.contains("\"")) {
/*  85 */         String[] split = s.split("\"");
/*     */         
/*  87 */         int i = 0;
/*  88 */         String ns = "";
/*  89 */         for (String ss : split) {
/*  90 */           if (i % 2 == 1) {
/*  91 */             ns = ns.concat("\"" + SkillString.unparseMessageSpecialChars(ss) + "\"");
/*     */           } else {
/*  93 */             ns = ns.concat(ss);
/*     */           } 
/*  95 */           i++;
/*     */         } 
/*  97 */         s = ns;
/*     */       } 
/*  99 */       SkillCondition sc = SkillCondition.getCondition(s);
/* 100 */       if (!(sc instanceof io.lumine.xikage.mythicmobs.skills.conditions.InvalidCondition)) {
/* 101 */         if (this.conditionsTarget == null) {
/* 102 */           this.conditionsTarget = new ArrayList();
/*     */         }
/* 104 */         this.conditionsTarget.add(sc);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     nTConditions = mc.getStringList("TriggerConditions");
/* 112 */     for (String s : nTConditions) {
/* 113 */       if (s.contains("\"")) {
/* 114 */         String[] split = s.split("\"");
/*     */         
/* 116 */         int i = 0;
/* 117 */         String ns = "";
/* 118 */         for (String ss : split) {
/* 119 */           if (i % 2 == 1) {
/* 120 */             ns = ns.concat("\"" + SkillString.unparseMessageSpecialChars(ss) + "\"");
/*     */           } else {
/* 122 */             ns = ns.concat(ss);
/*     */           } 
/* 124 */           i++;
/*     */         } 
/* 126 */         s = ns;
/*     */       } 
/* 128 */       SkillCondition sc = SkillCondition.getCondition(s);
/* 129 */       if (!(sc instanceof io.lumine.xikage.mythicmobs.skills.conditions.InvalidCondition)) {
/* 130 */         if (this.conditionsTrigger == null) {
/* 131 */           this.conditionsTrigger = new ArrayList();
/*     */         }
/* 133 */         this.conditionsTrigger.add(sc);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     List<String> nSkills = mc.getStringList("Skills");
/* 141 */     for (String s : nSkills) {
/*     */       
/* 143 */       s = MythicLineConfig.unparseBlock(s);
/*     */       
/* 145 */       SkillMechanic ms = null;
/*     */       try {
/* 147 */         ms = MythicMobs.inst().getSkillManager().getSkillMechanic(s);
/* 148 */       } catch (Exception ex) {
/* 149 */         MythicLogger.errorGenericConfig("Critical Error while attempting to load mechanic line '" + s + "'");
/* 150 */       } catch (Error ex) {
/* 151 */         MythicLogger.errorGenericConfig("Critical Error while attempting to load mechanic line '" + s + "'");
/*     */       } 
/*     */       
/* 154 */       if (ms != null) {
/* 155 */         this.skills.add(ms);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Skill(Collection<String> skillList) {
/* 161 */     this.file = "#λ";
/* 162 */     this.internalName = "#λ." + UUID.randomUUID().toString();
/* 163 */     this.config = null;
/* 164 */     this.cooldown = 0.0F;
/*     */     
/* 166 */     for (String s : skillList) {
/* 167 */       s = MythicLineConfig.unparseBlock(s);
/*     */       
/* 169 */       SkillMechanic ms = null;
/*     */       try {
/* 171 */         ms = MythicMobs.inst().getSkillManager().getSkillMechanic(s);
/* 172 */       } catch (Exception ex) {
/* 173 */         MythicLogger.errorGenericConfig("Critical Error while attempting to load mechanic line '" + s + "'");
/* 174 */       } catch (Error ex) {
/* 175 */         MythicLogger.errorGenericConfig("Critical Error while attempting to load mechanic line '" + s + "'");
/*     */       } 
/*     */       
/* 178 */       if (ms != null) {
/* 179 */         this.skills.add(ms);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getInternalName() {
/* 185 */     return this.internalName;
/*     */   }
/*     */   
/*     */   public MythicConfig getConfig() {
/* 189 */     return this.config;
/*     */   }
/*     */   
/*     */   public boolean usable(SkillMetadata meta, SkillTrigger trigger) {
/* 193 */     SkillCaster skillCaster = meta.getCaster();
/* 194 */     if (!rollChance()) {
/* 195 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! Skill not usable: Roll failed.", new Object[0]);
/* 196 */       return false;
/*     */     } 
/* 198 */     if (onCooldown(skillCaster) == true) {
/* 199 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! Skill not usable: Cooldown check failed.", new Object[0]);
/* 200 */       return false;
/*     */     } 
/* 202 */     if (!checkLegacyConditions(skillCaster)) {
/* 203 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! Skill not usable: Legacy check failed.", new Object[0]);
/* 204 */       return false;
/*     */     } 
/* 206 */     if (this.conditionsTarget != null) {
/* 207 */       for (SkillCondition mc : this.conditionsTarget) {
/* 208 */         if (!mc.evaluateTargets(meta)) {
/* 209 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! Skill not usable: TargetConditions failed.", new Object[0]);
/* 210 */           return false;
/*     */         } 
/*     */       } 
/*     */     }
/* 214 */     if (this.conditionsTrigger != null) {
/* 215 */       for (SkillCondition mc : this.conditionsTrigger) {
/* 216 */         if (!mc.evaluateTrigger(meta)) {
/* 217 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! Skill not usable: TriggerConditions failed.", new Object[0]);
/* 218 */           return false;
/*     */         } 
/*     */       } 
/*     */     }
/* 222 */     if (this.conditions != null) {
/* 223 */       for (SkillCondition mc : this.conditions) {
/* 224 */         if (!mc.evaluateCaster(meta)) {
/* 225 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! Skill not usable: Conditions failed.", new Object[0]);
/* 226 */           return false;
/*     */         } 
/*     */       } 
/*     */     }
/* 230 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! Skill usable!", new Object[0]);
/* 231 */     return true;
/*     */   }
/*     */   
/*     */   public void execute(SkillTrigger basetrigger, SkillCaster caster, AbstractEntity trigger, AbstractLocation origin, HashSet<AbstractEntity> eTargets, HashSet<AbstractLocation> lTargets, float power) {
/* 235 */     SkillMetadata data = new SkillMetadata(basetrigger, caster, trigger, origin, eTargets, lTargets, power);
/*     */     
/* 237 */     execute(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(SkillMetadata data) {
/* 242 */     Queue<SkillMechanic> skillqueue = (LinkedList)this.skills.clone();
/*     */     try {
/* 244 */       if (isUsable(data)) {
/* 245 */         execute(data, skillqueue);
/*     */       }
/* 247 */     } catch (Exception ex) {
/* 248 */       MythicLogger.error("Couldn't execute skill '" + this.internalName + "': Enable debugging for a stack trace.");
/* 249 */       MythicMobs.inst().getConfigManager(); if (ConfigManager.debugLevel > 0) {
/* 250 */         ex.printStackTrace();
/*     */       }
/*     */     } 
/* 253 */     setCooldown(data.getCaster(), this.cooldown);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void execute(SkillMetadata data, Queue<SkillMechanic> skillqueue) {
/* 260 */     while (skillqueue.size() > 0) {
/* 261 */       SkillMechanic mbs = skillqueue.poll();
/* 262 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "+ Evaluating SkillMechanic {0}", new Object[] { mbs.getConfigLine() });
/*     */       
/* 264 */       if (mbs instanceof DelaySkill) {
/* 265 */         AbstractSkill.DelayedSkill ds = new AbstractSkill.DelayedSkill(data, skillqueue);
/* 266 */         Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)ds, ((DelaySkill)mbs).getTicks());
/*     */         break;
/*     */       } 
/* 269 */       if (mbs.isUsableFromSkill(data)) {
/* 270 */         mbs.execute(data.deepClone());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUsable(SkillMetadata data) {
/* 277 */     return usable(data, null);
/*     */   }
/*     */   
/*     */   public boolean checkLegacyConditions(SkillCaster skillCaster) {
/* 281 */     if (this.conditionsLegacy.size() > 0)
/* 282 */       for (String strCondition : this.conditionsLegacy) {
/* 283 */         String[] split = strCondition.split(" ");
/* 284 */         String conditionData = null;
/*     */         
/* 286 */         if (split.length > 1) {
/* 287 */           conditionData = split[1];
/*     */         }
/*     */         
/* 290 */         if (!SCondition.getSpawningConditionByName(split[0]).check(BukkitAdapter.adapt(skillCaster.getEntity().getLocation()), (LivingEntity)skillCaster.getEntity().getBukkitEntity(), conditionData)) return false;
/*     */       
/*     */       }  
/* 293 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\Skill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */