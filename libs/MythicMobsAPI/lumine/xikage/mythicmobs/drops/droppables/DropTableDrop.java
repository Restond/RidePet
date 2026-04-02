/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropTable;
/*    */ import io.lumine.xikage.mythicmobs.drops.IMultiDrop;
/*    */ import io.lumine.xikage.mythicmobs.drops.LootBag;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ 
/*    */ public class DropTableDrop
/*    */   extends Drop implements IMultiDrop {
/*    */   private DropTable dropTable;
/*    */   
/*    */   public DropTableDrop(String line, String item, MythicLineConfig config) {
/* 16 */     super(line, config);
/*    */     
/* 18 */     this.dropTable = MythicMobs.inst().getDropManager().getDropTable(item).get();
/*    */   }
/*    */   
/*    */   public LootBag get(DropMetadata meta) {
/* 22 */     return this.dropTable.generate(meta);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\DropTableDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */