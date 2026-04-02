/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_12_R1;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileEntityHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_12_R1.CompoundTag_v1_12_R1;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_12_R1.Entity;
/*     */ import net.minecraft.server.v1_12_R1.EntityArmorStand;
/*     */ import net.minecraft.server.v1_12_R1.EntityInsentient;
/*     */ import net.minecraft.server.v1_12_R1.EntityItem;
/*     */ import net.minecraft.server.v1_12_R1.EntityLiving;
/*     */ import net.minecraft.server.v1_12_R1.EntityPlayer;
/*     */ import net.minecraft.server.v1_12_R1.ItemStack;
/*     */ import net.minecraft.server.v1_12_R1.NBTTagCompound;
/*     */ import net.minecraft.server.v1_12_R1.Packet;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutCloseWindow;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutEntityTeleport;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutGameStateChange;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutPosition;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutSetSlot;
/*     */ import net.minecraft.server.v1_12_R1.PacketPlayOutWorldBorder;
/*     */ import net.minecraft.server.v1_12_R1.WorldBorder;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftItem;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VolatileEntityHandler_v1_12_R1
/*     */   implements VolatileEntityHandler
/*     */ {
/*     */   public VolatileEntityHandler_v1_12_R1(VolatileCodeHandler handler) {}
/*     */   
/*     */   public void setEntityAbsorptionHearts(AbstractEntity entity, float value) {
/*  48 */     if (!entity.isLiving()) {
/*     */       return;
/*     */     }
/*     */     
/*  52 */     EntityLiving el = (EntityLiving)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*     */     
/*  54 */     el.setAbsorptionHearts(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEntityAbsorptionHearts(AbstractEntity entity) {
/*  59 */     if (!entity.isLiving()) {
/*  60 */       return 0.0F;
/*     */     }
/*     */     
/*  63 */     EntityLiving el = (EntityLiving)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*     */     
/*  65 */     return el.getAbsorptionHearts();
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
/*  85 */     e.world.entityJoinedWorld(e, false);
/*     */   }
/*     */   
/*     */   private void playerConnectionTeleport(AbstractEntity entity, double x, double y, double z, float yaw, float pitch, boolean noRotation, boolean noGravity) {
/*  89 */     EntityPlayer me = ((CraftPlayer)entity.getBukkitEntity()).getHandle();
/*  90 */     Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set = new HashSet<>();
/*     */     
/*  92 */     if (noRotation) {
/*  93 */       pitch = 0.0F;
/*  94 */       yaw = 0.0F;
/*  95 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.X_ROT);
/*  96 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y_ROT);
/*     */     } 
/*  98 */     if (noGravity) {
/*  99 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y);
/* 100 */       y = 0.0D;
/*     */     } 
/* 102 */     me.playerConnection.sendPacket((Packet)new PacketPlayOutPosition(x, y, z, yaw, pitch, set, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityInMotion(AbstractEntity entity) {
/* 107 */     if (entity.isLiving()) {
/* 108 */       EntityInsentient e = (EntityInsentient)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*     */       
/* 110 */       if (e.lastX != e.locX || e.lastY != e.locY || e.lastZ != e.locZ) {
/* 111 */         return true;
/*     */       }
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHitBox(AbstractEntity target, double a0, double a1, double a2) {
/* 119 */     Entity entity = BukkitAdapter.adapt(target);
/* 120 */     Entity me = ((CraftEntity)entity).getHandle();
/* 121 */     me.getBoundingBox().a(a0, a1, a2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemPosition(AbstractEntity target, AbstractLocation ol) {
/* 126 */     Entity entity = BukkitAdapter.adapt(target);
/*     */     
/* 128 */     if (entity instanceof Item) {
/* 129 */       Item item = (Item)entity;
/* 130 */       EntityItem ei = (EntityItem)((CraftItem)item).getHandle();
/* 131 */       ei.setPosition(ol.getX(), ol.getY(), ol.getZ());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendEntityTeleportPacket(AbstractEntity target) {
/* 137 */     Entity entity = BukkitAdapter.adapt(target);
/* 138 */     Entity me = ((CraftEntity)entity).getHandle();
/* 139 */     PacketPlayOutEntityTeleport tp = new PacketPlayOutEntityTeleport(me);
/*     */     
/* 141 */     entity.getLocation().getWorld().getNearbyEntities(entity.getLocation(), 32.0D, 32.0D, 32.0D).forEach(e -> {
/*     */           if (e instanceof Player) {
/*     */             (((CraftPlayer)e).getHandle()).playerConnection.sendPacket((Packet)paramPacketPlayOutEntityTeleport);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityRotation(AbstractEntity target, float pitch, float yaw) {
/* 150 */     Entity entity = BukkitAdapter.adapt(target);
/* 151 */     Entity me = ((CraftEntity)entity).getHandle();
/* 152 */     me.pitch = pitch;
/* 153 */     me.yaw = yaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArmorStandNoGravity(AbstractEntity target) {
/* 158 */     Entity entity = BukkitAdapter.adapt(target);
/*     */     
/* 160 */     if (entity.getType() == EntityType.ARMOR_STAND) {
/* 161 */       EntityArmorStand as = (EntityArmorStand)((CraftEntity)entity).getHandle();
/* 162 */       as.setNoGravity(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPlayerFakeInventoryItem(AbstractPlayer target, ItemStack stack, int slot) {
/* 168 */     if (slot < 9) {
/* 169 */       slot += 36;
/*     */     }
/* 171 */     Player player = BukkitAdapter.adapt(target);
/* 172 */     EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
/*     */     
/* 174 */     ItemStack item = CraftItemStack.asNMSCopy(stack);
/*     */     
/* 176 */     PacketPlayOutSetSlot packet = new PacketPlayOutSetSlot(0, slot, item);
/* 177 */     entityPlayer.playerConnection.sendPacket((Packet)packet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEntity addNBTData(AbstractEntity entity, String key, Tag value) {
/* 185 */     CompoundTag compound = getNBTData(entity).createBuilder().put(key, value).build();
/* 186 */     setNBTData(entity, compound);
/* 187 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag getNBTData(AbstractEntity entity) {
/* 192 */     Entity bukkitEntity = entity.getBukkitEntity();
/*     */     
/* 194 */     NBTTagCompound compound = new NBTTagCompound();
/* 195 */     ((CraftEntity)bukkitEntity).getHandle().c(compound);
/* 196 */     return CompoundTag_v1_12_R1.fromNMSTag(compound);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractEntity setNBTData(AbstractEntity entity, CompoundTag compoundTag) {
/* 201 */     Entity bukkitEntity = entity.getBukkitEntity();
/* 202 */     ((CraftEntity)bukkitEntity).getHandle().f(((CompoundTag_v1_12_R1)compoundTag).toNMSTag());
/* 203 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkybox(AbstractPlayer target, int skybox) {
/* 208 */     Player player = BukkitAdapter.adapt(target);
/* 209 */     PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(7, skybox);
/*     */     
/* 211 */     (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)packet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void forcePlayCredits(AbstractPlayer target, float f) {
/* 216 */     Player player = BukkitAdapter.adapt(target);
/* 217 */     EntityPlayer me = ((CraftPlayer)player).getHandle();
/* 218 */     me.playerConnection.sendPacket((Packet)new PacketPlayOutGameStateChange(4, f));
/*     */   }
/*     */ 
/*     */   
/*     */   public void forceCloseWindow(AbstractPlayer target) {
/* 223 */     Player player = BukkitAdapter.adapt(target);
/* 224 */     EntityPlayer me = ((CraftPlayer)player).getHandle();
/* 225 */     me.playerConnection.sendPacket((Packet)new PacketPlayOutCloseWindow(0));
/*     */   }
/*     */   
/*     */   public void setPlayerWorldBorder(AbstractPlayer target, AbstractLocation center, int radius) {
/*     */     WorldBorder border;
/* 230 */     Player player = BukkitAdapter.adapt(target);
/* 231 */     EntityPlayer ep = ((CraftPlayer)player).getHandle();
/*     */ 
/*     */ 
/*     */     
/* 235 */     if (radius == -1) {
/* 236 */       border = ep.world.getWorldBorder();
/*     */     } else {
/* 238 */       border = new WorldBorder();
/* 239 */       border.world = (ep.world.getWorldBorder()).world;
/* 240 */       border.setCenter(center.getX(), center.getZ());
/* 241 */       border.setSize(radius);
/* 242 */       border.setWarningDistance(1);
/*     */     } 
/* 244 */     ep.playerConnection.sendPacket((Packet)new PacketPlayOutWorldBorder(border, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE));
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_12_R1\VolatileEntityHandler_v1_12_R1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */