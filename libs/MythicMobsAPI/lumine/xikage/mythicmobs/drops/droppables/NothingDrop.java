/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.ILocationDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ 
/*    */ public class NothingDrop
/*    */   extends Drop implements ILocationDrop {
/*    */   public NothingDrop(String line, MythicLineConfig config) {
/* 12 */     super(line, config);
/*    */   }
/*    */   
/*    */   public NothingDrop(String line, MythicLineConfig config, double amount) {
/* 16 */     super(line, config, amount);
/*    */   }
/*    */   
/*    */   public void drop(AbstractLocation target, DropMetadata metadata) {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\NothingDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */