/*    */ package saukiya.sxattribute.data;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.data.RegisterSlot;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegisterSlotManager
/*    */ {
/*    */   private final SXAttribute plugin;
/* 18 */   private final Map<Integer, RegisterSlot> registerSlotMap = new HashMap<>(); public Map<Integer, RegisterSlot> getRegisterSlotMap() { return this.registerSlotMap; }
/*    */   
/*    */   public SXAttribute getPlugin() {
/* 21 */     return this.plugin;
/*    */   }
/*    */   
/*    */   public RegisterSlotManager(SXAttribute plugin) {
/* 25 */     this.plugin = plugin;
/* 26 */     loadData();
/*    */   }
/*    */   
/*    */   public void loadData() {
/* 30 */     this.registerSlotMap.clear();
/* 31 */     List<String> registerSlotList = Config.getConfig().getStringList("RegisterSlots.List");
/* 32 */     if (Config.isRegisterSlot() && registerSlotList.size() > 0) {
/* 33 */       for (String str : registerSlotList) {
/* 34 */         if (str.contains("#") && (str.split("#")).length > 1) {
/* 35 */           String[] args = str.split("#");
/* 36 */           String name = args[1];
/* 37 */           int slot = Integer.valueOf(args[0].replaceAll("[^0-9]", "")).intValue();
/* 38 */           String id = (args.length > 2) ? args[2] : null;
/* 39 */           this.registerSlotMap.put(Integer.valueOf(slot), new RegisterSlot(slot, name, id, this.plugin.getItemUtil()));
/*    */         } 
/*    */       } 
/* 42 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Load §c" + this.registerSlotMap.size() + " §rRegisterSlot");
/*    */     } else {
/* 44 */       Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cDisable RegisterSlot");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\RegisterSlotManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */