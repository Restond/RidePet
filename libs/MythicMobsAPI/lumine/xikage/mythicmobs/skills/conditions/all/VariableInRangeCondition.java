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
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "variableInRange", aliases = {"varinrange", "varrange"}, version = "4.6", description = "Checks if the given numeric variable is within a certain range.")
/*    */ public class VariableInRangeCondition
/*    */   extends VariableCondition implements IEntityCondition, ILocationCondition, ISkillMetaCondition {
/*    */   @MythicField(name = "value", aliases = {"val", "v"}, description = "A number range to match")
/*    */   private RangedDouble value;
/*    */   
/*    */   public VariableInRangeCondition(String line, MythicLineConfig mlc) {
/* 26 */     super(line, mlc);
/*    */     
/*    */     try {
/* 29 */       this.value = new RangedDouble(mlc.getString(new String[] { "value", "val", "v", "range", "r" }, null, new String[0]));
/* 30 */     } catch (Exception ex) {
/* 31 */       MythicLogger.errorConditionConfig((SkillCondition)this, mlc, "Variable range must be set.");
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 38 */     VariableRegistry registry = getPlugin().getVariableManager().getRegistry(this.scope, target);
/* 39 */     float compare = registry.getFloat(this.key);
/* 40 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "VariableInRange: checking {0} == {1}", new Object[] { Float.valueOf(compare), this.value.toString() });
/*    */     try {
/* 42 */       return this.value.equals(Float.valueOf(compare));
/* 43 */     } catch (Exception ex) {
/* 44 */       MythicLogger.error("Failed to process VariableInRange condition.");
/* 45 */       if (ConfigManager.debugLevel > 0) {
/* 46 */         ex.printStackTrace();
/*    */       }
/*    */       
/* 49 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean check(AbstractLocation target) {
/* 54 */     VariableRegistry registry = getPlugin().getVariableManager().getRegistry(this.scope, target);
/* 55 */     float compare = registry.getFloat(this.key);
/* 56 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "VariableInRange: checking {0} == {1}", new Object[] { Float.valueOf(compare), this.value.toString() });
/*    */     try {
/* 58 */       return this.value.equals(Float.valueOf(compare));
/* 59 */     } catch (Exception ex) {
/* 60 */       MythicLogger.error("Failed to process VariableInRange condition.");
/* 61 */       if (ConfigManager.debugLevel > 0) {
/* 62 */         ex.printStackTrace();
/*    */       }
/*    */       
/* 65 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean check(SkillMetadata meta) {
/* 70 */     VariableRegistry registry = getPlugin().getVariableManager().getRegistry(this.scope, meta, meta.getCaster().getEntity());
/* 71 */     float compare = registry.getFloat(this.key);
/* 72 */     MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "VariableInRange: checking {0} == {1}", new Object[] { Float.valueOf(compare), this.value.toString() });
/*    */     try {
/* 74 */       return this.value.equals(Float.valueOf(compare));
/* 75 */     } catch (Exception ex) {
/* 76 */       MythicLogger.error("Failed to process VariableInRange condition.");
/* 77 */       if (ConfigManager.debugLevel > 0) {
/* 78 */         ex.printStackTrace();
/*    */       }
/*    */       
/* 81 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\VariableInRangeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */