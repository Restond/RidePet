/*     */ package lumine.utils.commands;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.TabExecutor;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.util.StringUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Command<T extends Plugin>
/*     */   implements TabExecutor
/*     */ {
/*     */   protected final T plugin;
/*  20 */   private final Map<String, io.lumine.utils.commands.Command<T>> subCommands = new HashMap<>();
/*  21 */   private final Map<String, io.lumine.utils.commands.Command<T>> subCommandAliases = new HashMap<>();
/*     */   
/*     */   public Command(io.lumine.utils.commands.Command<T> parent) {
/*  24 */     this(parent.getPlugin());
/*     */   }
/*     */   
/*     */   public Command(T plugin) {
/*  28 */     this.plugin = plugin;
/*     */   }
/*     */   
/*     */   @SafeVarargs
/*     */   public final void addSubCommands(io.lumine.utils.commands.Command<T>... commands) {
/*  33 */     for (io.lumine.utils.commands.Command<T> command : commands) {
/*  34 */       this.subCommands.put(command.getName(), command);
/*     */       
/*  36 */       for (String alias : command.getAliases()) {
/*  37 */         this.subCommandAliases.put(alias, command);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
/*  47 */     if (getPermissionNode() != null && !sender.hasPermission(getPermissionNode()) && !sender.hasPermission("mythicmobs.admin")) {
/*  48 */       sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
/*  49 */       return true;
/*     */     } 
/*     */     
/*  52 */     if (!isConsoleFriendly() && !(sender instanceof org.bukkit.entity.Player)) {
/*  53 */       sender.sendMessage(ChatColor.RED + "Only players can do this!");
/*  54 */       return true;
/*     */     } 
/*     */     
/*  57 */     if (args.length > 0 && this.subCommands.get(args[0].toLowerCase()) != null) {
/*  58 */       io.lumine.utils.commands.Command<T> sub = this.subCommands.get(args[0].toLowerCase());
/*     */       
/*  60 */       return sub.onCommand(sender, cmd, label, Arrays.<String>copyOfRange(args, 1, args.length));
/*     */     } 
/*     */     
/*  63 */     if (args.length > 0 && this.subCommandAliases.get(args[0].toLowerCase()) != null) {
/*  64 */       io.lumine.utils.commands.Command<T> sub = this.subCommandAliases.get(args[0].toLowerCase());
/*     */       
/*  66 */       return sub.onCommand(sender, cmd, label, Arrays.<String>copyOfRange(args, 1, args.length));
/*     */     } 
/*     */     
/*  69 */     return onCommand(sender, args);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
/*  74 */     if (getPermissionNode() != null && !sender.hasPermission(getPermissionNode())) {
/*  75 */       return null;
/*     */     }
/*     */     
/*  78 */     if (args.length > 1 && this.subCommands.get(args[0].toLowerCase()) != null) {
/*  79 */       io.lumine.utils.commands.Command<T> sub = this.subCommands.get(args[0].toLowerCase());
/*  80 */       return sub.onTabComplete(sender, cmd, label, Arrays.<String>copyOfRange(args, 1, args.length));
/*     */     } 
/*     */     
/*  83 */     if (args.length > 1 && this.subCommandAliases.get(args[0].toLowerCase()) != null) {
/*  84 */       io.lumine.utils.commands.Command<T> sub = this.subCommandAliases.get(args[0].toLowerCase());
/*  85 */       return sub.onTabComplete(sender, cmd, label, Arrays.<String>copyOfRange(args, 1, args.length));
/*     */     } 
/*     */     
/*  88 */     List<String> result = onTabComplete(sender, args);
/*     */     
/*  90 */     if (result == null && args.length == 1) {
/*  91 */       result = new ArrayList<>();
/*  92 */       StringUtil.copyPartialMatches(args[0], this.subCommands.keySet(), result);
/*     */     } 
/*  94 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean onCommand(CommandSender paramCommandSender, String[] paramArrayOfString);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract List<String> onTabComplete(CommandSender paramCommandSender, String[] paramArrayOfString);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getPermissionNode();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean isConsoleFriendly();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 134 */     return new String[0];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getName();
/*     */ 
/*     */   
/*     */   protected T getPlugin() {
/* 143 */     return this.plugin;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\commands\Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */