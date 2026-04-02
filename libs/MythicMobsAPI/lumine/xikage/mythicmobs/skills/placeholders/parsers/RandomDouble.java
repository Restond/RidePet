/*    */ package lumine.xikage.mythicmobs.skills.placeholders.parsers;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*    */ 
/*    */ public class RandomDouble extends PlaceholderDouble {
/*    */   private io.lumine.xikage.mythicmobs.util.types.RandomDouble value;
/*    */   
/*    */   public RandomDouble(String string) {
/* 12 */     super(string);
/* 13 */     this.value = new io.lumine.xikage.mythicmobs.util.types.RandomDouble(string);
/*    */   }
/*    */   
/*    */   public RandomDouble(io.lumine.xikage.mythicmobs.util.types.RandomDouble amount) {
/* 17 */     super(String.valueOf(amount.get()));
/* 18 */     this.value = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public double get() {
/* 23 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public double get(PlaceholderMeta meta) {
/* 28 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public double get(AbstractEntity entity) {
/* 33 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public double get(PlaceholderMeta meta, AbstractEntity entity) {
/* 38 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public double get(SkillCaster caster) {
/* 43 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     return this.value.toString();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\parsers\RandomDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */