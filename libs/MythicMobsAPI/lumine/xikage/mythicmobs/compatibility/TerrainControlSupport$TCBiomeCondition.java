/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.TerrainControlSupport;
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
/*    */ public class TCBiomeCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition
/*    */ {
/* 40 */   private Set<String> biomes = new HashSet<>();
/*    */   private boolean strict = false;
/*    */   
/*    */   public TCBiomeCondition(String line, MythicLineConfig mlc) {
/* 44 */     super(line);
/*    */     
/* 46 */     String b = mlc.getString(new String[] { "biome", "b" }, "PLAINS", new String[] { this.conditionVar }).toUpperCase();
/* 47 */     this.strict = mlc.getBoolean(new String[] { "strict", "s" }, false);
/*    */     
/* 49 */     for (String s : b.split(",")) {
/* 50 */       this.biomes.add(s);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation location) {
/* 56 */     if (!MythicMobs.inst().getCompatibility().getTerrainControl().isPresent()) return false; 
/* 57 */     String biome = TerrainControlSupport.this.getBiome(location).toUpperCase();
/* 58 */     if (this.strict) {
/* 59 */       return this.biomes.contains(location.getBiome().toString());
/*    */     }
/* 61 */     for (String b : this.biomes) {
/* 62 */       if (biome.contains(b)) {
/* 63 */         return true;
/*    */       }
/*    */     } 
/* 66 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\TerrainControlSupport$TCBiomeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */