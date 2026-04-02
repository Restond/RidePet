/*    */ package lumine.xikage.mythicmobs.commands.mobs;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ public class CleanCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public CleanCommand(Command<MythicMobs> parent) {
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
/* 29 */     return "mythicmobs.command.mobs.clean";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 39 */     return "clean";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\mobs\CleanCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */