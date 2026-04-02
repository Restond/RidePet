/*    */ package saukiya.sxattribute.command.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.command.SenderType;
/*    */ import github.saukiya.sxattribute.command.SubCommand;
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeData;
/*    */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*    */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttributeListCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public AttributeListCommand() {
/* 28 */     super("attributeList", new SenderType[] { SenderType.ALL });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCommand(SXAttribute plugin, CommandSender sender, String[] args) {
/* 33 */     String search = (args.length < 2) ? "" : args[1];
/* 34 */     int filterSize = 0;
/* 35 */     SXAttributeData attributeData = (sender instanceof Player) ? plugin.getAttributeManager().getEntityData((LivingEntity)sender, new SXAttributeData[0]) : new SXAttributeData();
/* 36 */     for (Map.Entry<Integer, SubAttribute> entry : (Iterable<Map.Entry<Integer, SubAttribute>>)attributeData.getAttributeMap().entrySet()) {
/* 37 */       String message = "§b" + entry.getKey() + " §7- §c" + ((SubAttribute)entry.getValue()).getName() + " §8[§7Plugin: §c" + ((SubAttribute)entry.getValue()).getPlugin().getName() + "§7,Length: §c" + (((SubAttribute)entry.getValue()).getAttributes()).length + "§8]";
/* 38 */       if (message.contains(search)) {
/* 39 */         if (sender instanceof Player) {
/* 40 */           List<String> list = new ArrayList<>();
/* 41 */           list.add("&bAttributeType: ");
/* 42 */           for (SXAttributeType type : ((SubAttribute)entry.getValue()).getType()) {
/* 43 */             list.add("&7- " + type.getType() + " &8(&7" + type.getName() + "&8)");
/*    */           }
/* 45 */           list.add("&bPlaceholders: ");
/* 46 */           if (((SubAttribute)entry.getValue()).getPlaceholders() != null) {
/* 47 */             for (String placeName : ((SubAttribute)entry.getValue()).getPlaceholders()) {
/* 48 */               list.add("&7- %sx_" + placeName + "% : " + ((SubAttribute)entry.getValue()).getPlaceholder((Player)sender, placeName));
/*    */             }
/*    */           }
/* 51 */           list.add("&bValue: " + ((SubAttribute)entry.getValue()).getValue());
/* 52 */           TextComponent tc = Message.getTextComponent(message, null, list);
/* 53 */           if (((SubAttribute)entry.getValue()).introduction().size() > 0) {
/* 54 */             tc.addExtra((BaseComponent)Message.getTextComponent("§7 - §8[§cIntroduction§8]", null, ((SubAttribute)entry.getValue()).introduction()));
/*    */           }
/* 56 */           ((Player)sender).spigot().sendMessage((BaseComponent)tc); continue;
/*    */         } 
/* 58 */         sender.sendMessage(message);
/*    */         continue;
/*    */       } 
/* 61 */       filterSize++;
/*    */     } 
/*    */     
/* 64 */     if (search.length() > 0) {
/* 65 */       sender.sendMessage("§7> Filter§c " + filterSize + " §7Conditions");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(SXAttribute plugin, CommandSender sender, String[] args) {
/* 71 */     if (args.length == 2) {
/* 72 */       return (List<String>)(new SXAttributeData()).getAttributeMap().values().stream().map(SubAttribute::getName).collect(Collectors.toList());
/*    */     }
/* 74 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\AttributeListCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */