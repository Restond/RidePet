/*     */ package lumine.xikage.mythicmobs.skills;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.skills.ParticleMaker;
/*     */ import io.lumine.xikage.mythicmobs.util.ReflectionUtils;
/*     */ import java.awt.Color;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
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
/*     */ public final class ParticlePacket
/*     */ {
/*     */   private static int version;
/*     */   private Class<?> enumParticle;
/*     */   private Constructor<?> packetConstructor;
/*     */   private Method getHandle;
/*     */   private Field playerConnection;
/*     */   private Method sendPacket;
/*     */   private boolean initialized;
/*     */   private ParticleMaker.ParticleType particle;
/*     */   private int[] particleData;
/*     */   private float offsetX;
/*     */   private float offsetY;
/*     */   private float offsetZ;
/*     */   private float speed;
/*     */   private int amount;
/*     */   private final boolean longDistance;
/*     */   private Object packet;
/*     */   public static List<Player> playerCache;
/*     */   
/*     */   @Deprecated
/*     */   public ParticlePacket(String name, Color color, float speed, int amount, boolean longDistance) throws IllegalArgumentException {
/* 151 */     this(name, (color.getRed() / 255), (color.getGreen() / 255), (color.getBlue() / 255), 1.0F, 0, longDistance);
/*     */     
/* 153 */     this.offsetX = color.getRed() / 255.0F;
/* 154 */     this.offsetY = color.getGreen() / 255.0F;
/* 155 */     this.offsetZ = color.getBlue() / 255.0F;
/* 156 */     amount = 0;
/* 157 */     speed = 1.0F;
/*     */     
/* 159 */     if (this.offsetX < 1.1754944E-38F) {
/* 160 */       this.offsetX = 1.1754944E-38F;
/*     */     }
/*     */     
/* 163 */     switch (ParticleMaker.null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$ParticleMaker$ParticleType[this.particle.ordinal()]) {
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 173 */         MythicMobs.debug(2, "Particle type " + this.particle.toString() + " cannot be colored");
/* 174 */         amount = 1;
/*     */         break;
/*     */     } 
/*     */     try {
/* 178 */       this.packet = this.packetConstructor.newInstance(new Object[0]);
/*     */       
/* 180 */       ReflectionUtils.setValue(this.packet, true, "a", this.enumParticle.getEnumConstants()[this.particle.getId()]);
/* 181 */       ReflectionUtils.setValue(this.packet, true, "j", Boolean.valueOf(longDistance));
/* 182 */       if (this.particleData != null) {
/* 183 */         int[] packetData = this.particleData;
/* 184 */         ReflectionUtils.setValue(this.packet, true, "k", packetData);
/*     */       } 
/* 186 */       ReflectionUtils.setValue(this.packet, true, "e", Float.valueOf(this.offsetX));
/* 187 */       ReflectionUtils.setValue(this.packet, true, "f", Float.valueOf(this.offsetY));
/* 188 */       ReflectionUtils.setValue(this.packet, true, "g", Float.valueOf(this.offsetZ));
/* 189 */       ReflectionUtils.setValue(this.packet, true, "h", Float.valueOf(speed));
/* 190 */       ReflectionUtils.setValue(this.packet, true, "i", Integer.valueOf(amount));
/* 191 */     } catch (Exception exception) {
/* 192 */       throw new PacketInstantiationException("Packet instantiation failed", exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ParticlePacket(String name, AbstractVector vector, float speed, int amount, boolean longDistance) throws IllegalArgumentException {
/* 198 */     this(name, (float)vector.getX(), (float)vector.getY(), (float)vector.getZ(), speed, 0, longDistance);
/*     */     
/* 200 */     amount = 0;
/*     */ 
/*     */     
/* 203 */     switch (ParticleMaker.null.$SwitchMap$io$lumine$xikage$mythicmobs$skills$ParticleMaker$ParticleType[this.particle.ordinal()]) {
/*     */       case 1:
/* 205 */         MythicMobs.debug(2, "Particle type " + this.particle.toString() + " cannot be directional");
/*     */         break;
/*     */       case 2:
/* 208 */         MythicMobs.debug(2, "Particle type " + this.particle.toString() + " cannot be directional");
/*     */         break;
/*     */       case 3:
/* 211 */         MythicMobs.debug(2, "Particle type " + this.particle.toString() + " cannot be directional");
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 217 */       this.packet = this.packetConstructor.newInstance(new Object[0]);
/*     */       
/* 219 */       ReflectionUtils.setValue(this.packet, true, "a", this.enumParticle.getEnumConstants()[this.particle.getId()]);
/* 220 */       ReflectionUtils.setValue(this.packet, true, "j", Boolean.valueOf(longDistance));
/* 221 */       if (this.particleData != null) {
/* 222 */         int[] packetData = this.particleData;
/* 223 */         ReflectionUtils.setValue(this.packet, true, "k", packetData);
/*     */       } 
/* 225 */       ReflectionUtils.setValue(this.packet, true, "e", Float.valueOf(this.offsetX));
/* 226 */       ReflectionUtils.setValue(this.packet, true, "f", Float.valueOf(this.offsetY));
/* 227 */       ReflectionUtils.setValue(this.packet, true, "g", Float.valueOf(this.offsetZ));
/* 228 */       ReflectionUtils.setValue(this.packet, true, "h", Float.valueOf(speed));
/* 229 */       ReflectionUtils.setValue(this.packet, true, "i", Integer.valueOf(amount));
/* 230 */     } catch (Exception exception) {
/* 231 */       throw new PacketInstantiationException("Packet instantiation failed", exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ParticlePacket(String name, float offsetX, float offsetY, float offsetZ, float speed, int amount, boolean longDistance) throws IllegalArgumentException {
/* 237 */     initialize();
/*     */     
/* 239 */     MythicMobs.debug(3, "Loading ParticlePacket for particle type " + name);
/*     */     
/* 241 */     this.particle = ParticleMaker.ParticleType.get(name);
/*     */     
/* 243 */     if (this.particle == null) {
/* 244 */       MythicMobs.debug(3, "== Particle type " + name + " is null. Invalid particle type!");
/*     */     }
/*     */     
/* 247 */     if (name.contains("_")) {
/* 248 */       String[] split = name.split("_");
/* 249 */       name = split[0] + "_";
/* 250 */       if (split.length > 1) {
/* 251 */         String[] split2 = split[1].split(":");
/* 252 */         int[] data = new int[split2.length];
/* 253 */         for (int i = 0; i < data.length; i++) {
/* 254 */           data[i] = Integer.parseInt(split2[i]);
/*     */         }
/* 256 */         this.particleData = data;
/*     */       } 
/*     */     } 
/*     */     
/* 260 */     this.offsetX = offsetX;
/* 261 */     this.offsetY = offsetY;
/* 262 */     this.offsetZ = offsetZ;
/* 263 */     this.speed = speed;
/* 264 */     this.amount = amount;
/* 265 */     this.longDistance = longDistance;
/*     */     
/*     */     try {
/* 268 */       this.packet = this.packetConstructor.newInstance(new Object[0]);
/*     */       
/* 270 */       ReflectionUtils.setValue(this.packet, true, "a", this.enumParticle.getEnumConstants()[this.particle.getId()]);
/* 271 */       ReflectionUtils.setValue(this.packet, true, "j", Boolean.valueOf(longDistance));
/* 272 */       if (this.particleData != null) {
/* 273 */         int[] packetData = this.particleData;
/* 274 */         ReflectionUtils.setValue(this.packet, true, "k", packetData);
/*     */       } 
/* 276 */       ReflectionUtils.setValue(this.packet, true, "e", Float.valueOf(offsetX));
/* 277 */       ReflectionUtils.setValue(this.packet, true, "f", Float.valueOf(offsetY));
/* 278 */       ReflectionUtils.setValue(this.packet, true, "g", Float.valueOf(offsetZ));
/* 279 */       ReflectionUtils.setValue(this.packet, true, "h", Float.valueOf(speed));
/* 280 */       ReflectionUtils.setValue(this.packet, true, "i", Integer.valueOf(amount));
/* 281 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void initialize() throws VersionIncompatibleException {
/* 288 */     if (this.initialized) {
/*     */       return;
/*     */     }
/*     */     try {
/* 292 */       String[] split = ReflectionUtils.PackageType.getServerVersion().split("_");
/* 293 */       version = Integer.parseInt(split[1]);
/* 294 */       if (version > 7 && version < 13) {
/* 295 */         this.enumParticle = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EnumParticle");
/*     */       }
/* 297 */       Class<?> packetClass = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass((version < 7) ? "Packet63WorldParticles" : "PacketPlayOutWorldParticles");
/* 298 */       this.packetConstructor = ReflectionUtils.getConstructor(packetClass, new Class[0]);
/* 299 */       this.getHandle = ReflectionUtils.getMethod("CraftPlayer", ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY, "getHandle", new Class[0]);
/* 300 */       this.playerConnection = ReflectionUtils.getField("EntityPlayer", ReflectionUtils.PackageType.MINECRAFT_SERVER, false, "playerConnection");
/* 301 */       this.sendPacket = ReflectionUtils.getMethod(this.playerConnection.getType(), "sendPacket", new Class[] { ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Packet") });
/* 302 */     } catch (Exception exception) {
/* 303 */       throw new VersionIncompatibleException("Your current bukkit version seems to be incompatible with this library", exception);
/*     */     } 
/* 305 */     this.initialized = true;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean isInitialized() {
/* 310 */     return this.initialized;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void send(AbstractLocation center, Player player) throws PacketInstantiationException, PacketSendingException {
/*     */     try {
/* 316 */       ReflectionUtils.setValue(this.packet, true, "b", Float.valueOf((float)center.getX()));
/* 317 */       ReflectionUtils.setValue(this.packet, true, "c", Float.valueOf((float)center.getY()));
/* 318 */       ReflectionUtils.setValue(this.packet, true, "d", Float.valueOf((float)center.getZ()));
/* 319 */     } catch (Exception exception) {
/* 320 */       throw new PacketInstantiationException("Setting particle location failed", exception);
/*     */     } 
/*     */     
/*     */     try {
/* 324 */       this.sendPacket.invoke(this.playerConnection.get(this.getHandle.invoke(player, new Object[0])), new Object[] { this.packet });
/* 325 */     } catch (Exception exception) {
/* 326 */       throw new PacketSendingException("Failed to send the packet to player '" + player.getName() + "'", exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void send(AbstractLocation center, List<Player> players) throws IllegalArgumentException {
/* 332 */     if (players.isEmpty()) {
/* 333 */       throw new IllegalArgumentException("The player list is empty");
/*     */     }
/* 335 */     for (Player player : players) {
/* 336 */       send(center, player);
/*     */     }
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void send(AbstractLocation center, double range) throws IllegalArgumentException {
/* 342 */     if (range < 1.0D) {
/* 343 */       throw new IllegalArgumentException("The range is lower than 1");
/*     */     }
/* 345 */     Location c = BukkitAdapter.adapt(center);
/* 346 */     String worldName = center.getWorld().getName();
/* 347 */     double squared = range * range;
/* 348 */     for (Player player : Bukkit.getOnlinePlayers()) {
/* 349 */       if (!player.getWorld().getName().equals(worldName) || player.getLocation().distanceSquared(c) > squared) {
/*     */         continue;
/*     */       }
/* 352 */       send(center, player);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void sendAsync(AbstractLocation center, int viewDistance) {
/* 360 */     Location c = BukkitAdapter.adapt(center);
/* 361 */     (new Object(this, center, c, viewDistance))
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
/* 382 */       .runTaskAsynchronously((Plugin)MythicMobs.inst());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void sendAsync(List<AbstractLocation> ll, int viewDistance) {
/* 387 */     (new Object(this, ll, viewDistance))
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
/* 411 */       .runTaskAsynchronously((Plugin)MythicMobs.inst());
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\ParticleMaker$ParticlePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */