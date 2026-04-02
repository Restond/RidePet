/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "onattack", aliases = {"onhit"}, description = "Applies an aura to the target that triggers a skill when they damage something")
/*    */ public class OnAttackMechanic
/*    */   extends Aura
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   @MythicField(name = "onAttack", aliases = {"oa"}, version = "4.6", description = "Skill to execute if the target hits something")
/* 25 */   protected Optional<Skill> onAttackSkill = Optional.empty();
/*    */   
/*    */   protected String onAttackSkillName;
/*    */   
/*    */   @MythicField(name = "cancelEvent", aliases = {"ce"}, defValue = "false", version = "4.6", description = "Whether or not to cancel the event that triggered the aura")
/*    */   protected boolean cancelDamage;
/*    */   
/*    */   protected boolean modDamage = false;
/*    */   
/*    */   @MythicField(name = "damageAdd", aliases = {"a"}, defValue = "0", version = "4.6", description = "")
/*    */   protected double damageAdd;
/*    */   @MythicField(name = "damageMultiplier", aliases = {"m"}, defValue = "1", version = "4.6", description = "")
/*    */   protected double damageMult;
/*    */   
/*    */   public OnAttackMechanic(String skill, MythicLineConfig mlc) {
/* 40 */     super(skill, mlc);
/*    */     
/* 42 */     this.onAttackSkillName = mlc.getString(new String[] { "onattackskill", "onattack", "oa", "onmelee", "onhitskill", "onhit", "oh" });
/*    */     
/* 44 */     this.cancelDamage = mlc.getBoolean(new String[] { "cancelevent", "ce", "canceldamage", "cd" }, false);
/* 45 */     this.damageAdd = mlc.getDouble(new String[] { "damageadd", "add", "a" }, 0.0D);
/* 46 */     this.damageMult = mlc.getDouble(new String[] { "damagemultiplier", "multiplier", "m" }, 1.0D);
/*    */     
/* 48 */     if (this.damageAdd != 0.0D || this.damageMult != 1.0D) {
/* 49 */       this.modDamage = true;
/*    */     }
/*    */     
/* 52 */     MythicMobs.inst().getSkillManager().queueSecondPass(() -> {
/*    */           if (this.onAttackSkillName != null)
/*    */             this.onAttackSkill = MythicMobs.inst().getSkillManager().getSkill(this.onAttackSkillName); 
/*    */         });
/*    */   }
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 59 */     new Tracker(this, data, target);
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   protected double calculateDamage(double damage) {
/* 64 */     if (this.cancelDamage == true) return 0.0D; 
/* 65 */     return (damage + this.damageAdd) * this.damageMult;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\OnAttackMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */