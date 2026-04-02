/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "crouching", aliases = {"sneaking", "iscrouching", "issneaking"}, description = "Whether or not the target entity is crouching")
/*    */ public class CrouchingCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition {
/*    */   public CrouchingCondition(String line, MythicLineConfig mlc) {
/* 15 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 20 */     if (!e.isPlayer()) return false;
/*    */     
/* 22 */     return ((Player)e.getBukkitEntity()).isSneaking();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\CrouchingCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */