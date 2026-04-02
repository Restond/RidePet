/*    */ package lumine.xikage.mythicmobs.skills.placeholders.types;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.Placeholder;
/*    */ 
/*    */ public class StringPlaceholder
/*    */   implements Placeholder {
/*    */   private String string;
/*    */   
/*    */   public StringPlaceholder(String string) {
/* 10 */     this.string = string;
/*    */   }
/*    */   
/*    */   public String get() {
/* 14 */     return this.string;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\types\StringPlaceholder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */