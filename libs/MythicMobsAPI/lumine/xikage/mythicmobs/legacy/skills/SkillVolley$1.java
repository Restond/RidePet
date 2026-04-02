/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.Arrow;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class null
/*    */   implements Runnable
/*    */ {
/*    */   public void run() {
/* 56 */     for (Arrow a : arrowList) {
/* 57 */       a.remove();
/*    */     }
/* 59 */     arrowList.clear();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillVolley$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */