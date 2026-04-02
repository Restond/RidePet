/*     */ package lumine.xikage.mythicmobs.volatilecode.v1_8_R3;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileEntityHandler;
/*     */ import io.lumine.xikage.mythicmobs.volatilecode.v1_8_R3.CompoundTag_v1_8_R3;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_8_R3.Entity;
/*     */ import net.minecraft.server.v1_8_R3.EntityArmorStand;
/*     */ import net.minecraft.server.v1_8_R3.EntityInsentient;
/*     */ import net.minecraft.server.v1_8_R3.EntityItem;
/*     */ import net.minecraft.server.v1_8_R3.EntityLiving;
/*     */ import net.minecraft.server.v1_8_R3.EntityPlayer;
/*     */ import net.minecraft.server.v1_8_R3.ItemStack;
/*     */ import net.minecraft.server.v1_8_R3.NBTTagCompound;
/*     */ import net.minecraft.server.v1_8_R3.Packet;
/*     */ import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
/*     */ import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
/*     */ import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
/*     */ import net.minecraft.server.v1_8_R3.PacketPlayOutSetSlot;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.entity.CraftItem;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VolatileEntityHandler_v1_8_R3
/*     */   implements VolatileEntityHandler
/*     */ {
/*     */   public VolatileEntityHandler_v1_8_R3(VolatileCodeHandler handler) {}
/*     */   
/*     */   public void setEntityAbsorptionHearts(AbstractEntity entity, float value) {
/*  45 */     if (!entity.isLiving()) {
/*     */       return;
/*     */     }
/*     */     
/*  49 */     EntityLiving el = (EntityLiving)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*     */     
/*  51 */     el.setAbsorptionHearts(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEntityAbsorptionHearts(AbstractEntity entity) {
/*  56 */     if (!entity.isLiving()) {
/*  57 */       return 0.0F;
/*     */     }
/*     */     
/*  60 */     EntityLiving el = (EntityLiving)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*     */     
/*  62 */     return el.getAbsorptionHearts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(AbstractEntity entity, AbstractLocation location) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(AbstractEntity entity, double x, double y, double z, float yaw, float pitch) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(AbstractEntity entity, double x, double y, double z, float yaw, float pitch, boolean noRotation, boolean noGravity) {
/*  78 */     Entity e = ((CraftEntity)entity.getBukkitEntity()).getHandle();
/*  79 */     e.setLocation(x, y, z, yaw, pitch);
/*  80 */     if (entity.isPlayer()) {
/*  81 */       playerConnectionTeleport(entity, x, y, z, yaw, pitch, noRotation, noGravity);
/*     */     }
/*  83 */     e.world.entityJoinedWorld(e, false);
/*     */   }
/*     */   
/*     */   private void playerConnectionTeleport(AbstractEntity entity, double x, double y, double z, float yaw, float pitch, boolean noRotation, boolean noGravity) {
/*  87 */     EntityPlayer me = ((CraftPlayer)entity.getBukkitEntity()).getHandle();
/*  88 */     Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set = new HashSet<>();
/*     */     
/*  90 */     if (noRotation) {
/*  91 */       pitch = 0.0F;
/*  92 */       yaw = 0.0F;
/*  93 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.X_ROT);
/*  94 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y_ROT);
/*     */     } 
/*  96 */     if (noGravity) {
/*  97 */       set.add(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y);
/*  98 */       y = 0.0D;
/*     */     } 
/* 100 */     me.playerConnection.sendPacket((Packet)new PacketPlayOutPosition(x, y, z, yaw, pitch, set));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityInMotion(AbstractEntity entity) {
/* 105 */     if (entity.isLiving()) {
/* 106 */       EntityInsentient e = (EntityInsentient)((CraftEntity)entity.getBukkitEntity()).getHandle();
/*     */       
/* 108 */       if (e.lastX != e.locX || e.lastY != e.locY || e.lastZ != e.locZ) {
/* 109 */         return true;
/*     */       }
/*     */     } 
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHitBox(AbstractEntity target, double a0, double a1, double a2) {
/* 117 */     Entity entity = BukkitAdapter.adapt(target);
/* 118 */     Entity me = ((CraftEntity)entity).getHandle();
/* 119 */     me.getBoundingBox().a(a0, a1, a2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemPosition(AbstractEntity target, AbstractLocation ol) {
/* 124 */     Entity entity = BukkitAdapter.adapt(target);
/*     */     
/* 126 */     if (entity instanceof Item) {
/* 127 */       Item item = (Item)entity;
/* 128 */       EntityItem ei = (EntityItem)((CraftItem)item).getHandle();
/* 129 */       ei.setPosition(ol.getX(), ol.getY(), ol.getZ());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendEntityTeleportPacket(AbstractEntity target) {
/* 135 */     Entity entity = BukkitAdapter.adapt(target);
/* 136 */     Entity me = ((CraftEntity)entity).getHandle();
/* 137 */     PacketPlayOutEntityTeleport tp = new PacketPlayOutEntityTeleport(me);
/*     */     
/* 139 */     entity.getLocation().getWorld().getNearbyEntities(entity.getLocation(), 32.0D, 32.0D, 32.0D).forEach(e -> {
/*     */           if (e instanceof Player) {
/*     */             (((CraftPlayer)e).getHandle()).playerConnection.sendPacket((Packet)paramPacketPlayOutEntityTeleport);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityRotation(AbstractEntity target, float pitch, float yaw) {
/* 148 */     Entity entity = BukkitAdapter.adapt(target);
/* 149 */     Entity me = ((CraftEntity)entity).getHandle();
/* 150 */     me.pitch = pitch;
/* 151 */     me.yaw = yaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArmorStandNoGravity(AbstractEntity target) {
/* 156 */     Entity entity = BukkitAdapter.adapt(target);
/*     */     
/* 158 */     if (entity.getType() == EntityType.ARMOR_STAND) {
/* 159 */       EntityArmorStand as = (EntityArmorStand)((CraftEntity)entity).getHandle();
/* 160 */       as.setGravity(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPlayerFakeInventoryItem(AbstractPlayer target, ItemStack stack, int slot) {
/* 166 */     if (slot < 9) {
/* 167 */       slot += 36;
/*     */     }
/* 169 */     Player player = BukkitAdapter.adapt(target);
/* 170 */     EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
/*     */     
/* 172 */     ItemStack item = CraftItemStack.asNMSCopy(stack);
/*     */     
/* 174 */     PacketPlayOutSetSlot packet = new PacketPlayOutSetSlot(0, slot, item);
/* 175 */     entityPlayer.playerConnection.sendPacket((Packet)packet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEntity addNBTData(AbstractEntity entity, String key, Tag value) {
/* 183 */     CompoundTag compound = getNBTData(entity).createBuilder().put(key, value).build();
/* 184 */     setNBTData(entity, compound);
/* 185 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundTag getNBTData(AbstractEntity entity) {
/* 190 */     Entity bukkitEntity = entity.getBukkitEntity();
/*     */     
/* 192 */     NBTTagCompound compound = new NBTTagCompound();
/* 193 */     ((CraftEntity)bukkitEntity).getHandle().c(compound);
/* 194 */     return CompoundTag_v1_8_R3.fromNMSTag(compound);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractEntity setNBTData(AbstractEntity entity, CompoundTag compoundTag) {
/* 199 */     Entity bukkitEntity = entity.getBukkitEntity();
/* 200 */     ((CraftEntity)bukkitEntity).getHandle().f(((CompoundTag_v1_8_R3)compoundTag).toNMSTag());
/* 201 */     return entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkybox(AbstractPlayer target, int skybox) {
/* 206 */     Player player = BukkitAdapter.adapt(target);
/* 207 */     PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(7, skybox);
/*     */     
/* 209 */     (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)packet);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_8_R3\VolatileEntityHandler_v1_8_R3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */