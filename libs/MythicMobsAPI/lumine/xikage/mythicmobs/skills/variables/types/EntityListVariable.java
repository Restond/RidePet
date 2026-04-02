/*    */ package lumine.xikage.mythicmobs.skills.variables.types;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class EntityListVariable extends Variable {
/*    */   private final UUID value;
/*    */   
/* 10 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.skills.variables.types.EntityListVariable)) return false;  io.lumine.xikage.mythicmobs.skills.variables.types.EntityListVariable other = (io.lumine.xikage.mythicmobs.skills.variables.types.EntityListVariable)o; if (!other.canEqual(this)) return false;  Object this$value = getValue(), other$value = other.getValue(); return !((this$value == null) ? (other$value != null) : !this$value.equals(other$value)); } protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.skills.variables.types.EntityListVariable; } public int hashCode() { int PRIME = 59; result = 1; Object $value = getValue(); return result * 59 + (($value == null) ? 43 : $value.hashCode()); }
/*    */   
/*    */   public UUID getValue() {
/* 13 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityListVariable(UUID value) {
/* 22 */     Preconditions.checkNotNull(value);
/* 23 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 28 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     return "VAR_EntityList(" + this.value + ")";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\types\EntityListVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */