/*    */ package lumine.xikage.mythicmobs.commands.items;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.util.StringUtil;
/*    */ 
/*    */ 
/*    */ public class InfoCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public InfoCommand(Command<MythicMobs> parent) {
/* 20 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 25 */     Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(args[0]);
/* 26 */     if (maybeItem.isPresent()) {
/* 27 */       MythicItem mi = maybeItem.get();
/* 28 */       sender.sendMessage(ChatColor.GOLD + "Stats for " + ChatColor.BOLD + ChatColor.GRAY + mi.getInternalName() + ":");
/* 29 */       sender.sendMessage(ChatColor.GOLD + "displayName: " + ChatColor.GRAY + mi.getDisplayName() + ChatColor.GRAY + " / " + ChatColor.translateAlternateColorCodes('&', mi.getDisplayName()));
/* 30 */       sender.sendMessage(ChatColor.GOLD + "Id: " + ChatColor.GRAY + mi.getMaterialName() + ChatColor.GRAY + " / " + ChatColor.GRAY + Material.getMaterial(mi.getMaterialName()));
/* 31 */       sender.sendMessage(ChatColor.GOLD + "Data: " + ChatColor.GRAY + mi.getMaterialData());
/* 32 */       sender.sendMessage(ChatColor.GOLD + "Amount: " + ChatColor.GRAY + mi.getAmount());
/* 33 */       sender.sendMessage(ChatColor.GOLD + "Located in File: " + ChatColor.GRAY + mi.getFile());
/*    */     } else {
/* 35 */       CommandHelper.sendError(sender, "No Mythic Item loaded with the name " + args[0] + ".");
/*    */     } 
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 42 */     if (args.length == 1) {
/* 43 */       return (List<String>)StringUtil.copyPartialMatches(args[0], ((MythicMobs)getPlugin()).getItemManager().getItemNames(), new ArrayList());
/*    */     }
/* 45 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 50 */     return "mythicmobs.command.items.info";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 60 */     return "info";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\items\InfoCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */