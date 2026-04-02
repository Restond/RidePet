/*    */ package lumine.xikage.mythicmobs.commands;
/*    */ 
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VersionCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public VersionCommand(Command<MythicMobs> parent) {
/* 18 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 24 */     String devBuilds = MythicMobs.inst().getDescription().getVersion().contains("SNAPSHOT") ? "&aYes" : "&aNo";
/* 25 */     String preBuilds = MythicMobs.p() ? "&aYes" : "&cNo";
/* 26 */     String supported = (MythicMobs.inst().getVolatileCodeHandler() instanceof io.lumine.xikage.mythicmobs.volatilecode.VolatileCodeDisabled) ? "&cNo" : "&aYes";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     String[] messages = { ColorString.get("&6Server Version&f: " + Bukkit.getServer().getClass().getPackage().getName()), ColorString.get("&6Plugin Version&f: " + MythicMobs.inst().getVersion()), ColorString.get("&6Plugin Build&f: " + MythicMobs.inst().getBuildNumber()), ColorString.get("&6Is Premium&f: " + preBuilds), ColorString.get("&6Is Dev Build&f: " + devBuilds), ColorString.get("&6Supported Version&f: " + supported) };
/*    */     
/* 36 */     CommandHelper.sendCommandMessage(sender, messages);
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 42 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 47 */     return "mythicmobs.command.version";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 57 */     return "version";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 62 */     return new String[] { "ver", "v" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\VersionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */