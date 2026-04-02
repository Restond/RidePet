/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import org.bukkit.entity.AnimalTamer;
/*    */ import org.bukkit.entity.Wolf;
/*    */ 
/*    */ public class SetOwnerMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   public SetOwnerMechanic(String skill, MythicLineConfig mlc) {
/* 16 */     super(skill, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 21 */     if (data.getCaster() instanceof ActiveMob) {
/* 22 */       ActiveMob am = (ActiveMob)data.getCaster();
/* 23 */       am.setOwner(target.getUniqueId());
/*    */       
/* 25 */       if (target.isPlayer() && data.getCaster().getEntity().getBukkitEntity() instanceof Wolf) {
/* 26 */         ((Wolf)data.getCaster().getEntity().getBukkitEntity()).setOwner((AnimalTamer)target.getBukkitEntity());
/* 27 */         ((Wolf)data.getCaster().getEntity().getBukkitEntity()).setTamed(true);
/*    */       } 
/* 29 */       return true;
/*    */     } 
/* 31 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetOwnerMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */