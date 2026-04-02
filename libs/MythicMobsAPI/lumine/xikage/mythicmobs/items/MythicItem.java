/*     */ package lumine.xikage.mythicmobs.items;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*     */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.items.AttributeHandler;
/*     */ import io.lumine.xikage.mythicmobs.items.LegacyItemConverter;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*     */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTagBuilder;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileMaterial;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.regex.Matcher;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.FireworkEffect;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Banner;
/*     */ import org.bukkit.block.banner.Pattern;
/*     */ import org.bukkit.block.banner.PatternType;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.BannerMeta;
/*     */ import org.bukkit.inventory.meta.BlockStateMeta;
/*     */ import org.bukkit.inventory.meta.EnchantmentStorageMeta;
/*     */ import org.bukkit.inventory.meta.FireworkEffectMeta;
/*     */ import org.bukkit.inventory.meta.FireworkMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.bukkit.inventory.meta.PotionMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class MythicItem implements Comparable<MythicItem> {
/*     */   private final MythicConfig config;
/*     */   private BukkitItemStack itemStack;
/*     */   private final String internalName;
/*     */   private final String file;
/*     */   @Deprecated
/*  57 */   private int data = 0; private String displayName; private String id; private Material material; private int iid; private boolean useiid = false;
/*  58 */   private int amount = 1; private int customModelData; private String color; private String player; private String skinURL;
/*     */   private String skinTexture;
/*     */   private UUID skinUUID;
/*     */   private List<PlaceholderString> lore;
/*     */   private List<String> enchantments;
/*     */   private List<String> potionEffects;
/*     */   private List<String> bannerLayers;
/*     */   private List<String> fireworkColors;
/*     */   private List<String> fireworkFadeColors;
/*     */   private double speed;
/*     */   private double damage;
/*     */   private double knock;
/*     */   private double health;
/*     */   private double range;
/*  72 */   private Map<String, Map<String, String>> itemNBT = new HashMap<>();
/*  73 */   private Map<String, Map<String, Object>> itemAttributes = new HashMap<>();
/*  74 */   private Map<String, Object> itemOptions = new HashMap<>();
/*  75 */   private List<String> hideOptions = new ArrayList<>();
/*     */   private boolean unbreakable;
/*     */   private boolean hideFlags;
/*     */   
/*     */   public MythicItem(String file, String internalName, MythicConfig mc) {
/*  80 */     this.config = mc;
/*  81 */     this.file = file;
/*  82 */     this.internalName = internalName;
/*     */     
/*  84 */     this.data = mc.getInteger("Data", 0);
/*  85 */     this.data = mc.getInteger("Durability", this.data);
/*  86 */     this.data = mc.getInteger("Options.Durability", this.data);
/*     */     
/*  88 */     if (mc.isSet("ItemStack")) {
/*  89 */       ItemStack is = mc.getItemStack("ItemStack", null);
/*     */       
/*  91 */       if (is != null) {
/*  92 */         this.itemStack = new BukkitItemStack(is);
/*     */         
/*     */         try {
/*  95 */           this.displayName = is.getItemMeta().getDisplayName();
/*  96 */           this.lore = new ArrayList<>();
/*     */           
/*  98 */           for (String s : is.getItemMeta().getLore()) {
/*  99 */             this.lore.add(PlaceholderString.of(s));
/*     */           }
/* 101 */         } catch (Exception ex) {
/* 102 */           MythicMobs.error("Couldn't get name/lore of itemstack");
/*     */         } 
/*     */         return;
/*     */       } 
/* 106 */       this.itemStack = new BukkitItemStack(Material.STONE);
/*     */     } else {
/*     */       
/* 109 */       this.id = mc.getString("Id", "STONE").toUpperCase();
/*     */       
/*     */       try {
/* 112 */         if (MythicMobs.inst().getMinecraftVersion() >= 13) {
/* 113 */           this.material = Material.AIR;
/*     */           try {
/* 115 */             this.material = (this.data == 0) ? Material.valueOf(this.id) : LegacyItemConverter.getMaterial(this.id, (byte)this.data);
/*     */             
/* 117 */             if (this.material == null && this.data > 0) {
/* 118 */               this.material = Material.valueOf(this.id);
/*     */             }
/* 120 */           } catch (Exception ex) {
/* 121 */             this.material = LegacyItemConverter.getMaterial(this.id, (byte)this.data);
/*     */             
/* 123 */             if (this.material == null && this.data > 0) {
/* 124 */               this.material = Material.valueOf(this.id);
/*     */             }
/*     */           } finally {
/* 127 */             this.itemStack = new BukkitItemStack(this.material);
/*     */           }
/*     */         
/* 130 */         } else if (this.id.matches("[0-9]*")) {
/* 131 */           this.material = LegacyItemConverter.getMaterial(this.id, (byte)0);
/* 132 */           this.itemStack = (new BukkitItemStack(this.material)).data((byte)this.data).amount(this.amount * this.amount);
/*     */         } else {
/* 134 */           this.material = Material.valueOf(this.id);
/* 135 */           this.itemStack = (new BukkitItemStack(this.material)).data((byte)this.data).amount(this.amount * this.amount);
/*     */         }
/*     */       
/* 138 */       } catch (Exception ex) {
/* 139 */         this.material = Material.STONE;
/* 140 */         this.itemStack = new BukkitItemStack(this.material);
/* 141 */         MythicLogger.errorItemConfig(this, mc, "Material type '" + this.id + "' not found");
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     this.amount = mc.getInteger("Amount", 1);
/* 146 */     this.displayName = SkillString.parseMessageSpecialChars(mc.getColorString("Display"));
/*     */     
/* 148 */     this.lore = mc.getPlaceholderStringList("Lore");
/* 149 */     this.enchantments = mc.getStringList("Enchantments");
/* 150 */     this.potionEffects = mc.getStringList("PotionEffects");
/* 151 */     this.bannerLayers = mc.getStringList("BannerLayers");
/*     */     
/* 153 */     this.fireworkColors = mc.getStringList("Firework.Colors");
/* 154 */     this.fireworkFadeColors = mc.getStringList("Firework.FadeColors");
/*     */     
/* 156 */     this.speed = mc.getDouble("Options.MovementSpeed", 0.0D);
/* 157 */     this.damage = mc.getDouble("Options.Damage", -1.0D);
/* 158 */     this.health = mc.getDouble("Options.Health", 0.0D);
/* 159 */     this.range = mc.getDouble("Options.FollowRange", 0.0D);
/* 160 */     this.knock = mc.getDouble("Options.KnockbackResistance", 0.0D);
/* 161 */     this.color = mc.getString("Options.Color");
/* 162 */     this.player = mc.getString("Options.Player");
/* 163 */     this.skinURL = mc.getString("Options.SkinURL");
/* 164 */     this.skinTexture = mc.getString("Options.SkinTexture");
/*     */     
/* 166 */     if (this.skinTexture != null) {
/* 167 */       this.skinUUID = MythicUtil.getUUIDFromString(this.skinTexture);
/* 168 */     } else if (this.skinURL != null) {
/* 169 */       this.skinUUID = MythicUtil.getUUIDFromString(this.skinURL);
/*     */     } 
/*     */     
/* 172 */     this.unbreakable = mc.getBoolean("Options.Indestructable", false);
/* 173 */     this.unbreakable = mc.getBoolean("Options.Unbreakable", this.unbreakable);
/*     */     
/* 175 */     this.hideFlags = mc.getBoolean("Options.HideFlags", false);
/*     */     
/* 177 */     this.customModelData = mc.getInteger("Options.Model", -1);
/* 178 */     this.customModelData = mc.getInteger("Options.CustomModelData", this.customModelData);
/* 179 */     this.customModelData = mc.getInteger("Model", this.customModelData);
/* 180 */     this.customModelData = mc.getInteger("CustomModelData", this.customModelData);
/*     */     
/* 182 */     if (this.customModelData > -1) {
/* 183 */       this.itemOptions.put("CustomModelData", Integer.valueOf(this.customModelData));
/*     */     }
/* 185 */     if (this.unbreakable) {
/* 186 */       this.itemOptions.put("Unbreakable", Byte.valueOf((byte)1));
/*     */     }
/* 188 */     if (this.hideFlags) {
/* 189 */       this.itemOptions.put("HideFlags", Integer.valueOf(63));
/*     */     }
/* 191 */     int repairCost = mc.getInteger("Options.RepairCost", -1);
/* 192 */     if (repairCost >= 0) {
/* 193 */       this.itemOptions.put("RepairCost", Integer.valueOf(repairCost));
/*     */     }
/* 195 */     boolean repairable = mc.getBoolean("Options.Repairable", true);
/* 196 */     if (!repairable) {
/* 197 */       this.itemOptions.put("RepairCost", Integer.valueOf(2147483647));
/*     */     }
/*     */     
/* 200 */     List<String> hideOptions = mc.getStringList("Hide");
/* 201 */     if (hideOptions != null) {
/* 202 */       for (String hide : hideOptions) {
/* 203 */         String f = "HIDE_" + hide.toUpperCase();
/* 204 */         this.hideOptions.add(f);
/*     */       } 
/*     */     }
/*     */     
/* 208 */     boolean preventStacking = mc.getBoolean("Options.PreventStacking", false);
/* 209 */     if (preventStacking) {
/* 210 */       this.itemOptions.put("PreventStacking", Boolean.valueOf(true));
/*     */     }
/*     */     
/* 213 */     if (mc.isSet("NBT")) {
/*     */       try {
/* 215 */         for (String baseKey : mc.getKeys("NBT"))
/*     */         {
/* 217 */           Map<String, String> tags = new HashMap<>();
/*     */           
/* 219 */           if (mc.isConfigurationSection("NBT." + baseKey)) {
/* 220 */             for (String key : mc.getKeys("NBT." + baseKey)) {
/*     */               
/* 222 */               if (key.equals("@Denizen")) {
/* 223 */                 key = "Denizen NBT";
/*     */               }
/*     */               
/* 226 */               tags.put(key, mc.getString("NBT." + baseKey + "." + key));
/*     */             } 
/* 228 */             this.itemNBT.put(baseKey, tags); continue;
/*     */           } 
/* 230 */           for (String key : mc.getKeys("NBT")) {
/* 231 */             tags.put(key, mc.getString("NBT." + key));
/*     */           }
/* 233 */           this.itemNBT.put("Base", tags);
/*     */         }
/*     */       
/*     */       }
/* 237 */       catch (Exception ex) {
/* 238 */         MythicLogger.errorItemConfig(this, mc, "NBT tags are configured incorrectly.");
/*     */       } 
/*     */     }
/*     */     
/* 242 */     if (mc.isSet("Attributes")) {
/*     */       try {
/* 244 */         for (String key : mc.getKeys("Attributes")) {
/*     */ 
/*     */           
/* 247 */           Map<String, Object> attr = new HashMap<>();
/*     */           
/* 249 */           for (String key2 : mc.getKeys("Attributes." + key)) {
/*     */             
/* 251 */             String value = mc.getString("Attributes." + key + "." + key2);
/*     */             
/* 253 */             String attri = MythicMobs.inst().getItemManager().getItemAttribute(key2);
/*     */             try {
/* 255 */               double d = Double.valueOf(value).doubleValue();
/* 256 */               attr.put(attri, Double.valueOf(d));
/* 257 */             } catch (Exception ex) {
/*     */               
/* 259 */               attr.put(attri, value);
/*     */             } 
/*     */           } 
/* 262 */           this.itemAttributes.put(key.toLowerCase(), attr);
/*     */         } 
/* 264 */       } catch (Exception ex) {
/* 265 */         MythicLogger.errorItemConfig(this, mc, "Attributes are configured incorrectly.");
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInternalName() {
/* 276 */     return this.internalName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MythicConfig getConfig() {
/* 285 */     return this.config;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFile() {
/* 293 */     return this.file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDisplayName() {
/* 301 */     return this.displayName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getLore() {
/* 309 */     List<String> ret = new ArrayList<>();
/* 310 */     for (PlaceholderString pl : this.lore) {
/* 311 */       ret.add(pl.toString());
/*     */     }
/* 313 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Material getMaterial() {
/* 321 */     return this.material;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String getMaterialName() {
/* 330 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getMaterialData() {
/* 339 */     return this.data;
/*     */   }
/*     */   
/*     */   public int getAmount() {
/* 343 */     return this.amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractItemStack generateItemStack(int amount) {
/* 352 */     return generateItemStack(null, amount);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public AbstractItemStack generateItemStack(int amount, AbstractEntity dropper, AbstractEntity trigger) {
/* 357 */     return generateItemStack(null, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractItemStack generateItemStack(DropMetadata meta, int amount) {
/*     */     AbstractEntity dropper, trigger;
/* 365 */     if (meta != null) {
/* 366 */       dropper = meta.getDropper().isPresent() ? ((SkillCaster)meta.getDropper().get()).getEntity() : null;
/* 367 */       trigger = meta.getCause().isPresent() ? meta.getCause().get() : null;
/*     */     } else {
/* 369 */       dropper = null;
/* 370 */       trigger = null;
/*     */     } 
/*     */     
/* 373 */     Entity d = (dropper == null) ? null : BukkitAdapter.adapt(dropper);
/* 374 */     Entity k = (trigger == null) ? null : BukkitAdapter.adapt(trigger);
/*     */     
/* 376 */     ItemStack stack = this.itemStack.build();
/*     */     
/* 378 */     stack.setAmount(stack.getAmount() * amount);
/*     */     
/* 380 */     if (this.itemNBT.size() > 0) {
/*     */       try {
/* 382 */         for (Map.Entry<String, Map<String, String>> entry : this.itemNBT.entrySet()) {
/* 383 */           stack = addItemNBT(stack, entry.getKey(), entry.getValue());
/*     */         }
/* 385 */       } catch (Exception ex) {
/* 386 */         MythicLogger.errorItemConfig(this, this.config, "Failed to add Item NBT - is this version supported?");
/*     */       } 
/*     */     }
/*     */     
/* 390 */     ItemMeta im = stack.getItemMeta();
/* 391 */     if (im == null) {
/* 392 */       im = Bukkit.getItemFactory().getItemMeta(stack.getType());
/*     */     }
/*     */     
/* 395 */     ArrayList<String> parsedLore = new ArrayList<>();
/*     */     
/* 397 */     if (this.lore != null && this.lore.size() > 0) {
/* 398 */       for (PlaceholderString pl : this.lore) {
/* 399 */         String str = pl.get((PlaceholderMeta)meta);
/* 400 */         str = SkillString.parseMessageSpecialChars(str);
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
/* 430 */         if (str.contains("{")) {
/* 431 */           Matcher pMatcher = Patterns.LoreRanges.matcher(str);
/* 432 */           while (pMatcher.find()) {
/* 433 */             int min = Integer.parseInt(pMatcher.group(1));
/* 434 */             int max = Integer.parseInt(pMatcher.group(2));
/* 435 */             int num = MythicMobs.r.nextInt(max - min) + min;
/* 436 */             str = str.replace(pMatcher.group(0), "" + num);
/*     */           } 
/*     */         } 
/*     */         
/* 440 */         parsedLore.add(str);
/*     */       } 
/* 442 */       im.setLore(parsedLore);
/*     */     } 
/*     */     
/*     */     try {
/* 446 */       Class.forName("org.bukkit.inventory.meta.BannerMeta");
/* 447 */       if (im instanceof BannerMeta) {
/* 448 */         im = buildBanner(im);
/*     */       }
/* 450 */     } catch (ClassNotFoundException classNotFoundException) {}
/*     */ 
/*     */     
/* 453 */     try { if (stack.getType().equals(Material.SHIELD)) {
/* 454 */         im = buildShield(im);
/*     */       } }
/* 456 */     catch (Exception exception) {  } catch (Error error) {}
/*     */ 
/*     */     
/* 459 */     if (stack.getType() == Material.POTION) {
/* 460 */       im = buildPotion(im);
/* 461 */     } else if (MythicMobs.inst().getMinecraftVersion() > 8 && (
/* 462 */       stack.getType() == Material.SPLASH_POTION || stack
/* 463 */       .getType() == Material.LINGERING_POTION || stack
/* 464 */       .getType() == Material.TIPPED_ARROW)) {
/* 465 */       im = buildPotion(im);
/*     */     } 
/*     */ 
/*     */     
/* 469 */     if (stack.getType().equals(VolatileMaterial.PLAYER_HEAD) || stack.getType().equals(VolatileMaterial.PLAYER_WALL_HEAD)) {
/* 470 */       im = buildSkull((SkullMeta)im, this);
/*     */     }
/*     */     
/* 473 */     if (stack.getType().equals(VolatileMaterial.FIREWORK_ROCKET)) {
/* 474 */       im = buildFirework(im);
/*     */     }
/*     */     
/* 477 */     if (stack.getType().equals(VolatileMaterial.FIREWORK_STAR)) {
/* 478 */       im = buildFireworkCharge(im);
/*     */     }
/*     */     
/* 481 */     if (meta instanceof MapMeta && this.data > 0) {
/* 482 */       ((MapMeta)meta).setMapId(this.data);
/*     */     }
/*     */     
/*     */     try {
/* 486 */       if (this.displayName != null) {
/* 487 */         im.setDisplayName(this.displayName);
/*     */       }
/* 489 */     } catch (Exception exception) {}
/*     */     
/* 491 */     if (this.hideOptions != null) {
/* 492 */       for (String hide : this.hideOptions) {
/* 493 */         ItemFlag flag = ItemFlag.valueOf(hide);
/* 494 */         im.addItemFlags(new ItemFlag[] { flag });
/*     */       } 
/*     */     }
/*     */     
/* 498 */     stack.setItemMeta(im);
/*     */ 
/*     */     
/* 501 */     if (this.data != 0) {
/* 502 */       stack.setDurability((short)this.data);
/*     */     }
/*     */     
/* 505 */     if (CompatibilityManager.EnchantsPlus != null) {
/* 506 */       CompatibilityManager.EnchantsPlus.setEnchants(stack, this.enchantments);
/*     */     }
/*     */     
/* 509 */     if (this.enchantments != null) {
/* 510 */       stack = setEnchants(stack, this.enchantments);
/*     */     }
/*     */     
/* 513 */     if (this.color != null) {
/* 514 */       stack = setLeatherColor(stack, this);
/*     */     }
/*     */     
/* 517 */     if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 518 */       stack = MythicMobs.inst().getVolatileCodeHandler().setItemAttributes(stack, this.internalName, this.itemOptions, this.itemAttributes);
/*     */     } else {
/* 520 */       if (this.health != 0.0D) {
/* 521 */         stack = AttributeHandler.addHealth(this.internalName, stack, this.health);
/*     */       }
/* 523 */       if (this.damage > -1.0D) {
/* 524 */         stack = AttributeHandler.addDamage(this.internalName, stack, this.damage);
/*     */       }
/* 526 */       if (this.speed != 0.0D) {
/* 527 */         stack = AttributeHandler.addSpeed(this.internalName, stack, this.speed);
/*     */       }
/* 529 */       if (this.knock != 0.0D) {
/* 530 */         stack = AttributeHandler.addKnockBackRes(this.internalName, stack, this.knock);
/*     */       }
/* 532 */       if (this.range != 0.0D) {
/* 533 */         stack = AttributeHandler.addFollowRange(this.internalName, stack, this.range);
/*     */       }
/*     */       
/* 536 */       ItemMeta m = stack.getItemMeta();
/*     */       
/* 538 */       if (this.unbreakable) {
/* 539 */         if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 540 */           m.setUnbreakable(true);
/*     */         } else {
/* 542 */           stack = MythicMobs.inst().getVolatileCodeHandler().setItemUnbreakable(stack);
/*     */         } 
/*     */       }
/*     */       
/* 546 */       if (this.hideFlags) {
/* 547 */         if (MythicMobs.inst().getMinecraftVersion() >= 8) {
/* 548 */           m.addItemFlags(ItemFlag.values());
/*     */         } else {
/* 550 */           stack = MythicMobs.inst().getVolatileCodeHandler().setItemHideFlags(stack);
/*     */         } 
/*     */       }
/*     */       
/* 554 */       stack.setItemMeta(m);
/*     */     } 
/*     */     
/* 557 */     return BukkitAdapter.adapt(stack);
/*     */   }
/*     */   
/*     */   public static ItemStack addItemNBT(ItemStack itemStack, String baseKey, Map<String, String> pairs) {
/* 561 */     if (itemStack == null || itemStack.getType() == Material.AIR) {
/* 562 */       return null;
/*     */     }
/* 564 */     CompoundTag compoundTag = MythicMobs.inst().getVolatileCodeHandler().getItemHandler().getNBTData(itemStack);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 570 */     if (baseKey.equals("Base")) {
/*     */       
/* 572 */       CompoundTagBuilder builder = compoundTag.createBuilder();
/*     */       
/* 574 */       for (Map.Entry<String, String> entry : pairs.entrySet()) {
/* 575 */         builder.putString(entry.getKey(), entry.getValue());
/*     */       }
/*     */       
/* 578 */       compoundTag = builder.build();
/*     */     } else {
/* 580 */       if (compoundTag.getValue().containsKey(baseKey)) {
/* 581 */         newTag = (CompoundTag)compoundTag.getValue().get(baseKey);
/*     */       } else {
/* 583 */         newTag = MythicMobs.inst().getVolatileCodeHandler().createCompoundTag(new HashMap<>());
/*     */       } 
/*     */       
/* 586 */       CompoundTagBuilder builder = newTag.createBuilder();
/*     */       
/* 588 */       for (Map.Entry<String, String> entry : pairs.entrySet()) {
/* 589 */         builder.putString(entry.getKey(), entry.getValue());
/*     */       }
/*     */       
/* 592 */       CompoundTag newTag = builder.build();
/* 593 */       compoundTag = compoundTag.createBuilder().put(baseKey, (Tag)newTag).build();
/*     */     } 
/*     */     
/* 596 */     return MythicMobs.inst().getVolatileCodeHandler().getItemHandler().setNBTData(itemStack, compoundTag);
/*     */   }
/*     */ 
/*     */   
/*     */   private static ItemStack setEnchants(ItemStack stack, List<String> enchants) {
/* 601 */     if (enchants == null) return stack; 
/* 602 */     for (String s : enchants) {
/* 603 */       if (s.contains(":")) {
/* 604 */         String[] part = s.split(":");
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
/* 615 */         Enchantment en = Enchantment.getByName(part[0]);
/* 616 */         if (en == null || (
/* 617 */           MythicMobs.inst().getCompatibility().getArtifacts().isPresent() && (
/* 618 */           (ArtifactsSupport)MythicMobs.inst().getCompatibility().getArtifacts().get()).handleEnchant(stack, en, Integer.parseInt(part[1])))) {
/*     */           continue;
/*     */         }
/*     */         
/* 622 */         if (stack.getType() != Material.ENCHANTED_BOOK) {
/* 623 */           stack.addUnsafeEnchantment(en, Integer.parseInt(part[1])); continue;
/*     */         } 
/* 625 */         EnchantmentStorageMeta esm = (EnchantmentStorageMeta)stack.getItemMeta();
/* 626 */         esm.addStoredEnchant(en, Integer.parseInt(part[1]), true);
/* 627 */         stack.setItemMeta((ItemMeta)esm);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 634 */     return stack;
/*     */   }
/*     */   
/*     */   private ItemMeta buildPotion(ItemMeta im) {
/* 638 */     PotionMeta pm = (PotionMeta)im;
/*     */     
/* 640 */     if (this.color != null) {
/*     */       try {
/* 642 */         String[] rgb = this.color.split(",");
/* 643 */         int r = Integer.parseInt(rgb[0]);
/* 644 */         int g = Integer.parseInt(rgb[1]);
/* 645 */         int b = Integer.parseInt(rgb[2]);
/* 646 */         pm.setColor(Color.fromRGB(r, g, b));
/* 647 */       } catch (Exception ex) {
/* 648 */         ex.printStackTrace();
/*     */       } 
/*     */     }
/* 651 */     if (this.potionEffects != null) {
/* 652 */       pm.clearCustomEffects();
/* 653 */       for (String s : this.potionEffects) {
/*     */         try {
/* 655 */           String[] sp = s.split(" ");
/* 656 */           if (sp.length < 3) {
/* 657 */             MythicMobs.error("A potion effect for MythicItem " + this.internalName + " is configured incorrectly. Format is 'EFFECT DURATION LEVEL'. Line=" + s);
/*     */             
/*     */             continue;
/*     */           } 
/* 661 */           PotionEffectType pet = PotionEffectType.getByName(sp[0].toUpperCase());
/* 662 */           int duration = Integer.parseInt(sp[1]);
/* 663 */           int level = Integer.parseInt(sp[2]);
/*     */           
/* 665 */           PotionEffect p = new PotionEffect(pet, duration, level - 1, true);
/*     */           
/* 667 */           pm.addCustomEffect(p, false);
/*     */         
/*     */         }
/* 670 */         catch (Exception ex) {
/* 671 */           MythicMobs.error("A potion effect for MythicItem " + this.internalName + " is configured incorrectly. Format is 'EFFECT DURATION LEVEL'. Line=" + s);
/*     */         } 
/*     */       } 
/*     */     } 
/* 675 */     return im;
/*     */   }
/*     */   
/*     */   private ItemMeta buildFirework(ItemMeta im) {
/* 679 */     FireworkMeta pm = (FireworkMeta)im;
/*     */     
/* 681 */     FireworkEffect.Builder builder = FireworkEffect.builder();
/*     */     
/* 683 */     if (this.fireworkColors != null) {
/* 684 */       for (String s : this.fireworkColors) {
/*     */         try {
/* 686 */           String[] rgb = s.split(",");
/* 687 */           int r = Integer.parseInt(rgb[0]);
/* 688 */           int g = Integer.parseInt(rgb[1]);
/* 689 */           int b = Integer.parseInt(rgb[2]);
/* 690 */           builder.withColor(Color.fromRGB(r, g, b));
/* 691 */         } catch (Exception ex) {
/* 692 */           MythicMobs.error("A firework color for MythicItem " + this.internalName + " is configured incorrectly. Format is 'red,green,blue'. Line=" + s);
/*     */         } 
/*     */       } 
/*     */     }
/* 696 */     if (this.fireworkFadeColors != null) {
/* 697 */       for (String s : this.fireworkFadeColors) {
/*     */         try {
/* 699 */           String[] rgb = s.split(",");
/* 700 */           int r = Integer.parseInt(rgb[0]);
/* 701 */           int g = Integer.parseInt(rgb[1]);
/* 702 */           int b = Integer.parseInt(rgb[2]);
/* 703 */           builder.withColor(Color.fromRGB(r, g, b));
/* 704 */         } catch (Exception ex) {
/* 705 */           MythicMobs.error("A firework fade color for MythicItem " + this.internalName + " is configured incorrectly. Format is 'red,green,blue'. Line=" + s);
/*     */         } 
/*     */       } 
/*     */     }
/* 709 */     if (this.config.getBoolean("Firework.Flicker", false)) {
/* 710 */       builder.withFlicker();
/*     */     }
/* 712 */     if (this.config.getBoolean("Firework.Trail", false)) {
/* 713 */       builder.withTrail();
/*     */     }
/* 715 */     pm.addEffect(builder.build());
/*     */     
/* 717 */     return im;
/*     */   }
/*     */   
/*     */   private ItemMeta buildFireworkCharge(ItemMeta im) {
/* 721 */     FireworkEffectMeta pm = (FireworkEffectMeta)im;
/*     */     
/* 723 */     FireworkEffect.Builder builder = FireworkEffect.builder();
/*     */     
/* 725 */     if (this.fireworkColors != null) {
/* 726 */       for (String s : this.fireworkColors) {
/*     */         try {
/* 728 */           String[] rgb = s.split(",");
/* 729 */           int r = Integer.parseInt(rgb[0]);
/* 730 */           int g = Integer.parseInt(rgb[1]);
/* 731 */           int b = Integer.parseInt(rgb[2]);
/* 732 */           builder.withColor(Color.fromRGB(r, g, b));
/* 733 */         } catch (Exception ex) {
/* 734 */           MythicMobs.error("A firework color for MythicItem " + this.internalName + " is configured incorrectly. Format is 'red,green,blue'. Line=" + s);
/*     */         } 
/*     */       } 
/*     */     }
/* 738 */     if (this.fireworkFadeColors != null) {
/* 739 */       for (String s : this.fireworkFadeColors) {
/*     */         try {
/* 741 */           String[] rgb = s.split(",");
/* 742 */           int r = Integer.parseInt(rgb[0]);
/* 743 */           int g = Integer.parseInt(rgb[1]);
/* 744 */           int b = Integer.parseInt(rgb[2]);
/* 745 */           builder.withColor(Color.fromRGB(r, g, b));
/* 746 */         } catch (Exception ex) {
/* 747 */           MythicMobs.error("A firework fade color for MythicItem " + this.internalName + " is configured incorrectly. Format is 'red,green,blue'. Line=" + s);
/*     */         } 
/*     */       } 
/*     */     }
/* 751 */     if (this.config.getBoolean("Firework.Flicker", false)) {
/* 752 */       builder.withFlicker();
/*     */     }
/* 754 */     if (this.config.getBoolean("Firework.Trail", false)) {
/* 755 */       builder.withTrail();
/*     */     }
/* 757 */     pm.setEffect(builder.build());
/*     */     
/* 759 */     return im;
/*     */   }
/*     */   
/*     */   private ItemMeta buildBanner(ItemMeta im) {
/* 763 */     if (this.color != null) {
/*     */       try {
/* 765 */         ((BannerMeta)im).setBaseColor(DyeColor.valueOf(this.color));
/* 766 */       } catch (Exception ex) {
/* 767 */         MythicMobs.error("The banner color for MythicItem " + this.internalName + " is configured incorrectly, must use a color from the Bukkit DyeColor ENUM.");
/*     */       } 
/*     */     }
/* 770 */     if (this.bannerLayers != null) {
/* 771 */       for (String s : this.bannerLayers) {
/*     */         try {
/* 773 */           String[] sp = s.split(" ");
/* 774 */           if (sp.length < 2) {
/* 775 */             MythicMobs.error("A banner layer for MythicItem " + this.internalName + " is configured incorrectly. Format is 'COLOR PATTERN'. Line=" + s);
/*     */             
/*     */             continue;
/*     */           } 
/* 779 */           DyeColor dc = DyeColor.valueOf(sp[0].toUpperCase());
/* 780 */           PatternType pt = PatternType.valueOf(sp[1]);
/*     */           
/* 782 */           Pattern p = new Pattern(dc, pt);
/*     */           
/* 784 */           ((BannerMeta)im).addPattern(p);
/* 785 */         } catch (Exception ex) {
/* 786 */           MythicMobs.error("A banner layer for MythicItem " + this.internalName + " is configured incorrectly. Format is 'COLOR PATTERN'. Line=" + s);
/*     */         } 
/*     */       } 
/*     */     }
/* 790 */     return im;
/*     */   }
/*     */ 
/*     */   
/*     */   private ItemMeta buildShield(ItemMeta im) {
/* 795 */     BlockStateMeta meta = (BlockStateMeta)im;
/* 796 */     Banner banner = (Banner)meta.getBlockState();
/* 797 */     if (this.color != null) {
/*     */       try {
/* 799 */         banner.setBaseColor(DyeColor.valueOf(this.color));
/* 800 */       } catch (Exception ex) {
/* 801 */         MythicMobs.error("The shield color for MythicItem " + this.internalName + " is configured incorrectly, must use a color from the Bukkit DyeColor ENUM.");
/*     */       } 
/*     */     }
/* 804 */     if (this.bannerLayers != null) {
/* 805 */       for (String s : this.bannerLayers) {
/*     */         try {
/* 807 */           String[] sp = s.split(" ");
/* 808 */           if (sp.length < 2) {
/* 809 */             MythicMobs.error("A shield banner layer for MythicItem " + this.internalName + " is configured incorrectly. Format is 'COLOR PATTERN'. Line=" + s);
/*     */             
/*     */             continue;
/*     */           } 
/* 813 */           DyeColor dc = DyeColor.valueOf(sp[0].toUpperCase());
/* 814 */           PatternType pt = PatternType.valueOf(sp[1]);
/*     */           
/* 816 */           Pattern p = new Pattern(dc, pt);
/*     */           
/* 818 */           banner.addPattern(p);
/* 819 */         } catch (Exception ex) {
/* 820 */           MythicMobs.error("A shield banner layer for MythicItem " + this.internalName + " is configured incorrectly. Format is 'COLOR PATTERN'. Line=" + s);
/*     */         } 
/*     */       } 
/*     */     }
/* 824 */     banner.update();
/* 825 */     meta.setBlockState((BlockState)banner);
/*     */     
/* 827 */     return (ItemMeta)meta;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ItemStack setLeatherColor(ItemStack item, MythicItem mi) {
/* 832 */     if (item.getType().equals(Material.LEATHER_CHESTPLATE) || item.getType().equals(Material.LEATHER_BOOTS) || item.getType().equals(Material.LEATHER_LEGGINGS) || item.getType().equals(Material.LEATHER_HELMET)) {
/*     */       
/* 834 */       ItemMeta im = item.getItemMeta();
/* 835 */       LeatherArmorMeta la = (LeatherArmorMeta)im;
/* 836 */       if (mi.color.contains(",")) {
/* 837 */         String[] rgb = mi.color.split(",");
/* 838 */         int r = Integer.parseInt(rgb[0]);
/* 839 */         int g = Integer.parseInt(rgb[1]);
/* 840 */         int b = Integer.parseInt(rgb[2]);
/* 841 */         la.setColor(Color.fromRGB(r, g, b));
/*     */       } else {
/* 843 */         DyeColor dColor = DyeColor.valueOf(mi.color);
/* 844 */         la.setColor(dColor.getColor());
/*     */       } 
/* 846 */       item.setItemMeta((ItemMeta)la);
/*     */     } 
/* 848 */     return item;
/*     */   }
/*     */   
/*     */   private static ItemMeta buildSkull(SkullMeta meta, MythicItem mi) {
/* 852 */     if (mi.player != null) {
/* 853 */       meta.setOwner(mi.player);
/* 854 */     } else if (mi.skinTexture != null) {
/* 855 */       GameProfile profile = new GameProfile(mi.skinUUID, null);
/*     */       
/* 857 */       profile.getProperties().put("textures", new Property("textures", mi.skinTexture));
/* 858 */       Field profileField = null;
/*     */       try {
/* 860 */         profileField = meta.getClass().getDeclaredField("profile");
/* 861 */       } catch (NoSuchFieldException|SecurityException e) {
/* 862 */         e.printStackTrace();
/*     */       } 
/* 864 */       profileField.setAccessible(true);
/*     */       try {
/* 866 */         profileField.set(meta, profile);
/* 867 */       } catch (IllegalArgumentException|IllegalAccessException e) {
/* 868 */         e.printStackTrace();
/*     */       } 
/* 870 */     } else if (mi.skinURL != null) {
/* 871 */       GameProfile profile = new GameProfile(UUID.randomUUID(), null);
/*     */       
/* 873 */       profile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + mi.skinURL + "\"}}}")));
/* 874 */       Field profileField = null;
/*     */       try {
/* 876 */         profileField = meta.getClass().getDeclaredField("profile");
/* 877 */       } catch (NoSuchFieldException|SecurityException e) {
/* 878 */         e.printStackTrace();
/*     */       } 
/* 880 */       profileField.setAccessible(true);
/*     */       try {
/* 882 */         profileField.set(meta, profile);
/* 883 */       } catch (IllegalArgumentException|IllegalAccessException e) {
/* 884 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 888 */     return (ItemMeta)meta;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(MythicItem o) {
/* 893 */     return this.internalName.compareTo(o.getInternalName());
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\items\MythicItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */