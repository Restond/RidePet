/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import org.bukkit.entity.Creature;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "targetWithin", aliases = {}, description = "Tests if the target's target is within a certain distance")
/*    */ public class TargetWithinCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "distance", aliases = {"d"}, description = "Distance to check")
/*    */   private double distance;
/*    */   
/*    */   public TargetWithinCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/* 19 */     this.distance = Math.pow(Double.valueOf(mlc.getString(new String[] { "distance", "d" }, "0", new String[] { this.conditionVar })).doubleValue(), 2.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 24 */     if (!entity.isCreature()) return true;
/*    */     
/* 26 */     Creature c = (Creature)entity.getBukkitEntity();
/*    */     
/* 28 */     if (c.getTarget() == null) return false;
/*    */     
/* 30 */     if (c.getTarget().getWorld().equals(c.getWorld())) {
/* 31 */       if (c.getTarget().getLocation().distanceSquared(c.getLocation()) < this.distance) {
/* 32 */         return true;
/*    */       }
/* 34 */       return false;
/*    */     } 
/*    */     
/* 37 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\TargetWithinCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */