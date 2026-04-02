/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.entities;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Zombie;
/*    */ 
/*    */ public class BukkitBabyZombie
/*    */   extends MythicEntity
/*    */ {
/*    */   private static final int height = 1;
/* 17 */   private double reinforcementChance = -1.0D;
/*    */ 
/*    */   
/*    */   public void instantiate(MythicConfig mc) {
/* 21 */     this.reinforcementChance = mc.getDouble("Options.ReinforcementsChance", -1.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(MythicMob mm, Location location) {
/* 27 */     Zombie e = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/* 28 */     e.setBaby(true);
/*    */     
/* 30 */     if (MythicMobs.inst().getMinecraftVersion() < 11)
/*    */     {
/* 32 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 33 */         e.setVillagerProfession(null);
/*    */       } else {
/* 35 */         e.setVillager(false);
/*    */       } 
/*    */     }
/* 38 */     e = (Zombie)applyOptions((Entity)e);
/*    */     
/* 40 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity spawn(Location location) {
/* 46 */     Zombie e = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
/* 47 */     e.setBaby(true);
/*    */     
/* 49 */     if (MythicMobs.inst().getMinecraftVersion() < 11)
/*    */     {
/* 51 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 52 */         e.setVillagerProfession(null);
/*    */       } else {
/* 54 */         e.setVillager(false);
/*    */       } 
/*    */     }
/* 57 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity applyOptions(Entity entity) {
/* 62 */     Zombie e = (Zombie)entity;
/*    */     
/* 64 */     if (this.reinforcementChance >= 0.0D) {
/* 65 */       if (MythicMobs.inst().getMinecraftVersion() >= 9) {
/* 66 */         e.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(this.reinforcementChance);
/*    */       } else {
/* 68 */         MythicMobs.inst().getVolatileCodeHandler().setZombieSpawnReinforcements(e, this.reinforcementChance);
/*    */       } 
/*    */     }
/*    */     
/* 72 */     return (Entity)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compare(Entity e) {
/* 77 */     return isInstanceOf(e);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isInstanceOf(Entity e) {
/* 82 */     if (e instanceof Zombie) {
/* 83 */       if (MythicMobs.inst().getMinecraftVersion() >= 11) {
/* 84 */         return ((Zombie)e).isBaby();
/*    */       }
/* 86 */       return (((Zombie)e).isBaby() && !((Zombie)e).isVillager());
/*    */     } 
/* 88 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 93 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\entities\BukkitBabyZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */