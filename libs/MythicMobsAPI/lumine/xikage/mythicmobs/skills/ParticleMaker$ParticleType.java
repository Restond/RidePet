/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.skills.ParticleMaker;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum ParticleType
/*     */ {
/*  27 */   EXPLODE("explode", 0, -1, new String[] { "EXPLODE", "EXPLOSION", "EXPLOSION_SMALL" }),
/*  28 */   EXPLOSION_LARGE("largeexplode", 1, -1, new String[] { "largeexplosion", "EXPLOSION_LARGE" }),
/*  29 */   EXPLOSION_HUGE("hugeexplosion", 2, -1, new String[] { "hugeexplode", "EXPLOSION_HUGE" }),
/*  30 */   FIREWORKS_SPARK("fireworksSpark", 3, -1, new String[] { "FIREWORKS_SPARK" }),
/*  31 */   WATER_BUBBLE("bubble", 4, -1, new String[] { "WATER_BUBBLE" }),
/*  32 */   WATER_SPLASH("splash", 5, -1, new String[] { "WATER_SPLASH" }),
/*  33 */   WATER_WAKE("wake", 6, 7, new String[] { "WATER_WAKE" }),
/*  34 */   SUSPENDED("suspended", 7, -1, new String[0]),
/*  35 */   SUSPENDED_DEPTH("depthSuspend", 8, -1, new String[] { "SUSPENDED_DEPTH" }),
/*  36 */   CRIT("crit", 9, -1, new String[0]),
/*  37 */   CRIT_MAGIC("magicCrit", 10, -1, new String[] { "CRIT_MAGIC" }),
/*  38 */   SMOKE_NORMAL("smoke", 11, -1, new String[] { "SMOKE_NORMAL" }),
/*  39 */   SMOKE_LARGE("largesmoke", 12, -1, new String[] { "SMOKE_LARGE" }),
/*  40 */   SPELL("spell", 13, -1, new String[0]),
/*  41 */   SPELL_INSTANT("instantSpell", 14, -1, new String[] { "SPELL_INSTANT" }),
/*  42 */   SPELL_MOB("mobSpell", 15, -1, new String[] { "SPELL_MOB" }),
/*  43 */   SPELL_MOB_AMBIENT("mobSpellAmbient", 16, -1, new String[] { "SPELL_MOB_AMBIENT" }),
/*  44 */   SPELL_WITCH("witchMagic", 17, -1, new String[] { "SPELL_WITCH" }),
/*  45 */   DRIP_WATER("dripWater", 18, -1, new String[0]),
/*  46 */   DRIP_LAVA("dripLava", 19, -1, new String[0]),
/*  47 */   VILLAGER_ANGRY("angryVillager", 20, -1, new String[] { "VILLAGER_ANGRY" }),
/*  48 */   VILLAGER_HAPPY("happyVillager", 21, -1, new String[] { "VILLAGER_HAPPY" }),
/*  49 */   TOWN_AURA("townaura", 22, -1, new String[] { "TOWN_AURA" }),
/*  50 */   NOTE("note", 23, -1, new String[0]),
/*  51 */   PORTAL("portal", 24, -1, new String[0]),
/*  52 */   ENCHANTMENT_TABLE("enchantmenttable", 25, -1, new String[] { "ENCHANTMENT_TABLE", "enchanttable" }),
/*  53 */   FLAME("flame", 26, -1, new String[0]),
/*  54 */   LAVA("lava", 27, -1, new String[0]),
/*  55 */   FOOTSTEP("footstep", 28, -1, new String[0]),
/*  56 */   CLOUD("cloud", 29, -1, new String[0]),
/*  57 */   REDSTONE("reddust", 30, -1, new String[0]),
/*  58 */   SNOWBALL("snowballpoof", 31, -1, new String[0]),
/*  59 */   SNOW_SHOVEL("snowshovel", 32, -1, new String[] { "SNOW_SHOVEL" }),
/*  60 */   SLIME("slime", 33, -1, new String[0]),
/*  61 */   HEART("heart", 34, -1, new String[0]),
/*  62 */   BARRIER("barrier", 35, 8, new String[0]),
/*  63 */   ITEM_CRACK("iconcrack", 36, -1, new String[] { "ITEM_CRACK" }),
/*  64 */   BLOCK_CRACK("blockcrack", 37, -1, new String[0]),
/*  65 */   BLOCK_DUST("blockdust", 38, 7, new String[0]),
/*  66 */   WATER_DROP("droplet", 39, 8, new String[0]),
/*  67 */   ITEM_TAKE("take", 40, 8, new String[0]),
/*  68 */   MOB_APPEARANCE("mobappearance", 41, 8, new String[0]),
/*  69 */   END_ROD("endRod", 43, 9, new String[0]),
/*  70 */   DRAGON_BREATH("dragonbreath", 42, 9, new String[0]),
/*  71 */   DAMAGE_INDICATOR("damageIndicator", 44, 9, new String[0]),
/*  72 */   SWEEP_ATTACK("sweepAttack", 45, 9, new String[0]),
/*  73 */   FALLING_DUST("fallingdust", 46, 10, new String[0]),
/*  74 */   TOTEM("totem", 47, 11, new String[0]),
/*  75 */   SPIT("spit", 48, 11, new String[0]); private static final Map<String, ParticleType> LOOKUP_MAP; private final String name; private final int id; private final int requiredVersion;
/*     */   private final List<String> aliases;
/*     */   
/*  78 */   static { LOOKUP_MAP = new HashMap<>();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     for (ParticleType t : values()) {
/*  99 */       LOOKUP_MAP.put(t.name.toUpperCase(), t);
/*     */       
/* 101 */       for (String a : t.aliases) {
/* 102 */         LOOKUP_MAP.put(a.toUpperCase(), t);
/*     */       }
/*     */     }  }
/*     */ 
/*     */   
/*     */   public static ParticleType get(String s) {
/* 108 */     if (s.contains("_")) {
/* 109 */       String[] split = s.split("_");
/* 110 */       s = split[0];
/*     */     } 
/* 112 */     s = s.toUpperCase();
/* 113 */     MythicMobs.debug(3, "== Particle name reduced to " + s);
/*     */     
/* 115 */     if (LOOKUP_MAP.containsKey(s)) {
/* 116 */       return LOOKUP_MAP.get(s);
/*     */     }
/* 118 */     return null; }
/*     */   ParticleType(String name, int id, int requiredVersion, String... aliases) { if (name.contains("_")) {
/*     */       String[] split = name.split("_"); name = split[0];
/*     */     }  this.name = name; this.id = id; this.requiredVersion = requiredVersion;
/* 122 */     this.aliases = Arrays.asList(aliases); } public String getName() { return this.name; }
/*     */   
/*     */   public int getId() {
/* 125 */     return this.id;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\ParticleMaker$ParticleType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */