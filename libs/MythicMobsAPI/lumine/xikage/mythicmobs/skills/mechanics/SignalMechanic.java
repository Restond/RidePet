/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ 
/*    */ public class SignalMechanic extends SkillMechanic implements ITargetedEntitySkill {
/*    */   private PlaceholderString signal;
/*    */   
/*    */   public SignalMechanic(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/*    */     
/* 19 */     this.signal = mlc.getPlaceholderString(new String[] { "signal", "s" }, "ping", new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 24 */     if (MythicMobs.inst().getMobManager().isActiveMob(target.getUniqueId())) {
/* 25 */       ActiveMob at = MythicMobs.inst().getMobManager().getActiveMob(target.getUniqueId()).get();
/* 26 */       at.signalMob(data.getCaster().getEntity(), this.signal.get((PlaceholderMeta)data, target));
/* 27 */       return true;
/*    */     } 
/* 29 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SignalMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */