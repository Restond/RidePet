/*    */ package saukiya.sxattribute.data;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import java.text.MessageFormat;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.boss.BarColor;
/*    */ import org.bukkit.boss.BarStyle;
/*    */ import org.bukkit.boss.BossBar;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class BossBarData {
/*    */   private BossBar bossBar;
/*    */   private LivingEntity entity;
/*    */   private double maxHealth;
/*    */   private String name;
/*    */   
/*    */   public BossBar getBossBar() {
/* 22 */     return this.bossBar;
/*    */   }
/*    */   public LivingEntity getEntity() {
/* 25 */     return this.entity;
/*    */   }
/*    */   public double getMaxHealth() {
/* 28 */     return this.maxHealth;
/*    */   }
/*    */   public String getName() {
/* 31 */     return this.name;
/*    */   }
/*    */   
/* 34 */   private int updateTick = Config.getConfig().getInt("Health.BossBar.DisplayTime");
/*    */   
/* 36 */   private Map<Player, Long> timeMap = new ConcurrentHashMap<>(); public Map<Player, Long> getTimeMap() { return this.timeMap; }
/*    */ 
/*    */   
/*    */   public BossBarData(LivingEntity entity, String name, double maxHealth, double progress) {
/* 40 */     this.entity = entity;
/* 41 */     this.name = name;
/* 42 */     this.maxHealth = maxHealth;
/* 43 */     this.bossBar = Bukkit.createBossBar(MessageFormat.format(Config.getConfig().getString("Health.BossBar.Format"), new Object[] { name, SXAttribute.getDf().format(progress * maxHealth), SXAttribute.getDf().format(maxHealth) }).replace("&", "§"), BarColor.GREEN, BarStyle.SEGMENTED_20, new org.bukkit.boss.BarFlag[0]);
/*    */   }
/*    */   
/*    */   public void updateTitle() {
/* 47 */     this.bossBar.setTitle(MessageFormat.format(Config.getConfig().getString("Health.BossBar.Format"), new Object[] { this.name, SXAttribute.getDf().format(this.bossBar.getProgress() * this.maxHealth), SXAttribute.getDf().format(this.maxHealth) }).replace("&", "§"));
/*    */   }
/*    */   
/*    */   public void setProgress(double progress) {
/* 51 */     this.bossBar.setProgress(progress);
/* 52 */     this.bossBar.setColor((progress > 0.66D) ? BarColor.GREEN : ((progress > 0.33D) ? BarColor.YELLOW : BarColor.RED));
/*    */   }
/*    */   
/*    */   public github.saukiya.sxattribute.data.BossBarData addPlayer(Player player) {
/* 56 */     this.bossBar.addPlayer(player);
/* 57 */     this.timeMap.put(player, Long.valueOf(System.currentTimeMillis() + (this.updateTick * 1000)));
/* 58 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\BossBarData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */