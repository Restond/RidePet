/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.StunMechanic;
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
/*    */ public class StunMechanicTracker
/*    */   extends Aura.AuraTracker
/*    */ {
/*    */   private double x;
/*    */   private double y;
/*    */   private double z;
/*    */   private float pitch;
/*    */   private float yaw;
/*    */   private boolean hadAI = false;
/*    */   private boolean hadGravity = false;
/*    */   
/*    */   public StunMechanicTracker(AbstractEntity target, SkillMetadata data) {
/* 42 */     super((Aura)StunMechanic.this, target, data);
/*    */     
/* 44 */     this.x = target.getLocation().getX();
/* 45 */     this.y = target.getLocation().getY();
/* 46 */     this.z = target.getLocation().getZ();
/* 47 */     this.pitch = target.getLocation().getPitch();
/* 48 */     this.yaw = target.getLocation().getYaw();
/*    */     
/* 50 */     start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void auraStart() {
/* 55 */     if (StunMechanic.access$000(StunMechanic.this) && (
/* 56 */       (AbstractEntity)this.entity.get()).hasAI()) {
/* 57 */       this.hadAI = true;
/* 58 */       ((AbstractEntity)this.entity.get()).setAI(false);
/*    */     } 
/*    */     
/* 61 */     if (StunMechanic.access$100(StunMechanic.this) && (
/* 62 */       (AbstractEntity)this.entity.get()).hasGravity()) {
/* 63 */       this.hadGravity = true;
/* 64 */       ((AbstractEntity)this.entity.get()).setGravity(false);
/*    */     } 
/*    */     
/* 67 */     executeAuraSkill(StunMechanic.access$200(StunMechanic.this), this.skillMetadata);
/*    */   }
/*    */ 
/*    */   
/*    */   public void auraTick() {
/* 72 */     if (((AbstractEntity)this.entity.get()).isOnGround()) {
/* 73 */       if (!StunMechanic.access$300(StunMechanic.this)) {
/* 74 */         this.pitch = ((AbstractEntity)this.entity.get()).getLocation().getPitch();
/* 75 */         this.yaw = ((AbstractEntity)this.entity.get()).getLocation().getYaw();
/*    */       } 
/* 77 */       AbstractSkill.getPlugin().getVolatileCodeHandler().getEntityHandler().setLocation(this.entity.get(), this.x, this.y, this.z, this.yaw, this.pitch, StunMechanic.access$300(StunMechanic.this), StunMechanic.access$100(StunMechanic.this));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void auraStop() {
/* 83 */     if (StunMechanic.access$000(StunMechanic.this) && this.hadAI) {
/* 84 */       ((AbstractEntity)this.entity.get()).setAI(true);
/*    */     }
/* 86 */     if (StunMechanic.access$100(StunMechanic.this) && this.hadGravity) {
/* 87 */       ((AbstractEntity)this.entity.get()).setGravity(true);
/*    */     }
/* 89 */     executeAuraSkill(StunMechanic.access$400(StunMechanic.this), this.skillMetadata);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\StunMechanic$StunMechanicTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */