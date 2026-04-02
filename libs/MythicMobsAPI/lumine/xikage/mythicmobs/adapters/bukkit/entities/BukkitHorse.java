/*     */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileMaterial;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Horse;
/*     */ import org.bukkit.inventory.HorseInventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class BukkitHorse
/*     */   extends MythicEntity {
/*     */   private static final int height = 2;
/*     */   private AgeableProperty ageableProperty;
/*     */   private String horseStyle;
/*     */   private String horseType;
/*     */   private String horseColor;
/*     */   private String horseArmor;
/*     */   private boolean horseChest;
/*     */   private boolean horseSaddled;
/*     */   private boolean horseTamed;
/*     */   
/*     */   public void instantiate(MythicConfig mc) {
/*  30 */     this.ageableProperty = new AgeableProperty(mc);
/*     */     
/*  32 */     this.horseArmor = mc.getString("Options.HorseArmor");
/*  33 */     this.horseChest = mc.getBoolean("Options.HorseCarryingChest", false);
/*  34 */     this.horseStyle = mc.getString("Options.HorseStyle");
/*  35 */     this.horseType = mc.getString("Options.HorseType");
/*     */     
/*  37 */     this.horseColor = mc.getString("Options.HorseColor");
/*  38 */     this.horseColor = mc.getString("Options.Color", this.horseColor);
/*     */     
/*  40 */     this.horseSaddled = mc.getBoolean("Options.HorseSaddled", false);
/*  41 */     this.horseSaddled = mc.getBoolean("Options.Saddled", this.horseSaddled);
/*     */     
/*  43 */     this.horseTamed = mc.getBoolean("Options.HorseTamed", false);
/*  44 */     this.horseTamed = mc.getBoolean("Options.Tamed", this.horseTamed);
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(MythicMob mm, Location location) {
/*  49 */     Entity e = location.getWorld().spawnEntity(location, EntityType.HORSE);
/*     */     
/*  51 */     e = applyOptions(e);
/*     */     
/*  53 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(Location location) {
/*  58 */     Entity e = location.getWorld().spawnEntity(location, EntityType.HORSE);
/*     */     
/*  60 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity applyOptions(Entity entity) {
/*  65 */     Horse e = (Horse)entity;
/*     */     
/*  67 */     this.ageableProperty.applyProperties(entity);
/*     */     
/*  69 */     HorseInventory hi = e.getInventory();
/*     */     
/*  71 */     if (this.horseStyle != null) {
/*  72 */       e.setStyle(Horse.Style.valueOf(this.horseStyle.toUpperCase()));
/*     */     }
/*  74 */     if (MythicMobs.inst().getMinecraftVersion() < 11 && this.horseType != null) {
/*  75 */       e.setVariant(Horse.Variant.valueOf(this.horseType.toUpperCase()));
/*     */     }
/*  77 */     if (this.horseColor != null) {
/*     */       try {
/*  79 */         e.setColor(Horse.Color.valueOf(this.horseColor.toUpperCase()));
/*  80 */       } catch (Exception ex) {
/*  81 */         MythicMobs.error("invalid horse color");
/*     */       } 
/*     */     }
/*  84 */     if (this.horseTamed) {
/*  85 */       e.setTamed(true);
/*     */     }
/*  87 */     if (this.horseSaddled) {
/*  88 */       hi.setSaddle(new ItemStack(Material.SADDLE, 1, (short)0));
/*     */     }
/*  90 */     if (this.horseArmor != null)
/*  91 */     { switch (this.horseArmor)
/*     */       { case "diamond":
/*  93 */           hi.setArmor(new ItemStack(VolatileMaterial.DIAMOND_HORSE_ARMOR, 1, (short)0));
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
/* 104 */           return (Entity)e;case "gold": case "golden": hi.setArmor(new ItemStack(VolatileMaterial.GOLDEN_HORSE_ARMOR, 1, (short)0)); return (Entity)e; }  hi.setArmor(new ItemStack(VolatileMaterial.IRON_HORSE_ARMOR, 1, (short)0)); }  return (Entity)e;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compare(Entity e) {
/* 109 */     return e instanceof Horse;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 114 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */