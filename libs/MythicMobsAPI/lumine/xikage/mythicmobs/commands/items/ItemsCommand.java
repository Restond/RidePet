/*    */ package lumine.xikage.mythicmobs.commands.items;
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.commands.items.EnchantCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.items.ExportCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.items.GiveCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.items.ImportCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.items.InfoCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.items.ListCommand;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class ItemsCommand extends Command<MythicMobs> {
/*    */   public ItemsCommand(Command<MythicMobs> parent) {
/* 16 */     super(parent);
/*    */     
/* 18 */     addSubCommands(new Command[] { (Command)new ExportCommand(this), (Command)new GetCommand(this), (Command)new GiveCommand(this), (Command)new InfoCommand(this), (Command)new ListCommand(this), (Command)new ImportCommand(this), (Command)new EnchantCommand(this) });
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
/* 39 */     String[] messages = { ColorString.get("  &7Arguments: &6[] &f= Required&7, &3<> &f= Optional"), "", ColorString.get("&e/mm i &flist &3<filter> &7► &7&oList all loaded items"), ColorString.get("&e/mm i &finfo &6[name] &7► &7&oShow info about an item"), ColorString.get("&e/mm i &fget &6[name] &7► &7&oGive yourself an item"), ColorString.get("&e/mm i &fgive &3<-s> &6[player] &6[name] &3<amount> &7► &7&oGive a player an item"), ColorString.get("&e/mm i &fenchant &6[name] &6<level> &7► &7&oEnchant your held item"), ColorString.get("&e/mm i &fimport &6[name] &3<fileName> &7► &7&oImport your held item") };
/*    */     
/* 41 */     CommandHelper.sendCommandMessage(sender, messages);
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 47 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 52 */     return "mythicmobs.command.items";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 62 */     return "items";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 67 */     return new String[] { "i" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\items\ItemsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */