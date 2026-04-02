/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.IMetaSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import java.util.Optional;
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "metaskill", aliases = {"skill", "meta"}, description = "Executes a metaskill")
/*     */ public class MetaSkillMechanic
/*     */   extends SkillMechanic
/*     */   implements IMetaSkill
/*     */ {
/*     */   protected String skillName;
/*     */   protected Optional<Skill> metaskill;
/*     */   
/*     */   public MetaSkillMechanic(String skill, MythicLineConfig mlc) {
/*  24 */     super(skill, mlc);
/*  25 */     this.target_creative = true;
/*     */     
/*  27 */     this.skillName = mlc.getString(new String[] { "skill", "s", "meta", "m", "mechanics", "$", "()" });
/*     */     
/*  29 */     getPlugin().getSkillManager().queueSecondPass(() -> {
/*     */           MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Loading MetaSkillMechanic -> {0}", new Object[] { this.skillName });
/*     */           this.metaskill = getPlugin().getSkillManager().getSkill(this.skillName);
/*     */           if (!this.metaskill.isPresent()) {
/*     */             MythicLogger.errorMechanicConfig(this, paramMythicLineConfig, "Could not find MetaSkill " + this.skillName);
/*     */           } else {
/*     */             MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ Loaded MetaSkillMechanic successfully", new Object[0]);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public MetaSkillMechanic(String skill, String skillName, MythicLineConfig mlc) {
/*  42 */     super(skill, mlc);
/*  43 */     this.target_creative = true;
/*     */     
/*  45 */     this.skillName = skillName;
/*     */     
/*  47 */     MythicMobs.inst().getSkillManager().queueSecondPass(() -> {
/*     */           MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Loading MetaSkillMechanic -> {0}", new Object[] { this.skillName });
/*     */           this.metaskill = MythicMobs.inst().getSkillManager().getSkill(this.skillName);
/*     */           if (!this.metaskill.isPresent()) {
/*     */             MythicLogger.errorMechanicConfig(this, paramMythicLineConfig, "Could not find MetaSkill " + this.skillName);
/*     */           } else {
/*     */             MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ Loaded MetaSkillMechanic successfully", new Object[0]);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Skill getSkill() {
/*  64 */     return this.metaskill.get();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cast(SkillMetadata data) {
/*  70 */     if (this.metaskill.isPresent()) {
/*  71 */       Skill ms = this.metaskill.get();
/*  72 */       if (ms.isUsable(data)) {
/*  73 */         if (ConfigManager.debugLevel > 0) {
/*  74 */           if (data.getEntityTargets() != null) {
/*  75 */             MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Executing MetaSkill (et: {0}) -> {1}", new Object[] { Integer.valueOf(data.getEntityTargets().size()), this.skillName });
/*  76 */           } else if (data.getLocationTargets() != null) {
/*  77 */             MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Executing MetaSkill (lt: {0}) -> {1}", new Object[] { Integer.valueOf(data.getLocationTargets().size()), this.skillName });
/*     */           } else {
/*  79 */             MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Executing MetaSkill (t: 0) -> {0}", new Object[] { this.skillName });
/*     */           } 
/*     */         }
/*  82 */         if (this.forceSync == true && data.getIsAsync()) {
/*  83 */           data.setIsAsync(false);
/*     */           
/*  85 */           Scheduler.runSync(() -> {
/*     */                 paramSkillMetadata.setIsAsync(false);
/*     */                 MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "Running Skill {0} Sync from MetaSkillMechanic", new Object[] { this.skillName });
/*     */                 paramSkill.execute(paramSkillMetadata);
/*     */               });
/*     */         } else {
/*  91 */           MythicLogger.debug(MythicLogger.DebugLevel.SKILL, "Running Skill {0} Async from MetaSkillMechanic", new Object[] { this.skillName });
/*  92 */           ms.execute(data);
/*     */         } 
/*     */       } else {
/*  95 */         MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "MetaSkill {0} is currently unusable", new Object[] { this.skillName });
/*  96 */         return false;
/*     */       } 
/*     */     } else {
/*  99 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "MetaSkill {0} was not found on load", new Object[] { this.skillName });
/*     */     } 
/*     */     
/* 102 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\MetaSkillMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */