/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractGameMode;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitEntity;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.WeatherType;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class BukkitPlayer
/*     */   extends BukkitEntity implements AbstractPlayer {
/*     */   private boolean pre8 = false;
/*     */   
/*     */   public BukkitPlayer(Player p) {
/*  16 */     super((Entity)p);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInCreativeMode() {
/*  21 */     return getEntityAsPlayer().getGameMode().equals(GameMode.CREATIVE);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInSpectatorMode() {
/*  26 */     if (this.pre8 == true) return false; 
/*     */     try {
/*  28 */       return getEntityAsPlayer().getGameMode().equals(GameMode.SPECTATOR);
/*  29 */     } catch (NoSuchFieldError er) {
/*  30 */       this.pre8 = true;
/*  31 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessage(String message) {
/*  37 */     getEntityAsPlayer().sendMessage(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPermission(String perm) {
/*  42 */     return getEntityAsPlayer().hasPermission(perm);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getExperience() {
/*  47 */     return getEntityAsPlayer().getExp();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExperience(float exp) {
/*  52 */     getEntityAsPlayer().setExp(exp);
/*     */   }
/*     */ 
/*     */   
/*     */   public void hidePlayer(AbstractPlayer target) {
/*  57 */     getEntityAsPlayer().hidePlayer((Player)target.getBukkitEntity());
/*     */   }
/*     */ 
/*     */   
/*     */   public void showPlayer(AbstractPlayer target) {
/*  62 */     getEntityAsPlayer().showPlayer((Player)target.getBukkitEntity());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSee(AbstractPlayer target) {
/*  67 */     return getEntityAsPlayer().canSee((Player)target.getBukkitEntity());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnline() {
/*  72 */     return getEntityAsPlayer().isOnline();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLevel() {
/*  77 */     return getEntityAsPlayer().getLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLevel(int level) {
/*  82 */     getEntityAsPlayer().setLevel(level);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHealthScale(double scale) {
/*  87 */     getEntityAsPlayer().setHealthScale(scale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHealthScaled(boolean scale) {
/*  92 */     getEntityAsPlayer().setHealthScaled(scale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPersonalTime(long time, boolean relative) {
/*  97 */     getEntityAsPlayer().setPlayerTime(time, relative);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetPersonalTime() {
/* 102 */     getEntityAsPlayer().resetPlayerTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPersonalWeather(String type) {
/* 107 */     getEntityAsPlayer().setPlayerWeather(WeatherType.valueOf(type.toUpperCase()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetPersonalWeather() {
/* 112 */     getEntityAsPlayer().resetPlayerWeather();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllowFlight(boolean b) {
/* 117 */     getEntityAsPlayer().setAllowFlight(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getAllowFlight() {
/* 122 */     return getEntityAsPlayer().getAllowFlight();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlying(boolean b) {
/* 127 */     getEntityAsPlayer().setFlying(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlyingSpeed(float f) {
/* 132 */     getEntityAsPlayer().setFlySpeed(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWalkSpeed(float f) {
/* 137 */     getEntityAsPlayer().setWalkSpeed(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFoodLevel() {
/* 142 */     return getEntityAsPlayer().getFoodLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFoodLevel(int amount) {
/* 147 */     getEntityAsPlayer().setFoodLevel(amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFoodSaturation() {
/* 152 */     return getEntityAsPlayer().getSaturation();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFoodSaturation(float amount) {
/* 157 */     getEntityAsPlayer().setSaturation(amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGameMode(AbstractGameMode mode) {
/* 162 */     switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$adapters$AbstractGameMode[mode.ordinal()]) {
/*     */       case 1:
/* 164 */         getEntityAsPlayer().setGameMode(GameMode.ADVENTURE);
/*     */         break;
/*     */       case 2:
/* 167 */         getEntityAsPlayer().setGameMode(GameMode.CREATIVE);
/*     */         break;
/*     */       case 3:
/* 170 */         getEntityAsPlayer().setGameMode(GameMode.SPECTATOR);
/*     */         break;
/*     */       case 4:
/* 173 */         getEntityAsPlayer().setGameMode(GameMode.SURVIVAL);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */