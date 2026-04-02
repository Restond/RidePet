/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractBossBar;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.boss.BarColor;
/*     */ import org.bukkit.boss.BarFlag;
/*     */ import org.bukkit.boss.BarStyle;
/*     */ import org.bukkit.boss.BossBar;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class BukkitBossBar implements AbstractBossBar {
/*     */   BossBar bar;
/*     */   
/*     */   public BukkitBossBar(String title, AbstractBossBar.BarColor aColor, AbstractBossBar.BarStyle aStyle) {
/*  19 */     BarColor color = BarColor.valueOf(aColor.name());
/*  20 */     BarStyle style = BarStyle.valueOf(aStyle.name());
/*     */     
/*  22 */     this.bar = Bukkit.getServer().createBossBar(title, color, style, new BarFlag[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/*  27 */     return this.bar.getTitle();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/*  32 */     this.bar.setTitle(title);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractBossBar.BarColor getColor() {
/*  38 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(String color) {
/*  43 */     this.bar.setColor(BarColor.valueOf(color));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractBossBar.BarStyle getStyle() {
/*  49 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStyle(String style) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFlag(String flag) {
/*  59 */     this.bar.removeFlag(BarFlag.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFlag(String flag) {
/*  64 */     this.bar.addFlag(BarFlag.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFlag(String flag) {
/*  69 */     return this.bar.hasFlag(BarFlag.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProgress(double progress) {
/*  74 */     if (progress > 1.0D) { progress = 1.0D; }
/*  75 */     else if (progress < 0.0D) { progress = 0.0D; }
/*     */     
/*  77 */     this.bar.setProgress(progress);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getProgress() {
/*  82 */     return this.bar.getProgress();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPlayer(AbstractPlayer player) {
/*  87 */     this.bar.addPlayer((Player)player.getBukkitEntity());
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePlayer(AbstractPlayer player) {
/*  92 */     this.bar.removePlayer((Player)player.getBukkitEntity());
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/*  97 */     this.bar.removeAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<AbstractPlayer> getPlayers() {
/* 102 */     List<AbstractPlayer> list = new ArrayList<>();
/*     */     
/* 104 */     this.bar.getPlayers().stream().forEach(player -> paramList.add(BukkitAdapter.adapt(player)));
/* 105 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 110 */     this.bar.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 115 */     return this.bar.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreateFog(boolean b) {
/* 120 */     if (b) {
/* 121 */       this.bar.addFlag(BarFlag.CREATE_FOG);
/*     */     } else {
/* 123 */       this.bar.removeFlag(BarFlag.CREATE_FOG);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDarkenSky(boolean b) {
/* 129 */     if (b) {
/* 130 */       this.bar.addFlag(BarFlag.DARKEN_SKY);
/*     */     } else {
/* 132 */       this.bar.removeFlag(BarFlag.DARKEN_SKY);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayBossMusic(boolean b) {
/* 138 */     if (b) {
/* 139 */       this.bar.addFlag(BarFlag.PLAY_BOSS_MUSIC);
/*     */     } else {
/* 141 */       this.bar.removeFlag(BarFlag.PLAY_BOSS_MUSIC);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean terminate() {
/* 147 */     setVisible(false);
/* 148 */     removeAll();
/* 149 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitBossBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */