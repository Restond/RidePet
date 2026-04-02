/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*     */ import java.util.Collection;
/*     */ import java.util.function.Consumer;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TriggeredSkill
/*     */   implements IParentSkill
/*     */ {
/*     */   private SkillMetadata data;
/*     */   
/*     */   public SkillMetadata getData() {
/*  26 */     return this.data;
/*     */   }
/*     */   private boolean sync = false;
/*     */   
/*     */   public TriggeredSkill(SkillTrigger cause, ActiveMob am, AbstractEntity trigger, Pair<String, Object>... metadata) {
/*  31 */     this(cause, am, trigger, false, null, metadata);
/*     */   }
/*     */   private boolean cancel = false;
/*     */   public TriggeredSkill(SkillTrigger cause, ActiveMob am, AbstractEntity trigger, boolean sync, Pair<String, Object>... metadata) {
/*  35 */     this(cause, am, trigger, sync, null, metadata);
/*     */   }
/*     */   
/*     */   public TriggeredSkill(SkillTrigger cause, ActiveMob am, AbstractEntity trigger, boolean sync, Consumer<SkillMetadata> transformer, Pair<String, Object>... metadata) {
/*  39 */     this.data = new SkillMetadata(cause, (SkillCaster)am, trigger);
/*  40 */     this.data.setCallingEvent(this);
/*  41 */     if (sync) {
/*  42 */       this.data.setIsAsync(false);
/*     */     } else {
/*  44 */       this.data.setIsAsync(true);
/*     */     } 
/*     */     
/*  47 */     if (this.data.getTrigger() != null) {
/*  48 */       if (am instanceof ActiveMob) {
/*  49 */         am.setLastAggroCause(this.data.getTrigger());
/*     */       }
/*  51 */       this.data.setEntityTarget(trigger);
/*     */     } 
/*  53 */     this.data.setPower(am.getPower());
/*     */     
/*  55 */     if (metadata != null) {
/*  56 */       for (Pair<String, Object> entry : metadata) {
/*  57 */         this.data.setMetadata((String)entry.getKey(), entry.getValue());
/*     */       }
/*     */     }
/*     */     
/*  61 */     if (transformer != null) {
/*  62 */       transformer.accept(this.data);
/*     */     }
/*  64 */     MythicLogger.debug(MythicLogger.DebugLevel.TRIGGER, "Skill Trigger {0} fired for ActiveMob {1} (uuid: {2})", new Object[] { cause, am.getDisplayName(), am.getUniqueId() });
/*  65 */     am.getType().executeSkills(this.data.getCause(), this.data);
/*     */   }
/*     */   
/*     */   public TriggeredSkill(SkillTrigger cause, SkillCaster am, AbstractLocation origin, AbstractEntity trigger, Collection<SkillMechanic> mechanics, boolean sync, Pair<String, Object>... metadata) {
/*  69 */     this(cause, am, origin, trigger, mechanics, sync, null, metadata);
/*     */   }
/*     */   
/*     */   public TriggeredSkill(SkillTrigger cause, SkillCaster am, AbstractLocation origin, AbstractEntity trigger, Collection<SkillMechanic> mechanics, boolean sync, Consumer<SkillMetadata> transformer, Pair<String, Object>... metadata) {
/*  73 */     this.data = new SkillMetadata(cause, am, trigger);
/*  74 */     this.data.setCallingEvent(this);
/*  75 */     if (sync) {
/*  76 */       this.data.setIsAsync(false);
/*     */     } else {
/*  78 */       this.data.setIsAsync(true);
/*     */     } 
/*     */     
/*  81 */     if (origin != null) {
/*  82 */       this.data.setOrigin(origin);
/*     */     }
/*  84 */     if (this.data.getTrigger() != null) {
/*  85 */       this.data.setEntityTarget(trigger);
/*     */     }
/*  87 */     this.data.setPower(am.getPower());
/*     */     
/*  89 */     if (transformer != null) {
/*  90 */       transformer.accept(this.data);
/*     */     }
/*     */     
/*  93 */     if (metadata != null) {
/*  94 */       for (Pair<String, Object> entry : metadata) {
/*  95 */         this.data.setMetadata((String)entry.getKey(), entry.getValue());
/*     */       }
/*     */     }
/*     */     
/*  99 */     if (this.data.getIsAsync()) {
/* 100 */       for (SkillMechanic ms : mechanics) {
/* 101 */         if (!ms.getRunAsync() && ms.isUsableFromCaster(this.data)) {
/* 102 */           ms.execute(this.data.deepClone().setIsAsync(false));
/*     */         }
/*     */       } 
/* 105 */       Schedulers.async().run(() -> {
/*     */             for (SkillMechanic ms : paramCollection) {
/*     */               if (ms.getRunAsync() && ms.isUsableFromCaster(this.data)) {
/*     */                 ms.execute(this.data);
/*     */               }
/*     */             } 
/*     */           });
/*     */     } else {
/* 113 */       for (SkillMechanic ms : mechanics) {
/* 114 */         if (ms.isUsableFromCaster(this.data)) {
/* 115 */           ms.execute(this.data);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled() {
/* 123 */     this.cancel = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCancelled() {
/* 128 */     return this.cancel;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\TriggeredSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */