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
/*     */ import org.bukkit.entity.WanderingTrader;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.MerchantRecipe;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BukkitWanderingTrader
/*     */   extends MythicEntity
/*     */ {
/*     */   private static final int height = 2;
/*  26 */   private int age = 0;
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
/*  38 */     this.age = mc.getInteger("Options.Age", 0);
/*  39 */     this.ageLock = mc.getBoolean("Options.AgeLock", false);
/*     */     
/*  41 */     this.hasTrades = mc.getBoolean("Options.HasTrades", true);
/*     */     
/*  43 */     if (mc.isSet("Trades")) {
/*  44 */       this.trades = new ArrayList<>();
/*  45 */       for (MythicConfig tradeConf : mc.getNestedConfigs("Trades").values()) {
/*     */         try {
/*  47 */           String strResult = tradeConf.getString("Result", "STONE");
/*  48 */           String strItem1 = tradeConf.getString("Item1", "5 EMERALD");
/*  49 */           String strItem2 = tradeConf.getString("Item2", null);
/*  50 */           int maxUses = tradeConf.getInteger("MaxUses", 10000);
/*     */           
/*  52 */           String[] spResult = strResult.split(" ");
/*  53 */           ItemStack result = (spResult.length == 1) ? (new BukkitItemStack(spResult[0])).build() : (new BukkitItemStack(spResult[1])).build();
/*     */           
/*  55 */           MerchantRecipe recipe = new MerchantRecipe(result, maxUses);
/*     */           
/*  57 */           if (strItem1 != null) {
/*  58 */             String[] spItem1 = strItem1.split(" ");
/*  59 */             ItemStack item1 = (spItem1.length == 1) ? (new BukkitItemStack(spItem1[0])).build() : (new BukkitItemStack(spItem1[1])).build();
/*     */             
/*  61 */             if (spItem1.length == 2) {
/*  62 */               int amount = Integer.valueOf(spItem1[0]).intValue();
/*  63 */               item1.setAmount(amount);
/*     */             } 
/*  65 */             recipe.addIngredient(item1);
/*     */           } 
/*  67 */           if (strItem2 != null) {
/*  68 */             String[] spItem2 = strItem2.split(" ");
/*  69 */             ItemStack item2 = (spItem2.length == 1) ? (new BukkitItemStack(spItem2[0])).build() : (new BukkitItemStack(spItem2[1])).build();
/*     */             
/*  71 */             if (spItem2.length == 2) {
/*  72 */               int amount = Integer.valueOf(spItem2[0]).intValue();
/*  73 */               item2.setAmount(amount);
/*     */             } 
/*  75 */             recipe.addIngredient(item2);
/*     */           } 
/*  77 */           this.trades.add(recipe);
/*  78 */         } catch (Exception ex) {
/*  79 */           MythicLogger.errorEntityConfig(this, mc, "A wandering trader trade is configured incorrectly.");
/*  80 */           ex.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(MythicMob mm, Location location) {
/*  88 */     Entity e = location.getWorld().spawnEntity(location, EntityType.WANDERING_TRADER);
/*     */     
/*  90 */     e = applyOptions(e);
/*     */     
/*  92 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(Location location) {
/*  97 */     Entity e = location.getWorld().spawnEntity(location, EntityType.WANDERING_TRADER);
/*     */     
/*  99 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity applyOptions(Entity entity) {
/* 104 */     WanderingTrader e = (WanderingTrader)entity;
/*     */     
/* 106 */     e.setAge(this.age);
/* 107 */     e.setAgeLock(this.ageLock);
/*     */     
/* 109 */     if (this.trades != null && this.trades.size() > 0) {
/* 110 */       e.setRecipes(this.trades);
/*     */     }
/* 112 */     else if (!this.hasTrades && 
/* 113 */       MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 114 */       e.setRecipes(null);
/*     */     } 
/*     */ 
/*     */     
/* 118 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compare(Entity e) {
/* 123 */     return e instanceof WanderingTrader;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 128 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitWanderingTrader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */