/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Events;
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.holograms.types.CastBar;
/*     */ import io.lumine.xikage.mythicmobs.skills.AbstractSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.CastMechanic;
/*     */ import org.bukkit.event.player.PlayerMoveEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CastTracker
/*     */   extends Aura.AuraTracker
/*     */ {
/* 102 */   private CastBar castBar = null;
/*     */   
/*     */   public CastTracker(AbstractEntity target, SkillMetadata data) {
/* 105 */     super((Aura)CastMechanic.this, data.getCaster(), target, data);
/* 106 */     start();
/*     */   }
/*     */   
/*     */   public CastTracker(AbstractLocation target, SkillMetadata data) {
/* 110 */     super((Aura)CastMechanic.this, data.getCaster(), target, data);
/* 111 */     start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void auraStart() {
/* 116 */     if (CastMechanic.this.cancelOnMove && this.skillMetadata.getCaster().getEntity().isPlayer()) {
/* 117 */       this.components.accept(Events.subscribe(PlayerMoveEvent.class)
/* 118 */           .handler(event -> {
/*     */               if (event.getPlayer().getUniqueId().equals(getCasterUUID())) {
/*     */                 terminate();
/*     */               }
/*     */             }));
/*     */     }
/*     */     
/* 125 */     if (CastMechanic.this.showCastBar) {
/* 126 */       if (!AbstractSkill.getPlugin().getHologramManager().isActive()) {
/* 127 */         CastMechanic.this.showCastBar = false;
/*     */       }
/* 129 */       Schedulers.sync().run(() -> {
/*     */             if (!hasTerminated()) {
/*     */               this.castBar = AbstractSkill.getPlugin().getHologramManager().createCastBar(this, CastMechanic.this.castingText);
/*     */               
/*     */               this.components.accept(this.castBar);
/*     */             } 
/*     */           });
/*     */     } 
/* 137 */     executeAuraSkill(CastMechanic.access$000(CastMechanic.this), this.skillMetadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public void auraTick() {
/* 142 */     if (CastMechanic.this.showCastBar && this.castBar != null) {
/* 143 */       this.castBar.setCastPercent(this.ticksRemaining / CastMechanic.access$100(CastMechanic.this));
/*     */     }
/* 145 */     executeAuraSkill(CastMechanic.access$200(CastMechanic.this), this.skillMetadata, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void auraStop() {
/* 150 */     if (this.ticksRemaining <= 0) {
/* 151 */       if (CastMechanic.this.showCastBar && this.castBar != null) {
/* 152 */         this.castBar.setFinished();
/*     */       }
/* 154 */       onCast();
/*     */     } else {
/* 156 */       if (CastMechanic.this.showCastBar && this.castBar != null) {
/* 157 */         this.castBar.setInterrupted();
/*     */       }
/* 159 */       onInterrupted();
/*     */     } 
/* 161 */     if (CastMechanic.access$300(CastMechanic.this)) {
/* 162 */       executeAuraSkill(CastMechanic.access$400(CastMechanic.this), this.skillMetadata);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onCast() {
/* 167 */     executeAuraSkill(CastMechanic.this.onCastSkill, this.skillMetadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onInterrupted() {
/* 172 */     executeAuraSkill(CastMechanic.this.onInterruptedSkill, this.skillMetadata);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\CastMechanic$CastTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */