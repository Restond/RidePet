/*     */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Villager;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.MerchantRecipe;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BukkitVillager
/*     */   extends MythicEntity
/*     */ {
/*     */   private static final int height = 2;
/*  25 */   private int age = 0;
/*     */   
/*     */   private boolean ageLock = false;
/*     */   
/*     */   private Villager.Type villagerType;
/*     */   
/*     */   private Villager.Profession villagerProfession;
/*     */   private int level;
/*     */   private boolean hasTrades = true;
/*     */   private List<MerchantRecipe> trades;
/*     */   
/*     */   public void instantiate(MythicConfig mc) {
/*  37 */     this.age = mc.getInteger("Options.Age", 0);
/*  38 */     this.ageLock = mc.getBoolean("Options.AgeLock", false);
/*     */     
/*  40 */     String strVillagerProfession = mc.getString("Options.Profession", "RANDOM");
/*  41 */     strVillagerProfession = mc.getString("Options.VillagerType", strVillagerProfession);
/*     */     try {
/*  43 */       if (strVillagerProfession.equals("RANDOM")) {
/*  44 */         this.villagerProfession = null;
/*     */       } else {
/*  46 */         this.villagerProfession = Villager.Profession.valueOf(strVillagerProfession);
/*     */       } 
/*  48 */     } catch (Exception ex) {
/*  49 */       MythicLogger.errorEntityConfig(this, mc, "Invalid Villager Profession specified");
/*     */     } 
/*     */     
/*  52 */     if (MythicMobs.inst().getMinecraftVersion() >= 14) {
/*  53 */       String strVillagerType = mc.getString("Options.Type", "RANDOM");
/*     */       try {
/*  55 */         if (strVillagerType.equals("RANDOM")) {
/*  56 */           this.villagerType = null;
/*     */         } else {
/*  58 */           this.villagerType = Villager.Type.valueOf(strVillagerType);
/*     */         } 
/*  60 */       } catch (Exception ex) {
/*  61 */         MythicLogger.errorEntityConfig(this, mc, "Invalid Villager Type specified");
/*     */       } 
/*     */       
/*  64 */       this.level = mc.getInteger("Options.TradingLevel", 1);
/*  65 */       this.level = mc.getInteger("Options.Level", this.level);
/*     */     } 
/*     */     
/*  68 */     this.hasTrades = mc.getBoolean("Options.HasTrades", true);
/*     */     
/*  70 */     if (mc.isSet("Trades")) {
/*  71 */       this.trades = new ArrayList<>();
/*  72 */       for (MythicConfig tradeConf : mc.getNestedConfigs("Trades").values()) {
/*     */         try {
/*  74 */           String strResult = tradeConf.getString("Result", "STONE");
/*  75 */           String strItem1 = tradeConf.getString("Item1", "5 EMERALD");
/*  76 */           String strItem2 = tradeConf.getString("Item2", null);
/*  77 */           int maxUses = tradeConf.getInteger("MaxUses", 10000);
/*     */           
/*  79 */           String[] spResult = strResult.split(" ");
/*  80 */           ItemStack result = (spResult.length == 1) ? (new BukkitItemStack(spResult[0])).build() : (new BukkitItemStack(spResult[1])).build();
/*     */           
/*  82 */           MerchantRecipe recipe = new MerchantRecipe(result, maxUses);
/*     */           
/*  84 */           if (strItem1 != null) {
/*  85 */             String[] spItem1 = strItem1.split(" ");
/*  86 */             ItemStack item1 = (spItem1.length == 1) ? (new BukkitItemStack(spItem1[0])).build() : (new BukkitItemStack(spItem1[1])).build();
/*     */             
/*  88 */             if (spItem1.length == 2) {
/*  89 */               int amount = Integer.valueOf(spItem1[0]).intValue();
/*  90 */               item1.setAmount(amount);
/*     */             } 
/*  92 */             recipe.addIngredient(item1);
/*     */           } 
/*  94 */           if (strItem2 != null) {
/*  95 */             String[] spItem2 = strItem2.split(" ");
/*  96 */             ItemStack item2 = (spItem2.length == 1) ? (new BukkitItemStack(spItem2[0])).build() : (new BukkitItemStack(spItem2[1])).build();
/*     */             
/*  98 */             if (spItem2.length == 2) {
/*  99 */               int amount = Integer.valueOf(spItem2[0]).intValue();
/* 100 */               item2.setAmount(amount);
/*     */             } 
/* 102 */             recipe.addIngredient(item2);
/*     */           } 
/* 104 */           this.trades.add(recipe);
/* 105 */         } catch (Exception ex) {
/* 106 */           MythicLogger.errorEntityConfig(this, mc, "A villager trade is configured incorrectly.");
/* 107 */           ex.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(MythicMob mm, Location location) {
/* 115 */     Entity e = location.getWorld().spawnEntity(location, EntityType.VILLAGER);
/*     */     
/* 117 */     e = applyOptions(e);
/*     */     
/* 119 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(Location location) {
/* 124 */     Entity e = location.getWorld().spawnEntity(location, EntityType.VILLAGER);
/*     */     
/* 126 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity applyOptions(Entity entity) {
/* 131 */     Villager e = (Villager)entity;
/*     */     
/* 133 */     e.setAge(this.age);
/* 134 */     e.setAgeLock(this.ageLock);
/*     */     
/* 136 */     if (MythicMobs.inst().getMinecraftVersion() >= 14) {
/* 137 */       e.setVillagerLevel(this.level);
/* 138 */       if (this.villagerType != null) {
/* 139 */         e.setVillagerType(this.villagerType);
/*     */       }
/*     */     } 
/* 142 */     if (this.villagerProfession != null) {
/* 143 */       e.setProfession(this.villagerProfession);
/*     */     }
/* 145 */     else if (this.trades != null && this.trades.size() > 0) {
/* 146 */       e.setProfession(Villager.Profession.NITWIT);
/*     */     } 
/*     */ 
/*     */     
/* 150 */     if (this.trades != null && this.trades.size() > 0) {
/* 151 */       e.setRecipes(this.trades);
/*     */     }
/* 153 */     else if (!this.hasTrades && 
/* 154 */       MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 155 */       e.setRecipes(null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 160 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compare(Entity e) {
/* 165 */     return e instanceof Villager;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 170 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */