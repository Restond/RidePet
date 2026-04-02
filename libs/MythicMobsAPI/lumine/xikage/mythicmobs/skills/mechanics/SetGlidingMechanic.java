/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class SetGlidingMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected boolean b;
/*    */   
/*    */   public SetGlidingMechanic(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/*    */     
/* 19 */     this.b = mlc.getBoolean(new String[] { "gliding", "g" }, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 24 */     if (target.isLiving()) {
/* 25 */       LivingEntity e = (LivingEntity)BukkitAdapter.adapt(target);
/* 26 */       e.setGliding(this.b);
/*    */     } 
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetGlidingMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */