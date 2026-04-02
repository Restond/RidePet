/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ISkillMetaCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.VariableRegistry;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "variableIsSet", aliases = {"varisset", "varset"}, version = "4.6", description = "Checks if the given variable is set.")
/*    */ public class VariableIsSetCondition
/*    */   extends VariableCondition implements IEntityCondition, ILocationCondition, ISkillMetaCondition {
/*    */   public VariableIsSetCondition(String line, MythicLineConfig mlc) {
/* 20 */     super(line, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 25 */     VariableRegistry registry = getPlugin().getVariableManager().getRegistry(this.scope, target);
/*    */     
/*    */     try {
/* 28 */       return registry.has(this.key);
/* 29 */     } catch (Exception ex) {
/* 30 */       MythicLogger.error("Failed to process VariableIsSet condition.");
/* 31 */       if (ConfigManager.debugLevel > 0) {
/* 32 */         ex.printStackTrace();
/*    */       }
/*    */       
/* 35 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean check(AbstractLocation target) {
/* 40 */     VariableRegistry registry = getPlugin().getVariableManager().getRegistry(this.scope, target);
/*    */     
/*    */     try {
/* 43 */       return registry.has(this.key);
/* 44 */     } catch (Exception ex) {
/* 45 */       MythicLogger.error("Failed to process VariableIsSet condition.");
/* 46 */       if (ConfigManager.debugLevel > 0) {
/* 47 */         ex.printStackTrace();
/*    */       }
/*    */       
/* 50 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean check(SkillMetadata meta) {
/* 55 */     VariableRegistry registry = getPlugin().getVariableManager().getRegistry(this.scope, meta, meta.getCaster().getEntity());
/*    */     
/*    */     try {
/* 58 */       return registry.has(this.key);
/* 59 */     } catch (Exception ex) {
/* 60 */       MythicLogger.error("Failed to process VariableIsSet condition.");
/* 61 */       if (ConfigManager.debugLevel > 0) {
/* 62 */         ex.printStackTrace();
/*    */       }
/*    */       
/* 65 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\VariableIsSetCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */