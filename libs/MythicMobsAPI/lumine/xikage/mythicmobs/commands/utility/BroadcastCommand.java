/*    */ package lumine.xikage.mythicmobs.commands.utility;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class BroadcastCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public BroadcastCommand(Command<MythicMobs> parent) {
/* 17 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 23 */     if (args.length < 1) {
/* 24 */       return true;
/*    */     }
/*    */     
/* 27 */     String s = "";
/*    */     
/* 29 */     for (int i = 0; i < args.length; i++) {
/* 30 */       s = s.concat(args[i]) + " ";
/*    */     }
/*    */     
/* 33 */     s = SkillString.parseMessageSpecialChars(s);
/* 34 */     s = ChatColor.translateAlternateColorCodes('&', s);
/*    */     
/* 36 */     for (Player p : Bukkit.getServer().getOnlinePlayers()) {
/* 37 */       p.sendMessage(s);
/*    */     }
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 49 */     return "mythicmobs.command.utilities.broadcast";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 59 */     return "broadcast";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 64 */     return new String[] { "b" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\command\\utility\BroadcastCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */