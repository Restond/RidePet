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
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.CompoundTag_v1_14_R1;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.VolatileAIHandler_v1_14_R1;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.VolatileBlockHandler_v1_14_R1;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.VolatileEntityHandler_v1_14_R1;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.VolatileItemHandler_v1_14_R1;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.VolatileWorldHandler_v1_14_R1;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Collection;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.server.v1_14_R1.AttributeModifiable;
/*     */ import net.minecraft.server.v1_14_R1.BlockPosition;
/*     */ import net.minecraft.server.v1_14_R1.ChatComponentText;
/*     */ import net.minecraft.server.v1_14_R1.ChatMessageType;
/*     */ import net.minecraft.server.v1_14_R1.DamageSource;
/*     */ import net.minecraft.server.v1_14_R1.DragonControllerPhase;
/*     */ import net.minecraft.server.v1_14_R1.Entity;
/*     */ import net.minecraft.server.v1_14_R1.EntityChicken;
/*     */ import net.minecraft.server.v1_14_R1.EntityEnderDragon;
/*     */ import net.minecraft.server.v1_14_R1.EntityInsentient;
/*     */ import net.minecraft.server.v1_14_R1.EntityLightning;
/*     */ import net.minecraft.server.v1_14_R1.EntityLiving;
/*     */ import net.minecraft.server.v1_14_R1.EntityPlayer;
/*     */ import net.minecraft.server.v1_14_R1.EntityZombie;
/*     */ import net.minecraft.server.v1_14_R1.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_14_R1.ItemStack;
/*     */ import net.minecraft.server.v1_14_R1.NBTBase;
/*     */ import net.minecraft.server.v1_14_R1.NBTTagCompound;
/*     */ import net.minecraft.server.v1_14_R1.NBTTagDouble;
/*     */ import net.minecraft.server.v1_14_R1.NBTTagInt;
/*     */ import net.minecraft.server.v1_14_R1.NBTTagList;
/*     */ import net.minecraft.server.v1_14_R1.NBTTagLong;
/*     */ import net.minecraft.server.v1_14_R1.NBTTagString;
/*     */ import net.minecraft.server.v1_14_R1.Packet;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutChat;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutEntityVelocity;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutResourcePackSend;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutSpawnEntityWeather;
/*     */ import net.minecraft.server.v1_14_R1.PlayerConnection;
/*     */ import net.minecraft.server.v1_14_R1.Vec3D;
/*     */ import net.minecraft.server.v1_14_R1.WorldGenEndTrophy;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftChicken;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftZombie;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.Chicken;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VolatileCodeEnabled_v1_14_R1
/*     */   implements VolatileCodeHandler
/*     */ {
/* 130 */   private final VolatileAIHandler AIHandler = (VolatileAIHandler)new VolatileAIHandler_v1_14_R1(this); public VolatileAIHandler getAIHandler() { return this.AIHandler; }
/* 131 */    private final VolatileBlockHandler blockHandler = (VolatileBlockHandler)new VolatileBlockHandler_v1_14_R1(this); public VolatileBlockHandler getBlockHandler() { return this.blockHandler; }
/* 132 */    private final VolatileEntityHandler entityHandler = (VolatileEntityHandler)new VolatileEntityHandler_v1_14_R1(this); public VolatileEntityHandler getEntityHandler() { return this.entityHandler; }
/* 133 */    private final VolatileItemHandler itemHandler = (VolatileItemHandler)new VolatileItemHandler_v1_14_R1(this); public VolatileItemHandler getItemHandler() { return this.itemHandler; }
/* 134 */    private final VolatileWorldHandler worldHandler = (VolatileWorldHandler)new VolatileWorldHandler_v1_14_R1(this); public VolatileWorldHandler getWorldHandler() { return this.worldHandler; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag createCompoundTag(Map<String, Tag> value) {
/* 140 */     return (CompoundTag)new CompoundTag_v1_14_R1(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack setItemDurability(ItemStack item, int durability) {
/* 149 */     ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
/*     */     
/* 151 */     NBTTagCompound tag = nmsItem.getOrCreateTag();
/* 152 */     tag.setInt("Damage", durability);
/* 153 */     nmsItem.setTag(tag);
/*     */     
/* 155 */     return (ItemStack)CraftItemStack.asCraftMirror(nmsItem);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 186 */     return null;
/*     */   }
/*     */   
/*     */   public void PlaySoundAtLocation(AbstractLocation location, String sound, float volume, float pitch) {
/* 190 */     Location l = BukkitAdapter.adapt(location);
/* 191 */     l.getWorld().playSound(l, sound, volume, pitch);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void CreateFireworksExplosion(Location location, boolean flicker, boolean trail, int type, int[] colors, int[] fadeColors, int flightDuration) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack setItemAttributes(ItemStack item, String itemName, Map<String, Object> options, Map<String, Map<String, Object>> attributes) {
/* 201 */     ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
/* 202 */     if (nmsItem == null) return item; 
/* 203 */     NBTTagList itemAttributes = getItemAttributeList(nmsItem);
/* 204 */     UUID tagUUID = MythicUtil.getUUIDFromString(itemName);
/*     */     
/* 206 */     if (attributes.size() > 0) {
/* 207 */       attributes.forEach((slot, entry) -> entry.forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 256 */       nmsItem.getTag().set("AttributeModifiers", (NBTBase)itemAttributes);
/*     */     } 
/*     */     
/* 259 */     options.forEach((option, value) -> {
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
/* 281 */     return (ItemStack)CraftItemStack.asCraftMirror(nmsItem);
/*     */   }
/*     */   
/*     */   public ItemStack setItemAttribute(String itemName, String attr, double amount, ItemStack item) {
/* 285 */     VolatileCodeHandler.Attributes a = VolatileCodeHandler.Attributes.get(attr);
/* 286 */     int op = -1;
/*     */     
/* 288 */     ItemStack nms = CraftItemStack.asNMSCopy(item);
/* 289 */     NBTTagList attrmod = getItemAttributeList(nms);
/* 290 */     NBTTagCompound c = new NBTTagCompound();
/* 291 */     if (op == -1) op = a.op;
/*     */     
/* 293 */     c.set("Name", (NBTBase)new NBTTagString(attr));
/* 294 */     c.set("AttributeName", (NBTBase)new NBTTagString(a.name));
/* 295 */     c.set("Amount", (NBTBase)new NBTTagDouble(amount));
/*     */ 
/*     */     
/* 298 */     c.set("Operation", (NBTBase)new NBTTagInt(op));
/*     */     
/* 300 */     UUID tagUUID = MythicUtil.getUUIDFromString(itemName);
/* 301 */     c.set("UUIDMost", (NBTBase)new NBTTagLong(tagUUID.getMostSignificantBits()));
/* 302 */     c.set("UUIDLeast", (NBTBase)new NBTTagLong(tagUUID.getLeastSignificantBits()));
/* 303 */     attrmod.add(c);
/* 304 */     nms.getTag().set("AttributeModifiers", (NBTBase)attrmod);
/* 305 */     return (ItemStack)CraftItemStack.asCraftMirror(nms);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList getItemAttributeList(ItemStack nms) {
/* 312 */     if (nms == null) return null; 
/* 313 */     if (nms.getTag() == null)
/*     */     {
/* 315 */       nms.setTag(new NBTTagCompound());
/*     */     }
/* 317 */     NBTTagList attrmod = nms.getTag().getList("AttributeModifiers", 10);
/* 318 */     if (attrmod == null)
/*     */     {
/* 320 */       nms.getTag().set("AttributeModifiers", (NBTBase)new NBTTagList());
/*     */     }
/* 322 */     return nms.getTag().getList("AttributeModifiers", 10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void listItemAttributes(Player player) {}
/*     */ 
/*     */   
/*     */   public void setZombieSpawnReinforcements(Zombie z, double d) {
/* 331 */     EntityZombie zombie = ((CraftZombie)z).getHandle();
/*     */ 
/*     */ 
/*     */     
/* 335 */     AttributeModifiable ar = (AttributeModifiable)zombie.getAttributeMap().a("Spawn Reinforcements Chance");
/*     */     
/* 337 */     ar.setValue(d);
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
/*     */   public void setChickenHostile(Chicken c) {
/* 352 */     EntityChicken chicken = ((CraftChicken)c).getHandle();
/*     */     
/* 354 */     chicken.r(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack setItemUnbreakable(ItemStack i) {
/* 360 */     return i;
/*     */   }
/*     */   public ItemStack setItemHideFlags(ItemStack i) {
/* 363 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {}
/*     */   
/*     */   public void sendActionBarMessageToPlayer(Player player, String message) {
/* 370 */     PlayerConnection conn = (((CraftPlayer)player).getHandle()).playerConnection;
/* 371 */     PacketPlayOutChat packet = new PacketPlayOutChat((IChatBaseComponent)new ChatComponentText(message), ChatMessageType.GAME_INFO);
/* 372 */     conn.sendPacket((Packet)packet);
/*     */   }
/*     */   
/*     */   private static NBTTagCompound getTag(ItemStack item) {
/* 376 */     if (item instanceof CraftItemStack) {
/*     */       try {
/* 378 */         Field field = CraftItemStack.class.getDeclaredField("handle");
/* 379 */         field.setAccessible(true);
/* 380 */         return ((ItemStack)field.get(item)).getTag();
/* 381 */       } catch (Exception exception) {}
/*     */     }
/*     */     
/* 384 */     return null;
/*     */   }
/*     */   
/*     */   private static ItemStack setTag(ItemStack item, NBTTagCompound tag) {
/* 388 */     CraftItemStack craftItem = null;
/* 389 */     if (item instanceof CraftItemStack) {
/* 390 */       craftItem = (CraftItemStack)item;
/*     */     } else {
/* 392 */       craftItem = CraftItemStack.asCraftCopy(item);
/*     */     } 
/*     */     
/* 395 */     ItemStack nmsItem = null;
/*     */     try {
/* 397 */       Field field = CraftItemStack.class.getDeclaredField("handle");
/* 398 */       field.setAccessible(true);
/* 399 */       nmsItem = (ItemStack)field.get(item);
/* 400 */     } catch (Exception exception) {}
/*     */     
/* 402 */     if (nmsItem == null) {
/* 403 */       nmsItem = CraftItemStack.asNMSCopy((ItemStack)craftItem);
/*     */     }
/*     */     
/* 406 */     nmsItem.setTag(tag);
/*     */     try {
/* 408 */       Field field = CraftItemStack.class.getDeclaredField("handle");
/* 409 */       field.setAccessible(true);
/* 410 */       field.set(craftItem, nmsItem);
/* 411 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 414 */     return (ItemStack)craftItem;
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
/*     */ 
/*     */   
/*     */   public void sendWorldEnvironment(AbstractPlayer player, String env) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoAI(ActiveMob am) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playLocalizedLightningEffect(AbstractLocation target, double radius) {
/* 455 */     Location location = BukkitAdapter.adapt(target);
/* 456 */     double distanceSquared = Math.pow(radius, 2.0D);
/*     */     
/* 458 */     for (Player p : location.getWorld().getPlayers()) {
/* 459 */       if (location.distanceSquared(p.getLocation()) <= distanceSquared) {
/* 460 */         (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutSpawnEntityWeather((Entity)new EntityLightning(((CraftPlayer)p)
/*     */                 
/* 462 */                 .getHandle().getWorld(), location.getX(), location.getY(), location.getZ(), false, false)));
/* 463 */         p.playSound(location, "entity.lightning.thunder", 10000.0F, 63.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doDamage(ActiveMob mob, AbstractEntity t, float amount) {
/* 470 */     CraftLivingEntity caster = (CraftLivingEntity)mob.getEntity().getBukkitEntity();
/* 471 */     CraftLivingEntity target = (CraftLivingEntity)t.getBukkitEntity();
/*     */     
/* 473 */     target.getHandle().damageEntity(DamageSource.mobAttack(caster.getHandle()), amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAbsorptionHearts(LivingEntity entity) {
/* 478 */     return ((CraftLivingEntity)entity).getHandle().getAbsorptionHearts();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClientVelocity(Player player, Vector velocity) {
/* 483 */     (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutEntityVelocity(player.getEntityId(), new Vec3D(velocity.getX(), velocity.getY(), velocity.getZ())));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveSkinData(Player player, String name) {
/* 488 */     GameProfile profile = ((CraftPlayer)player).getHandle().getProfile();
/* 489 */     Collection<Property> props = profile.getProperties().get("textures");
/* 490 */     Iterator<Property> iterator = props.iterator(); if (iterator.hasNext()) { Property prop = iterator.next();
/* 491 */       String skin = prop.getValue();
/* 492 */       String sig = prop.getSignature();
/*     */       
/* 494 */       File folder = new File(MythicMobs.inst().getDataFolder(), "PlayerSkins");
/* 495 */       if (!folder.exists()) {
/* 496 */         folder.mkdir();
/*     */       }
/* 498 */       File skinFile = new File(folder, name + ".skin.txt");
/* 499 */       File sigFile = new File(folder, name + ".sig.txt");
/*     */       try {
/* 501 */         FileWriter writer = new FileWriter(skinFile);
/* 502 */         writer.write(skin);
/* 503 */         writer.flush();
/* 504 */         writer.close();
/* 505 */         writer = new FileWriter(sigFile);
/* 506 */         writer.write(sig);
/* 507 */         writer.flush();
/* 508 */         writer.close();
/* 509 */       } catch (Exception e) {
/* 510 */         e.printStackTrace();
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
/*     */   
/*     */   private GameProfile setTexture(GameProfile profile, String texture, String signature) {
/* 524 */     if (signature == null || signature.equalsIgnoreCase("")) {
/* 525 */       profile.getProperties().put("textures", new Property("textures", texture));
/*     */     } else {
/* 527 */       profile.getProperties().put("textures", new Property("textures", texture, signature));
/*     */     } 
/* 529 */     return profile;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getItemRecharge(Player player) {
/* 534 */     EntityPlayer ep = ((CraftPlayer)player).getHandle();
/* 535 */     return ep.s(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getItemRecharging(Player player) {
/* 540 */     EntityPlayer ep = ((CraftPlayer)player).getHandle();
/* 541 */     return (ep.s(0.0F) < 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doEffectArmSwing(AbstractEntity entity) {
/* 546 */     Entity e = ((CraftEntity)entity.getBukkitEntity()).getHandle();
/* 547 */     e.getWorld().broadcastEntityEffect(e, (byte)4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnderDragonAI(Entity entity) {
/* 552 */     if (!(entity instanceof org.bukkit.entity.EnderDragon))
/*     */       return; 
/* 554 */     EntityEnderDragon dragon = (EntityEnderDragon)((CraftEntity)entity).getHandle();
/* 555 */     dragon.getDragonControllerManager().setControllerPhase(DragonControllerPhase.CHARGING_PLAYER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnderDragonPathPoint(AbstractLocation location) {
/*     */     try {
/* 561 */       setFinalStatic(WorldGenEndTrophy.class.getField("a"), new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
/* 562 */     } catch (NoSuchFieldException e) {
/*     */       
/* 564 */       e.printStackTrace();
/* 565 */     } catch (SecurityException e) {
/*     */       
/* 567 */       e.printStackTrace();
/* 568 */     } catch (Exception e) {
/*     */       
/* 570 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setFinalStatic(Field field, Object newValue) throws Exception {
/* 575 */     field.setAccessible(true);
/*     */     
/* 577 */     Field modifiersField = Field.class.getDeclaredField("modifiers");
/* 578 */     modifiersField.setAccessible(true);
/* 579 */     modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
/*     */     
/* 581 */     field.set(null, newValue);
/*     */   }
/*     */ 
/*     */   
/* 585 */   private static final Set<EntityType> BAD_CONTROLLER_LOOK = EnumSet.of(EntityType.BAT, new EntityType[] { EntityType.ENDERMITE, EntityType.ENDER_DRAGON, EntityType.GHAST, EntityType.HORSE, EntityType.MAGMA_CUBE, EntityType.POLAR_BEAR, EntityType.SILVERFISH, EntityType.SLIME });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void lookAt(AbstractEntity entity, float yaw, float pitch) {
/* 597 */     Entity handle = ((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/*     */     
/* 599 */     yaw = VectorUtils.clampYaw(yaw);
/* 600 */     handle.yaw = yaw;
/* 601 */     setHeadYaw(entity, yaw);
/* 602 */     handle.pitch = pitch;
/*     */   }
/*     */ 
/*     */   
/*     */   public void lookAtLocation(AbstractEntity entity, AbstractLocation to, boolean headOnly, boolean immediate) {
/* 607 */     Entity handle = ((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/*     */     
/* 609 */     if (immediate || headOnly || BAD_CONTROLLER_LOOK.contains(handle.getBukkitEntity().getType()) || !(handle instanceof EntityInsentient)) {
/*     */       
/* 611 */       AbstractLocation fromLocation = entity.getLocation();
/*     */       
/* 613 */       double xDiff = to.getX() - fromLocation.getX();
/* 614 */       double yDiff = to.getY() - fromLocation.getY();
/* 615 */       double zDiff = to.getZ() - fromLocation.getZ();
/*     */       
/* 617 */       double distanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
/* 618 */       double distanceY = Math.sqrt(distanceXZ * distanceXZ + yDiff * yDiff);
/*     */       
/* 620 */       double yaw = Math.toDegrees(Math.acos(xDiff / distanceXZ));
/* 621 */       double pitch = Math.toDegrees(Math.acos(yDiff / distanceY)) - 90.0D;
/*     */       
/* 623 */       if (zDiff < 0.0D) {
/* 624 */         yaw += Math.abs(180.0D - yaw) * 2.0D;
/*     */       }
/*     */       
/* 627 */       yaw -= 90.0D;
/*     */       
/* 629 */       if (headOnly) {
/* 630 */         setHeadYaw(entity, (float)yaw);
/*     */       } else {
/* 632 */         lookAt(entity, (float)yaw, (float)pitch);
/*     */       } 
/*     */       return;
/*     */     } 
/* 636 */     if (handle instanceof EntityInsentient) {
/* 637 */       ((EntityInsentient)handle).getControllerLook().a(to.getX(), to.getY(), to.getZ(), ((EntityInsentient)handle)
/* 638 */           .dB(), ((EntityInsentient)handle).M());
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
/*     */   public void lookAtEntity(AbstractEntity entity, AbstractEntity to, boolean headOnly, boolean immediate) {
/* 651 */     Entity handle = ((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/* 652 */     Entity target = ((CraftEntity)BukkitAdapter.adapt(to)).getHandle();
/*     */     
/* 654 */     if (BAD_CONTROLLER_LOOK.contains(handle.getBukkitEntity().getType())) {
/* 655 */       if (to.isLiving()) {
/* 656 */         lookAtLocation(entity, to.getEyeLocation(), headOnly, immediate);
/*     */       } else {
/* 658 */         lookAtLocation(entity, to.getLocation(), headOnly, immediate);
/*     */       } 
/* 660 */     } else if (handle instanceof EntityInsentient) {
/* 661 */       EntityInsentient insentient = (EntityInsentient)handle;
/* 662 */       insentient.getControllerLook().a(target, insentient.dB(), insentient.M());
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
/*     */   
/*     */   public void setHeadYaw(AbstractEntity entity, float yaw) {
/* 677 */     if (!entity.isLiving())
/*     */       return; 
/* 679 */     EntityLiving handle = (EntityLiving)((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/* 680 */     yaw = VectorUtils.clampYaw(yaw);
/*     */ 
/*     */     
/* 683 */     if (!(handle instanceof net.minecraft.server.v1_14_R1.EntityHuman));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendResourcePack(AbstractPlayer player, String url, String hash) {
/* 691 */     EntityPlayer p = ((CraftPlayer)player.getBukkitEntity()).getHandle();
/* 692 */     p.playerConnection.sendPacket((Packet)new PacketPlayOutResourcePackSend(url, hash));
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\VolatileCodeEnabled_v1_14_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */