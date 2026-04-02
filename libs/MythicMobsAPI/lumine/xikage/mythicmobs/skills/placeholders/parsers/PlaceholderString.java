/*    */ package lumine.xikage.mythicmobs.skills.placeholders.parsers;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.PlaceholderAPISupport;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.GenericPlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderParser;
/*    */ 
/*    */ 
/*    */ public class PlaceholderString
/*    */   extends PlaceholderParser
/*    */ {
/*    */   public PlaceholderString(String string) {
/* 16 */     super(string);
/*    */   }
/*    */   
/*    */   public String get() {
/* 20 */     String string = this.strValue;
/* 21 */     if (this.papi) {
/* 22 */       string = ((PlaceholderAPISupport)MythicMobs.inst().getCompatibility().getPlaceholderAPI().get()).parse(string);
/*    */     }
/* 24 */     return string;
/*    */   }
/*    */   
/*    */   public String get(PlaceholderMeta meta) {
/* 28 */     return get(meta, null);
/*    */   }
/*    */   
/*    */   public String get(AbstractEntity entity) {
/* 32 */     return get(null, entity);
/*    */   }
/*    */   
/*    */   public String get(PlaceholderMeta meta, AbstractEntity entity) {
/* 36 */     String string = parse(meta, entity);
/*    */     
/* 38 */     if (this.papi) {
/* 39 */       if (entity != null && entity.isPlayer()) {
/* 40 */         string = ((PlaceholderAPISupport)MythicMobs.inst().getCompatibility().getPlaceholderAPI().get()).parse(string, entity.asPlayer());
/*    */       } else {
/* 42 */         string = ((PlaceholderAPISupport)MythicMobs.inst().getCompatibility().getPlaceholderAPI().get()).parse(string);
/*    */       } 
/*    */     }
/*    */     
/* 46 */     return string;
/*    */   }
/*    */   
/*    */   public String get(SkillCaster caster) {
/* 50 */     return get((PlaceholderMeta)new GenericPlaceholderMeta(caster, caster.getEntity()));
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     return this.strValue;
/*    */   }
/*    */   
/*    */   public static io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString of(String string) {
/* 59 */     return new io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString(string);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\parsers\PlaceholderString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */