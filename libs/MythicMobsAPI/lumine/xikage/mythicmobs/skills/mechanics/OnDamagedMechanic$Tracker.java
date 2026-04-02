/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.events.Events;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitTriggerMetadata;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.OnDamagedMechanic;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ class Tracker
/*     */   extends Aura.AuraTracker
/*     */   implements IParentSkill, Runnable
/*     */ {
/*     */   public Tracker(SkillMetadata data, AbstractEntity entity) {
/* 109 */     super((Aura)paramOnDamagedMechanic, entity, data);
/* 110 */     start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void auraStart() {
/* 115 */     registerAuraComponent(
/* 116 */         (Terminable)Events.subscribe(EntityDamageByEntityEvent.class, EventPriority.HIGHEST)
/* 117 */         .filter(event -> event.getEntity().getUniqueId().equals(((AbstractEntity)this.entity.get()).getUniqueId()))
/* 118 */         .handler(event -> {
/*     */             MythicLogger.log("fired event");
/*     */             SkillMetadata meta = this.skillMetadata.deepClone();
/*     */             meta.setEntityTarget(BukkitAdapter.adapt(event.getEntity()));
/*     */             BukkitTriggerMetadata.apply(meta, event);
/*     */             if (executeAuraSkill(OnDamagedMechanic.this.onDamagedSkill, meta)) {
/*     */               consumeCharge();
/*     */               if (OnDamagedMechanic.this.cancelDamage) {
/*     */                 event.setCancelled(true);
/*     */               } else if (OnDamagedMechanic.this.modDamage) {
/*     */                 double damage = OnDamagedMechanic.this.calculateDamage(this.entity.get(), event);
/*     */                 event.setDamage(damage);
/*     */               } 
/*     */             } 
/*     */           }));
/* 133 */     executeAuraSkill(OnDamagedMechanic.access$000(OnDamagedMechanic.this), this.skillMetadata);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\OnDamagedMechanic$Tracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */