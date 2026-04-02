/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "randommessage", aliases = {"randommsg", "rmsg", "rm"}, description = "Sends a random message to the target player")
/*    */ public class RandomMessageMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   protected static boolean noloop = false;
/* 22 */   protected List<PlaceholderString> messages = new ArrayList<>();
/*    */   
/*    */   public RandomMessageMechanic(String line, MythicLineConfig mlc) {
/* 25 */     super(line, mlc);
/* 26 */     this.target_creative = true;
/*    */     
/*    */     try {
/* 29 */       String m = mlc.getString(new String[] { "messages", "message", "msg", "msgs", "m" });
/*    */       
/* 31 */       for (String s : m.split(",")) {
/* 32 */         s = s.trim();
/* 33 */         this.messages.add(new PlaceholderString(s));
/* 34 */         MythicMobs.debug(2, "-- Loaded RandomMessage " + s.substring(1, s.length() - 1));
/*    */       } 
/* 36 */     } catch (Exception ex) {
/* 37 */       MythicLogger.errorMechanicConfig(this, mlc, "The 'messages' attribute is required.");
/* 38 */       this.messages.add(new PlaceholderString("INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD."));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 45 */     PlaceholderString message = this.messages.get(MythicMobs.r.nextInt(this.messages.size()));
/*    */     
/* 47 */     MythicMobs.debug(2, "Executing RandomMessage skill with message: " + message);
/*    */     
/* 49 */     if (target.isPlayer()) {
/* 50 */       target.asPlayer().sendMessage(message.get((PlaceholderMeta)data, target));
/* 51 */       return true;
/*    */     } 
/* 53 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\RandomMessageMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */