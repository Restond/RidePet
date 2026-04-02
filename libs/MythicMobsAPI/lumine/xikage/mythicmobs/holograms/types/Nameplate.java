/*    */ package lumine.xikage.mythicmobs.holograms.types;
/*    */ 
/*    */ import io.lumine.utils.events.Events;
/*    */ import io.lumine.utils.tasks.Scheduler;
/*    */ import io.lumine.utils.terminable.Terminable;
/*    */ import io.lumine.utils.terminable.TerminableRegistry;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
/*    */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDespawnEvent;
/*    */ import io.lumine.xikage.mythicmobs.holograms.HologramManager;
/*    */ import io.lumine.xikage.mythicmobs.holograms.IHologram;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ public class Nameplate
/*    */   implements Terminable
/*    */ {
/*    */   private final ActiveMob activemob;
/*    */   private final HologramManager manager;
/*    */   private IHologram hologram;
/*    */   private double yOffset;
/* 23 */   private TerminableRegistry components = TerminableRegistry.create();
/*    */   
/*    */   public Nameplate(HologramManager manager, ActiveMob am) {
/* 26 */     this.manager = manager;
/*    */     
/* 28 */     this.activemob = am;
/* 29 */     this.yOffset = am.getType().getConfig().getDouble("Disguise.NameplateOffset", 0.5249999761581421D);
/* 30 */     this.yOffset = am.getType().getConfig().getDouble("Nameplate.Offset", this.yOffset);
/*    */     
/* 32 */     this.hologram = manager.createHologram("#TempNameplate" + this.activemob.getUniqueId().toString(), getLocation(), am.getDisplayName());
/*    */     
/* 34 */     this.components.accept(this.hologram);
/* 35 */     this.components.accept(Events.subscribe(MythicMobDeathEvent.class).handler(event -> {
/*    */             UUID uuid = event.getEntity().getUniqueId();
/*    */             if (this.activemob.getUniqueId().equals(uuid)) {
/*    */               terminate();
/*    */             }
/*    */           }));
/* 41 */     this.components.accept(Events.subscribe(MythicMobDespawnEvent.class).handler(event -> {
/*    */             UUID uuid = event.getEntity().getUniqueId();
/*    */             if (this.activemob.getUniqueId().equals(uuid)) {
/*    */               terminate();
/*    */             }
/*    */           }));
/* 47 */     this.components.accept(Scheduler.runTaskRepeatingAsync(() -> check(), 1L, 10L));
/* 48 */     this.components.accept(Scheduler.runTaskRepeatingAsync(() -> teleport(), 1L, 1L));
/*    */   }
/*    */   
/*    */   public boolean check() {
/* 52 */     if (this.activemob.isDead() || !this.activemob.getEntity().isValid()) {
/* 53 */       terminate();
/* 54 */       return false;
/*    */     } 
/* 56 */     return true;
/*    */   }
/*    */   
/*    */   public void teleport() {
/* 60 */     AbstractLocation location = getLocation();
/*    */     
/* 62 */     if (location == null) {
/* 63 */       terminate();
/*    */     } else {
/* 65 */       this.hologram.teleport(getLocation());
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean terminate() {
/* 70 */     return this.components.terminate();
/*    */   }
/*    */   
/*    */   private AbstractLocation getLocation() {
/* 74 */     return this.activemob.getEntity().getEyeLocation().add(0.0D, this.yOffset, 0.0D);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\holograms\types\Nameplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */