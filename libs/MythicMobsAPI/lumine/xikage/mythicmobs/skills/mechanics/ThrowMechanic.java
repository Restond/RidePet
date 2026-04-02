/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.SkillAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "throw", description = "Throws the target entity")
/*    */ public class ThrowMechanic extends SkillMechanic implements ITargetedEntitySkill {
/*    */   protected PlaceholderFloat velocity;
/*    */   
/*    */   public ThrowMechanic(String line, MythicLineConfig mlc) {
/* 18 */     super(line, mlc);
/*    */     
/* 20 */     this.velocity = mlc.getPlaceholderFloat(new String[] { "velocity", "v" }, 1.0F, new String[0]);
/* 21 */     this.velocityY = mlc.getPlaceholderFloat(new String[] { "velocityy", "yvelocity", "vy", "yv" }, 1.0F, new String[0]);
/*    */   }
/*    */   protected PlaceholderFloat velocityY;
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 26 */     float velocity = this.velocity.get((PlaceholderMeta)data, target) / 10.0F;
/* 27 */     float velocityY = this.velocityY.get((PlaceholderMeta)data, target) / 10.0F;
/* 28 */     SkillAdapter.get().throwSkill(data.getCaster().getLocation(), target, velocity, velocityY);
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ThrowMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */