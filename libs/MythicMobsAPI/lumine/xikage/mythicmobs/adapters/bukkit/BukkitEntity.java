/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitPlayer;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*     */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Creature;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.EntityEquipment;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.metadata.FixedMetadataValue;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BukkitEntity
/*     */   implements AbstractEntity
/*     */ {
/*     */   private final Entity entityRef;
/*     */   
/*     */   public BukkitEntity(Entity e) {
/*  43 */     this.entityRef = e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getBukkitEntity() {
/*  48 */     Entity entity = this.entityRef;
/*  49 */     if (entity != null) {
/*  50 */       return entity;
/*     */     }
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public LivingEntity getEntityAsLiving() {
/*  57 */     return (LivingEntity)getBukkitEntity();
/*     */   }
/*     */   
/*     */   public Creature getEntityAsCreature() {
/*  61 */     return (Creature)getBukkitEntity();
/*     */   }
/*     */   
/*     */   public Player getEntityAsPlayer() {
/*  65 */     return (Player)getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractLocation getLocation() {
/*  70 */     return BukkitAdapter.adapt(getBukkitEntity().getLocation());
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractWorld getWorld() {
/*  75 */     return getLocation().getWorld();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLiving() {
/*  80 */     return getBukkitEntity() instanceof LivingEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCreature() {
/*  85 */     return getBukkitEntity() instanceof Creature;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMonster() {
/*  90 */     return getBukkitEntity() instanceof org.bukkit.entity.Monster;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPlayer() {
/*  95 */     return getBukkitEntity() instanceof Player;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getUniqueId() {
/* 100 */     return getBukkitEntity().getUniqueId();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasLineOfSight(AbstractEntity e) {
/* 105 */     return ((LivingEntity)getBukkitEntity()).hasLineOfSight(e.getBukkitEntity());
/*     */   }
/*     */ 
/*     */   
/*     */   public void teleport(AbstractLocation l) {
/* 110 */     getBukkitEntity().teleport(BukkitAdapter.adapt(l));
/*     */   }
/*     */ 
/*     */   
/*     */   public double getHealth() {
/* 115 */     return getEntityAsLiving().getHealth();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDead() {
/* 120 */     return getBukkitEntity().isDead();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/* 125 */     return getBukkitEntity().isValid();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove() {
/* 130 */     Entity entity = this.entityRef;
/* 131 */     if (entity != null) {
/* 132 */       entity.remove();
/* 133 */       return entity.isDead();
/*     */     } 
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMaxHealth() {
/* 141 */     return getEntityAsLiving().getMaxHealth();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFireTicks(int ticks) {
/* 146 */     getBukkitEntity().setFireTicks(ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCustomName() {
/* 151 */     return getEntityAsLiving().getCustomName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPotionEffect(PotionEffect effect) {
/* 156 */     getEntityAsLiving().addPotionEffect(effect);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPassenger(Entity entity) {
/* 161 */     getBukkitEntity().setPassenger(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasGravity() {
/* 166 */     return getBukkitEntity().hasGravity();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGravity(boolean bool) {
/* 171 */     getBukkitEntity().setGravity(bool);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasAI() {
/* 176 */     if (isLiving()) {
/* 177 */       return getEntityAsLiving().hasAI();
/*     */     }
/* 179 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAI(boolean bool) {
/* 184 */     if (isLiving()) {
/* 185 */       getEntityAsLiving().setAI(bool);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractLocation getEyeLocation() {
/* 191 */     if (isLiving()) {
/* 192 */       return BukkitAdapter.adapt(getEntityAsLiving().getEyeLocation());
/*     */     }
/* 194 */     return BukkitAdapter.adapt(getBukkitEntity().getLocation());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/* 200 */     if (isLiving()) {
/* 201 */       return getEntityAsLiving().getEyeHeight();
/*     */     }
/* 203 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEntity getTarget() {
/* 209 */     if (isCreature()) {
/* 210 */       return BukkitAdapter.adapt((Entity)getEntityAsCreature().getTarget());
/*     */     }
/* 212 */     if (getBukkitEntity().getLastDamageCause() != null) {
/* 213 */       return BukkitAdapter.adapt(getBukkitEntity().getLastDamageCause().getEntity());
/*     */     }
/* 215 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEntity getPassenger() {
/* 222 */     Entity e = getBukkitEntity().getPassenger();
/*     */     
/* 224 */     if (e != null) {
/* 225 */       return BukkitAdapter.adapt(e);
/*     */     }
/* 227 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEntity getVehicle() {
/* 233 */     Entity e = getBukkitEntity().getVehicle();
/*     */     
/* 235 */     if (e != null) {
/* 236 */       return BukkitAdapter.adapt(e);
/*     */     }
/* 238 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void eject() {
/* 244 */     getBukkitEntity().eject();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHealth(double d) {
/* 249 */     Entity e = getBukkitEntity();
/*     */     
/* 251 */     if (e instanceof LivingEntity) {
/* 252 */       ((LivingEntity)e).setHealth(d);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 258 */     if (isPlayer()) {
/* 259 */       return getEntityAsPlayer().getName();
/*     */     }
/* 261 */     if (getBukkitEntity().getName() != null) {
/* 262 */       return getBukkitEntity().getName();
/*     */     }
/* 264 */     return getBukkitEntity().getCustomName();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxHealth(double health) {
/* 270 */     getEntityAsLiving().setMaxHealth(health);
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(float damage) {
/* 275 */     getEntityAsLiving().damage(damage);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPassenger(AbstractEntity entity) {
/* 280 */     getBukkitEntity().setPassenger(BukkitAdapter.adapt(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractPlayer asPlayer() {
/* 285 */     if (getBukkitEntity() instanceof Player) {
/* 286 */       return (AbstractPlayer)new BukkitPlayer(getEntityAsPlayer());
/*     */     }
/* 288 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 294 */     if (o instanceof io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitEntity) {
/* 295 */       return getUniqueId().equals(((io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitEntity)o).getUniqueId());
/*     */     }
/* 297 */     return super.equals(o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 303 */     return getBukkitEntity().getUniqueId().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoDamageTicks(int ticks) {
/* 308 */     if (isLiving()) {
/* 309 */       ((LivingEntity)getBukkitEntity()).setNoDamageTicks(ticks);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCitizensNPC() {
/* 315 */     return getBukkitEntity().hasMetadata("NPC");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAnimal() {
/* 320 */     return getBukkitEntity() instanceof org.bukkit.entity.Animals;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWaterMob() {
/* 325 */     return getBukkitEntity() instanceof org.bukkit.entity.WaterMob;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyingMob() {
/* 330 */     return getBukkitEntity() instanceof org.bukkit.entity.Flying;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGliding() {
/* 335 */     return ((LivingEntity)getBukkitEntity()).isGliding();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPotionEffect(String type) {
/* 340 */     return hasPotionEffect(type, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPotionEffect(String type, RangedDouble level, RangedDouble duration) {
/* 345 */     if (isLiving()) {
/* 346 */       LivingEntity e = (LivingEntity)getBukkitEntity();
/* 347 */       PotionEffectType potiontype = PotionEffectType.getByName(type);
/*     */       
/* 349 */       if (e.hasPotionEffect(potiontype)) {
/* 350 */         PotionEffect effect = e.getPotionEffect(potiontype);
/* 351 */         if (level != null && !level.equals(Integer.valueOf(effect.getAmplifier()))) {
/* 352 */           return false;
/*     */         }
/* 354 */         if (duration != null && !duration.equals(Integer.valueOf(effect.getDuration()))) {
/* 355 */           return false;
/*     */         }
/* 357 */         return true;
/*     */       } 
/*     */     } 
/* 360 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPotionEffect() {
/* 365 */     if (isLiving()) {
/* 366 */       LivingEntity e = (LivingEntity)getBukkitEntity();
/* 367 */       return (e.getActivePotionEffects().size() > 0);
/*     */     } 
/* 369 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasScoreboardTag(String tag) {
/* 374 */     return getBukkitEntity().getScoreboardTags().contains(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addScoreboardTag(String tag) {
/* 379 */     getBukkitEntity().addScoreboardTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeScoreboardTag(String tag) {
/* 384 */     getBukkitEntity().removeScoreboardTag(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void equipItemHead(AbstractItemStack item) {
/* 389 */     if (!isLiving())
/*     */       return; 
/* 391 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/* 392 */     EntityEquipment ee = le.getEquipment();
/*     */     
/* 394 */     ee.setHelmet(BukkitAdapter.adapt(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public void equipItemChest(AbstractItemStack item) {
/* 399 */     if (!isLiving())
/*     */       return; 
/* 401 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/* 402 */     EntityEquipment ee = le.getEquipment();
/*     */     
/* 404 */     ee.setChestplate(BukkitAdapter.adapt(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public void equipItemLegs(AbstractItemStack item) {
/* 409 */     if (!isLiving())
/*     */       return; 
/* 411 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/* 412 */     EntityEquipment ee = le.getEquipment();
/*     */     
/* 414 */     ee.setLeggings(BukkitAdapter.adapt(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public void equipItemFeet(AbstractItemStack item) {
/* 419 */     if (!isLiving())
/*     */       return; 
/* 421 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/* 422 */     EntityEquipment ee = le.getEquipment();
/*     */     
/* 424 */     ee.setBoots(BukkitAdapter.adapt(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public void equipItemMainHand(AbstractItemStack item) {
/* 429 */     if (!isLiving())
/*     */       return; 
/* 431 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/* 432 */     EntityEquipment ee = le.getEquipment();
/*     */     
/* 434 */     if (MythicMobs.inst().getMinecraftVersion() <= 8) {
/* 435 */       ee.setItemInHand(BukkitAdapter.adapt(item));
/*     */     } else {
/* 437 */       ee.setItemInMainHand(BukkitAdapter.adapt(item));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void equipItemOffHand(AbstractItemStack item) {
/* 443 */     if (!isLiving())
/* 444 */       return;  if (MythicMobs.inst().getMinecraftVersion() <= 8) {
/*     */       return;
/*     */     }
/*     */     
/* 448 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/* 449 */     EntityEquipment ee = le.getEquipment();
/*     */     
/* 451 */     ee.setItemInOffHand(BukkitAdapter.adapt(item));
/*     */   }
/*     */   
/*     */   public void setDamage(double damage) {
/* 455 */     if (!isLiving())
/*     */       return; 
/* 457 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/*     */     
/* 459 */     if (MythicMobs.inst().getMinecraftVersion() >= 9 && 
/* 460 */       le.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE) != null) {
/* 461 */       le.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(damage);
/*     */     }
/*     */ 
/*     */     
/* 465 */     if (CompatibilityManager.Heroes != null) {
/* 466 */       CompatibilityManager.Heroes.setMobDamage(le, damage);
/*     */     }
/*     */   }
/*     */   
/*     */   public double getDamage() {
/* 471 */     if (!isLiving()) return 0.0D;
/*     */     
/* 473 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/*     */     
/* 475 */     if (MythicMobs.inst().getMinecraftVersion() >= 9 && 
/* 476 */       le.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE) != null) {
/* 477 */       return le.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
/*     */     }
/*     */ 
/*     */     
/* 481 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMovementSpeed(double speed) {
/* 486 */     if (!isLiving())
/*     */       return; 
/* 488 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/*     */     
/* 490 */     if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 491 */       le.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
/*     */     } else {
/* 493 */       MythicMobs.inst().getVolatileCodeHandler().setMobSpeed((Entity)le, speed);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAttackSpeed(double speed) {
/* 499 */     if (!isLiving())
/*     */       return; 
/* 501 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/*     */     
/* 503 */     if (MythicMobs.inst().getMinecraftVersion() >= 9 && 
/* 504 */       le.getAttribute(Attribute.GENERIC_ATTACK_SPEED) != null) {
/* 505 */       le.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(speed);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLuck() {
/* 512 */     if (!isLiving()) return 0;
/*     */     
/* 514 */     if (MythicMobs.inst().getMinecraftVersion() < 9) {
/* 515 */       return 0;
/*     */     }
/*     */     
/* 518 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/*     */     
/* 520 */     double luck = 0.0D;
/*     */     
/* 522 */     if (le.getAttribute(Attribute.GENERIC_LUCK) != null) {
/* 523 */       luck = le.getAttribute(Attribute.GENERIC_LUCK).getBaseValue();
/*     */     }
/*     */     
/* 526 */     luck += le.getEquipment().getItemInMainHand().getEnchantmentLevel(Enchantment.LUCK);
/*     */     
/* 528 */     for (PotionEffect pe : le.getActivePotionEffects()) {
/* 529 */       if (pe.getType() == PotionEffectType.LUCK) {
/* 530 */         luck += pe.getAmplifier(); continue;
/* 531 */       }  if (pe.getType() == PotionEffectType.UNLUCK) {
/* 532 */         luck -= pe.getAmplifier();
/*     */       }
/*     */     } 
/*     */     
/* 536 */     return (int)luck;
/*     */   }
/*     */   
/*     */   public int getEnchantmentLevel(String enchantmentName) {
/*     */     Enchantment enchant;
/* 541 */     if (!isLiving()) return 0;
/*     */ 
/*     */     
/*     */     try {
/* 545 */       enchant = Enchantment.getByName(enchantmentName);
/* 546 */     } catch (Exception ex) {
/* 547 */       return 0;
/*     */     } 
/* 549 */     if (enchant == null) {
/* 550 */       return 0;
/*     */     }
/*     */     
/* 553 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/*     */     
/* 555 */     int level = 0;
/*     */     
/* 557 */     level += le.getEquipment().getItemInMainHand().getEnchantmentLevel(enchant);
/*     */     
/* 559 */     if (MythicMobs.inst().getMinecraftVersion() > 8) {
/* 560 */       level += le.getEquipment().getItemInOffHand().getEnchantmentLevel(enchant);
/*     */     }
/*     */     
/* 563 */     for (ItemStack item : le.getEquipment().getArmorContents()) {
/* 564 */       level += item.getEnchantmentLevel(enchant);
/*     */     }
/* 566 */     return level;
/*     */   }
/*     */   
/*     */   public int getEnchantmentLevelHeld(String enchantmentName) {
/*     */     Enchantment enchant;
/* 571 */     if (!isLiving()) return 0;
/*     */ 
/*     */     
/*     */     try {
/* 575 */       enchant = Enchantment.getByName(enchantmentName);
/* 576 */     } catch (Exception ex) {
/* 577 */       return 0;
/*     */     } 
/* 579 */     if (enchant == null) {
/* 580 */       return 0;
/*     */     }
/*     */     
/* 583 */     LivingEntity le = (LivingEntity)getBukkitEntity();
/*     */     
/* 585 */     return le.getEquipment().getItemInMainHand().getEnchantmentLevel(enchant);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMetadata(String key, Object value) {
/* 590 */     Entity e = getBukkitEntity();
/* 591 */     e.setMetadata(key, (MetadataValue)new FixedMetadataValue((Plugin)MythicMobs.inst(), value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMetadata(String key) {
/* 596 */     Entity e = getBukkitEntity();
/* 597 */     return e.hasMetadata(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMetadata(String key) {
/* 602 */     Entity e = getBukkitEntity();
/* 603 */     e.removeMetadata(key, (Plugin)MythicMobs.inst());
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean isLoaded() {
/* 608 */     Entity e = getBukkitEntity();
/*     */     
/* 610 */     return e.getLocation().getChunk().isLoaded();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<Object> getMetadata(String key) {
/* 616 */     Entity e = getBukkitEntity();
/*     */     
/* 618 */     List<MetadataValue> values = e.getMetadata(key);
/*     */     
/* 620 */     for (MetadataValue mv : values) {
/* 621 */       if (mv.getOwningPlugin().equals(MythicMobs.inst())) {
/* 622 */         return Optional.of(mv.value());
/*     */       }
/*     */     } 
/* 625 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVelocity(AbstractVector velocity) {
/* 630 */     Entity e = getBukkitEntity();
/* 631 */     Vector v = BukkitAdapter.adapt(velocity);
/*     */     
/* 633 */     e.setVelocity(v);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnGround() {
/* 638 */     return getBukkitEntity().isOnGround();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */