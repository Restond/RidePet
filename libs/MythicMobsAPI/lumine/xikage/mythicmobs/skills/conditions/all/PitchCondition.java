/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "jaylawl", name = "pitch", aliases = {}, version = "4.5", description = "Checks if the pitch of the target entity is within a range")
/*    */ public class PitchCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "pitch", aliases = {"p"}, description = "The number range to match")
/*    */   private String yaw;
/*    */   
/*    */   public PitchCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/* 19 */     this.yaw = mlc.getString(new String[] { "pitch", "p" }, this.conditionVar, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 24 */     return MythicUtil.matchNumber(this.yaw, e.getBukkitEntity().getLocation().getPitch());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\PitchCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */