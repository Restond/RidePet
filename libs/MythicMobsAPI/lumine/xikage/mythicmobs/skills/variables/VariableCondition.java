/*    */ package lumine.xikage.mythicmobs.skills.variables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableScope;
/*    */ 
/*    */ public abstract class VariableCondition
/*    */   extends SkillCondition {
/*    */   protected String key;
/* 11 */   protected VariableScope scope = VariableScope.SKILL;
/*    */   
/*    */   public VariableCondition(String line, MythicLineConfig mlc) {
/* 14 */     super(line);
/*    */     
/*    */     try {
/* 17 */       this.key = mlc.getString(new String[] { "name", "n", "variable", "var", "key", "k" }, this.conditionVar, new String[0]);
/* 18 */     } catch (Exception ex) {
/* 19 */       MythicLogger.errorConditionConfig(this, mlc, "Variable name must be set.");
/*    */       
/*    */       return;
/*    */     } 
/* 23 */     if (this.key == null) {
/* 24 */       MythicLogger.errorConditionConfig(this, mlc, "Variable name must be set.");
/*    */       
/*    */       return;
/*    */     } 
/* 28 */     String prefixVar = VariableScope.SKILL.toString();
/* 29 */     if (this.key.contains(".")) {
/* 30 */       String[] split = this.key.split("\\.");
/* 31 */       prefixVar = split[0].toUpperCase();
/* 32 */       this.key = split[1];
/*    */     } 
/*    */     
/* 35 */     String strScope = mlc.getString(new String[] { "scope", "s" }, prefixVar, new String[0]);
/*    */     try {
/* 37 */       this.scope = VariableScope.valueOf(strScope.toUpperCase());
/* 38 */     } catch (Exception ex) {
/* 39 */       MythicLogger.errorConditionConfig(this, mlc, "'" + strScope + "' is not a valid variable scope.");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\VariableCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */