/*    */ package lumine.xikage.mythicmobs.util.types;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RangedInt
/*    */ {
/*    */   protected final Operation op;
/*    */   protected int min;
/*    */   protected int max;
/*    */   
/*    */   public RangedInt(String value) {
/* 17 */     this(value, false);
/*    */   }
/*    */   
/*    */   public RangedInt(String value, boolean squared) {
/* 21 */     if (value.contains("to")) {
/* 22 */       String[] split = value.split("to");
/* 23 */       this.min = Integer.valueOf(split[0]).intValue();
/* 24 */       this.max = Integer.valueOf(split[1]).intValue();
/* 25 */       this.op = Operation.RANGE;
/* 26 */     } else if (!value.startsWith("-") && value.contains("-")) {
/* 27 */       String[] split = value.split("-");
/* 28 */       this.min = Integer.valueOf(split[0]).intValue();
/* 29 */       this.max = Integer.valueOf(split[1]).intValue();
/* 30 */       this.op = Operation.RANGE;
/* 31 */     } else if (value.startsWith(">")) {
/* 32 */       String s = value.substring(1);
/* 33 */       this.min = Integer.valueOf(s).intValue();
/* 34 */       this.max = Integer.MAX_VALUE;
/* 35 */       this.op = Operation.GREATER_THAN;
/* 36 */     } else if (value.startsWith("<")) {
/* 37 */       String s = value.substring(1);
/* 38 */       this.min = Integer.MIN_VALUE;
/* 39 */       this.max = Integer.valueOf(s).intValue();
/* 40 */       this.op = Operation.LESS_THAN;
/*    */     } else {
/* 42 */       this.min = Integer.valueOf(value).intValue();
/* 43 */       this.max = Integer.valueOf(value).intValue();
/* 44 */       this.op = Operation.EQUALS;
/*    */     } 
/*    */     
/* 47 */     if (squared) {
/* 48 */       this.min *= this.min;
/* 49 */       this.max *= this.max;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Operation getOperation() {
/* 54 */     return this.op;
/*    */   }
/*    */   
/*    */   public int getMin() {
/* 58 */     return this.min;
/*    */   }
/*    */   
/*    */   public int getMax() {
/* 62 */     return this.max;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 66 */     if (o instanceof Integer || o instanceof Double || o instanceof Float) {
/*    */       
/* 68 */       int d = ((Integer)o).intValue();
/*    */       
/* 70 */       switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$util$types$RangedInt$Operation[this.op.ordinal()]) {
/*    */         case 1:
/* 72 */           return (d == this.min);
/*    */         case 2:
/* 74 */           return (d > this.min);
/*    */         case 3:
/* 76 */           return (d < this.max);
/*    */         case 4:
/* 78 */           return (d >= this.min && d <= this.max);
/*    */       } 
/* 80 */       return true;
/*    */     } 
/* 82 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     return "RangedInt{" + this.min + " to " + this.max + "}";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\types\RangedInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */