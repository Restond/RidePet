/*     */ package lumine.xikage.mythicmobs.holograms.types;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.chat.ColorString;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import io.lumine.utils.terminable.Terminable;
/*     */ import io.lumine.utils.terminable.TerminableRegistry;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.holograms.HologramManager;
/*     */ import io.lumine.xikage.mythicmobs.holograms.IHologram;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.CastMechanic;
/*     */ import org.bukkit.ChatColor;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CastBar
/*     */   implements Terminable
/*     */ {
/*     */   private final CastMechanic.CastTracker castTracker;
/*     */   private final SkillCaster skillCaster;
/*     */   private final HologramManager manager;
/*     */   private Task timer;
/*     */   private IHologram nameHologram;
/*     */   private IHologram castHologram;
/*     */   private double yOffset;
/*  28 */   private double castPercent = 0.0D;
/*     */   
/*  30 */   private TerminableRegistry components = TerminableRegistry.create();
/*     */   
/*     */   public CastBar(HologramManager manager, CastMechanic.CastTracker castTracker, String castText) {
/*  33 */     this.manager = manager;
/*     */     
/*  35 */     this.castTracker = castTracker;
/*  36 */     this.skillCaster = castTracker.getSkillMetadata().getCaster();
/*     */     
/*  38 */     if (this.skillCaster instanceof ActiveMob) {
/*  39 */       ActiveMob am = (ActiveMob)this.skillCaster;
/*  40 */       this.yOffset = 1.0D + am.getType().getConfig().getDouble("HealthBar.Offset", am.getType().isFakePlayer() ? 0.30000001192092896D : am.getType().getMythicEntity().getHealthbarOffset());
/*     */     } else {
/*  42 */       this.yOffset = 1.3D;
/*     */     } 
/*     */     
/*  45 */     this.nameHologram = manager.createHologram("#TempCastBarName" + this.skillCaster.getEntity().getUniqueId().toString(), getLocation(), castText);
/*  46 */     this.castHologram = manager.createHologram("#TempCastBarCast" + this.skillCaster.getEntity().getUniqueId().toString(), getLocation(), getCastBar());
/*     */     
/*  48 */     this.components.accept(this.nameHologram);
/*  49 */     this.components.accept(this.castHologram);
/*     */     
/*  51 */     this.timer = Schedulers.async().runRepeating(() -> teleport(), 1L, 1L);
/*  52 */     this.components.accept(this.timer);
/*     */   }
/*     */   
/*     */   public void teleport() {
/*  56 */     AbstractLocation location = getLocation();
/*     */     
/*  58 */     if (location == null || this.castTracker.isHasEnded()) {
/*  59 */       terminate();
/*     */     } else {
/*  61 */       this.castHologram.teleport(getLocation());
/*  62 */       this.nameHologram.teleport(getLocation().add(0.0D, 0.25D, 0.0D));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setInterrupted() {
/*  67 */     this.timer.terminate();
/*  68 */     this.castHologram.setText(ColorString.get("&c[Interrupted]"));
/*     */   }
/*     */   
/*     */   public void setFinished() {
/*  72 */     this.timer.terminate();
/*  73 */     this.castHologram.setText(ColorString.get("&a[Casted!]"));
/*     */   }
/*     */   
/*     */   public boolean terminate() {
/*  77 */     Schedulers.sync().runLater(() -> this.components.terminate(), 10L);
/*     */ 
/*     */     
/*  80 */     return true;
/*     */   }
/*     */   
/*     */   private AbstractLocation getLocation() {
/*  84 */     return this.skillCaster.getEntity().getEyeLocation().add(0.0D, this.yOffset, 0.0D);
/*     */   }
/*     */   
/*     */   public void setCastPercent(double percent) {
/*  88 */     this.castPercent = percent;
/*  89 */     this.castHologram.setText(getCastBar());
/*     */   }
/*     */   
/*     */   private String getCastBar() {
/*  93 */     int length = 30;
/*  94 */     int gray = (int)Math.floor(this.castPercent * 30.0D);
/*     */     
/*  96 */     StringBuilder line = new StringBuilder();
/*     */     
/*  98 */     boolean passed = false;
/*  99 */     for (int i = 30; i > 0; i--) {
/* 100 */       if (!passed && i < gray) {
/* 101 */         passed = true;
/*     */       }
/* 103 */       line.append(passed ? ChatColor.DARK_GRAY : ChatColor.AQUA);
/* 104 */       line.append("|");
/*     */     } 
/*     */     
/* 107 */     return line.toString();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\holograms\types\CastBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */