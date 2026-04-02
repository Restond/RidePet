/*    */ package lumine.xikage.mythicmobs.commands;
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.commands.InfoCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.ReloadCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.SaveCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.SignalCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.VersionCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.debug.DebugCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.eggs.EggsCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.mobs.MobsCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.skills.SkillsCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.spawners.SpawnersCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.test.TestCommand;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class BaseCommand extends Command<MythicMobs> {
/*    */   public BaseCommand(MythicMobs plugin) {
/* 23 */     super((Plugin)plugin);
/*    */     
/* 25 */     addSubCommands(new Command[] { (Command)new InfoCommand(this), (Command)new ReloadCommand(this), (Command)new SaveCommand(this), (Command)new SignalCommand(this), (Command)new SpawnersCommand(this), (Command)new SkillsCommand(this), (Command)new EggsCommand(this), (Command)new MobsCommand(this), (Command)new ItemsCommand(this), (Command)new UtilitiesCommand(this), (Command)new DebugCommand(this), (Command)new TestCommand(this), (Command)new VersionCommand(this) });
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 53 */     String[] messages = { ChatColor.GOLD + "/mm mobs" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists mob-related commands.", ChatColor.GOLD + "/mm eggs" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists egg-related commands.", ChatColor.GOLD + "/mm items" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists item-related commands.", ChatColor.GOLD + "/mm skills" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists skill-related commands.", ChatColor.GOLD + "/mm spawners" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists spawner-related commands.", ChatColor.GOLD + "/mm test" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists special commands used for testing.", ChatColor.GOLD + "/mm utility" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Lists some helpful utility commands.", ColorString.get("&e/mm signal &6[uuid] [signal] &a- " + ChatColor.ITALIC + "Sends a signal to a specific mob."), ChatColor.YELLOW + "/mm reload" + ChatColor.GREEN + " - " + ChatColor.ITALIC + "Reloads all configuration files and mobs.", ColorString.get("&e/mm version &a- " + ChatColor.ITALIC + "Plugin version information") };
/*    */     
/* 55 */     CommandHelper.sendCommandMessage(sender, messages);
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 61 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 66 */     return "mythicmobs.command.base";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 71 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 76 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\BaseCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */