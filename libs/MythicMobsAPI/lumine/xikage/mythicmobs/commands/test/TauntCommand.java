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
/*    */ public class TauntCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public TauntCommand(Command<MythicMobs> parent) {
/* 19 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 25 */     Player player = (Player)sender;
/* 26 */     LivingEntity target = MythicUtil.getTargetedEntity(player);
/*    */     
/* 28 */     if (MythicMobs.inst().getAPIHelper().taunt((Entity)target, (LivingEntity)player)) {
/* 29 */       CommandHelper.sendSuccess(sender, "Taunted target mob.");
/*    */     } else {
/* 31 */       CommandHelper.sendError(sender, "Failed to taunt target mob.");
/*    */     } 
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
/* 43 */     return "mythicmobs.command.test.taunt";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 53 */     return "taunt";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\test\TauntCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */