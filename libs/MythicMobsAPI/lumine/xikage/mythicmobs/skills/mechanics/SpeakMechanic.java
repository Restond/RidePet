/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "speak", aliases = {"speech"}, description = "Makes the caster speak using chat and speech bubbles")
/*    */ public class SpeakMechanic
/*    */   extends Aura
/*    */   implements INoTargetSkill, ITargetedEntitySkill
/*    */ {
/*    */   protected PlaceholderString chatMessage;
/*    */   protected PlaceholderString bubbleMessage;
/*    */   protected float offset;
/*    */   protected int radius;
/*    */   protected int maxLineLength;
/*    */   protected boolean animateWords;
/*    */   protected boolean animateLetters;
/*    */   protected PlaceholderString linePrefix;
/*    */   
/*    */   public SpeakMechanic(String skill, MythicLineConfig mlc) {
/* 34 */     super(skill, mlc);
/* 35 */     this.ASYNC_SAFE = false;
/* 36 */     this.target_creative = true;
/*    */     
/* 38 */     this.auraName = Optional.of("#speaking");
/* 39 */     this.charges = 1;
/* 40 */     this.maxStacks = 1;
/* 41 */     this.mergeSameCaster = false;
/* 42 */     this.overwriteCaster = true;
/* 43 */     this.refreshDuration = false;
/*    */     
/* 45 */     this.offset = mlc.getFloat(new String[] { "offset", "o" }, 0.6F);
/* 46 */     this.radius = mlc.getInteger(new String[] { "radius", "r" }, 12);
/* 47 */     this.maxLineLength = mlc.getInteger(new String[] { "maxlinelength", "linelength", "ll", "mll", "ml" }, 22);
/* 48 */     this.linePrefix = mlc.getPlaceholderString(new String[] { "lineprefix", "lp" }, "&f", new String[0]);
/*    */     
/* 50 */     PlaceholderString message = mlc.getPlaceholderString(new String[] { "message", "msg", "m" }, null, new String[0]);
/* 51 */     String chatPrefix = mlc.getString(new String[] { "chatprefix", "cp" }, "<caster.name>&f<&co> ", new String[0]);
/*    */     
/* 53 */     if (message == null) {
/* 54 */       MythicLogger.errorMechanicConfig((SkillMechanic)this, mlc, "The 'message' attribute is required.");
/* 55 */       this.chatMessage = PlaceholderString.of("INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD.");
/* 56 */       this.bubbleMessage = PlaceholderString.of("INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD.");
/*    */     } else {
/* 58 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Loaded Speak mechanic with message {0}", new Object[] { message.toString() });
/* 59 */       this.chatMessage = PlaceholderString.of(chatPrefix + message);
/* 60 */       this.bubbleMessage = message;
/*    */     } 
/*    */     
/* 63 */     this.duration = mlc.getInteger(new String[] { "ticks", "t", "duration", "d", "time", "t" }, message.toString().length() * 4);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 68 */     if (!getPlugin().getHologramManager().isActive()) {
/* 69 */       sendChatSpeech(data, this.chatMessage.get((PlaceholderMeta)data));
/* 70 */       return false;
/*    */     } 
/* 72 */     new SpeechTracker(this, data);
/* 73 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 79 */     if (!getPlugin().getHologramManager().isActive()) {
/* 80 */       sendChatSpeech(data, this.chatMessage.get((PlaceholderMeta)data, target));
/* 81 */       return false;
/*    */     } 
/* 83 */     new SpeechTracker(this, target, data);
/* 84 */     return true;
/*    */   }
/*    */   
/*    */   public void sendChatSpeech(SkillMetadata data, String message) {
/* 88 */     for (AbstractPlayer p : MythicMobs.inst().getEntityManager().getPlayers(data.getCaster().getEntity().getWorld())) {
/* 89 */       if (p.getWorld().equals(data.getCaster().getEntity().getWorld()) && 
/* 90 */         data.getCaster().getEntity().getLocation().distanceSquared(p.getLocation()) < Math.pow(this.radius, 2.0D)) {
/* 91 */         MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Sending speech to {0}: {1}", new Object[] { p.getName(), message });
/* 92 */         p.sendMessage(message);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SpeakMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */