/*    */ package lumine.xikage.mythicmobs.commands.eggs;
/*    */ 
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.commands.eggs.GetCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.eggs.GiveCommand;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class EggsCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public EggsCommand(Command<MythicMobs> parent) {
/* 16 */     super(parent);
/*    */     
/* 18 */     addSubCommands(new Command[] { (Command)new GetCommand(this), (Command)new GiveCommand(this) });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 30 */     String[] messages = { ColorString.get("  &7Arguments: &6[] &f= Required&7, &3<> &f= Optional"), "", ColorString.get("/mm egg get &6[mob] &3<amount> &7► &7&oGives you mob eggs"), ColorString.get("/mm egg give &6[player] &6[mob] &3<amount> &7► &7&oGives player some mob eggs") };
/*    */     
/* 32 */     CommandHelper.sendCommandMessage(sender, messages);
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 38 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 43 */     return "mythicmobs.command.eggs";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 53 */     return "egg";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 58 */     return new String[] { "eggs", "e" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\eggs\EggsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */