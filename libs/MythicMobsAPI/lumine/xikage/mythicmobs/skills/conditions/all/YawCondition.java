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
/*    */ @MythicCondition(author = "jaylawl", name = "yaw", aliases = {}, version = "4.5", description = "Checks the yaw of the target entity against a range.")
/*    */ public class YawCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "yaw", aliases = {"y"}, description = "The yaw to check for")
/*    */   private String yaw;
/*    */   
/*    */   public YawCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/* 19 */     this.yaw = mlc.getString(new String[] { "yaw", "y" }, this.conditionVar, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 24 */     return MythicUtil.matchNumber(this.yaw, e.getBukkitEntity().getLocation().getYaw());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\YawCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */