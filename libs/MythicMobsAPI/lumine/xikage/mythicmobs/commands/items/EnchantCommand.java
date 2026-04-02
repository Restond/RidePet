/*     */ package lumine.xikage.mythicmobs.commands.items;
/*     */ 
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*     */ import io.lumine.xikage.mythicmobs.compatibility.ArtifactsSupport;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.EnchantmentStorageMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.util.StringUtil;
/*     */ 
/*     */ public class EnchantCommand extends Command<MythicMobs> {
/*     */   public EnchantCommand(Command<MythicMobs> parent) {
/*  20 */     super(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*     */     ItemStack item;
/*  26 */     if (args == null || args.length == 0) {
/*  27 */       CommandHelper.sendError(sender, "Syntax: /mm item enchant <name> [level]");
/*  28 */       return true;
/*     */     } 
/*     */     
/*  31 */     String enchantment = args[0];
/*  32 */     int level = 1;
/*     */     
/*  34 */     if (args.length > 1) {
/*     */       try {
/*  36 */         level = Integer.valueOf(args[1]).intValue();
/*  37 */       } catch (Exception ex) {
/*  38 */         CommandHelper.sendError(sender, "Level must be an integer.");
/*  39 */         return true;
/*     */       } 
/*     */     }
/*     */     
/*  43 */     Player p = (Player)sender;
/*     */ 
/*     */     
/*  46 */     if (MythicMobs.inst().getMinecraftVersion() < 9) {
/*  47 */       item = p.getInventory().getItemInHand();
/*     */     } else {
/*  49 */       item = p.getInventory().getItemInMainHand();
/*     */     } 
/*     */     
/*  52 */     if (item == null || item.getType().equals(Material.AIR)) {
/*  53 */       CommandHelper.sendError(sender, "You must be holding a valid item!");
/*  54 */       return true;
/*     */     } 
/*     */     
/*  57 */     Enchantment en = Enchantment.getByName(enchantment);
/*  58 */     if (en == null) {
/*  59 */       CommandHelper.sendError(sender, "You must enter a valid enchantment name!");
/*  60 */       return true;
/*     */     } 
/*     */     
/*  63 */     if (MythicMobs.inst().getCompatibility().getArtifacts().isPresent() && (
/*  64 */       (ArtifactsSupport)MythicMobs.inst().getCompatibility().getArtifacts().get()).handleEnchant(item, en, level)) {
/*  65 */       CommandHelper.sendSuccess(sender, "Applied enchantment to held item!");
/*  66 */       return true;
/*     */     } 
/*     */     
/*  69 */     if (item.getType() != Material.ENCHANTED_BOOK) {
/*  70 */       item.addUnsafeEnchantment(en, level);
/*     */     } else {
/*  72 */       EnchantmentStorageMeta esm = (EnchantmentStorageMeta)item.getItemMeta();
/*  73 */       esm.addStoredEnchant(en, level, true);
/*  74 */       item.setItemMeta((ItemMeta)esm);
/*     */     } 
/*     */     
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/*  82 */     if (args.length == 1) {
/*  83 */       List<String> enchants = new ArrayList<>();
/*  84 */       for (Enchantment ench : Enchantment.values()) {
/*  85 */         enchants.add(ench.getName());
/*     */       }
/*  87 */       return (List<String>)StringUtil.copyPartialMatches(args[0], enchants, new ArrayList());
/*     */     } 
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/*  94 */     return "mythicmobs.command.items.enchant";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 104 */     return "enchant";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 109 */     return new String[] { "ench", "e" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\items\EnchantCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */