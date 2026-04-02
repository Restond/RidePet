/*    */ package saukiya.sxattribute.command.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.command.SenderType;
/*    */ import github.saukiya.sxattribute.command.SubCommand;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.EntityEquipment;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NBTCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public NBTCommand() {
/* 21 */     super("nbt", new SenderType[] { SenderType.PLAYER });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCommand(SXAttribute plugin, CommandSender sender, String[] args) {
/* 26 */     EntityEquipment eq = ((Player)sender).getEquipment();
/* 27 */     ItemStack item = (SXAttribute.getVersionSplit()[1] >= 9) ? eq.getItemInMainHand() : eq.getItemInHand();
/* 28 */     String str = plugin.getItemUtil().getAllNBT(item);
/* 29 */     sender.sendMessage(str);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(SXAttribute plugin, CommandSender sender, String[] args) {
/* 34 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\NBTCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */