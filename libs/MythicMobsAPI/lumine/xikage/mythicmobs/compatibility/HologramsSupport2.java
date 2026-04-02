/*     */ package lumine.xikage.mythicmobs.compatibility;
/*     */ 
/*     */ import com.sainttx.holograms.HologramPlugin;
/*     */ import com.sainttx.holograms.api.Hologram;
/*     */ import com.sainttx.holograms.api.HologramManager;
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
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
/*     */ public class HologramsSupport2
/*     */   implements Listener
/*     */ {
/*     */   private final MythicMobs core;
/*     */   private HologramManager hologramManager;
/*  33 */   private ConcurrentHashMap<UUID, Nameplate> nameplates = new ConcurrentHashMap<>();
/*  34 */   private ConcurrentHashMap<UUID, HealthBar> healthBars = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public HologramsSupport2(MythicMobs mm) {
/*  39 */     this.core = mm;
/*  40 */     this.hologramManager = ((HologramPlugin)JavaPlugin.getPlugin(HologramPlugin.class)).getHologramManager();
/*     */     
/*  42 */     if (this.hologramManager == null)
/*     */       return; 
/*  44 */     this.hologramManager.getActiveHolograms().values().forEach(hologram -> {
/*     */           if (hologram.getId().startsWith("#Temp")) {
/*     */             Schedulers.sync().runLater((), 1L);
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     this.core.getServer().getPluginManager().registerEvents(this, (Plugin)this.core);
/*  54 */     this.core.getServer().getScheduler().scheduleAsyncRepeatingTask((Plugin)this.core, () -> { this.nameplates.values().removeIf(()); this.healthBars.values().removeIf(()); this.healthBars.values().stream().forEach(()); }0L, 10L);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     this.core.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)this.core, () -> { this.nameplates.values().stream().forEach(()); this.healthBars.values().stream().forEach(()); }0L, 1L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNameplate(ActiveMob am) {
/*  66 */     if (!this.nameplates.containsKey(am.getUniqueId())) {
/*  67 */       this.nameplates.put(am.getUniqueId(), new Nameplate(this, am));
/*     */     }
/*     */   }
/*     */   
/*     */   public void shutdown() {
/*  72 */     this.healthBars.values().stream().forEach(bar -> bar.remove());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onDamaged(EntityDamageEvent event) {
/*  77 */     UUID uuid = event.getEntity().getUniqueId();
/*     */     
/*  79 */     if (this.core.getMobManager().isActiveMob(uuid)) {
/*  80 */       ActiveMob am = MythicMobs.inst().getMobManager().getActiveMob(uuid).get();
/*     */       
/*  82 */       if (this.healthBars.containsKey(uuid)) {
/*  83 */         ((HealthBar)this.healthBars.get(uuid)).update();
/*     */       }
/*  85 */       else if (am.getType().getConfig().getBoolean("HealthBar.Enabled", false) == true) {
/*  86 */         this.healthBars.put(uuid, new HealthBar(this, am));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onMythicMobDeath(MythicMobDeathEvent event) {
/*  94 */     UUID uuid = event.getEntity().getUniqueId();
/*  95 */     if (this.nameplates.containsKey(uuid)) {
/*  96 */       ((Nameplate)this.nameplates.get(uuid)).remove();
/*  97 */       this.nameplates.remove(event.getEntity().getUniqueId());
/*     */     } 
/*  99 */     if (this.healthBars.containsKey(uuid)) {
/* 100 */       ((HealthBar)this.healthBars.get(uuid)).remove();
/* 101 */       this.healthBars.remove(event.getEntity().getUniqueId());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\HologramsSupport2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */