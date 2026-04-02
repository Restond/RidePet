/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.ConditionAction;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.ICasterCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityComparisonCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityLocationComparisonCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationComparisonCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.ISkillMetaCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.InvalidCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.conditions.all.CustomCondition;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*     */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnPoint;
/*     */ import java.util.HashSet;
/*     */ import java.util.Optional;
/*     */ 
/*     */ public class SkillCondition {
/*     */   public static MythicMobs getPlugin() {
/*  30 */     return MythicMobs.inst();
/*     */   }
/*     */   
/*  33 */   protected String conditionVar = "0";
/*  34 */   protected ConditionAction ACTION = ConditionAction.REQUIRED;
/*  35 */   protected PlaceholderString actionVar = null;
/*     */   
/*     */   public SkillCondition(String line) {
/*  38 */     String[] split = line.split(" ");
/*     */     
/*  40 */     if (split.length < 2)
/*     */       return; 
/*  42 */     if (ConditionAction.isAction(split[1])) {
/*  43 */       this.ACTION = ConditionAction.valueOf(split[1].toUpperCase());
/*     */       
/*  45 */       if (split.length > 2) {
/*  46 */         this.actionVar = PlaceholderString.of(split[2]);
/*     */       }
/*     */     } else {
/*  49 */       this.conditionVar = split[1];
/*     */       
/*  51 */       if (split.length > 2 && 
/*  52 */         ConditionAction.isAction(split[2])) {
/*  53 */         this.ACTION = ConditionAction.valueOf(split[2].toUpperCase());
/*     */         
/*  55 */         if (split.length > 3) {
/*  56 */           this.actionVar = PlaceholderString.of(split[3]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean evaluateEntity(AbstractEntity t) {
/*     */     io.lumine.xikage.mythicmobs.skills.SkillCondition condition;
/*  66 */     if (this instanceof CustomCondition && ((CustomCondition)this).getCondition().isPresent()) {
/*  67 */       condition = ((CustomCondition)this).getCondition().get();
/*     */     } else {
/*  69 */       condition = this;
/*     */     } 
/*  71 */     if (condition instanceof InvalidCondition) return true; 
/*  72 */     if (condition instanceof IEntityCondition)
/*  73 */       return condition.handleOutcome(t, ((IEntityCondition)condition).check(t)); 
/*  74 */     if (condition instanceof ILocationCondition) {
/*  75 */       return condition.handleOutcome(t, ((ILocationCondition)condition).check(t.getLocation()));
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public boolean evaluateToEntity(AbstractEntity base, AbstractEntity t) {
/*     */     io.lumine.xikage.mythicmobs.skills.SkillCondition condition;
/*  82 */     if (this instanceof CustomCondition && ((CustomCondition)this).getCondition().isPresent()) {
/*  83 */       condition = ((CustomCondition)this).getCondition().get();
/*     */     } else {
/*  85 */       condition = this;
/*     */     } 
/*  87 */     if (condition instanceof InvalidCondition) return true; 
/*  88 */     if (condition instanceof IEntityCondition)
/*  89 */       return condition.handleOutcome(t, ((IEntityCondition)condition).check(t)); 
/*  90 */     if (condition instanceof ILocationCondition)
/*  91 */       return condition.handleOutcome(t, ((ILocationCondition)condition).check(t.getLocation())); 
/*  92 */     if (condition instanceof IEntityComparisonCondition)
/*  93 */       return condition.handleOutcome(t, ((IEntityComparisonCondition)condition).check(base, t)); 
/*  94 */     if (condition instanceof ILocationComparisonCondition)
/*  95 */       return condition.handleOutcome(t, ((ILocationComparisonCondition)condition).check(base.getLocation(), t.getLocation())); 
/*  96 */     if (this instanceof IEntityLocationComparisonCondition) {
/*  97 */       return condition.handleOutcome(t, ((IEntityLocationComparisonCondition)condition).check(base, t.getLocation()));
/*     */     }
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   public boolean evaluateCaster(SkillMetadata meta) {
/*     */     io.lumine.xikage.mythicmobs.skills.SkillCondition condition;
/* 104 */     if (this instanceof CustomCondition && ((CustomCondition)this).getCondition().isPresent()) {
/* 105 */       condition = ((CustomCondition)this).getCondition().get();
/*     */     } else {
/* 107 */       condition = this;
/*     */     } 
/* 109 */     if (condition instanceof InvalidCondition) return true; 
/* 110 */     if (condition instanceof ISkillMetaCondition)
/* 111 */       return condition.handleOutcome(meta, ((ISkillMetaCondition)condition).check(meta)); 
/* 112 */     if (condition instanceof ICasterCondition)
/* 113 */       return condition.handleOutcome(meta, ((ICasterCondition)condition).check(meta.getCaster())); 
/* 114 */     if (condition instanceof IEntityCondition)
/* 115 */       return condition.handleOutcome(meta, ((IEntityCondition)condition).check(meta.getCaster().getEntity())); 
/* 116 */     if (condition instanceof ILocationCondition) {
/* 117 */       return condition.handleOutcome(meta, ((ILocationCondition)condition).check(meta.getCaster().getLocation()));
/*     */     }
/* 119 */     return false;
/*     */   }
/*     */   
/*     */   public boolean evaluateTrigger(SkillMetadata meta) {
/*     */     io.lumine.xikage.mythicmobs.skills.SkillCondition condition;
/* 124 */     if (this instanceof CustomCondition && ((CustomCondition)this).getCondition().isPresent()) {
/* 125 */       condition = ((CustomCondition)this).getCondition().get();
/*     */     } else {
/* 127 */       condition = this;
/*     */     } 
/* 129 */     if (condition instanceof InvalidCondition) return true; 
/* 130 */     if (meta.getTrigger() == null) return false; 
/* 131 */     if (this instanceof IEntityCondition)
/* 132 */       return condition.handleOutcome(meta, ((IEntityCondition)condition).check(meta.getTrigger())); 
/* 133 */     if (this instanceof IEntityComparisonCondition)
/* 134 */       return condition.handleOutcome(meta, ((IEntityComparisonCondition)condition).check(meta.getCaster().getEntity(), meta.getTrigger())); 
/* 135 */     if (this instanceof ILocationCondition)
/* 136 */       return condition.handleOutcome(meta, ((ILocationCondition)condition).check(meta.getTrigger().getLocation())); 
/* 137 */     if (this instanceof ILocationComparisonCondition)
/* 138 */       return condition.handleOutcome(meta, ((ILocationComparisonCondition)condition).check(meta.getCaster().getLocation(), meta.getTrigger().getLocation())); 
/* 139 */     if (this instanceof IEntityLocationComparisonCondition) {
/* 140 */       return condition.handleOutcome(meta, ((IEntityLocationComparisonCondition)condition).check(meta.getCaster().getEntity(), meta.getTrigger().getLocation()));
/*     */     }
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean evaluateTargets(SkillMetadata meta) {
/*     */     io.lumine.xikage.mythicmobs.skills.SkillCondition condition;
/* 149 */     if (this instanceof CustomCondition && ((CustomCondition)this).getCondition().isPresent()) {
/* 150 */       condition = ((CustomCondition)this).getCondition().get();
/*     */     } else {
/* 152 */       condition = this;
/*     */     } 
/* 154 */     if (condition instanceof InvalidCondition) return true; 
/* 155 */     if (meta.getEntityTargets() != null && meta.getEntityTargets().size() > 0) {
/* 156 */       HashSet<AbstractEntity> targets = (HashSet<AbstractEntity>)meta.getEntityTargets().clone();
/* 157 */       targets.removeIf(target -> (paramSkillCondition instanceof IEntityCondition) ? (!paramSkillCondition.handleOutcome(paramSkillMetadata, ((IEntityCondition)paramSkillCondition).check(target))) : ((paramSkillCondition instanceof IEntityComparisonCondition) ? (!paramSkillCondition.handleOutcome(paramSkillMetadata, ((IEntityComparisonCondition)paramSkillCondition).check(paramSkillMetadata.getCaster().getEntity(), target))) : ((paramSkillCondition instanceof ILocationCondition) ? (!paramSkillCondition.handleOutcome(paramSkillMetadata, ((ILocationCondition)paramSkillCondition).check(target.getLocation()))) : ((paramSkillCondition instanceof ILocationComparisonCondition) ? (!paramSkillCondition.handleOutcome(paramSkillMetadata, ((ILocationComparisonCondition)paramSkillCondition).check(paramSkillMetadata.getCaster().getLocation(), target.getLocation()))) : ((this instanceof IEntityLocationComparisonCondition) ? paramSkillCondition.handleOutcome(paramSkillMetadata, ((IEntityLocationComparisonCondition)paramSkillCondition).check(paramSkillMetadata.getCaster().getEntity(), target.getLocation())) : false)))));
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
/* 172 */       meta.setEntityTargets(targets);
/* 173 */       if (targets.size() == 0) return false; 
/* 174 */     } else if (meta.getLocationTargets() != null && meta.getLocationTargets().size() > 0) {
/* 175 */       HashSet<AbstractLocation> targets = (HashSet<AbstractLocation>)meta.getLocationTargets().clone();
/* 176 */       targets.removeIf(target -> (paramSkillCondition instanceof ILocationCondition) ? (!paramSkillCondition.handleOutcome(paramSkillMetadata, ((ILocationCondition)paramSkillCondition).check(target))) : ((paramSkillCondition instanceof ILocationComparisonCondition) ? (!paramSkillCondition.handleOutcome(paramSkillMetadata, ((ILocationComparisonCondition)paramSkillCondition).check(paramSkillMetadata.getCaster().getLocation(), target))) : ((this instanceof IEntityLocationComparisonCondition) ? paramSkillCondition.handleOutcome(paramSkillMetadata, ((IEntityLocationComparisonCondition)paramSkillCondition).check(paramSkillMetadata.getCaster().getEntity(), target)) : false)));
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
/* 187 */       meta.setLocationTargets(targets);
/* 188 */       if (targets.size() == 0) return false; 
/*     */     } else {
/* 190 */       return false;
/*     */     } 
/* 192 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean evaluateRandomSpawnPoint(RandomSpawnPoint rsp) {
/*     */     io.lumine.xikage.mythicmobs.skills.SkillCondition condition;
/* 198 */     if (this instanceof CustomCondition && ((CustomCondition)this).getCondition().isPresent()) {
/* 199 */       condition = ((CustomCondition)this).getCondition().get();
/*     */     } else {
/* 201 */       condition = this;
/*     */     } 
/* 203 */     if (condition instanceof InvalidCondition) return true; 
/* 204 */     if (condition instanceof IEntityCondition)
/* 205 */       return condition.handleOutcome(rsp, ((IEntityCondition)condition).check(rsp.getEntity())); 
/* 206 */     if (condition instanceof ILocationCondition) {
/* 207 */       return condition.handleOutcome(rsp, ((ILocationCondition)condition).check(rsp.getLocation()));
/*     */     }
/* 209 */     return false;
/*     */   }
/*     */   public boolean evaluateDropper(DropMetadata meta) {
/*     */     io.lumine.xikage.mythicmobs.skills.SkillCondition condition;
/* 213 */     if (!meta.getDropper().isPresent()) {
/* 214 */       return false;
/*     */     }
/* 216 */     SkillCaster dropper = meta.getDropper().get();
/*     */ 
/*     */     
/* 219 */     if (this instanceof CustomCondition && ((CustomCondition)this).getCondition().isPresent()) {
/* 220 */       condition = ((CustomCondition)this).getCondition().get();
/*     */     } else {
/* 222 */       condition = this;
/*     */     } 
/* 224 */     if (condition instanceof InvalidCondition) return true; 
/* 225 */     if (condition instanceof ICasterCondition)
/* 226 */       return condition.handleOutcome(meta, ((ICasterCondition)condition).check(dropper)); 
/* 227 */     if (condition instanceof IEntityCondition)
/* 228 */       return condition.handleOutcome(meta, ((IEntityCondition)condition).check(dropper.getEntity())); 
/* 229 */     if (condition instanceof ILocationCondition) {
/* 230 */       return condition.handleOutcome(meta, ((ILocationCondition)condition).check(dropper.getLocation()));
/*     */     }
/* 232 */     return false;
/*     */   }
/*     */   public boolean evaluateDropCause(DropMetadata meta) {
/*     */     io.lumine.xikage.mythicmobs.skills.SkillCondition condition;
/* 236 */     if (!meta.getCause().isPresent()) {
/* 237 */       return false;
/*     */     }
/* 239 */     AbstractEntity cause = meta.getCause().get();
/*     */ 
/*     */     
/* 242 */     if (this instanceof CustomCondition && ((CustomCondition)this).getCondition().isPresent()) {
/* 243 */       condition = ((CustomCondition)this).getCondition().get();
/*     */     } else {
/* 245 */       condition = this;
/*     */     } 
/* 247 */     if (condition instanceof InvalidCondition) return true; 
/* 248 */     if (condition instanceof IEntityCondition)
/* 249 */       return condition.handleOutcome(meta, ((IEntityCondition)condition).check(cause)); 
/* 250 */     if (condition instanceof ILocationCondition) {
/* 251 */       return condition.handleOutcome(meta, ((ILocationCondition)condition).check(cause.getLocation()));
/*     */     }
/* 253 */     return false;
/*     */   }
/*     */   
/*     */   public boolean handleOutcome(AbstractEntity entity, boolean outcome) {
/* 257 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$conditions$ConditionAction[this.ACTION.ordinal()]) { case 1:
/*     */       case 2:
/* 259 */         return !outcome;
/*     */       case 3:
/*     */       case 4:
/* 262 */         return outcome; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     return true;
/*     */   }
/*     */   
/*     */   public boolean handleOutcome(SkillMetadata meta, boolean outcome) {
/* 271 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$conditions$ConditionAction[this.ACTION.ordinal()]) {
/*     */       case 5:
/* 273 */         if (outcome) {
/* 274 */           Optional<Skill> maybeSkill = MythicMobs.inst().getSkillManager().getSkill(this.actionVar.get((PlaceholderMeta)meta));
/*     */           
/* 276 */           if (maybeSkill.isPresent() && (
/* 277 */             (Skill)maybeSkill.get()).isUsable(meta)) {
/* 278 */             ((Skill)maybeSkill.get()).execute(meta);
/*     */           }
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 6:
/* 285 */         if (outcome) {
/* 286 */           Optional<Skill> maybeSkill = MythicMobs.inst().getSkillManager().getSkill(this.actionVar.get((PlaceholderMeta)meta));
/*     */           
/* 288 */           if (maybeSkill.isPresent() && (
/* 289 */             (Skill)maybeSkill.get()).isUsable(meta)) {
/* 290 */             ((Skill)maybeSkill.get()).execute(meta);
/*     */           }
/*     */           
/* 293 */           return false;
/*     */         } 
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/* 298 */         if (outcome && this.actionVar != null && meta.getCaster().getEntity().isPlayer()) {
/* 299 */           meta.getCaster().getEntity().asPlayer().sendMessage(this.actionVar.get((PlaceholderMeta)meta));
/*     */         }
/* 301 */         return !outcome;
/*     */       
/*     */       case 7:
/* 304 */         if (outcome)
/*     */           try {
/* 306 */             float powerMod = Float.valueOf(this.actionVar.get((PlaceholderMeta)meta)).floatValue();
/* 307 */             meta.setPower(meta.getPower() * powerMod);
/* 308 */           } catch (Exception ex) {
/* 309 */             ex.printStackTrace();
/*     */           }  
/*     */         break;
/*     */       case 3:
/*     */       case 4:
/* 314 */         if (!outcome && this.actionVar != null && meta.getCaster().getEntity().isPlayer()) {
/* 315 */           meta.getCaster().getEntity().asPlayer().sendMessage(this.actionVar.get((PlaceholderMeta)meta));
/*     */         }
/* 317 */         return outcome;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 322 */     return true;
/*     */   }
/*     */   
/*     */   public boolean handleOutcome(RandomSpawnPoint rsp, boolean outcome) {
/* 326 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$conditions$ConditionAction[this.ACTION.ordinal()]) { case 1:
/*     */       case 2:
/* 328 */         return !outcome;
/*     */       case 8:
/*     */         try {
/* 331 */           float levelMod = Float.valueOf(this.actionVar.get()).floatValue();
/* 332 */           rsp.setLevelMod(levelMod);
/* 333 */         } catch (Exception ex) {
/* 334 */           ex.printStackTrace();
/*     */         }  break;
/*     */       case 3:
/*     */       case 4:
/* 338 */         return outcome; }
/*     */ 
/*     */ 
/*     */     
/* 342 */     return true;
/*     */   }
/*     */   
/*     */   public boolean handleOutcome(DropMetadata meta, boolean outcome) {
/* 346 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$conditions$ConditionAction[this.ACTION.ordinal()]) { case 1:
/*     */       case 2:
/* 348 */         return !outcome;
/*     */       case 3: case 4:
/* 350 */         return outcome; }
/*     */ 
/*     */ 
/*     */     
/* 354 */     return true;
/*     */   }
/*     */   
/*     */   public void setAction(ConditionAction action) {
/* 358 */     this.ACTION = action;
/*     */   }
/*     */   
/*     */   public String getActionVar() {
/* 362 */     return this.actionVar.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.skills.SkillCondition getCondition(String condition) {
/* 367 */     condition = MythicLineConfig.unparseBlock(condition);
/* 368 */     if (condition.contains("}")) {
/*     */       
/* 370 */       String sp1 = condition.substring(0, condition.indexOf("}"));
/* 371 */       String sp2 = condition.substring(condition.indexOf("}"));
/* 372 */       String ns = sp1.replace(" ", "") + sp2;
/* 373 */       condition = ns;
/* 374 */       MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, ": Normalized Condition string to: " + condition, new Object[0]);
/*     */     } 
/*     */     
/* 377 */     String[] s = condition.split(" ");
/*     */     
/* 379 */     String name = null;
/*     */     
/* 381 */     MythicLineConfig mlc = new MythicLineConfig(s[0]);
/*     */     
/* 383 */     if (s[0].contains("{")) {
/* 384 */       name = s[0].substring(0, s[0].indexOf("{"));
/*     */     } else {
/* 386 */       name = s[0];
/*     */     } 
/*     */     
/* 389 */     MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "? Matching MythicCondition type to " + name, new Object[0]);
/*     */     
/* 391 */     ImmutableMap immutableMap = getPlugin().getSkillManager().getConditions();
/* 392 */     if (immutableMap.containsKey(name.toUpperCase())) {
/* 393 */       Class<? extends io.lumine.xikage.mythicmobs.skills.SkillCondition> clazz = (Class<? extends io.lumine.xikage.mythicmobs.skills.SkillCondition>)immutableMap.get(name.toUpperCase());
/*     */       
/*     */       try {
/* 396 */         return clazz.getConstructor(new Class[] { String.class, MythicLineConfig.class }).newInstance(new Object[] { condition, mlc });
/* 397 */       } catch (Exception e) {
/* 398 */         MythicLogger.error("Failed to construct condition {0}", new Object[] { condition });
/* 399 */         e.printStackTrace();
/*     */       } 
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
/*     */     try {
/* 485 */       return (io.lumine.xikage.mythicmobs.skills.SkillCondition)new CustomCondition(name, condition, mlc);
/* 486 */     } catch (Exception ex) {
/* 487 */       MythicLogger.error("Failed to load condition '" + name + "'");
/*     */       
/* 489 */       ex.printStackTrace();
/*     */       
/* 491 */       return (io.lumine.xikage.mythicmobs.skills.SkillCondition)new InvalidCondition(condition);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\SkillCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */