/*     */ package lumine.xikage.mythicmobs.api.bukkit.events;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*     */ import io.lumine.xikage.mythicmobs.drops.IIntangibleDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.LootBag;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.ExperienceDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.VaultDrop;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import java.util.Collection;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
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
/*     */ public class MythicMobLootDropEvent
/*     */   extends Event
/*     */ {
/*     */   private final ActiveMob mob;
/*     */   private final LivingEntity killer;
/*     */   private final LootBag drops;
/*  35 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   public MythicMobLootDropEvent(ActiveMob am, LivingEntity killer, LootBag drops) {
/*  38 */     this.mob = am;
/*  39 */     this.killer = killer;
/*  40 */     this.drops = drops;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActiveMob getMob() {
/*  48 */     return this.mob;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getEntity() {
/*  56 */     return BukkitAdapter.adapt(this.mob.getEntity());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MythicMob getMobType() {
/*  64 */     return this.mob.getType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMobLevel() {
/*  72 */     return this.mob.getLevel();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int getNumberOfPlayerKills() {
/*  77 */     return this.mob.getPlayerKills();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LivingEntity getKiller() {
/*  85 */     return this.killer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LootBag getDrops() {
/*  93 */     return this.drops;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<Drop> getPhysicalDrops() {
/* 101 */     return this.drops.getLootTable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<Drop> getIntangibleDrops() {
/* 109 */     return this.drops.getLootTableIntangible().values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Optional<Drop> getIntangibleDrop(Class<? extends IIntangibleDrop> clazz) {
/* 117 */     return Optional.ofNullable((Drop)this.drops.getLootTableIntangible().getOrDefault(clazz, null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExp() {
/* 125 */     Drop drop = (Drop)this.drops.getLootTableIntangible().getOrDefault(ExperienceDrop.class, null);
/*     */     
/* 127 */     if (drop == null) {
/* 128 */       return 0;
/*     */     }
/* 130 */     return (int)drop.getAmount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExp(int amount) {
/* 139 */     Drop drop = (Drop)this.drops.getLootTableIntangible().getOrDefault(ExperienceDrop.class, null);
/*     */     
/* 141 */     if (drop == null) {
/* 142 */       this.drops.getLootTableIntangible().put(ExperienceDrop.class, new ExperienceDrop("API", null, amount));
/*     */     } else {
/* 144 */       drop.setAmount(amount);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMoney() {
/* 153 */     Drop drop = (Drop)this.drops.getLootTableIntangible().getOrDefault(VaultDrop.class, null);
/*     */     
/* 155 */     if (drop == null) {
/* 156 */       return 0;
/*     */     }
/* 158 */     return (int)drop.getAmount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMoney(int amount) {
/* 166 */     Drop drop = (Drop)this.drops.getLootTableIntangible().getOrDefault(VaultDrop.class, null);
/*     */     
/* 168 */     if (drop == null) {
/* 169 */       this.drops.getLootTableIntangible().put(VaultDrop.class, new VaultDrop("API", null, amount));
/*     */     } else {
/* 171 */       drop.setAmount(amount);
/*     */     } 
/*     */   }
/*     */   
/*     */   public HandlerList getHandlers() {
/* 176 */     return handlers;
/*     */   }
/*     */   
/*     */   public static HandlerList getHandlerList() {
/* 180 */     return handlers;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\api\bukkit\events\MythicMobLootDropEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */