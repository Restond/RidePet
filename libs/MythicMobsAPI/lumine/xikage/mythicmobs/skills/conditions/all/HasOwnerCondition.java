/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "hasOwner", aliases = {}, description = "Tests if the target mob has an owner")
/*    */ public class HasOwnerCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   public HasOwnerCondition(String line, MythicLineConfig mlc) {
/* 15 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 20 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(target);
/*    */     
/* 22 */     if (am == null) {
/* 23 */       return false;
/*    */     }
/* 25 */     return am.getOwner().isPresent();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HasOwnerCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */