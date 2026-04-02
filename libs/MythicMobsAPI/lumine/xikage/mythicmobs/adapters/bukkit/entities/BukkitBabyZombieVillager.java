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
/*     */ import org.bukkit.entity.Villager;
/*     */ import org.bukkit.entity.Zombie;
/*     */ import org.bukkit.entity.ZombieVillager;
/*     */ 
/*     */ public class BukkitBabyZombieVillager
/*     */   extends MythicEntity
/*     */ {
/*     */   private static final int height = 1;
/*  19 */   private double reinforcementChance = -1.0D;
/*     */   
/*     */   private Villager.Profession villagerProfession;
/*     */   
/*     */   public void instantiate(MythicConfig mc) {
/*  24 */     this.reinforcementChance = mc.getDouble("Options.ReinforcementsChance", -1.0D);
/*     */     
/*  26 */     String prof = mc.getString("Options.Profession", "FARMER");
/*     */     try {
/*  28 */       this.villagerProfession = Villager.Profession.valueOf(prof.toUpperCase());
/*  29 */     } catch (Exception ex) {
/*  30 */       this.villagerProfession = Villager.Profession.FARMER;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity spawn(MythicMob mm, Location location) {
/*  38 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/*  39 */       ZombieVillager zombieVillager = (ZombieVillager)location.getWorld().spawnEntity(location, EntityType.ZOMBIE_VILLAGER);
/*  40 */       zombieVillager.setBaby(true);
/*     */       
/*  42 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  43 */         zombieVillager.setVillagerProfession(this.villagerProfession);
/*     */       } else {
/*  45 */         zombieVillager.setVillager(true);
/*     */       } 
/*     */       
/*  48 */       zombieVillager = (ZombieVillager)applyOptions((Entity)zombieVillager);
/*     */       
/*  50 */       return (Entity)zombieVillager;
/*     */     } 
/*  52 */     Zombie e = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/*  53 */     e.setBaby(true);
/*  54 */     if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  55 */       e.setVillagerProfession(this.villagerProfession);
/*     */     } else {
/*  57 */       e.setVillager(true);
/*     */     } 
/*  59 */     e = (Zombie)applyOptions((Entity)e);
/*     */     
/*  61 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity spawn(Location location) {
/*  68 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/*  69 */       ZombieVillager zombieVillager = (ZombieVillager)location.getWorld().spawnEntity(location, EntityType.ZOMBIE_VILLAGER);
/*  70 */       zombieVillager.setBaby(true);
/*  71 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  72 */         zombieVillager.setVillagerProfession(this.villagerProfession);
/*     */       } else {
/*  74 */         zombieVillager.setVillager(true);
/*     */       } 
/*  76 */       zombieVillager = (ZombieVillager)applyOptions((Entity)zombieVillager);
/*     */       
/*  78 */       return (Entity)zombieVillager;
/*     */     } 
/*  80 */     Zombie e = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/*  81 */     e.setBaby(true);
/*  82 */     if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  83 */       e.setVillagerProfession(this.villagerProfession);
/*     */     } else {
/*  85 */       e.setVillager(true);
/*     */     } 
/*  87 */     e = (Zombie)applyOptions((Entity)e);
/*     */     
/*  89 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity applyOptions(Entity entity) {
/*  96 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/*  97 */       ZombieVillager e = (ZombieVillager)entity;
/*  98 */       e.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(this.reinforcementChance);
/*     */     } else {
/* 100 */       Zombie e = (Zombie)entity;
/*     */       
/* 102 */       if (this.reinforcementChance >= 0.0D) {
/* 103 */         if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 104 */           e.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(this.reinforcementChance);
/*     */         } else {
/* 106 */           MythicMobs.inst().getVolatileCodeHandler().setZombieSpawnReinforcements(e, this.reinforcementChance);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 111 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compare(Entity e) {
/* 116 */     return isInstanceOf(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInstanceOf(Entity e) {
/* 121 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 122 */       if (e instanceof ZombieVillager) {
/* 123 */         if (((ZombieVillager)e).isBaby()) {
/* 124 */           return true;
/*     */         }
/* 126 */         return false;
/*     */       }
/*     */     
/*     */     }
/* 130 */     else if (e instanceof Zombie) {
/* 131 */       if (((Zombie)e).isBaby() && ((Zombie)e).isVillager()) {
/* 132 */         return true;
/*     */       }
/* 134 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 143 */     return 1;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitBabyZombieVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */