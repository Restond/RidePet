/*     */ package lumine.xikage.mythicmobs.volatilecode;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import com.mojang.brigadier.StringReader;
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
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.CompoundTag_v1_13_R2;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.VolatileAIHandler_v1_13_R2;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.VolatileBlockHandler_v1_13_R2;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.VolatileEntityHandler_v1_13_R2;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.VolatileItemHandler_v1_13_R2;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.VolatileWorldHandler_v1_13_R2;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.server.v1_13_R2.ArgumentParserSelector;
/*     */ import net.minecraft.server.v1_13_R2.AttributeModifiable;
/*     */ import net.minecraft.server.v1_13_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_13_R2.ChatComponentText;
/*     */ import net.minecraft.server.v1_13_R2.ChatMessageType;
/*     */ import net.minecraft.server.v1_13_R2.DamageSource;
/*     */ import net.minecraft.server.v1_13_R2.DragonControllerPhase;
/*     */ import net.minecraft.server.v1_13_R2.Entity;
/*     */ import net.minecraft.server.v1_13_R2.EntityChicken;
/*     */ import net.minecraft.server.v1_13_R2.EntityEnderDragon;
/*     */ import net.minecraft.server.v1_13_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_13_R2.EntityLightning;
/*     */ import net.minecraft.server.v1_13_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_13_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_13_R2.EntitySelector;
/*     */ import net.minecraft.server.v1_13_R2.EntityZombie;
/*     */ import net.minecraft.server.v1_13_R2.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_13_R2.ItemStack;
/*     */ import net.minecraft.server.v1_13_R2.NBTBase;
/*     */ import net.minecraft.server.v1_13_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_13_R2.NBTTagDouble;
/*     */ import net.minecraft.server.v1_13_R2.NBTTagInt;
/*     */ import net.minecraft.server.v1_13_R2.NBTTagList;
/*     */ import net.minecraft.server.v1_13_R2.NBTTagLong;
/*     */ import net.minecraft.server.v1_13_R2.NBTTagString;
/*     */ import net.minecraft.server.v1_13_R2.Packet;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutEntityVelocity;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutResourcePackSend;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutSpawnEntityWeather;
/*     */ import net.minecraft.server.v1_13_R2.PlayerConnection;
/*     */ import net.minecraft.server.v1_13_R2.WorldGenEndTrophy;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.entity.CraftChicken;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.entity.CraftZombie;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
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
/*     */ public class VolatileCodeEnabled_v1_13_R2
/*     */   implements VolatileCodeHandler
/*     */ {
/* 130 */   private final VolatileAIHandler AIHandler = (VolatileAIHandler)new VolatileAIHandler_v1_13_R2(this); public VolatileAIHandler getAIHandler() { return this.AIHandler; }
/* 131 */    private final VolatileBlockHandler blockHandler = (VolatileBlockHandler)new VolatileBlockHandler_v1_13_R2(this); public VolatileBlockHandler getBlockHandler() { return this.blockHandler; }
/* 132 */    private final VolatileEntityHandler entityHandler = (VolatileEntityHandler)new VolatileEntityHandler_v1_13_R2(this); public VolatileEntityHandler getEntityHandler() { return this.entityHandler; }
/* 133 */    private final VolatileItemHandler itemHandler = (VolatileItemHandler)new VolatileItemHandler_v1_13_R2(this); public VolatileItemHandler getItemHandler() { return this.itemHandler; }
/* 134 */    private final VolatileWorldHandler worldHandler = (VolatileWorldHandler)new VolatileWorldHandler_v1_13_R2(this); public VolatileWorldHandler getWorldHandler() { return this.worldHandler; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundTag createCompoundTag(Map<String, Tag> value) {
/* 140 */     return (CompoundTag)new CompoundTag_v1_13_R2(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack setItemDurability(ItemStack item, int durability) {
/* 145 */     ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
/*     */     
/* 147 */     NBTTagCompound tag = nmsItem.getOrCreateTag();
/* 148 */     tag.setInt("Damage", durability);
/* 149 */     nmsItem.setTag(tag);
/*     */     
/* 151 */     return (ItemStack)CraftItemStack.asCraftMirror(nmsItem);
/*     */   }
/*     */   
/*     */   public Set<AbstractEntity> getEntitiesBySelector(SkillCaster am, String targetSelector) {
/*     */     List<? extends Entity> targets;
/* 156 */     MythicMobs.debug(4, "------ Running VANILLA Target Selector: " + targetSelector);
/*     */     
/* 158 */     CraftEntity cE = (CraftEntity)BukkitAdapter.adapt(am.getEntity());
/* 159 */     Entity entity = cE.getHandle();
/* 160 */     Entity entity1 = entity;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 165 */       targetSelector = "playsound minecraft:ambient.underwater.enter master " + targetSelector;
/* 166 */       ArgumentParserSelector argumentparserselector = new ArgumentParserSelector(new StringReader(targetSelector), false);
/* 167 */       EntitySelector entityselector = argumentparserselector.s();
/*     */ 
/*     */       
/* 170 */       targets = entityselector.b(entity.getCommandListener());
/* 171 */     } catch (Exception ex) {
/* 172 */       targets = Collections.emptyList();
/* 173 */       ex.printStackTrace();
/*     */     } 
/*     */     
/* 176 */     MythicMobs.debug(4, "-------- Found " + targets.size() + " targets");
/* 177 */     Set<AbstractEntity> ftargets = new HashSet<>();
/* 178 */     for (Entity target : targets) {
/* 179 */       ftargets.add(BukkitAdapter.adapt((Entity)target.getBukkitEntity()));
/*     */     }
/* 181 */     return ftargets;
/*     */   }
/*     */   
/*     */   public void PlaySoundAtLocation(AbstractLocation location, String sound, float volume, float pitch) {
/* 185 */     Location l = BukkitAdapter.adapt(location);
/* 186 */     for (Player player : l.getWorld().getPlayers()) {
/* 187 */       player.playSound(l, sound, volume, pitch);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void CreateFireworksExplosion(Location location, boolean flicker, boolean trail, int type, int[] colors, int[] fadeColors, int flightDuration) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack setItemAttributes(ItemStack item, String itemName, Map<String, Object> options, Map<String, Map<String, Object>> attributes) {
/* 198 */     ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
/* 199 */     if (nmsItem == null) return item; 
/* 200 */     NBTTagList itemAttributes = getItemAttributeList(nmsItem);
/* 201 */     UUID tagUUID = MythicUtil.getUUIDFromString(itemName);
/*     */     
/* 203 */     if (attributes.size() > 0) {
/* 204 */       attributes.forEach((slot, entry) -> entry.forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 253 */       nmsItem.getTag().set("AttributeModifiers", (NBTBase)itemAttributes);
/*     */     } 
/*     */     
/* 256 */     options.forEach((option, value) -> {
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
/* 278 */     return (ItemStack)CraftItemStack.asCraftMirror(nmsItem);
/*     */   }
/*     */   
/*     */   public ItemStack setItemAttribute(String itemName, String attr, double amount, ItemStack item) {
/* 282 */     VolatileCodeHandler.Attributes a = VolatileCodeHandler.Attributes.get(attr);
/* 283 */     int op = -1;
/*     */     
/* 285 */     ItemStack nms = CraftItemStack.asNMSCopy(item);
/* 286 */     NBTTagList attrmod = getItemAttributeList(nms);
/* 287 */     NBTTagCompound c = new NBTTagCompound();
/* 288 */     if (op == -1) op = a.op;
/*     */     
/* 290 */     c.set("Name", (NBTBase)new NBTTagString(attr));
/* 291 */     c.set("AttributeName", (NBTBase)new NBTTagString(a.name));
/* 292 */     c.set("Amount", (NBTBase)new NBTTagDouble(amount));
/*     */ 
/*     */     
/* 295 */     c.set("Operation", (NBTBase)new NBTTagInt(op));
/*     */     
/* 297 */     UUID tagUUID = MythicUtil.getUUIDFromString(itemName);
/* 298 */     c.set("UUIDMost", (NBTBase)new NBTTagLong(tagUUID.getMostSignificantBits()));
/* 299 */     c.set("UUIDLeast", (NBTBase)new NBTTagLong(tagUUID.getLeastSignificantBits()));
/* 300 */     attrmod.add((NBTBase)c);
/* 301 */     nms.getTag().set("AttributeModifiers", (NBTBase)attrmod);
/* 302 */     return (ItemStack)CraftItemStack.asCraftMirror(nms);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList getItemAttributeList(ItemStack nms) {
/* 309 */     if (nms == null) return null; 
/* 310 */     if (nms.getTag() == null)
/*     */     {
/* 312 */       nms.setTag(new NBTTagCompound());
/*     */     }
/* 314 */     NBTTagList attrmod = nms.getTag().getList("AttributeModifiers", 10);
/* 315 */     if (attrmod == null)
/*     */     {
/* 317 */       nms.getTag().set("AttributeModifiers", (NBTBase)new NBTTagList());
/*     */     }
/* 319 */     return nms.getTag().getList("AttributeModifiers", 10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void listItemAttributes(Player player) {}
/*     */ 
/*     */   
/*     */   public void setZombieSpawnReinforcements(Zombie z, double d) {
/* 328 */     EntityZombie zombie = ((CraftZombie)z).getHandle();
/*     */ 
/*     */ 
/*     */     
/* 332 */     AttributeModifiable ar = (AttributeModifiable)zombie.getAttributeMap().a("Spawn Reinforcements Chance");
/*     */     
/* 334 */     ar.setValue(d);
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
/* 349 */     EntityChicken chicken = ((CraftChicken)c).getHandle();
/*     */     
/* 351 */     chicken.o(true);
/*     */   }
/*     */   
/*     */   public ItemStack setItemUnbreakable(ItemStack i) {
/* 355 */     return i;
/*     */   }
/*     */   public ItemStack setItemHideFlags(ItemStack i) {
/* 358 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendTitleToPlayer(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {}
/*     */   
/*     */   public void sendActionBarMessageToPlayer(Player player, String message) {
/* 365 */     PlayerConnection conn = (((CraftPlayer)player).getHandle()).playerConnection;
/* 366 */     PacketPlayOutChat packet = new PacketPlayOutChat((IChatBaseComponent)new ChatComponentText(message), ChatMessageType.GAME_INFO);
/* 367 */     conn.sendPacket((Packet)packet);
/*     */   }
/*     */   
/*     */   private static NBTTagCompound getTag(ItemStack item) {
/* 371 */     if (item instanceof CraftItemStack) {
/*     */       try {
/* 373 */         Field field = CraftItemStack.class.getDeclaredField("handle");
/* 374 */         field.setAccessible(true);
/* 375 */         return ((ItemStack)field.get(item)).getTag();
/* 376 */       } catch (Exception exception) {}
/*     */     }
/*     */     
/* 379 */     return null;
/*     */   }
/*     */   
/*     */   private static ItemStack setTag(ItemStack item, NBTTagCompound tag) {
/* 383 */     CraftItemStack craftItem = null;
/* 384 */     if (item instanceof CraftItemStack) {
/* 385 */       craftItem = (CraftItemStack)item;
/*     */     } else {
/* 387 */       craftItem = CraftItemStack.asCraftCopy(item);
/*     */     } 
/*     */     
/* 390 */     ItemStack nmsItem = null;
/*     */     try {
/* 392 */       Field field = CraftItemStack.class.getDeclaredField("handle");
/* 393 */       field.setAccessible(true);
/* 394 */       nmsItem = (ItemStack)field.get(item);
/* 395 */     } catch (Exception exception) {}
/*     */     
/* 397 */     if (nmsItem == null) {
/* 398 */       nmsItem = CraftItemStack.asNMSCopy((ItemStack)craftItem);
/*     */     }
/*     */     
/* 401 */     nmsItem.setTag(tag);
/*     */     try {
/* 403 */       Field field = CraftItemStack.class.getDeclaredField("handle");
/* 404 */       field.setAccessible(true);
/* 405 */       field.set(craftItem, nmsItem);
/* 406 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 409 */     return (ItemStack)craftItem;
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
/* 451 */     Location location = BukkitAdapter.adapt(target);
/* 452 */     double distanceSquared = Math.pow(radius, 2.0D);
/*     */     
/* 454 */     for (Player p : location.getWorld().getPlayers()) {
/* 455 */       if (location.distanceSquared(p.getLocation()) <= distanceSquared) {
/* 456 */         (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutSpawnEntityWeather((Entity)new EntityLightning(((CraftPlayer)p)
/*     */                 
/* 458 */                 .getHandle().getWorld(), location.getX(), location.getY(), location.getZ(), false, false)));
/* 459 */         p.playSound(location, "entity.lightning.thunder", 10000.0F, 63.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doDamage(ActiveMob mob, AbstractEntity t, float amount) {
/* 466 */     CraftLivingEntity caster = (CraftLivingEntity)mob.getEntity().getBukkitEntity();
/* 467 */     CraftLivingEntity target = (CraftLivingEntity)t.getBukkitEntity();
/*     */     
/* 469 */     target.getHandle().damageEntity(DamageSource.mobAttack(caster.getHandle()), amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAbsorptionHearts(LivingEntity entity) {
/* 474 */     return ((CraftLivingEntity)entity).getHandle().getAbsorptionHearts();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setClientVelocity(Player player, Vector velocity) {
/* 479 */     (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutEntityVelocity(player.getEntityId(), velocity.getX(), velocity.getY(), velocity.getZ()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveSkinData(Player player, String name) {
/* 484 */     GameProfile profile = ((CraftPlayer)player).getHandle().getProfile();
/* 485 */     Collection<Property> props = profile.getProperties().get("textures");
/* 486 */     Iterator<Property> iterator = props.iterator(); if (iterator.hasNext()) { Property prop = iterator.next();
/* 487 */       String skin = prop.getValue();
/* 488 */       String sig = prop.getSignature();
/*     */       
/* 490 */       File folder = new File(MythicMobs.inst().getDataFolder(), "PlayerSkins");
/* 491 */       if (!folder.exists()) {
/* 492 */         folder.mkdir();
/*     */       }
/* 494 */       File skinFile = new File(folder, name + ".skin.txt");
/* 495 */       File sigFile = new File(folder, name + ".sig.txt");
/*     */       try {
/* 497 */         FileWriter writer = new FileWriter(skinFile);
/* 498 */         writer.write(skin);
/* 499 */         writer.flush();
/* 500 */         writer.close();
/* 501 */         writer = new FileWriter(sigFile);
/* 502 */         writer.write(sig);
/* 503 */         writer.flush();
/* 504 */         writer.close();
/* 505 */       } catch (Exception e) {
/* 506 */         e.printStackTrace();
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
/* 520 */     if (signature == null || signature.equalsIgnoreCase("")) {
/* 521 */       profile.getProperties().put("textures", new Property("textures", texture));
/*     */     } else {
/* 523 */       profile.getProperties().put("textures", new Property("textures", texture, signature));
/*     */     } 
/* 525 */     return profile;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getItemRecharge(Player player) {
/* 530 */     EntityPlayer ep = ((CraftPlayer)player).getHandle();
/* 531 */     return ep.r(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getItemRecharging(Player player) {
/* 536 */     EntityPlayer ep = ((CraftPlayer)player).getHandle();
/* 537 */     return (ep.r(0.0F) < 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doEffectArmSwing(AbstractEntity entity) {
/* 542 */     Entity e = ((CraftEntity)entity.getBukkitEntity()).getHandle();
/* 543 */     e.getWorld().broadcastEntityEffect(e, (byte)4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnderDragonAI(Entity entity) {
/* 548 */     if (!(entity instanceof org.bukkit.entity.EnderDragon))
/*     */       return; 
/* 550 */     EntityEnderDragon dragon = (EntityEnderDragon)((CraftEntity)entity).getHandle();
/* 551 */     dragon.getDragonControllerManager().setControllerPhase(DragonControllerPhase.CHARGING_PLAYER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnderDragonPathPoint(AbstractLocation location) {
/*     */     try {
/* 557 */       setFinalStatic(WorldGenEndTrophy.class.getField("a"), new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
/* 558 */     } catch (NoSuchFieldException e) {
/*     */       
/* 560 */       e.printStackTrace();
/* 561 */     } catch (SecurityException e) {
/*     */       
/* 563 */       e.printStackTrace();
/* 564 */     } catch (Exception e) {
/*     */       
/* 566 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void setFinalStatic(Field field, Object newValue) throws Exception {
/* 571 */     field.setAccessible(true);
/*     */     
/* 573 */     Field modifiersField = Field.class.getDeclaredField("modifiers");
/* 574 */     modifiersField.setAccessible(true);
/* 575 */     modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
/*     */     
/* 577 */     field.set(null, newValue);
/*     */   }
/*     */ 
/*     */   
/* 581 */   private static final Set<EntityType> BAD_CONTROLLER_LOOK = EnumSet.of(EntityType.BAT, new EntityType[] { EntityType.ENDERMITE, EntityType.ENDER_DRAGON, EntityType.GHAST, EntityType.HORSE, EntityType.MAGMA_CUBE, EntityType.POLAR_BEAR, EntityType.SILVERFISH, EntityType.SLIME });
/*     */ 
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
/* 593 */     Entity handle = ((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/*     */     
/* 595 */     yaw = VectorUtils.clampYaw(yaw);
/* 596 */     handle.yaw = yaw;
/* 597 */     setHeadYaw(entity, yaw);
/* 598 */     handle.pitch = pitch;
/*     */   }
/*     */ 
/*     */   
/*     */   public void lookAtLocation(AbstractEntity entity, AbstractLocation to, boolean headOnly, boolean immediate) {
/* 603 */     Entity handle = ((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/*     */     
/* 605 */     if (immediate || headOnly || BAD_CONTROLLER_LOOK.contains(handle.getBukkitEntity().getType()) || !(handle instanceof EntityInsentient)) {
/*     */       
/* 607 */       AbstractLocation fromLocation = entity.getLocation();
/*     */       
/* 609 */       double xDiff = to.getX() - fromLocation.getX();
/* 610 */       double yDiff = to.getY() - fromLocation.getY();
/* 611 */       double zDiff = to.getZ() - fromLocation.getZ();
/*     */       
/* 613 */       double distanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
/* 614 */       double distanceY = Math.sqrt(distanceXZ * distanceXZ + yDiff * yDiff);
/*     */       
/* 616 */       double yaw = Math.toDegrees(Math.acos(xDiff / distanceXZ));
/* 617 */       double pitch = Math.toDegrees(Math.acos(yDiff / distanceY)) - 90.0D;
/*     */       
/* 619 */       if (zDiff < 0.0D) {
/* 620 */         yaw += Math.abs(180.0D - yaw) * 2.0D;
/*     */       }
/*     */       
/* 623 */       yaw -= 90.0D;
/*     */       
/* 625 */       if (headOnly) {
/* 626 */         setHeadYaw(entity, (float)yaw);
/*     */       } else {
/* 628 */         lookAt(entity, (float)yaw, (float)pitch);
/*     */       } 
/*     */       return;
/*     */     } 
/* 632 */     if (handle instanceof EntityInsentient) {
/* 633 */       ((EntityInsentient)handle).getControllerLook().a(to.getX(), to.getY(), to.getZ(), ((EntityInsentient)handle)
/* 634 */           .L(), ((EntityInsentient)handle).K());
/*     */       
/* 636 */       while (((EntityLiving)handle).aP >= 180.0F) {
/* 637 */         ((EntityLiving)handle).aP -= 360.0F;
/*     */       }
/* 639 */       while (((EntityLiving)handle).aP < -180.0F) {
/* 640 */         ((EntityLiving)handle).aP += 360.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void lookAtEntity(AbstractEntity entity, AbstractEntity to, boolean headOnly, boolean immediate) {
/* 647 */     Entity handle = ((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/* 648 */     Entity target = ((CraftEntity)BukkitAdapter.adapt(to)).getHandle();
/*     */     
/* 650 */     if (BAD_CONTROLLER_LOOK.contains(handle.getBukkitEntity().getType())) {
/* 651 */       if (to.isLiving()) {
/* 652 */         lookAtLocation(entity, to.getEyeLocation(), headOnly, immediate);
/*     */       } else {
/* 654 */         lookAtLocation(entity, to.getLocation(), headOnly, immediate);
/*     */       } 
/* 656 */     } else if (handle instanceof EntityInsentient) {
/* 657 */       EntityInsentient insentient = (EntityInsentient)handle;
/* 658 */       insentient.getControllerLook().a(target, insentient.L(), insentient.K());
/*     */       
/* 660 */       while (insentient.aP >= 180.0F) {
/* 661 */         insentient.aP -= 360.0F;
/*     */       }
/* 663 */       while (((EntityLiving)handle).aP < -180.0F) {
/* 664 */         insentient.aP += 360.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeadYaw(AbstractEntity entity, float yaw) {
/* 671 */     if (!entity.isLiving())
/*     */       return; 
/* 673 */     EntityLiving handle = (EntityLiving)((CraftEntity)BukkitAdapter.adapt(entity)).getHandle();
/* 674 */     yaw = VectorUtils.clampYaw(yaw);
/* 675 */     handle.aO = yaw;
/* 676 */     if (!(handle instanceof net.minecraft.server.v1_13_R2.EntityHuman)) {
/* 677 */       handle.aN = yaw;
/*     */     }
/* 679 */     handle.aP = yaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendResourcePack(AbstractPlayer player, String url, String hash) {
/* 684 */     EntityPlayer p = ((CraftPlayer)player.getBukkitEntity()).getHandle();
/* 685 */     p.playerConnection.sendPacket((Packet)new PacketPlayOutResourcePackSend(url, hash));
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\VolatileCodeEnabled_v1_13_R2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */