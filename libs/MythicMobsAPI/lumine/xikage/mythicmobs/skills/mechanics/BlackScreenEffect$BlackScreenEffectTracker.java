/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.BlackScreenEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlackScreenEffectTracker
/*    */   extends Aura.AuraTracker
/*    */ {
/*    */   public BlackScreenEffectTracker(AbstractEntity target, SkillMetadata data) {
/* 44 */     super((Aura)BlackScreenEffect.this, target, data);
/* 45 */     start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void auraStart() {
/* 50 */     executeAuraSkill(BlackScreenEffect.access$000(BlackScreenEffect.this), this.skillMetadata);
/* 51 */     AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().forcePlayCredits(((AbstractEntity)this.entity.get()).asPlayer(), 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void auraTick() {
/* 56 */     AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().forcePlayCredits(((AbstractEntity)this.entity.get()).asPlayer(), 1.0F);
/* 57 */     executeAuraSkill(BlackScreenEffect.access$100(BlackScreenEffect.this), this.skillMetadata);
/*    */   }
/*    */ 
/*    */   
/*    */   public void auraStop() {
/* 62 */     AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().forceCloseWindow(((AbstractEntity)this.entity.get()).asPlayer());
/* 63 */     executeAuraSkill(BlackScreenEffect.access$200(BlackScreenEffect.this), this.skillMetadata);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BlackScreenEffect$BlackScreenEffectTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */