/*    */ package lumine.xikage.mythicmobs.skills.placeholders;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.types.EntityPlaceholder;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.types.MetaPlaceholder;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ 
/*    */ public interface Placeholder
/*    */ {
/*    */   static MetaPlaceholder meta(BiFunction<PlaceholderMeta, String, String> transformer) {
/* 13 */     return new MetaPlaceholder(transformer);
/*    */   }
/*    */   
/*    */   static EntityPlaceholder entity(BiFunction<AbstractEntity, String, String> transformer) {
/* 17 */     return new EntityPlaceholder(transformer);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\Placeholder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */