/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_13_R2;
/*     */ import io.lumine.utils.reflection.Reflector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_13_R2.Block;
/*     */ import net.minecraft.server.v1_13_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_13_R2.DifficultyDamageScaler;
/*     */ import net.minecraft.server.v1_13_R2.EntityFallingBlock;
/*     */ import net.minecraft.server.v1_13_R2.IBlockData;
/*     */ import net.minecraft.server.v1_13_R2.IWorldAccess;
/*     */ import net.minecraft.server.v1_13_R2.Packet;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutEntityDestroy;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutEntityVelocity;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutSpawnEntity;
/*     */ import net.minecraft.server.v1_13_R2.World;
/*     */ import net.minecraft.server.v1_13_R2.WorldServer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class VolatileWorldHandler_v1_13_R2 implements VolatileWorldHandler {
/*  32 */   private final Map<World, IWorldAccess> worldAccessMap = new HashMap<>();
/*     */ 
/*     */   
/*     */   Reflector<World> worldAccessList;
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerWorldAccess(World world) {
/*  40 */     if (this.worldAccessMap.containsKey(world)) {
/*  41 */       unregisterWorldAccess(world);
/*     */     }
/*  43 */     Object object = new Object(this);
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
/*  93 */     this.worldAccessMap.put(world, object);
/*  94 */     ((CraftWorld)world).getHandle().addIWorldAccess((IWorldAccess)object);
/*     */   }
/*     */   public VolatileWorldHandler_v1_13_R2(VolatileCodeHandler handler) {
/*  97 */     this.worldAccessList = new Reflector(World.class, new String[] { "v" });
/*     */   }
/*     */   
/*     */   public void unregisterWorldAccess(World world) {
/* 101 */     if (!this.worldAccessMap.containsKey(world)) {
/*     */       return;
/*     */     }
/* 104 */     WorldServer worldServer = ((CraftWorld)world).getHandle();
/* 105 */     List<IWorldAccess> list = (List<IWorldAccess>)this.worldAccessList.get(worldServer, "v");
/*     */     
/* 107 */     if (list != null) {
/* 108 */       list.remove(this.worldAccessMap.get(world));
/*     */     }
/* 110 */     this.worldAccessMap.remove(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void playSoundAtLocation(AbstractLocation location, String sound, float volume, float pitch, double radius) {
/* 115 */     Location l = BukkitAdapter.adapt(location);
/*     */     
/* 117 */     for (Player player : l.getWorld().getPlayers()) {
/* 118 */       player.playSound(l, sound, volume, pitch);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChunkLoaded(AbstractWorld world, int x, int z) {
/* 124 */     CraftWorld bukkitWorld = (CraftWorld)BukkitAdapter.adapt(world);
/*     */     
/* 126 */     WorldServer worldServer = bukkitWorld.getHandle();
/* 127 */     return (null != worldServer.getChunkIfLoaded(x, z));
/*     */   }
/*     */   
/*     */   public void doBlockTossEffect(AbstractLocation target, Material material, AbstractVector velocity, int duration, boolean hideSourceBlock) {
/*     */     IBlockData blockState;
/* 132 */     Location location = BukkitAdapter.adapt(target);
/* 133 */     BlockPosition blockPosition = new BlockPosition(target.getBlockX(), target.getBlockY(), target.getBlockZ());
/*     */     
/* 135 */     CraftWorld bukkitWorld = (CraftWorld)location.getWorld();
/*     */     
/* 137 */     WorldServer nmsWorld = bukkitWorld.getHandle();
/*     */ 
/*     */ 
/*     */     
/* 141 */     if (material == null) {
/* 142 */       blockState = nmsWorld.getType(blockPosition);
/*     */     } else {
/* 144 */       blockState = ((CraftBlockData)Bukkit.createBlockData(material)).getState();
/*     */     } 
/*     */     
/* 147 */     EntityFallingBlock block = new EntityFallingBlock((World)nmsWorld, target.getBlockX() + 0.5D, (target.getBlockY() + 1), target.getBlockZ() + 0.5D, blockState);
/*     */     
/* 149 */     PacketPlayOutSpawnEntity packet = new PacketPlayOutSpawnEntity((Entity)block, Block.getCombinedId(blockState));
/* 150 */     PacketPlayOutEntityVelocity packetV = new PacketPlayOutEntityVelocity(block.getId(), velocity.getX(), velocity.getY(), velocity.getZ());
/*     */     
/* 152 */     for (Player p : Bukkit.getOnlinePlayers()) {
/* 153 */       if (hideSourceBlock) p.sendBlockChange(location, Material.AIR, (byte)0); 
/* 154 */       (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packet);
/* 155 */       (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)packetV);
/*     */       
/* 157 */       Scheduler.runLaterAsync(() -> { PacketPlayOutEntityDestroy packet2 = new PacketPlayOutEntityDestroy(new int[] { paramEntityFallingBlock.getId() }, ); (((CraftPlayer)paramPlayer).getHandle()).playerConnection.sendPacket((Packet)packet2); if (paramBoolean) paramPlayer.sendBlockChange(paramLocation, paramLocation.getBlock().getType(), (byte)0);  }duration);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDifficultyScale(AbstractLocation location) {
/* 167 */     BlockPosition pos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
/* 168 */     DifficultyDamageScaler scaler = ((CraftWorld)location.getWorld()).getHandle().getDamageScaler(pos);
/* 169 */     return scaler.b();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_13_R2\VolatileWorldHandler_v1_13_R2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */