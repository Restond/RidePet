/*    */ package saukiya.sxattribute.data.condition;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConditionMap
/*    */ {
/* 14 */   private Map<Integer, SubCondition> map = new TreeMap<>();
/*    */   
/*    */   public SubCondition get(Integer i) {
/* 17 */     return this.map.get(i);
/*    */   }
/*    */   
/*    */   public int size() {
/* 21 */     return this.map.size();
/*    */   }
/*    */   
/*    */   public Collection<SubCondition> values() {
/* 25 */     return this.map.values();
/*    */   }
/*    */   
/*    */   boolean containsKey(Integer i) {
/* 29 */     return this.map.containsKey(i);
/*    */   }
/*    */   
/*    */   public SubCondition put(Integer i, SubCondition subCondition) {
/* 33 */     return this.map.put(i, subCondition);
/*    */   }
/*    */   
/*    */   public Set<Map.Entry<Integer, SubCondition>> entrySet() {
/* 37 */     return this.map.entrySet();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\ConditionMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */