/*    */ package lumine.xikage.mythicmobs.mobs.ai;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*    */ 
/*    */ public abstract class WrappedPathfinder {
/*    */   protected AbstractEntity entity;
/*    */   
/*    */   protected static MythicMobs getPlugin() {
/* 12 */     return MythicMobs.inst();
/*    */   }
/*    */   
/*    */   protected static VolatileAIHandler ai() {
/* 16 */     return MythicMobs.inst().getVolatileCodeHandler().getAIHandler();
/*    */   }
/*    */   public AbstractEntity getEntity() {
/* 19 */     return this.entity;
/* 20 */   } private int index = -1; public int getIndex() { return this.index; }
/* 21 */    protected String dataVar1 = null;
/* 22 */   protected String dataVar2 = null;
/*    */   
/*    */   public WrappedPathfinder(AbstractEntity entity, String line, MythicLineConfig mlc) {
/* 25 */     this.entity = entity;
/*    */ 
/*    */     
/* 28 */     String[] spLine = line.split(" ");
/*    */     
/* 30 */     if (spLine[0].matches("[0-9]*")) {
/*    */       
/* 32 */       this.index = Integer.parseInt(spLine[0]);
/* 33 */       String goal = spLine[1];
/*    */       
/* 35 */       if (spLine.length > 2) {
/* 36 */         this.dataVar1 = spLine[2];
/*    */       } else {
/* 38 */         this.dataVar1 = null;
/*    */       } 
/* 40 */       if (spLine.length > 3) {
/* 41 */         this.dataVar2 = spLine[3];
/*    */       } else {
/* 43 */         this.dataVar2 = null;
/*    */       } 
/*    */     } else {
/* 46 */       this.index = -1;
/* 47 */       String goal = spLine[0];
/*    */       
/* 49 */       if (spLine.length > 1) {
/* 50 */         this.dataVar1 = spLine[1];
/*    */       } else {
/* 52 */         this.dataVar1 = null;
/*    */       } 
/* 54 */       if (spLine.length > 2) {
/* 55 */         this.dataVar2 = spLine[2];
/*    */       } else {
/* 57 */         this.dataVar2 = null;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\ai\WrappedPathfinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */