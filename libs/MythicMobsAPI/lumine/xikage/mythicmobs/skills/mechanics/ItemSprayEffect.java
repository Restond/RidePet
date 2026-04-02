/*     */ package lumine.xikage.mythicmobs.skills.mechanics;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.SkillAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ @MythicMechanic(author = "Ashijin", name = "effect:itemspray", aliases = {"itemspray", "e:itemspray"}, description = "Sprays items everywhere")
/*     */ public class ItemSprayEffect
/*     */   extends SkillMechanic
/*     */   implements ITargetedEntitySkill, ITargetedLocationSkill {
/*     */   protected String itemName;
/*  25 */   protected ItemStack item = null;
/*     */   
/*     */   protected int amount;
/*     */   
/*     */   protected int duration;
/*     */   protected double radius;
/*     */   
/*     */   public ItemSprayEffect(String skill, MythicLineConfig mlc) {
/*  33 */     super(skill, mlc);
/*  34 */     this.ASYNC_SAFE = false;
/*     */     
/*  36 */     this.itemName = mlc.getString(new String[] { "item", "i" }, "iron_sword", new String[0]);
/*     */     
/*  38 */     this.amount = mlc.getInteger(new String[] { "amount", "a" }, 10);
/*  39 */     this.duration = mlc.getInteger(new String[] { "duration", "d" }, 20);
/*  40 */     this.radius = mlc.getDouble(new String[] { "radius", "r" }, 0.0D);
/*     */     
/*  42 */     this.force = mlc.getDouble("force", 1.0D);
/*  43 */     this.force = mlc.getDouble("f", this.force);
/*  44 */     this.force = mlc.getDouble("velocity", this.force);
/*  45 */     this.force = mlc.getDouble("v", this.force);
/*     */     
/*  47 */     this.yForce = mlc.getDouble("yforce", this.force);
/*  48 */     this.yForce = mlc.getDouble("yf", this.yForce);
/*  49 */     this.yForce = mlc.getDouble("yvelocity", this.yForce);
/*  50 */     this.yForce = mlc.getDouble("yv", this.yForce);
/*     */     
/*  52 */     this.yOffset = mlc.getDouble("yoffset", 1.0D);
/*  53 */     this.yOffset = mlc.getDouble("y", this.yOffset);
/*     */     
/*  55 */     this.allowPickup = mlc.getBoolean(new String[] { "allowpickup", "ap" }, false);
/*     */     
/*  57 */     MythicMobs.inst().getSkillManager().queueSecondPass(() -> {
/*     */           Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(this.itemName);
/*     */           
/*     */           if (maybeItem.isPresent()) {
/*     */             this.item = BukkitAdapter.adapt(((MythicItem)maybeItem.get()).generateItemStack(1));
/*     */           } else {
/*     */             try {
/*     */               this.item = new ItemStack(Material.valueOf(this.itemName.toUpperCase()));
/*  65 */             } catch (Exception exception) {}
/*     */           } 
/*     */         });
/*     */   }
/*     */   protected double force; protected double yForce; protected double yOffset; protected boolean allowPickup;
/*     */   
/*     */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*  72 */     playEffect(target);
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*  78 */     playEffect(target.getLocation());
/*  79 */     return false;
/*     */   }
/*     */   
/*     */   protected void playEffect(AbstractLocation l) {
/*  83 */     if (this.item == null) {
/*  84 */       Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(this.itemName);
/*     */       
/*  86 */       if (maybeItem.isPresent()) {
/*  87 */         this.item = BukkitAdapter.adapt(((MythicItem)maybeItem.get()).generateItemStack(1));
/*     */       } else {
/*     */         try {
/*  90 */           this.item = new ItemStack(Material.valueOf(this.itemName.toUpperCase()));
/*  91 */         } catch (Exception ex) {
/*  92 */           MythicMobs.inst().getConfigManager(); if (ConfigManager.debugLevel > 0) {
/*  93 */             ex.printStackTrace();
/*     */           }
/*  95 */           MythicMobs.error("Executing ItemSprayEffect failed: Could not find item " + this.itemName);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 101 */     SkillAdapter.get().itemSprayEffect(l, this.item, this.amount, this.duration, this.force, this.yForce, this.radius, this.yOffset, this.allowPickup);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ItemSprayEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */