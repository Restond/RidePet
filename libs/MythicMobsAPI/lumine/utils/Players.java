/*     */ package lumine.utils;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import io.lumine.utils.chat.ColorString;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Stream;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
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
/*     */ public final class Players
/*     */ {
/*     */   public static Player getNullable(UUID uuid) {
/*  38 */     return Bukkit.getServer().getPlayer(uuid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Optional<Player> get(UUID uuid) {
/*  48 */     return Optional.ofNullable(getNullable(uuid));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Player getNullable(String username) {
/*  59 */     return Bukkit.getServer().getPlayerExact(username);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Optional<Player> get(String username) {
/*  69 */     return Optional.ofNullable(getNullable(username));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Collection<Player> all() {
/*  79 */     return Bukkit.getOnlinePlayers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Stream<Player> stream() {
/*  88 */     return all().stream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void forEach(Consumer<Player> consumer) {
/*  97 */     all().forEach(consumer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void forEachIfPlayer(Iterable<Object> objects, Consumer<Player> consumer) {
/* 107 */     for (Object o : objects) {
/* 108 */       if (o instanceof Player) {
/* 109 */         consumer.accept((Player)o);
/*     */       }
/*     */     } 
/*     */   }
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
/*     */   public static Stream<Player> streamInRange(Location center, double radius) {
/* 124 */     return center.getWorld().getNearbyEntities(center, radius, radius, radius).stream().filter(e -> e instanceof Player).map(e -> (Player)e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void forEachInRange(Location center, double radius, Consumer<Player> consumer) {
/* 135 */     center.getWorld().getNearbyEntities(center, radius, radius, radius).stream()
/* 136 */       .filter(e -> e instanceof Player)
/* 137 */       .map(e -> (Player)e)
/* 138 */       .forEach(consumer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void msg(CommandSender sender, String... msgs) {
/* 148 */     for (String s : msgs) {
/* 149 */       sender.sendMessage(ColorString.get(s));
/*     */     }
/*     */   }
/*     */   
/*     */   public static OfflinePlayer getOfflineNullable(UUID uuid) {
/* 154 */     return Bukkit.getServer().getOfflinePlayer(uuid);
/*     */   }
/*     */   
/*     */   public static Optional<OfflinePlayer> getOffline(UUID uuid) {
/* 158 */     return Optional.ofNullable(getOfflineNullable(uuid));
/*     */   }
/*     */ 
/*     */   
/*     */   public static OfflinePlayer getOfflineNullable(String username) {
/* 163 */     return Bukkit.getServer().getOfflinePlayer(username);
/*     */   }
/*     */   
/*     */   public static Optional<OfflinePlayer> getOffline(String username) {
/* 167 */     return Optional.ofNullable(getOfflineNullable(username));
/*     */   }
/*     */   
/*     */   public static Collection<OfflinePlayer> allOffline() {
/* 171 */     return (Collection<OfflinePlayer>)ImmutableList.copyOf((Object[])Bukkit.getOfflinePlayers());
/*     */   }
/*     */   
/*     */   public static Stream<OfflinePlayer> streamOffline() {
/* 175 */     return Arrays.stream(Bukkit.getOfflinePlayers());
/*     */   }
/*     */   
/*     */   public static void forEachOffline(Consumer<OfflinePlayer> consumer) {
/* 179 */     for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
/* 180 */       consumer.accept(player);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void playSound(Player player, Sound sound) {
/* 185 */     player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void playSound(Player player, Location location, Sound sound) {
/* 189 */     player.playSound(location, sound, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void playSound(Location location, Sound sound) {
/* 193 */     location.getWorld().playSound(location, sound, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendBlockChange(Player player, Location loc, Material type, int data) {
/* 198 */     player.sendBlockChange(loc, type, (byte)data);
/*     */   }
/*     */   
/*     */   public static void sendBlockChange(Player player, Block block, Material type, int data) {
/* 202 */     sendBlockChange(player, block.getLocation(), type, data);
/*     */   }
/*     */   
/*     */   public static void sendBlockChange(Player player, Location loc, Material type) {
/* 206 */     sendBlockChange(player, loc, type, 0);
/*     */   }
/*     */   
/*     */   public static void sendBlockChange(Player player, Block block, Material type) {
/* 210 */     sendBlockChange(player, block, type, 0);
/*     */   }
/*     */   
/*     */   public static void spawnParticle(Player player, Location location, Particle particle) {
/* 214 */     player.spawnParticle(particle, location, 1);
/*     */   }
/*     */   
/*     */   public static void spawnParticle(Location location, Particle particle) {
/* 218 */     location.getWorld().spawnParticle(particle, location, 1);
/*     */   }
/*     */   
/*     */   public static void spawnParticle(Player player, Location location, Particle particle, int amount) {
/* 222 */     Preconditions.checkArgument((amount > 0), "amount > 0");
/* 223 */     player.spawnParticle(particle, location, amount);
/*     */   }
/*     */   
/*     */   public static void spawnParticle(Location location, Particle particle, int amount) {
/* 227 */     Preconditions.checkArgument((amount > 0), "amount > 0");
/* 228 */     location.getWorld().spawnParticle(particle, location, amount);
/*     */   }
/*     */   
/*     */   public static void spawnParticleOffset(Player player, Location location, Particle particle, double offset) {
/* 232 */     player.spawnParticle(particle, location, 1, offset, offset, offset);
/*     */   }
/*     */   
/*     */   public static void spawnParticleOffset(Location location, Particle particle, double offset) {
/* 236 */     location.getWorld().spawnParticle(particle, location, 1, offset, offset, offset);
/*     */   }
/*     */   
/*     */   public static void spawnParticleOffset(Player player, Location location, Particle particle, int amount, double offset) {
/* 240 */     Preconditions.checkArgument((amount > 0), "amount > 0");
/* 241 */     player.spawnParticle(particle, location, amount, offset, offset, offset);
/*     */   }
/*     */   
/*     */   public static void spawnParticleOffset(Location location, Particle particle, int amount, double offset) {
/* 245 */     Preconditions.checkArgument((amount > 0), "amount > 0");
/* 246 */     location.getWorld().spawnParticle(particle, location, amount, offset, offset, offset);
/*     */   }
/*     */   
/*     */   public static void spawnEffect(Player player, Location location, Effect effect) {
/* 250 */     player.playEffect(location, effect, null);
/*     */   }
/*     */   
/*     */   public static void spawnEffect(Location location, Effect effect) {
/* 254 */     location.getWorld().playEffect(location, effect, null);
/*     */   }
/*     */   
/*     */   public static void spawnEffect(Player player, Location location, Effect effect, int amount) {
/* 258 */     Preconditions.checkArgument((amount > 0), "amount > 0");
/* 259 */     for (int i = 0; i < amount; i++) {
/* 260 */       player.playEffect(location, effect, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void spawnEffect(Location location, Effect effect, int amount) {
/* 265 */     Preconditions.checkArgument((amount > 0), "amount > 0");
/* 266 */     for (int i = 0; i < amount; i++) {
/* 267 */       location.getWorld().playEffect(location, effect, null);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void resetWalkSpeed(Player player) {
/* 272 */     player.setWalkSpeed(0.2F);
/*     */   }
/*     */   
/*     */   public static void resetFlySpeed(Player player) {
/* 276 */     player.setFlySpeed(0.1F);
/*     */   }
/*     */   
/*     */   private Players() {
/* 280 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\Players.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */