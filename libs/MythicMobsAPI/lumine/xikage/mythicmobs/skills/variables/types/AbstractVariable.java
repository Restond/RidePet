/*    */ package lumine.xikage.mythicmobs.skills.variables.types;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ 
/*    */ public class AbstractVariable extends Variable {
/*    */   private final Object value;
/*    */   
/*  8 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.skills.variables.types.AbstractVariable)) return false;  io.lumine.xikage.mythicmobs.skills.variables.types.AbstractVariable other = (io.lumine.xikage.mythicmobs.skills.variables.types.AbstractVariable)o; if (!other.canEqual(this)) return false;  Object this$value = getValue(), other$value = other.getValue(); return !((this$value == null) ? (other$value != null) : !this$value.equals(other$value)); } protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.skills.variables.types.AbstractVariable; } public int hashCode() { int PRIME = 59; result = 1; Object $value = getValue(); return result * 59 + (($value == null) ? 43 : $value.hashCode()); }
/*    */   
/*    */   public Object getValue() {
/* 11 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractVariable(Object value) {
/* 20 */     Preconditions.checkNotNull(value);
/* 21 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 26 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldSave() {
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 36 */     return "VAR_Abstract(" + this.value.toString() + ")";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\types\AbstractVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */