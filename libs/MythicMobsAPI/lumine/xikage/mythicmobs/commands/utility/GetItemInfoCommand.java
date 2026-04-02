/*     */ package lumine.xikage.mythicmobs.commands.utility;
/*     */ 
/*     */ import io.lumine.utils.chat.ColorString;
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.CompoundTag;
/*     */ import io.lumine.xikage.mythicmobs.util.jnbt.Tag;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GetItemInfoCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public GetItemInfoCommand(Command<MythicMobs> parent) {
/*  34 */     super(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*     */     ItemStack item;
/*  40 */     Player p = (Player)sender;
/*     */ 
/*     */     
/*  43 */     if (MythicMobs.inst().getMinecraftVersion() < 9) {
/*  44 */       item = p.getInventory().getItemInHand();
/*     */     } else {
/*  46 */       item = p.getInventory().getItemInMainHand();
/*     */     } 
/*     */     
/*  49 */     if (item == null || item.getType().equals(Material.AIR)) {
/*  50 */       CommandHelper.sendError(sender, "You must be holding a valid item!");
/*  51 */       return true;
/*     */     } 
/*     */     
/*  54 */     sender.sendMessage(ColorString.get("&6&lInformation About Held Item&f:"));
/*  55 */     sender.sendMessage(ColorString.get("&6Material&f: &7" + item.getType().toString()));
/*     */     
/*  57 */     if (item.hasItemMeta()) {
/*  58 */       ItemMeta meta = item.getItemMeta();
/*     */       
/*  60 */       if (meta.hasDisplayName()) {
/*  61 */         sender.sendMessage(ColorString.get("&6Item Display&f: &7" + meta.getDisplayName()));
/*     */       }
/*     */       
/*  64 */       try { if (meta instanceof Damageable) {
/*  65 */           sender.sendMessage(ColorString.get("&6Durability&f: &7" + ((Damageable)meta).getDamage() + " / " + item.getType().getMaxDurability()));
/*     */         } }
/*  67 */       catch (Exception exception) {  }
/*  68 */       catch (Error error) {}
/*     */     } 
/*     */     
/*     */     try {
/*  72 */       CompoundTag compoundTag = MythicMobs.inst().getVolatileCodeHandler().getItemHandler().getNBTData(item);
/*     */       
/*  74 */       sender.sendMessage(ColorString.get("&6NBT&f: &7"));
/*     */       
/*  76 */       if (compoundTag.getValue().size() == 0) {
/*  77 */         sender.sendMessage(ColorString.get("&7-- &fNone"));
/*     */       } else {
/*  79 */         for (Map.Entry<String, Tag> entry : (Iterable<Map.Entry<String, Tag>>)compoundTag.getValue().entrySet()) {
/*  80 */           String key = entry.getKey();
/*  81 */           Tag value = entry.getValue();
/*     */           
/*  83 */           if (value instanceof CompoundTag) {
/*  84 */             sender.sendMessage(ColorString.get("&7-- &f" + key + "&e: "));
/*  85 */             for (Map.Entry<String, Tag> entry2 : (Iterable<Map.Entry<String, Tag>>)((CompoundTag)value).getValue().entrySet()) {
/*  86 */               String key2 = entry2.getKey();
/*  87 */               Tag value2 = entry2.getValue();
/*  88 */               sender.sendMessage(ColorString.get("&7---- &f" + key2 + " &7 == &e" + value2.toString()));
/*     */             }  continue;
/*     */           } 
/*  91 */           sender.sendMessage(ColorString.get("&7-- &f" + key + " &7 == &e" + value.toString()));
/*     */         }
/*     */       
/*     */       } 
/*  95 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/* 109 */     return "mythicmobs.command.utilities.getiteminfo";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 119 */     return "getiteminfo";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 124 */     return new String[] { "gii" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\command\\utility\GetItemInfoCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */