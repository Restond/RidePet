/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import io.lumine.artifacts.Artifacts;
/*    */ import io.lumine.artifacts.enchants.MythicEnchantment;
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArtifactsSupport
/*    */ {
/* 20 */   private Artifacts artifacts = Artifacts.inst();
/*    */ 
/*    */   
/*    */   public void reparseWeapon(AbstractPlayer player) {
/* 24 */     Schedulers.sync().runLater(() -> Artifacts.inst().getProfileManager().getPlayerProfile(BukkitAdapter.adapt(paramAbstractPlayer)).parseWeapons(), 1L);
/*    */   }
/*    */   
/*    */   public boolean handleEnchant(ItemStack item, Enchantment enchant, int level) {
/* 28 */     if (enchant instanceof MythicEnchantment) {
/* 29 */       ((MythicEnchantment)enchant).applyToItem(item, level);
/* 30 */       return true;
/*    */     } 
/* 32 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\ArtifactsSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */