/*    */ package saukiya.sxattribute.command.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.command.SenderType;
/*    */ import github.saukiya.sxattribute.command.SubCommand;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GiveCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public GiveCommand() {
/* 23 */     super("give", " <ItemName> <Player> <Amount>", new SenderType[] { SenderType.ALL });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCommand(SXAttribute plugin, CommandSender sender, String[] args) {
/* 28 */     if (args.length < 2) {
/* 29 */       plugin.getItemDataManager().sendItemMapToPlayer(sender, new String[0]);
/*    */       return;
/*    */     } 
/* 32 */     Player player = null;
/* 33 */     if (plugin.getItemDataManager().getItem(args[1], null) == null) {
/* 34 */       plugin.getItemDataManager().sendItemMapToPlayer(sender, new String[] { args[1] });
/*    */       return;
/*    */     } 
/* 37 */     int amount = 1;
/* 38 */     if (args.length > 2) {
/* 39 */       player = Bukkit.getPlayerExact(args[2]);
/* 40 */       if (player != null) {
/* 41 */         if (args.length > 3) {
/* 42 */           amount = Integer.valueOf(args[3].replaceAll("[^0-9]", "")).intValue();
/*    */         }
/*    */       } else {
/* 45 */         sender.sendMessage(Message.getMsg(Message.ADMIN__NO_ONLINE, new Object[0]));
/*    */         return;
/*    */       } 
/*    */     } 
/* 49 */     player = getPlayer(sender, player);
/* 50 */     if (player != null) {
/* 51 */       for (int i = 0; i < amount; i++) {
/* 52 */         player.getInventory().addItem(new ItemStack[] { plugin.getItemDataManager().getItem(args[1], player) });
/*    */       } 
/* 54 */       plugin.getAttributeManager().loadHandData((LivingEntity)player);
/* 55 */       sender.sendMessage(Message.getMsg(Message.ADMIN__GIVE_ITEM, new Object[] { player.getName(), String.valueOf(amount), args[1] }));
/*    */     } else {
/* 57 */       sender.sendMessage(Message.getMsg(Message.ADMIN__NO_CONSOLE, new Object[0]));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(SXAttribute plugin, CommandSender sender, String[] args) {
/* 63 */     if (args.length == 2) {
/* 64 */       if (args[1].length() == 0) plugin.getItemDataManager().sendItemMapToPlayer(sender, new String[0]); 
/* 65 */       return (List<String>)plugin.getItemDataManager().getItemList().stream().filter(itemName -> itemName.contains(args[1])).collect(Collectors.toList());
/*    */     } 
/* 67 */     if (args.length == 4) {
/* 68 */       return Collections.singletonList("1");
/*    */     }
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\GiveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */