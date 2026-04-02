/*     */ package saukiya.sxattribute.data.attribute;
/*     */ 
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeManager;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.IntStream;
/*     */ 
/*     */ public class SXAttributeData
/*     */ {
/*     */   private boolean valid = false;
/*  16 */   private double value = 0.0D;
/*     */   public boolean isValid() {
/*  18 */     return this.valid;
/*     */   }
/*     */   public Map<Integer, SubAttribute> getAttributeMap() {
/*  21 */     return this.attributeMap;
/*  22 */   } private Map<Integer, SubAttribute> attributeMap = SXAttributeManager.cloneSXAttributeList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubAttribute getSubAttribute(String attributeName) {
/*  31 */     for (SubAttribute subAttribute : this.attributeMap.values()) {
/*  32 */       if (subAttribute.getName().equalsIgnoreCase(attributeName)) {
/*  33 */         return subAttribute;
/*     */       }
/*     */     } 
/*  36 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void valid() {
/*  43 */     this.valid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public github.saukiya.sxattribute.data.attribute.SXAttributeData add(github.saukiya.sxattribute.data.attribute.SXAttributeData attributeData) {
/*  54 */     if (attributeData != null && attributeData.isValid()) {
/*  55 */       valid();
/*  56 */       Iterator<SubAttribute> iterator = getAttributeMap().values().iterator();
/*  57 */       Iterator<SubAttribute> addIterator = attributeData.getAttributeMap().values().iterator();
/*  58 */       while (iterator.hasNext() && addIterator.hasNext()) {
/*  59 */         ((SubAttribute)iterator.next()).addAttribute(((SubAttribute)addIterator.next()).getAttributes());
/*     */       }
/*     */     } 
/*  62 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void correct() {
/*  69 */     getAttributeMap().values().forEach(SubAttribute::correct);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double calculationValue() {
/*  78 */     this.value = 0.0D;
/*  79 */     getAttributeMap().values().forEach(attribute -> this.value += attribute.getValue());
/*  80 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getValue() {
/*  89 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public github.saukiya.sxattribute.data.attribute.SXAttributeData loadFromString(String string) {
/*  99 */     return loadFromList(Arrays.asList(string.split("//")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public github.saukiya.sxattribute.data.attribute.SXAttributeData loadFromList(List<String> list) {
/* 109 */     list.forEach(attributeString -> getAttributeMap().values().forEach(()));
/* 110 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String saveToString() {
/* 119 */     List<String> list = saveToList();
/* 120 */     return IntStream.range(0, list.size()).<CharSequence>mapToObj(i -> (i == list.size() - 1) ? list.get(i) : ((String)list.get(i) + "//")).collect(Collectors.joining());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> saveToList() {
/* 129 */     return (List<String>)getAttributeMap().values().stream().map(SubAttribute::saveToString).filter(Objects::nonNull).collect(Collectors.toList());
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\SXAttributeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */