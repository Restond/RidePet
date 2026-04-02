/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillToggleLever;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockRedstoneEvent;
/*    */ import org.bukkit.material.Lever;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class LeverUnswitcher
/*    */   implements Runnable
/*    */ {
/*    */   private Block block;
/*    */   
/*    */   public LeverUnswitcher(Block block) {
/* 61 */     this.block = block;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     try {
/* 67 */       if (!this.block.getChunk().isLoaded()) {
/* 68 */         this.block.getChunk().load();
/*    */       }
/* 70 */       Lever button = new Lever(Material.LEVER, this.block.getData());
/* 71 */       button.setPowered(false);
/* 72 */       BlockRedstoneEvent toggle = new BlockRedstoneEvent(this.block, 0, 1);
/* 73 */       Bukkit.getPluginManager().callEvent((Event)toggle);
/*    */     }
/* 75 */     catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillToggleLever$LeverUnswitcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */