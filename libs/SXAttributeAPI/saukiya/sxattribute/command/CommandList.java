/*    */ package saukiya.sxattribute.command;
/*    */ 
/*    */ import github.saukiya.sxattribute.command.SubCommand;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandList
/*    */ {
/* 13 */   private final Map<Integer, SubCommand> subCommands = new TreeMap<>();
/*    */   
/*    */   public void add(SubCommand subCommand) {
/* 16 */     for (Map.Entry<Integer, SubCommand> entry : this.subCommands.entrySet()) {
/* 17 */       if (((SubCommand)entry.getValue()).cmd().equalsIgnoreCase(subCommand.cmd())) {
/* 18 */         Bukkit.getConsoleSender().sendMessage("[" + subCommand.getPlugin().getName() + "] Command >> The §c" + subCommand.cmd() + " §rCover To §c" + ((SubCommand)entry.getValue()).cmd() + " §7[§c" + ((SubCommand)entry.getValue()).getPlugin() + "§7]§r !");
/* 19 */         this.subCommands.put(entry.getKey(), subCommand);
/*    */         return;
/*    */       } 
/*    */     } 
/* 23 */     this.subCommands.put(Integer.valueOf(this.subCommands.size() + 1), subCommand);
/*    */   }
/*    */   
/*    */   public int size() {
/* 27 */     return this.subCommands.size();
/*    */   }
/*    */   
/*    */   public boolean contains(SubCommand subCommand) {
/* 31 */     return this.subCommands.values().stream().anyMatch(cmd -> cmd.cmd().contains(subCommand.cmd()));
/*    */   }
/*    */   
/*    */   public Collection<SubCommand> toCollection() {
/* 35 */     return this.subCommands.values();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\CommandList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */