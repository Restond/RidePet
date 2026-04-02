/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "heal", aliases = {"h"}, description = "Heals the target entity")
/*    */ public class HealMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected static boolean noloop = false;
/*    */   protected PlaceholderDouble amount;
/*    */   protected boolean overheal = false;
/*    */   protected PlaceholderDouble maxOverheal;
/*    */   
/*    */   public HealMechanic(String line, MythicLineConfig mlc) {
/* 23 */     super(line, mlc);
/*    */     
/* 25 */     String strAmount = mlc.getString(new String[] { "amount", "a" }, "1", new String[0]);
/*    */     
/* 27 */     this.amount = PlaceholderDouble.of(strAmount);
/* 28 */     this.overheal = mlc.getBoolean(new String[] { "overheal", "oh" }, false);
/* 29 */     this.maxOverheal = PlaceholderDouble.of(mlc.getString(new String[] { "maxoverheal", "maxabsorb", "maxshield", "mo", "ma", "ms" }, strAmount, new String[0]));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 34 */     if (target.isDead()) return false;
/*    */     
/* 36 */     double health = target.getHealth() + this.amount.get((PlaceholderMeta)data, target);
/*    */     
/* 38 */     if (health >= target.getMaxHealth()) {
/* 39 */       if (this.overheal == true) {
/* 40 */         double diff = health - target.getMaxHealth();
/* 41 */         double absorb = MythicMobs.inst().getVolatileCodeHandler().getEntityHandler().getEntityAbsorptionHearts(target) + diff;
/*    */         
/* 43 */         if (absorb < this.maxOverheal.get((PlaceholderMeta)data, target)) {
/* 44 */           MythicMobs.inst().getVolatileCodeHandler().getEntityHandler().setEntityAbsorptionHearts(target, (float)absorb);
/*    */         }
/* 46 */         target.setHealth(target.getMaxHealth());
/*    */       } else {
/* 48 */         target.setHealth(target.getMaxHealth());
/*    */       } 
/*    */     } else {
/* 51 */       target.setHealth(health);
/*    */     } 
/* 53 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\HealMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */