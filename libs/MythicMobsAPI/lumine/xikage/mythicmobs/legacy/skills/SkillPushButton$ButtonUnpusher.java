/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillPushButton;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockRedstoneEvent;
/*    */ import org.bukkit.material.Button;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ButtonUnpusher
/*    */   implements Runnable
/*    */ {
/*    */   private Block block;
/*    */   
/*    */   public ButtonUnpusher(Block block) {
/* 54 */     this.block = block;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     try {
/* 60 */       this.block.getChunk().load();
/* 61 */       Button button = new Button(Material.STONE_BUTTON, this.block.getData());
/* 62 */       button.setPowered(false);
/* 63 */       BlockRedstoneEvent toggle = new BlockRedstoneEvent(this.block, 0, 1);
/* 64 */       Bukkit.getPluginManager().callEvent((Event)toggle);
/*    */     }
/* 66 */     catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillPushButton$ButtonUnpusher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */