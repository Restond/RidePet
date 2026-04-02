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
/*    */ @MythicCondition(author = "Ashijin", name = "targetInLineOfSight", aliases = {}, description = "Tests if the target has line of sight to their target")
/*    */ public class TargetInLineOfSightCondition extends SkillCondition implements IEntityCondition {
/*    */   public TargetInLineOfSightCondition(String line, MythicLineConfig mlc) {
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
/* 25 */     return c.hasLineOfSight((Entity)c.getTarget());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\TargetInLineOfSightCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */