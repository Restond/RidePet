/*    */ package lumine.xikage.mythicmobs.skills.variables.types;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ 
/*    */ public class IntegerVariable extends Variable {
/*    */   private int value;
/*    */   
/*  9 */   public void setValue(int value) { this.value = value; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.skills.variables.types.IntegerVariable)) return false;  io.lumine.xikage.mythicmobs.skills.variables.types.IntegerVariable other = (io.lumine.xikage.mythicmobs.skills.variables.types.IntegerVariable)o; return !other.canEqual(this) ? false : (!(getValue() != other.getValue())); } protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.skills.variables.types.IntegerVariable; } public int hashCode() { int PRIME = 59; result = 1; return result * 59 + getValue(); }
/*    */   
/*    */   public int getValue() {
/* 12 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IntegerVariable(int value) {
/* 21 */     Preconditions.checkNotNull(Integer.valueOf(value));
/* 22 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IntegerVariable(int value, long expirationTime) {
/* 32 */     super(expirationTime);
/* 33 */     Preconditions.checkNotNull(Integer.valueOf(value));
/* 34 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 39 */     return Integer.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     return "" + this.value + "";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\types\IntegerVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */