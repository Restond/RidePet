/*    */ package lumine.xikage.mythicmobs.skills.variables.types;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ 
/*    */ public class StringVariable extends Variable {
/*    */   private String value;
/*    */   
/*  9 */   public void setValue(String value) { this.value = value; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.skills.variables.types.StringVariable)) return false;  io.lumine.xikage.mythicmobs.skills.variables.types.StringVariable other = (io.lumine.xikage.mythicmobs.skills.variables.types.StringVariable)o; if (!other.canEqual(this)) return false;  Object this$value = getValue(), other$value = other.getValue(); return !((this$value == null) ? (other$value != null) : !this$value.equals(other$value)); } protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.skills.variables.types.StringVariable; } public int hashCode() { int PRIME = 59; result = 1; Object $value = getValue(); return result * 59 + (($value == null) ? 43 : $value.hashCode()); }
/*    */   
/*    */   public String getValue() {
/* 12 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StringVariable(String value) {
/* 21 */     Preconditions.checkNotNull(value);
/* 22 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StringVariable(String value, long expirationTime) {
/* 32 */     super(expirationTime);
/* 33 */     Preconditions.checkNotNull(value);
/* 34 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 39 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     return this.value;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\types\StringVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */