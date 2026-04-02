/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ 
/*    */ public class SetStanceMechanic extends SkillMechanic implements ITargetedEntitySkill {
/*    */   protected PlaceholderString stance;
/*    */   
/*    */   public SetStanceMechanic(String skill, MythicLineConfig mlc) {
/* 16 */     super(skill, mlc);
/*    */     
/* 18 */     this.stance = PlaceholderString.of(mlc.getString(new String[] { "stance", "s" }, "default", new String[0]));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 23 */     if (getPlugin().getMobManager().isActiveMob(target.getUniqueId())) {
/* 24 */       ActiveMob at = getPlugin().getMobManager().getActiveMob(target.getUniqueId()).get();
/* 25 */       String stance = this.stance.get((PlaceholderMeta)data, target);
/* 26 */       at.setStance(stance);
/* 27 */       return true;
/*    */     } 
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetStanceMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */