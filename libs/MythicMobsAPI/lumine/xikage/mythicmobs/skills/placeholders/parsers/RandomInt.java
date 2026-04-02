/*    */ package lumine.xikage.mythicmobs.skills.placeholders.parsers;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*    */ 
/*    */ public class RandomInt
/*    */   extends PlaceholderInt {
/*    */   private io.lumine.xikage.mythicmobs.util.types.RandomInt value;
/*    */   
/*    */   public RandomInt(String string) {
/* 13 */     super(string);
/*    */     
/* 15 */     this.value = new io.lumine.xikage.mythicmobs.util.types.RandomInt(string);
/*    */   }
/*    */ 
/*    */   
/*    */   public int get() {
/* 20 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public int get(PlaceholderMeta meta) {
/* 25 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public int get(AbstractEntity entity) {
/* 30 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public int get(PlaceholderMeta meta, AbstractEntity entity) {
/* 35 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public int get(SkillCaster caster) {
/* 40 */     return this.value.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     return this.value.toString();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\parsers\RandomInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */