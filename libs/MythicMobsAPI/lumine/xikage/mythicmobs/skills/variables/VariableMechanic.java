/*    */ package lumine.xikage.mythicmobs.skills.variables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableManager;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableScope;
/*    */ 
/*    */ public class VariableMechanic extends SkillMechanic {
/*    */   protected String key;
/*    */   
/*    */   public VariableMechanic(String skill, MythicLineConfig mlc) {
/* 14 */     super(skill, mlc);
/*    */     
/* 16 */     this.key = mlc.getString(new String[] { "name", "n", "variable", "var", "key", "k" }, null, new String[0]);
/*    */     
/* 18 */     if (this.key == null) {
/* 19 */       MythicLogger.errorMechanicConfig(this, mlc, "Variable name must be set.");
/*    */       
/*    */       return;
/*    */     } 
/* 23 */     String prefixVar = VariableScope.SKILL.toString();
/*    */     
/* 25 */     if (this.key.contains(".")) {
/* 26 */       String[] split = this.key.split("\\.");
/* 27 */       prefixVar = split[0].toUpperCase();
/* 28 */       this.key = split[1];
/*    */     } 
/*    */     
/* 31 */     String strScope = mlc.getString(new String[] { "scope", "s" }, prefixVar, new String[0]);
/*    */     try {
/* 33 */       this.scope = VariableScope.valueOf(strScope.toUpperCase());
/* 34 */     } catch (Exception ex) {
/* 35 */       MythicLogger.errorMechanicConfig(this, mlc, "'" + strScope + "' is not a valid variable scope.");
/*    */     } 
/*    */     
/* 38 */     boolean shouldSave = mlc.getBoolean("save", false);
/* 39 */     long duration = mlc.getLong(new String[] { "duration", "d", "expire", "e" }, 0L);
/*    */     
/* 41 */     if (duration == 0L) {
/* 42 */       this.duration = shouldSave ? 0L : 1L;
/*    */     } else {
/* 44 */       this.duration = System.currentTimeMillis() + duration * 50L;
/*    */     } 
/*    */   }
/*    */   protected VariableScope scope; protected long duration;
/*    */   public static VariableManager getVariableManager() {
/* 49 */     return getPlugin().getVariableManager();
/*    */   }
/*    */   
/*    */   protected void handleDuration(Variable var) {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\VariableMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */