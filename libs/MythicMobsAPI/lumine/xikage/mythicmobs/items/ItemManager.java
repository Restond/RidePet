/*     */ package lumine.xikage.mythicmobs.items;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.IOHandler;
/*     */ import io.lumine.xikage.mythicmobs.io.IOLoader;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*     */ import java.io.File;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ public class ItemManager
/*     */ {
/*     */   private final MythicMobs core;
/*     */   private List<File> itemFiles;
/*     */   private IOLoader<MythicMobs> defaultItems;
/*     */   private List<IOLoader<MythicMobs>> itemLoaders;
/*  25 */   private ConcurrentHashMap<String, MythicItem> items = new ConcurrentHashMap<>();
/*     */   
/*     */   public ItemManager(MythicMobs core) {
/*  28 */     this.core = core;
/*     */   }
/*     */   
/*     */   public void loadItems() {
/*  32 */     this.defaultItems = new IOLoader((JavaPlugin)MythicMobs.inst(), "ExampleItems.yml", "Items");
/*  33 */     this.itemFiles = IOHandler.getAllFiles(this.defaultItems.getFile().getParent());
/*  34 */     this.itemLoaders = IOHandler.getSaveLoad((JavaPlugin)this.core, this.itemFiles, "Items");
/*  35 */     this.items.clear();
/*     */     
/*  37 */     for (IOLoader<MythicMobs> sl : this.itemLoaders) {
/*  38 */       for (String name : sl.getCustomConfig().getConfigurationSection("").getKeys(false)) {
/*  39 */         MythicConfig mc = new MythicConfig(name, sl.getFile(), sl.getCustomConfig());
/*  40 */         MythicItem mi = new MythicItem(sl.getFile().getName(), name, mc);
/*  41 */         registerItem(name, mi);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Collection<MythicItem> getItems() {
/*  47 */     return this.items.values();
/*     */   }
/*     */   
/*     */   public Collection<String> getItemNames() {
/*  51 */     return this.items.keySet();
/*     */   }
/*     */   
/*     */   public Optional<MythicItem> getItem(String name) {
/*  55 */     return Optional.ofNullable(this.items.getOrDefault(name, null));
/*     */   }
/*     */   
/*     */   public ItemStack getItemStack(String name) {
/*  59 */     Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(name);
/*     */     
/*  61 */     if (maybeItem.isPresent()) {
/*  62 */       return BukkitAdapter.adapt(((MythicItem)maybeItem.get()).generateItemStack(1));
/*     */     }
/*     */     try {
/*  65 */       return new ItemStack(Material.valueOf(name.toUpperCase()));
/*  66 */     } catch (Exception ex) {
/*  67 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean registerItem(String internalName, MythicItem item) {
/*  73 */     if (this.items.containsKey(internalName)) {
/*  74 */       return false;
/*     */     }
/*  76 */     this.items.put(internalName, item);
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemAttribute(String input) {
/*  83 */     switch (input.toUpperCase()) { case "DAMAGE": case "ATTACK_DAMAGE":
/*     */       case "ATTACKDAMAGE":
/*  85 */         attribute = "generic.attackDamage";
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
/* 111 */         return attribute;case "HEALTH": case "MAX_HEALTH": case "MAXHEALTH": case "HP": attribute = "generic.maxHealth"; return attribute;case "MOVEMENT_SPEED": case "MOVEMENTSPEED": case "MOVESPEED": case "RUNSPEED": case "RUN_SPEED": attribute = "generic.movementSpeed"; return attribute;case "ATTACK_SPEED": case "ATTACKSPEED": case "WEAPON_SPEED": case "WEAPONSPEED": attribute = "generic.attackSpeed"; return attribute;case "KNOCKBACK_RESISTANCE": case "KNOCKBACKRESISTANCE": case "KNOCKBACKRESIST": attribute = "generic.knockbackResistance"; return attribute;case "ARMOR": attribute = "generic.armor"; return attribute;case "ARMOR_TOUGHNESS": case "TOUGHNESS": case "ARMORTOUGHNESS": attribute = "generic.armorToughness"; return attribute;case "LUCK": attribute = "generic.luck"; return attribute; }  String attribute = input; return attribute;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\items\ItemManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */