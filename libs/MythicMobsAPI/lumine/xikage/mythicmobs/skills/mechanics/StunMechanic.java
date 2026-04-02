/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", version = "4.8", name = "stun", description = "Applies an aura that stuns the target entity")
/*    */ public class StunMechanic extends Aura implements ITargetedEntitySkill {
/*    */   private boolean stopAI;
/*    */   private boolean stopGravity;
/*    */   private boolean freezeFacing;
/*    */   
/*    */   public StunMechanic(String line, MythicLineConfig mlc) {
/* 18 */     super(line, mlc);
/* 19 */     this.ASYNC_SAFE = false;
/*    */     
/* 21 */     this.stopAI = mlc.getBoolean(new String[] { "stopai", "ai" }, false);
/* 22 */     this.stopGravity = mlc.getBoolean(new String[] { "gravity", "g" }, false);
/* 23 */     this.freezeFacing = mlc.getBoolean(new String[] { "facing", "face", "f" }, false);
/* 24 */     this.interval = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 29 */     new StunMechanicTracker(this, target, data);
/* 30 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\StunMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */