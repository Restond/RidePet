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
/*    */ import org.bukkit.material.Lever;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillToggleLever
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 19 */     String[] base = skill.split(" ");
/* 20 */     String[] data = base[1].split(":");
/*    */     
/* 22 */     int x = Integer.parseInt(data[0]);
/* 23 */     int y = Integer.parseInt(data[1]);
/* 24 */     int z = Integer.parseInt(data[2]);
/* 25 */     int duration = 10;
/*    */     
/*    */     try {
/* 28 */       duration = Integer.parseInt(data[3]);
/* 29 */     } catch (Exception e) {
/* 30 */       duration = 10;
/*    */     } 
/*    */     
/* 33 */     Location location = l.getLocation().clone();
/*    */     
/* 35 */     location.setX(x);
/* 36 */     location.setY(y);
/* 37 */     location.setZ(z);
/*    */     
/* 39 */     Block block = l.getWorld().getBlockAt(location);
/*    */     
/* 41 */     MythicMobs.debug(2, "Executing togglelever skill @ " + x + "," + y + "," + z + " duration=" + duration);
/*    */     try {
/* 43 */       Lever button = new Lever(Material.LEVER, block.getData());
/* 44 */       button.setPowered(true);
/* 45 */       BlockRedstoneEvent toggle = new BlockRedstoneEvent(block, 0, 1);
/* 46 */       Bukkit.getPluginManager().callEvent((Event)toggle);
/*    */ 
/*    */       
/* 49 */       LeverUnswitcher BU = new LeverUnswitcher(block);
/* 50 */       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MythicMobs.inst(), (Runnable)BU, duration);
/*    */     }
/* 52 */     catch (Exception e) {
/* 53 */       MythicMobs.error("A pushbutton skill is improperly configured: block is not a button. AbstractSkill=" + skill);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillToggleLever.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */