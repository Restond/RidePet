/*     */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.properties.AgeableProperty;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Wolf;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BukkitWolf
/*     */   extends MythicEntity
/*     */ {
/*     */   private static final int height = 1;
/*     */   private AgeableProperty ageableProperty;
/*     */   private boolean angry = false;
/*     */   private DyeColor color;
/*     */   private boolean tameable = false;
/*     */   
/*     */   public void instantiate(MythicConfig mc) {
/*  30 */     this.ageableProperty = new AgeableProperty(mc);
/*  31 */     this.angry = mc.getBoolean("Options.Angry", false);
/*  32 */     this.tameable = mc.getBoolean("Options.Tameable", false);
/*     */     
/*  34 */     String strColor = mc.getString("Options.Color");
/*     */     
/*  36 */     if (strColor != null) {
/*  37 */       if (strColor.matches("[0-9]*")) {
/*  38 */         this.color = DyeColor.getByWoolData(Byte.parseByte(strColor));
/*     */       } else {
/*     */         try {
/*  41 */           this.color = DyeColor.valueOf(strColor.toUpperCase());
/*  42 */         } catch (Exception ex) {
/*  43 */           MythicLogger.errorEntityConfig(this, mc, "Invalid Color specified");
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(MythicMob mm, Location location) {
/*  51 */     Entity e = location.getWorld().spawnEntity(location, EntityType.WOLF);
/*     */     
/*  53 */     e = applyOptions(e);
/*     */     
/*  55 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(Location location) {
/*  60 */     Entity e = location.getWorld().spawnEntity(location, EntityType.WOLF);
/*     */     
/*  62 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity applyOptions(Entity entity) {
/*  67 */     Wolf e = (Wolf)entity;
/*     */     
/*  69 */     e = (Wolf)this.ageableProperty.applyProperties((Entity)e);
/*  70 */     e.setAngry(this.angry);
/*  71 */     if (this.color != null) {
/*  72 */       e.setCollarColor(this.color);
/*     */     }
/*     */     
/*  75 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compare(Entity e) {
/*  80 */     return e instanceof Wolf;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/*  85 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isTameable() {
/*  89 */     return this.tameable;
/*     */   }
/*     */   
/*     */   public AbstractPlayer getOwner(ActiveMob am) {
/*  93 */     Entity e = BukkitAdapter.adapt(am.getEntity());
/*  94 */     if (e instanceof Wolf) {
/*  95 */       Wolf w = (Wolf)e;
/*  96 */       if (w.getOwner() instanceof Player) {
/*  97 */         return BukkitAdapter.adapt((Player)w.getOwner());
/*     */       }
/*     */     } 
/* 100 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */