/*     */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Husk;
/*     */ import org.bukkit.entity.Zombie;
/*     */ 
/*     */ 
/*     */ public class BukkitHusk
/*     */   extends MythicEntity
/*     */ {
/*     */   private static final int height = 2;
/*  19 */   private double reinforcementChance = -1.0D;
/*     */ 
/*     */   
/*     */   public void instantiate(MythicConfig mc) {
/*  23 */     this.reinforcementChance = mc.getDouble("Options.ReinforcementsChance", -1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity spawn(MythicMob mm, Location location) {
/*  29 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/*  30 */       Husk husk = (Husk)location.getWorld().spawnEntity(location, EntityType.HUSK);
/*  31 */       husk.setBaby(false);
/*  32 */       return (Entity)husk;
/*     */     } 
/*  34 */     Zombie e = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/*     */     
/*  36 */     e.setBaby(false);
/*     */     
/*  38 */     e = (Zombie)applyOptions((Entity)e);
/*     */     
/*  40 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity spawn(Location location) {
/*  47 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/*  48 */       Husk husk = (Husk)location.getWorld().spawnEntity(location, EntityType.HUSK);
/*  49 */       husk.setBaby(false);
/*  50 */       return (Entity)husk;
/*     */     } 
/*  52 */     Zombie e = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/*     */     
/*  54 */     e.setBaby(false);
/*     */     
/*  56 */     e = (Zombie)applyOptions((Entity)e);
/*     */     
/*  58 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity applyOptions(Entity entity) {
/*  64 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/*  65 */       Husk husk = (Husk)entity;
/*     */       
/*  67 */       if (this.reinforcementChance >= 0.0D) {
/*  68 */         if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  69 */           husk.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(this.reinforcementChance);
/*     */         } else {
/*  71 */           MythicMobs.inst().getVolatileCodeHandler().setZombieSpawnReinforcements((Zombie)husk, this.reinforcementChance);
/*     */         } 
/*     */       }
/*     */       
/*  75 */       return (Entity)husk;
/*     */     } 
/*  77 */     Zombie e = (Zombie)entity;
/*     */     
/*  79 */     if (this.reinforcementChance >= 0.0D) {
/*  80 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  81 */         e.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(this.reinforcementChance);
/*     */       } else {
/*  83 */         MythicMobs.inst().getVolatileCodeHandler().setZombieSpawnReinforcements(e, this.reinforcementChance);
/*     */       } 
/*     */     }
/*     */     
/*  87 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compare(Entity e) {
/*  93 */     return isInstanceOf(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInstanceOf(Entity e) {
/*  98 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/*  99 */       return (e instanceof Husk && !((Husk)e).isBaby());
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 106 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitHusk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */