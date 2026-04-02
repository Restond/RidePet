/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "fly", description = "Aura that enables flying on the target entity")
/*    */ public class FlyMechanic extends Aura implements ITargetedEntitySkill {
/*    */   public FlyMechanic(String line, MythicLineConfig mlc) {
/* 14 */     super(line, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 19 */     if (target.isPlayer()) {
/* 20 */       new FlyMechanicTracker(this, target, data);
/*    */     }
/* 22 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\FlyMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */