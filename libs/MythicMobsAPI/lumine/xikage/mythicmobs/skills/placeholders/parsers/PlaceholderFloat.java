/*    */ package lumine.xikage.mythicmobs.skills.placeholders.parsers;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.PlaceholderAPISupport;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.GenericPlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderParser;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.RandomFloat;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.StaticFloat;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RandomFloat;
/*    */ import net.objecthunter.exp4j.ExpressionBuilder;
/*    */ 
/*    */ public class PlaceholderFloat
/*    */   extends PlaceholderParser {
/*    */   public PlaceholderFloat(String string) {
/* 19 */     super(string);
/*    */     
/* 21 */     if (string.startsWith("=")) {
/* 22 */       this.doMath = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public float get() {
/* 27 */     String string = this.strValue;
/* 28 */     if (this.papi) {
/* 29 */       string = ((PlaceholderAPISupport)MythicMobs.inst().getCompatibility().getPlaceholderAPI().get()).parse(string);
/*    */     }
/* 31 */     if (this.doMath) {
/*    */       try {
/* 33 */         return (float)(new ExpressionBuilder(string)).build().evaluate();
/* 34 */       } catch (Exception ex) {
/* 35 */         MythicLogger.error("Invalid math operation on PlaceholderString: " + this.strValue);
/* 36 */         ex.printStackTrace();
/*    */       } 
/*    */     }
/* 39 */     return Float.valueOf(string).floatValue();
/*    */   }
/*    */   
/*    */   public float get(PlaceholderMeta meta) {
/* 43 */     return get(meta, (meta == null) ? null : meta.getTrigger());
/*    */   }
/*    */   
/*    */   public float get(AbstractEntity entity) {
/* 47 */     return get(null, entity);
/*    */   }
/*    */   
/*    */   public float get(PlaceholderMeta meta, AbstractEntity entity) {
/* 51 */     String string = parse(meta, entity);
/*    */     
/* 53 */     if (this.papi) {
/* 54 */       if (entity != null && entity.isPlayer()) {
/* 55 */         string = ((PlaceholderAPISupport)MythicMobs.inst().getCompatibility().getPlaceholderAPI().get()).parse(string, entity.asPlayer());
/*    */       } else {
/* 57 */         string = ((PlaceholderAPISupport)MythicMobs.inst().getCompatibility().getPlaceholderAPI().get()).parse(string);
/*    */       } 
/*    */     }
/*    */     
/* 61 */     if (this.doMath) {
/*    */       try {
/* 63 */         return (float)(new ExpressionBuilder(string)).build().evaluate();
/* 64 */       } catch (Exception ex) {
/* 65 */         MythicLogger.error("Invalid math operation on PlaceholderString: " + this.strValue);
/* 66 */         ex.printStackTrace();
/*    */       } 
/*    */     }
/*    */     
/* 70 */     return Float.valueOf(string).floatValue();
/*    */   }
/*    */   
/*    */   public float get(SkillCaster caster) {
/* 74 */     return get((PlaceholderMeta)new GenericPlaceholderMeta(caster, caster.getEntity()));
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 79 */     return this.strValue;
/*    */   }
/*    */   
/*    */   public static io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat of(String string) {
/*    */     try {
/* 84 */       if (RandomFloat.matches(string)) {
/* 85 */         return (io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat)new RandomFloat(string);
/*    */       }
/* 87 */       if (string.matches("-?\\d+\\.?\\d*")) {
/* 88 */         return (io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat)new StaticFloat(string);
/*    */       }
/* 90 */       if (!MythicMobs.p()) {
/* 91 */         MythicLogger.error("Failed to parse Placeholder '" + string + "': Math and variables in numeric values require premium! Consider getting MythicMobs Premium at www.mythicmobs.net.");
/* 92 */         return (io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat)new StaticFloat("1");
/*    */       } 
/* 94 */       return new io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat(string);
/* 95 */     } catch (Exception exception) {
/*    */       
/* 97 */       MythicLogger.error("Failed to parse Placeholder '" + string + "': invalid syntax.");
/* 98 */       return (io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat)new StaticFloat("1");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\parsers\PlaceholderFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */