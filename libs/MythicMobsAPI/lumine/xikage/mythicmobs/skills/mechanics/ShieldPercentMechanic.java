/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ 
/*    */ public class ShieldPercentMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected static boolean noloop = false;
/* 14 */   protected float multiplier = 0.1F;
/* 15 */   protected float maxShield = 0.1F;
/*    */   
/*    */   public ShieldPercentMechanic(String line, MythicLineConfig mlc) {
/* 18 */     super(line, mlc);
/*    */     
/* 20 */     this.multiplier = (float)mlc.getDouble(new String[] { "multiplier", "m" }, 0.1D);
/* 21 */     this.maxShield = mlc.getFloat(new String[] { "maxabsorb", "maxshield", "ma", "ms" }, this.multiplier);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 26 */     if (target.isDead()) return false;
/*    */     
/* 28 */     double shield = target.getMaxHealth() * this.multiplier;
/* 29 */     double max = target.getMaxHealth() * this.maxShield;
/* 30 */     double absorb = MythicMobs.inst().getVolatileCodeHandler().getEntityHandler().getEntityAbsorptionHearts(target) + shield;
/*    */     
/* 32 */     if (absorb < max) {
/* 33 */       MythicMobs.inst().getVolatileCodeHandler().getEntityHandler().setEntityAbsorptionHearts(target, (float)absorb);
/*    */     }
/*    */     
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ShieldPercentMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */