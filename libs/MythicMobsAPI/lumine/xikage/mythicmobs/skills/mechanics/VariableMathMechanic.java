/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.types.FloatVariable;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.types.IntegerVariable;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import net.objecthunter.exp4j.ExpressionBuilder;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "variableMath", aliases = {"varMath", "mathVariable", "mathVar"}, description = "Sets a variable to the result of a math equation, where 'x' is the variable's current value")
/*    */ public class VariableMathMechanic
/*    */   extends VariableMechanic implements ITargetedEntitySkill {
/*    */   public VariableMathMechanic(String skill, MythicLineConfig mlc) {
/* 23 */     super(skill, mlc);
/*    */     
/* 25 */     this.equation = mlc.getPlaceholderString(new String[] { "equation", "eq", "e" }, "x", new String[0]);
/*    */   }
/*    */   protected PlaceholderString equation;
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 30 */     VariableRegistry variables = getVariableManager().getRegistry(this.scope, data, target);
/* 31 */     Variable var = variables.get(this.key);
/*    */     
/* 33 */     if (var == null) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     String eq = this.equation.get((PlaceholderMeta)data, target);
/*    */     
/* 39 */     if (var instanceof IntegerVariable) {
/* 40 */       int val = ((IntegerVariable)var).getValue();
/* 41 */       double amount = (new ExpressionBuilder(eq)).variable("x").build().setVariable("x", val).evaluate();
/* 42 */       ((IntegerVariable)var).setValue((int)amount);
/* 43 */     } else if (var instanceof FloatVariable) {
/* 44 */       float val = ((FloatVariable)var).getValue();
/* 45 */       double amount = (new ExpressionBuilder(eq)).variable("x").build().setVariable("x", val).evaluate();
/* 46 */       ((FloatVariable)var).setValue((float)amount);
/*    */     } else {
/* 48 */       MythicLogger.errorMechanicConfig((SkillMechanic)this, this.config, "Could not perform math operation on non-numeric variable type");
/* 49 */       return false;
/*    */     } 
/* 51 */     handleDuration(var);
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\VariableMathMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */