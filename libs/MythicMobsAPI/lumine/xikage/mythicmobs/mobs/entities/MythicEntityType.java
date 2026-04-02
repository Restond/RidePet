/*     */ package lumine.xikage.mythicmobs.mobs.entities;
/*     */ 
/*     */ import org.bukkit.entity.EntityType;
/*     */ 
/*     */ public enum MythicEntityType {
/*   6 */   ARMOR_STAND,
/*   7 */   BABY_DROWNED,
/*   8 */   BABY_PIG_ZOMBIE,
/*   9 */   BABY_PIG_ZOMBIE_VILLAGER,
/*  10 */   BABY_ZOMBIE,
/*  11 */   BABY_ZOMBIE_VILLAGER,
/*  12 */   BAT,
/*  13 */   BLAZE,
/*  14 */   CAT,
/*  15 */   CAVE_SPIDER,
/*  16 */   CHICKEN,
/*  17 */   COD,
/*  18 */   COW,
/*  19 */   CREEPER,
/*  20 */   CUSTOM,
/*  21 */   DOLPHIN,
/*  22 */   DONKEY,
/*  23 */   DROWNED,
/*  24 */   ELDER_GUARDIAN,
/*  25 */   ENDER_DRAGON,
/*  26 */   ENDERMAN,
/*  27 */   ENDERMITE,
/*  28 */   EVOKER,
/*  29 */   FALLING_BLOCK,
/*  30 */   FOX,
/*  31 */   GHAST,
/*  32 */   GIANT,
/*  33 */   GUARDIAN,
/*  34 */   HORSE,
/*  35 */   HUSK,
/*  36 */   ILLUSIONER,
/*  37 */   IRON_GOLEM,
/*  38 */   LLAMA,
/*  39 */   MAGMA_CUBE,
/*  40 */   MINECART,
/*  41 */   MINECART_CHEST,
/*  42 */   MINECART_COMMAND,
/*  43 */   MINECART_FURNACE,
/*  44 */   MINECART_HOPPER,
/*  45 */   MINECART_MOB_SPAWNER,
/*  46 */   MINECART_TNT,
/*  47 */   MULE,
/*  48 */   MUSHROOM_COW,
/*  49 */   OCELOT,
/*  50 */   PANDA,
/*  51 */   PARROT,
/*  52 */   PHANTOM,
/*  53 */   PIG,
/*  54 */   PIG_ZOMBIE,
/*  55 */   PIG_ZOMBIE_VILLAGER,
/*  56 */   PILLAGER,
/*  57 */   POLAR_BEAR,
/*  58 */   PRIMED_TNT,
/*  59 */   PUFFERFISH,
/*  60 */   RABBIT,
/*  61 */   RAVAGER,
/*  62 */   SALMON,
/*  63 */   SHEEP,
/*  64 */   SHULKER,
/*  65 */   SILVERFISH,
/*  66 */   SKELETON,
/*  67 */   SKELETON_HORSE,
/*  68 */   SLIME,
/*  69 */   SNOWMAN,
/*  70 */   SPIDER,
/*  71 */   STRAY,
/*  72 */   SQUID,
/*  73 */   TRADER_LLAMA,
/*  74 */   TROPICAL_FISH,
/*  75 */   TURTLE,
/*  76 */   VEX,
/*  77 */   VILLAGER,
/*  78 */   VINDICATOR,
/*  79 */   WANDERING_TRADER,
/*  80 */   WITCH,
/*  81 */   WITHER,
/*  82 */   WITHER_SKELETON,
/*  83 */   WOLF,
/*  84 */   ZOMBIE,
/*  85 */   ZOMBIE_HORSE,
/*  86 */   ZOMBIE_VILLAGER;
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.mobs.entities.MythicEntityType get(String s) {
/*  89 */     if (s == null) {
/*  90 */       return null;
/*     */     }
/*     */     try {
/*  93 */       return valueOf(s.toUpperCase());
/*  94 */     } catch (IllegalArgumentException ex) {
/*     */       try {
/*  96 */         EntityType et = EntityType.valueOf(s.toUpperCase());
/*  97 */         if (et == null) {
/*  98 */           return null;
/*     */         }
/* 100 */         return CUSTOM;
/*     */       }
/* 102 */       catch (Exception exx) {
/* 103 */         return null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\entities\MythicEntityType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */