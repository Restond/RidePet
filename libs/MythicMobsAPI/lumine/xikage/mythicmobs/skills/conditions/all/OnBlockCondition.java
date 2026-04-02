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
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "onBlock", aliases = {}, description = "Matches the block the target entity is standing on")
/*    */ public class OnBlockCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition
/*    */ {
/*    */   @MythicField(name = "material", aliases = {"m"}, description = "A list of materials")
/* 23 */   private Set<Material> blocks = new HashSet<>();
/*    */ 
/*    */   
/*    */   public OnBlockCondition(String line, MythicLineConfig config) {
/* 27 */     super(line);
/*    */     
/* 29 */     String materials = config.getString(new String[] { "type", "material", "m", "block", "b" }, "STONE", new String[0]);
/*    */     
/* 31 */     String[] split = materials.split(",");
/*    */     
/* 33 */     for (String block : split) {
/*    */       try {
/* 35 */         Material m = Material.valueOf(block.toUpperCase());
/* 36 */         this.blocks.add(m);
/* 37 */       } catch (Exception ex) {
/* 38 */         MythicLogger.errorConditionConfig(this, config, "'" + block + "' is not a valid Material");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 45 */     Material mat = BukkitAdapter.adapt(l).getBlock().getRelative(BlockFace.DOWN).getType();
/* 46 */     MythicLogger.debug(MythicLogger.DebugLevel.CONDITION, "Checking OnBlock: {0} vs {1}", new Object[] { mat.toString(), this.blocks.toString() });
/* 47 */     return this.blocks.contains(mat);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\OnBlockCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */