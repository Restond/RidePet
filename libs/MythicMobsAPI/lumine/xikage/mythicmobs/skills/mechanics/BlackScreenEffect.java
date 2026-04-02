/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", version = "4.8", name = "blackScreen", aliases = {"effect:blackScreen", "e:blackScreen"}, description = "Causes the player's screen to black out")
/*    */ public class BlackScreenEffect
/*    */   extends Aura implements ITargetedEntitySkill {
/* 16 */   private static final AbstractLocation LOCATION = new AbstractLocation(null, 99999.0D, 0.0D, 99999.0D);
/*    */   
/*    */   @MythicField(name = "duration", aliases = {"d"}, version = "4.8", description = "How long the effect should last")
/*    */   private int duration;
/*    */   @MythicField(name = "cancel", aliases = {"c"}, version = "4.8", description = "If true, will cancel any existing effects immediately")
/*    */   private boolean cancel;
/*    */   
/*    */   public BlackScreenEffect(String skill, MythicLineConfig mlc) {
/* 24 */     super(skill, mlc);
/* 25 */     this.ASYNC_SAFE = false;
/*    */     
/* 27 */     this.auraName = Optional.of("#blackScreen");
/* 28 */     this.maxStacks = 1;
/* 29 */     this.refreshDuration = true;
/* 30 */     this.interval = 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 35 */     if (target.isPlayer()) {
/* 36 */       new BlackScreenEffectTracker(this, target, data);
/*    */     }
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BlackScreenEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */