/*    */ package lumine.xikage.mythicmobs.skills.variables.types;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ 
/*    */ public class FloatVariable extends Variable {
/*    */   private float value;
/*    */   
/*  9 */   public void setValue(float value) { this.value = value; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.skills.variables.types.FloatVariable)) return false;  io.lumine.xikage.mythicmobs.skills.variables.types.FloatVariable other = (io.lumine.xikage.mythicmobs.skills.variables.types.FloatVariable)o; return !other.canEqual(this) ? false : (!(Float.compare(getValue(), other.getValue()) != 0)); } protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.skills.variables.types.FloatVariable; } public int hashCode() { int PRIME = 59; result = 1; return result * 59 + Float.floatToIntBits(getValue()); }
/*    */   
/*    */   public float getValue() {
/* 12 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FloatVariable(float value) {
/* 21 */     Preconditions.checkNotNull(Float.valueOf(value));
/* 22 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FloatVariable(float value, long expirationTime) {
/* 32 */     super(expirationTime);
/* 33 */     Preconditions.checkNotNull(Float.valueOf(value));
/* 34 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 39 */     return Float.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     return "" + this.value + "";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\types\FloatVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */