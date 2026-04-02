/*    */ package lumine.xikage.mythicmobs.skills.placeholders.parsers;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.PlaceholderAPISupport;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderParser;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.StaticInt;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RandomInt;
/*    */ import net.objecthunter.exp4j.ExpressionBuilder;
/*    */ 
/*    */ public class PlaceholderInt extends PlaceholderParser {
/*    */   public PlaceholderInt(String string) {
/* 15 */     super(string);
/*    */   }
/*    */   
/*    */   public int get() {
/* 19 */     String string = this.strValue;
/* 20 */     if (this.papi) {
/* 21 */       string = ((PlaceholderAPISupport)MythicMobs.inst().getCompatibility().getPlaceholderAPI().get()).parse(string);
/*    */     }
/* 23 */     if (this.doMath) {
/*    */       try {
/* 25 */         return (int)(new ExpressionBuilder(string)).build().evaluate();
/* 26 */       } catch (Exception ex) {
/* 27 */         MythicLogger.error("Invalid math operation on PlaceholderString: " + this.strValue);
/* 28 */         ex.printStackTrace();
/*    */       } 
/*    */     }
/* 31 */     return Integer.valueOf(string).intValue();
/*    */   }
/*    */   
/*    */   public int get(PlaceholderMeta meta) {
/* 35 */     return get(meta, meta.getTrigger());
/*    */   }
/*    */   
/*    */   public int get(AbstractEntity entity) {
/* 39 */     return get(null, entity);
/*    */   }
/*    */   
/*    */   public int get(PlaceholderMeta meta, AbstractEntity entity) {
/* 43 */     String string = parse(meta, entity);
/*    */     
/* 45 */     if (this.papi) {
/* 46 */       if (entity != null && entity.isPlayer()) {
/* 47 */         string = ((PlaceholderAPISupport)MythicMobs.inst().getCompatibility().getPlaceholderAPI().get()).parse(string, entity.asPlayer());
/*    */       } else {
/* 49 */         string = ((PlaceholderAPISupport)MythicMobs.inst().getCompatibility().getPlaceholderAPI().get()).parse(string);
/*    */       } 
/*    */     }
/*    */     
/* 53 */     if (this.doMath) {
/*    */       try {
/* 55 */         return (int)(new ExpressionBuilder(string)).build().evaluate();
/* 56 */       } catch (Exception ex) {
/* 57 */         MythicLogger.error("Invalid math operation on PlaceholderString: " + this.strValue);
/* 58 */         ex.printStackTrace();
/*    */       } 
/*    */     }
/*    */     
/* 62 */     return Integer.valueOf(string).intValue();
/*    */   }
/*    */   
/*    */   public int get(SkillCaster caster) {
/* 66 */     return get((PlaceholderMeta)new GenericPlaceholderMeta(caster, caster.getEntity()));
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     return this.strValue;
/*    */   }
/*    */   
/*    */   public static io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt of(String string) {
/*    */     try {
/* 76 */       if (RandomInt.matches(string)) {
/* 77 */         return (io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt)new RandomInt(string);
/*    */       }
/* 79 */       if (string.matches("-?\\d+")) {
/* 80 */         return (io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt)new StaticInt(string);
/*    */       }
/* 82 */       if (!MythicMobs.p()) {
/* 83 */         MythicLogger.error("Failed to parse Placeholder '" + string + "': Math and variables in numeric values require premium! Consider getting MythicMobs Premium at www.mythicmobs.net.");
/* 84 */         return (io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt)new StaticInt("1");
/*    */       } 
/* 86 */       return new io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt(string);
/* 87 */     } catch (Exception exception) {
/*    */       
/* 89 */       MythicLogger.error("Failed to parse Placeholder '" + string + "': invalid syntax.");
/* 90 */       return (io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt)new StaticInt("1");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\parsers\PlaceholderInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */