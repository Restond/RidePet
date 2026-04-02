/*    */ package lumine.xikage.mythicmobs.commands;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class SignalCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public SignalCommand(Command<MythicMobs> parent) {
/* 20 */     super(parent);
/*    */   }
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/*    */     UUID uuid;
/*    */     String signal;
/*    */     AbstractPlayer abstractPlayer;
/*    */     try {
/* 28 */       uuid = UUID.fromString(args[0]);
/* 29 */       signal = args[1];
/* 30 */     } catch (Exception ex) {
/* 31 */       sender.sendMessage(ChatColor.RED + "Signal skill must be in format: /mm signal <uuid> <signal>");
/* 32 */       return true;
/*    */     } 
/*    */     
/* 35 */     if (!MythicMobs.inst().getMobManager().isActiveMob(uuid)) {
/* 36 */       sender.sendMessage(ChatColor.RED + "Failed to send Signal: UUID does not correspond to a Mythic Mob.");
/* 37 */       return true;
/*    */     } 
/* 39 */     ActiveMob am = MythicMobs.inst().getMobManager().getActiveMob(uuid).get();
/*    */     
/* 41 */     AbstractEntity trigger = null;
/* 42 */     if (sender instanceof Player) {
/* 43 */       if (!sender.hasPermission("mythicmobs.signal")) {
/* 44 */         sender.sendMessage(ChatColor.RED + "Failed to send Signal: You do not have permission to use that command!");
/* 45 */         return true;
/*    */       } 
/* 47 */       abstractPlayer = BukkitAdapter.adapt((Player)sender);
/*    */     } 
/*    */     
/* 50 */     am.signalMob((AbstractEntity)abstractPlayer, signal);
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 56 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 61 */     return "mythicmobs.command.signal";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 66 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 71 */     return "signal";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 76 */     return new String[] { "si" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\SignalCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */