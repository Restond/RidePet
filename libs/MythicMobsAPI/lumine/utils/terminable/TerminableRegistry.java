/*    */ package lumine.utils.terminable;
/*    */ 
/*    */ import io.lumine.utils.terminable.SimpleTerminableRegistry;
/*    */ import io.lumine.utils.terminable.Terminable;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ public interface TerminableRegistry
/*    */   extends Consumer<Terminable>, Terminable
/*    */ {
/*    */   static io.lumine.utils.terminable.TerminableRegistry create() {
/* 11 */     return (io.lumine.utils.terminable.TerminableRegistry)new SimpleTerminableRegistry();
/*    */   }
/*    */   
/*    */   <T extends io.lumine.utils.terminable.CompositeTerminable> T bindTerminable(T paramT);
/*    */   
/*    */   void cleanup();
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\terminable\TerminableRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */