/*    */ package lumine.xikage.mythicmobs.skills.placeholders.parsers;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat;
/*    */ 
/*    */ public class RandomFloat extends PlaceholderFloat {
/*    */   private io.lumine.xikage.mythicmobs.util.types.RandomFloat value;
/*    */   
/*    */   public RandomFloat(String string) {
/* 12 */     super(string);
/* 13 */     this.value = new io.lumine.xikage.mythicmobs.util.types.RandomFloat(string);
/*    */   }
/*    */   
/*    */   public RandomFloat(io.lumine.xikage.mythicmobs.util.types.RandomFloat amount) {
/* 17 */     super(String.valueOf(amount.get()));
/* 18 */     this.value = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public float get() {
/* 23 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public float get(PlaceholderMeta meta) {
/* 28 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public float get(AbstractEntity entity) {
/* 33 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public float get(PlaceholderMeta meta, AbstractEntity entity) {
/* 38 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public float get(SkillCaster caster) {
/* 43 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     return this.value.toString();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\parsers\RandomFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */