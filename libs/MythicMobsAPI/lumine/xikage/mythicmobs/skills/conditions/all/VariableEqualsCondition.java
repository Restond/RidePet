/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ISkillMetaCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "variableEquals", aliases = {"variableeq", "varequals", "vareq"}, version = "4.6", description = "Checks if the given variable has a particular value.")
/*    */ public class VariableEqualsCondition extends VariableCondition implements IEntityCondition, ILocationCondition, ISkillMetaCondition {
/*    */   @MythicField(name = "value", aliases = {"val", "v"}, description = "The value to match")
/*    */   private PlaceholderString value;
/*    */   
/*    */   public VariableEqualsCondition(String line, MythicLineConfig mlc) {
/* 25 */     super(line, mlc);
/*    */     
/*    */     try {
/* 28 */       this.value = PlaceholderString.of(mlc.getString(new String[] { "value", "val", "v" }, null, new String[0]));
/* 29 */     } catch (Exception ex) {
/* 30 */       MythicLogger.errorConditionConfig((SkillCondition)this, mlc, "Variable name must be set.");
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 37 */     VariableRegistry registry = getPlugin().getVariableManager().getRegistry(this.scope, target);
/* 38 */     String compare = this.value.get(target);
/*    */     
/*    */     try {
/* 41 */       return registry.getString(this.key).equals(compare);
/* 42 */     } catch (Exception ex) {
/* 43 */       MythicLogger.error("Failed to process VariableEquals condition.");
/* 44 */       if (ConfigManager.debugLevel > 0) {
/* 45 */         ex.printStackTrace();
/*    */       }
/*    */       
/* 48 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean check(AbstractLocation target) {
/* 53 */     VariableRegistry registry = getPlugin().getVariableManager().getRegistry(this.scope, target);
/* 54 */     String compare = this.value.toString();
/*    */     
/*    */     try {
/* 57 */       return registry.getString(this.key).equals(compare);
/* 58 */     } catch (Exception ex) {
/* 59 */       MythicLogger.error("Failed to process VariableEquals condition.");
/* 60 */       if (ConfigManager.debugLevel > 0) {
/* 61 */         ex.printStackTrace();
/*    */       }
/*    */       
/* 64 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean check(SkillMetadata meta) {
/* 69 */     VariableRegistry registry = getPlugin().getVariableManager().getRegistry(this.scope, meta, meta.getCaster().getEntity());
/* 70 */     String compare = this.value.toString();
/*    */     
/*    */     try {
/* 73 */       return registry.getString(this.key).equals(compare);
/* 74 */     } catch (Exception ex) {
/* 75 */       MythicLogger.error("Failed to process VariableEquals condition.");
/* 76 */       if (ConfigManager.debugLevel > 0) {
/* 77 */         ex.printStackTrace();
/*    */       }
/*    */       
/* 80 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\VariableEqualsCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */