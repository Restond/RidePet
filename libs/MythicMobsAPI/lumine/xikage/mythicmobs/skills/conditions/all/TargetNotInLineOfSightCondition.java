/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "targetNotInLineOfSight", aliases = {}, description = "Tests if the target doesn't have line of sight to their target")
/*    */ public class TargetNotInLineOfSightCondition extends SkillCondition implements IEntityCondition {
/*    */   public TargetNotInLineOfSightCondition(String line, MythicLineConfig mlc) {
/* 14 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 19 */     if (!entity.isCreature()) return true;
/*    */     
/* 21 */     Creature c = (Creature)entity.getBukkitEntity();
/*    */     
/* 23 */     if (c.getTarget() == null) return false;
/*    */     
/* 25 */     return !c.hasLineOfSight((Entity)c.getTarget());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\TargetNotInLineOfSightCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */