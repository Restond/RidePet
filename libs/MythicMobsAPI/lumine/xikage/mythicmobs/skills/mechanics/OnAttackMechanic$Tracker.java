/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Events;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.OnAttackMechanic;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
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
/*  71 */     super((Aura)paramOnAttackMechanic, entity, data);
/*  72 */     start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void auraStart() {
/*  77 */     registerAuraComponent(
/*  78 */         (Terminable)Events.subscribe(EntityDamageByEntityEvent.class)
/*  79 */         .filter(event -> (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK))
/*  80 */         .filter(event -> event.getDamager().getUniqueId().equals(((AbstractEntity)this.entity.get()).getUniqueId()))
/*  81 */         .filter(event -> {
/*     */             Optional<Object> md = BukkitAdapter.adapt(event.getDamager()).getMetadata("doing-skill-damage");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             return md.isPresent() ? (!((Boolean)md.get()).booleanValue()) : true;
/*  89 */           }).handler(event -> {
/*     */             SkillMetadata meta = this.skillMetadata.deepClone();
/*     */             
/*     */             meta.setEntityTarget(BukkitAdapter.adapt(event.getEntity()));
/*     */             if (executeAuraSkill(OnAttackMechanic.this.onAttackSkill, meta)) {
/*     */               consumeCharge();
/*     */               if (OnAttackMechanic.this.cancelDamage) {
/*     */                 event.setCancelled(true);
/*     */               } else if (OnAttackMechanic.this.modDamage) {
/*     */                 double damage = OnAttackMechanic.this.calculateDamage(event.getDamage());
/*     */                 event.setDamage(damage);
/*     */               } 
/*     */             } 
/*     */           }));
/* 103 */     executeAuraSkill(OnAttackMechanic.access$000(OnAttackMechanic.this), this.skillMetadata);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\OnAttackMechanic$Tracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */