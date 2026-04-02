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
/*     */ import java.util.UUID;
/*     */ import org.bukkit.ChatColor;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HealthBar
/*     */   implements Terminable
/*     */ {
/*     */   private final ActiveMob activemob;
/*     */   private final HologramManager manager;
/*     */   private IHologram hologram;
/*     */   private double yOffset;
/*  25 */   private TerminableRegistry components = TerminableRegistry.create();
/*     */   
/*     */   public HealthBar(HologramManager manager, ActiveMob am) {
/*  28 */     this.manager = manager;
/*     */     
/*  30 */     this.activemob = am;
/*  31 */     this.yOffset = am.getType().getConfig().getDouble("HealthBar.Offset", am.getType().isFakePlayer() ? 0.30000001192092896D : am.getType().getMythicEntity().getHealthbarOffset());
/*     */     
/*  33 */     this.hologram = manager.createHologram("#TempHealthBar" + this.activemob.getUniqueId().toString(), getLocation(), getLine());
/*     */     
/*  35 */     this.components.accept(this.hologram);
/*  36 */     this.components.accept(Events.subscribe(MythicMobDeathEvent.class).handler(event -> {
/*     */             UUID uuid = event.getEntity().getUniqueId();
/*     */             if (this.activemob.getUniqueId().equals(uuid)) {
/*     */               terminate();
/*     */             }
/*     */           }));
/*  42 */     this.components.accept(Events.subscribe(MythicMobDespawnEvent.class).handler(event -> {
/*     */             UUID uuid = event.getEntity().getUniqueId();
/*     */             if (this.activemob.getUniqueId().equals(uuid)) {
/*     */               terminate();
/*     */             }
/*     */           }));
/*  48 */     this.components.accept(Scheduler.runTaskRepeatingAsync(() -> check(), 1L, 10L));
/*  49 */     this.components.accept(Scheduler.runTaskRepeatingAsync(() -> teleport(), 1L, 1L));
/*     */   }
/*     */   
/*     */   public boolean check() {
/*  53 */     if (this.activemob.isDead() || !this.activemob.getEntity().isValid() || this.activemob.getEntity().getHealth() == this.activemob.getEntity().getMaxHealth()) {
/*  54 */       terminate();
/*  55 */       return false;
/*     */     } 
/*  57 */     return true;
/*     */   }
/*     */   
/*     */   public void teleport() {
/*  61 */     AbstractLocation location = getLocation();
/*     */     
/*  63 */     if (location == null) {
/*  64 */       terminate();
/*     */     } else {
/*  66 */       this.hologram.teleport(getLocation());
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean terminate() {
/*  71 */     return this.components.terminate();
/*     */   }
/*     */   
/*     */   private AbstractLocation getLocation() {
/*  75 */     return this.activemob.getEntity().getEyeLocation().add(0.0D, this.yOffset, 0.0D);
/*     */   }
/*     */   
/*     */   public void update() {
/*  79 */     this.hologram.setText(getLine());
/*     */   }
/*     */   
/*     */   private String getLine() {
/*  83 */     int hp = (int)this.activemob.getEntity().getHealth();
/*     */     
/*  85 */     double percent = this.activemob.getEntity().getHealth() / this.activemob.getEntity().getMaxHealth();
/*  86 */     String sHP = String.valueOf(hp);
/*  87 */     int hplength = sHP.length();
/*  88 */     int length = 10 + hplength;
/*  89 */     int gray = (int)Math.floor(percent * length);
/*     */ 
/*     */ 
/*     */     
/*  93 */     StringBuilder line = (new StringBuilder()).append(ChatColor.DARK_RED).append("[");
/*     */     
/*  95 */     boolean passed = false;
/*  96 */     for (int i = 0; i < length; i++) {
/*  97 */       if (!passed && i > gray) {
/*  98 */         passed = true;
/*     */       }
/* 100 */       if (i < 5) {
/* 101 */         line.append(passed ? ChatColor.DARK_GRAY : ChatColor.RED);
/* 102 */         line.append("|");
/* 103 */       } else if (i < 5 + hplength) {
/* 104 */         line.append(passed ? ChatColor.GRAY : ChatColor.DARK_RED);
/*     */         try {
/* 106 */           line.append(sHP.substring(i - 5, i - 4));
/* 107 */         } catch (Exception exception) {}
/*     */       } else {
/* 109 */         if (i == hplength + 2 && !passed) {
/* 110 */           line.append(ChatColor.RED);
/*     */         }
/* 112 */         line.append(passed ? ChatColor.DARK_GRAY : ChatColor.RED);
/* 113 */         line.append("|");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     return line.append(ChatColor.DARK_RED).append("]").toString();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\holograms\types\HealthBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */