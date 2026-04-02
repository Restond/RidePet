/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.tasks.Task;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.skills.mechanics.GeyserEffect;
/*     */ import java.util.List;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
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
/*     */ 
/*     */ class Animator
/*     */   implements Runnable
/*     */ {
/*     */   private Task task;
/*  73 */   private int tick = 0;
/*     */   private AbstractLocation start;
/*     */   private List<AbstractPlayer> nearby;
/*     */   
/*     */   public Animator(AbstractLocation start, List<AbstractPlayer> nearby) {
/*  78 */     this.start = start;
/*  79 */     this.nearby = nearby;
/*  80 */     this.task = Schedulers.sync().runRepeating(this, 0L, GeyserEffect.access$000(paramGeyserEffect));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/*  87 */       if (this.tick > GeyserEffect.access$100(GeyserEffect.this) * 2) {
/*  88 */         this.task.terminate();
/*  89 */       } else if (this.tick < GeyserEffect.access$100(GeyserEffect.this)) {
/*  90 */         Block block = BukkitAdapter.adapt(this.start.clone().add(0.0D, this.tick, 0.0D)).getBlock();
/*  91 */         if (block.getType() == Material.AIR) {
/*  92 */           for (AbstractPlayer p : this.nearby) {
/*  93 */             BukkitAdapter.adapt(p).sendBlockChange(block.getLocation(), GeyserEffect.access$200(GeyserEffect.this), (byte)0);
/*     */           }
/*     */         }
/*     */       } else {
/*     */         
/*  98 */         int n = GeyserEffect.access$100(GeyserEffect.this) - this.tick - GeyserEffect.access$100(GeyserEffect.this) - 1;
/*  99 */         Block block = BukkitAdapter.adapt(this.start.clone().add(0.0D, n, 0.0D)).getBlock();
/* 100 */         for (AbstractPlayer p : this.nearby) {
/* 101 */           BukkitAdapter.adapt(p).sendBlockChange(block.getLocation(), block.getType(), block.getData());
/*     */         }
/*     */       } 
/* 104 */       this.tick++;
/* 105 */     } catch (Exception ex) {
/* 106 */       if (ConfigManager.debugLevel > 0) {
/* 107 */         ex.printStackTrace();
/*     */       }
/* 109 */       this.task.terminate();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\GeyserEffect$Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */