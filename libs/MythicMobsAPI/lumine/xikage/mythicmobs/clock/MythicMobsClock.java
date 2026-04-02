/*    */ package lumine.xikage.mythicmobs.clock;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillSkill;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*    */ 
/*    */ public class MythicMobsClock
/*    */   implements Runnable {
/* 12 */   int clock = 0; int ticker = 0; int save = 0; int spawns = 0; int scanner = 0; int cleanup = 0; int entity = 0;
/* 13 */   long st = 0L;
/*    */ 
/*    */   
/*    */   public void run() {
/* 17 */     MythicLogger.debug(MythicLogger.DebugLevel.CLOCK, "Sync clock fired", new Object[0]);
/*    */     
/* 19 */     MythicMobs.inst().getTimingsHandler().markTotalNew();
/*    */     
/* 21 */     this.st++;
/* 22 */     this.ticker += ConfigManager.ClockInterval;
/*    */     
/* 24 */     SkillSkill.cooldownTimer = Long.valueOf(SkillSkill.cooldownTimer.longValue() + ConfigManager.ClockInterval);
/* 25 */     AbstractSkill.cooldownTimer += ConfigManager.ClockInterval;
/*    */     
/* 27 */     if (ConfigManager.EnableTimerSkills == true) {
/* 28 */       MythicMobs.inst().getTimingsHandler().markSkillsNew();
/* 29 */       MythicMobs.inst().getSkillManager().runLegacyTimerSkills(this.st);
/* 30 */       MythicMobs.inst().getTimingsHandler().markSkillsComplete();
/*    */     } 
/*    */     
/* 33 */     if (this.ticker > 20) {
/* 34 */       this.save++;
/* 35 */       this.spawns++;
/* 36 */       this.scanner++;
/* 37 */       this.cleanup++;
/*    */ 
/*    */       
/* 40 */       if (this.save >= ConfigManager.SaveInteval) {
/* 41 */         executeSave();
/* 42 */         this.save = 0;
/*    */       } 
/* 44 */       if (this.spawns >= ConfigManager.SpawningInterval && 
/* 45 */         !ConfigManager.debugSpawners) {
/* 46 */         MythicMobs.inst().getTimingsHandler().markRandomSpawnersNew();
/* 47 */         MythicMobs.inst().getRandomSpawningManager().spawnMobs();
/* 48 */         MythicMobs.inst().getTimingsHandler().markRandomSpawnersComplete();
/*    */         
/* 50 */         MythicMobs.inst().getTimingsHandler().markSpawnersNew();
/* 51 */         MythicMobs.inst().getSpawnerManager().tickSpawnerClocks();
/* 52 */         this.spawns = 0;
/* 53 */         MythicMobs.inst().getTimingsHandler().markSpawnersComplete();
/*    */       } 
/*    */       
/* 56 */       if (this.scanner >= ConfigManager.ScanInterval) {
/* 57 */         MythicMobs.inst().getTimingsHandler().markScannerNew();
/*    */         
/* 59 */         this.scanner = 0;
/* 60 */         MythicMobs.inst().getTimingsHandler().markScannerComplete();
/*    */       } 
/*    */       
/* 63 */       if (ConfigManager.EnableThreatTables == true) {
/* 64 */         MythicMobs.inst().getTimingsHandler().markThreatTablesNew();
/* 65 */         MythicMobs.inst().getMobManager().getMobsInCombat().forEach(am -> am.getThreatTable().tickThreat());
/* 66 */         MythicMobs.inst().getTimingsHandler().markThreatTablesComplete();
/*    */       } 
/*    */ 
/*    */       
/* 70 */       MythicMobs.inst().getEntityManager().refreshCaches();
/*    */ 
/*    */ 
/*    */       
/* 74 */       this.ticker = 0;
/*    */     } 
/*    */     
/* 77 */     MythicMobs.inst().getTimingsHandler().markTotalComplete();
/*    */   }
/*    */   
/*    */   private void executeSave() {
/* 81 */     MythicMobs.inst().getConfigManager().SaveAll();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\clock\MythicMobsClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */