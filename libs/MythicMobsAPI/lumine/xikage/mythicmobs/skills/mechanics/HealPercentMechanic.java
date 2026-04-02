/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "healpercent", aliases = {"percentheal", "hp"}, description = "Heals the target entity for a percentage of their health")
/*    */ public class HealPercentMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected static boolean noloop = false;
/* 16 */   protected float multiplier = 0.1F;
/*    */   protected boolean overheal = false;
/* 18 */   protected float maxOverheal = 0.1F;
/*    */   
/*    */   public HealPercentMechanic(String line, MythicLineConfig mlc) {
/* 21 */     super(line, mlc);
/*    */     
/* 23 */     this.multiplier = (float)mlc.getDouble(new String[] { "multiplier", "m" }, 0.1D);
/* 24 */     this.overheal = mlc.getBoolean("overheal", false);
/* 25 */     this.maxOverheal = mlc.getFloat(new String[] { "maxoverheal", "maxabsorb", "maxshield", "mo", "ma", "ms" }, this.multiplier);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 30 */     if (target.isDead()) return false;
/*    */     
/* 32 */     double heal = target.getMaxHealth() * this.multiplier;
/* 33 */     double health = target.getHealth() + heal;
/*    */     
/* 35 */     if (health >= target.getMaxHealth()) {
/* 36 */       if (this.overheal == true) {
/* 37 */         double max = target.getMaxHealth() * this.maxOverheal;
/* 38 */         double diff = health - target.getMaxHealth();
/* 39 */         double absorb = MythicMobs.inst().getVolatileCodeHandler().getEntityHandler().getEntityAbsorptionHearts(target) + diff;
/*    */         
/* 41 */         if (absorb < max) {
/* 42 */           MythicMobs.inst().getVolatileCodeHandler().getEntityHandler().setEntityAbsorptionHearts(target, (float)absorb);
/*    */         }
/* 44 */         target.setHealth(target.getMaxHealth());
/*    */       } else {
/* 46 */         target.setHealth(target.getMaxHealth());
/*    */       } 
/*    */     } else {
/* 49 */       if (health < 0.0D) {
/* 50 */         health = 0.0D;
/*    */       }
/* 52 */       target.setHealth(health);
/*    */     } 
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\HealPercentMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */