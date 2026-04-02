/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityComparisonCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import java.util.UUID;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "owner", aliases = {}, description = "Checks if the target entity is the owner of the caster")
/*    */ public class OwnerCondition extends SkillCondition implements IEntityComparisonCondition {
/*    */   public OwnerCondition(String line, MythicLineConfig mlc) {
/* 15 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity, AbstractEntity target) {
/* 20 */     if (MythicMobs.inst().getMobManager().isActiveMob(entity)) {
/* 21 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(entity);
/*    */       
/* 23 */       if (am.getOwner().isPresent()) {
/* 24 */         return ((UUID)am.getOwner().get()).equals(target.getUniqueId());
/*    */       }
/*    */     } 
/* 27 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\OwnerCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */