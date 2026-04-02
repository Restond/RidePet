/*     */ package lumine.xikage.mythicmobs.commands.items;
/*     */ 
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*     */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*     */ import io.lumine.xikage.mythicmobs.drops.DropTable;
/*     */ import io.lumine.xikage.mythicmobs.drops.LootBag;
/*     */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*     */ import io.lumine.xikage.mythicmobs.mobs.GenericCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.StringUtil;
/*     */ 
/*     */ public class GiveCommand extends Command<MythicMobs> {
/*     */   public GiveCommand(Command<MythicMobs> parent) {
/*  28 */     super(parent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  34 */     boolean optSilent = false;
/*     */     
/*  36 */     if (args != null && args.length > 2 && 
/*  37 */       args[0].startsWith("-")) {
/*  38 */       if (args[0].startsWith("-s")) {
/*  39 */         optSilent = true;
/*     */       }
/*  41 */       args = Arrays.<String>copyOfRange(args, 1, args.length);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  46 */     if (args.length < 2) {
/*  47 */       if (!optSilent) CommandHelper.sendError(sender, "Command Syntax: /mm item give <name> <item>"); 
/*  48 */       return true;
/*     */     } 
/*     */     
/*  51 */     Player target = Bukkit.getPlayer(args[0]);
/*  52 */     String item = args[1];
/*  53 */     int amount = 1;
/*     */     
/*  55 */     if (args.length > 2) {
/*  56 */       amount = Integer.valueOf(args[2]).intValue();
/*     */     }
/*     */     
/*  59 */     if (target == null) {
/*  60 */       if (!optSilent) CommandHelper.sendError(sender, "That player is not online"); 
/*  61 */       return true;
/*     */     } 
/*     */     
/*  64 */     AbstractPlayer atarget = BukkitAdapter.adapt(target);
/*  65 */     DropMetadata meta = new DropMetadata((SkillCaster)new GenericCaster((AbstractEntity)atarget), (AbstractEntity)atarget);
/*     */     
/*  67 */     Optional<DropTable> maybeDrops = MythicMobs.inst().getDropManager().getDropTable(item);
/*     */     
/*  69 */     if (maybeDrops.isPresent()) {
/*  70 */       DropTable dt = maybeDrops.get();
/*     */       
/*  72 */       if (dt.hasDrops()) {
/*  73 */         for (int i = 0; i < amount; i++) {
/*  74 */           LootBag loot = dt.generate(meta);
/*  75 */           loot.give(atarget, optSilent);
/*     */         } 
/*     */       }
/*  78 */       if (!optSilent) CommandHelper.sendSuccess(sender, "DropTable &b" + item + " &awas put in your inventory!"); 
/*  79 */       return true;
/*     */     } 
/*     */     
/*  82 */     Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(item);
/*  83 */     if (maybeItem.isPresent())
/*  84 */     { MythicItem mi = maybeItem.get();
/*  85 */       for (int i = 0; i < amount; i++) {
/*  86 */         target.getInventory().addItem(new ItemStack[] { BukkitAdapter.adapt(mi.generateItemStack(meta, 1)) });
/*     */       } 
/*  88 */       if (!optSilent) target.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.GREEN + "Item " + ChatColor.AQUA + item + ChatColor.GREEN + " was put in your inventory!"); 
/*  89 */       if (!optSilent) sender.sendMessage("");
/*     */        }
/*  91 */     else if (!optSilent) { sender.sendMessage(ChatColor.GOLD + "[MythicMobs] " + ChatColor.RED + "No Mythic Item loaded with the name " + item + "."); }
/*     */     
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/*  98 */     if (args.length == 1) {
/*  99 */       List<String> players = new ArrayList<>();
/*     */       
/* 101 */       for (Player p : Bukkit.getOnlinePlayers()) {
/* 102 */         players.add(p.getName());
/*     */       }
/* 104 */       return (List<String>)StringUtil.copyPartialMatches(args[0], players, new ArrayList());
/*     */     } 
/* 106 */     if (args.length == 2) {
/* 107 */       return (List<String>)StringUtil.copyPartialMatches(args[1], ((MythicMobs)getPlugin()).getItemManager().getItemNames(), new ArrayList());
/*     */     }
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/* 114 */     return "mythicmobs.command.items.give";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/* 119 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 124 */     return "give";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 129 */     return new String[] { "g", "gi" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\items\GiveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */