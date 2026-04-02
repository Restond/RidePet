/*     */ package saukiya.sxattribute.listener;
/*     */ 
/*     */ import com.gmail.filoghost.holographicdisplays.api.Hologram;
/*     */ import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.BossBarData;
/*     */ import github.saukiya.sxattribute.data.NameData;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OnHealthChangeDisplayListener
/*     */   implements Listener
/*     */ {
/*  36 */   private final List<BossBarData> bossList = new ArrayList<>(); public List<BossBarData> getBossList() { return this.bossList; }
/*     */ 
/*     */   
/*  39 */   private final List<NameData> nameList = new ArrayList<>(); public List<NameData> getNameList() { return this.nameList; }
/*     */ 
/*     */   
/*     */   private final SXAttribute plugin;
/*     */   
/*     */   public OnHealthChangeDisplayListener(SXAttribute plugin) {
/*  45 */     this.plugin = plugin;
/*  46 */     (new Object(this))
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
/*  87 */       .runTaskTimer((Plugin)plugin, 20L, 20L);
/*     */   }
/*     */   
/*     */   public static double getMaxHealth(LivingEntity entity) {
/*  91 */     return (SXAttribute.getVersionSplit()[1] >= 9) ? entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() : entity.getMaxHealth();
/*     */   }
/*     */   
/*     */   public String getEntityName(LivingEntity entity) {
/*  95 */     if (entity instanceof Player) {
/*  96 */       return ((Player)entity).getDisplayName();
/*     */     }
/*  98 */     String entityName = entity.getName();
/*  99 */     for (NameData nameData : this.nameList) {
/* 100 */       if (nameData.getEntity().equals(entity)) {
/* 101 */         if (nameData.getName() != null) {
/* 102 */           entityName = Message.replace(nameData.getName());
/*     */         } else {
/* 104 */           String tempName = entityName;
/* 105 */           entity.setCustomName(null);
/* 106 */           entityName = Message.replace(entity.getName());
/* 107 */           entity.setCustomName(tempName);
/*     */         } 
/* 109 */         return entityName;
/*     */       } 
/*     */     } 
/* 112 */     return Message.replace(entityName);
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGHEST)
/*     */   void OnEntityDamageEvent(EntityDamageEvent event) {
/* 117 */     if (event.isCancelled() || event.getFinalDamage() == 0.0D)
/* 118 */       return;  if (!(event.getEntity() instanceof LivingEntity) || event.getEntity() instanceof org.bukkit.entity.ArmorStand) {
/*     */       return;
/*     */     }
/* 121 */     LivingEntity entity = (LivingEntity)event.getEntity();
/* 122 */     LivingEntity damager = null;
/* 123 */     String name = getEntityName(entity);
/*     */     
/* 125 */     double health = entity.getHealth() - event.getFinalDamage();
/* 126 */     if (health < 0.0D) health = 0.0D; 
/* 127 */     Double maxHealth = Double.valueOf(getMaxHealth(entity));
/* 128 */     if (health > maxHealth.doubleValue()) health = maxHealth.doubleValue(); 
/* 129 */     double progress = health / maxHealth.doubleValue();
/* 130 */     BossBarData bossBarData = null;
/* 131 */     for (BossBarData barData : this.bossList) {
/* 132 */       if (barData.getEntity().equals(entity)) {
/* 133 */         bossBarData = barData;
/* 134 */         bossBarData.setProgress(progress);
/* 135 */         bossBarData.updateTitle();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 140 */     if (event instanceof EntityDamageByEntityEvent) {
/* 141 */       EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent)event;
/* 142 */       if (event2.getDamager() instanceof LivingEntity) {
/* 143 */         damager = (LivingEntity)event2.getDamager();
/* 144 */       } else if (event2.getDamager() instanceof Projectile) {
/* 145 */         Projectile projectile = (Projectile)event2.getDamager();
/* 146 */         if (projectile.getShooter() instanceof LivingEntity) {
/* 147 */           damager = (LivingEntity)projectile.getShooter();
/*     */         }
/*     */       } 
/* 150 */       if (damager != null)
/*     */       {
/* 152 */         if (Config.isHealthBossBar() && damager instanceof Player && SXAttribute.getVersionSplit()[1] >= 9 && !Config.getBossBarBlackCauseList().contains(event.getCause().name())) {
/* 153 */           if (bossBarData == null) {
/* 154 */             bossBarData = new BossBarData(entity, name, maxHealth.doubleValue(), progress);
/* 155 */             bossBarData.setProgress(entity.getHealth() / maxHealth.doubleValue());
/* 156 */             this.bossList.add(bossBarData);
/*     */           } 
/* 158 */           bossBarData.addPlayer((Player)damager);
/* 159 */           bossBarData.setProgress(progress);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 164 */     if (damager == null && Config.isHolographic() && Config.isHolographicHealthTake() && SXAttribute.isHolographic() && !Config.getHolographicBlackList().contains(event.getCause().name())) {
/* 165 */       Location loc = entity.getEyeLocation().clone().add(0.0D, 0.6D - SXAttribute.getRandom().nextDouble() / 2.0D, 0.0D);
/* 166 */       loc.setYaw(entity.getLocation().getYaw() - 90.0F);
/* 167 */       loc.add(loc.getDirection().multiply(0.8D));
/* 168 */       Hologram hologram = HologramsAPI.createHologram((Plugin)this.plugin, loc);
/* 169 */       hologram.appendTextLine(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__TAKE, new Object[] { Double.valueOf(event.getFinalDamage()) }));
/* 170 */       this.plugin.getOnDamageListener().getHologramsList().add(hologram);
/* 171 */       (new Object(this, hologram))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 177 */         .runTaskLater((Plugin)this.plugin, Config.getConfig().getInt("Holographic.DisplayTime"));
/*     */     } 
/* 179 */     if (Config.isHealthNameVisible()) {
/* 180 */       NameData nameData = null;
/* 181 */       for (NameData data : this.nameList) {
/* 182 */         if (data.getEntity().equals(entity)) {
/* 183 */           nameData = data;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 188 */       if (health == 0.0D) {
/* 189 */         for (int i = this.nameList.size() - 1; i >= 0; i--) {
/* 190 */           NameData data = this.nameList.get(i);
/* 191 */           if (data.getEntity().equals(entity)) {
/* 192 */             entity.setCustomName(data.getName());
/* 193 */             entity.setCustomNameVisible(data.isVisible());
/* 194 */             this.nameList.remove(i);
/*     */           } 
/* 196 */           if (data.getEntity().equals(damager)) {
/* 197 */             damager.setCustomName(data.getName());
/* 198 */             damager.setCustomNameVisible(data.isVisible());
/* 199 */             this.nameList.remove(i);
/*     */           } 
/*     */         } 
/* 202 */       } else if ((damager instanceof Player || nameData != null) && !(entity instanceof Player)) {
/* 203 */         StringBuilder healthName = new StringBuilder(Config.getConfig().getString("Health.NameVisible.Prefix"));
/* 204 */         int maxSize = Config.getConfig().getInt("Health.NameVisible.Size");
/* 205 */         int size = (int)(maxSize * progress);
/* 206 */         String current = Config.getConfig().getString("Health.NameVisible.Current");
/* 207 */         String loss = Config.getConfig().getString("Health.NameVisible.Loss"); int i;
/* 208 */         for (i = 0; i < size; i++) {
/* 209 */           healthName.append(current);
/*     */         }
/* 211 */         for (i = size; i < maxSize; i++) {
/* 212 */           healthName.append(loss);
/*     */         }
/* 214 */         healthName = new StringBuilder(healthName.append(Config.getConfig().getString("Health.NameVisible.Suffix")).toString().replace("&", "§"));
/*     */         
/* 216 */         if (nameData == null) {
/* 217 */           nameData = new NameData(entity, entity.getCustomName(), entity.isCustomNameVisible());
/* 218 */           nameData.updateTick();
/* 219 */           this.nameList.add(nameData);
/* 220 */         } else if (damager instanceof Player) {
/* 221 */           nameData.updateTick();
/*     */         } 
/* 223 */         entity.setCustomName(MessageFormat.format(healthName.toString(), new Object[] { SXAttribute.getDf().format(health), SXAttribute.getDf().format(maxHealth) }));
/* 224 */         entity.setCustomNameVisible(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR)
/*     */   void OnEntityRegainHealthEvent(EntityRegainHealthEvent event) {
/* 231 */     if (event.isCancelled() || event.getAmount() == 0.0D)
/* 232 */       return;  if (!(event.getEntity() instanceof LivingEntity) || event.getEntity() instanceof org.bukkit.entity.ArmorStand) {
/*     */       return;
/*     */     }
/* 235 */     LivingEntity entity = (LivingEntity)event.getEntity();
/* 236 */     double health = entity.getHealth() + event.getAmount();
/* 237 */     Double maxHealth = Double.valueOf(getMaxHealth(entity));
/* 238 */     double progress = (health > maxHealth.doubleValue()) ? 1.0D : ((health < 0.0D) ? 0.0D : (health / maxHealth.doubleValue()));
/*     */     
/* 240 */     for (BossBarData bossBarData : this.bossList) {
/* 241 */       if (bossBarData.getEntity().equals(entity)) {
/* 242 */         if (progress == 1.0D) {
/* 243 */           bossBarData.getBossBar().removeAll(); break;
/*     */         } 
/* 245 */         bossBarData.setProgress(progress);
/* 246 */         bossBarData.updateTitle();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     
/* 252 */     if (Config.isHolographic() && Config.isHolographicHealthTake() && SXAttribute.isHolographic()) {
/* 253 */       Location loc = entity.getEyeLocation().clone().add(0.0D, 0.6D - SXAttribute.getRandom().nextDouble() * 1.5D, 0.0D);
/* 254 */       loc.setYaw(entity.getLocation().getYaw() + 90.0F);
/* 255 */       loc.add(loc.getDirection().multiply(0.8D));
/* 256 */       Hologram hologram = HologramsAPI.createHologram((Plugin)this.plugin, loc);
/* 257 */       hologram.appendTextLine(Message.getMsg(Message.PLAYER__HOLOGRAPHIC__HEALTH, new Object[] { SXAttribute.getDf().format(event.getAmount()) }));
/* 258 */       this.plugin.getOnDamageListener().getHologramsList().add(hologram);
/* 259 */       (new Object(this, hologram))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 265 */         .runTaskLater((Plugin)this.plugin, Config.getConfig().getInt("Holographic.DisplayTime"));
/*     */     } 
/* 267 */     for (NameData nameData : this.nameList) {
/* 268 */       if (nameData.getEntity().equals(entity)) {
/* 269 */         if (progress == 1.0D) {
/* 270 */           nameData.getEntity().setCustomName(nameData.getName());
/* 271 */           nameData.getEntity().setCustomNameVisible(nameData.isVisible());
/* 272 */           this.nameList.remove(nameData); break;
/*     */         } 
/* 274 */         StringBuilder healthName = new StringBuilder(Config.getConfig().getString("Health.NameVisible.Prefix"));
/* 275 */         int maxSize = Config.getConfig().getInt("Health.NameVisible.Size");
/* 276 */         int size = (int)(maxSize * progress);
/* 277 */         String current = Config.getConfig().getString("Health.NameVisible.Current");
/* 278 */         String loss = Config.getConfig().getString("Health.NameVisible.Loss"); int i;
/* 279 */         for (i = 0; i < size; i++) {
/* 280 */           healthName.append(current);
/*     */         }
/* 282 */         for (i = size; i < maxSize; i++) {
/* 283 */           healthName.append(loss);
/*     */         }
/* 285 */         healthName = new StringBuilder((healthName + Config.getConfig().getString("Health.NameVisible.Suffix")).replace("&", "§"));
/* 286 */         entity.setCustomName(MessageFormat.format(healthName.toString(), new Object[] { SXAttribute.getDf().format(health), SXAttribute.getDf().format(maxHealth) }));
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\listener\OnHealthChangeDisplayListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */