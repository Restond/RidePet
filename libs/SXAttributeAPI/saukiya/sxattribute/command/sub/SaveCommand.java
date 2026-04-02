/*    */ package saukiya.sxattribute.command.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.command.SenderType;
/*    */ import github.saukiya.sxattribute.command.SubCommand;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SaveCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public SaveCommand() {
/* 22 */     super("save", " <ItemName> ", new SenderType[] { SenderType.PLAYER });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onCommand(SXAttribute plugin, CommandSender sender, String[] args) {
/* 28 */     if (args.length < 2) {
/* 29 */       sender.sendMessage(Message.getMsg(Message.ADMIN__NO_FORMAT, new Object[0]));
/*    */       return;
/*    */     } 
/* 32 */     String itemName = args[1];
/* 33 */     Player player = (Player)sender;
/* 34 */     ItemStack itemStack = player.getEquipment().getItemInMainHand();
/* 35 */     if (itemStack.getType().toString().contains("AIR")) {
/* 36 */       player.sendMessage(Message.getMsg(Message.ADMIN__NO_ITEM, new Object[0]));
/*    */       return;
/*    */     } 
/* 39 */     if (plugin.getItemDataManager().isItem(itemName)) {
/* 40 */       player.sendMessage(Message.getMsg(Message.ADMIN__HAS_ITEM, new Object[] { itemName }));
/*    */       return;
/*    */     } 
/*    */     try {
/* 44 */       if (args.length > 2 && args[2].equalsIgnoreCase("-a")) {
/* 45 */         plugin.getItemDataManager().importItem(itemName, itemStack);
/*    */       } else {
/* 47 */         plugin.getItemDataManager().saveItem(itemName, itemStack);
/*    */       } 
/* 49 */       sender.sendMessage(Message.getMsg(Message.ADMIN__SAVE_ITEM, new Object[] { itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : itemStack.getType().name(), args[1] }));
/* 50 */     } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
/* 51 */       e.printStackTrace();
/* 52 */       sender.sendMessage(Message.getMsg(Message.ADMIN__SAVE_ITEM_ERROR, new Object[] { itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : itemStack.getType().name(), args[1] }));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(SXAttribute plugin, CommandSender sender, String[] args) {
/* 58 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\SaveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */