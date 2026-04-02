/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_14_R1;
/*     */ 
/*     */ import io.lumine.utils.Schedulers;
/*     */ import io.lumine.utils.reflection.Reflector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileWorldHandler;
/*     */ import net.minecraft.server.v1_14_R1.Block;
/*     */ import net.minecraft.server.v1_14_R1.BlockPosition;
/*     */ import net.minecraft.server.v1_14_R1.DifficultyDamageScaler;
/*     */ import net.minecraft.server.v1_14_R1.Entity;
/*     */ import net.minecraft.server.v1_14_R1.EntityFallingBlock;
/*     */ import net.minecraft.server.v1_14_R1.IBlockData;
/*     */ import net.minecraft.server.v1_14_R1.Packet;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutEntityDestroy;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutEntityVelocity;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutSpawnEntity;
/*     */ import net.minecraft.server.v1_14_R1.Vec3D;
/*     */ import net.minecraft.server.v1_14_R1.World;
/*     */ import net.minecraft.server.v1_14_R1.WorldServer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ public class VolatileWorldHandler_v1_14_R1
/*     */   implements VolatileWorldHandler
/*     */ {
/*     */   public void registerWorldAccess(World world) {}
/*     */   
/*  39 */   Reflector<World> worldAccessList = new Reflector(World.class, new String[] { "v" });
/*     */ 
/*     */ 
/*     */   
/*     */   public void unregisterWorldAccess(World world) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSoundAtLocation(AbstractLocation location, String sound, float volume, float pitch, double radius) {
/*  48 */     Location l = BukkitAdapter.adapt(location);
/*     */     
/*  50 */     l.getWorld().playSound(l, sound, volume, pitch);
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
/*     */   public boolean isChunkLoaded(AbstractWorld world, int x, int z) {
/*  62 */     CraftWorld bukkitWorld = (CraftWorld)BukkitAdapter.adapt(world);
/*     */     
/*  64 */     WorldServer worldServer = bukkitWorld.getHandle();
/*  65 */     return (null != worldServer.getChunkIfLoaded(x, z));
/*     */   }
/*     */   
/*     */   public void doBlockTossEffect(AbstractLocation target, Material material, AbstractVector velocity, int duration, boolean hideSourceBlock) {
/*     */     IBlockData blockState;
/*  70 */     Location location = BukkitAdapter.adapt(target);
/*  71 */     BlockPosition blockPosition = new BlockPosition(target.getBlockX(), target.getBlockY(), target.getBlockZ());
/*     */     
/*  73 */     CraftWorld bukkitWorld = (CraftWorld)location.getWorld();
/*     */     
/*  75 */     WorldServer worldServer = bukkitWorld.getHandle();
/*     */ 
/*     */ 
/*     */     
/*  79 */     if (material == null) {
/*  80 */       blockState = worldServer.getType(blockPosition);
/*     */     } else {
/*  82 */       blockState = ((CraftBlockData)Bukkit.createBlockData(material)).getState();
/*     */     } 
/*     */     
/*  85 */     EntityFallingBlock block = new EntityFallingBlock((World)worldServer, target.getBlockX() + 0.5D, (target.getBlockY() + 1), target.getBlockZ() + 0.5D, blockState);
/*     */     
/*  87 */     PacketPlayOutSpawnEntity packet = new PacketPlayOutSpawnEntity((Entity)block, block.getEntityType(), Block.getCombinedId(blockState), blockPosition);
/*  88 */     PacketPlayOutEntityVelocity packetV = new PacketPlayOutEntityVelocity(block.getId(), new Vec3D(velocity.getX(), velocity.getY(), velocity.getZ()));
/*     */     
/*  90 */     for (Player p : Bukkit.getOnlinePlayers()) {
/*  91 */       if (hideSourceBlock) p.sendBlockChange(location, Material.AIR, (byte)0); 
/*  92 */       (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet);
/*  93 */       (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetV);
/*     */       
/*  95 */       Schedulers.async().runLater(() -> { PacketPlayOutEntityDestroy packet2 = new PacketPlayOutEntityDestroy(new int[] { paramEntityFallingBlock.getId() }, ); (((CraftPlayer)paramPlayer).getHandle()).playerConnection.sendPacket((Packet)packet2); if (paramBoolean) paramPlayer.sendBlockChange(paramLocation, paramLocation.getBlock().getType(), (byte)0);  }duration);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDifficultyScale(AbstractLocation location) {
/* 106 */     BlockPosition pos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
/* 107 */     DifficultyDamageScaler scaler = ((CraftWorld)location.getWorld()).getHandle().getDamageScaler(pos);
/* 108 */     return scaler.b();
/*     */   }
/*     */   
/*     */   public VolatileWorldHandler_v1_14_R1(VolatileCodeHandler handler) {}
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_14_R1\VolatileWorldHandler_v1_14_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */