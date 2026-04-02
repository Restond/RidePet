/*    */ package lumine.xikage.mythicmobs.commands.test;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.test.AddThreatCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.test.CastCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.test.ReduceThreatCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.test.SetHealthCommand;
/*    */ import io.lumine.xikage.mythicmobs.commands.test.TauntCommand;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class TestCommand extends Command<MythicMobs> {
/*    */   public TestCommand(Command<MythicMobs> parent) {
/* 13 */     super(parent);
/*    */     
/* 15 */     addSubCommands(new Command[] { (Command)new AddThreatCommand(this), (Command)new CastCommand(this), (Command)new SetHealthCommand(this), (Command)new ReduceThreatCommand(this), (Command)new TauntCommand(this) });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 21 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 26 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 31 */     return "mythicmobs.command.test";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 41 */     return "test";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 46 */     return new String[] { "t" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\test\TestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */