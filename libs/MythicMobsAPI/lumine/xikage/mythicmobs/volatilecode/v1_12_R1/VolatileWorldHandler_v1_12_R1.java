/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_12_R1;
/*     */ import io.lumine.utils.reflection.Reflector;
/*     */ import io.lumine.utils.tasks.Scheduler;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_12_R1.BlockPosition;
/*     */ import net.minecraft.server.v1_12_R1.DifficultyDamageScaler;
/*     */ import net.minecraft.server.v1_12_R1.Entity;
/*     */ import net.minecraft.server.v1_12_R1.EntityFallingBlock;
/*     */ import net.minecraft.server.v1_12_R1.IBlockData;
/*     */ import net.minecraft.server.v1_12_R1.IWorldAccess;
/*     */ import net.minecraft.server.v1_12_R1.Packet;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutEntityVelocity;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntity;
/*     */ import net.minecraft.server.v1_12_R1.World;
/*     */ import net.minecraft.server.v1_12_R1.WorldServer;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class VolatileWorldHandler_v1_12_R1 implements VolatileWorldHandler {
/*  31 */   private final Map<World, IWorldAccess> worldAccessMap = new HashMap<>();
/*     */ 
/*     */   
/*     */   Reflector<World> worldAccessList;
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerWorldAccess(World world) {
/*  39 */     if (this.worldAccessMap.containsKey(world)) {
/*  40 */       unregisterWorldAccess(world);
/*     */     }
/*  42 */     Object object = new Object(this);
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
/*  92 */     this.worldAccessMap.put(world, object);
/*  93 */     ((CraftWorld)world).getHandle().addIWorldAccess((IWorldAccess)object);
/*     */   }
/*     */   public VolatileWorldHandler_v1_12_R1(VolatileCodeHandler handler) {
/*  96 */     this.worldAccessList = new Reflector(World.class, new String[] { "u" });
/*     */   }
/*     */   
/*     */   public void unregisterWorldAccess(World world) {
/* 100 */     if (!this.worldAccessMap.containsKey(world)) {
/*     */       return;
/*     */     }
/* 103 */     WorldServer worldServer = ((CraftWorld)world).getHandle();
/* 104 */     List<IWorldAccess> list = (List<IWorldAccess>)this.worldAccessList.get(worldServer, "u");
/*     */     
/* 106 */     if (list != null) {
/* 107 */       list.remove(this.worldAccessMap.get(world));
/*     */     }
/* 109 */     this.worldAccessMap.remove(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void playSoundAtLocation(AbstractLocation location, String sound, float volume, float pitch, double radius) {
/* 114 */     Location l = BukkitAdapter.adapt(location);
/* 115 */     for (Player player : l.getWorld().getPlayers()) {
/* 116 */       player.playSound(l, sound, volume, pitch);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChunkLoaded(AbstractWorld world, int x, int z) {
/* 122 */     CraftWorld bukkitWorld = (CraftWorld)BukkitAdapter.adapt(world);
/*     */     
/* 124 */     WorldServer worldServer = bukkitWorld.getHandle();
/* 125 */     return (null != worldServer.getChunkIfLoaded(x, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public void doBlockTossEffect(AbstractLocation target, Material material, AbstractVector velocity, int duration, boolean hideSourceBlock) {
/* 130 */     Location location = BukkitAdapter.adapt(target);
/* 131 */     BlockPosition blockPosition = new BlockPosition(target.getBlockX(), target.getBlockY(), target.getBlockZ());
/*     */     
/* 133 */     CraftWorld bukkitWorld = (CraftWorld)location.getWorld();
/*     */     
/* 135 */     WorldServer nmsWorld = bukkitWorld.getHandle();
/*     */ 
/*     */ 
/*     */     
/* 139 */     IBlockData blockState = nmsWorld.getType(blockPosition);
/*     */     
/* 141 */     EntityFallingBlock block = new EntityFallingBlock((World)nmsWorld, target.getBlockX() + 0.5D, (target.getBlockY() + 1), target.getBlockZ() + 0.5D, blockState);
/*     */     
/* 143 */     PacketPlayOutSpawnEntity packet = new PacketPlayOutSpawnEntity((Entity)block, Block.getCombinedId(blockState));
/* 144 */     PacketPlayOutEntityVelocity packetV = new PacketPlayOutEntityVelocity(block.getId(), velocity.getX(), velocity.getY(), velocity.getZ());
/*     */     
/* 146 */     for (Player p : Bukkit.getOnlinePlayers()) {
/* 147 */       if (hideSourceBlock) p.sendBlockChange(location, Material.AIR, (byte)0); 
/* 148 */       (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet);
/* 149 */       (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetV);
/*     */       
/* 151 */       Scheduler.runLaterAsync(() -> { PacketPlayOutEntityDestroy packet2 = new PacketPlayOutEntityDestroy(new int[] { paramEntityFallingBlock.getId() }, ); (((CraftPlayer)paramPlayer).getHandle()).playerConnection.sendPacket((Packet)packet2); if (paramBoolean) paramPlayer.sendBlockChange(paramLocation, paramLocation.getBlock().getType(), (byte)0);  }duration);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDifficultyScale(AbstractLocation location) {
/* 161 */     BlockPosition pos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
/* 162 */     DifficultyDamageScaler scaler = ((CraftWorld)location.getWorld()).getHandle().D(pos);
/* 163 */     return scaler.b();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_12_R1\VolatileWorldHandler_v1_12_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */