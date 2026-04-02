/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractBiome;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitWorld;
/*     */ import java.util.HashMap;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BukkitAdapter
/*     */ {
/*  30 */   HashMap<Entity, AbstractEntity> cachedEntities = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbstractEntity adapt(Entity entity) {
/*  38 */     if (entity == null) return null; 
/*  39 */     return (AbstractEntity)new BukkitEntity(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Entity adapt(AbstractEntity entity) {
/*  48 */     if (entity == null) return null; 
/*  49 */     return entity.getBukkitEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbstractPlayer adapt(Player player) {
/*  58 */     if (player == null) return null; 
/*  59 */     return (AbstractPlayer)new BukkitPlayer(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack adapt(AbstractItemStack item) {
/*  68 */     if (item == null) return null; 
/*  69 */     return ((BukkitItemStack)item).build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbstractItemStack adapt(ItemStack item) {
/*  78 */     if (item == null) return null; 
/*  79 */     return (AbstractItemStack)new BukkitItemStack(item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Player adapt(AbstractPlayer player) {
/*  88 */     if (player == null) return null; 
/*  89 */     return (Player)player.getBukkitEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbstractWorld adapt(World world) {
/*  98 */     return (AbstractWorld)new BukkitWorld(world);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static World adapt(AbstractWorld world) {
/* 107 */     if (world instanceof BukkitWorld) {
/* 108 */       return ((BukkitWorld)world).getBukkitWorld();
/*     */     }
/* 110 */     World match = Bukkit.getServer().getWorld(world.getName());
/* 111 */     if (match != null) {
/* 112 */       return match;
/*     */     }
/* 114 */     throw new IllegalArgumentException("Can't find a Bukkit world for " + world);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbstractLocation adapt(Location location) {
/* 125 */     if (location == null) return null; 
/* 126 */     return new AbstractLocation(adapt(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Location adapt(AbstractLocation location) {
/* 135 */     return new Location(
/* 136 */         adapt(location.getWorld()), location
/* 137 */         .getX(), location.getY(), location.getZ(), location
/* 138 */         .getYaw(), location
/* 139 */         .getPitch());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AbstractVector adapt(Vector vector) {
/* 148 */     if (vector == null) return null; 
/* 149 */     return new AbstractVector(vector.getX(), vector.getY(), vector.getZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vector adapt(AbstractVector vector) {
/* 158 */     return new Vector(vector.getX(), vector.getY(), vector.getZ());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static AbstractBiome adapt(Biome b) {
/* 163 */     if (b == null) return null; 
/* 164 */     return new AbstractBiome(b.toString());
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */