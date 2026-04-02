/*    */ package lumine.xikage.mythicmobs.commands.mobs;
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.commands.mobs.InfoCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.mobs.KillTypeCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.mobs.ListActiveCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.mobs.SpawnCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.mobs.StatsCommand;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class MobsCommand extends Command<MythicMobs> {
/*    */   public MobsCommand(Command<MythicMobs> parent) {
/* 15 */     super(parent);
/*    */     
/* 17 */     addSubCommands(new Command[] { (Command)new InfoCommand(this), (Command)new KillAllCommand(this), (Command)new KillTypeCommand(this), (Command)new ListActiveCommand(this), (Command)new ListCommand(this), (Command)new SpawnCommand(this), (Command)new StatsCommand(this) });
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
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 40 */     String[] messages = { ColorString.get("  &7Arguments: &6[] &f= Required&7, &3<> &f= Optional"), "", ColorString.get("&e/mm m &flist &3<filter> &7► &7&oList all loaded Mythic Mobs"), ColorString.get("&e/mm m &finfo &6[type] &7► &7&oShow info about a mob type"), ColorString.get("&e/mm m &fkill &6[type] &7► &7&oKill all mobs of type [type]"), ColorString.get("&e/mm m &fkillall &3<radius> &7► &7&oRemove all active mobs"), ColorString.get("&e/mm m &fspawn &3<-t> &6[type] &3<amount> &3<w,x,y,z> &7► &7&oSpawn mobs"), ColorString.get("&e/mm m &fstats &7► &7&oShows stats about active mobs") };
/*    */     
/* 42 */     CommandHelper.sendCommandMessage(sender, messages);
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 48 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 53 */     return "mythicmobs.command.mobs";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 63 */     return "mobs";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 68 */     return new String[] { "mob", "m" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\mobs\MobsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */