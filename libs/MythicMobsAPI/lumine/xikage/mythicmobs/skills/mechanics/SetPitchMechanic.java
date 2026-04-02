/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "setpitch", aliases = {}, description = "Modifies the head pitch of the target entity")
/*    */ public class SetPitchMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill {
/*    */   private final PlaceholderFloat amount;
/*    */   
/*    */   public SetPitchMechanic(String line, MythicLineConfig mlc) {
/* 18 */     super(line, mlc);
/*    */     
/* 20 */     this.amount = mlc.getPlaceholderFloat(new String[] { "amount", "a", "pitch", "p" }, 0.0F, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 25 */     if (target.isLiving()) {
/* 26 */       getPlugin().getVolatileCodeHandler().getEntityHandler().setEntityPitch(target, this.amount.get((PlaceholderMeta)data));
/*    */     }
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetPitchMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */