/*     */ package lumine.xikage.mythicmobs.adapters.bukkit.listeners;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*     */ import java.util.Collection;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.world.ChunkLoadEvent;
/*     */ import org.bukkit.event.world.ChunkUnloadEvent;
/*     */ import org.bukkit.event.world.WorldUnloadEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkListeners
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void ChunkLoadEvent(ChunkLoadEvent e) {
/*  29 */     if (e.getChunk() == null) {
/*     */       return;
/*     */     }
/*  32 */     ChunkLoader cl = new ChunkLoader(this, e.getChunk());
/*  33 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)cl, 10L);
/*     */   }
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
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void ChunkUnloadedEvent(ChunkUnloadEvent e) {
/*  92 */     String cS = e.getChunk().getWorld().getName() + "-" + e.getChunk().getX() + "-" + e.getChunk().getZ();
/*  93 */     Optional<Collection<MythicSpawner>> maybeSpawners = MythicMobs.inst().getSpawnerManager().getSpawnersByChunk(cS);
/*  94 */     if (maybeSpawners.isPresent()) {
/*  95 */       for (MythicSpawner ms : maybeSpawners.get()) {
/*  96 */         ms.unloadSpawner();
/*     */       }
/*     */     }
/*  99 */     for (Entity ee : e.getChunk().getEntities()) {
/* 100 */       if (ee instanceof org.bukkit.entity.LivingEntity) {
/* 101 */         ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(ee);
/*     */         
/* 103 */         if (am != null && 
/* 104 */           am.getType().getDespawns() == true) {
/* 105 */           am.setDespawned();
/* 106 */           MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/* 107 */           ee.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void WorldUnloadEvent(WorldUnloadEvent e) {
/* 117 */     for (Entity ee : e.getWorld().getEntities()) {
/* 118 */       if (ee instanceof org.bukkit.entity.LivingEntity) {
/* 119 */         ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(ee);
/*     */         
/* 121 */         if (am != null) {
/* 122 */           if (am.getType().getDespawns() == true) {
/* 123 */             am.setDespawned();
/* 124 */             MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/* 125 */             ee.remove(); continue;
/*     */           } 
/* 127 */           am.setUnloaded();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\listeners\ChunkListeners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */