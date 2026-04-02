/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetMaxHealthMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   private final PlaceholderFloat amount;
/*    */   
/*    */   public SetMaxHealthMechanic(String line, MythicLineConfig mlc) {
/* 20 */     super(line, mlc);
/*    */     
/* 22 */     this.amount = mlc.getPlaceholderFloat(new String[] { "amount", "a" }, 1.0F, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 27 */     if (target.isLiving()) {
/* 28 */       target.setMaxHealth(this.amount.get((PlaceholderMeta)data, target));
/*    */     }
/* 30 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetMaxHealthMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */