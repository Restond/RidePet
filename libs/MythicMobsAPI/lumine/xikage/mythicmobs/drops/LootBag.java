/*     */ package lumine.xikage.mythicmobs.drops;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*     */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*     */ import io.lumine.xikage.mythicmobs.drops.EquipSlot;
/*     */ import io.lumine.xikage.mythicmobs.drops.IIntangibleDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.IItemDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.ILocationDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.IMessagingDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.IMultiDrop;
/*     */ import io.lumine.xikage.mythicmobs.drops.droppables.CustomDrop;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class LootBag {
/*     */   private final DropMetadata metadata;
/*     */   
/*  30 */   public void setLootTable(List<Drop> lootTable) { this.lootTable = lootTable; } public void setLootTableIntangible(Map<Class<?>, Drop> lootTableIntangible) { this.lootTableIntangible = lootTableIntangible; } public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof io.lumine.xikage.mythicmobs.drops.LootBag)) return false;  io.lumine.xikage.mythicmobs.drops.LootBag other = (io.lumine.xikage.mythicmobs.drops.LootBag)o; if (!other.canEqual(this)) return false;  Object this$metadata = getMetadata(), other$metadata = other.getMetadata(); if ((this$metadata == null) ? (other$metadata != null) : !this$metadata.equals(other$metadata)) return false;  Object<Drop> this$lootTable = (Object<Drop>)getLootTable(), other$lootTable = (Object<Drop>)other.getLootTable(); if ((this$lootTable == null) ? (other$lootTable != null) : !this$lootTable.equals(other$lootTable)) return false;  Object<Class, Drop> this$lootTableIntangible = (Object<Class, Drop>)getLootTableIntangible(), other$lootTableIntangible = (Object<Class, Drop>)other.getLootTableIntangible(); return !((this$lootTableIntangible == null) ? (other$lootTableIntangible != null) : !this$lootTableIntangible.equals(other$lootTableIntangible)); } protected boolean canEqual(Object other) { return other instanceof io.lumine.xikage.mythicmobs.drops.LootBag; } public int hashCode() { int PRIME = 59; result = 1; Object $metadata = getMetadata(); result = result * 59 + (($metadata == null) ? 43 : $metadata.hashCode()); Object<Drop> $lootTable = (Object<Drop>)getLootTable(); result = result * 59 + (($lootTable == null) ? 43 : $lootTable.hashCode()); Object<Class, Drop> $lootTableIntangible = (Object<Class, Drop>)getLootTableIntangible(); return result * 59 + (($lootTableIntangible == null) ? 43 : $lootTableIntangible.hashCode()); } public String toString() { return "LootBag(metadata=" + getMetadata() + ", lootTable=" + getLootTable() + ", lootTableIntangible=" + getLootTableIntangible() + ")"; }
/*     */   
/*     */   public DropMetadata getMetadata() {
/*  33 */     return this.metadata;
/*     */   }
/*  35 */   private List<Drop> lootTable = new LinkedList<>(); public List<Drop> getLootTable() { return this.lootTable; }
/*  36 */    private Map<Class, Drop> lootTableIntangible = (Map)new HashMap<>(); public Map<Class, Drop> getLootTableIntangible() { return this.lootTableIntangible; }
/*     */   
/*     */   public LootBag(DropMetadata meta) {
/*  39 */     this.metadata = meta;
/*     */   }
/*     */   
/*     */   public Collection<Drop> getDrops() {
/*  43 */     Collection<Drop> drops = new LinkedList<>();
/*  44 */     drops.addAll(this.lootTable);
/*  45 */     drops.addAll(this.lootTableIntangible.values());
/*  46 */     return drops;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public io.lumine.xikage.mythicmobs.drops.LootBag add(Drop type) {
/*  51 */     return add(null, type);
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.drops.LootBag add(DropMetadata data, Drop type) {
/*  55 */     MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "Adding Drop to LootBag: {0}", new Object[] { type.getLine() });
/*  56 */     if (type instanceof CustomDrop) {
/*  57 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_INFO, "-- Rolling Custom Drop from API", new Object[0]);
/*  58 */       if (!((CustomDrop)type).getDrop().isPresent()) {
/*  59 */         return this;
/*     */       }
/*  61 */       type.rollAmount(data);
/*  62 */       type = ((CustomDrop)type).getDrop().get();
/*     */     } 
/*  64 */     if (type instanceof IIntangibleDrop || type instanceof io.lumine.xikage.mythicmobs.drops.droppables.ExperienceDrop) {
/*  65 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Adding Intangible Drop", new Object[0]);
/*  66 */       this.lootTableIntangible.merge(type.getClass(), type, (o, n) -> o.addAmount(n));
/*     */ 
/*     */     
/*     */     }
/*  70 */     else if (type instanceof IMultiDrop) {
/*  71 */       int amount = (int)type.getAmount();
/*  72 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Adding {0} of MultiDrop", new Object[] { Integer.valueOf(amount) });
/*  73 */       for (int i = 0; i < amount; i++) {
/*  74 */         add(data, ((IMultiDrop)type).get(this.metadata));
/*     */       }
/*     */     } else {
/*  77 */       MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "Adding {0} of drop: {1}", new Object[] { Double.valueOf(type.getAmount()), type.getLine() });
/*  78 */       this.lootTable.add(type);
/*     */     } 
/*  80 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.drops.LootBag add(DropMetadata data, io.lumine.xikage.mythicmobs.drops.LootBag other) {
/*  84 */     for (Drop type : other.getLootTableIntangible().values()) {
/*  85 */       this.lootTableIntangible.merge(type.getClass(), type, (o, n) -> o.addAmount(n));
/*     */     }
/*  87 */     for (Drop type : other.getLootTable()) {
/*  88 */       type.rollAmount(data);
/*  89 */       this.lootTable.add(type);
/*     */     } 
/*  91 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drop(AbstractLocation target) {
/*  96 */     Location loc = BukkitAdapter.adapt(target);
/*     */     
/*  98 */     for (Drop type : getDrops()) {
/*  99 */       if (type instanceof IItemDrop) {
/* 100 */         AbstractItemStack stack = ((IItemDrop)type).getDrop(this.metadata);
/* 101 */         loc.getWorld().dropItem(loc, BukkitAdapter.adapt(stack)); continue;
/* 102 */       }  if (type instanceof ILocationDrop) {
/* 103 */         ((ILocationDrop)type).drop(target, this.metadata);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropOrGive(AbstractLocation location, AbstractPlayer target) {}
/*     */ 
/*     */   
/*     */   public void give(AbstractPlayer player) {
/* 113 */     give(player, true);
/*     */   }
/*     */   
/*     */   public void give(AbstractPlayer player, boolean sendMessages) {
/* 117 */     Player p = BukkitAdapter.adapt(player);
/*     */     
/* 119 */     Map<IMessagingDrop, Double> messagingDrops = new HashMap<>();
/*     */     
/* 121 */     for (Drop type : getDrops()) {
/* 122 */       if (type instanceof IItemDrop) {
/* 123 */         AbstractItemStack stack = ((IItemDrop)type).getDrop(this.metadata);
/* 124 */         ItemStack is = BukkitAdapter.adapt(stack);
/* 125 */         p.getInventory().addItem(new ItemStack[] { is });
/* 126 */       } else if (type instanceof io.lumine.xikage.mythicmobs.drops.droppables.ExperienceDrop) {
/* 127 */         p.giveExp((int)type.getAmount());
/* 128 */       } else if (type instanceof IIntangibleDrop) {
/* 129 */         ((IIntangibleDrop)type).giveDrop(player, this.metadata);
/*     */       } 
/*     */       
/* 132 */       if (type instanceof IMessagingDrop) {
/* 133 */         messagingDrops.merge((IMessagingDrop)type, Double.valueOf(type.getAmount()), (n, o) -> Double.valueOf(n.doubleValue() + o.doubleValue()));
/*     */       }
/*     */     } 
/* 136 */     if (messagingDrops.size() > 0 && sendMessages) {
/* 137 */       for (Map.Entry<IMessagingDrop, Double> m : messagingDrops.entrySet()) {
/* 138 */         String message = ((IMessagingDrop)m.getKey()).getRewardMessage(this.metadata, ((Double)m.getValue()).doubleValue());
/* 139 */         if (message != null) {
/* 140 */           player.sendMessage(message);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void equip(AbstractEntity entity) {
/* 148 */     AbstractItemStack hand = null, offhand = null, head = null, chest = null, legs = null, feet = null;
/*     */     
/* 150 */     for (Drop type : getDrops()) {
/*     */       try {
/* 152 */         if (type instanceof IItemDrop && 
/* 153 */           type.getDropVar() != null) {
/*     */           
/* 155 */           EquipSlot slot = EquipSlot.of(type.getDropVar().toUpperCase());
/*     */           
/* 157 */           switch (null.$SwitchMap$io$lumine$xikage$mythicmobs$drops$EquipSlot[slot.ordinal()]) {
/*     */             case 1:
/* 159 */               hand = ((IItemDrop)type).getDrop(this.metadata);
/*     */             
/*     */             case 2:
/* 162 */               feet = ((IItemDrop)type).getDrop(this.metadata);
/*     */             
/*     */             case 3:
/* 165 */               legs = ((IItemDrop)type).getDrop(this.metadata);
/*     */             
/*     */             case 4:
/* 168 */               chest = ((IItemDrop)type).getDrop(this.metadata);
/*     */             
/*     */             case 5:
/* 171 */               head = ((IItemDrop)type).getDrop(this.metadata);
/*     */             
/*     */             case 6:
/* 174 */               offhand = ((IItemDrop)type).getDrop(this.metadata);
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         } 
/* 181 */       } catch (Exception ex) {
/* 182 */         if (ConfigManager.debugLevel > 0) {
/* 183 */           ex.printStackTrace();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 188 */     if (head != null) {
/* 189 */       entity.equipItemHead(head);
/*     */     }
/* 191 */     if (chest != null) {
/* 192 */       entity.equipItemChest(chest);
/*     */     }
/* 194 */     if (legs != null) {
/* 195 */       entity.equipItemLegs(legs);
/*     */     }
/* 197 */     if (feet != null) {
/* 198 */       entity.equipItemFeet(feet);
/*     */     }
/* 200 */     if (hand != null) {
/* 201 */       entity.equipItemMainHand(hand);
/*     */     }
/* 203 */     if (offhand != null) {
/* 204 */       entity.equipItemOffHand(offhand);
/*     */     }
/*     */   }
/*     */   
/*     */   public int size() {
/* 209 */     return this.lootTable.size();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\LootBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */