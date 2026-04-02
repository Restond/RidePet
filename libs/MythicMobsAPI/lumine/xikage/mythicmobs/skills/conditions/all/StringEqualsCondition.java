/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ISkillMetaCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "stringEquals", aliases = {"stringEq"}, version = "4.6", description = "Checks if value1 equals value2. Both values can use variables and placeholders.")
/*    */ public class StringEqualsCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition, ILocationCondition, ISkillMetaCondition {
/*    */   @MythicField(name = "value1", aliases = {"val1", "v1"}, description = "")
/*    */   private PlaceholderString value1;
/*    */   @MythicField(name = "value2", aliases = {"val2", "v2"}, description = "")
/*    */   private PlaceholderString value2;
/*    */   
/*    */   public StringEqualsCondition(String line, MythicLineConfig mlc) {
/* 27 */     super(line);
/*    */     
/*    */     try {
/* 30 */       this.value1 = PlaceholderString.of(mlc.getString(new String[] { "value1", "val1", "v1", "string", "s" }, null, new String[0]));
/* 31 */     } catch (Exception ex) {
/* 32 */       MythicLogger.errorConditionConfig(this, mlc, "Variable name must be set.");
/*    */       
/*    */       return;
/*    */     } 
/*    */     try {
/* 37 */       this.value2 = PlaceholderString.of(mlc.getString(new String[] { "value2", "val2", "v2", "value", "val", "v", "equals", "eq", "e" }, null, new String[0]));
/* 38 */     } catch (Exception ex) {
/* 39 */       MythicLogger.errorConditionConfig(this, mlc, "Variable name must be set.");
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 47 */     String compare1 = this.value1.get(target);
/* 48 */     String compare2 = this.value2.get(target);
/*    */     
/* 50 */     return compare1.equals(compare2);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation target) {
/* 55 */     String compare1 = this.value1.get();
/* 56 */     String compare2 = this.value2.get();
/*    */     
/* 58 */     return compare1.equals(compare2);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(SkillMetadata meta) {
/* 63 */     String compare1 = this.value1.get((PlaceholderMeta)meta, meta.getCaster().getEntity());
/* 64 */     String compare2 = this.value2.get((PlaceholderMeta)meta, meta.getCaster().getEntity());
/*    */     
/* 66 */     return compare1.equals(compare2);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\StringEqualsCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */