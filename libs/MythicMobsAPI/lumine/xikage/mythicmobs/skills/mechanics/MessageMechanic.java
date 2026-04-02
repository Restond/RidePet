/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "message", aliases = {"msg", "m"}, description = "Sends a message to the target entity")
/*    */ public class MessageMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected static boolean noloop = false;
/*    */   protected PlaceholderString message;
/*    */   
/*    */   public MessageMechanic(String line, MythicLineConfig mlc) {
/* 21 */     super(line, mlc);
/* 22 */     this.target_creative = true;
/*    */     
/*    */     try {
/* 25 */       this.message = mlc.getPlaceholderString(new String[] { "message", "msg", "m" }, null, new String[0]);
/* 26 */     } catch (Exception ex) {
/* 27 */       this.message = null;
/* 28 */       ex.printStackTrace();
/*    */     } 
/* 30 */     if (this.message == null) {
/* 31 */       MythicLogger.errorMechanicConfig(this, mlc, "The 'message' attribute is required.");
/* 32 */       this.message = PlaceholderString.of("INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD.");
/*    */     } else {
/* 34 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Loaded message skill with message {0}", new Object[] { this.message.toString() });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 40 */     if (target.isPlayer()) {
/* 41 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Sending message {0}", new Object[] { this.message });
/* 42 */       target.asPlayer().sendMessage(this.message.get((PlaceholderMeta)data, target));
/* 43 */       return true;
/*    */     } 
/* 45 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Failed to send message to non-player", new Object[0]);
/* 46 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\MessageMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */