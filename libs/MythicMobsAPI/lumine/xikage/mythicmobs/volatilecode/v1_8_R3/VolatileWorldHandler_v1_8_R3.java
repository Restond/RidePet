/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_8_R3;
/*     */ 
/*     */ import io.lumine.utils.reflection.Reflector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileWorldHandler;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_8_R3.BlockPosition;
/*     */ import net.minecraft.server.v1_8_R3.DifficultyDamageScaler;
/*     */ import net.minecraft.server.v1_8_R3.IWorldAccess;
/*     */ import net.minecraft.server.v1_8_R3.World;
/*     */ import net.minecraft.server.v1_8_R3.WorldServer;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
/*     */ 
/*     */ 
/*     */ public class VolatileWorldHandler_v1_8_R3
/*     */   implements VolatileWorldHandler
/*     */ {
/*  24 */   private final Map<World, IWorldAccess> worldAccessMap = new HashMap<>();
/*     */ 
/*     */   
/*     */   Reflector<World> worldAccessList;
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerWorldAccess(World world) {
/*  32 */     if (this.worldAccessMap.containsKey(world)) {
/*  33 */       unregisterWorldAccess(world);
/*     */     }
/*  35 */     Object object = new Object(this);
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
/* 112 */     this.worldAccessMap.put(world, object);
/* 113 */     ((CraftWorld)world).getHandle().addIWorldAccess((IWorldAccess)object);
/*     */   }
/*     */   public VolatileWorldHandler_v1_8_R3(VolatileCodeHandler handler) {
/* 116 */     this.worldAccessList = new Reflector(World.class, new String[] { "u" });
/*     */   }
/*     */   
/*     */   public void unregisterWorldAccess(World world) {
/* 120 */     if (!this.worldAccessMap.containsKey(world)) {
/*     */       return;
/*     */     }
/* 123 */     WorldServer worldServer = ((CraftWorld)world).getHandle();
/* 124 */     List<IWorldAccess> list = (List<IWorldAccess>)this.worldAccessList.get(worldServer, "u");
/*     */     
/* 126 */     if (list != null) {
/* 127 */       list.remove(this.worldAccessMap.get(world));
/*     */     }
/* 129 */     this.worldAccessMap.remove(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void playSoundAtLocation(AbstractLocation location, String sound, float volume, float pitch, double radius) {
/* 134 */     ((CraftWorld)BukkitAdapter.adapt(location.getWorld())).getHandle().makeSound(location.getX(), location.getY(), location.getZ(), sound, volume, pitch);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isChunkLoaded(AbstractWorld world, int x, int z) {
/* 139 */     CraftWorld bukkitWorld = (CraftWorld)BukkitAdapter.adapt(world);
/*     */     
/* 141 */     WorldServer worldServer = bukkitWorld.getHandle();
/* 142 */     return (null != worldServer.getChunkIfLoaded(x, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDifficultyScale(AbstractLocation location) {
/* 147 */     BlockPosition pos = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
/* 148 */     DifficultyDamageScaler scaler = ((CraftWorld)location.getWorld()).getHandle().E(pos);
/* 149 */     return scaler.c();
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_8_R3\VolatileWorldHandler_v1_8_R3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */