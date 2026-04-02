/*    */ package lumine.xikage.mythicmobs.mobs.ai;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ai.PathfinderAdapter;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*    */ 
/*    */ public abstract class Pathfinder implements PathfinderAdapter {
/*    */   protected ActiveMob activeMob;
/*    */   protected AbstractEntity entity;
/*    */   
/*    */   protected static MythicMobs getPlugin() {
/* 14 */     return MythicMobs.inst();
/*    */   }
/*    */   
/*    */   protected static VolatileAIHandler ai() {
/* 18 */     return MythicMobs.inst().getVolatileCodeHandler().getAIHandler();
/*    */   }
/*    */   
/* 21 */   public ActiveMob getActiveMob() { return this.activeMob; } public AbstractEntity getEntity() {
/* 22 */     return this.entity;
/* 23 */   } private int index = -1; public int getIndex() { return this.index; }
/* 24 */    protected String dataVar1 = null;
/* 25 */   protected String dataVar2 = null;
/*    */   
/* 27 */   protected GoalType goalType = GoalType.NONE; public GoalType getGoalType() { return this.goalType; } public void setGoalType(GoalType goalType) { this.goalType = goalType; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Pathfinder(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 37 */     this.entity = entity;
/* 38 */     this.activeMob = getPlugin().getMobManager().getMythicMobInstance(this.entity);
/*    */ 
/*    */     
/* 41 */     String[] spLine = line.split(" ");
/*    */     
/* 43 */     if (spLine[0].matches("[0-9]*")) {
/*    */       
/* 45 */       this.index = Integer.parseInt(spLine[0]);
/* 46 */       String goal = spLine[1];
/*    */       
/* 48 */       if (spLine.length > 2) {
/* 49 */         this.dataVar1 = spLine[2];
/*    */       } else {
/* 51 */         this.dataVar1 = null;
/*    */       } 
/* 53 */       if (spLine.length > 3) {
/* 54 */         this.dataVar2 = spLine[3];
/*    */       } else {
/* 56 */         this.dataVar2 = null;
/*    */       } 
/*    */     } else {
/* 59 */       this.index = -1;
/* 60 */       String goal = spLine[0];
/*    */       
/* 62 */       if (spLine.length > 1) {
/* 63 */         this.dataVar1 = spLine[1];
/*    */       } else {
/* 65 */         this.dataVar1 = null;
/*    */       } 
/* 67 */       if (spLine.length > 2) {
/* 68 */         this.dataVar2 = spLine[2];
/*    */       } else {
/* 70 */         this.dataVar2 = null;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public Pathfinder(AbstractEntity entity, int index, String line, MythicLineConfig mlc) {
/* 76 */     this.entity = entity;
/* 77 */     this.index = index;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 84 */     return true;
/*    */   }
/*    */   
/*    */   public abstract boolean shouldStart();
/*    */   
/*    */   public abstract void start();
/*    */   
/*    */   public abstract void tick();
/*    */   
/*    */   public abstract boolean shouldEnd();
/*    */   
/*    */   public abstract void end();
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\ai\Pathfinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */