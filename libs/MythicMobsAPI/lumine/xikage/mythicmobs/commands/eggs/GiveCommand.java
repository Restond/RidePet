/*     */ package lumine.xikage.mythicmobs.commands.eggs;
/*     */ 
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*     */ import io.lumine.xikage.mythicmobs.mobs.EggManager;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.util.StringUtil;
/*     */ 
/*     */ public class GiveCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public GiveCommand(Command<MythicMobs> parent) {
/*  21 */     super(parent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  27 */     boolean optSilent = false;
/*  28 */     if (args != null && args.length > 1 && 
/*  29 */       args[0].startsWith("-")) {
/*  30 */       if (args[0].contains("s")) {
/*  31 */         optSilent = true;
/*     */       }
/*  33 */       args = Arrays.<String>copyOfRange(args, 1, args.length);
/*     */     } 
/*     */ 
/*     */     
/*  37 */     if (args.length < 2) {
/*  38 */       CommandHelper.sendError(sender, "Command Syntax: /mm egg give <name> <mob_name> [amount]");
/*  39 */       return true;
/*     */     } 
/*     */     
/*  42 */     Player target = Bukkit.getPlayer(args[0]);
/*  43 */     MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(args[1]);
/*  44 */     int amount = 1;
/*     */     
/*  46 */     if (args.length > 2) {
/*  47 */       amount = Integer.valueOf(args[2]).intValue();
/*     */     }
/*     */     
/*  50 */     if (target == null) {
/*  51 */       CommandHelper.sendError(sender, "That player is not online");
/*  52 */       return true;
/*     */     } 
/*     */     
/*  55 */     if (mm == null) {
/*  56 */       if (!optSilent) sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Mob loaded with the name " + args[2] + "."); 
/*  57 */       return true;
/*     */     } 
/*     */     
/*  60 */     if (EggManager.giveMythicEgg(mm, target, amount) == true)
/*  61 */     { if (!optSilent) sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Gave " + ChatColor.AQUA + amount + ChatColor.GREEN + " Mythic Eggs to " + ChatColor.AQUA + target.getName() + ChatColor.GREEN + "!");
/*     */        }
/*  63 */     else if (!optSilent) { sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "An error occured, could not give mob egg."); }
/*     */     
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/*  70 */     if (args != null && args.length > 1 && 
/*  71 */       args[0].startsWith("-")) {
/*  72 */       args = Arrays.<String>copyOfRange(args, 1, args.length);
/*     */     }
/*     */     
/*  75 */     if (args.length == 1) {
/*  76 */       List<String> players = new ArrayList<>();
/*     */       
/*  78 */       for (Player p : Bukkit.getOnlinePlayers()) {
/*  79 */         players.add(p.getName());
/*     */       }
/*  81 */       return (List<String>)StringUtil.copyPartialMatches(args[0], players, new ArrayList());
/*     */     } 
/*  83 */     if (args.length == 2) {
/*  84 */       return (List<String>)StringUtil.copyPartialMatches(args[1], ((MythicMobs)getPlugin()).getMobManager().getMobNames(), new ArrayList());
/*     */     }
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/*  91 */     return "mythicmobs.command.eggs.give";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 101 */     return "give";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 106 */     return new String[] { "g", "gi" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\eggs\GiveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */