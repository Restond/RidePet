package lumine.xikage.mythicmobs.volatilecode.handlers;

import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
import org.bukkit.inventory.ItemStack;

public interface VolatileItemHandler {
  ItemStack addNBTData(ItemStack paramItemStack, String paramString, Tag paramTag);
  
  CompoundTag getNBTData(ItemStack paramItemStack);
  
  ItemStack setNBTData(ItemStack paramItemStack, CompoundTag paramCompoundTag);
  
  void destroyItem(ItemStack paramItemStack);
}


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\handlers\VolatileItemHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */