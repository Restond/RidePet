/*    */ package lumine.xikage.mythicmobs.commands.debug;
/*    */ 
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.commands.debug.TimingsCommand;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DebugCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public DebugCommand(Command<MythicMobs> parent) {
/* 19 */     super(parent);
/*    */     
/* 21 */     addSubCommands(new Command[] { (Command)new TimingsCommand(this) });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 26 */     if (args.length == 0) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 35 */       String[] messages = { ColorString.get("  &7Arguments: &6[] &f= Required&7, &3<> &f= Optional"), "", ColorString.get("&e/mm d &3[level] &7► &7&oSets debug reporting. Can be between 0 and 4 (0=off,4=too much info)."), ColorString.get("&e/mm d &fmode &6[true/false] &7► &7&oSets debug mode on or off. See plugin manual for details)."), ColorString.get("&e/mm d &ftimings &7► &7&oShow basic timing information (timings must be on first)."), ColorString.get("&e/mm d &ftimings &3[on/off] &7► &7&oTurns timings on/off."), ColorString.get("&e/mm d &ftimings skills &7► &7&oShows skill-specific timings in the console."), ColorString.get("&e/mm d &ftimings spawners &7► &7&oShows spawner-specific timings in the console.") };
/*    */       
/* 37 */       CommandHelper.sendCommandMessage(sender, messages);
/* 38 */       return true;
/*    */     } 
/*    */     
/*    */     try {
/* 42 */       ConfigManager.debugLevel = Integer.parseInt(args[0]);
/* 43 */       sender.sendMessage(ChatColor.GOLD + "[MythicMobs]" + ChatColor.GREEN + " Debug level has been set to " + args[0]);
/* 44 */     } catch (Exception e) {
/* 45 */       CommandHelper.sendError(sender, "Debug Level must be an integer (1-5).");
/*    */     } 
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 57 */     return "mythicmobs.command.debug";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 62 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 67 */     return "debug";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 72 */     return new String[] { "d" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\debug\DebugCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */