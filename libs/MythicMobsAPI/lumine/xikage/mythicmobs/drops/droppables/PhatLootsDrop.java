/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.IMultiDrop;
/*    */ import io.lumine.xikage.mythicmobs.drops.LootBag;
/*    */ import io.lumine.xikage.mythicmobs.drops.droppables.ExperienceDrop;
/*    */ import io.lumine.xikage.mythicmobs.drops.droppables.ItemDrop;
/*    */ import io.lumine.xikage.mythicmobs.drops.droppables.VaultDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class PhatLootsDrop extends Drop implements IMultiDrop {
/*    */   public PhatLootsDrop(String line, MythicLineConfig config) {
/* 19 */     super(line, config);
/*    */     
/* 21 */     String s = config.getString(new String[] { "type", "t" }, this.dropVar, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootBag get(DropMetadata meta) {
/* 26 */     LootBag loot = new LootBag(meta);
/*    */     
/* 28 */     if (this.dropVar != null && CompatibilityManager.PhatLoots != null) {
/*    */       
/* 30 */       List<ItemStack> dropsList = new ArrayList<>();
/* 31 */       int exp = 0;
/* 32 */       int money = 0;
/*    */       
/* 34 */       for (int i = 0; i < getAmount(); i++) {
/* 35 */         if (CompatibilityManager.PhatLoots.generateBundle(this.dropVar) == true) {
/* 36 */           dropsList.addAll(CompatibilityManager.PhatLoots.getLootBundleItems());
/* 37 */           exp += CompatibilityManager.PhatLoots.getLootBundleXP();
/* 38 */           money = (int)(money + CompatibilityManager.PhatLoots.getLootBundleMoney());
/*    */         } 
/*    */       } 
/*    */       
/* 42 */       for (ItemStack item : dropsList) {
/* 43 */         loot.add(meta, (Drop)new ItemDrop(getLine(), (MythicLineConfig)getConfig(), new BukkitItemStack(item)));
/*    */       }
/* 45 */       if (exp > 0) {
/* 46 */         loot.add(meta, (Drop)new ExperienceDrop(getLine(), (MythicLineConfig)getConfig(), exp));
/*    */       }
/* 48 */       if (money > 0) {
/* 49 */         loot.add(meta, (Drop)new VaultDrop(getLine(), (MythicLineConfig)getConfig(), money));
/*    */       }
/*    */     } 
/*    */     
/* 53 */     return loot;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\PhatLootsDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */