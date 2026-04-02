/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.GenericCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.MetaSkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Set;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "sudoskill", aliases = {"sudo"}, description = "Forces the inherited target to execute the skill at the targeted entity")
/*    */ public class SudoSkillMechanic
/*    */   extends MetaSkillMechanic {
/*    */   private boolean casterAsTrigger = false;
/*    */   
/*    */   public SudoSkillMechanic(String skill, MythicLineConfig mlc) {
/* 24 */     super(skill, mlc);
/*    */     
/* 26 */     this.casterAsTrigger = mlc.getBoolean(new String[] { "setcasterastrigger", "cat" }, false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 32 */     Set<AbstractEntity> targets = data.getEntityTargets();
/* 33 */     if (this.targeter.isPresent()) {
/* 34 */       SkillTargeter targeter = this.targeter.get();
/* 35 */       if (targeter instanceof IEntitySelector) {
/* 36 */         targets = ((IEntitySelector)targeter).getEntities(data);
/* 37 */         ((IEntitySelector)targeter).filter(data, targetsCreativePlayers());
/*    */       } 
/*    */     } 
/* 40 */     if (targets == null || targets.size() == 0) return false;
/*    */     
/* 42 */     for (AbstractEntity target : targets) {
/*    */       GenericCaster genericCaster;
/*    */       
/* 45 */       if (MythicMobs.inst().getMobManager().isActiveMob(target)) {
/* 46 */         ActiveMob activeMob = MythicMobs.inst().getMobManager().getMythicMobInstance(target);
/*    */       } else {
/* 48 */         genericCaster = new GenericCaster(target);
/*    */       } 
/*    */       
/* 51 */       SkillMetadata newData = data.deepClone();
/* 52 */       newData.setCaster((SkillCaster)genericCaster);
/* 53 */       if (this.casterAsTrigger) {
/* 54 */         newData.setTrigger(data.getCaster().getEntity());
/*    */       }
/*    */       
/* 57 */       if (this.metaskill.isPresent()) {
/* 58 */         Skill ms = this.metaskill.get();
/* 59 */         if (ms.isUsable(newData)) {
/* 60 */           MythicMobs.debug(3, "------ Executing SudoSkill " + this.skillName);
/* 61 */           if (this.forceSync == true) {
/* 62 */             newData.setIsAsync(false);
/* 63 */             (new Object(this, newData, ms))
/*    */ 
/*    */ 
/*    */ 
/*    */               
/* 68 */               .runTask((Plugin)MythicMobs.inst()); continue;
/*    */           } 
/* 70 */           ms.execute(newData);
/*    */           continue;
/*    */         } 
/* 73 */         MythicMobs.debug(3, "------ Skill is not usable at this time! Cancelling.");
/* 74 */         return false;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 79 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SudoSkillMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */