/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.types.FloatVariable;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.types.IntegerVariable;
/*    */ 
/*    */ public class VariableSubtractMechanic extends VariableMechanic implements ITargetedEntitySkill {
/*    */   protected float amount;
/*    */   
/*    */   public VariableSubtractMechanic(String skill, MythicLineConfig mlc) {
/* 19 */     super(skill, mlc);
/*    */     
/* 21 */     this.amount = mlc.getFloat(new String[] { "amount", "a" }, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 26 */     VariableRegistry variables = getVariableManager().getRegistry(this.scope, data, target);
/* 27 */     Variable var = variables.get(this.key);
/*    */     
/* 29 */     if (var == null) {
/* 30 */       return false;
/*    */     }
/*    */     
/* 33 */     if (var instanceof IntegerVariable) {
/* 34 */       int val = ((IntegerVariable)var).getValue();
/* 35 */       ((IntegerVariable)var).setValue(val - (int)this.amount);
/* 36 */     } else if (var instanceof FloatVariable) {
/* 37 */       float val = ((FloatVariable)var).getValue();
/* 38 */       ((FloatVariable)var).setValue(val - this.amount);
/*    */     } else {
/* 40 */       MythicLogger.errorMechanicConfig((SkillMechanic)this, this.config, "Could not perform math operation on non-numeric variable type");
/* 41 */       return false;
/*    */     } 
/* 43 */     handleDuration(var);
/* 44 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\VariableSubtractMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */