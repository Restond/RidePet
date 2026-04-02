/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "effect:spin", aliases = {"spin", "e:spin"}, description = "Forces the target entity to spin")
/*    */ public class SpinEffect
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   protected int duration;
/*    */   protected int velocity;
/*    */   
/*    */   public SpinEffect(String skill, MythicLineConfig mlc) {
/* 22 */     super(skill, mlc);
/*    */     
/* 24 */     this.duration = mlc.getInteger(new String[] { "duration", "d" }, 40);
/* 25 */     this.velocity = mlc.getInteger(new String[] { "velocity", "v" }, 18);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 30 */     new Animator(this, target, this.duration, this.velocity);
/* 31 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SpinEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */