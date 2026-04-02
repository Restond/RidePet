/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitItemStack;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.IMultiDrop;
/*    */ import io.lumine.xikage.mythicmobs.drops.LootBag;
/*    */ import io.lumine.xikage.mythicmobs.drops.droppables.ItemDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MythicDropsDrop
/*    */   extends Drop
/*    */   implements IMultiDrop
/*    */ {
/*    */   public MythicDropsDrop(String line, MythicLineConfig config) {
/* 20 */     super(line, config);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootBag get(DropMetadata meta) {
/* 25 */     LootBag loot = new LootBag(meta);
/*    */     
/* 27 */     if (CompatibilityManager.MythicDrops != null) {
/* 28 */       for (int i = 0; i < getAmount(); i++) {
/*    */         ItemStack md;
/*    */         
/* 31 */         if (this.dropVar != null) {
/* 32 */           md = CompatibilityManager.MythicDrops.getTierLoot(this.dropVar);
/*    */         } else {
/* 34 */           md = CompatibilityManager.MythicDrops.getLoot();
/*    */         } 
/*    */         
/* 37 */         if (md != null) {
/* 38 */           loot.add(meta, (Drop)new ItemDrop(getLine(), (MythicLineConfig)getConfig(), new BukkitItemStack(md)));
/*    */         }
/*    */       } 
/*    */     }
/*    */     
/* 43 */     return loot;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\MythicDropsDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */