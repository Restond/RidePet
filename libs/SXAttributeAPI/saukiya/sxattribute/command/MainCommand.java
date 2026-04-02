/*     */ package saukiya.sxattribute.command;
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.command.CommandList;
/*     */ import github.saukiya.sxattribute.command.SenderType;
/*     */ import github.saukiya.sxattribute.command.SubCommand;
/*     */ import github.saukiya.sxattribute.command.sub.ConditionListCommand;
/*     */ import github.saukiya.sxattribute.command.sub.GiveCommand;
/*     */ import github.saukiya.sxattribute.command.sub.NBTCommand;
/*     */ import github.saukiya.sxattribute.command.sub.ReloadCommand;
/*     */ import github.saukiya.sxattribute.command.sub.RepairCommand;
/*     */ import github.saukiya.sxattribute.command.sub.SaveCommand;
/*     */ import github.saukiya.sxattribute.command.sub.SellCommand;
/*     */ import github.saukiya.sxattribute.command.sub.StatsCommand;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.TabCompleter;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class MainCommand implements CommandExecutor, TabCompleter {
/*  25 */   private final CommandList subCommands = SubCommand.subCommands;
/*     */   
/*     */   private final SXAttribute plugin;
/*     */   
/*     */   public MainCommand(SXAttribute plugin) {
/*  30 */     this.plugin = plugin;
/*  31 */     (new StatsCommand()).registerCommand((JavaPlugin)plugin);
/*  32 */     (new RepairCommand()).registerCommand((JavaPlugin)plugin);
/*  33 */     (new SellCommand()).registerCommand((JavaPlugin)plugin);
/*  34 */     (new GiveCommand()).registerCommand((JavaPlugin)plugin);
/*  35 */     (new SaveCommand()).registerCommand((JavaPlugin)plugin);
/*  36 */     (new NBTCommand()).registerCommand((JavaPlugin)plugin);
/*  37 */     (new DisplaySlotCommand()).registerCommand((JavaPlugin)plugin);
/*  38 */     (new AttributeListCommand()).registerCommand((JavaPlugin)plugin);
/*  39 */     (new ConditionListCommand()).registerCommand((JavaPlugin)plugin);
/*  40 */     (new ReloadCommand()).registerCommand((JavaPlugin)plugin);
/*     */   }
/*     */   
/*     */   public void setUp(String command) {
/*  44 */     this.plugin.getCommand(command).setExecutor(this);
/*  45 */     this.plugin.getCommand(command).setTabCompleter(this);
/*  46 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Load §c" + this.subCommands.size() + "§r Commands");
/*     */   }
/*     */   
/*     */   public void onCommandEnable() {
/*  50 */     this.subCommands.toCollection().forEach(SubCommand::onEnable);
/*     */   }
/*     */   
/*     */   public void onCommandDisable() {
/*  54 */     this.subCommands.toCollection().forEach(SubCommand::onDisable);
/*     */   }
/*     */ 
/*     */   
/*     */   private SenderType getType(CommandSender sender) {
/*  59 */     if (sender instanceof org.bukkit.entity.Player) {
/*  60 */       return SenderType.PLAYER;
/*     */     }
/*  62 */     return SenderType.CONSOLE;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command arg1, String label, String[] args) {
/*  67 */     SenderType type = getType(sender);
/*  68 */     if (args.length == 0) {
/*  69 */       sender.sendMessage("§0-§8 --§7 ---§c ----§4 -----§b " + SXAttribute.getPluginName() + "§4 -----§c ----§7 ---§8 --§0 - §0Author Saukiya");
/*  70 */       String color = "§7";
/*  71 */       for (SubCommand sub : this.subCommands.toCollection()) {
/*  72 */         if (sub.isUse(sender, type) && !sub.hide()) {
/*  73 */           color = color.equals("§7") ? "" : "§7";
/*  74 */           sub.sendIntroduction(sender, color, label);
/*     */         } 
/*     */       } 
/*  77 */       return true;
/*     */     } 
/*  79 */     for (SubCommand sub : this.subCommands.toCollection()) {
/*  80 */       if (sub.cmd().equalsIgnoreCase(args[0])) {
/*  81 */         if (!sub.isUse(sender, type)) {
/*  82 */           sender.sendMessage(Message.getMsg(Message.ADMIN__NO_PERMISSION_CMD, new Object[0]));
/*     */         } else {
/*  84 */           sub.onCommand(this.plugin, sender, args);
/*     */         } 
/*  86 */         return true;
/*     */       } 
/*     */     } 
/*  89 */     sender.sendMessage(Message.getMsg(Message.ADMIN__NO_CMD, new Object[] { args[0] }));
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
/*  95 */     SenderType type = getType(sender);
/*  96 */     if (args.length == 1) {
/*  97 */       List<String> list = new ArrayList<>();
/*  98 */       for (SubCommand subCommand : this.subCommands.toCollection()) {
/*  99 */         if (subCommand.isUse(sender, type) && !subCommand.hide() && subCommand.cmd().contains(args[0])) {
/* 100 */           String cmd = subCommand.cmd();
/* 101 */           list.add(cmd);
/*     */         } 
/*     */       } 
/* 104 */       return list;
/*     */     } 
/* 106 */     for (SubCommand subCommand : this.subCommands.toCollection()) {
/* 107 */       if (subCommand.cmd().equalsIgnoreCase(args[0])) {
/* 108 */         return Optional.<SubCommand>of(subCommand).filter(sub -> sub.isUse(sender, type)).map(sub -> sub.onTabComplete(this.plugin, sender, args)).orElse(null);
/*     */       }
/*     */     } 
/* 111 */     return Optional.empty().filter(sub -> sub.isUse(sender, type)).map(sub -> sub.onTabComplete(this.plugin, sender, args)).orElse(null);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\MainCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */