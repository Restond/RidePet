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
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableType;
/*    */ 
/*    */ public class VariableSetMechanic extends VariableMechanic implements ITargetedEntitySkill {
/*    */   protected PlaceholderString value;
/*    */   protected VariableType type;
/*    */   
/*    */   public VariableSetMechanic(String skill, MythicLineConfig mlc) {
/* 21 */     super(skill, mlc);
/*    */     
/*    */     try {
/* 24 */       this.value = PlaceholderString.of(mlc.getString(new String[] { "value", "val", "v" }, null, new String[0]));
/* 25 */     } catch (Exception ex) {
/* 26 */       MythicLogger.errorMechanicConfig((SkillMechanic)this, mlc, "Variable value must be set.");
/*    */       
/*    */       return;
/*    */     } 
/* 30 */     String strType = mlc.getString(new String[] { "type", "t" }, VariableType.INTEGER.toString(), new String[0]);
/*    */     try {
/* 32 */       this.type = VariableType.valueOf(strType.toUpperCase());
/* 33 */     } catch (Exception ex) {
/* 34 */       MythicLogger.errorMechanicConfig((SkillMechanic)this, mlc, "'" + strType + "' is not a valid variable type.");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 40 */     VariableRegistry variables = getVariableManager().getRegistry(this.scope, data, target);
/*    */     
/* 42 */     if (variables != null) {
/* 43 */       Variable var = Variable.ofType(this.type, this.value.get((PlaceholderMeta)data, target), this.duration);
/* 44 */       variables.put(this.key, var);
/*    */     } else {
/* 46 */       MythicLogger.errorMechanicConfig((SkillMechanic)this, this.config, "Failed to get variable registry");
/*    */     } 
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\VariableSetMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */