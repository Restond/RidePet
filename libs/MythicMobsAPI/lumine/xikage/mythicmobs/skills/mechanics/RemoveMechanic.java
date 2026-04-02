/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "remove", description = "Removes the target entity from existence")
/*    */ public class RemoveMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill {
/*    */   public RemoveMechanic(String line, MythicLineConfig mlc) {
/* 14 */     super(line, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 19 */     if (target.isPlayer()) {
/* 20 */       return false;
/*    */     }
/* 22 */     target.remove();
/* 23 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\RemoveMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */