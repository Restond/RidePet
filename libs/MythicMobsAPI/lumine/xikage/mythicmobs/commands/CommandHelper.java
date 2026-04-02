/*    */ package lumine.xikage.mythicmobs.commands;
/*    */ 
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ public class CommandHelper
/*    */ {
/*    */   public static void sendHeader(CommandSender sender) {
/* 10 */     sender.sendMessage(ColorString.get("&e&m----------&6&m=====&6 &lMythicMobs&6 &n=====&e&n----------"));
/*    */   }
/*    */   
/*    */   public static void sendSuccess(CommandSender sender, String message) {
/* 14 */     sender.sendMessage(ColorString.get("&6[MythicMobs] &a" + message));
/*    */   }
/*    */   
/*    */   public static void sendError(CommandSender sender, String message) {
/* 18 */     sender.sendMessage(ColorString.get("&6[MythicMobs] &c" + message));
/*    */   }
/*    */   
/*    */   public static void sendCommandMessage(CommandSender player, String[] args) {
/* 22 */     player.sendMessage(ColorString.get("&e&m----------&6&m=====&6 &lMythicMobs&6 &m=====&e&m----------"));
/* 23 */     player.sendMessage(" ");
/* 24 */     player.sendMessage(args);
/* 25 */     player.sendMessage(" ");
/* 26 */     player.sendMessage(ColorString.get("&e&m-------------&b www.mythicmobs.net &e&m-------------"));
/*    */   }
/*    */   
/*    */   public static void sendCommandArgumentMessage(CommandSender player, String[] args) {
/* 30 */     player.sendMessage(ColorString.get("&e&n----------&6&n=====&6 &lMythicMobs&6 &n=====&e&n----------"));
/* 31 */     player.sendMessage(" ");
/* 32 */     player.sendMessage(args);
/* 33 */     player.sendMessage(" ");
/* 34 */     player.sendMessage(ColorString.get("&e&n-------------&b www.mythicmobs.net &e&n-------------"));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\CommandHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */