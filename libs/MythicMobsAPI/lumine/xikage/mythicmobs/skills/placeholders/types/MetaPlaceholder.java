/*    */ package lumine.xikage.mythicmobs.skills.placeholders.types;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.Placeholder;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MetaPlaceholder
/*    */   implements Placeholder, BiFunction<PlaceholderMeta, String, String>
/*    */ {
/*    */   private final BiFunction<PlaceholderMeta, String, String> transformer;
/*    */   
/*    */   public MetaPlaceholder(BiFunction<PlaceholderMeta, String, String> transformer) {
/* 15 */     this.transformer = transformer;
/*    */   }
/*    */ 
/*    */   
/*    */   public String apply(PlaceholderMeta target, String conditional) {
/* 20 */     return this.transformer.apply(target, conditional);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\types\MetaPlaceholder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */