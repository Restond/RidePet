/*     */ package lumine.xikage.mythicmobs.volatilecode;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.util.MathParser;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import io.lumine.xikage.mythicmobs.util.VectorUtils;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileBlockHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileEntityHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileItemHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileWorldHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.CompoundTag_v1_12_R1;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.VolatileAIHandler_v1_12_R1;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.VolatileBlockHandler_v1_12_R1;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.VolatileEntityHandler_v1_12_R1;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.VolatileItemHandler_v1_12_R1;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.VolatileWorldHandler_v1_12_R1;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.server.v1_12_R1.AttributeModifiable;
/*     */ import net.minecraft.server.v1_12_R1.Block;
/*     */ import net.minecraft.server.v1_12_R1.BlockPosition;
/*     */ import net.minecraft.server.v1_12_R1.ChatComponentText;
/*     */ import net.minecraft.server.v1_12_R1.ChatMessageType;
/*     */ import net.minecraft.server.v1_12_R1.CommandAbstract;
/*     */ import net.minecraft.server.v1_12_R1.DamageSource;
/*     */ import net.minecraft.server.v1_12_R1.DragonControllerPhase;
/*     */ import net.minecraft.server.v1_12_R1.Entity;
/*     */ import net.minecraft.server.v1_12_R1.EntityChicken;
/*     */ import net.minecraft.server.v1_12_R1.EntityEnderDragon;
/*     */ import net.minecraft.server.v1_12_R1.EntityFireworks;
/*     */ import net.minecraft.server.v1_12_R1.EntityInsentient;
/*     */ import net.minecraft.server.v1_12_R1.EntityLightning;
/*     */ import net.minecraft.server.v1_12_R1.EntityLiving;
/*     */ import net.minecraft.server.v1_12_R1.EntityPlayer;
/*     */ import net.minecraft.server.v1_12_R1.EntityZombie;
/*     */ import net.minecraft.server.v1_12_R1.EnumDifficulty;
/*     */ import net.minecraft.server.v1_12_R1.EnumParticle;
/*     */ import net.minecraft.server.v1_12_R1.IBlockData;
/*     */ import net.minecraft.server.v1_12_R1.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_12_R1.ICommandListener;
/*     */ import net.minecraft.server.v1_12_R1.Item;
/*     */ import net.minecraft.server.v1_12_R1.ItemStack;
/*     */ import net.minecraft.server.v1_12_R1.MinecraftServer;
/*     */ import net.minecraft.server.v1_12_R1.NBTBase;
/*     */ import net.minecraft.server.v1_12_R1.NBTTagCompound;
/*     */ import net.minecraft.server.v1_12_R1.NBTTagDouble;
/*     */ import net.minecraft.server.v1_12_R1.NBTTagInt;
/*     */ import net.minecraft.server.v1_12_R1.NBTTagList;
/*     */ import net.minecraft.server.v1_12_R1.NBTTagLong;
/*     */ import net.minecraft.server.v1_12_R1.NBTTagString;
/*     */ import net.minecraft.server.v1_12_R1.Packet;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutCustomSoundEffect;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutEntityVelocity;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutResourcePackSend;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutRespawn;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityWeather;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;
/*     */ import net.minecraft.server.v1_12_R1.PlayerConnection;
/*     */ import net.minecraft.server.v1_12_R1.SoundCategory;
/*     */ import net.minecraft.server.v1_12_R1.World;
/*     */ import net.minecraft.server.v1_12_R1.WorldGenEndTrophy;
/*     */ import net.minecraft.server.v1_12_R1.WorldServer;
/*     */ import net.minecraft.server.v1_12_R1.WorldSettings;
/*     */ import net.minecraft.server.v1_12_R1.WorldType;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftChicken;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftZombie;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.Chicken;
/*     */ import org.bukkit.entity.Creeper;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Zombie;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VolatileCodeEnabled_v1_12_R1
/*     */   implements VolatileCodeHandler
/*     */ {
/* 133 */   private final VolatileAIHandler AIHandler = (VolatileAIHandler)new VolatileAIHandler_v1_12_R1(this); public VolatileAIHandler getAIHandler() { return this.AIHandler; }
/*     */   
/* 135 */   private final VolatileBlockHandler blockHandler = (VolatileBlockHandler)new VolatileBlockHandler_v1_12_R1(this); public VolatileBlockHandler getBlockHandler() { return this.blockHandler; }
/*     */   
/* 137 */   private final VolatileEntityHandler entityHandler = (VolatileEntityHandler)new VolatileEntityHandler_v1_12_R1(this); public VolatileEntityHandler getEntityHandler() { return this.entityHandler; }
/*     */   
/* 139 */   private final VolatileItemHandler itemHandler = (VolatileItemHandler)new VolatileItemHandler_v1_12_R1(this); public VolatileItemHandler getItemHandler() { return this.itemHandler; }
/*     */   
/* 141 */   private final VolatileWorldHandler worldHandler = (VolatileWorldHandler)new VolatileWorldHandler_v1_12_R1(this); Field[] packet63Fields; Map<String, EnumParticle> particleMap; public VolatileWorldHandler getWorldHandler() { return this.worldHandler; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag createCompoundTag(Map<String, Tag> value) {
/* 173 */     return (CompoundTag)new CompoundTag_v1_12_R1(value);
/*     */   }
/*     */   
/*     */   public Set<AbstractEntity> getEntitiesBySelector(SkillCaster am, String targetSelector) {
/*     */     List<Entity> targets;
/* 178 */     MythicMobs.debug(4, "------ Running VANILLA Target Selector: " + targetSelector);
/* 179 */     Entity entity = ((CraftEntity)BukkitAdapter.adapt(am.getEntity())).getHandle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 186 */       MinecraftServer ms = MinecraftServer.getServer();
/* 187 */       targets = CommandAbstract.d(ms, (ICommandListener)entity, targetSelector);
/* 188 */     } catch (Exception ex) {
/* 189 */       targets = Collections.emptyList();
/* 190 */       ex.printStackTrace();
/*     */     } 
/*     */     
/* 193 */     MythicMobs.debug(4, "-------- Found " + targets.size() + " targets");
/* 194 */     Set<AbstractEntity> ftargets = new HashSet<>();
/* 195 */     for (Entity target : targets) {
/* 196 */       ftargets.add(BukkitAdapter.adapt((Entity)target.getBukkitEntity()));
/*     */     }
/* 198 */     return ftargets;
/*     */   }
/*     */   
/*     */   public void PlaySoundAtLocation(AbstractLocation location, String sound, float volume, float pitch) {
/* 202 */     Location l = BukkitAdapter.adapt(location);
/* 203 */     for (Player player : l.getWorld().getPlayers()) {
/* 204 */       player.playSound(l, sound, volume, pitch);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void CreateFireworksExplosion(Location loc, boolean flicker, boolean trail, int type, int[] colors, int[] fadeColors, int flightDuration) {
/* 212 */     ItemStack item = new ItemStack(Item.getById(401), 1, 0);
/*     */ 
/*     */ 
/*     */     
/* 216 */     NBTTagCompound tag = item.getTag();
/* 217 */     if (tag == null) {
/* 218 */       tag = new NBTTagCompound();
/*     */     }
/*     */ 
/*     */     
/* 222 */     NBTTagCompound explTag = new NBTTagCompound();
/* 223 */     explTag.setByte("Flicker", flicker ? 1 : 0);
/* 224 */     explTag.setByte("Trail", trail ? 1 : 0);
/* 225 */     explTag.setByte("Type", (byte)type);
/* 226 */     explTag.setIntArray("Colors", colors);
/* 227 */     explTag.setIntArray("FadeColors", fadeColors);
/*     */ 
/*     */     
/* 230 */     NBTTagCompound fwTag = new NBTTagCompound();
/* 231 */     fwTag.setByte("Flight", (byte)flightDuration);
/* 232 */     NBTTagList explList = new NBTTagList();
/* 233 */     explList.add((NBTBase)explTag);
/* 234 */     fwTag.set("Explosions", (NBTBase)explList);
/* 235 */     tag.set("Fireworks", (NBTBase)fwTag);
/*     */ 
/*     */     
/* 238 */     item.setTag(tag);
/*     */ 
/*     */ 
/*     */     
/* 242 */     EntityFireworks fireworks = new EntityFireworks((World)((CraftWorld)loc.getWorld()).getHandle(), loc.getX(), loc.getY(), loc.getZ(), item);
/* 243 */     ((CraftWorld)loc.getWorld()).getHandle().addEntity((Entity)fireworks);
/*     */ 
/*     */     
/* 246 */     if (flightDuration == 0) {
/* 247 */       ((CraftWorld)loc.getWorld()).getHandle().broadcastEntityEffect((Entity)fireworks, (byte)17);
/* 248 */       fireworks.die();
/*     */     } 
/*     */   }
/*     */   
/* 252 */   public VolatileCodeEnabled_v1_12_R1() { this.packet63Fields = new Field[11];
/* 253 */     this.particleMap = new HashMap<>(); try { this.packet63Fields[0] = PacketPlayOutWorldParticles.class.getDeclaredField("a"); this.packet63Fields[1] = PacketPlayOutWorldParticles.class.getDeclaredField("b"); this.packet63Fields[2] = PacketPlayOutWorldParticles.class.getDeclaredField("c"); this.packet63Fields[3] = PacketPlayOutWorldParticles.class.getDeclaredField("d"); this.packet63Fields[4] = PacketPlayOutWorldParticles.class.getDeclaredField("e"); this.packet63Fields[5] = PacketPlayOutWorldParticles.class.getDeclaredField("f"); this.packet63Fields[6] = PacketPlayOutWorldParticles.class.getDeclaredField("g"); this.packet63Fields[7] = PacketPlayOutWorldParticles.class.getDeclaredField("h"); this.packet63Fields[8] = PacketPlayOutWorldParticles.class.getDeclaredField("i"); this.packet63Fields[9] = PacketPlayOutWorldParticles.class.getDeclaredField("j"); this.packet63Fields[10] = PacketPlayOutWorldParticles.class.getDeclaredField("k"); for (int i = 0; i <= 10; i++)
/*     */         this.packet63Fields[i].setAccessible(true);  }
/*     */     catch (Exception e) { e.printStackTrace(); }
/*     */      for (EnumParticle particle : EnumParticle.values()) { if (particle != null)
/*     */         this.particleMap.put(particle.b(), particle);  }
/* 258 */      } public void doParticleEffect(Location location, String name, float spreadHoriz, float spreadVert, int count, float speed, float yOffset, int radius) { PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
/*     */     
/* 260 */     int[] data = null;
/* 261 */     if (name.contains("_")) {
/* 262 */       String[] split = name.split("_");
/* 263 */       name = split[0] + "_";
/* 264 */       if (split.length > 1) {
/* 265 */         String[] split2 = split[1].split(":");
/* 266 */         data = new int[split2.length];
/* 267 */         for (int i = 0; i < data.length; i++) {
/* 268 */           data[i] = Integer.parseInt(split2[i]);
/*     */         }
/*     */       } 
/*     */     } 
/* 272 */     EnumParticle particle = this.particleMap.get(name);
/*     */     
/* 274 */     if (particle == null) {
/* 275 */       MythicMobs.error("Cannot do Particle Effect: Invalid particle specified (particle = " + name + ")");
/*     */       return;
/*     */     } 
/*     */     try {
/* 279 */       this.packet63Fields[0].set(packet, particle);
/* 280 */       this.packet63Fields[1].setFloat(packet, (float)location.getX());
/* 281 */       this.packet63Fields[2].setFloat(packet, (float)location.getY() + yOffset);
/* 282 */       this.packet63Fields[3].setFloat(packet, (float)location.getZ());
/* 283 */       this.packet63Fields[4].setFloat(packet, spreadHoriz);
/* 284 */       this.packet63Fields[5].setFloat(packet, spreadVert);
/* 285 */       this.packet63Fields[6].setFloat(packet, spreadHoriz);
/* 286 */       this.packet63Fields[7].setFloat(packet, speed);
/* 287 */       this.packet63Fields[8].setInt(packet, count);
/* 288 */       this.packet63Fields[9].setBoolean(packet, (radius >= 200));
/* 289 */       if (data != null) {
/* 290 */         this.packet63Fields[10].set(packet, data);
/*     */       }
/*     */       
/* 293 */       int rSq = radius * radius * 2;
/*     */       
/* 295 */       for (Player player : location.getWorld().getPlayers()) {
/* 296 */         if (!player.getWorld().equals(location.getWorld()))
/*     */           continue; 
/* 298 */         if (player.getLocation().distanceSquared(location) <= rSq) {
/* 299 */           (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)packet);
/*     */         }
/*     */       }
/*     */     
/* 303 */     } catch (Exception e) {
/* 304 */       e.printStackTrace();
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxHealth(Entity e, double health) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFollowRange(Entity e, double range) {}
/*     */ 
/*     */   
/*     */   public void setKnockBackResistance(Entity e, double knock) {}
/*     */ 
/*     */   
/*     */   public void setMobSpeed(Entity e, double speed) {}
/*     */ 
/*     */   
/*     */   public void setAttackDamage(Entity e, double damage) {}
/*     */ 
/*     */   
/*     */   public ItemStack setItemAttributes(ItemStack item, String itemName, Map<String, Object> options, Map<String, Map<String, Object>> attributes) {
/* 327 */     ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
/* 328 */     if (nmsItem == null)
/* 329 */       return item; 
/* 330 */     NBTTagList itemAttributes = getItemAttributeList(nmsItem);
/* 331 */     UUID tagUUID = MythicUtil.getUUIDFromString(itemName);
/*     */     
/* 333 */     if (attributes.size() > 0) {
/* 334 */       attributes.forEach((slot, entry) -> entry.forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 386 */       nmsItem.getTag().set("AttributeModifiers", (NBTBase)itemAttributes);
/*     */     } 
/*     */     
/* 389 */     options.forEach((option, value) -> {
/*     */           if (option.equals("PreventStacking")) {
/*     */             UUID rand = UUID.randomUUID();
/*     */ 
/*     */             
/*     */             paramItemStack.getTag().setLong("UUIDMost", rand.getMostSignificantBits());
/*     */ 
/*     */             
/*     */             paramItemStack.getTag().setLong("UUIDLeast", rand.getLeastSignificantBits());
/*     */           } else if (value instanceof Integer) {
/*     */             paramItemStack.getTag().setInt(option, ((Integer)value).intValue());
/*     */           } else if (value instanceof Long) {
/*     */             paramItemStack.getTag().setLong(option, ((Long)value).longValue());
/*     */           } else if (value instanceof Byte) {
/*     */             paramItemStack.getTag().setByte(option, ((Byte)value).byteValue());
/*     */           } else if (value instanceof String) {
/*     */             paramItemStack.getTag().setString(option, (String)value);
/*     */           } else if (value instanceof Byte) {
/*     */             paramItemStack.getTag().setByte(option, ((Byte)value).byteValue());
/*     */           } 
/*     */         });
/*     */     
/* 411 */     return (ItemStack)CraftItemStack.asCraftMirror(nmsItem);
/*     */   }
/*     */   
/*     */   public ItemStack setItemAttribute(String itemName, String attr, double amount, ItemStack item) {
/* 415 */     VolatileCodeHandler.Attributes a = VolatileCodeHandler.Attributes.get(attr);
/* 416 */     int op = -1;
/*     */ 
/*     */     
/* 419 */     ItemStack nms = CraftItemStack.asNMSCopy(item);
/* 420 */     NBTTagList attrmod = getItemAttributeList(nms);
/* 421 */     NBTTagCompound c = new NBTTagCompound();
/* 422 */     if (op == -1) {
/* 423 */       op = a.op;
/*     */     }
/* 425 */     c.set("Name", (NBTBase)new NBTTagString(attr));
/* 426 */     c.set("AttributeName", (NBTBase)new NBTTagString(a.name));
/* 427 */     c.set("Amount", (NBTBase)new NBTTagDouble(amount));
/*     */     
/* 429 */     c.set("Operation", (NBTBase)new NBTTagInt(op));
/*     */     
/* 431 */     UUID tagUUID = MythicUtil.getUUIDFromString(itemName);
/* 432 */     c.set("UUIDMost", (NBTBase)new NBTTagLong(tagUUID.getMostSignificantBits()));
/* 433 */     c.set("UUIDLeast", (NBTBase)new NBTTagLong(tagUUID.getLeastSignificantBits()));
/* 434 */     attrmod.add((NBTBase)c);
/* 435 */     nms.getTag().set("AttributeModifiers", (NBTBase)attrmod);
/* 436 */     return (ItemStack)CraftItemStack.asCraftMirror(nms);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList getItemAttributeList(ItemStack nms) {
/* 442 */     if (nms == null)
/* 443 */       return null; 
/* 444 */     if (nms.getTag() == null) {
/* 445 */       nms.setTag(new NBTTagCompound());
/*     */     }
/* 447 */     NBTTagList attrmod = nms.getTag().getList("AttributeModifiers", 10);
/* 448 */     if (attrmod == null) {
/* 449 */       nms.getTag().set("AttributeModifiers", (NBTBase)new NBTTagList());
/*     */     }
/* 451 */     return nms.getTag().getList("AttributeModifiers", 10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void listItemAttributes(Player player) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreeperFuseTicks(Creeper c, int t) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreeperExplosionRadius(Creeper c, int r) {}
/*     */ 
/*     */   
/*     */   public void setZombieSpawnReinforcements(Zombie z, double d) {
/* 468 */     EntityZombie zombie = ((CraftZombie)z).getHandle();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 473 */     AttributeModifiable ar = (AttributeModifiable)zombie.getAttributeMap().a("Spawn Reinforcements Chance");
/*     */     
/* 475 */     ar.setValue(d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntitySilent(Entity e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChickenHostile(Chicken c) {
/* 491 */     EntityChicken chicken = ((CraftChicken)c).getHandle();
/* 492 */     chicken.o(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack setItemUnbreakable(ItemStack i) {
/* 497 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack setItemHideFlags(ItemStack i) {
/* 502 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {}
/*     */ 
/*     */   
/*     */   public void sendActionBarMessageToPlayer(Player player, String message) {
/* 511 */     PlayerConnection conn = (((CraftPlayer)player).getHandle()).playerConnection;
/* 512 */     PacketPlayOutChat packet = new PacketPlayOutChat((IChatBaseComponent)new ChatComponentText(message), ChatMessageType.GAME_INFO);
/* 513 */     conn.sendPacket((Packet)packet);
/*     */   }
/*     */   
/*     */   private static NBTTagCompound getTag(ItemStack item) {
/* 517 */     if (item instanceof CraftItemStack) {
/*     */       try {
/* 519 */         Field field = CraftItemStack.class.getDeclaredField("handle");
/* 520 */         field.setAccessible(true);
/* 521 */         return ((ItemStack)field.get(item)).getTag();
/* 522 */       } catch (Exception exception) {}
/*     */     }
/*     */     
/* 525 */     return null;
/*     */   }
/*     */   
/*     */   private static ItemStack setTag(ItemStack item, NBTTagCompound tag) {
/* 529 */     CraftItemStack craftItem = null;
/* 530 */     if (item instanceof CraftItemStack) {
/* 531 */       craftItem = (CraftItemStack)item;
/*     */     } else {
/* 533 */       craftItem = CraftItemStack.asCraftCopy(item);
/*     */     } 
/*     */     
/* 536 */     ItemStack nmsItem = null;
/*     */     try {
/* 538 */       Field field = CraftItemStack.class.getDeclaredField("handle");
/* 539 */       field.setAccessible(true);
/* 540 */       nmsItem = (ItemStack)field.get(item);
/* 541 */     } catch (Exception exception) {}
/*     */     
/* 543 */     if (nmsItem == null) {
/* 544 */       nmsItem = CraftItemStack.asNMSCopy((ItemStack)craftItem);
/*     */     }
/*     */     
/* 547 */     nmsItem.setTag(tag);
/*     */     try {
/* 549 */       Field field = CraftItemStack.class.getDeclaredField("handle");
/* 550 */       field.setAccessible(true);
/* 551 */       field.set(craftItem, nmsItem);
/* 552 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 555 */     return (ItemStack)craftItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendWorldEnvironment(AbstractPlayer player, String env) {
/* 560 */     World.Environment environment = World.Environment.valueOf(env);
/*     */     
/* 562 */     CraftPlayer craftPlayer = (CraftPlayer)player.getBukkitEntity();
/* 563 */     CraftWorld world = (CraftWorld)craftPlayer.getWorld();
/* 564 */     Location location = player.getBukkitEntity().getLocation();
/*     */ 
/*     */ 
/*     */     
/* 568 */     PacketPlayOutRespawn packet = new PacketPlayOutRespawn(environment.getId(), EnumDifficulty.getById(world.getDifficulty().getValue()), WorldType.NORMAL, WorldSettings.a(((Player)player.getBukkitEntity()).getGameMode().getValue()));
/*     */     
/* 570 */     (craftPlayer.getHandle()).playerConnection.sendPacket((Packet)packet);
/*     */     
/* 572 */     int viewDistance = MythicMobs.inst().getServer().getViewDistance();
/*     */     
/* 574 */     int xMin = location.getChunk().getX() - viewDistance;
/* 575 */     int xMax = location.getChunk().getX() + viewDistance;
/* 576 */     int zMin = location.getChunk().getZ() - viewDistance;
/* 577 */     int zMax = location.getChunk().getZ() + viewDistance;
/*     */     
/* 579 */     for (int x = xMin; x < xMax; x++) {
/* 580 */       for (int z = zMin; z < zMax; z++) {
/* 581 */         world.refreshChunk(x, z);
/*     */       }
/*     */     } 
/*     */     
/* 585 */     craftPlayer.updateInventory();
/*     */     
/* 587 */     Location tp = new Location((World)world, 0.0D, 0.0D, 0.0D);
/*     */     
/* 589 */     craftPlayer.teleport(tp);
/* 590 */     craftPlayer.teleport(location);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoAI(ActiveMob am) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void playLocalizedLightningEffect(AbstractLocation target, double radius) {
/* 600 */     Location location = BukkitAdapter.adapt(target);
/* 601 */     double distanceSquared = Math.pow(radius, 2.0D);
/*     */     
/* 603 */     for (Player p : location.getWorld().getPlayers()) {
/* 604 */       if (location.distanceSquared(p.getLocation()) <= distanceSquared) {
/* 605 */         (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutSpawnEntityWeather((Entity)new EntityLightning(((CraftPlayer)p)
/* 606 */                 .getHandle().getWorld(), location.getX(), location
/* 607 */                 .getY(), location.getZ(), false, false)));
/* 608 */         (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutCustomSoundEffect("entity.lightning.thunder", SoundCategory.WEATHER, location
/*     */               
/* 610 */               .getX(), location.getY(), location.getZ(), 10000.0F, 63.0F));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doDamage(ActiveMob mob, AbstractEntity t, float amount) {
/* 617 */     CraftLivingEntity caster = (CraftLivingEntity)mob.getEntity().getBukkitEntity();
/* 618 */     CraftLivingEntity target = (CraftLivingEntity)t.getBukkitEntity();
/*     */     
/* 620 */     target.getHandle().damageEntity(DamageSource.mobAttack(caster.getHandle()), amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAbsorptionHearts(LivingEntity entity) {
/* 625 */     return ((CraftLivingEntity)entity).getHandle().getAbsorptionHearts();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClientVelocity(Player player, Vector velocity) {
/* 630 */     (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutEntityVelocity(player
/* 631 */           .getEntityId(), velocity.getX(), velocity.getY(), velocity.getZ()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveSkinData(Player player, String name) {
/* 636 */     GameProfile profile = ((CraftPlayer)player).getHandle().getProfile();
/* 637 */     Collection<Property> props = profile.getProperties().get("textures");
/* 638 */     Iterator<Property> iterator = props.iterator(); if (iterator.hasNext()) { Property prop = iterator.next();
/* 639 */       String skin = prop.getValue();
/* 640 */       String sig = prop.getSignature();
/*     */       
/* 642 */       File folder = new File(MythicMobs.inst().getDataFolder(), "PlayerSkins");
/* 643 */       if (!folder.exists()) {
/* 644 */         folder.mkdir();
/*     */       }
/* 646 */       File skinFile = new File(folder, name + ".skin.txt");
/* 647 */       File sigFile = new File(folder, name + ".sig.txt");
/*     */       try {
/* 649 */         FileWriter writer = new FileWriter(skinFile);
/* 650 */         writer.write(skin);
/* 651 */         writer.flush();
/* 652 */         writer.close();
/* 653 */         writer = new FileWriter(sigFile);
/* 654 */         writer.write(sig);
/* 655 */         writer.flush();
/* 656 */         writer.close();
/* 657 */       } catch (Exception e) {
/* 658 */         e.printStackTrace();
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private GameProfile setTexture(GameProfile profile, String texture, String signature) {
/* 671 */     if (signature == null || signature.equalsIgnoreCase("")) {
/* 672 */       profile.getProperties().put("textures", new Property("textures", texture));
/*     */     } else {
/* 674 */       profile.getProperties().put("textures", new Property("textures", texture, signature));
/*     */     } 
/* 676 */     return profile;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getItemRecharge(Player player) {
/* 681 */     EntityPlayer ep = ((CraftPlayer)player).getHandle();
/* 682 */     return ep.n(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getItemRecharging(Player player) {
/* 687 */     EntityPlayer ep = ((CraftPlayer)player).getHandle();
/* 688 */     return (ep.n(0.0F) < 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doEffectArmSwing(AbstractEntity entity) {
/* 693 */     Entity e = ((CraftEntity)entity.getBukkitEntity()).getHandle();
/* 694 */     e.getWorld().broadcastEntityEffect(e, (byte)4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnderDragonAI(Entity entity) {
/* 699 */     if (!(entity instanceof org.bukkit.entity.EnderDragon)) {
/*     */       return;
/*     */     }
/* 702 */     EntityEnderDragon dragon = (EntityEnderDragon)((CraftEntity)entity).getHandle();
/* 703 */     dragon.getDragonControllerManager().setControllerPhase(DragonControllerPhase.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnderDragonPathPoint(AbstractLocation location) {
/*     */     try {
/* 709 */       setFinalStatic(WorldGenEndTrophy.class.getField("a"), new BlockPosition(location
/* 710 */             .getBlockX(), location.getBlockY(), location.getBlockZ()));
/* 711 */     } catch (NoSuchFieldException e) {
/*     */       
/* 713 */       e.printStackTrace();
/* 714 */     } catch (SecurityException e) {
/*     */       
/* 716 */       e.printStackTrace();
/* 717 */     } catch (Exception e) {
/*     */       
/* 719 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setFinalStatic(Field field, Object newValue) throws Exception {
/* 724 */     field.setAccessible(true);
/*     */     
/* 726 */     Field modifiersField = Field.class.getDeclaredField("modifiers");
/* 727 */     modifiersField.setAccessible(true);
/* 728 */     modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
/*     */     
/* 730 */     field.set(null, newValue);
/*     */   }
/*     */   
/* 733 */   private static final Set<EntityType> BAD_CONTROLLER_LOOK = EnumSet.of(EntityType.BAT, new EntityType[] { EntityType.ENDERMITE, EntityType.ENDER_DRAGON, EntityType.GHAST, EntityType.HORSE, EntityType.MAGMA_CUBE, EntityType.POLAR_BEAR, EntityType.SILVERFISH, EntityType.SLIME });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void lookAt(AbstractEntity entity, float yaw, float pitch) {
/* 739 */     Entity handle = ((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/*     */     
/* 741 */     yaw = VectorUtils.clampYaw(yaw);
/* 742 */     handle.yaw = yaw;
/* 743 */     setHeadYaw(entity, yaw);
/* 744 */     handle.pitch = pitch;
/*     */   }
/*     */ 
/*     */   
/*     */   public void lookAtLocation(AbstractEntity entity, AbstractLocation to, boolean headOnly, boolean immediate) {
/* 749 */     Entity handle = ((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/*     */     
/* 751 */     if (immediate || headOnly || BAD_CONTROLLER_LOOK.contains(handle.getBukkitEntity().getType()) || !(handle instanceof EntityInsentient)) {
/*     */ 
/*     */       
/* 754 */       AbstractLocation fromLocation = entity.getLocation();
/*     */       
/* 756 */       double xDiff = to.getX() - fromLocation.getX();
/* 757 */       double yDiff = to.getY() - fromLocation.getY();
/* 758 */       double zDiff = to.getZ() - fromLocation.getZ();
/*     */       
/* 760 */       double distanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
/* 761 */       double distanceY = Math.sqrt(distanceXZ * distanceXZ + yDiff * yDiff);
/*     */       
/* 763 */       double yaw = Math.toDegrees(Math.acos(xDiff / distanceXZ));
/* 764 */       double pitch = Math.toDegrees(Math.acos(yDiff / distanceY)) - 90.0D;
/*     */       
/* 766 */       if (zDiff < 0.0D) {
/* 767 */         yaw += Math.abs(180.0D - yaw) * 2.0D;
/*     */       }
/*     */       
/* 770 */       yaw -= 90.0D;
/*     */       
/* 772 */       if (headOnly) {
/* 773 */         setHeadYaw(entity, (float)yaw);
/*     */       } else {
/* 775 */         lookAt(entity, (float)yaw, (float)pitch);
/*     */       } 
/*     */       return;
/*     */     } 
/* 779 */     if (handle instanceof EntityInsentient) {
/* 780 */       ((EntityInsentient)handle).getControllerLook().a(to.getX(), to.getY(), to.getZ(), ((EntityInsentient)handle)
/* 781 */           .O(), ((EntityInsentient)handle).N());
/*     */       
/* 783 */       while (((EntityLiving)handle).aP >= 180.0F) {
/* 784 */         ((EntityLiving)handle).aP -= 360.0F;
/*     */       }
/* 786 */       while (((EntityLiving)handle).aP < -180.0F) {
/* 787 */         ((EntityLiving)handle).aP += 360.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void lookAtEntity(AbstractEntity entity, AbstractEntity to, boolean headOnly, boolean immediate) {
/* 794 */     Entity handle = ((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/* 795 */     Entity target = ((CraftEntity)BukkitAdapter.adapt(to)).getHandle();
/*     */     
/* 797 */     if (BAD_CONTROLLER_LOOK.contains(handle.getBukkitEntity().getType())) {
/* 798 */       if (to.isLiving()) {
/* 799 */         lookAtLocation(entity, to.getEyeLocation(), headOnly, immediate);
/*     */       } else {
/* 801 */         lookAtLocation(entity, to.getLocation(), headOnly, immediate);
/*     */       } 
/* 803 */     } else if (handle instanceof EntityInsentient) {
/* 804 */       EntityInsentient insentient = (EntityInsentient)handle;
/* 805 */       insentient.getControllerLook().a(target, insentient.O(), insentient.N());
/*     */       
/* 807 */       while (insentient.aP >= 180.0F) {
/* 808 */         insentient.aP -= 360.0F;
/*     */       }
/* 810 */       while (((EntityLiving)handle).aP < -180.0F) {
/* 811 */         insentient.aP += 360.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeadYaw(AbstractEntity entity, float yaw) {
/* 818 */     if (!entity.isLiving()) {
/*     */       return;
/*     */     }
/*     */     
/* 822 */     EntityLiving handle = (EntityLiving)((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/* 823 */     yaw = VectorUtils.clampYaw(yaw);
/* 824 */     handle.aO = yaw;
/* 825 */     if (!(handle instanceof net.minecraft.server.v1_12_R1.EntityHuman)) {
/* 826 */       handle.aN = yaw;
/*     */     }
/* 828 */     handle.aP = yaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyPhysics(Block target) {
/* 833 */     Location location = target.getLocation();
/* 834 */     WorldServer worldServer = ((CraftWorld)location.getWorld()).getHandle();
/*     */     
/* 836 */     BlockPosition blockposition = new BlockPosition(location.getX(), location.getY(), location.getZ());
/* 837 */     IBlockData iblockdata = worldServer.getType(blockposition);
/* 838 */     Block block = iblockdata.getBlock();
/*     */     
/* 840 */     worldServer.applyPhysics(blockposition, block, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendResourcePack(AbstractPlayer player, String url, String hash) {
/* 845 */     EntityPlayer p = ((CraftPlayer)player.getBukkitEntity()).getHandle();
/* 846 */     p.playerConnection.sendPacket((Packet)new PacketPlayOutResourcePackSend(url, hash));
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\VolatileCodeEnabled_v1_12_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */