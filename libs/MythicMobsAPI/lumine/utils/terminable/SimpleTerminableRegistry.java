/*    */ package lumine.utils.terminable;
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.common.collect.Lists;
/*    */ import io.lumine.utils.terminable.Terminable;
/*    */ import io.lumine.utils.terminable.TerminableRegistry;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ final class SimpleTerminableRegistry implements TerminableRegistry {
/* 12 */   private final List<Terminable> terminables = Collections.synchronizedList(new ArrayList<>());
/*    */   
/*    */   private boolean terminated = false;
/*    */   
/*    */   public void accept(Terminable terminable) {
/* 17 */     Preconditions.checkNotNull(terminable, "terminable");
/* 18 */     this.terminables.add(terminable);
/*    */   }
/*    */ 
/*    */   
/*    */   public <T extends io.lumine.utils.terminable.CompositeTerminable> T bindTerminable(T terminable) {
/* 23 */     Preconditions.checkNotNull(terminable, "terminable");
/* 24 */     terminable.bind((Consumer)this);
/* 25 */     return terminable;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean terminate() {
/* 30 */     Lists.reverse(this.terminables).forEach(terminable -> {
/*    */           try {
/*    */             terminable.terminate();
/* 33 */           } catch (Exception e) {
/*    */             e.printStackTrace();
/*    */           } 
/*    */         });
/* 37 */     this.terminables.clear();
/* 38 */     this.terminated = true;
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasTerminated() {
/* 44 */     return this.terminated;
/*    */   }
/*    */ 
/*    */   
/*    */   public void cleanup() {
/* 49 */     this.terminables.removeIf(Terminable::hasTerminated);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\terminable\SimpleTerminableRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */