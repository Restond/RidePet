/*    */ package lumine.xikage.mythicmobs.skills.placeholders.parsers;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*    */ 
/*    */ public class StaticInt
/*    */   extends PlaceholderInt
/*    */ {
/*    */   private int value;
/*    */   
/*    */   public StaticInt(String string) {
/* 14 */     super(string);
/*    */     
/* 16 */     this.value = Integer.valueOf(string).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public int get() {
/* 21 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public int get(PlaceholderMeta meta) {
/* 26 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public int get(AbstractEntity entity) {
/* 31 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public int get(PlaceholderMeta meta, AbstractEntity entity) {
/* 36 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public int get(SkillCaster caster) {
/* 41 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return String.valueOf(this.value);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\parsers\StaticInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */