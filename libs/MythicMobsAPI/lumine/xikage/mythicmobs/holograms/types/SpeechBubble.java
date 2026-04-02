/*     */ package lumine.xikage.mythicmobs.holograms.types;
/*     */ 
/*     */ import io.lumine.utils.events.Events;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.utils.terminable.TerminableRegistry;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
/*     */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDespawnEvent;
/*     */ import io.lumine.xikage.mythicmobs.holograms.HologramManager;
/*     */ import io.lumine.xikage.mythicmobs.holograms.IHologram;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpeechBubble
/*     */   implements Terminable
/*     */ {
/*     */   private final SkillCaster activemob;
/*     */   private final HologramManager manager;
/*     */   private IHologram hologram;
/*  27 */   private String text = "";
/*  28 */   private String linePrefix = "";
/*  29 */   private int lineLength = 24; public int getLineLength() { return this.lineLength; } public void setLineLength(int lineLength) { this.lineLength = lineLength; }
/*  30 */    private double yOffset = 0.4D;
/*  31 */   private double currentOffset = this.yOffset;
/*     */   
/*  33 */   private TerminableRegistry components = TerminableRegistry.create();
/*     */   
/*     */   public SpeechBubble(HologramManager manager, SkillCaster am) {
/*  36 */     this.manager = manager;
/*     */     
/*  38 */     this.activemob = am;
/*     */     
/*  40 */     if (am instanceof ActiveMob) {
/*  41 */       this.yOffset = ((ActiveMob)am).getType().getConfig().getDouble("Speech.Offset", this.yOffset);
/*     */     }
/*     */     
/*  44 */     this.hologram = manager.createHologram("#TempSpeechBubble" + this.activemob.getEntity().getUniqueId().toString(), getLocation(), " ");
/*     */     
/*  46 */     this.components.accept(this.hologram);
/*  47 */     this.components.accept(Events.subscribe(MythicMobDeathEvent.class).handler(event -> {
/*     */             UUID uuid = event.getEntity().getUniqueId();
/*     */             if (this.activemob.getEntity().getUniqueId().equals(uuid)) {
/*     */               terminate();
/*     */             }
/*     */           }));
/*  53 */     this.components.accept(Events.subscribe(MythicMobDespawnEvent.class).handler(event -> {
/*     */             UUID uuid = event.getEntity().getUniqueId();
/*     */             if (this.activemob.getEntity().getUniqueId().equals(uuid)) {
/*     */               terminate();
/*     */             }
/*     */           }));
/*  59 */     this.components.accept(Scheduler.runTaskRepeatingAsync(() -> check(), 1L, 10L));
/*  60 */     this.components.accept(Scheduler.runTaskRepeatingAsync(() -> teleport(), 1L, 1L));
/*     */   }
/*     */   
/*     */   public boolean check() {
/*  64 */     if (this.activemob.getEntity().isDead() || !this.activemob.getEntity().isValid()) {
/*  65 */       terminate();
/*  66 */       return false;
/*     */     } 
/*  68 */     return true;
/*     */   }
/*     */   
/*     */   public void teleport() {
/*  72 */     AbstractLocation location = getLocation();
/*     */     
/*  74 */     if (location == null) {
/*  75 */       terminate();
/*     */     } else {
/*  77 */       this.hologram.teleport(getLocation());
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean terminate() {
/*  82 */     return this.components.terminate();
/*     */   }
/*     */   
/*     */   private AbstractLocation getLocation() {
/*  86 */     return this.activemob.getEntity().getEyeLocation().add(0.0D, this.currentOffset, 0.0D);
/*     */   }
/*     */   
/*     */   public void setText(String text) {
/*  90 */     if (text.length() > this.lineLength) {
/*  91 */       String[] lines = MythicUtil.wrapString(text, this.lineLength);
/*  92 */       this.currentOffset = this.yOffset + lines.length * 0.25D;
/*  93 */       this.hologram.setText(lines, this.linePrefix);
/*     */     } else {
/*  95 */       this.currentOffset = this.yOffset;
/*  96 */       this.hologram.setText(this.linePrefix + text);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLinePrefix(String linePrefix) {
/* 101 */     this.linePrefix = linePrefix;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\holograms\types\SpeechBubble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */