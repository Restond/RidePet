/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.holograms.types.SpeechBubble;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.SpeakMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpeechTracker
/*     */   extends Aura.AuraTracker
/*     */ {
/*     */   private SpeechBubble bubble;
/*     */   private String speech;
/*     */   
/*     */   public SpeechTracker(SkillMetadata data) {
/* 103 */     super((Aura)SpeakMechanic.this, data);
/*     */     
/* 105 */     this.speech = SpeakMechanic.this.bubbleMessage.get((PlaceholderMeta)data);
/* 106 */     if (start()) {
/* 107 */       SpeakMechanic.this.sendChatSpeech(this.skillMetadata, SpeakMechanic.this.chatMessage.get((PlaceholderMeta)data));
/*     */     }
/*     */   }
/*     */   
/*     */   public SpeechTracker(AbstractEntity target, SkillMetadata data) {
/* 112 */     super((Aura)SpeakMechanic.this, data.getCaster(), target, data);
/*     */     
/* 114 */     this.speech = SpeakMechanic.this.bubbleMessage.get((PlaceholderMeta)data, target);
/* 115 */     if (start()) {
/* 116 */       SpeakMechanic.this.sendChatSpeech(this.skillMetadata, SpeakMechanic.this.chatMessage.get((PlaceholderMeta)data, target));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void auraStart() {
/* 122 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Creating speech bubble...", new Object[0]);
/* 123 */     this.bubble = AbstractSkill.getPlugin().getHologramManager().createSpeechBubble(this.skillMetadata.getCaster());
/* 124 */     this.bubble.setLineLength(SpeakMechanic.this.maxLineLength);
/* 125 */     this.bubble.setLinePrefix(SpeakMechanic.this.linePrefix.get());
/* 126 */     this.bubble.setText(this.speech);
/*     */     
/* 128 */     executeAuraSkill(SpeakMechanic.access$000(SpeakMechanic.this), this.skillMetadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public void auraTick() {
/* 133 */     executeAuraSkill(SpeakMechanic.access$100(SpeakMechanic.this), this.skillMetadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public void auraStop() {
/* 138 */     this.bubble.terminate();
/* 139 */     if (SpeakMechanic.access$200(SpeakMechanic.this))
/* 140 */       executeAuraSkill(SpeakMechanic.access$300(SpeakMechanic.this), this.skillMetadata); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SpeakMechanic$SpeechTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */