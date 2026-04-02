/*     */ package saukiya.sxattribute.command;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.command.CommandList;
/*     */ import github.saukiya.sxattribute.command.SenderType;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SubCommand
/*     */ {
/*  22 */   static final CommandList subCommands = new CommandList(); private JavaPlugin plugin;
/*     */   public JavaPlugin getPlugin() {
/*  24 */     return this.plugin;
/*     */   }
/*     */   private String cmd;
/*  27 */   private String arg = "";
/*     */   
/*     */   private boolean hide = false;
/*     */   
/*  31 */   private SenderType[] senderTypes = new SenderType[] { SenderType.ALL };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubCommand(String cmd, String arg, Boolean hide, SenderType... senderTypes) {
/*  40 */     this.cmd = cmd;
/*  41 */     this.arg = arg;
/*  42 */     this.hide = hide.booleanValue();
/*  43 */     this.senderTypes = senderTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubCommand(String cmd, String arg, SenderType... senderTypes) {
/*  52 */     this.cmd = cmd;
/*  53 */     this.arg = arg;
/*  54 */     this.senderTypes = senderTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubCommand(String cmd, SenderType... senderTypes) {
/*  62 */     this.cmd = cmd;
/*  63 */     this.senderTypes = senderTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SubCommand(String cmd) {
/*  70 */     this.cmd = cmd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void registerCommand(JavaPlugin plugin) {
/*  79 */     if (plugin == null) {
/*  80 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cCommand >> §4" + cmd() + " §cNull Plugin!");
/*     */       return;
/*     */     } 
/*  83 */     if (SXAttribute.isPluginEnabled()) {
/*  84 */       Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] §cCommand >> §cSXAttribute is Enabled §4" + cmd() + "§r !");
/*     */       return;
/*     */     } 
/*  87 */     this.plugin = plugin;
/*  88 */     subCommands.add(this);
/*     */   }
/*     */   
/*     */   public String cmd() {
/*  92 */     return this.cmd;
/*     */   }
/*     */   
/*     */   public String arg() {
/*  96 */     return this.arg;
/*     */   }
/*     */   
/*     */   public boolean hide() {
/* 100 */     return this.hide;
/*     */   }
/*     */   
/*     */   private String permission() {
/* 104 */     return this.plugin.getName() + "." + this.cmd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUse(CommandSender sender, SenderType type) {
/* 149 */     return (sender.hasPermission(permission()) && Arrays.<SenderType>stream(this.senderTypes).anyMatch(senderType -> (senderType.equals(SenderType.ALL) || senderType.equals(type))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendIntroduction(CommandSender sender, String color, String label) {
/* 160 */     String introduction = Message.getMsg(Message.valueOf("COMMAND__" + cmd().toUpperCase()), new Object[0]);
/* 161 */     String message = color + MessageFormat.format("/{0} {1}{2}§7 -§c {3}", new Object[] { label, cmd(), arg(), introduction });
/* 162 */     if (sender instanceof Player) {
/* 163 */       (new String[2])[0] = "§c" + introduction; (new String[2])[1] = "§cPermission: " + permission(); (new String[1])[0] = "§c" + introduction; ((Player)sender).spigot().sendMessage((BaseComponent)Message.getTextComponent(message, MessageFormat.format("/{0} {1}", new Object[] { label, cmd() }), Arrays.asList(sender.isOp() ? new String[2] : new String[1])));
/*     */     } else {
/* 165 */       sender.sendMessage(message);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Player getPlayer(CommandSender sender, Player player) {
/* 170 */     if (player == null) {
/* 171 */       if (sender instanceof Player) {
/* 172 */         player = (Player)sender;
/*     */       } else {
/* 174 */         sender.sendMessage(Message.getMsg(Message.ADMIN__NO_CONSOLE, new Object[0]));
/* 175 */         return null;
/*     */       } 
/*     */     }
/* 178 */     return player;
/*     */   }
/*     */   
/*     */   public abstract void onCommand(SXAttribute paramSXAttribute, CommandSender paramCommandSender, String[] paramArrayOfString);
/*     */   
/*     */   public abstract List<String> onTabComplete(SXAttribute paramSXAttribute, CommandSender paramCommandSender, String[] paramArrayOfString);
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\SubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */