/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.utils.events.Events;
/*    */ import io.lumine.utils.terminable.Terminable;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.OnShootMechanic;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.entity.EntityShootBowEvent;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Tracker
/*    */   extends Aura.AuraTracker
/*    */   implements IParentSkill, Runnable
/*    */ {
/*    */   public Tracker(SkillMetadata data, AbstractEntity entity) {
/* 58 */     super((Aura)paramOnShootMechanic, entity, data);
/* 59 */     start();
/*    */   }
/*    */ 
/*    */   
/*    */   public void auraStart() {
/* 64 */     registerAuraComponent(
/* 65 */         (Terminable)Events.subscribe(EntityShootBowEvent.class)
/* 66 */         .filter(event -> event.getEntity().getUniqueId().equals(((AbstractEntity)this.entity.get()).getUniqueId()))
/* 67 */         .handler(event -> {
/*    */             SkillMetadata meta = this.skillMetadata.deepClone();
/*    */             
/*    */             meta.setEntityTarget(BukkitAdapter.adapt((Entity)event.getEntity()));
/*    */             if (executeAuraSkill(OnShootMechanic.this.onShootSkill, meta)) {
/*    */               consumeCharge();
/*    */               if (OnShootMechanic.this.cancelEvent) {
/*    */                 event.setCancelled(true);
/*    */               }
/*    */             } 
/*    */           }));
/* 78 */     executeAuraSkill(OnShootMechanic.access$000(OnShootMechanic.this), this.skillMetadata);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\OnShootMechanic$Tracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */