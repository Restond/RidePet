/*    */ package lumine.xikage.mythicmobs.commands.items;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ public class ExportCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public ExportCommand(Command<MythicMobs> parent) {
/* 13 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 24 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 29 */     return "mythicmobs.command.items.export";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 39 */     return "export";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\items\ExportCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */