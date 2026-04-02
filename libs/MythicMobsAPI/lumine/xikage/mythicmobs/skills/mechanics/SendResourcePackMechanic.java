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
/*    */ 
/*    */ 
/*    */ public class SendResourcePackMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   private PlaceholderString url;
/*    */   private PlaceholderString hash;
/*    */   
/*    */   public SendResourcePackMechanic(String line, MythicLineConfig mlc) {
/* 22 */     super(line, mlc);
/* 23 */     this.target_creative = true;
/*    */     
/* 25 */     this.url = mlc.getPlaceholderString(new String[] { "url", "u", "link" }, null, new String[0]);
/* 26 */     this.hash = mlc.getPlaceholderString(new String[] { "hash", "h" }, "mythicmobs", new String[0]);
/*    */     
/* 28 */     if (this.url == null) {
/* 29 */       MythicLogger.errorMechanicConfig(this, mlc, "'URL' attribute must be set to a valid resource pack URL");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 35 */     if (!target.isPlayer()) {
/* 36 */       return false;
/*    */     }
/* 38 */     MythicMobs.inst().getVolatileCodeHandler().sendResourcePack(target.asPlayer(), this.url.get((PlaceholderMeta)data, target), this.hash.get((PlaceholderMeta)data, target));
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SendResourcePackMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */