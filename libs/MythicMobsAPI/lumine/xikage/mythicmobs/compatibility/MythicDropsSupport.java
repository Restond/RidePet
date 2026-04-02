/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.tealcube.minecraft.bukkit.mythicdrops.MythicDropsPlugin;
/*    */ import com.tealcube.minecraft.bukkit.mythicdrops.api.items.builders.DropBuilder;
/*    */ import com.tealcube.minecraft.bukkit.mythicdrops.api.tiers.Tier;
/*    */ import com.tealcube.minecraft.bukkit.mythicdrops.utils.TierUtil;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MythicDropsSupport
/*    */ {
/* 15 */   private MythicDropsPlugin handle = (MythicDropsPlugin)Bukkit.getPluginManager().getPlugin("MythicDrops");
/*    */ 
/*    */   
/*    */   public MythicDropsPlugin getMythicDrops() {
/* 19 */     return this.handle;
/*    */   }
/*    */   
/*    */   public ItemStack getLoot() {
/* 23 */     DropBuilder db = MythicDropsPlugin.getNewDropBuilder();
/*    */     
/* 25 */     return db.build();
/*    */   }
/*    */   
/*    */   public ItemStack getTierLoot(String t) {
/* 29 */     Tier tier = TierUtil.getTier(t);
/*    */     
/* 31 */     DropBuilder db = MythicDropsPlugin.getNewDropBuilder().withTier(tier);
/*    */     
/* 33 */     return db.build();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\MythicDropsSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */