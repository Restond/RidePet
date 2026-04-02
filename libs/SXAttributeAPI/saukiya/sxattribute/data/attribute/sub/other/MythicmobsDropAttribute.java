/*     */ package saukiya.sxattribute.data.attribute.sub.other;
/*     */ 
/*     */ import github.saukiya.sxattribute.SXAttribute;
/*     */ import github.saukiya.sxattribute.data.attribute.SXAttributeType;
/*     */ import github.saukiya.sxattribute.data.attribute.SubAttribute;
/*     */ import github.saukiya.sxattribute.data.eventdata.EventData;
/*     */ import github.saukiya.sxattribute.util.Message;
/*     */ import github.saukiya.sxseal.SXSeal;
/*     */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ 
/*     */ public class MythicmobsDropAttribute
/*     */   extends SubAttribute
/*     */   implements Listener
/*     */ {
/*     */   private static boolean sxSeal = false;
/*     */   
/*     */   public MythicmobsDropAttribute() {
/*  28 */     super("MythicmobsDrop", 0, new SXAttributeType[] { new SXAttributeType(SXAttributeType.Type.OTHER, "Drops") });
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private static void onMythicMobDeathEvent(MythicMobDeathEvent event) {
/*  33 */     MythicMob mm = event.getMobType();
/*  34 */     List<String> dropList = mm.getDrops();
/*  35 */     List<ItemStack> drops = event.getDrops();
/*  36 */     if (event.getKiller() instanceof Player) {
/*  37 */       for (String str : dropList) {
/*  38 */         if (str.contains(" ")) {
/*  39 */           String[] args = str.split(" ");
/*  40 */           if (args.length > 1 && args[0].equalsIgnoreCase("sx")) {
/*  41 */             int amount = 1;
/*  42 */             if (args.length > 3 && args[3].length() > 0 && SXAttribute.getRandom().nextDouble() > Double.valueOf(args[3].replaceAll("[^0-9.]", "")).doubleValue()) {
/*     */               continue;
/*     */             }
/*  45 */             if (args.length > 2 && args[2].length() > 0) {
/*  46 */               if (args[2].contains("-") && (args[2].split("-")).length > 1) {
/*  47 */                 int i1 = Integer.valueOf(args[2].split("-")[0].replaceAll("[^0-9]", "")).intValue();
/*  48 */                 int i2 = Integer.valueOf(args[2].split("-")[1].replaceAll("[^0-9]", "")).intValue();
/*  49 */                 if (i1 > i2) {
/*  50 */                   Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§c随机数大小不正确!: §4" + str);
/*     */                 } else {
/*  52 */                   amount = SXAttribute.getRandom().nextInt(i2 - i1 + 1) + i1;
/*     */                 } 
/*     */               } else {
/*  55 */                 amount = Integer.valueOf(args[2].replaceAll("[^0-9]", "")).intValue();
/*     */               } 
/*     */             }
/*  58 */             ItemStack item = SXAttribute.getApi().getItem(args[1], (Player)event.getKiller());
/*  59 */             if (item != null) {
/*  60 */               if (str.contains("seal") && sxSeal) {
/*  61 */                 SXSeal.getApi().sealItem(item);
/*     */               }
/*  63 */               item.setAmount(amount);
/*  64 */               drops.add(item.clone()); continue;
/*     */             } 
/*  66 */             Bukkit.getConsoleSender().sendMessage(Message.getMessagePrefix() + "§cMythicmobs怪物: §4" + mm.getDisplayName() + "§c 不存在这个掉落物品: §4" + args[1]);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  76 */     if (Bukkit.getPluginManager().getPlugin("MythicMobs") != null) {
/*  77 */       if (Bukkit.getPluginManager().getPlugin("SX-Seal") != null) {
/*  78 */         sxSeal = true;
/*     */       }
/*  80 */       Bukkit.getPluginManager().registerEvents(this, (Plugin)getPlugin());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void eventMethod(EventData eventData) {}
/*     */ 
/*     */   
/*     */   public String getPlaceholder(Player player, String string) {
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getPlaceholders() {
/*  95 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean loadAttribute(String lore) {
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getValue() {
/* 105 */     return 0.0D;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\attribute\sub\other\MythicmobsDropAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */