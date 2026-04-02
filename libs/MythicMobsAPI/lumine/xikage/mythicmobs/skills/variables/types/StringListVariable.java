/*    */ package lumine.xikage.mythicmobs.skills.variables.types;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import io.lumine.xikage.mythicmobs.skills.variables.Variable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class StringListVariable extends Variable {
/*    */   private final List<String> values;
/*    */   
/* 10 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.skills.variables.types.StringListVariable)) return false;  io.lumine.xikage.mythicmobs.skills.variables.types.StringListVariable other = (io.lumine.xikage.mythicmobs.skills.variables.types.StringListVariable)o; if (!other.canEqual(this)) return false;  Object<String> this$values = (Object<String>)getValues(), other$values = (Object<String>)other.getValues(); return !((this$values == null) ? (other$values != null) : !this$values.equals(other$values)); } protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.skills.variables.types.StringListVariable; } public int hashCode() { int PRIME = 59; result = 1; Object<String> $values = (Object<String>)getValues(); return result * 59 + (($values == null) ? 43 : $values.hashCode()); }
/*    */   
/*    */   public List<String> getValues() {
/* 13 */     return this.values;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StringListVariable(List<String> value) {
/* 22 */     Preconditions.checkNotNull(value);
/* 23 */     this.values = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 28 */     return this.values;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     StringBuilder builder = new StringBuilder();
/*    */     
/* 35 */     for (int i = 0; i < this.values.size(); i++) {
/* 36 */       builder.append(this.values.get(i));
/*    */       
/* 38 */       if (i < this.values.size() - 1) {
/* 39 */         builder.append(",");
/*    */       }
/*    */     } 
/* 42 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\variables\types\StringListVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */