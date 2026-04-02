/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_14_R1;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileEntityHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_14_R1.CompoundTag_v1_14_R1;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_14_R1.DataWatcher;
/*     */ import net.minecraft.server.v1_14_R1.DataWatcherObject;
/*     */ import net.minecraft.server.v1_14_R1.DataWatcherRegistry;
/*     */ import net.minecraft.server.v1_14_R1.Entity;
/*     */ import net.minecraft.server.v1_14_R1.EntityArmorStand;
/*     */ import net.minecraft.server.v1_14_R1.EntityInsentient;
/*     */ import net.minecraft.server.v1_14_R1.EntityItem;
/*     */ import net.minecraft.server.v1_14_R1.EntityLiving;
/*     */ import net.minecraft.server.v1_14_R1.EntityPlayer;
/*     */ import net.minecraft.server.v1_14_R1.ItemStack;
/*     */ import net.minecraft.server.v1_14_R1.NBTTagCompound;
/*     */ import net.minecraft.server.v1_14_R1.Packet;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutCloseWindow;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutEntityMetadata;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutEntityTeleport;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutGameStateChange;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutPosition;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutSetSlot;
/*     */ import net.minecraft.server.v1_14_R1.PacketPlayOutWorldBorder;
/*     */ import net.minecraft.server.v1_14_R1.WorldBorder;
/*     */ import net.minecraft.server.v1_14_R1.WorldServer;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftItem;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ public class VolatileEntityHandler_v1_14_R1
/*     */   implements VolatileEntityHandler
/*     */ {
/*     */   public VolatileEntityHandler_v1_14_R1(VolatileCodeHandler handler) {}
/*     */   
/*     */   public float getEntityAbsorptionHearts(AbstractEntity entity) {
/*  52 */     if (!entity.isLiving()) {
/*  53 */       return 0.0F;
/*     */     }
/*  55 */     EntityLiving el = (EntityLiving)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*  56 */     return el.getAbsorptionHearts();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityAbsorptionHearts(AbstractEntity entity, float value) {
/*  61 */     if (!entity.isLiving()) {
/*     */       return;
/*     */     }
/*  64 */     EntityLiving el = (EntityLiving)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*  65 */     el.setAbsorptionHearts(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(AbstractEntity entity, AbstractLocation location) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(AbstractEntity entity, double x, double y, double z, float yaw, float pitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(AbstractEntity entity, double x, double y, double z, float yaw, float pitch, boolean noRotation, boolean noGravity) {
/*  80 */     Entity e = ((CraftEntity)entity.getBukkitEntity()).getHandle();
/*  81 */     e.setLocation(x, y, z, yaw, pitch);
/*  82 */     if (entity.isPlayer()) {
/*  83 */       playerConnectionTeleport(entity, x, y, z, yaw, pitch, noRotation, noGravity);
/*     */     }
/*  85 */     if (e.world instanceof WorldServer) {
/*  86 */       ((WorldServer)e.world).entityJoinedWorld(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void playerConnectionTeleport(AbstractEntity entity, double x, double y, double z, float yaw, float pitch, boolean noRotation, boolean noGravity) {
/*  91 */     EntityPlayer me = ((CraftPlayer)entity.getBukkitEntity()).getHandle();
/*  92 */     Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set = new HashSet<>();
/*     */     
/*  94 */     if (noRotation) {
/*  95 */       pitch = 0.0F;
/*  96 */       yaw = 0.0F;
/*  97 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.X_ROT);
/*  98 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y_ROT);
/*     */     } 
/* 100 */     if (noGravity) {
/* 101 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y);
/* 102 */       y = 0.0D;
/*     */     } 
/* 104 */     me.playerConnection.sendPacket((Packet)new PacketPlayOutPosition(x, y, z, yaw, pitch, set, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityInMotion(AbstractEntity entity) {
/* 109 */     if (entity.isLiving()) {
/* 110 */       EntityInsentient e = (EntityInsentient)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*     */       
/* 112 */       if (e.lastX != e.locX || e.lastY != e.locY || e.lastZ != e.locZ) {
/* 113 */         return true;
/*     */       }
/*     */     } 
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHitBox(AbstractEntity target, double a0, double a1, double a2) {
/* 121 */     Entity entity = BukkitAdapter.adapt(target);
/* 122 */     Entity me = ((CraftEntity)entity).getHandle();
/* 123 */     me.getBoundingBox().a(a0, a1, a2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemPosition(AbstractEntity target, AbstractLocation ol) {
/* 128 */     Entity entity = BukkitAdapter.adapt(target);
/*     */     
/* 130 */     if (entity instanceof Item) {
/* 131 */       Item item = (Item)entity;
/* 132 */       EntityItem ei = (EntityItem)((CraftItem)item).getHandle();
/* 133 */       ei.setPosition(ol.getX(), ol.getY(), ol.getZ());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendEntityTeleportPacket(AbstractEntity target) {
/* 139 */     Entity entity = BukkitAdapter.adapt(target);
/* 140 */     Entity me = ((CraftEntity)entity).getHandle();
/* 141 */     PacketPlayOutEntityTeleport tp = new PacketPlayOutEntityTeleport(me);
/*     */     
/* 143 */     entity.getLocation().getWorld().getNearbyEntities(entity.getLocation(), 32.0D, 32.0D, 32.0D).forEach(e -> {
/*     */           if (e instanceof Player) {
/*     */             (((CraftPlayer)e).getHandle()).playerConnection.sendPacket((Packet)paramPacketPlayOutEntityTeleport);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityRotation(AbstractEntity target, float pitch, float yaw) {
/* 152 */     Entity entity = BukkitAdapter.adapt(target);
/* 153 */     Entity me = ((CraftEntity)entity).getHandle();
/* 154 */     me.pitch = pitch;
/* 155 */     me.yaw = yaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArmorStandNoGravity(AbstractEntity target) {
/* 160 */     Entity entity = BukkitAdapter.adapt(target);
/*     */     
/* 162 */     if (entity.getType() == EntityType.ARMOR_STAND) {
/* 163 */       EntityArmorStand as = (EntityArmorStand)((CraftEntity)entity).getHandle();
/* 164 */       as.setNoGravity(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkybox(AbstractPlayer target, int skybox) {
/* 170 */     Player player = BukkitAdapter.adapt(target);
/* 171 */     PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(7, skybox);
/*     */     
/* 173 */     (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void forcePlayCredits(AbstractPlayer target, float f) {
/* 178 */     Player player = BukkitAdapter.adapt(target);
/* 179 */     EntityPlayer me = ((CraftPlayer)player).getHandle();
/* 180 */     me.playerConnection.sendPacket((Packet)new PacketPlayOutGameStateChange(4, f));
/*     */   }
/*     */ 
/*     */   
/*     */   public void forceCloseWindow(AbstractPlayer target) {
/* 185 */     Player player = BukkitAdapter.adapt(target);
/* 186 */     EntityPlayer me = ((CraftPlayer)player).getHandle();
/* 187 */     me.playerConnection.sendPacket((Packet)new PacketPlayOutCloseWindow(0));
/*     */   }
/*     */   
/*     */   public void setPlayerWorldBorder(AbstractPlayer target, AbstractLocation center, int radius) {
/*     */     WorldBorder border;
/* 192 */     Player player = BukkitAdapter.adapt(target);
/* 193 */     EntityPlayer ep = ((CraftPlayer)player).getHandle();
/*     */ 
/*     */ 
/*     */     
/* 197 */     if (radius == -1) {
/* 198 */       border = ep.world.getWorldBorder();
/*     */     } else {
/* 200 */       border = new WorldBorder();
/* 201 */       border.world = (ep.world.getWorldBorder()).world;
/* 202 */       border.setCenter(center.getX(), center.getZ());
/* 203 */       border.setSize(radius);
/* 204 */       border.setWarningDistance(1);
/*     */     } 
/* 206 */     ep.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(border, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPlayerFakeInventoryItem(AbstractPlayer target, ItemStack stack, int slot) {
/* 211 */     if (slot < 9) {
/* 212 */       slot += 36;
/*     */     }
/* 214 */     Player player = BukkitAdapter.adapt(target);
/* 215 */     EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
/*     */     
/* 217 */     ItemStack item = CraftItemStack.asNMSCopy(stack);
/*     */     
/* 219 */     PacketPlayOutSetSlot packet = new PacketPlayOutSetSlot(0, slot, item);
/* 220 */     entityPlayer.playerConnection.sendPacket((Packet)packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void hideEntityModel(AbstractEntity target) {
/* 225 */     Entity entity = BukkitAdapter.adapt(target);
/* 226 */     Entity me = ((CraftEntity)entity).getHandle();
/* 227 */     DataWatcher w = me.getDataWatcher();
/* 228 */     w.set(new DataWatcherObject(0, DataWatcherRegistry.a), Byte.valueOf((byte)32));
/*     */     
/* 230 */     PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(me.getId(), w, true);
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
/*     */   public AbstractEntity addNBTData(AbstractEntity entity, String key, Tag value) {
/* 245 */     CompoundTag compound = getNBTData(entity).createBuilder().put(key, value).build();
/* 246 */     setNBTData(entity, compound);
/* 247 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag getNBTData(AbstractEntity entity) {
/* 252 */     Entity bukkitEntity = entity.getBukkitEntity();
/*     */     
/* 254 */     NBTTagCompound compound = new NBTTagCompound();
/* 255 */     ((CraftEntity)bukkitEntity).getHandle().c(compound);
/* 256 */     return CompoundTag_v1_14_R1.fromNMSTag(compound);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractEntity setNBTData(AbstractEntity entity, CompoundTag compoundTag) {
/* 261 */     Entity bukkitEntity = entity.getBukkitEntity();
/* 262 */     ((CraftEntity)bukkitEntity).getHandle().f(((CompoundTag_v1_14_R1)compoundTag).toNMSTag());
/* 263 */     return entity;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_14_R1\VolatileEntityHandler_v1_14_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */