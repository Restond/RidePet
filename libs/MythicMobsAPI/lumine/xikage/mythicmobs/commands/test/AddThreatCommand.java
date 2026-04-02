/*    */ package lumine.xikage.mythicmobs.commands.test;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AddThreatCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public AddThreatCommand(Command<MythicMobs> parent) {
/* 19 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 25 */     Player player = (Player)sender;
/* 26 */     LivingEntity target = MythicUtil.getTargetedEntity(player);
/*    */     
/* 28 */     double amount = 100.0D;
/*    */     
/* 30 */     if (args.length > 0) {
/*    */       try {
/* 32 */         amount = Double.valueOf(args[0]).doubleValue();
/* 33 */       } catch (Exception ex) {
/* 34 */         CommandHelper.sendError(sender, "Amount must be a number.");
/*    */       } 
/*    */     }
/*    */     
/* 38 */     if (MythicMobs.inst().getAPIHelper().addThreat((Entity)target, (LivingEntity)player, amount)) {
/* 39 */       CommandHelper.sendSuccess(sender, "Added &a" + amount + " &bthreat to target mob.");
/*    */     } else {
/* 41 */       CommandHelper.sendError(sender, "Failed to add threat to target mob.");
/*    */     } 
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
/* 53 */     return "mythicmobs.command.test.addthreat";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 63 */     return "addthreat";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\test\AddThreatCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */