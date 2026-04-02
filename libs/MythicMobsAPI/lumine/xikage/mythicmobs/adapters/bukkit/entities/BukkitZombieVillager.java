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
/*     */ 
/*     */ public class BukkitZombieVillager
/*     */   extends MythicEntity
/*     */ {
/*     */   private static final int height = 2;
/*  20 */   private double reinforcementChance = -1.0D;
/*     */   
/*     */   private Villager.Profession villagerProfession;
/*     */   
/*     */   public void instantiate(MythicConfig mc) {
/*  25 */     this.reinforcementChance = mc.getDouble("Options.ReinforcementsChance", -1.0D);
/*     */     
/*  27 */     String prof = mc.getString("Options.Profession", "FARMER");
/*     */     try {
/*  29 */       this.villagerProfession = Villager.Profession.valueOf(prof.toUpperCase());
/*  30 */     } catch (Exception ex) {
/*  31 */       this.villagerProfession = Villager.Profession.FARMER;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity spawn(MythicMob mm, Location location) {
/*  38 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/*  39 */       ZombieVillager zombieVillager = (ZombieVillager)location.getWorld().spawnEntity(location, EntityType.ZOMBIE_VILLAGER);
/*     */       
/*  41 */       zombieVillager.setBaby(false);
/*     */       
/*  43 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  44 */         zombieVillager.setVillagerProfession(this.villagerProfession);
/*     */       } else {
/*  46 */         zombieVillager.setVillager(true);
/*     */       } 
/*     */       
/*  49 */       zombieVillager = (ZombieVillager)applyOptions((Entity)zombieVillager);
/*  50 */       return (Entity)zombieVillager;
/*     */     } 
/*  52 */     Zombie e = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/*     */     
/*  54 */     e.setBaby(false);
/*     */     
/*  56 */     if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  57 */       e.setVillagerProfession(this.villagerProfession);
/*     */     } else {
/*  59 */       e.setVillager(true);
/*     */     } 
/*     */     
/*  62 */     e = (Zombie)applyOptions((Entity)e);
/*  63 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity spawn(Location location) {
/*  70 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/*  71 */       ZombieVillager zombieVillager = (ZombieVillager)location.getWorld().spawnEntity(location, EntityType.ZOMBIE_VILLAGER);
/*     */       
/*  73 */       zombieVillager.setBaby(false);
/*     */       
/*  75 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  76 */         zombieVillager.setVillagerProfession(this.villagerProfession);
/*     */       } else {
/*  78 */         zombieVillager.setVillager(true);
/*     */       } 
/*     */       
/*  81 */       zombieVillager = (ZombieVillager)applyOptions((Entity)zombieVillager);
/*  82 */       return (Entity)zombieVillager;
/*     */     } 
/*  84 */     Zombie e = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/*     */     
/*  86 */     e.setBaby(false);
/*     */     
/*  88 */     if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/*  89 */       e.setVillagerProfession(this.villagerProfession);
/*     */     } else {
/*  91 */       e.setVillager(true);
/*     */     } 
/*     */     
/*  94 */     e = (Zombie)applyOptions((Entity)e);
/*  95 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity applyOptions(Entity entity) {
/* 102 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 103 */       ZombieVillager zombieVillager = (ZombieVillager)entity;
/*     */       
/* 105 */       if (this.reinforcementChance >= 0.0D) {
/* 106 */         if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 107 */           zombieVillager.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(this.reinforcementChance);
/*     */         } else {
/* 109 */           MythicMobs.inst().getVolatileCodeHandler().setZombieSpawnReinforcements((Zombie)zombieVillager, this.reinforcementChance);
/*     */         } 
/*     */       }
/*     */       
/* 113 */       return (Entity)zombieVillager;
/*     */     } 
/* 115 */     Zombie e = (Zombie)entity;
/*     */     
/* 117 */     if (this.reinforcementChance >= 0.0D) {
/* 118 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 119 */         e.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(this.reinforcementChance);
/*     */       } else {
/* 121 */         MythicMobs.inst().getVolatileCodeHandler().setZombieSpawnReinforcements(e, this.reinforcementChance);
/*     */       } 
/*     */     }
/*     */     
/* 125 */     return (Entity)e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean compare(Entity e) {
/* 131 */     return isInstanceOf(e);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInstanceOf(Entity e) {
/* 136 */     if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 137 */       return (e instanceof ZombieVillager && !((ZombieVillager)e).isBaby());
/*     */     }
/* 139 */     return (e instanceof Zombie && !((Zombie)e).isBaby() && ((Zombie)e).isVillager());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 144 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitZombieVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */