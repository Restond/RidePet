/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.OpenTerrainGeneratorSupport;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OTGBiomeCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition
/*    */ {
/* 41 */   private Set<String> biomes = new HashSet<>();
/*    */   private boolean strict = false;
/*    */   
/*    */   public OTGBiomeCondition(String line, MythicLineConfig mlc) {
/* 45 */     super(line);
/*    */     
/* 47 */     String b = mlc.getString(new String[] { "biome", "b" }, "PLAINS", new String[] { this.conditionVar }).toUpperCase();
/* 48 */     this.strict = mlc.getBoolean(new String[] { "strict", "s" }, false);
/*    */     
/* 50 */     for (String s : b.split(",")) {
/* 51 */       this.biomes.add(s);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation location) {
/* 57 */     if (!MythicMobs.inst().getCompatibility().getTerrainControl().isPresent()) return false; 
/* 58 */     String biome = OpenTerrainGeneratorSupport.this.getBiome(location).toUpperCase();
/* 59 */     if (this.strict) {
/* 60 */       return this.biomes.contains(location.getBiome().toString());
/*    */     }
/* 62 */     for (String b : this.biomes) {
/* 63 */       if (biome.contains(b)) {
/* 64 */         return true;
/*    */       }
/*    */     } 
/* 67 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\OpenTerrainGeneratorSupport$OTGBiomeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */