/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*     */ import java.awt.Color;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum BukkitParticle
/*     */ {
/*  27 */   EXPLOSION_NORMAL(new String[] { "poof", "explode", "explosion", "explosion_small" }),
/*  28 */   EXPLOSION_LARGE(new String[] { "largeexplode", "largeexplosion" }),
/*  29 */   EXPLOSION_HUGE(new String[] { "explosion_emitter", "hugeexplode", "hugeexplosion" }),
/*  30 */   FIREWORKS_SPARK(new String[] { "firework", "fireworksspark" }),
/*  31 */   WATER_BUBBLE(new String[] { "bubble" }),
/*  32 */   WATER_SPLASH(new String[] { "splash" }),
/*  33 */   WATER_WAKE(new String[] { "fishing", "wake" }),
/*  34 */   SUSPENDED(new String[] { "underwater" }),
/*  35 */   SUSPENDED_DEPTH(new String[] { "underwater", "depthsuspend" }),
/*  36 */   CRIT(new String[] { "crit" }),
/*  37 */   CRIT_MAGIC(new String[] { "enchanted_hit", "magiccrit" }),
/*  38 */   SMOKE_NORMAL(new String[] { "smoke" }),
/*  39 */   SMOKE_LARGE(new String[] { "large_smoke", "largesmoke" }),
/*  40 */   SPELL(new String[] { "effect" }),
/*  41 */   SPELL_INSTANT(new String[] { "instant_effect", "instantSpell" }),
/*  42 */   SPELL_MOB(new String[] { "entity_effect", "mobSpell" }),
/*  43 */   SPELL_MOB_AMBIENT(new String[] { "ambient_entity_effect", "mobSpellAmbient" }),
/*  44 */   SPELL_WITCH(new String[] { "witch", "witchMagic" }),
/*  45 */   DRIP_WATER(new String[] { "dripping_water", "dripWater" }),
/*  46 */   DRIP_LAVA(new String[] { "dripping_lava", "dripLava" }),
/*  47 */   VILLAGER_ANGRY(new String[] { "angry_villager", "angryVillager" }),
/*  48 */   VILLAGER_HAPPY(new String[] { "happy_villager", "happyVillager" }),
/*  49 */   TOWN_AURA(new String[] { "mycelium", "townaura" }),
/*  50 */   NOTE(new String[] { "note" }),
/*  51 */   PORTAL(new String[] { "portal" }),
/*  52 */   ENCHANTMENT_TABLE(new String[] { "enchant", "enchantmenttable", "enchantingtable" }),
/*  53 */   FLAME(new String[] { "flame" }),
/*  54 */   LAVA(new String[] { "lava" }),
/*  55 */   CLOUD(new String[] { "cloud" }),
/*  56 */   REDSTONE(new String[] { "dust", "reddust" }),
/*  57 */   SNOWBALL(new String[] { "item_snowball", "snowballpoof" }),
/*  58 */   SNOW_SHOVEL(new String[] { "item_snowball", "snowshovel" }),
/*  59 */   SLIME(new String[] { "item_slime" }),
/*  60 */   HEART(new String[] { "heart" }),
/*  61 */   BARRIER(new String[] { "barrier" }),
/*  62 */   ITEM_CRACK(new String[] { "item", "iconcrack", "itemcrack" }),
/*  63 */   BLOCK_CRACK(new String[] { "block", "blockcrack" }),
/*  64 */   BLOCK_DUST(new String[] { "dust", "blockdust" }),
/*  65 */   WATER_DROP(new String[] { "rain", "droplet" }),
/*  66 */   MOB_APPEARANCE(new String[] { "elder_guardian", "mobappearance" }),
/*  67 */   DRAGON_BREATH(new String[] { "dragon_breath", "dragonbreath" }),
/*  68 */   END_ROD(new String[] { "end_rod", "endRod" }),
/*  69 */   DAMAGE_INDICATOR(new String[] { "damage_indicator", "damageIndicator" }),
/*  70 */   SWEEP_ATTACK(new String[] { "sweep_attack", "sweepAttack" }),
/*  71 */   FALLING_DUST(new String[] { "falling_dust", "fallingDust" }),
/*  72 */   TOTEM(new String[] { "totem_of_undying" }),
/*  73 */   SPIT(new String[] { "spit" }),
/*  74 */   SQUID_INK(new String[] { "squid_ink", "squidink" }),
/*  75 */   BUBBLE_POP(new String[] { "bubble_pop", "bubblepop" }),
/*  76 */   CURRENT_DOWN(new String[] { "current_down", "currentdown" }),
/*  77 */   BUBBLE_COLUMN_UP(new String[] { "bubble_column_up", "bubblecolumn", "bubble_column" }),
/*  78 */   NAUTILUS(new String[] { "nautilus" }),
/*  79 */   DOLPHIN(new String[] { "dolphin" }),
/*  80 */   COMPOSTER(new String[] { "composter" }),
/*  81 */   FALLING_LAVA(new String[] { "fallinglava", "falling_lava" }),
/*  82 */   FALLING_WATER(new String[] { "fallingwater", "falling_water" }),
/*  83 */   FLASH(new String[] { "flash" }),
/*  84 */   LANDING_LAVA(new String[] { "landinglava", "landing_lava" }),
/*  85 */   SNEEZE(new String[] { "sneeze" }),
/*  86 */   CAMPFIRE_COSY_SMOKE(new String[] { "campfire_cosy_smoke", "campfire_cosy", "campfire_cozy_smoke", "campfire_cozy", "campfire" }),
/*  87 */   CAMPFIRE_SIGNAL_SMOKE(new String[] { "campfire_signal_smoke", "campfire_signal" }); private static final Map<String, io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitParticle> PARTICLE_ALIASES;
/*     */   
/*     */   static {
/*  90 */     PARTICLE_ALIASES = new HashMap<>();
/*     */ 
/*     */     
/*  93 */     for (io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitParticle particle : values()) {
/*  94 */       PARTICLE_ALIASES.put(particle.toString(), particle);
/*  95 */       for (String alias : particle.getAliases())
/*  96 */         PARTICLE_ALIASES.put(alias.toUpperCase(), particle); 
/*     */     } 
/*     */   }
/*     */   private final String[] aliases;
/*     */   
/*     */   public static io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitParticle get(String key) {
/* 102 */     io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitParticle particle = PARTICLE_ALIASES.getOrDefault(key.toUpperCase(), null);
/*     */     
/* 104 */     if (particle == null) {
/* 105 */       MythicLogger.errorGenericConfig("Particle '" + key + "' is not supported by this version of MythicMobs.");
/* 106 */       return CLOUD;
/*     */     } 
/* 108 */     return particle;
/*     */   }
/*     */   
/*     */   public String[] getAliases() {
/* 112 */     return this.aliases;
/*     */   }
/*     */   BukkitParticle(String... aliases) {
/* 115 */     this.aliases = aliases;
/*     */   }
/*     */   
/*     */   public Particle toBukkitParticle() {
/* 119 */     return Particle.valueOf(toString());
/*     */   }
/*     */   
/*     */   public boolean requiresData() {
/* 123 */     return !toBukkitParticle().getDataType().equals(Void.class);
/*     */   }
/*     */   
/*     */   public boolean validateData(Object obj) {
/* 127 */     Particle particle = toBukkitParticle();
/* 128 */     if (particle.getDataType().equals(Void.class)) {
/* 129 */       return false;
/*     */     }
/* 131 */     if (particle.getDataType().equals(ItemStack.class)) {
/* 132 */       return obj instanceof ItemStack;
/*     */     }
/* 134 */     if (particle.getDataType() == BlockData.class) {
/* 135 */       return obj instanceof BlockData;
/*     */     }
/* 137 */     if (particle.getDataType() == MaterialData.class) {
/* 138 */       return obj instanceof MaterialData;
/*     */     }
/* 140 */     if (particle.getDataType() == Particle.DustOptions.class) {
/* 141 */       return obj instanceof Particle.DustOptions;
/*     */     }
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object parseDataOptions(MythicLineConfig config) {
/* 148 */     Particle particle = toBukkitParticle();
/*     */     
/* 150 */     if (particle.getDataType().equals(ItemStack.class)) {
/* 151 */       String strMaterial = config.getString(new String[] { "material", "m" }, "STONE", new String[0]);
/*     */       try {
/* 153 */         return new ItemStack(Material.matchMaterial(strMaterial.toUpperCase()));
/* 154 */       } catch (Exception ex) {
/* 155 */         return new ItemStack(Material.STONE);
/*     */       } 
/*     */     } 
/* 158 */     if (particle.getDataType().equals(BlockData.class)) {
/* 159 */       String strMaterial = config.getString(new String[] { "material", "m" }, "STONE", new String[0]);
/*     */       try {
/* 161 */         Material material = Material.matchMaterial(strMaterial.toUpperCase());
/* 162 */         return Bukkit.getServer().createBlockData(material);
/* 163 */       } catch (Exception ex) {
/* 164 */         return Bukkit.getServer().createBlockData(Material.STONE);
/*     */       } 
/*     */     } 
/* 167 */     if (particle.getDataType().equals(MaterialData.class)) {
/* 168 */       String strMaterial = config.getString(new String[] { "material", "m" }, "STONE", new String[0]);
/*     */       try {
/* 170 */         Material.matchMaterial(strMaterial.toUpperCase()).getData();
/* 171 */       } catch (Exception ex) {
/* 172 */         return Material.STONE.getData();
/*     */       } 
/*     */     } 
/* 175 */     if (particle.getDataType().equals(Particle.DustOptions.class)) {
/* 176 */       String strColor = config.getString(new String[] { "color", "c" }, "#FF0000", new String[0]);
/* 177 */       Color color = Color.decode(strColor);
/* 178 */       float size = config.getFloat(new String[] { "size" }, 1.0F);
/*     */       
/* 180 */       int r = color.getRed();
/* 181 */       int g = color.getGreen();
/* 182 */       int b = color.getBlue();
/* 183 */       Color c = Color.fromRGB(r, g, b);
/*     */       
/* 185 */       return new Particle.DustOptions(c, size);
/*     */     } 
/* 187 */     return null;
/*     */   }
/*     */   
/*     */   public void send(AbstractLocation location) {
/* 191 */     Particle particle = toBukkitParticle();
/* 192 */     Location loc = BukkitAdapter.adapt(location);
/* 193 */     for (Player player : Bukkit.getOnlinePlayers()) {
/* 194 */       player.spawnParticle(particle, loc, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public void send(AbstractPlayer target, AbstractLocation location, float speed, int amount, float offsetX, float offsetY, float offsetZ) {
/* 199 */     Particle particle = toBukkitParticle();
/* 200 */     Location loc = BukkitAdapter.adapt(location);
/* 201 */     Player player = (Player)target.getBukkitEntity();
/*     */     
/* 203 */     player.spawnParticle(particle, loc, amount, offsetX, offsetY, offsetZ, speed);
/*     */   }
/*     */   
/*     */   public void send(AbstractLocation location, float speed, int amount, float offsetX, float offsetY, float offsetZ) {
/* 207 */     Particle particle = toBukkitParticle();
/* 208 */     Location loc = BukkitAdapter.adapt(location);
/* 209 */     for (Player player : Bukkit.getOnlinePlayers()) {
/* 210 */       if (player.getWorld().equals(loc.getWorld())) {
/* 211 */         player.spawnParticle(particle, loc, amount, offsetX, offsetY, offsetZ, speed);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void send(AbstractLocation location, float speed, int amount, float offsetX, float offsetY, float offsetZ, Object data) {
/* 217 */     if (!validateData(data)) {
/* 218 */       MythicLogger.error("Could not send particle: invalid particle data supplied.");
/*     */       return;
/*     */     } 
/* 221 */     Particle particle = toBukkitParticle();
/* 222 */     Location loc = BukkitAdapter.adapt(location);
/* 223 */     for (Player player : Bukkit.getOnlinePlayers()) {
/* 224 */       if (player.getWorld().equals(loc.getWorld())) {
/* 225 */         player.spawnParticle(particle, loc, amount, offsetX, offsetY, offsetZ, speed, data);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendDirectional(AbstractLocation location, float speed, int amount, float offsetX, float offsetY, float offsetZ, AbstractVector direction) {
/* 231 */     Particle particle = toBukkitParticle();
/* 232 */     Location loc = BukkitAdapter.adapt(location);
/*     */     
/* 234 */     for (int i = 0; i < amount; i++) {
/* 235 */       Location ln = loc.clone().add((0.0F - offsetX) + MythicMobs.r.nextDouble() * offsetX * 2.0D, (offsetY - offsetY) + MythicMobs.r.nextDouble() * offsetY * 2.0D, (0.0F - offsetZ) + MythicMobs.r.nextDouble() * offsetZ * 2.0D);
/* 236 */       for (Player player : Bukkit.getOnlinePlayers()) {
/* 237 */         if (player.getWorld().equals(loc.getWorld())) {
/* 238 */           player.spawnParticle(particle, ln, 0, (float)direction.getX(), (float)direction.getY(), (float)direction.getZ(), speed);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendLegacyColored(AbstractLocation location, float speed, int amount, float offsetX, float offsetY, float offsetZ, Color color) {
/* 245 */     Particle particle = toBukkitParticle();
/* 246 */     Location loc = BukkitAdapter.adapt(location);
/*     */     
/* 248 */     float r = color.getRed() / 255.0F;
/* 249 */     float g = color.getGreen() / 255.0F;
/* 250 */     float b = color.getBlue() / 255.0F;
/*     */     
/* 252 */     if (r < 1.1754944E-38F) {
/* 253 */       r = 1.1754944E-38F;
/*     */     }
/*     */     
/* 256 */     for (int i = 0; i < amount; i++) {
/* 257 */       Location ln = loc.clone().add((0.0F - offsetX) + MythicMobs.r.nextDouble() * offsetX * 2.0D, (offsetY - offsetY) + MythicMobs.r.nextDouble() * offsetY * 2.0D, (0.0F - offsetZ) + MythicMobs.r.nextDouble() * offsetZ * 2.0D);
/* 258 */       for (Player player : Bukkit.getOnlinePlayers()) {
/* 259 */         if (player.getWorld().equals(loc.getWorld()))
/* 260 */           player.spawnParticle(particle, ln, 0, r, g, b, (speed > 0.0F) ? speed : 1.0D); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */