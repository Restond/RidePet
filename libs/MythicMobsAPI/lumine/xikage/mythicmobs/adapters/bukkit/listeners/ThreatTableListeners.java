/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.listeners;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.EntityTargetEvent;
/*    */ import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
/*    */ 
/*    */ public class ThreatTableListeners
/*    */   implements Listener {
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void MobTargetEventNoOwner(EntityTargetLivingEntityEvent e) {
/* 19 */     if (e.getTarget() == null)
/* 20 */       return;  if (!MythicMobs.inst().getMobManager().isActiveMob(e.getEntity().getUniqueId()))
/*    */       return; 
/* 22 */     ActiveMob amE = MythicMobs.inst().getMobManager().getMythicMobInstance(e.getEntity());
/*    */     
/* 24 */     if (amE.getOwner().isPresent() && (
/* 25 */       (UUID)amE.getOwner().get()).equals(e.getTarget().getUniqueId())) {
/* 26 */       e.setCancelled(true);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void MobTargetEventNoFaction(EntityTargetLivingEntityEvent e) {
/* 34 */     if (e.getTarget() == null)
/* 35 */       return;  if (!MythicMobs.inst().getMobManager().isActiveMob(e.getEntity().getUniqueId()))
/* 36 */       return;  if (!MythicMobs.inst().getMobManager().isActiveMob(e.getTarget().getUniqueId()))
/*    */       return; 
/* 38 */     ActiveMob amE = MythicMobs.inst().getMobManager().getMythicMobInstance(e.getEntity());
/* 39 */     ActiveMob amT = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)e.getTarget());
/*    */     
/* 41 */     if (amE.getFaction() != null && 
/* 42 */       amE.getFaction().equals(amT.getFaction())) {
/* 43 */       e.setCancelled(true);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler(priority = EventPriority.LOW)
/*    */   public void MobTargetEvent(EntityTargetLivingEntityEvent e) {
/* 50 */     if (e.isCancelled())
/* 51 */       return;  if (!ConfigManager.EnableThreatTables)
/* 52 */       return;  if (!(e.getEntity() instanceof org.bukkit.entity.LivingEntity))
/*    */       return; 
/* 54 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(e.getEntity());
/*    */     
/* 56 */     if (am == null)
/* 57 */       return;  if (am.isDead() || !am.getType().usesThreatTable())
/*    */       return; 
/* 59 */     MythicMobs.debug(3, "Target Change Event called, reviewing Threat Table");
/* 60 */     if (!am.getThreatTable().targetEvent(BukkitAdapter.adapt((Entity)e.getTarget()))) {
/* 61 */       e.setCancelled(true);
/* 62 */       am.setTarget(am.getThreatTable().getTopThreatHolder());
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.LOW)
/*    */   public void MobTargetEvent(EntityTargetEvent e) {
/* 68 */     if (!ConfigManager.EnableThreatTables)
/* 69 */       return;  if (!(e.getEntity() instanceof org.bukkit.entity.LivingEntity))
/*    */       return; 
/* 71 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(e.getEntity());
/*    */     
/* 73 */     if (am == null)
/* 74 */       return;  if (am.isDead() || !am.getType().usesThreatTable() || am.getThreatTable().inCombat())
/*    */       return; 
/* 76 */     MythicMobs.debug(3, "Target Change Event called, reviewing Threat Table");
/* 77 */     if (!am.getThreatTable().targetEvent(BukkitAdapter.adapt(e.getTarget()))) {
/* 78 */       e.setCancelled(true);
/* 79 */       am.setTarget(am.getThreatTable().getTopThreatHolder());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\listeners\ThreatTableListeners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */