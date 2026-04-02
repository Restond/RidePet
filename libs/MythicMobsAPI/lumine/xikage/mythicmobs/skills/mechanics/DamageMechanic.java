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
/*    */ @MythicMechanic(author = "Ashijin", name = "damage", aliases = {"d"}, description = "Deals damage to the target")
/*    */ public class DamageMechanic
/*    */   extends DamagingMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   protected PlaceholderDouble amount;
/*    */   
/*    */   public DamageMechanic(String line, MythicLineConfig mlc) {
/* 21 */     super(line, mlc);
/*    */     
/* 23 */     this.amount = PlaceholderDouble.of(mlc.getString(new String[] { "amount", "a" }, "1", new String[0]));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 28 */     if (target.isDead() || target.getHealth() <= 0.0D) return false; 
/* 29 */     if (data.getCaster().isUsingDamageSkill()) return false;
/*    */     
/* 31 */     double damage = this.amount.get((PlaceholderMeta)data, target) * data.getPower();
/*    */     
/* 33 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ DamageMechanic fired for {0} with {1} power", new Object[] { Double.valueOf(damage), Float.valueOf(data.getPower()) });
/* 34 */     doDamage(data.getCaster(), target, damage);
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DamageMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */