/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkillMetadata
/*     */   implements Cloneable, PlaceholderMeta
/*     */ {
/*     */   private final SkillTrigger cause;
/*     */   
/*     */   public SkillTrigger getCause() {
/*  23 */     return this.cause;
/*  24 */   } private SkillCaster caster; private AbstractEntity trigger; private AbstractLocation origin; private IParentSkill caller = null; private HashSet<AbstractEntity> eTargets; private HashSet<AbstractLocation> lTargets; public SkillCaster getCaster() {
/*  25 */     return this.caster; }
/*  26 */   public AbstractEntity getTrigger() { return this.trigger; } public AbstractLocation getOrigin() {
/*  27 */     return this.origin;
/*     */   }
/*     */   
/*  30 */   private HashMap<String, Object> metadata = Maps.newHashMap();
/*  31 */   private VariableRegistry variables = new VariableRegistry(); private float power; public VariableRegistry getVariables() { return this.variables; }
/*  32 */   public float getPower() { return this.power; } public void setPower(float power) { this.power = power; }
/*     */   
/*     */   private boolean async = true;
/*     */   public SkillMetadata(SkillTrigger cause, SkillCaster am, AbstractEntity trigger) {
/*  36 */     this.cause = cause;
/*  37 */     this.caster = am;
/*  38 */     this.trigger = trigger;
/*     */   }
/*     */   
/*     */   public SkillMetadata(SkillTrigger cause, SkillCaster am, AbstractEntity trigger, AbstractLocation origin, HashSet<AbstractEntity> eTargets, HashSet<AbstractLocation> lTargets, float power) {
/*  42 */     this.cause = cause;
/*  43 */     this.caster = am;
/*  44 */     this.trigger = trigger;
/*  45 */     this.origin = origin;
/*  46 */     this.eTargets = eTargets;
/*  47 */     this.lTargets = lTargets;
/*  48 */     this.power = power;
/*     */   }
/*     */   
/*     */   public SkillMetadata(io.lumine.xikage.mythicmobs.skills.SkillMetadata source) {
/*  52 */     this.cause = source.getCause();
/*  53 */     this.caster = source.getCaster();
/*  54 */     this.trigger = source.getTrigger();
/*  55 */     this.origin = source.getOrigin();
/*  56 */     this.eTargets = source.getEntityTargets();
/*  57 */     this.lTargets = source.getLocationTargets();
/*  58 */     this.power = source.getPower();
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.skills.SkillMetadata setOrigin(AbstractLocation o) {
/*  62 */     this.origin = o;
/*  63 */     return this;
/*     */   }
/*     */   
/*     */   public boolean getIsAsync() {
/*  67 */     return this.async;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.skills.SkillMetadata setIsAsync(boolean b) {
/*  71 */     this.async = b;
/*  72 */     return this;
/*     */   }
/*     */   
/*     */   public IParentSkill getCallingEvent() {
/*  76 */     return this.caller;
/*     */   }
/*     */   
/*     */   public void setCallingEvent(IParentSkill caller) {
/*  80 */     this.caller = caller;
/*     */   }
/*     */   
/*     */   public void cancelEvent() {
/*  84 */     if (this.caller != null) {
/*  85 */       this.caller.setCancelled();
/*     */     }
/*     */   }
/*     */   
/*     */   public HashSet<AbstractEntity> getEntityTargets() {
/*  90 */     return this.eTargets;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.skills.SkillMetadata setEntityTargets(HashSet<AbstractEntity> targets) {
/*  94 */     if (this.lTargets != null) this.lTargets = new HashSet<>(); 
/*  95 */     this.eTargets = targets;
/*  96 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.skills.SkillMetadata setEntityTarget(AbstractEntity target) {
/* 100 */     if (this.lTargets != null) this.lTargets = new HashSet<>(); 
/* 101 */     this.eTargets = new HashSet<>();
/* 102 */     this.eTargets.add(target);
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   public HashSet<AbstractLocation> getLocationTargets() {
/* 107 */     return this.lTargets;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.skills.SkillMetadata setLocationTargets(HashSet<AbstractLocation> targets) {
/* 111 */     if (this.eTargets != null) this.eTargets = new HashSet<>(); 
/* 112 */     this.lTargets = targets;
/* 113 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.skills.SkillMetadata setLocationTarget(AbstractLocation target) {
/* 117 */     if (this.eTargets != null) this.eTargets = new HashSet<>(); 
/* 118 */     this.lTargets = new HashSet<>();
/* 119 */     this.lTargets.add(target);
/* 120 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.skills.SkillMetadata setMetadata(String key, Object value) {
/* 124 */     this.metadata.put(key, value);
/* 125 */     return this;
/*     */   }
/*     */   
/*     */   public Optional<Object> getMetadata(String key) {
/* 129 */     return Optional.ofNullable(this.metadata.getOrDefault(key, null));
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.skills.SkillMetadata clone() throws CloneNotSupportedException {
/* 133 */     return (io.lumine.xikage.mythicmobs.skills.SkillMetadata)super.clone();
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.skills.SkillMetadata deepClone() {
/*     */     try {
/* 138 */       io.lumine.xikage.mythicmobs.skills.SkillMetadata clone = clone();
/* 139 */       clone.setCallingEvent(getCallingEvent());
/* 140 */       return clone;
/* 141 */     } catch (CloneNotSupportedException e) {
/* 142 */       e.printStackTrace();
/* 143 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCaster(SkillCaster caster) {
/* 148 */     this.caster = caster;
/*     */   }
/*     */   
/*     */   public void setTrigger(AbstractEntity entity) {
/* 152 */     this.trigger = entity;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\SkillMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */