/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "threat", aliases = {"threatchange", "threatmod"}, description = "Modifies the target entity's threat")
/*    */ public class ThreatMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill {
/* 15 */   protected float amount = 1.0F;
/*    */   
/*    */   public ThreatMechanic(String line, MythicLineConfig mlc) {
/* 18 */     super(line, mlc);
/* 19 */     this.target_creative = false;
/*    */     
/* 21 */     this.amount = (float)mlc.getDouble("amount", 1.0D);
/* 22 */     this.amount = (float)mlc.getDouble("a", this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 27 */     if (!(data.getCaster() instanceof ActiveMob)) {
/* 28 */       return false;
/*    */     }
/*    */     
/* 31 */     ActiveMob am = (ActiveMob)data.getCaster();
/* 32 */     if (target.isDead() || target.getHealth() <= 0.0D) return false;
/*    */     
/* 34 */     if (am.getThreatTable() == null) return false;
/*    */     
/* 36 */     if (MythicMobs.inst().getMobManager().isActiveMob(target)) {
/* 37 */       ActiveMob amt = MythicMobs.inst().getMobManager().getMythicMobInstance(target);
/*    */       
/* 39 */       if (am.hasFaction() && am.getFaction().equals(amt.getFaction())) {
/* 40 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 44 */     if (this.amount > 0.0F) {
/* 45 */       am.getThreatTable().threatGain(target, this.amount);
/*    */     } else {
/* 47 */       am.getThreatTable().threatLoss(target, (-1.0F * this.amount));
/*    */     } 
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ThreatMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */