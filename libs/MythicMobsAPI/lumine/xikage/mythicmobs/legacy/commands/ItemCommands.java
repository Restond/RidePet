/*     */ package lumine.xikage.mythicmobs.legacy.commands;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemCommands
/*     */ {
/*     */   public static void parseCommands(CommandSender sender, Command cmd, String commandlabel, String[] args) {
/*  20 */     if (args.length == 1) {
/*     */       
/*  22 */       sender.sendMessage(MythicMobs.menu_header);
/*  23 */       sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Command argumnts in <>'s are optional");
/*  24 */       sender.sendMessage(ChatColor.GOLD + "/mm items list <search filter>" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - List all loaded Mythic Items");
/*  25 */       sender.sendMessage(ChatColor.GOLD + "/mm items info [ItemName]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Show info about the given Mythic Item");
/*  26 */       sender.sendMessage(ChatColor.GOLD + "/mm items get [ItemName]" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Give yourself a Mythic Item");
/*  27 */       sender.sendMessage(ChatColor.GOLD + "/mm items give [Player] [ItemName] <amount>" + ChatColor.GREEN + "" + ChatColor.ITALIC + " - Give a player a Mythic Item");
/*     */     }
/*  29 */     else if (args.length == 2) {
/*     */       
/*  31 */       if (args[1].equalsIgnoreCase("list"))
/*     */       {
/*  33 */         String s = ChatColor.GOLD + "Items Loaded: ";
/*  34 */         for (MythicItem mi : MythicMobs.inst().getItemManager().getItems())
/*     */         {
/*  36 */           s = s + ChatColor.RED + mi.getInternalName() + ChatColor.GRAY + ", ";
/*     */         }
/*  38 */         sender.sendMessage(s);
/*     */       }
/*     */     
/*  41 */     } else if (args.length == 3) {
/*     */       
/*  43 */       if (args[1].equalsIgnoreCase("list")) {
/*     */         
/*  45 */         String s = ChatColor.GOLD + "Mythic Items found containing " + args[2] + ": ";
/*  46 */         for (MythicItem mi : MythicMobs.inst().getItemManager().getItems()) {
/*     */           
/*  48 */           if (mi.getInternalName().contains(args[2])) {
/*     */             
/*  50 */             String[] parts = mi.getInternalName().split(args[2]);
/*  51 */             if (parts.length == 2)
/*  52 */               s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.RED + parts[1] + ChatColor.GRAY + ", "; 
/*  53 */             if (parts.length == 1)
/*  54 */               s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/*  55 */             if (parts.length == 0)
/*  56 */               s = s + ChatColor.DARK_RED + args[2] + ChatColor.GRAY + ", "; 
/*     */           } 
/*     */         } 
/*  59 */         sender.sendMessage(s);
/*     */       }
/*  61 */       else if (args[1].equalsIgnoreCase("info")) {
/*     */         
/*  63 */         Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(args[2]);
/*  64 */         if (maybeItem.isPresent()) {
/*  65 */           MythicItem mi = maybeItem.get();
/*  66 */           sender.sendMessage(ChatColor.GOLD + "Stats for " + ChatColor.BOLD + ChatColor.GRAY + mi.getInternalName() + ":");
/*  67 */           sender.sendMessage(ChatColor.GOLD + "displayName: " + ChatColor.GRAY + mi.getDisplayName() + ChatColor.GRAY + " / " + ChatColor.translateAlternateColorCodes('&', mi.getDisplayName()));
/*  68 */           sender.sendMessage(ChatColor.GOLD + "Id: " + ChatColor.GRAY + mi.getMaterialName() + ChatColor.GRAY + " / " + ChatColor.GRAY + Material.getMaterial(mi.getMaterialName()));
/*  69 */           sender.sendMessage(ChatColor.GOLD + "Data: " + ChatColor.GRAY + mi.getMaterialData());
/*  70 */           sender.sendMessage(ChatColor.GOLD + "Amount: " + ChatColor.GRAY + mi.getAmount());
/*  71 */           sender.sendMessage(ChatColor.GOLD + "Located in File: " + ChatColor.GRAY + mi.getFile());
/*     */         }
/*     */         else {
/*     */           
/*  75 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Item loaded with the name " + args[2] + ".");
/*     */         }
/*     */       
/*  78 */       } else if (args[1].equalsIgnoreCase("get")) {
/*     */         
/*  80 */         if (sender instanceof Player) {
/*     */           
/*  82 */           Player p = (Player)sender;
/*     */           
/*  84 */           Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(args[2]);
/*  85 */           if (maybeItem.isPresent()) {
/*  86 */             MythicItem mi = maybeItem.get();
/*     */             
/*  88 */             sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Item " + ChatColor.AQUA + args[2] + ChatColor.GREEN + " was put in your inventory!");
/*     */           }
/*     */           else {
/*     */             
/*  92 */             sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Item loaded with the name " + args[2] + ".");
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/*  97 */           sender.sendMessage(ChatColor.RED + "This command can't be cast from the console! :(");
/*     */         }
/*     */       
/*     */       } 
/* 101 */     } else if (args.length >= 4 && 
/* 102 */       args[1].equalsIgnoreCase("give")) {
/* 103 */       Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(args[3]);
/* 104 */       if (maybeItem.isPresent()) {
/* 105 */         MythicItem mi = maybeItem.get();
/* 106 */         if (Bukkit.getPlayer(args[2]) != null) {
/* 107 */           Player p = Bukkit.getPlayer(args[2]);
/* 108 */           if (p.isOnline()) {
/* 109 */             int amount = 1;
/*     */             
/* 111 */             if (args.length > 4) {
/* 112 */               amount = Integer.valueOf(args[4]).intValue();
/*     */             }
/*     */             
/* 115 */             for (int i = 0; i < amount; i++);
/*     */ 
/*     */             
/* 118 */             sender.sendMessage(ChatColor.GREEN + "Item(s) Given");
/*     */           } else {
/*     */             
/* 121 */             sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No player found online with the name " + args[2] + "!");
/*     */           } 
/*     */         } else {
/* 124 */           sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No player found online with the name " + args[2] + "!");
/*     */         } 
/*     */       } else {
/* 127 */         sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Item loaded with the name " + args[3] + ".");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\commands\ItemCommands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */