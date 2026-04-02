/*    */ package lumine.xikage.mythicmobs.clock;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ 
/*    */ public class MythicMobsAsyncClock
/*    */   implements Runnable
/*    */ {
/* 10 */   int st = 0, ticker = 0, spawns = 0, cleanup = 0, scan = 0;
/*    */ 
/*    */   
/*    */   public void run() {
/* 14 */     MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "Async clock fired", new Object[0]);
/*    */     
/* 16 */     this.st++;
/* 17 */     this.ticker += ConfigManager.ClockInterval;
/*    */     
/* 19 */     if (ConfigManager.EnableTimerSkills == true)
/*    */     {
/* 21 */       MythicMobs.inst().getSkillManager().runTimerSkills(this.st);
/*    */     }
/*    */ 
/*    */     
/* 25 */     if (this.ticker > 20) {
/* 26 */       this.spawns++;
/* 27 */       this.cleanup++;
/* 28 */       this.scan++;
/*    */       
/* 30 */       if (this.cleanup >= ConfigManager.ClnrInterval) {
/* 31 */         this.cleanup = 0;
/*    */       }
/* 33 */       if (this.scan >= ConfigManager.ScanInterval) {
/* 34 */         MythicMobs.inst().getMobManager().cleanActiveMobs();
/* 35 */         this.scan = 0;
/*    */       } 
/* 37 */       this.ticker = 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\clock\MythicMobsAsyncClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */