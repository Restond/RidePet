/*     */ package lumine.xikage.mythicmobs.volatilecode;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileBlockHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileEntityHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileItemHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileWorldHandler;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.server.v1_8_R3.AttributeInstance;
/*     */ import net.minecraft.server.v1_8_R3.AttributeModifiable;
/*     */ import net.minecraft.server.v1_8_R3.Block;
/*     */ import net.minecraft.server.v1_8_R3.BlockPosition;
/*     */ import net.minecraft.server.v1_8_R3.ChatComponentText;
/*     */ import net.minecraft.server.v1_8_R3.Entity;
/*     */ import net.minecraft.server.v1_8_R3.EntityCreeper;
/*     */ import net.minecraft.server.v1_8_R3.EntityFireworks;
/*     */ import net.minecraft.server.v1_8_R3.EntityInsentient;
/*     */ import net.minecraft.server.v1_8_R3.EntityZombie;
/*     */ import net.minecraft.server.v1_8_R3.EnumParticle;
/*     */ import net.minecraft.server.v1_8_R3.GenericAttributes;
/*     */ import net.minecraft.server.v1_8_R3.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_8_R3.ItemStack;
/*     */ import net.minecraft.server.v1_8_R3.NBTBase;
/*     */ import net.minecraft.server.v1_8_R3.NBTTagCompound;
/*     */ import net.minecraft.server.v1_8_R3.NBTTagList;
/*     */ import net.minecraft.server.v1_8_R3.Packet;
/*     */ import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
/*     */ import net.minecraft.server.v1_8_R3.PacketPlayOutRespawn;
/*     */ import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
/*     */ import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
/*     */ import net.minecraft.server.v1_8_R3.PlayerConnection;
/*     */ import net.minecraft.server.v1_8_R3.WorldServer;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.Creeper;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Zombie;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class VolatileCodeEnabled_v1_8_R3 implements VolatileCodeHandler {
/*  59 */   private final VolatileAIHandler AIHandler = (VolatileAIHandler)new VolatileAIHandler_v1_8_R3(this); public VolatileAIHandler getAIHandler() { return this.AIHandler; }
/*  60 */    private final VolatileBlockHandler blockHandler = (VolatileBlockHandler)new VolatileBlockHandler_v1_8_R3(this); public VolatileBlockHandler getBlockHandler() { return this.blockHandler; }
/*  61 */    private final VolatileEntityHandler entityHandler = (VolatileEntityHandler)new VolatileEntityHandler_v1_8_R3(this); public VolatileEntityHandler getEntityHandler() { return this.entityHandler; }
/*  62 */    private final VolatileItemHandler itemHandler = (VolatileItemHandler)new VolatileItemHandler_v1_8_R3(this); public VolatileItemHandler getItemHandler() { return this.itemHandler; }
/*  63 */    private final VolatileWorldHandler worldHandler = (VolatileWorldHandler)new VolatileWorldHandler_v1_8_R3(this); Field[] packet63Fields; Map<String, EnumParticle> particleMap; public VolatileWorldHandler getWorldHandler() { return this.worldHandler; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<AbstractEntity> getEntitiesBySelector(SkillCaster am, String targetSelector) {
/*     */     List<Entity> targets;
/*  94 */     MythicMobs.debug(4, "------ Running VANILLA Target Selector: " + targetSelector);
/*  95 */     Entity entity = ((CraftEntity)BukkitAdapter.adapt(am.getEntity())).getHandle();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 100 */       targets = CommandAbstract.c((ICommandListener)entity, targetSelector);
/* 101 */     } catch (ExceptionEntityNotFound ex) {
/* 102 */       targets = Collections.emptyList();
/* 103 */       ex.printStackTrace();
/*     */     } 
/*     */     
/* 106 */     MythicMobs.debug(4, "-------- Found " + targets.size() + " targets");
/* 107 */     Set<AbstractEntity> ftargets = new HashSet<>();
/* 108 */     for (Entity target : targets) {
/* 109 */       ftargets.add(BukkitAdapter.adapt((Entity)target.getBukkitEntity()));
/*     */     }
/* 111 */     return ftargets;
/*     */   }
/*     */   
/*     */   public void PlaySoundAtLocation(AbstractLocation location, String sound, float volume, float pitch) {
/* 115 */     ((CraftWorld)BukkitAdapter.adapt(location.getWorld())).getHandle().makeSound(location.getX(), location.getY(), location.getZ(), sound, volume, pitch);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void CreateFireworksExplosion(Location location, boolean flicker, boolean trail, int type, int[] colors, int[] fadeColors, int flightDuration) {
/* 121 */     ItemStack item = new ItemStack(Item.getById(401), 1, 0);
/*     */ 
/*     */     
/* 124 */     NBTTagCompound tag = item.getTag();
/* 125 */     if (tag == null) {
/* 126 */       tag = new NBTTagCompound();
/*     */     }
/*     */ 
/*     */     
/* 130 */     NBTTagCompound explTag = new NBTTagCompound();
/* 131 */     explTag.setByte("Flicker", flicker ? 1 : 0);
/* 132 */     explTag.setByte("Trail", trail ? 1 : 0);
/* 133 */     explTag.setByte("Type", (byte)type);
/* 134 */     explTag.setIntArray("Colors", colors);
/* 135 */     explTag.setIntArray("FadeColors", fadeColors);
/*     */ 
/*     */     
/* 138 */     NBTTagCompound fwTag = new NBTTagCompound();
/* 139 */     fwTag.setByte("Flight", (byte)flightDuration);
/* 140 */     NBTTagList explList = new NBTTagList();
/* 141 */     explList.add((NBTBase)explTag);
/* 142 */     fwTag.set("Explosions", (NBTBase)explList);
/* 143 */     tag.set("Fireworks", (NBTBase)fwTag);
/*     */ 
/*     */     
/* 146 */     item.setTag(tag);
/*     */ 
/*     */     
/* 149 */     EntityFireworks fireworks = new EntityFireworks((World)((CraftWorld)location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), item);
/* 150 */     ((CraftWorld)location.getWorld()).getHandle().addEntity((Entity)fireworks);
/*     */ 
/*     */     
/* 153 */     if (flightDuration == 0) {
/* 154 */       ((CraftWorld)location.getWorld()).getHandle().broadcastEntityEffect((Entity)fireworks, (byte)17);
/* 155 */       fireworks.die();
/*     */     } 
/*     */   }
/*     */   
/* 159 */   public VolatileCodeEnabled_v1_8_R3() { this.packet63Fields = new Field[11];
/* 160 */     this.particleMap = new HashMap<>(); try { this.packet63Fields[0] = PacketPlayOutWorldParticles.class.getDeclaredField("a"); this.packet63Fields[1] = PacketPlayOutWorldParticles.class.getDeclaredField("b"); this.packet63Fields[2] = PacketPlayOutWorldParticles.class.getDeclaredField("c"); this.packet63Fields[3] = PacketPlayOutWorldParticles.class.getDeclaredField("d"); this.packet63Fields[4] = PacketPlayOutWorldParticles.class.getDeclaredField("e"); this.packet63Fields[5] = PacketPlayOutWorldParticles.class.getDeclaredField("f"); this.packet63Fields[6] = PacketPlayOutWorldParticles.class.getDeclaredField("g"); this.packet63Fields[7] = PacketPlayOutWorldParticles.class.getDeclaredField("h"); this.packet63Fields[8] = PacketPlayOutWorldParticles.class.getDeclaredField("i"); this.packet63Fields[9] = PacketPlayOutWorldParticles.class.getDeclaredField("j"); this.packet63Fields[10] = PacketPlayOutWorldParticles.class.getDeclaredField("k"); for (int i = 0; i <= 10; i++)
/*     */         this.packet63Fields[i].setAccessible(true);  } catch (Exception e) { e.printStackTrace(); }  for (EnumParticle particle : EnumParticle.values()) { if (particle != null)
/*     */         this.particleMap.put(particle.b(), particle);  }
/* 163 */      } public void doParticleEffect(Location location, String name, float spreadHoriz, float spreadVert, int count, float speed, float yOffset, int radius) { PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
/*     */     
/* 165 */     int[] data = null;
/* 166 */     if (name.contains("_")) {
/* 167 */       String[] split = name.split("_");
/* 168 */       name = split[0] + "_";
/* 169 */       if (split.length > 1) {
/* 170 */         String[] split2 = split[1].split(":");
/* 171 */         data = new int[split2.length];
/* 172 */         for (int i = 0; i < data.length; i++) {
/* 173 */           data[i] = Integer.parseInt(split2[i]);
/*     */         }
/*     */       } 
/*     */     } 
/* 177 */     EnumParticle particle = this.particleMap.get(name);
/*     */     
/* 179 */     if (particle == null) {
/* 180 */       MythicMobs.error("Cannot do Particle Effect: Invalid particle specified (particle = " + name + ")");
/*     */       return;
/*     */     } 
/*     */     try {
/* 184 */       this.packet63Fields[0].set(packet, particle);
/* 185 */       this.packet63Fields[1].setFloat(packet, (float)location.getX());
/* 186 */       this.packet63Fields[2].setFloat(packet, (float)location.getY() + yOffset);
/* 187 */       this.packet63Fields[3].setFloat(packet, (float)location.getZ());
/* 188 */       this.packet63Fields[4].setFloat(packet, spreadHoriz);
/* 189 */       this.packet63Fields[5].setFloat(packet, spreadVert);
/* 190 */       this.packet63Fields[6].setFloat(packet, spreadHoriz);
/* 191 */       this.packet63Fields[7].setFloat(packet, speed);
/* 192 */       this.packet63Fields[8].setInt(packet, count);
/* 193 */       this.packet63Fields[9].setBoolean(packet, (radius >= 200));
/* 194 */       if (data != null) {
/* 195 */         this.packet63Fields[10].set(packet, data);
/*     */       }
/*     */       
/* 198 */       int rSq = radius * radius * 2;
/*     */       
/* 200 */       for (Player player : location.getWorld().getPlayers()) {
/* 201 */         if (player.getWorld().equals(location.getWorld()) && 
/* 202 */           player.getLocation().distanceSquared(location) <= rSq) {
/* 203 */           (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)packet);
/*     */         }
/*     */       }
/*     */     
/* 207 */     } catch (Exception e) {
/* 208 */       e.printStackTrace();
/*     */     }  }
/*     */ 
/*     */   
/*     */   public void setMaxHealth(Entity e, double health) {
/* 213 */     AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.maxHealth);
/* 214 */     attributes.setValue(health);
/* 215 */     LivingEntity l = (LivingEntity)e;
/* 216 */     l.setHealth(l.getMaxHealth());
/*     */   }
/*     */   
/*     */   public void setFollowRange(Entity e, double range) {
/* 220 */     AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
/* 221 */     if (attributes != null)
/* 222 */       attributes.setValue(range); 
/*     */   }
/*     */   
/*     */   public void setKnockBackResistance(Entity e, double knock) {
/* 226 */     AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.c);
/* 227 */     if (attributes != null)
/* 228 */       attributes.setValue(knock); 
/*     */   }
/*     */   
/*     */   public void setMobSpeed(Entity e, double speed) {
/* 232 */     AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/* 233 */     if (attributes != null)
/* 234 */       attributes.setValue(speed); 
/*     */   }
/*     */   
/*     */   public void setAttackDamage(Entity e, double damage) {
/* 238 */     AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)e).getHandle()).getAttributeInstance(GenericAttributes.ATTACK_DAMAGE);
/* 239 */     if (attributes != null) {
/* 240 */       attributes.setValue(damage);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack setItemAttribute(String itemName, String attr, double amount, ItemStack item) {
/* 246 */     VolatileCodeHandler.Attributes a = VolatileCodeHandler.Attributes.get(attr);
/* 247 */     int op = -1;
/*     */     
/* 249 */     ItemStack nms = CraftItemStack.asNMSCopy(item);
/* 250 */     NBTTagList attrmod = getItemAttributeList(nms);
/* 251 */     NBTTagCompound c = new NBTTagCompound();
/* 252 */     if (op == -1) op = a.op;
/*     */     
/* 254 */     c.set("Name", (NBTBase)new NBTTagString(attr));
/* 255 */     c.set("AttributeName", (NBTBase)new NBTTagString(a.name));
/* 256 */     c.set("Amount", (NBTBase)new NBTTagDouble(amount));
/* 257 */     c.set("Operation", (NBTBase)new NBTTagInt(op));
/*     */     
/* 259 */     UUID tagUUID = MythicUtil.getUUIDFromString(itemName);
/* 260 */     c.set("UUIDMost", (NBTBase)new NBTTagLong(tagUUID.getMostSignificantBits()));
/* 261 */     c.set("UUIDLeast", (NBTBase)new NBTTagLong(tagUUID.getLeastSignificantBits()));
/* 262 */     attrmod.add((NBTBase)c);
/* 263 */     nms.getTag().set("AttributeModifiers", (NBTBase)attrmod);
/* 264 */     return (ItemStack)CraftItemStack.asCraftMirror(nms);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList getItemAttributeList(ItemStack nms) {
/* 271 */     if (nms.getTag() == null)
/*     */     {
/* 273 */       nms.setTag(new NBTTagCompound());
/*     */     }
/* 275 */     NBTTagList attrmod = nms.getTag().getList("AttributeModifiers", 10);
/* 276 */     if (attrmod == null)
/*     */     {
/* 278 */       nms.getTag().set("AttributeModifiers", (NBTBase)new NBTTagList());
/*     */     }
/* 280 */     return nms.getTag().getList("AttributeModifiers", 10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void listItemAttributes(Player player) {}
/*     */ 
/*     */   
/*     */   public void setCreeperFuseTicks(Creeper c, int t) {
/* 289 */     EntityCreeper entCreeper = ((CraftCreeper)c).getHandle();
/* 290 */     Field fuseF = null;
/*     */     try {
/* 292 */       fuseF = EntityCreeper.class.getDeclaredField("maxFuseTicks");
/* 293 */     } catch (Exception exception) {}
/* 294 */     fuseF.setAccessible(true);
/*     */     try {
/* 296 */       fuseF.setInt(entCreeper, t);
/* 297 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreeperExplosionRadius(Creeper c, int r) {
/* 302 */     EntityCreeper entCreeper = ((CraftCreeper)c).getHandle();
/* 303 */     Field radiusF = null;
/*     */     try {
/* 305 */       radiusF = EntityCreeper.class.getDeclaredField("explosionRadius");
/* 306 */     } catch (Exception exception) {}
/* 307 */     radiusF.setAccessible(true);
/*     */     try {
/* 309 */       radiusF.setInt(entCreeper, r);
/* 310 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public void setZombieSpawnReinforcements(Zombie z, double d) {
/* 315 */     EntityZombie zombie = ((CraftZombie)z).getHandle();
/*     */ 
/*     */ 
/*     */     
/* 319 */     AttributeModifiable ar = (AttributeModifiable)zombie.getAttributeMap().a("Spawn Reinforcements Chance");
/*     */     
/* 321 */     ar.setValue(d);
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
/*     */   public void setEntitySilent(Entity e) {
/* 333 */     Entity ee = ((CraftEntity)e).getHandle();
/* 334 */     ee.b(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChickenHostile(Chicken c) {
/* 339 */     EntityChicken chicken = ((CraftChicken)c).getHandle();
/*     */     
/* 341 */     chicken.i(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack setItemUnbreakable(ItemStack i) {
/*     */     CraftItemStack craftItemStack;
/* 347 */     if (!(i instanceof CraftItemStack)) {
/* 348 */       craftItemStack = CraftItemStack.asCraftCopy(i);
/*     */     }
/* 350 */     NBTTagCompound tag = getTag((ItemStack)craftItemStack);
/* 351 */     if (tag == null) {
/* 352 */       tag = new NBTTagCompound();
/*     */     }
/* 354 */     tag.setByte("Unbreakable", (byte)1);
/* 355 */     return setTag((ItemStack)craftItemStack, tag);
/*     */   }
/*     */   
/*     */   public ItemStack setItemHideFlags(ItemStack i) {
/* 359 */     return i;
/*     */   }
/*     */   
/*     */   public void sendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
/* 363 */     PlayerConnection conn = (((CraftPlayer)player).getHandle()).playerConnection;
/*     */     
/* 365 */     PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);
/* 366 */     conn.sendPacket((Packet)packet);
/* 367 */     if (title != null) {
/* 368 */       packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, (IChatBaseComponent)new ChatComponentText(title));
/* 369 */       conn.sendPacket((Packet)packet);
/*     */     } 
/* 371 */     if (subtitle != null) {
/* 372 */       packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, (IChatBaseComponent)new ChatComponentText(subtitle));
/* 373 */       conn.sendPacket((Packet)packet);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendActionBarMessageToPlayer(Player player, String message) {
/* 379 */     PlayerConnection conn = (((CraftPlayer)player).getHandle()).playerConnection;
/* 380 */     PacketPlayOutChat packet = new PacketPlayOutChat((IChatBaseComponent)new ChatComponentText(message), (byte)2);
/* 381 */     conn.sendPacket((Packet)packet);
/*     */   }
/*     */   
/*     */   private static NBTTagCompound getTag(ItemStack item) {
/* 385 */     if (item instanceof CraftItemStack) {
/*     */       try {
/* 387 */         Field field = CraftItemStack.class.getDeclaredField("handle");
/* 388 */         field.setAccessible(true);
/* 389 */         return ((ItemStack)field.get(item)).getTag();
/* 390 */       } catch (Exception exception) {}
/*     */     }
/*     */     
/* 393 */     return null;
/*     */   }
/*     */   
/*     */   private static ItemStack setTag(ItemStack item, NBTTagCompound tag) {
/* 397 */     CraftItemStack craftItem = null;
/* 398 */     if (item instanceof CraftItemStack) {
/* 399 */       craftItem = (CraftItemStack)item;
/*     */     } else {
/* 401 */       craftItem = CraftItemStack.asCraftCopy(item);
/*     */     } 
/*     */     
/* 404 */     ItemStack nmsItem = null;
/*     */     try {
/* 406 */       Field field = CraftItemStack.class.getDeclaredField("handle");
/* 407 */       field.setAccessible(true);
/* 408 */       nmsItem = (ItemStack)field.get(item);
/* 409 */     } catch (Exception exception) {}
/*     */     
/* 411 */     if (nmsItem == null) {
/* 412 */       nmsItem = CraftItemStack.asNMSCopy((ItemStack)craftItem);
/*     */     }
/*     */     
/* 415 */     nmsItem.setTag(tag);
/*     */     try {
/* 417 */       Field field = CraftItemStack.class.getDeclaredField("handle");
/* 418 */       field.setAccessible(true);
/* 419 */       field.set(craftItem, nmsItem);
/* 420 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 423 */     return (ItemStack)craftItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendWorldEnvironment(AbstractPlayer player, String env) {
/* 428 */     World.Environment environment = World.Environment.valueOf(env);
/*     */     
/* 430 */     CraftPlayer craftPlayer = (CraftPlayer)player.getBukkitEntity();
/* 431 */     CraftWorld world = (CraftWorld)craftPlayer.getWorld();
/* 432 */     Location location = player.getBukkitEntity().getLocation();
/*     */     
/* 434 */     PacketPlayOutRespawn packet = new PacketPlayOutRespawn(environment.getId(), EnumDifficulty.getById(world.getDifficulty().getValue()), WorldType.NORMAL, WorldSettings.EnumGamemode.getById(((Player)player.getBukkitEntity()).getGameMode().getValue()));
/*     */     
/* 436 */     (craftPlayer.getHandle()).playerConnection.sendPacket((Packet)packet);
/*     */     
/* 438 */     int viewDistance = MythicMobs.inst().getServer().getViewDistance();
/*     */     
/* 440 */     int xMin = location.getChunk().getX() - viewDistance;
/* 441 */     int xMax = location.getChunk().getX() + viewDistance;
/* 442 */     int zMin = location.getChunk().getZ() - viewDistance;
/* 443 */     int zMax = location.getChunk().getZ() + viewDistance;
/*     */     
/* 445 */     for (int x = xMin; x < xMax; x++) {
/* 446 */       for (int z = zMin; z < zMax; z++) {
/* 447 */         world.refreshChunk(x, z);
/*     */       }
/*     */     } 
/*     */     
/* 451 */     craftPlayer.updateInventory();
/*     */     
/* 453 */     Location tp = new Location((World)world, 0.0D, 0.0D, 0.0D);
/*     */     
/* 455 */     craftPlayer.teleport(tp);
/* 456 */     craftPlayer.teleport(location);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoAI(ActiveMob am) {
/* 462 */     Entity nmsEntity = ((CraftEntity)am.getEntity().getBukkitEntity()).getHandle();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 467 */     NBTTagCompound tag = new NBTTagCompound();
/* 468 */     nmsEntity.c(tag);
/* 469 */     tag.setInt("NoAI", 1);
/* 470 */     nmsEntity.f(tag);
/*     */   }
/*     */ 
/*     */   
/*     */   public void playLocalizedLightningEffect(AbstractLocation target, double radius) {
/* 475 */     Location location = BukkitAdapter.adapt(target);
/* 476 */     double distanceSquared = Math.pow(radius, 2.0D);
/*     */     
/* 478 */     for (Player p : location.getWorld().getPlayers()) {
/* 479 */       if (location.distanceSquared(p.getLocation()) <= distanceSquared) {
/* 480 */         (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutSpawnEntityWeather((Entity)new EntityLightning(((CraftPlayer)p)
/*     */                 
/* 482 */                 .getHandle().getWorld(), location.getX(), location.getY(), location.getZ(), false, false)));
/* 483 */         (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutNamedSoundEffect("ambient.weather.thunder", p
/* 484 */               .getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 10000.0F, 63.0F));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack setItemAttributes(ItemStack item, String itemName, Map<String, Object> options, Map<String, Map<String, Object>> attributes) {
/* 492 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doDamage(ActiveMob mob, AbstractEntity target, float amount) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public double getAbsorptionHearts(LivingEntity entity) {
/* 503 */     return ((CraftLivingEntity)entity).getHandle().getAbsorptionHearts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveSkinData(Player player, String name) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClientVelocity(Player player, Vector velocity) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getItemRecharge(Player player) {
/* 520 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doEffectArmSwing(AbstractEntity entity) {
/* 525 */     Entity e = ((CraftEntity)entity.getBukkitEntity()).getHandle();
/* 526 */     e.getWorld().broadcastEntityEffect(e, (byte)4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnderDragonAI(Entity entity) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnderDragonPathPoint(AbstractLocation location) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyPhysics(Block target) {
/* 542 */     Location location = target.getLocation();
/* 543 */     WorldServer worldServer = ((CraftWorld)location.getWorld()).getHandle();
/* 544 */     BlockPosition blockposition = new BlockPosition(location.getX(), location.getY(), location.getZ());
/* 545 */     IBlockData iblockdata = worldServer.getType(blockposition);
/* 546 */     Block block = iblockdata.getBlock();
/*     */ 
/*     */     
/* 549 */     worldServer.applyPhysics(blockposition, block);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\VolatileCodeEnabled_v1_8_R3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */