/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "lunge", description = "Causes the caster to lunge forward at the target entity")
/*    */ public class LungeMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected float velocity;
/*    */   protected float velocityY;
/*    */   
/*    */   public LungeMechanic(String line, MythicLineConfig mlc) {
/* 20 */     super(line, mlc);
/*    */     
/* 22 */     this.velocity = mlc.getFloat(new String[] { "velocity", "v", "magnitude" }, 1.0F);
/* 23 */     this.velocityY = mlc.getFloat(new String[] { "velocityy", "yvelocity", "vy", "yv" }, 0.01337F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 28 */     Entity m = data.getCaster().getEntity().getBukkitEntity();
/* 29 */     Entity e = target.getBukkitEntity();
/* 30 */     Vector v = m.getLocation().toVector().subtract(e.getLocation().toVector()).normalize();
/*    */     
/* 32 */     if (this.velocityY != 0.01337D) {
/* 33 */       v.setY(this.velocityY);
/*    */     }
/*    */     
/* 36 */     v = v.multiply(this.velocity);
/*    */     
/* 38 */     if (v.length() > 4.0D) {
/* 39 */       v = v.normalize().multiply(4);
/*    */     }
/*    */     
/* 42 */     m.setVelocity(v);
/*    */     
/* 44 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\LungeMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */