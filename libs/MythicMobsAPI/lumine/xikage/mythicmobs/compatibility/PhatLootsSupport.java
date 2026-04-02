/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.codisimus.plugins.phatloots.PhatLoots;
/*    */ import com.codisimus.plugins.phatloots.PhatLootsAPI;
/*    */ import com.codisimus.plugins.phatloots.loot.LootBundle;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PhatLootsSupport
/*    */ {
/*    */   PhatLoots phatloots;
/*    */   public boolean enabled = false;
/* 18 */   private LootBundle lastBundle = null;
/*    */   
/*    */   public PhatLootsSupport() {
/* 21 */     this.phatloots = (PhatLoots)Bukkit.getPluginManager().getPlugin("PhatLoots");
/*    */     
/* 23 */     if (this.phatloots != null) {
/* 24 */       this.enabled = true;
/*    */     } else {
/* 26 */       this.enabled = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean generateBundle(String pl) {
/* 31 */     if (!this.enabled) return false;
/*    */     
/* 33 */     LootBundle lb = PhatLootsAPI.rollForLoot(pl);
/*    */     
/* 35 */     if (lb != null) {
/* 36 */       this.lastBundle = lb;
/* 37 */       return true;
/*    */     } 
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<ItemStack> getLootBundleItems() {
/* 44 */     if (!this.enabled) return null;
/*    */     
/* 46 */     return this.lastBundle.getItemList();
/*    */   }
/*    */   public int getLootBundleXP() {
/* 49 */     if (!this.enabled) return 0;
/*    */     
/* 51 */     return this.lastBundle.getExp();
/*    */   }
/*    */   public double getLootBundleMoney() {
/* 54 */     if (!this.enabled) return 0.0D;
/*    */     
/* 56 */     return this.lastBundle.getMoney();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\PhatLootsSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */