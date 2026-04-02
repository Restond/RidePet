/*    */ package saukiya.sxattribute.data.attribute.sub.damage;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.attribute.sub.damage.TearingAttribute;
/*    */ import github.saukiya.sxattribute.data.eventdata.sub.DamageEventData;
/*    */ import org.bukkit.EntityEffect;
/*    */ import org.bukkit.Particle;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
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
/*    */ class null
/*    */   extends BukkitRunnable
/*    */ {
/* 42 */   int i = 0;
/*    */ 
/*    */   
/*    */   public void run() {
/* 46 */     this.i++;
/* 47 */     if (this.i >= 12 / size || damageEventData.getEntity().isDead() || damageEventData.getEvent().isCancelled())
/* 48 */       cancel(); 
/* 49 */     damageEventData.getEntity().playEffect(EntityEffect.HURT);
/* 50 */     EntityDamageByEntityEvent event = new EntityDamageByEntityEvent((Entity)damageEventData.getDamager(), (Entity)damageEventData.getEntity(), EntityDamageEvent.DamageCause.CUSTOM, tearingDamage);
/* 51 */     if (!event.isCancelled()) {
/* 52 */       double damage = (damageEventData.getEntity().getHealth() < event.getDamage()) ? damageEventData.getEntity().getHealth() : event.getDamage();
/* 53 */       damageEventData.getEntity().setHealth(damageEventData.getEntity().getHealth() - damage);
/* 54 */       if (SXAttribute.getVersionSplit()[1] >= 9) {
/* 55 */         damageEventData.getEntity().getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, damageEventData.getEntity().getEyeLocation().add(0.0D, -1.0D, 0.0D), 2, 0.2D, 0.2D, 0.2D, 0.10000000149011612D);
/*    */       }
/* 57 */       if (damageEventData.getDamager() instanceof Player)
/* 58 */         ((Player)damageEventData.getDamager()).playSound(damageEventData.getEntity().getEyeLocation(), "ENTITY_" + damageEventData.getEntity().getType().toString() + "_HURT", 1.0F, 1.0F); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\damage\TearingAttribute$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */