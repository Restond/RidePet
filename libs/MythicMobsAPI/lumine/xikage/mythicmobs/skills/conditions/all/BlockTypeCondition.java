/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "blockType", aliases = {}, description = "Tests the material type present at the target location")
/*    */ public class BlockTypeCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition {
/*    */   @MythicField(name = "types", aliases = {"type", "t"}, description = "A list of materials to check")
/* 20 */   private Set<Material> types = new HashSet<>();
/*    */ 
/*    */   
/*    */   public BlockTypeCondition(String line, MythicLineConfig mlc) {
/* 24 */     super(line);
/*    */     
/* 26 */     String b = mlc.getString(new String[] { "types", "type", "t" }, "DIRT", new String[] { this.conditionVar });
/*    */     
/* 28 */     for (String s : b.split(",")) {
/*    */       try {
/* 30 */         this.types.add(Material.valueOf(s.toUpperCase()));
/* 31 */       } catch (IllegalArgumentException ex) {
/* 32 */         MythicLogger.errorConditionConfig(this, mlc, "'" + s + "' is not a valid material.");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 40 */     return this.types.contains(BukkitAdapter.adapt(l).getBlock().getType());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\BlockTypeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */