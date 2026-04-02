/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_13_R2;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileEntityHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.CompoundTag_v1_13_R2;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_13_R2.Entity;
/*     */ import net.minecraft.server.v1_13_R2.EntityArmorStand;
/*     */ import net.minecraft.server.v1_13_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_13_R2.EntityItem;
/*     */ import net.minecraft.server.v1_13_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_13_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_13_R2.ItemStack;
/*     */ import net.minecraft.server.v1_13_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_13_R2.Packet;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutCloseWindow;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutEntityTeleport;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutGameStateChange;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutPosition;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutSetSlot;
/*     */ import net.minecraft.server.v1_13_R2.PacketPlayOutWorldBorder;
/*     */ import net.minecraft.server.v1_13_R2.WorldBorder;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.entity.CraftItem;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VolatileEntityHandler_v1_13_R2
/*     */   implements VolatileEntityHandler
/*     */ {
/*     */   public VolatileEntityHandler_v1_13_R2(VolatileCodeHandler handler) {}
/*     */   
/*     */   public void setEntityAbsorptionHearts(AbstractEntity entity, float value) {
/*  48 */     if (!entity.isLiving()) {
/*     */       return;
/*     */     }
/*  51 */     EntityLiving el = (EntityLiving)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*  52 */     el.setAbsorptionHearts(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEntityAbsorptionHearts(AbstractEntity entity) {
/*  57 */     if (!entity.isLiving()) {
/*  58 */       return 0.0F;
/*     */     }
/*  60 */     EntityLiving el = (EntityLiving)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*  61 */     return el.getAbsorptionHearts();
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
/*  76 */     Entity e = ((CraftEntity)entity.getBukkitEntity()).getHandle();
/*  77 */     e.setLocation(x, y, z, yaw, pitch);
/*  78 */     if (entity.isPlayer()) {
/*  79 */       playerConnectionTeleport(entity, x, y, z, yaw, pitch, noRotation, noGravity);
/*     */     }
/*  81 */     e.world.entityJoinedWorld(e, false);
/*     */   }
/*     */   
/*     */   private void playerConnectionTeleport(AbstractEntity entity, double x, double y, double z, float yaw, float pitch, boolean noRotation, boolean noGravity) {
/*  85 */     EntityPlayer me = ((CraftPlayer)entity.getBukkitEntity()).getHandle();
/*  86 */     Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set = new HashSet<>();
/*     */     
/*  88 */     if (noRotation) {
/*  89 */       pitch = 0.0F;
/*  90 */       yaw = 0.0F;
/*  91 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.X_ROT);
/*  92 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y_ROT);
/*     */     } 
/*  94 */     if (noGravity) {
/*  95 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y);
/*  96 */       y = 0.0D;
/*     */     } 
/*  98 */     me.playerConnection.sendPacket((Packet)new PacketPlayOutPosition(x, y, z, yaw, pitch, set, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityInMotion(AbstractEntity entity) {
/* 103 */     if (entity.isLiving()) {
/* 104 */       EntityInsentient e = (EntityInsentient)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*     */       
/* 106 */       if (e.lastX != e.locX || e.lastY != e.locY || e.lastZ != e.locZ) {
/* 107 */         return true;
/*     */       }
/*     */     } 
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHitBox(AbstractEntity target, double a0, double a1, double a2) {
/* 115 */     Entity entity = BukkitAdapter.adapt(target);
/* 116 */     Entity me = ((CraftEntity)entity).getHandle();
/* 117 */     me.getBoundingBox().a(a0, a1, a2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemPosition(AbstractEntity target, AbstractLocation ol) {
/* 122 */     Entity entity = BukkitAdapter.adapt(target);
/*     */     
/* 124 */     if (entity instanceof Item) {
/* 125 */       Item item = (Item)entity;
/* 126 */       EntityItem ei = (EntityItem)((CraftItem)item).getHandle();
/* 127 */       ei.setPosition(ol.getX(), ol.getY(), ol.getZ());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendEntityTeleportPacket(AbstractEntity target) {
/* 133 */     Entity entity = BukkitAdapter.adapt(target);
/* 134 */     Entity me = ((CraftEntity)entity).getHandle();
/* 135 */     PacketPlayOutEntityTeleport tp = new PacketPlayOutEntityTeleport(me);
/*     */     
/* 137 */     entity.getLocation().getWorld().getNearbyEntities(entity.getLocation(), 32.0D, 32.0D, 32.0D).forEach(e -> {
/*     */           if (e instanceof Player) {
/*     */             (((CraftPlayer)e).getHandle()).playerConnection.sendPacket((Packet)paramPacketPlayOutEntityTeleport);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityRotation(AbstractEntity target, float pitch, float yaw) {
/* 146 */     Entity entity = BukkitAdapter.adapt(target);
/* 147 */     Entity me = ((CraftEntity)entity).getHandle();
/* 148 */     me.pitch = pitch;
/* 149 */     me.yaw = yaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityPitch(AbstractEntity target, float pitch) {
/* 154 */     Entity entity = BukkitAdapter.adapt(target);
/* 155 */     Entity me = ((CraftEntity)entity).getHandle();
/* 156 */     me.pitch = pitch;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArmorStandNoGravity(AbstractEntity target) {
/* 161 */     Entity entity = BukkitAdapter.adapt(target);
/*     */     
/* 163 */     if (entity.getType() == EntityType.ARMOR_STAND) {
/* 164 */       EntityArmorStand as = (EntityArmorStand)((CraftEntity)entity).getHandle();
/* 165 */       as.setNoGravity(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void forcePlayCredits(AbstractPlayer target, float f) {
/* 171 */     Player player = BukkitAdapter.adapt(target);
/* 172 */     EntityPlayer me = ((CraftPlayer)player).getHandle();
/* 173 */     me.playerConnection.sendPacket((Packet)new PacketPlayOutGameStateChange(4, f));
/*     */   }
/*     */ 
/*     */   
/*     */   public void forceCloseWindow(AbstractPlayer target) {
/* 178 */     Player player = BukkitAdapter.adapt(target);
/* 179 */     EntityPlayer me = ((CraftPlayer)player).getHandle();
/* 180 */     me.playerConnection.sendPacket((Packet)new PacketPlayOutCloseWindow(0));
/*     */   }
/*     */   
/*     */   public void setPlayerWorldBorder(AbstractPlayer target, AbstractLocation center, int radius) {
/*     */     WorldBorder border;
/* 185 */     Player player = BukkitAdapter.adapt(target);
/* 186 */     EntityPlayer ep = ((CraftPlayer)player).getHandle();
/*     */ 
/*     */ 
/*     */     
/* 190 */     if (radius == -1) {
/* 191 */       border = ep.world.getWorldBorder();
/*     */     } else {
/* 193 */       border = new WorldBorder();
/* 194 */       border.world = (ep.world.getWorldBorder()).world;
/* 195 */       border.setCenter(center.getX(), center.getZ());
/* 196 */       border.setSize(radius);
/* 197 */       border.setWarningDistance(1);
/*     */     } 
/* 199 */     ep.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(border, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPlayerFakeInventoryItem(AbstractPlayer target, ItemStack stack, int slot) {
/* 204 */     if (slot < 9) {
/* 205 */       slot += 36;
/*     */     }
/* 207 */     Player player = BukkitAdapter.adapt(target);
/* 208 */     EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
/*     */     
/* 210 */     ItemStack item = CraftItemStack.asNMSCopy(stack);
/*     */     
/* 212 */     PacketPlayOutSetSlot packet = new PacketPlayOutSetSlot(0, slot, item);
/* 213 */     entityPlayer.playerConnection.sendPacket((Packet)packet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEntity addNBTData(AbstractEntity entity, String key, Tag value) {
/* 221 */     CompoundTag compound = getNBTData(entity).createBuilder().put(key, value).build();
/* 222 */     setNBTData(entity, compound);
/* 223 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag getNBTData(AbstractEntity entity) {
/* 228 */     Entity bukkitEntity = entity.getBukkitEntity();
/*     */     
/* 230 */     NBTTagCompound compound = new NBTTagCompound();
/* 231 */     ((CraftEntity)bukkitEntity).getHandle().c(compound);
/* 232 */     return CompoundTag_v1_13_R2.fromNMSTag(compound);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractEntity setNBTData(AbstractEntity entity, CompoundTag compoundTag) {
/* 237 */     Entity bukkitEntity = entity.getBukkitEntity();
/* 238 */     ((CraftEntity)bukkitEntity).getHandle().f(((CompoundTag_v1_13_R2)compoundTag).toNMSTag());
/* 239 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkybox(AbstractPlayer target, int skybox) {
/* 244 */     Player player = BukkitAdapter.adapt(target);
/* 245 */     PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(7, skybox);
/*     */     
/* 247 */     (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)packet);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_13_R2\VolatileEntityHandler_v1_13_R2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */