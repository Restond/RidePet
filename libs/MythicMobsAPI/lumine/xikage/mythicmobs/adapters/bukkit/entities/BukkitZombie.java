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
/*     */ import org.bukkit.entity.Zombie;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BukkitZombie
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
/*  29 */     Zombie e = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/*     */     
/*  31 */     e.setBaby(false);
/*     */     
/*  33 */     if (MythicMobs.inst().getMinecraftVersion() < 11)
/*     */     {
/*     */       
/*  36 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  37 */         e.setVillagerProfession(null);
/*     */       } else {
/*  39 */         e.setVillager(false);
/*     */       } 
/*     */     }
/*     */     
/*  43 */     e = (Zombie)applyOptions((Entity)e);
/*     */     
/*  45 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity spawn(Location location) {
/*  50 */     Zombie e = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/*     */     
/*  52 */     e.setBaby(false);
/*     */     
/*  54 */     if (MythicMobs.inst().getMinecraftVersion() < 11)
/*     */     {
/*     */       
/*  57 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  58 */         e.setVillagerProfession(null);
/*     */       } else {
/*  60 */         e.setVillager(false);
/*     */       } 
/*     */     }
/*     */     
/*  64 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity applyOptions(Entity entity) {
/*  69 */     Zombie e = (Zombie)entity;
/*     */     
/*  71 */     if (this.reinforcementChance >= 0.0D) {
/*  72 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  73 */         e.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(this.reinforcementChance);
/*     */       } else {
/*  75 */         MythicMobs.inst().getVolatileCodeHandler().setZombieSpawnReinforcements(e, this.reinforcementChance);
/*     */       } 
/*     */     }
/*  78 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compare(Entity e) {
/*  83 */     return isInstanceOf(e);
/*     */   }
/*     */   
/*     */   public static boolean isInstanceOf(Entity e) {
/*  87 */     if (e instanceof Zombie && e.getType().equals(EntityType.ZOMBIE))
/*     */     {
/*  89 */       if (MythicMobs.inst().getMinecraftVersion() >= 11 && 
/*  90 */         !((Zombie)e).isBaby()) {
/*  91 */         return true;
/*     */       }
/*     */     }
/*     */     
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 100 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */