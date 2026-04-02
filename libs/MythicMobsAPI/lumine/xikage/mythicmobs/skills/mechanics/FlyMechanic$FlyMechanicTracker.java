/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.FlyMechanic;
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
/*    */ public class FlyMechanicTracker
/*    */   extends Aura.AuraTracker
/*    */ {
/*    */   public FlyMechanicTracker(AbstractEntity target, SkillMetadata data) {
/* 28 */     super((Aura)FlyMechanic.this, target, data);
/* 29 */     start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void auraStart() {
/* 34 */     executeAuraSkill(FlyMechanic.access$000(FlyMechanic.this), this.skillMetadata);
/* 35 */     this.skillMetadata.getCaster().getEntity().asPlayer().setFlying(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void auraStop() {
/* 40 */     this.skillMetadata.getCaster().getEntity().asPlayer().setFlying(false);
/* 41 */     executeAuraSkill(FlyMechanic.access$100(FlyMechanic.this), this.skillMetadata);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\FlyMechanic$FlyMechanicTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */