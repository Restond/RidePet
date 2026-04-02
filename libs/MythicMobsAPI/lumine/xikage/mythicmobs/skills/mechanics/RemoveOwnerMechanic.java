/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.entity.Wolf;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "removeowner", aliases = {"clearowner"}, description = "")
/*    */ public class RemoveOwnerMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   public RemoveOwnerMechanic(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 22 */     if (data.getCaster() instanceof ActiveMob) {
/* 23 */       ActiveMob am = (ActiveMob)data.getCaster();
/* 24 */       am.removeOwner();
/*    */       
/* 26 */       if (target.isPlayer() && data.getCaster().getEntity().getBukkitEntity() instanceof Wolf) {
/* 27 */         ((Wolf)data.getCaster().getEntity().getBukkitEntity()).setOwner(null);
/*    */       }
/* 29 */       return true;
/*    */     } 
/* 31 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\RemoveOwnerMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */