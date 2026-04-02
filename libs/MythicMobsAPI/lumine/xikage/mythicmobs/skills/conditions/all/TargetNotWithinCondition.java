/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import org.bukkit.entity.Creature;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "targetnotwithin", aliases = {}, description = "Tests if the target's target is not within a certain distance")
/*    */ public class TargetNotWithinCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   private double distance;
/*    */   
/*    */   public TargetNotWithinCondition(String line, MythicLineConfig mlc) {
/* 16 */     super(line);
/* 17 */     this.distance = Math.pow(Double.valueOf(mlc.getString(new String[] { "distance", "d" }, this.conditionVar, new String[] { "0" })).doubleValue(), 2.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 22 */     if (!entity.isCreature()) return true;
/*    */     
/* 24 */     Creature c = (Creature)entity.getBukkitEntity();
/*    */     
/* 26 */     if (c.getTarget() == null) return false;
/*    */     
/* 28 */     if (c.getTarget().getWorld().equals(c.getWorld())) {
/* 29 */       if (c.getTarget().getLocation().distanceSquared(c.getLocation()) > this.distance) {
/* 30 */         return true;
/*    */       }
/* 32 */       return false;
/*    */     } 
/*    */     
/* 35 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\TargetNotWithinCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */