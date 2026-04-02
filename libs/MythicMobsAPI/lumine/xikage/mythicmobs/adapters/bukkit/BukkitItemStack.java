/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*     */ import io.lumine.xikage.mythicmobs.util.UUIDUtil;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BukkitItemStack
/*     */   implements AbstractItemStack, Cloneable
/*     */ {
/*     */   private ItemStack item;
/*  33 */   private MythicItem mythicItem = null;
/*     */   
/*     */   public BukkitItemStack(String type) {
/*  36 */     Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(type);
/*     */     
/*  38 */     if (maybeItem.isPresent()) {
/*  39 */       this.mythicItem = maybeItem.get();
/*  40 */       this.item = BukkitAdapter.adapt(this.mythicItem.generateItemStack(1));
/*     */     } else {
/*  42 */       this.item = new ItemStack(Material.matchMaterial(type.toUpperCase()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public BukkitItemStack(Material m) {
/*  47 */     this.item = new ItemStack(m);
/*     */   }
/*     */   
/*     */   public BukkitItemStack(ItemStack stack) {
/*  51 */     this.item = stack;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack durability(int d) {
/*  55 */     if (MythicMobs.inst().getMinecraftVersion() < 13) {
/*  56 */       this.item.setDurability((short)(byte)d);
/*     */     } else {
/*  58 */       checkItemMeta();
/*  59 */       if (this.item.getItemMeta() instanceof Damageable) {
/*  60 */         ItemMeta meta = this.item.getItemMeta();
/*  61 */         ((Damageable)meta).setDamage(d);
/*  62 */         this.item.setItemMeta(meta);
/*     */       } 
/*     */     } 
/*  65 */     return this;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack data(int d) {
/*  70 */     if (MythicMobs.inst().getMinecraftVersion() < 13) {
/*  71 */       this.item.setDurability((short)(byte)d);
/*     */     } else {
/*  73 */       checkItemMeta();
/*  74 */       if (this.item.getItemMeta() instanceof Damageable) {
/*  75 */         ItemMeta meta = this.item.getItemMeta();
/*  76 */         ((Damageable)meta).setDamage(d);
/*  77 */         this.item.setItemMeta(meta);
/*     */       } 
/*     */     } 
/*  80 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack amount(int a) {
/*  84 */     this.item.setAmount(a);
/*  85 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack colorData(DyeColor dc) {
/*  89 */     this.item.setDurability((short)dc.getWoolData());
/*  90 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack color(String color) {
/*     */     try {
/*  95 */       if (this.item.getType().equals(Material.LEATHER_CHESTPLATE) || this.item.getType().equals(Material.LEATHER_BOOTS) || this.item.getType().equals(Material.LEATHER_LEGGINGS) || this.item.getType().equals(Material.LEATHER_HELMET)) {
/*  96 */         ItemMeta im = this.item.getItemMeta();
/*  97 */         LeatherArmorMeta la = (LeatherArmorMeta)im;
/*  98 */         if (color.contains(",")) {
/*  99 */           String[] rgb = color.split(",");
/* 100 */           int r = Integer.parseInt(rgb[0]);
/* 101 */           int g = Integer.parseInt(rgb[1]);
/* 102 */           int b = Integer.parseInt(rgb[2]);
/* 103 */           la.setColor(Color.fromRGB(r, g, b));
/*     */         } else {
/* 105 */           DyeColor dColor = DyeColor.valueOf(color);
/* 106 */           la.setColor(dColor.getColor());
/*     */         } 
/* 108 */         this.item.setItemMeta((ItemMeta)la);
/*     */       }
/*     */     
/*     */     }
/* 112 */     catch (Exception ex) {
/* 113 */       MythicMobs.inst().handleException(ex);
/*     */     } 
/* 115 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack display(String d) {
/* 119 */     checkItemMeta();
/*     */     
/* 121 */     ItemMeta im = this.item.getItemMeta();
/* 122 */     im.setDisplayName(d);
/* 123 */     this.item.setItemMeta(im);
/* 124 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack lore(String[] lore) {
/* 128 */     checkItemMeta();
/*     */     
/* 130 */     ItemMeta im = this.item.getItemMeta();
/* 131 */     List<String> lores = new ArrayList<>();
/* 132 */     for (String l : lore) lores.add(l); 
/* 133 */     im.setLore(lores);
/* 134 */     this.item.setItemMeta(im);
/* 135 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack lore(List<String> lores) {
/* 139 */     checkItemMeta();
/*     */     
/* 141 */     ItemMeta im = this.item.getItemMeta();
/* 142 */     im.setLore(lores);
/* 143 */     this.item.setItemMeta(im);
/* 144 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack enchant(Enchantment ench, int level) {
/* 148 */     this.item.addUnsafeEnchantment(ench, level);
/* 149 */     return this;
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack setOwner(String owner) {
/* 153 */     checkItemMeta();
/* 154 */     if (!(this.item.getItemMeta() instanceof SkullMeta)) return this;
/*     */     
/* 156 */     SkullMeta im = (SkullMeta)this.item.getItemMeta();
/* 157 */     im.setOwner(owner);
/*     */     
/* 159 */     this.item.setItemMeta((ItemMeta)im);
/* 160 */     return this;
/*     */   }
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack setSkullTexture(String texture) {
/*     */     UUID skinUUID;
/* 164 */     checkItemMeta();
/* 165 */     if (!(this.item.getItemMeta() instanceof SkullMeta)) return this;
/*     */     
/* 167 */     SkullMeta im = (SkullMeta)this.item.getItemMeta();
/*     */ 
/*     */     
/* 170 */     if (im.getDisplayName() != null) {
/* 171 */       skinUUID = UUIDUtil.getUUIDFromString(im.getDisplayName());
/*     */     } else {
/* 173 */       skinUUID = UUID.randomUUID();
/*     */     } 
/* 175 */     GameProfile profile = new GameProfile(skinUUID, null);
/*     */     
/* 177 */     profile.getProperties().put("textures", new Property("textures", texture));
/* 178 */     Field profileField = null;
/*     */     try {
/* 180 */       profileField = im.getClass().getDeclaredField("profile");
/* 181 */     } catch (NoSuchFieldException|SecurityException e) {
/* 182 */       e.printStackTrace();
/*     */     } 
/* 184 */     profileField.setAccessible(true);
/*     */     try {
/* 186 */       profileField.set(im, profile);
/* 187 */     } catch (IllegalArgumentException|IllegalAccessException e) {
/* 188 */       e.printStackTrace();
/*     */     } 
/* 190 */     this.item.setItemMeta((ItemMeta)im);
/* 191 */     return this;
/*     */   }
/*     */   
/*     */   public int getAmount() {
/* 195 */     return this.item.getAmount();
/*     */   }
/*     */   
/*     */   public CompoundTag getNBT() {
/* 199 */     return MythicMobs.inst().getVolatileCodeHandler().getItemHandler().getNBTData(this.item);
/*     */   }
/*     */   
/*     */   public ItemStack build() {
/* 203 */     if (this.mythicItem != null) {
/* 204 */       this.item = BukkitAdapter.adapt(this.mythicItem.generateItemStack(1));
/*     */     }
/* 206 */     return this.item.clone();
/*     */   }
/*     */   
/*     */   private void checkItemMeta() {
/* 210 */     if (!this.item.hasItemMeta()) {
/* 211 */       this.item.setItemMeta(Bukkit.getItemFactory().getItemMeta(this.item.getType()));
/*     */     }
/*     */   }
/*     */   
/*     */   public io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack clone() {
/* 216 */     return new io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack(this.item.clone());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 221 */     return "BukkitItemStack{" + this.item.toString() + "}";
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */