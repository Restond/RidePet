/*    */ package lumine.xikage.mythicmobs.commands.test;
/*    */ 
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ public class SetHealthCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public SetHealthCommand(Command<MythicMobs> parent) {
/* 15 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 21 */     if (args.length < 2) {
/* 22 */       return true;
/*    */     }
/*    */     
/* 25 */     MythicMob mm = ((MythicMobs)getPlugin()).getMobManager().getMythicMob(args[0]);
/*    */     
/* 27 */     if (mm == null) {
/* 28 */       sender.sendMessage(ColorString.get("&cMob not found"));
/* 29 */       return true;
/*    */     } 
/*    */     
/* 32 */     double health = Double.valueOf(args[1]).doubleValue();
/*    */     
/* 34 */     mm.setBaseHealth(health);
/* 35 */     sender.sendMessage(ColorString.get("&aHealth set to " + health + " on mob type " + mm.getInternalName()));
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 41 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 46 */     return "mythicmobs.command.test";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 56 */     return "sethealth";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\test\SetHealthCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */