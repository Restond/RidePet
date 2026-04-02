/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.IItemDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*    */ 
/*    */ public class MythicItemDrop
/*    */   extends Drop implements IItemDrop {
/*    */   private final MythicItem item;
/*    */   
/*    */   public MythicItemDrop(String line, String item, MythicLineConfig config) {
/* 16 */     super(line, config);
/* 17 */     this.item = MythicMobs.inst().getItemManager().getItem(item).get();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractItemStack getDrop(DropMetadata metadata) {
/* 22 */     int amount = (int)getAmount();
/* 23 */     return this.item.generateItemStack(metadata, amount);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\MythicItemDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */