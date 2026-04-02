/*    */ package lumine.xikage.mythicmobs.holograms;
/*    */ 
/*    */ import io.lumine.utils.logging.ConsoleColor;
/*    */ import io.lumine.utils.tasks.Scheduler;
/*    */ import io.lumine.utils.terminable.Terminable;
/*    */ import io.lumine.utils.terminable.TerminableRegistry;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import io.lumine.xikage.mythicmobs.holograms.HologramProvider;
/*    */ import io.lumine.xikage.mythicmobs.holograms.IHologram;
/*    */ import io.lumine.xikage.mythicmobs.holograms.types.CastBar;
/*    */ import io.lumine.xikage.mythicmobs.holograms.types.HealthBar;
/*    */ import io.lumine.xikage.mythicmobs.holograms.types.Nameplate;
/*    */ import io.lumine.xikage.mythicmobs.holograms.types.SpeechBubble;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.CastMechanic;
/*    */ 
/*    */ public class HologramManager implements Terminable {
/*    */   private final MythicMobs core;
/*    */   private final CompatibilityManager compat;
/*    */   private HologramProvider provider;
/* 25 */   private TerminableRegistry components = TerminableRegistry.create();
/*    */   
/*    */   public HologramManager(MythicMobs core, CompatibilityManager compat) {
/* 28 */     this.core = core;
/* 29 */     this.compat = compat;
/*    */     
/* 31 */     Scheduler.runSync(() -> initialize());
/*    */   }
/*    */   
/*    */   private void initialize() {
/* 35 */     if (this.compat.getHolograms().isPresent()) {
/* 36 */       MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + "Using Holograms plugin for holograms");
/* 37 */       this.provider = this.compat.getHolograms().get();
/* 38 */     } else if (this.compat.getHolographicDisplays().isPresent()) {
/* 39 */       MythicLogger.log("" + ConsoleColor.GREEN + ConsoleColor.CHECK_MARK + "Using HolographicDisplays plugin for holograms");
/* 40 */       this.provider = this.compat.getHolographicDisplays().get();
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 45 */       this.provider = null;
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean terminate() {
/* 52 */     this.components.terminate();
/* 53 */     return true;
/*    */   }
/*    */   
/*    */   public boolean isActive() {
/* 57 */     return (this.provider != null);
/*    */   }
/*    */   
/*    */   public IHologram createHologram(String name, AbstractLocation location) {
/* 61 */     return (this.provider == null) ? null : this.provider.createHologram(name, location, " ");
/*    */   }
/*    */   
/*    */   public IHologram createHologram(String name, AbstractLocation location, String text) {
/* 65 */     return (this.provider == null) ? null : this.provider.createHologram(name, location, text);
/*    */   }
/*    */   
/*    */   public Nameplate createNameplate(ActiveMob activeMob) {
/* 69 */     return (this.provider == null) ? null : new Nameplate(this, activeMob);
/*    */   }
/*    */   
/*    */   public HealthBar createHealthBar(ActiveMob activeMob) {
/* 73 */     return (this.provider == null) ? null : new HealthBar(this, activeMob);
/*    */   }
/*    */   
/*    */   public SpeechBubble createSpeechBubble(SkillCaster skillCaster) {
/* 77 */     return (this.provider == null) ? null : new SpeechBubble(this, skillCaster);
/*    */   }
/*    */   
/*    */   public CastBar createCastBar(CastMechanic.CastTracker castTracker, String castingText) {
/* 81 */     return (this.provider == null) ? null : new CastBar(this, castTracker, castingText);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\holograms\HologramManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */