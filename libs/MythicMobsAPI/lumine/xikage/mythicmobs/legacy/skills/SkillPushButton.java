/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockRedstoneEvent;
/*    */ import org.bukkit.material.Button;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillPushButton
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 19 */     String[] base = skill.split(" ");
/* 20 */     String[] data = base[1].split(":");
/*    */     
/* 22 */     int x = Integer.parseInt(data[0]);
/* 23 */     int y = Integer.parseInt(data[1]);
/* 24 */     int z = Integer.parseInt(data[2]);
/*    */     
/* 26 */     Location location = l.getLocation().clone();
/*    */     
/* 28 */     location.setX(x);
/* 29 */     location.setY(y);
/* 30 */     location.setZ(z);
/*    */     
/* 32 */     Block block = l.getWorld().getBlockAt(location);
/*    */     
/* 34 */     MythicMobs.debug(2, "Executing pushbutton skill @ " + x + "," + y + "," + z + " ");
/*    */     try {
/* 36 */       Button button = new Button(Material.STONE_BUTTON, block.getData());
/* 37 */       button.setPowered(true);
/* 38 */       BlockRedstoneEvent toggle = new BlockRedstoneEvent(block, 0, 1);
/* 39 */       Bukkit.getPluginManager().callEvent((Event)toggle);
/*    */ 
/*    */       
/* 42 */       ButtonUnpusher BU = new ButtonUnpusher(block);
/* 43 */       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)BU, 10L);
/*    */     }
/* 45 */     catch (Exception e) {
/* 46 */       MythicMobs.error("A pushbutton skill is improperly configured: block is not a button. AbstractSkill=" + skill);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillPushButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */