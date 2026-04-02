/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.damage.DamagingMechanic;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "baseDamage", aliases = {"bd", "weaponDamage", "wd"}, description = "Deals a percent of the mob's damage stat as damage")
/*    */ public class BaseDamageMechanic
/*    */   extends DamagingMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/* 20 */   protected double multiplier = 1.0D;
/*    */   
/*    */   public BaseDamageMechanic(String line, MythicLineConfig mlc) {
/* 23 */     super(line, mlc);
/* 24 */     this.ASYNC_SAFE = false;
/*    */     
/* 26 */     this.multiplier = mlc.getDouble(new String[] { "multiplier", "m" }, 1.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 31 */     if (target.isDead() || target.getHealth() <= 0.0D) return false;
/*    */     
/* 33 */     SkillCaster caster = data.getCaster();
/*    */     
/* 35 */     if (caster instanceof ActiveMob) {
/* 36 */       ActiveMob am = (ActiveMob)caster;
/* 37 */       if (am.isUsingDamageSkill()) return false;
/*    */       
/* 39 */       double damage = am.getDamage() * this.multiplier * data.getPower();
/*    */       
/* 41 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ BaseDamageMechanic fired for {0} with {1} power", new Object[] { Double.valueOf(damage), Float.valueOf(data.getPower()) });
/*    */       
/* 43 */       doDamage(data.getCaster(), target, damage);
/*    */     } else {
/* 45 */       double damage = this.multiplier * data.getPower();
/*    */       
/* 47 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ BaseDamageMechanic fired for {0} with {1} power", new Object[] { Double.valueOf(damage), Float.valueOf(data.getPower()) });
/*    */       
/* 49 */       doDamage(data.getCaster(), target, damage);
/*    */     } 
/* 51 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BaseDamageMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */