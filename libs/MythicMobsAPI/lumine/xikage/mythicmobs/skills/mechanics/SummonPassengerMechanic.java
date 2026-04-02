/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ 
/*    */ public class SummonPassengerMechanic extends SkillMechanic implements INoTargetSkill {
/*    */   protected MythicMob mount;
/*    */   protected String strType;
/*    */   
/*    */   public SummonPassengerMechanic(String skill, MythicLineConfig mlc) {
/* 18 */     super(skill, mlc);
/* 19 */     this.ASYNC_SAFE = false;
/*    */     
/* 21 */     this.strType = mlc.getString(new String[] { "passenger", "p", "rider", "r", "type", "t" });
/*    */     
/* 23 */     this.mount = MythicMobs.inst().getMobManager().getMythicMob(this.strType);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 28 */     if (this.mount == null) {
/* 29 */       this.mount = MythicMobs.inst().getMobManager().getMythicMob(this.strType);
/*    */       
/* 31 */       if (this.mount == null) {
/* 32 */         MythicLogger.errorMechanicConfig(this, this.config, "The 'mob' attribute must be a valid MythicMob.");
/* 33 */         return false;
/*    */       } 
/*    */     } 
/*    */     
/* 37 */     AbstractEntity me = this.mount.spawn(data.getCaster().getEntity().getLocation(), data.getCaster().getLevel()).getEntity();
/*    */     
/* 39 */     if (me == null) return false;
/*    */     
/* 41 */     data.getCaster().getEntity().setPassenger(me);
/*    */     
/* 43 */     ((ActiveMob)MythicMobs.inst().getMobManager().getActiveMob(me.getUniqueId()).get()).setOwner(data.getCaster().getEntity().getUniqueId());
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SummonPassengerMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */