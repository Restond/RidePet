/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractBiome;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "biome", aliases = {}, description = "Tests if the target is within the given list of biomes")
/*    */ public class BiomeCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition
/*    */ {
/*    */   @MythicField(name = "biome", aliases = {"b"}, description = "A list of biomes to check")
/* 19 */   private Set<AbstractBiome> biome = new HashSet<>();
/*    */ 
/*    */   
/*    */   public BiomeCondition(String line, MythicLineConfig mlc) {
/* 23 */     super(line);
/*    */     
/* 25 */     String b = mlc.getString(new String[] { "biome", "b" }, "PLAINS", new String[] { this.conditionVar });
/*    */     
/* 27 */     for (String s : b.split(",")) {
/* 28 */       this.biome.add(new AbstractBiome(s));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 34 */     return this.biome.contains(l.getBiome());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\BiomeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */