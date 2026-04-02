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
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "inblock", aliases = {"insideblock"}, description = "Checks the material at the target location")
/*    */ public class InBlockCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition
/*    */ {
/*    */   @MythicField(name = "block", aliases = {"blocks", "b"}, description = "A list of blocks to match")
/* 22 */   private Set<Material> blocks = new HashSet<>();
/*    */ 
/*    */   
/*    */   public InBlockCondition(String line, MythicLineConfig config) {
/* 26 */     super(line);
/*    */     
/* 28 */     String[] split = config.getString(new String[] { "blocks", "block", "b" }, this.conditionVar, new String[0]).split(",");
/*    */     
/* 30 */     for (String block : split) {
/*    */       try {
/* 32 */         Material m = Material.valueOf(block.toUpperCase());
/* 33 */         this.blocks.add(m);
/* 34 */       } catch (IllegalArgumentException ex) {
/* 35 */         MythicLogger.errorConditionConfig(this, config, "'" + block + "' is not a valid material.");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 42 */     MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "Checking INBLOCK condition...", new Object[0]);
/* 43 */     Material mat = BukkitAdapter.adapt(l).getBlock().getType();
/*    */     
/* 45 */     if (this.blocks.contains(mat)) {
/* 46 */       MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "+ Material check passed for {0}", new Object[] { mat.toString() });
/* 47 */       return true;
/*    */     } 
/* 49 */     MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "! Material check failed for {0}", new Object[] { mat.toString() });
/* 50 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\InBlockCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */