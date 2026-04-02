/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Joikd", name = "haspermission", aliases = {"permission"}, description = "Tests if the target player has a permission")
/*    */ public class HasPermissionCondition extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "permission", aliases = {"p"}, description = "The permission to check for")
/* 13 */   private String perm = "";
/*    */ 
/*    */   
/*    */   public HasPermissionCondition(String line, MythicLineConfig mlc) {
/* 17 */     super(line);
/*    */     
/* 19 */     this.perm = mlc.getString(new String[] { "permission", "p" }, this.conditionVar, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 24 */     if (target.isPlayer()) {
/* 25 */       return target.asPlayer().hasPermission(this.perm);
/*    */     }
/* 27 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HasPermissionCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */