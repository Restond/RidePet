/*    */ package saukiya.sxattribute.data.attribute;
/*    */ 
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.TreeMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttributeMap
/*    */ {
/* 13 */   private Map<Integer, SubAttribute> map = new TreeMap<>();
/*    */   
/*    */   public SubAttribute get(int i) {
/* 16 */     return this.map.get(Integer.valueOf(i));
/*    */   }
/*    */   
/*    */   public void remove(int i) {
/* 20 */     this.map.remove(Integer.valueOf(i));
/*    */   }
/*    */   
/*    */   public int size() {
/* 24 */     return this.map.size();
/*    */   }
/*    */   
/*    */   public Collection<SubAttribute> values() {
/* 28 */     return this.map.values();
/*    */   }
/*    */   
/*    */   boolean containsKey(Integer i) {
/* 32 */     return this.map.containsKey(i);
/*    */   }
/*    */   
/*    */   public SubAttribute put(Integer i, SubAttribute subAttribute) {
/* 36 */     return this.map.put(i, subAttribute);
/*    */   }
/*    */   
/*    */   public Set<Map.Entry<Integer, SubAttribute>> entrySet() {
/* 40 */     return this.map.entrySet();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\AttributeMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */