/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.listeners;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.SpawnerManager;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.event.entity.SpawnerSpawnEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpawnerListeners
/*    */   implements Listener
/*    */ {
/*    */   private final SpawnerManager MANAGER;
/*    */   
/*    */   public SpawnerListeners(SpawnerManager manager) {
/* 21 */     this.MANAGER = manager;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onBlockBreak(BlockBreakEvent e) {
/* 26 */     AbstractLocation location = BukkitAdapter.adapt(e.getBlock().getLocation());
/* 27 */     if (this.MANAGER.hasBreakableSpawner(location)) {
/* 28 */       MythicSpawner ms = this.MANAGER.getSpawnerAtLocation(location);
/* 29 */       this.MANAGER.removeSpawner(ms);
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.LOWEST)
/*    */   public void onMobSpawn(SpawnerSpawnEvent event) {
/* 35 */     if (this.MANAGER.getSpawnerAtLocation(BukkitAdapter.adapt(event.getSpawner().getLocation())) != null) {
/* 36 */       event.setCancelled(true);
/* 37 */       event.getSpawner().setDelay(2147483647);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\listeners\SpawnerListeners.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */