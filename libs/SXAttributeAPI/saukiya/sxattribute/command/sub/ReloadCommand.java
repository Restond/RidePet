/*    */ package saukiya.sxattribute.command.sub;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.command.SubCommand;
/*    */ import github.saukiya.sxattribute.event.SXReloadEvent;
/*    */ import github.saukiya.sxattribute.util.Config;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReloadCommand
/*    */   extends SubCommand
/*    */ {
/*    */   public ReloadCommand() {
/* 27 */     super("reload");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCommand(SXAttribute plugin, CommandSender sender, String[] args) {
/* 32 */     Long oldTimes = Long.valueOf(System.currentTimeMillis());
/*    */     try {
/* 34 */       Config.loadConfig();
/* 35 */       Message.loadMessage();
/* 36 */       plugin.getRandomStringManager().loadData();
/* 37 */       plugin.getItemDataManager().loadItemData();
/* 38 */     } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
/* 39 */       e.printStackTrace();
/* 40 */       sender.sendMessage(Message.getMessagePrefix() + "§cIO Error");
/*    */       return;
/*    */     } 
/* 43 */     SXAttribute.getSdf().reload();
/* 44 */     plugin.getRegisterSlotManager().loadData();
/* 45 */     plugin.getAttributeManager().getSlotMap().clear();
/* 46 */     plugin.getAttributeManager().loadDefaultAttributeData();
/* 47 */     (new Object(this, plugin))
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 56 */       .runTaskAsynchronously((Plugin)plugin);
/* 57 */     int size = 0;
/* 58 */     if (SXAttribute.getVersionSplit()[1] >= 11) {
/* 59 */       for (UUID uuid : plugin.getAttributeManager().getEquipmentMap().keySet()) {
/* 60 */         Entity entity = Bukkit.getEntity(uuid);
/* 61 */         if (entity == null) {
/* 62 */           plugin.getAttributeManager().clearEntityData(uuid);
/* 63 */           size++;
/*    */         } 
/*    */       } 
/*    */     } else {
/* 67 */       for (World world : Bukkit.getWorlds()) {
/* 68 */         for (Entity entity : world.getEntities()) {
/* 69 */           for (UUID uuid : plugin.getAttributeManager().getEquipmentMap().keySet()) {
/* 70 */             if (entity == null) {
/* 71 */               plugin.getAttributeManager().clearEntityData(uuid);
/* 72 */               size++;
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 78 */     if (size > 0) {
/* 79 */       sender.sendMessage(Message.getMsg(Message.ADMIN__CLEAR_ENTITY_DATA, new Object[] { String.valueOf(size) }));
/*    */     }
/* 81 */     sender.sendMessage(Message.getMsg(Message.ADMIN__PLUGIN_RELOAD, new Object[0]));
/* 82 */     Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "Reloading Time: §c" + (System.currentTimeMillis() - oldTimes.longValue()) + "§7 ms");
/* 83 */     Bukkit.getPluginManager().callEvent((Event)new SXReloadEvent(sender));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(SXAttribute plugin, CommandSender sender, String[] args) {
/* 88 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\command\sub\ReloadCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */