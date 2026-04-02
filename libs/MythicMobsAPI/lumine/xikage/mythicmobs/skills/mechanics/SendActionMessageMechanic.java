/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "sendactionmessage", aliases = {"actionmessage", "am"}, description = "Send an Action Bar message to the target player")
/*    */ public class SendActionMessageMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected PlaceholderString message;
/*    */   
/*    */   public SendActionMessageMechanic(String line, MythicLineConfig mlc) {
/* 23 */     super(line, mlc);
/* 24 */     this.target_creative = true;
/*    */     
/*    */     try {
/* 27 */       this.message = PlaceholderString.of(mlc.getString(new String[] { "message", "msg", "m" }));
/* 28 */     } catch (Exception ex) {
/* 29 */       MythicLogger.errorMechanicConfig(this, mlc, "The 'message' attribute is required.");
/* 30 */       this.message = PlaceholderString.of("INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD.");
/* 31 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 37 */     if (target.isPlayer()) {
/* 38 */       MythicMobs.inst().getVolatileCodeHandler().sendActionBarMessageToPlayer((Player)BukkitAdapter.adapt(target), this.message.get((PlaceholderMeta)data, target));
/* 39 */       return true;
/*    */     } 
/* 41 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SendActionMessageMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */