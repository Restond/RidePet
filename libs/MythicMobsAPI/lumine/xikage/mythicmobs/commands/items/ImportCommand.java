/*    */ package lumine.xikage.mythicmobs.commands.items;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicConfig;
/*    */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ImportCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public ImportCommand(Command<MythicMobs> parent) {
/* 21 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 27 */     if (args.length < 1) {
/* 28 */       CommandHelper.sendError(sender, "You must specify a name for this item.");
/* 29 */       return true;
/*    */     } 
/*    */     
/* 32 */     String name = args[0];
/* 33 */     String fileName = name + ".yml";
/*    */     
/* 35 */     if (args.length > 1) {
/* 36 */       fileName = args[1];
/*    */     }
/*    */     
/* 39 */     Player player = (Player)sender;
/* 40 */     ItemStack item = player.getInventory().getItemInHand();
/*    */     
/* 42 */     if (item == null || item.getType() == Material.AIR) {
/* 43 */       CommandHelper.sendError(sender, "You must be holding an item to import.");
/* 44 */       return true;
/*    */     } 
/*    */     
/* 47 */     String path = ((MythicMobs)this.plugin).getDataFolder() + System.getProperty("file.separator") + "Items" + System.getProperty("file.separator") + fileName;
/*    */     
/* 49 */     if (!path.endsWith(".yml")) {
/* 50 */       path = path + ".yml";
/*    */     }
/*    */     
/* 53 */     File file = new File(path);
/*    */     
/* 55 */     if (!file.exists()) {
/*    */       try {
/* 57 */         if (!file.createNewFile()) {
/* 58 */           CommandHelper.sendError(sender, "Failed to create new file for Item Import.");
/* 59 */           return true;
/*    */         } 
/* 61 */       } catch (IOException e) {
/* 62 */         CommandHelper.sendError(sender, "Failed to create new file for Item Import.");
/* 63 */         e.printStackTrace();
/*    */       } 
/*    */     }
/*    */     
/* 67 */     MythicConfig mc = new MythicConfig(name, file);
/* 68 */     mc.load();
/* 69 */     mc.set("ItemStack", item);
/* 70 */     mc.save();
/*    */     
/* 72 */     MythicItem mi = new MythicItem(file.getName(), name, mc);
/* 73 */     ((MythicMobs)getPlugin()).getItemManager().registerItem(name, mi);
/*    */     
/* 75 */     CommandHelper.sendSuccess(sender, "Item '" + name + "' imported successfully to file " + path);
/* 76 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 81 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 86 */     return "mythicmobs.command.items.import";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 91 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 96 */     return "import";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\items\ImportCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */