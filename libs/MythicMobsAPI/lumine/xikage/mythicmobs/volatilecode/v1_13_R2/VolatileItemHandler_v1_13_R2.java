/*    */ package lumine.xikage.mythicmobs.volatilecode.v1_13_R2;
/*    */ 
/*    */ import io.lumine.utils.reflection.Reflector;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*    */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeHandler;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileItemHandler;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.v1_13_R2.CompoundTag_v1_13_R2;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.server.v1_13_R2.ItemStack;
/*    */ import net.minecraft.server.v1_13_R2.NBTTagCompound;
/*    */ import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class VolatileItemHandler_v1_13_R2
/*    */   implements VolatileItemHandler {
/* 17 */   private Reflector refItemStack = new Reflector(CraftItemStack.class, new String[] { "handle" });
/*    */ 
/*    */ 
/*    */   
/*    */   public VolatileItemHandler_v1_13_R2(VolatileCodeHandler handler) {}
/*    */ 
/*    */   
/*    */   public void destroyItem(ItemStack itemStack) {
/* 25 */     ItemStack realStack = (ItemStack)this.refItemStack.get(itemStack, "handle");
/* 26 */     realStack.setCount(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack addNBTData(ItemStack itemStack, String key, Tag value) {
/* 31 */     ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
/* 32 */     NBTTagCompound tag = nmsItemStack.hasTag() ? nmsItemStack.getTag() : new NBTTagCompound();
/* 33 */     CompoundTag compound = CompoundTag_v1_13_R2.fromNMSTag(tag).createBuilder().put(key, value).build();
/* 34 */     nmsItemStack.setTag(((CompoundTag_v1_13_R2)compound).toNMSTag());
/* 35 */     return CraftItemStack.asBukkitCopy(nmsItemStack);
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundTag getNBTData(ItemStack itemStack) {
/* 40 */     ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
/* 41 */     if (nmsItemStack != null && nmsItemStack.hasTag()) {
/* 42 */       return CompoundTag_v1_13_R2.fromNMSTag(nmsItemStack.getTag());
/*    */     }
/* 44 */     return (CompoundTag)new CompoundTag_v1_13_R2(new HashMap<>());
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack setNBTData(ItemStack itemStack, CompoundTag compoundTag) {
/* 49 */     ItemStack nmsItemStack = (ItemStack)this.refItemStack.get(itemStack, "handle");
/* 50 */     nmsItemStack.setTag(((CompoundTag_v1_13_R2)compoundTag).toNMSTag());
/* 51 */     return CraftItemStack.asBukkitCopy(nmsItemStack);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\v1_13_R2\VolatileItemHandler_v1_13_R2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */