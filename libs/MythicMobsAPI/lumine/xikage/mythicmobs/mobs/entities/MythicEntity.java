/*     */ package lumine.xikage.mythicmobs.mobs.entities;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitBabyPigZombie;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitCustom;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitIronGolem;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitPig;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.entities.BukkitPillager;
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntityType;
/*     */ import java.util.HashMap;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Entity;
/*     */ 
/*     */ public abstract class MythicEntity {
/*  17 */   private static HashMap<MythicEntityType, Class<? extends io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity>> entities = new HashMap<>();
/*     */   
/*     */   public abstract void instantiate(MythicConfig paramMythicConfig);
/*     */   
/*     */   public abstract Entity spawn(MythicMob paramMythicMob, Location paramLocation);
/*     */   
/*     */   public abstract Entity spawn(Location paramLocation);
/*     */   
/*     */   public abstract Entity applyOptions(Entity paramEntity);
/*     */   
/*     */   public abstract boolean compare(Entity paramEntity);
/*     */   
/*     */   public int getHeight() {
/*  30 */     return 2;
/*     */   }
/*     */   
/*     */   public double getHealthbarOffset() {
/*  34 */     return -0.1D;
/*     */   }
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity getMythicEntity(MythicMob mm) {
/*  38 */     return getMythicEntity(mm.getEntityType());
/*     */   }
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity getMythicEntity(String s) {
/*  42 */     MythicEntityType met = MythicEntityType.get(s);
/*     */     
/*  44 */     if (met == null) return null; 
/*  45 */     return getMythicEntity(met);
/*     */   }
/*     */   public static io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity getMythicEntity(MythicEntityType entityType) {
/*     */     Class<BukkitCustom> clazz1;
/*  49 */     if (entityType == null) return null; 
/*  50 */     Class<? extends io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity> clazz = entities.get(entityType);
/*  51 */     if (clazz == null) {
/*  52 */       MythicLogger.debug(MythicLogger.DebugLevel.SKILL_CHECK, "! MythicEntityType not found", new Object[0]);
/*     */       try {
/*  54 */         clazz1 = BukkitCustom.class;
/*  55 */         return (io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity)clazz1.newInstance();
/*  56 */       } catch (Exception e) {
/*  57 */         if (ConfigManager.debugLevel >= 1) {
/*  58 */           e.printStackTrace();
/*     */         }
/*  60 */         return null;
/*     */       } 
/*     */     } 
/*     */     try {
/*  64 */       return (io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity)clazz1.newInstance();
/*  65 */     } catch (Exception e) {
/*  66 */       if (ConfigManager.debugLevel >= 1) {
/*  67 */         e.printStackTrace();
/*     */       }
/*  69 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/*  75 */     entities.put(MythicEntityType.ARMOR_STAND, BukkitArmorStand.class);
/*  76 */     entities.put(MythicEntityType.BABY_DROWNED, BukkitBabyDrowned.class);
/*  77 */     entities.put(MythicEntityType.BABY_PIG_ZOMBIE, BukkitBabyPigZombie.class);
/*  78 */     entities.put(MythicEntityType.BABY_PIG_ZOMBIE_VILLAGER, BukkitBabyPigZombieVillager.class);
/*  79 */     entities.put(MythicEntityType.BABY_ZOMBIE, BukkitBabyZombie.class);
/*  80 */     entities.put(MythicEntityType.BABY_ZOMBIE_VILLAGER, BukkitBabyZombieVillager.class);
/*  81 */     entities.put(MythicEntityType.BAT, BukkitBat.class);
/*  82 */     entities.put(MythicEntityType.BLAZE, BukkitBlaze.class);
/*  83 */     entities.put(MythicEntityType.CAVE_SPIDER, BukkitCaveSpider.class);
/*  84 */     entities.put(MythicEntityType.CHICKEN, BukkitChicken.class);
/*  85 */     entities.put(MythicEntityType.COD, BukkitCod.class);
/*  86 */     entities.put(MythicEntityType.COW, BukkitCow.class);
/*  87 */     entities.put(MythicEntityType.CREEPER, BukkitCreeper.class);
/*  88 */     entities.put(MythicEntityType.CUSTOM, BukkitCustom.class);
/*  89 */     entities.put(MythicEntityType.DOLPHIN, BukkitDolphin.class);
/*  90 */     entities.put(MythicEntityType.DONKEY, BukkitDonkey.class);
/*  91 */     entities.put(MythicEntityType.DROWNED, BukkitDrowned.class);
/*  92 */     entities.put(MythicEntityType.ELDER_GUARDIAN, BukkitElderGuardian.class);
/*  93 */     entities.put(MythicEntityType.ENDERMAN, BukkitEnderman.class);
/*  94 */     entities.put(MythicEntityType.ENDERMITE, BukkitEndermite.class);
/*  95 */     entities.put(MythicEntityType.ENDER_DRAGON, BukkitEnderDragon.class);
/*  96 */     entities.put(MythicEntityType.EVOKER, BukkitEvoker.class);
/*  97 */     entities.put(MythicEntityType.FALLING_BLOCK, BukkitFallingBlock.class);
/*  98 */     entities.put(MythicEntityType.FOX, BukkitFox.class);
/*  99 */     entities.put(MythicEntityType.GHAST, BukkitGhast.class);
/* 100 */     entities.put(MythicEntityType.GIANT, BukkitGiant.class);
/* 101 */     entities.put(MythicEntityType.GUARDIAN, BukkitGuardian.class);
/* 102 */     entities.put(MythicEntityType.HORSE, BukkitHorse.class);
/* 103 */     entities.put(MythicEntityType.HUSK, BukkitHusk.class);
/* 104 */     entities.put(MythicEntityType.ILLUSIONER, BukkitIllusioner.class);
/* 105 */     entities.put(MythicEntityType.IRON_GOLEM, BukkitIronGolem.class);
/* 106 */     entities.put(MythicEntityType.LLAMA, BukkitLlama.class);
/* 107 */     entities.put(MythicEntityType.MAGMA_CUBE, BukkitMagmaCube.class);
/* 108 */     entities.put(MythicEntityType.MULE, BukkitMule.class);
/* 109 */     entities.put(MythicEntityType.MUSHROOM_COW, BukkitMushroomCow.class);
/* 110 */     entities.put(MythicEntityType.OCELOT, BukkitOcelot.class);
/* 111 */     entities.put(MythicEntityType.PANDA, BukkitPanda.class);
/* 112 */     entities.put(MythicEntityType.PARROT, BukkitParrot.class);
/* 113 */     entities.put(MythicEntityType.PHANTOM, BukkitPhantom.class);
/* 114 */     entities.put(MythicEntityType.PIG, BukkitPig.class);
/* 115 */     entities.put(MythicEntityType.PIG_ZOMBIE, BukkitPigZombie.class);
/* 116 */     entities.put(MythicEntityType.PIG_ZOMBIE_VILLAGER, BukkitPigZombieVillager.class);
/* 117 */     entities.put(MythicEntityType.PILLAGER, BukkitPillager.class);
/* 118 */     entities.put(MythicEntityType.POLAR_BEAR, BukkitPolarBear.class);
/* 119 */     entities.put(MythicEntityType.PRIMED_TNT, BukkitTNT.class);
/* 120 */     entities.put(MythicEntityType.PUFFERFISH, BukkitPufferFish.class);
/* 121 */     entities.put(MythicEntityType.RABBIT, BukkitRabbit.class);
/* 122 */     entities.put(MythicEntityType.RAVAGER, BukkitRavager.class);
/* 123 */     entities.put(MythicEntityType.SALMON, BukkitSalmon.class);
/* 124 */     entities.put(MythicEntityType.SHEEP, BukkitSheep.class);
/* 125 */     entities.put(MythicEntityType.SHULKER, BukkitShulker.class);
/* 126 */     entities.put(MythicEntityType.SILVERFISH, BukkitSilverfish.class);
/* 127 */     entities.put(MythicEntityType.SKELETON, BukkitSkeleton.class);
/* 128 */     entities.put(MythicEntityType.SKELETON_HORSE, BukkitSkeletonHorse.class);
/* 129 */     entities.put(MythicEntityType.SLIME, BukkitSlime.class);
/* 130 */     entities.put(MythicEntityType.SNOWMAN, BukkitSnowman.class);
/* 131 */     entities.put(MythicEntityType.SPIDER, BukkitSpider.class);
/* 132 */     entities.put(MythicEntityType.STRAY, BukkitStray.class);
/* 133 */     entities.put(MythicEntityType.SQUID, BukkitSquid.class);
/* 134 */     entities.put(MythicEntityType.TROPICAL_FISH, BukkitTropicalFish.class);
/* 135 */     entities.put(MythicEntityType.TURTLE, BukkitTurtle.class);
/* 136 */     entities.put(MythicEntityType.VEX, BukkitVex.class);
/* 137 */     entities.put(MythicEntityType.VILLAGER, BukkitVillager.class);
/* 138 */     entities.put(MythicEntityType.VINDICATOR, BukkitVindicator.class);
/* 139 */     entities.put(MythicEntityType.WANDERING_TRADER, BukkitWanderingTrader.class);
/* 140 */     entities.put(MythicEntityType.WITCH, BukkitWitch.class);
/* 141 */     entities.put(MythicEntityType.WITHER, BukkitWither.class);
/* 142 */     entities.put(MythicEntityType.WITHER_SKELETON, BukkitWitherSkeleton.class);
/* 143 */     entities.put(MythicEntityType.WOLF, BukkitWolf.class);
/* 144 */     entities.put(MythicEntityType.ZOMBIE, BukkitZombie.class);
/* 145 */     entities.put(MythicEntityType.ZOMBIE_HORSE, BukkitZombieHorse.class);
/* 146 */     entities.put(MythicEntityType.ZOMBIE_VILLAGER, BukkitZombieVillager.class);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\entities\MythicEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */