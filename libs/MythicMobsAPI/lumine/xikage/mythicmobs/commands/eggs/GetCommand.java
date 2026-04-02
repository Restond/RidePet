/*    */ package lumine.xikage.mythicmobs.commands.eggs;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.mobs.EggManager;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.util.StringUtil;
/*    */ 
/*    */ public class GetCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public GetCommand(Command<MythicMobs> parent) {
/* 19 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 24 */     Player p = (Player)sender;
/*    */     
/* 26 */     if (args.length < 1) {
/* 27 */       CommandHelper.sendError(sender, "Command Syntax: /mm egg get <mob_name> [amount]");
/* 28 */       return true;
/*    */     } 
/*    */     
/* 31 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(args[0]);
/* 32 */     int amount = 1;
/*    */     
/* 34 */     if (args.length > 1) {
/* 35 */       amount = Integer.valueOf(args[1]).intValue();
/*    */     }
/*    */     
/* 38 */     if (mm == null) {
/* 39 */       CommandHelper.sendError(sender, "No Mythic Mob loaded with the name " + args[0] + ".");
/* 40 */       return true;
/*    */     } 
/*    */     
/* 43 */     if (EggManager.giveMythicEgg(mm, p, amount) == true) {
/* 44 */       CommandHelper.sendSuccess(sender, "Got " + ChatColor.AQUA + amount + ChatColor.GREEN + " Mythic Eggs" + ChatColor.GREEN + "!");
/*    */     } else {
/* 46 */       CommandHelper.sendError(sender, "An error occured, could not get mob egg.");
/*    */     } 
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 53 */     if (args.length == 1) {
/* 54 */       return (List<String>)StringUtil.copyPartialMatches(args[0], ((MythicMobs)getPlugin()).getMobManager().getMobNames(), new ArrayList());
/*    */     }
/* 56 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 61 */     return "mythicmobs.command.eggs.get";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 71 */     return "get";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\eggs\GetCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */