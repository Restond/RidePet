/*    */ package saukiya.sxattribute.command.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.command.SenderType;
/*    */ import github.saukiya.sxattribute.command.SubCommand;
/*    */ import github.saukiya.sxattribute.data.condition.SXConditionType;
/*    */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConditionListCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public ConditionListCommand() {
/* 26 */     super("conditionList", new SenderType[] { SenderType.ALL });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCommand(SXAttribute plugin, CommandSender sender, String[] args) {
/* 31 */     String search = (args.length < 2) ? "" : args[1];
/* 32 */     int filterSize = 0;
/* 33 */     for (Map.Entry<Integer, SubCondition> entry : (Iterable<Map.Entry<Integer, SubCondition>>)plugin.getConditionManager().getConditionMap().entrySet()) {
/* 34 */       String message = "§b" + entry.getKey() + " §7- §c" + ((SubCondition)entry.getValue()).getName() + " §8[§7Plugin: §c" + ((SubCondition)entry.getValue()).getPlugin().getName() + "§8]";
/* 35 */       if (message.contains(search)) {
/* 36 */         if (sender instanceof Player) {
/* 37 */           List<String> list = new ArrayList<>();
/* 38 */           list.add("&bConditionType: ");
/* 39 */           Arrays.<SXConditionType>stream(((SubCondition)entry.getValue()).getType()).map(type -> "&7- " + type.getType() + " &8(&7" + type.getName() + "&8)").forEach(list::add);
/* 40 */           TextComponent tc = Message.getTextComponent(message, null, list);
/* 41 */           if (((SubCondition)entry.getValue()).introduction().size() > 0) {
/* 42 */             tc.addExtra((BaseComponent)Message.getTextComponent("§7 - §8[§cIntroduction§8]", null, ((SubCondition)entry.getValue()).introduction()));
/*    */           }
/* 44 */           ((Player)sender).spigot().sendMessage((BaseComponent)tc); continue;
/*    */         } 
/* 46 */         sender.sendMessage(message);
/*    */         continue;
/*    */       } 
/* 49 */       filterSize++;
/*    */     } 
/*    */     
/* 52 */     if (search.length() > 0) {
/* 53 */       sender.sendMessage("§7> Filter§c " + filterSize + " §7Conditions");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(SXAttribute plugin, CommandSender sender, String[] args) {
/* 59 */     if (args.length == 2) {
/* 60 */       return (List<String>)plugin.getConditionManager().getConditionMap().values().stream().map(SubCondition::getName).collect(Collectors.toList());
/*    */     }
/* 62 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\ConditionListCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */