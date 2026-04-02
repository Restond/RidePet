/*    */ package lumine.xikage.mythicmobs.commands.items;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ListCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public ListCommand(Command<MythicMobs> parent) {
/* 18 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 23 */     List<MythicItem> mobs = new ArrayList<>(MythicMobs.inst().getItemManager().getItems());
/* 24 */     Collections.sort(mobs);
/*    */     
/* 26 */     if (args.length > 0) {
/* 27 */       sender.sendMessage(ChatColor.GOLD + "Items found containing " + args[0] + ": ");
/*    */     } else {
/* 29 */       sender.sendMessage(ChatColor.GOLD + "Items Loaded (" + mobs.size() + "): ");
/*    */     } 
/*    */     
/* 32 */     String s = "";
/* 33 */     int i = 0;
/* 34 */     for (MythicItem mm : mobs) {
/* 35 */       if (args.length > 0) {
/* 36 */         if (mm.getInternalName().contains(args[0])) {
/* 37 */           String[] parts = mm.getInternalName().split(args[0]);
/* 38 */           if (parts.length == 2)
/* 39 */             s = s + ChatColor.GREEN + parts[0] + ChatColor.DARK_GREEN + args[0] + ChatColor.GREEN + parts[1] + ChatColor.GRAY + ", "; 
/* 40 */           if (parts.length == 1)
/* 41 */             s = s + ChatColor.GREEN + parts[0] + ChatColor.DARK_GREEN + args[0] + ChatColor.GRAY + ", "; 
/* 42 */           if (parts.length == 0)
/* 43 */             s = s + ChatColor.GOLD + args[0] + ChatColor.GRAY + ", "; 
/*    */         } 
/*    */       } else {
/* 46 */         s = s + ChatColor.GREEN + mm.getInternalName() + ChatColor.GRAY + ", ";
/*    */       } 
/* 48 */       if (++i == 25) {
/* 49 */         i = 0;
/* 50 */         sender.sendMessage(s);
/* 51 */         s = "";
/*    */       } 
/*    */     } 
/* 54 */     sender.sendMessage(s);
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 60 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 65 */     return "mythicmobs.command.items.list";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 70 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 75 */     return "list";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\items\ListCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */