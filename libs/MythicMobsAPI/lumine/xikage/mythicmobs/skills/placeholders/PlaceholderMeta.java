/*    */ package lumine.xikage.mythicmobs.skills.placeholders;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ 
/*    */ public interface PlaceholderMeta
/*    */ {
/*    */   default SkillCaster getCaster() {
/*  9 */     return null;
/*    */   }
/*    */   
/*    */   default AbstractEntity getTrigger() {
/* 13 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\placeholders\PlaceholderMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */