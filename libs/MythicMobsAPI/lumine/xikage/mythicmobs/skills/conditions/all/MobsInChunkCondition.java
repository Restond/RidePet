/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "mobsinchunk", aliases = {}, description = "Matches a range to how many mobs are in the target location's chunk")
/*    */ public class MobsInChunkCondition
/*    */   extends SkillCondition implements ILocationCondition {
/*    */   @MythicField(name = "amount", aliases = {"a"}, description = "The number range to match")
/*    */   private RangedDouble amount;
/*    */   
/*    */   public MobsInChunkCondition(String line, MythicLineConfig mlc) {
/* 19 */     super(line);
/*    */     
/* 21 */     String k = mlc.getString(new String[] { "amount", "a" }, "0", new String[] { this.conditionVar });
/* 22 */     this.amount = new RangedDouble(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 27 */     return this.amount.equals(Integer.valueOf((BukkitAdapter.adapt(l).getChunk().getEntities()).length));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\MobsInChunkCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */