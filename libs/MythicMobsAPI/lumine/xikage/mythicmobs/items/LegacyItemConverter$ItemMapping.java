/*    */ package lumine.xikage.mythicmobs.items;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.items.LegacyItemConverter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ItemMapping
/*    */ {
/*    */   private final String materialName;
/*    */   private final int legacyId;
/*    */   private final int legacyDataValue;
/*    */   private final String legacyName;
/*    */   
/*    */   public boolean equals(Object o) {
/* 16 */     if (o == this) return true;  if (!(o instanceof ItemMapping)) return false;  ItemMapping other = (ItemMapping)o; if (!other.canEqual(this)) return false;  Object this$materialName = getMaterialName(), other$materialName = other.getMaterialName(); if ((this$materialName == null) ? (other$materialName != null) : !this$materialName.equals(other$materialName)) return false;  if (getLegacyId() != other.getLegacyId()) return false;  if (getLegacyDataValue() != other.getLegacyDataValue()) return false;  Object this$legacyName = getLegacyName(), other$legacyName = other.getLegacyName(); return !((this$legacyName == null) ? (other$legacyName != null) : !this$legacyName.equals(other$legacyName)); } protected boolean canEqual(Object other) { return other instanceof ItemMapping; } public int hashCode() { int PRIME = 59; result = 1; Object $materialName = getMaterialName(); result = result * 59 + (($materialName == null) ? 43 : $materialName.hashCode()); result = result * 59 + getLegacyId(); result = result * 59 + getLegacyDataValue(); Object $legacyName = getLegacyName(); return result * 59 + (($legacyName == null) ? 43 : $legacyName.hashCode()); } public String toString() { return "LegacyItemConverter.ItemMapping(materialName=" + getMaterialName() + ", legacyId=" + getLegacyId() + ", legacyDataValue=" + getLegacyDataValue() + ", legacyName=" + getLegacyName() + ")"; }
/*    */ 
/*    */   
/* 19 */   public String getMaterialName() { return this.materialName; }
/* 20 */   public int getLegacyId() { return this.legacyId; }
/* 21 */   public int getLegacyDataValue() { return this.legacyDataValue; } public String getLegacyName() {
/* 22 */     return this.legacyName;
/*    */   }
/*    */   public ItemMapping(int legacyId, int legacyDataValue, String legacyType, String name) {
/* 25 */     this.materialName = name.toUpperCase();
/* 26 */     this.legacyName = legacyType.toUpperCase();
/* 27 */     this.legacyId = legacyId;
/* 28 */     this.legacyDataValue = legacyDataValue;
/*    */   }
/*    */   
/*    */   public ItemMapping(int legacyId, String name) {
/* 32 */     this(legacyId, 0, name, name);
/*    */   }
/*    */   
/*    */   public ItemMapping(int legacyId, String legacyType, String name) {
/* 36 */     this(legacyId, 0, legacyType, name);
/*    */   }
/*    */   
/*    */   public ItemMapping(int legacyId, int data, String name) {
/* 40 */     this(legacyId, data, name, name);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\items\LegacyItemConverter$ItemMapping.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */