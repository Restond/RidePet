/*     */ package lumine.xikage.mythicmobs.clock;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimingsHandler
/*     */ {
/*     */   private boolean enabled = false;
/*     */   private long startTime;
/*     */   private long total;
/*     */   private long lastMark;
/*     */   private long scannerTotal;
/*     */   private long scannerLastMark;
/*     */   private long skillsTotal;
/*     */   private long skillsLastMark;
/*     */   private long spawnersTotal;
/*     */   private long spawnersLastMark;
/*     */   private long randomSpawnersTotal;
/*     */   private long randomSpawnersLastMark;
/*     */   private long randomGeneratorsTotal;
/*     */   private long randomGeneratorsLastMark;
/*     */   private long threattablesTotal;
/*     */   private long threattablesLastMark;
/*  30 */   private HashMap<String, Long> skillMarks = new HashMap<>();
/*  31 */   private HashMap<String, Long> skillTimings = new HashMap<>();
/*     */   
/*  33 */   private HashMap<String, Long> spawnerMarks = new HashMap<>();
/*  34 */   private HashMap<String, Long> spawnerTimings = new HashMap<>();
/*     */   
/*     */   public void enable() {
/*  37 */     this.startTime = System.nanoTime();
/*  38 */     this.enabled = true;
/*     */   }
/*     */   public void disable() {
/*  41 */     this.enabled = false;
/*     */   }
/*     */   public void reset() {
/*  44 */     this.startTime = System.nanoTime();
/*  45 */     this.total = 0L;
/*  46 */     this.lastMark = 0L;
/*  47 */     this.scannerTotal = 0L;
/*  48 */     this.scannerLastMark = 0L;
/*     */     
/*  50 */     this.skillsTotal = 0L;
/*  51 */     this.skillsLastMark = 0L;
/*     */     
/*  53 */     this.spawnersTotal = 0L;
/*  54 */     this.spawnersLastMark = 0L;
/*     */     
/*  56 */     this.randomSpawnersTotal = 0L;
/*  57 */     this.randomSpawnersLastMark = 0L;
/*     */     
/*  59 */     this.randomGeneratorsTotal = 0L;
/*  60 */     this.randomGeneratorsLastMark = 0L;
/*     */     
/*  62 */     this.threattablesTotal = 0L;
/*  63 */     this.threattablesLastMark = 0L;
/*     */     
/*  65 */     this.spawnerMarks.clear();
/*  66 */     this.spawnerTimings.clear();
/*     */     
/*  68 */     this.skillMarks.clear();
/*  69 */     this.skillTimings.clear();
/*     */   }
/*     */   public boolean isEnabled() {
/*  72 */     return this.enabled;
/*     */   }
/*     */   public void markTotalNew() {
/*  75 */     if (!this.enabled)
/*  76 */       return;  this.lastMark = System.nanoTime();
/*     */   }
/*     */   public void markTotalComplete() {
/*  79 */     if (!this.enabled)
/*  80 */       return;  if (this.lastMark == 0L)
/*     */       return; 
/*  82 */     this.total += System.nanoTime() - this.lastMark;
/*     */   }
/*     */   public long getStartTime() {
/*  85 */     return this.startTime;
/*     */   }
/*     */   public long getRunTime() {
/*  88 */     return System.nanoTime() - this.startTime;
/*     */   }
/*     */   public double getRunTimeMillis() {
/*  91 */     return (Math.round((float)((System.nanoTime() - this.startTime) / 10000L)) / 100);
/*     */   }
/*     */   public long getTimeTotal() {
/*  94 */     return this.total;
/*     */   }
/*     */   public void markScannerNew() {
/*  97 */     if (!this.enabled)
/*  98 */       return;  this.scannerLastMark = System.nanoTime();
/*     */   }
/*     */   public void markScannerComplete() {
/* 101 */     if (!this.enabled)
/* 102 */       return;  if (this.scannerLastMark == 0L)
/*     */       return; 
/* 104 */     this.scannerTotal += System.nanoTime() - this.scannerLastMark;
/*     */   }
/*     */   public long getTimeScanner() {
/* 107 */     return this.scannerTotal;
/*     */   }
/*     */   public void markSkillsNew() {
/* 110 */     if (!this.enabled)
/* 111 */       return;  this.skillsLastMark = System.nanoTime();
/*     */   }
/*     */   public void markSkillsComplete() {
/* 114 */     if (!this.enabled)
/* 115 */       return;  if (this.skillsLastMark == 0L)
/*     */       return; 
/* 117 */     this.skillsTotal += System.nanoTime() - this.skillsLastMark;
/*     */   }
/*     */   public long getTimeSkills() {
/* 120 */     return this.skillsTotal;
/*     */   }
/*     */   public void markRandomSpawnersNew() {
/* 123 */     if (!this.enabled)
/* 124 */       return;  this.randomSpawnersLastMark = System.nanoTime();
/*     */   }
/*     */   public void markRandomSpawnersComplete() {
/* 127 */     if (!this.enabled)
/* 128 */       return;  if (this.randomSpawnersLastMark == 0L)
/*     */       return; 
/* 130 */     this.randomSpawnersTotal += System.nanoTime() - this.randomSpawnersLastMark;
/*     */   }
/*     */   public long getTimeRandomSpawners() {
/* 133 */     return this.randomSpawnersTotal;
/*     */   }
/*     */   public void markRandomGeneratorsNew() {
/* 136 */     if (!this.enabled)
/* 137 */       return;  this.randomGeneratorsLastMark = System.nanoTime();
/*     */   }
/*     */   public void markRandomGeneratorsComplete() {
/* 140 */     if (!this.enabled)
/* 141 */       return;  if (this.randomGeneratorsLastMark == 0L)
/*     */       return; 
/* 143 */     this.randomGeneratorsTotal += System.nanoTime() - this.randomGeneratorsLastMark;
/*     */   }
/*     */   public long getTimeRandomGenerators() {
/* 146 */     return this.randomGeneratorsTotal;
/*     */   }
/*     */   public void markSpawnersNew() {
/* 149 */     if (!this.enabled)
/* 150 */       return;  this.spawnersLastMark = System.nanoTime();
/*     */   }
/*     */   public void markSpawnersComplete() {
/* 153 */     if (!this.enabled)
/* 154 */       return;  if (this.spawnersLastMark == 0L)
/*     */       return; 
/* 156 */     this.spawnersTotal += System.nanoTime() - this.spawnersLastMark;
/*     */   }
/*     */   public long getTimeSpawners() {
/* 159 */     return this.spawnersTotal;
/*     */   }
/*     */   public void markThreatTablesNew() {
/* 162 */     if (!this.enabled)
/* 163 */       return;  this.threattablesLastMark = System.nanoTime();
/*     */   }
/*     */   public void markThreatTablesComplete() {
/* 166 */     if (!this.enabled)
/* 167 */       return;  if (this.threattablesLastMark == 0L)
/*     */       return; 
/* 169 */     this.threattablesTotal += System.nanoTime() - this.threattablesLastMark;
/*     */   }
/*     */   public long getTimeThreatTables() {
/* 172 */     return this.threattablesTotal;
/*     */   }
/*     */   
/*     */   public void markSpawnerNew(String s) {
/* 176 */     if (!this.enabled)
/* 177 */       return;  this.spawnerMarks.put(s, Long.valueOf(System.nanoTime()));
/*     */   }
/*     */   public void markSpawnerComplete(String s) {
/* 180 */     if (!this.enabled)
/*     */       return; 
/* 182 */     long l = 0L;
/*     */     
/* 184 */     if (this.spawnerMarks.containsKey(s)) {
/* 185 */       l = ((Long)this.spawnerMarks.get(s)).longValue();
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */     
/* 190 */     if (this.spawnerTimings.containsKey(s)) {
/* 191 */       long ct = ((Long)this.spawnerTimings.get(s)).longValue();
/* 192 */       this.spawnerTimings.put(s, Long.valueOf(ct + System.nanoTime() - l));
/*     */     } else {
/* 194 */       this.spawnerTimings.put(s, Long.valueOf(System.nanoTime() - l));
/*     */     } 
/*     */   }
/*     */   public HashMap<String, Long> getAllSpawnerTimes() {
/* 198 */     return this.spawnerTimings;
/*     */   }
/*     */   
/*     */   public void markSkillNew(String s) {
/* 202 */     if (!this.enabled)
/* 203 */       return;  this.skillMarks.put(s, Long.valueOf(System.nanoTime()));
/*     */   }
/*     */   public void markSkillComplete(String s) {
/* 206 */     if (!this.enabled)
/*     */       return; 
/* 208 */     long l = 0L;
/*     */     
/* 210 */     if (this.skillMarks.containsKey(s)) {
/* 211 */       l = ((Long)this.skillMarks.get(s)).longValue();
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */     
/* 216 */     if (this.skillTimings.containsKey(s)) {
/* 217 */       long ct = ((Long)this.skillTimings.get(s)).longValue();
/* 218 */       this.skillTimings.put(s, Long.valueOf(ct + System.nanoTime() - l));
/*     */     } else {
/* 220 */       this.skillTimings.put(s, Long.valueOf(System.nanoTime() - l));
/*     */     } 
/*     */   }
/*     */   public HashMap<String, Long> getAllSkillTimes() {
/* 224 */     return this.skillTimings;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\clock\TimingsHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */