/*    */ package lumine.xikage.mythicmobs.skills.placeholders.parsers;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat;
/*    */ 
/*    */ public class StaticFloat extends PlaceholderFloat {
/*    */   private float value;
/*    */   
/*    */   public StaticFloat(String string) {
/* 12 */     super(string);
/* 13 */     this.value = Float.valueOf(string).floatValue();
/*    */   }
/*    */   
/*    */   public StaticFloat(float amount) {
/* 17 */     super(String.valueOf(amount));
/* 18 */     this.value = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public float get() {
/* 23 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public float get(PlaceholderMeta meta) {
/* 28 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public float get(AbstractEntity entity) {
/* 33 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public float get(PlaceholderMeta meta, AbstractEntity entity) {
/* 38 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public float get(SkillCaster caster) {
/* 43 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     return String.valueOf(this.value);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\parsers\StaticFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */