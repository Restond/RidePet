/*     */ package saukiya.sxattribute.listener;
/*     */ 
/*     */ import com.gmail.filoghost.holographicdisplays.api.Hologram;
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeData;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*     */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*     */ import github.saukiya.sxattribute.data.eventdata.sub.DamageEventData;
/*     */ import github.saukiya.sxattribute.event.SXDamageEvent;
/*     */ import github.saukiya.sxattribute.util.Config;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.event.player.PlayerItemDamageEvent;
/*     */ import org.bukkit.inventory.EntityEquipment;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class OnDamageListener implements Listener {
/*     */   public List<Hologram> getHologramsList() {
/*  38 */     return this.hologramsList;
/*     */   }
/*     */   
/*     */   private final List<Hologram> hologramsList;
/*     */   
/*     */   public OnDamageListener(SXAttribute plugin) {
/*  44 */     this.plugin = plugin;
/*  45 */     if (SXAttribute.isHolographic()) {
/*  46 */       this.hologramsList = new ArrayList<>();
/*     */     } else {
/*  48 */       this.hologramsList = null;
/*     */     } 
/*     */   }
/*     */   private final SXAttribute plugin;
/*     */   @EventHandler
/*     */   void onProjectileHitEvent(EntityShootBowEvent event) {
/*  54 */     if (event.isCancelled())
/*  55 */       return;  Entity projectile = event.getProjectile();
/*  56 */     LivingEntity entity = event.getEntity();
/*  57 */     if (entity instanceof Player) {
/*  58 */       this.plugin.getAttributeManager().setProjectileData(projectile.getUniqueId(), this.plugin.getAttributeManager().getEntityData(entity, new SXAttributeData[0]));
/*  59 */       ItemStack item = event.getBow();
/*  60 */       if (item != null && SubCondition.getUnbreakable(item.getItemMeta())) {
/*  61 */         Bukkit.getPluginManager().callEvent((Event)new PlayerItemDamageEvent((Player)entity, item, 1));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGHEST)
/*     */   void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
/*  69 */     if (event.isCancelled() || event.getCause().equals(EntityDamageEvent.DamageCause.CUSTOM)) {
/*     */       return;
/*     */     }
/*  72 */     LivingEntity entity = (event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof org.bukkit.entity.ArmorStand)) ? (LivingEntity)event.getEntity() : null;
/*  73 */     LivingEntity damager = null;
/*     */     
/*  75 */     SXAttributeData damagerData = null;
/*     */     
/*  77 */     if (event.getDamager() instanceof Projectile && ((Projectile)event.getDamager()).getShooter() instanceof LivingEntity) {
/*  78 */       damager = (LivingEntity)((Projectile)event.getDamager()).getShooter();
/*  79 */       damagerData = this.plugin.getAttributeManager().getProjectileData(event.getDamager().getUniqueId());
/*  80 */     } else if (event.getDamager() instanceof LivingEntity) {
/*  81 */       damager = (LivingEntity)event.getDamager();
/*     */     } 
/*     */     
/*  84 */     if (entity == null || damager == null || (!Config.isDamageCalculationToEVE() && !(entity instanceof Player) && !(damager instanceof Player))) {
/*     */       return;
/*     */     }
/*     */     
/*  88 */     SXAttributeData entityData = this.plugin.getAttributeManager().getEntityData(entity, new SXAttributeData[0]);
/*  89 */     damagerData = (damagerData != null) ? damagerData : this.plugin.getAttributeManager().getEntityData(damager, new SXAttributeData[0]);
/*     */     
/*  91 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
/*  92 */       EntityEquipment eq = damager.getEquipment();
/*  93 */       ItemStack mainHand = eq.getItemInMainHand();
/*  94 */       if (mainHand != null) {
/*     */         
/*  96 */         if (Material.BOW.equals(mainHand.getType())) {
/*  97 */           event.setDamage(1.0D);
/*  98 */           damagerData = this.plugin.getAttributeManager().getEntityData(damager, new SXAttributeData[] { null });
/*     */         } 
/* 100 */         if (!Material.AIR.equals(mainHand.getType()) && mainHand.getItemMeta().hasLore() && 
/* 101 */           damager instanceof Player && !((HumanEntity)damager).getGameMode().equals(GameMode.CREATIVE) && (
/* 102 */           mainHand.getType().getMaxDurability() == 0 || SubCondition.getUnbreakable(mainHand.getItemMeta()))) {
/* 103 */           Bukkit.getPluginManager().callEvent((Event)new PlayerItemDamageEvent((Player)damager, mainHand, 1));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 110 */     String entityName = this.plugin.getOnHealthChangeDisplayListener().getEntityName(entity);
/* 111 */     String damagerName = this.plugin.getOnHealthChangeDisplayListener().getEntityName(damager);
/*     */     
/* 113 */     DamageEventData damageEventData = new DamageEventData(entity, damager, entityName, damagerName, entityData, damagerData, event);
/*     */ 
/*     */ 
/*     */     
/* 117 */     Iterator<SubAttribute> damagerIterator = damagerData.getAttributeMap().values().iterator();
/* 118 */     Iterator<SubAttribute> entityIterator = entityData.getAttributeMap().values().iterator();
/*     */     
/* 120 */     while (damagerIterator.hasNext() && entityIterator.hasNext()) {
/* 121 */       SubAttribute damageAttribute = damagerIterator.next();
/* 122 */       SubAttribute entityAttribute = entityIterator.next();
/* 123 */       if (damageAttribute.containsType(SXAttributeType.DAMAGE) && Arrays.stream(damageAttribute.getAttributes()).anyMatch(d -> (d != 0.0D))) {
/* 124 */         damageAttribute.eventMethod((EventData)damageEventData);
/* 125 */       } else if (entityAttribute.containsType(SXAttributeType.DEFENCE) && Arrays.stream(entityAttribute.getAttributes()).anyMatch(d -> (d != 0.0D))) {
/* 126 */         entityAttribute.eventMethod((EventData)damageEventData);
/*     */       } 
/* 128 */       if (damageEventData.isCancelled() || damageEventData.getDamage() <= 0.0D) {
/* 129 */         event.setCancelled(damageEventData.isCancelled());
/* 130 */         damageEventData.setDamage(0.1D);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 135 */     event.setDamage(damageEventData.getDamage());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     Bukkit.getPluginManager().callEvent((Event)new SXDamageEvent(damageEventData));
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\listener\OnDamageListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */