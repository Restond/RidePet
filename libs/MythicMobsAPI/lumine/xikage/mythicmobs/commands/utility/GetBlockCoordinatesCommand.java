/*    */ package lumine.xikage.mythicmobs.commands.utility;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetBlockCoordinatesCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public GetBlockCoordinatesCommand(Command<MythicMobs> parent) {
/* 19 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 25 */     Player p = (Player)sender;
/*    */     
/* 27 */     Location location = p.getTargetBlock((HashSet)null, 64).getLocation();
/* 28 */     if (location == null) {
/* 29 */       CommandHelper.sendError(sender, "You must target a valid block!");
/* 30 */       return true;
/*    */     } 
/*    */     
/* 33 */     CommandHelper.sendSuccess(sender, "Coordinates you are targeting: " + ChatColor.AQUA + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + ChatColor.GREEN + " (x,y,z)");
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 44 */     return "mythicmobs.command.utilities.getblockcoordinates";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 54 */     return "getblockcoordinates";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 59 */     return new String[] { "getblockcoords", "gbc" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\command\\utility\GetBlockCoordinatesCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */