/*    */ package lumine.xikage.mythicmobs.skills.placeholders.types;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.Placeholder;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ 
/*    */ public class EntityPlaceholder
/*    */   implements Placeholder, BiFunction<AbstractEntity, String, String>
/*    */ {
/*    */   private final BiFunction<AbstractEntity, String, String> transformer;
/*    */   
/*    */   public EntityPlaceholder(BiFunction<AbstractEntity, String, String> transformer) {
/* 14 */     this.transformer = transformer;
/*    */   }
/*    */ 
/*    */   
/*    */   public String apply(AbstractEntity target, String conditional) {
/* 19 */     return this.transformer.apply(target, conditional);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\types\EntityPlaceholder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */