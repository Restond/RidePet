/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ 
/*    */ public class ShieldMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected static boolean noloop = false;
/* 14 */   protected float amount = 1.0F;
/* 15 */   protected float maxShield = 1.0F;
/*    */   
/*    */   public ShieldMechanic(String line, MythicLineConfig mlc) {
/* 18 */     super(line, mlc);
/*    */     
/* 20 */     this.amount = (float)mlc.getDouble(new String[] { "amount", "a" }, 1.0D);
/* 21 */     this.maxShield = mlc.getFloat(new String[] { "maxabsorb", "maxshield", "ma", "ms" }, this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 26 */     if (target.isDead()) return false;
/*    */     
/* 28 */     double absorb = (MythicMobs.inst().getVolatileCodeHandler().getEntityHandler().getEntityAbsorptionHearts(target) + this.amount);
/*    */     
/* 30 */     if (absorb < this.maxShield) {
/* 31 */       MythicMobs.inst().getVolatileCodeHandler().getEntityHandler().setEntityAbsorptionHearts(target, (float)absorb);
/*    */     }
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ShieldMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */