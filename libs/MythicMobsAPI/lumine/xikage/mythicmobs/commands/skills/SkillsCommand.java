/*    */ package lumine.xikage.mythicmobs.commands.skills;
/*    */ 
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.commands.skills.InfoCommand;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class SkillsCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public SkillsCommand(Command<MythicMobs> parent) {
/* 15 */     super(parent);
/*    */     
/* 17 */     addSubCommands(new Command[] { (Command)new InfoCommand(this) });
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
/* 29 */     String[] messages = { ColorString.get("  &7Arguments: &6[] &f= Required&7, &3<> &f= Optional"), "", ColorString.get("&e/mm m &finfo &6[name] &7► &7&oShow info about a skill") };
/*    */     
/* 31 */     CommandHelper.sendCommandMessage(sender, messages);
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 37 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 42 */     return "mythicmobs.command.skills";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 52 */     return "skills";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 57 */     return new String[] { "skill", "sk" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\skills\SkillsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */