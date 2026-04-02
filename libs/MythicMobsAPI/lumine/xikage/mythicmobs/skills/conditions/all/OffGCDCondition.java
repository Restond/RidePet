/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ICasterCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "offGCD", aliases = {}, description = "Checks if the target mob has an active Global Cooldown")
/*    */ public class OffGCDCondition
/*    */   extends SkillCondition implements ICasterCondition, IEntityCondition {
/*    */   public OffGCDCondition(String line, MythicLineConfig mlc) {
/* 17 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 22 */     if (MythicMobs.inst().getMobManager().isActiveMob(entity)) {
/* 23 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(entity);
/* 24 */       return (am.getGlobalCooldown() <= 0);
/*    */     } 
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(SkillCaster caster) {
/* 31 */     return (caster.getGlobalCooldown() <= 0);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\OffGCDCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */