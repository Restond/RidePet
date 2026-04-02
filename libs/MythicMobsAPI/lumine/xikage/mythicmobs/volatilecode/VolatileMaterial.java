/*    */ package lumine.xikage.mythicmobs.volatilecode;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ public class VolatileMaterial
/*    */ {
/*  9 */   private static final int VERSION = MythicMobs.inst().getMinecraftVersion();
/*    */   
/* 11 */   public static final Material DIAMOND_HORSE_ARMOR = (VERSION < 13) ? Material.valueOf("DIAMOND_BARDING") : Material.valueOf("DIAMOND_HORSE_ARMOR");
/* 12 */   public static final Material FIREWORK_ROCKET = (VERSION < 13) ? Material.valueOf("FIREWORK") : Material.valueOf("FIREWORK_ROCKET");
/* 13 */   public static final Material FIREWORK_STAR = (VERSION < 13) ? Material.valueOf("FIREWORK_CHARGE") : Material.valueOf("FIREWORK_STAR");
/* 14 */   public static final Material GOLDEN_HELMET = (VERSION < 13) ? Material.valueOf("GOLD_HELMET") : Material.valueOf("GOLDEN_HELMET");
/* 15 */   public static final Material GOLDEN_CHESTPLATE = (VERSION < 13) ? Material.valueOf("GOLD_CHESTPLATE") : Material.valueOf("GOLDEN_CHESTPLATE");
/* 16 */   public static final Material GOLDEN_LEGGINGS = (VERSION < 13) ? Material.valueOf("GOLD_LEGGINGS") : Material.valueOf("GOLDEN_LEGGINGS");
/* 17 */   public static final Material GOLDEN_BOOTS = (VERSION < 13) ? Material.valueOf("GOLD_BOOTS") : Material.valueOf("GOLDEN_BOOTS");
/* 18 */   public static final Material GOLDEN_HORSE_ARMOR = (VERSION < 13) ? Material.valueOf("GOLD_BARDING") : Material.valueOf("GOLDEN_HORSE_ARMOR");
/* 19 */   public static final Material IRON_HORSE_ARMOR = (VERSION < 13) ? Material.valueOf("IRON_BARDING") : Material.valueOf("IRON_HORSE_ARMOR");
/* 20 */   public static final Material LAVA = (VERSION < 13) ? Material.valueOf("STATIONARY_LAVA") : Material.valueOf("LAVA");
/* 21 */   public static final Material PLAYER_HEAD = (VERSION < 13) ? Material.valueOf("SKULL") : Material.valueOf("PLAYER_HEAD");
/* 22 */   public static final Material PLAYER_WALL_HEAD = (VERSION < 13) ? Material.valueOf("SKULL_ITEM") : Material.valueOf("PLAYER_WALL_HEAD");
/* 23 */   public static final Material SPAWNER = (VERSION < 13) ? Material.valueOf("MOB_SPAWNER") : Material.valueOf("SPAWNER");
/* 24 */   public static final Material SPAWN_EGG = (VERSION < 13) ? Material.valueOf("MONSTER_EGG") : Material.valueOf("PIG_SPAWN_EGG");
/* 25 */   public static final Material WATER = (VERSION < 13) ? Material.valueOf("STATIONARY_WATER") : Material.valueOf("WATER");
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\VolatileMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */