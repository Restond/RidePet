/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_8_R3;
/*    */ 
/*    */ import io.lumine.utils.reflection.Reflector;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileItemHandler;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_8_R3.CompoundTag_v1_8_R3;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.server.v1_8_R3.ItemStack;
/*    */ import net.minecraft.server.v1_8_R3.NBTTagCompound;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class VolatileItemHandler_v1_8_R3
/*    */   implements VolatileItemHandler {
/* 18 */   private Reflector refItemStack = new Reflector(CraftItemStack.class, new String[] { "handle" });
/*    */ 
/*    */ 
/*    */   
/*    */   public VolatileItemHandler_v1_8_R3(VolatileCodeHandler handler) {}
/*    */ 
/*    */   
/*    */   public void destroyItem(ItemStack itemStack) {
/* 26 */     itemStack.setAmount(0);
/* 27 */     itemStack.setType(Material.AIR);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack addNBTData(ItemStack itemStack, String key, Tag value) {
/* 32 */     ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
/* 33 */     NBTTagCompound tag = nmsItemStack.hasTag() ? nmsItemStack.getTag() : new NBTTagCompound();
/* 34 */     CompoundTag compound = CompoundTag_v1_8_R3.fromNMSTag(tag).createBuilder().put(key, value).build();
/* 35 */     nmsItemStack.setTag(((CompoundTag_v1_8_R3)compound).toNMSTag());
/* 36 */     return CraftItemStack.asBukkitCopy(nmsItemStack);
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundTag getNBTData(ItemStack itemStack) {
/* 41 */     ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
/* 42 */     if (nmsItemStack != null && nmsItemStack.hasTag()) {
/* 43 */       return CompoundTag_v1_8_R3.fromNMSTag(nmsItemStack.getTag());
/*    */     }
/* 45 */     return (CompoundTag)new CompoundTag_v1_8_R3(new HashMap<>());
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack setNBTData(ItemStack itemStack, CompoundTag compoundTag) {
/* 50 */     ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
/* 51 */     nmsItemStack.setTag(((CompoundTag_v1_8_R3)compoundTag).toNMSTag());
/* 52 */     return CraftItemStack.asBukkitCopy(nmsItemStack);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_8_R3\VolatileItemHandler_v1_8_R3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */