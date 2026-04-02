/*    */ package saukiya.sxattribute.listener;
/*    */ 
/*    */ import github.saukiya.sxattribute.SXAttribute;
/*    */ import github.saukiya.sxattribute.util.Message;
/*    */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.inventory.EntityEquipment;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OnMythicmobsSpawnListener
/*    */   implements Listener
/*    */ {
/*    */   private final SXAttribute plugin;
/*    */   
/*    */   public OnMythicmobsSpawnListener(SXAttribute plugin) {
/* 25 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   void onMythicMobSpawnEvent(MythicMobSpawnEvent event) {
/* 30 */     if (event.getEntity() instanceof LivingEntity) {
/* 31 */       MythicMob mm = event.getMobType();
/* 32 */       List<String> list = mm.getEquipment();
/* 33 */       LivingEntity entity = (LivingEntity)event.getEntity();
/*    */       
/* 35 */       EntityEquipment eq = entity.getEquipment();
/* 36 */       for (String str : list) {
/*    */         
/* 38 */         if (str.contains(":") && str.split(":")[0].contains(" ")) {
/* 39 */           String[] args1 = str.split(":");
/* 40 */           String[] args2 = args1[0].split(" ");
/* 41 */           if (args2[0].equalsIgnoreCase("sx") && args1.length > 1 && args2.length > 1) {
/* 42 */             int position = 0;
/* 43 */             String strSplitReplaceAll = args1[1].replaceAll("[^0-9]", "");
/* 44 */             if (args1[1].contains(" ")) {
/* 45 */               String[] args1Split = args1[1].split(" ");
/* 46 */               if (args1Split.length > 1 && args1Split[1].replaceAll("[^0-9.]", "").length() > 0 && SXAttribute.getRandom().nextDouble() > Double.valueOf(args1Split[1].replaceAll("[^0-9.]", "")).doubleValue()) {
/*    */                 continue;
/*    */               }
/* 49 */               strSplitReplaceAll = args1Split[0].replace("[^0-9]", "");
/*    */             } 
/*    */             
/* 52 */             if (strSplitReplaceAll.length() == 1) {
/* 53 */               position = Integer.valueOf(strSplitReplaceAll).intValue();
/*    */             }
/* 55 */             ItemStack item = this.plugin.getItemDataManager().getItem(args2[1], null);
/* 56 */             if (item != null) {
/* 57 */               if (position >= -1 && position < 5) {
/* 58 */                 switch (position) {
/*    */                   case -1:
/* 60 */                     eq.setItemInOffHand(item);
/*    */                     continue;
/*    */                   
/*    */                   case 0:
/* 64 */                     eq.setItemInMainHand(item);
/*    */                     continue;
/*    */                   
/*    */                   case 1:
/* 68 */                     eq.setBoots(item);
/*    */                     continue;
/*    */                   
/*    */                   case 2:
/* 72 */                     eq.setLeggings(item);
/*    */                     continue;
/*    */                   
/*    */                   case 3:
/* 76 */                     eq.setChestplate(item);
/*    */                     continue;
/*    */                   
/*    */                   case 4:
/* 80 */                     eq.setHelmet(item);
/*    */                     continue;
/*    */                 } 
/*    */                 continue;
/*    */               } 
/* 85 */               Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cMythicmobs怪物: §4" + mm.getDisplayName() + "§c 的物品: §4" + args2[1] + " §c穿戴位置不合法!");
/*    */               continue;
/*    */             } 
/* 88 */             Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cMythicmobs怪物: §4" + mm.getDisplayName() + "§1 不存在这个穿戴物品: §4" + args2[1]);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\listener\OnMythicmobsSpawnListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */