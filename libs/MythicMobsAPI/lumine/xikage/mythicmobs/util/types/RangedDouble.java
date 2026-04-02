/*     */ package lumine.xikage.mythicmobs.util.types;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RangedDouble
/*     */ {
/*     */   protected final Operation op;
/*     */   protected double min;
/*     */   protected double max;
/*     */   
/*     */   public RangedDouble(String value) {
/*  17 */     this(value, false);
/*     */   }
/*     */   
/*     */   public RangedDouble(String value, boolean squared) {
/*  21 */     if (value.contains("to")) {
/*  22 */       String[] split = value.split("to");
/*  23 */       this.min = Double.valueOf(split[0]).doubleValue();
/*  24 */       this.max = Double.valueOf(split[1]).doubleValue();
/*  25 */       this.op = Operation.RANGE;
/*  26 */     } else if (!value.startsWith("-") && value.contains("-")) {
/*  27 */       String[] split = value.split("-");
/*  28 */       this.min = Integer.valueOf(split[0]).intValue();
/*  29 */       this.max = Integer.valueOf(split[1]).intValue();
/*  30 */       this.op = Operation.RANGE;
/*  31 */     } else if (value.startsWith(">")) {
/*  32 */       String s = value.substring(1);
/*  33 */       this.min = Double.valueOf(s).doubleValue();
/*  34 */       this.max = Double.MAX_VALUE;
/*  35 */       this.op = Operation.GREATER_THAN;
/*  36 */     } else if (value.startsWith("<")) {
/*  37 */       String s = value.substring(1);
/*  38 */       this.min = Double.MIN_VALUE;
/*  39 */       this.max = Double.valueOf(s).doubleValue();
/*  40 */       this.op = Operation.LESS_THAN;
/*     */     } else {
/*  42 */       this.min = Double.valueOf(value).doubleValue();
/*  43 */       this.max = Double.valueOf(value).doubleValue();
/*  44 */       this.op = Operation.EQUALS;
/*     */     } 
/*     */     
/*  47 */     if (squared) {
/*  48 */       this.min *= this.min;
/*  49 */       this.max *= this.max;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Operation getOperation() {
/*  54 */     return this.op;
/*     */   }
/*     */   
/*     */   public double getMin() {
/*  58 */     return this.min;
/*     */   }
/*     */   
/*     */   public double getMax() {
/*  62 */     return this.max;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  66 */     if (o instanceof Integer || o instanceof Double || o instanceof Float) {
/*     */       double d;
/*  68 */       if (o instanceof Integer) {
/*  69 */         d = ((Integer)o).intValue() * 1.0D;
/*  70 */       } else if (o instanceof Float) {
/*  71 */         d = ((Float)o).floatValue() * 1.0D;
/*     */       } else {
/*  73 */         d = ((Double)o).doubleValue();
/*     */       } 
/*     */       
/*  76 */       switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$util$types$RangedDouble$Operation[this.op.ordinal()]) {
/*     */         case 1:
/*  78 */           return (d == this.min);
/*     */         case 2:
/*  80 */           return (d > this.min);
/*     */         case 3:
/*  82 */           return (d < this.max);
/*     */         case 4:
/*  84 */           return (d >= this.min && d <= this.max);
/*     */       } 
/*  86 */       return true;
/*     */     } 
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equalsSquared(Object o) {
/*  93 */     if (o instanceof Integer || o instanceof Double || o instanceof Float) {
/*     */       double d;
/*  95 */       if (o instanceof Integer) {
/*  96 */         d = ((Integer)o).intValue() * 1.0D;
/*  97 */       } else if (o instanceof Float) {
/*  98 */         d = ((Float)o).floatValue() * 1.0D;
/*     */       } else {
/* 100 */         d = ((Double)o).doubleValue();
/*     */       } 
/*     */       
/* 103 */       switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$util$types$RangedDouble$Operation[this.op.ordinal()]) {
/*     */         case 1:
/* 105 */           return (d == Math.pow(this.min, 2.0D));
/*     */         case 2:
/* 107 */           return (d > Math.pow(this.min, 2.0D));
/*     */         case 3:
/* 109 */           return (d < Math.pow(this.max, 2.0D));
/*     */         case 4:
/* 111 */           return (d >= Math.pow(this.min, 2.0D) && d <= Math.pow(this.max, 2.0D));
/*     */       } 
/* 113 */       return true;
/*     */     } 
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 121 */     return "RangedDouble{" + this.min + " to " + this.max + "}";
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmob\\util\types\RangedDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */