/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.damage.DamagingMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "damagePercent", aliases = {"percentDamage"}, description = "Deals a percentage of the target's health in damage")
/*    */ public class DamagePercentMechanic
/*    */   extends DamagingMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected PlaceholderDouble percent;
/*    */   protected boolean currentHealth = false;
/*    */   
/*    */   public DamagePercentMechanic(String line, MythicLineConfig mlc) {
/* 21 */     super(line, mlc);
/*    */     
/* 23 */     this.percent = PlaceholderDouble.of(mlc.getString(new String[] { "percent", "p" }, "0.1", new String[0]));
/* 24 */     this.currentHealth = mlc.getBoolean(new String[] { "current", "ch", "c" }, false);
/*    */   }
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*    */     double damage;
/* 29 */     if (target.isDead() || target.getHealth() <= 0.0D) return false;
/*    */ 
/*    */     
/* 32 */     if (this.currentHealth) {
/* 33 */       damage = target.getHealth() * this.percent.get((PlaceholderMeta)data, target) * this.power;
/*    */     } else {
/* 35 */       damage = target.getMaxHealth() * this.percent.get((PlaceholderMeta)data, target) * this.power;
/*    */     } 
/*    */     
/* 38 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ DamagePercentMechanic fired for {0} with {1} power", new Object[] { Double.valueOf(damage), Float.valueOf(data.getPower()) });
/* 39 */     doDamage(data.getCaster(), target, damage);
/* 40 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DamagePercentMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */