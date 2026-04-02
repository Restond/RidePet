/*    */ package lumine.xikage.mythicmobs.spawning.random;
/*    */ 
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.utils.tasks.Task;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.spawning.random.RandomSpawnPoint;
/*    */ 
/*    */ 
/*    */ public abstract class RandomSpawnGenerator
/*    */   implements Runnable
/*    */ {
/* 12 */   private Task task = Schedulers.async().runRepeating(this, 0L, 20L);
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 17 */     generateSpawnPoints();
/*    */   }
/*    */   
/*    */   public void stop() {
/* 21 */     this.task.terminate();
/*    */   }
/*    */   
/*    */   public abstract void generateSpawnPoints();
/*    */   
/*    */   public abstract RandomSpawnPoint findPointNearPlayer(AbstractPlayer paramAbstractPlayer, boolean paramBoolean);
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\spawning\random\RandomSpawnGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */