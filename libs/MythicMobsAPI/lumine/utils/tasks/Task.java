/*    */ package lumine.utils.tasks;
/*    */ 
/*    */ import io.lumine.utils.annotation.NonnullByDefault;
/*    */ import io.lumine.utils.terminable.Terminable;
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
/*    */ @NonnullByDefault
/*    */ public interface Task
/*    */   extends Terminable
/*    */ {
/*    */   int getTimesRan();
/*    */   
/*    */   boolean stop();
/*    */   
/*    */   int getBukkitId();
/*    */   
/*    */   default boolean terminate() {
/* 38 */     stop();
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\tasks\Task.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */