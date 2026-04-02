/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "cast", description = "Casts a metaskill with various options")
/*    */ public class CastMechanic
/*    */   extends Aura
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*    */ {
/*    */   @MythicField(name = "onCast", aliases = {"oc"}, version = "4.6", description = "Skill to execute if the cast finishes successfully")
/* 27 */   protected Optional<Skill> onCastSkill = Optional.empty();
/*    */   
/*    */   @MythicField(name = "onInterrupted", aliases = {"oi"}, version = "4.6", description = "Skill to execute if the cast is interrupted")
/* 30 */   protected Optional<Skill> onInterruptedSkill = Optional.empty();
/*    */   
/*    */   @MythicField(name = "onNoTarget", aliases = {"ont"}, version = "4.6", description = "Skill to execute if no target is found")
/* 33 */   protected Optional<Skill> onNoTargetsSkill = Optional.empty();
/*    */   
/*    */   protected String onCastSkillName;
/*    */   
/*    */   protected String onInterruptedSkillName;
/*    */   protected String onNoTargetsSkillName;
/*    */   @MythicField(name = "skillname", aliases = {"sn"}, version = "4.6", description = "Display name of the spell in the cast bar")
/*    */   protected PlaceholderString spellName;
/*    */   @MythicField(name = "showCastBar", aliases = {"cb"}, defValue = "true", version = "4.6", description = "Whether to show the cast bar. Requires a compatible hologram plugin.")
/*    */   protected boolean showCastBar;
/*    */   @MythicField(name = "cancelOnMove", aliases = {"com"}, defValue = "false", version = "4.6", description = "Whether to cancel the aura if the caster moves")
/*    */   protected boolean cancelOnMove;
/*    */   protected String castingText;
/*    */   
/*    */   public CastMechanic(String skill, MythicLineConfig mlc) {
/* 48 */     super(skill, mlc);
/*    */     
/* 50 */     this.onCastSkillName = mlc.getString(new String[] { "oncastskill", "oncast", "oc" });
/* 51 */     this.onInterruptedSkillName = mlc.getString(new String[] { "oninterruptedskill", "oninterrupted", "oninterrupt", "oi" });
/* 52 */     this.onNoTargetsSkillName = mlc.getString(new String[] { "onnotargetsskill", "onnotargets", "onnotarget", "ont" });
/*    */     
/* 54 */     this.spellName = mlc.getPlaceholderString(new String[] { "skillname", "spellname", "sn" }, null, new String[0]);
/* 55 */     this.showCastBar = mlc.getBoolean(new String[] { "showcastbar", "castbar", "cb" }, true);
/* 56 */     this.cancelOnMove = mlc.getBoolean(new String[] { "cancelonmove", "com" }, false);
/*    */     
/* 58 */     this.auraName = Optional.of("#casting");
/* 59 */     this.charges = 1;
/* 60 */     this.maxStacks = 1;
/* 61 */     this.mergeAll = true;
/*    */     
/* 63 */     if (this.spellName == null) {
/* 64 */       this.castingText = ColorString.get("&eCasting...");
/*    */     } else {
/* 66 */       this.castingText = ColorString.get("&eCasting &6" + this.spellName.toString() + "&e...");
/*    */     } 
/*    */     
/* 69 */     getPlugin().getSkillManager().queueSecondPass(() -> {
/*    */           if (this.onCastSkillName != null)
/*    */             this.onCastSkill = getPlugin().getSkillManager().getSkill(this.onCastSkillName); 
/*    */           if (this.onInterruptedSkillName != null)
/*    */             this.onInterruptedSkill = getPlugin().getSkillManager().getSkill(this.onInterruptedSkillName); 
/*    */           if (this.onNoTargetsSkillName != null)
/*    */             this.onNoTargetsSkill = getPlugin().getSkillManager().getSkill(this.onNoTargetsSkillName); 
/*    */         });
/*    */   } public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 78 */     new CastTracker(this, target, data);
/* 79 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 84 */     new CastTracker(this, target, data);
/* 85 */     return true;
/*    */   }
/*    */   
/*    */   public void failNoTargets(SkillMetadata data) {
/* 89 */     if (this.onNoTargetsSkill == null)
/* 90 */       return;  if (!this.onNoTargetsSkill.isPresent())
/*    */       return; 
/* 92 */     data = data.deepClone();
/* 93 */     data.setEntityTarget(data.getCaster().getEntity());
/*    */     
/* 95 */     if (((Skill)this.onNoTargetsSkill.get()).isUsable(data))
/* 96 */       ((Skill)this.onNoTargetsSkill.get()).execute(data); 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\CastMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */