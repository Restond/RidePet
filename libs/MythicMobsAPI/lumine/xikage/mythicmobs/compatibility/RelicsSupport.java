/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.herocraftonline.items.api.ItemPlugin;
/*    */ import com.herocraftonline.items.api.item.Item;
/*    */ import com.herocraftonline.items.api.item.ItemManager;
/*    */ import java.util.Optional;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class RelicsSupport
/*    */ {
/*    */   private ItemManager itemManager;
/*    */   
/*    */   public RelicsSupport() {
/* 16 */     ItemPlugin ip = (ItemPlugin)Bukkit.getPluginManager().getPlugin("Relics");
/* 17 */     this.itemManager = ip.getItemManager();
/*    */   }
/*    */   
/*    */   public Optional<ItemStack> getItem(String name, Object[] argz) {
/* 21 */     if (this.itemManager.hasItemConfig(name)) {
/* 22 */       if (argz == null) {
/* 23 */         return Optional.of(((Item)this.itemManager.getItem(name, new Object[0]).get()).getItem());
/*    */       }
/* 25 */       return Optional.of(((Item)this.itemManager.getItem(name, argz).get()).getItem());
/*    */     } 
/*    */     
/* 28 */     return Optional.empty();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\RelicsSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */