/*    */ package lumine.xikage.mythicmobs.skills.placeholders.parsers;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*    */ 
/*    */ public class StaticDouble extends PlaceholderDouble {
/*    */   private double value;
/*    */   
/*    */   public StaticDouble(String string) {
/* 12 */     super(string);
/* 13 */     this.value = Double.valueOf(string).doubleValue();
/*    */   }
/*    */   
/*    */   public StaticDouble(double amount) {
/* 17 */     super(String.valueOf(amount));
/* 18 */     this.value = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public double get() {
/* 23 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public double get(PlaceholderMeta meta) {
/* 28 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public double get(AbstractEntity entity) {
/* 33 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public double get(PlaceholderMeta meta, AbstractEntity entity) {
/* 38 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public double get(SkillCaster caster) {
/* 43 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     return String.valueOf(this.value);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\parsers\StaticDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */