/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ISkillMetaCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "DamageCause", aliases = {}, version = "4.7", description = "Checks the damage cause of the current skill tree. Only works with onDamaged trigger or aura.")
/*    */ public class DamageCauseCondition extends SkillCondition implements ISkillMetaCondition {
/*    */   @MythicField(name = "damagecause", aliases = {"cause", "c"}, description = "The damage cause to match")
/*    */   protected PlaceholderString cause;
/*    */   
/*    */   public DamageCauseCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/*    */     
/* 20 */     this.cause = mlc.getPlaceholderString(new String[] { "damagecause", "cause", "c" }, "ENTITY_ATTACK", new String[] { this.conditionVar });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(SkillMetadata meta) {
/* 25 */     if (meta.getVariables().has("damage-cause")) {
/* 26 */       String cause = (String)meta.getVariables().get("damage-cause").get();
/* 27 */       return this.cause.get((PlaceholderMeta)meta).equalsIgnoreCase(cause);
/*    */     } 
/* 29 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\DamageCauseCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */