/*    */ package lumine.xikage.mythicmobs.adapters;
/*    */ 
/*    */ public class AbstractBiome
/*    */ {
/*    */   private String biome;
/*    */   
/*    */   public AbstractBiome(String biome) {
/*  8 */     this.biome = biome.toUpperCase();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 13 */     return this.biome;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 18 */     if (o instanceof io.lumine.xikage.mythicmobs.adapters.AbstractBiome)
/* 19 */       return o.toString().equals(this.biome); 
/* 20 */     if (o instanceof String) {
/* 21 */       return this.biome.equals(((String)o).toUpperCase());
/*    */     }
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 28 */     return this.biome.hashCode();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\AbstractBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */