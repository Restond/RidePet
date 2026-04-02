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
/*     */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*     */ import io.lumine.xikage.mythicmobs.items.MythicItem;
/*     */ import io.lumine.xikage.mythicmobs.mobs.GenericCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.StringUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GetCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public GetCommand(Command<MythicMobs> parent) {
/*  32 */     super(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  37 */     Player p = (Player)sender;
/*     */     
/*  39 */     if (args.length < 1) {
/*  40 */       CommandHelper.sendError(sender, "Command Syntax: /mm item get <name>");
/*  41 */       return true;
/*     */     } 
/*     */     
/*  44 */     String item = args[0];
/*  45 */     int amount = 1;
/*     */     
/*  47 */     if (args.length > 1) {
/*  48 */       amount = Integer.valueOf(args[1]).intValue();
/*     */     }
/*     */     
/*  51 */     AbstractPlayer target = BukkitAdapter.adapt(p);
/*  52 */     DropMetadata meta = new DropMetadata((SkillCaster)new GenericCaster((AbstractEntity)target), (AbstractEntity)target);
/*     */     
/*  54 */     Optional<DropTable> maybeDrops = MythicMobs.inst().getDropManager().getDropTable(item);
/*     */     
/*  56 */     if (maybeDrops.isPresent()) {
/*  57 */       DropTable dt = maybeDrops.get();
/*     */       
/*  59 */       if (dt.hasDrops()) {
/*  60 */         for (int i = 0; i < amount; i++) {
/*  61 */           LootBag loot = dt.generate(meta);
/*  62 */           loot.give(target);
/*     */         } 
/*     */       }
/*  65 */       CommandHelper.sendSuccess(sender, "DropTable &b" + item + " &awas put in your inventory!");
/*  66 */       return true;
/*     */     } 
/*     */     
/*  69 */     Optional<MythicItem> maybeItem = MythicMobs.inst().getItemManager().getItem(item);
/*  70 */     if (maybeItem.isPresent()) {
/*  71 */       MythicItem mi = maybeItem.get();
/*  72 */       for (int i = 0; i < amount; i++) {
/*     */         try {
/*  74 */           p.getInventory().addItem(new ItemStack[] { BukkitAdapter.adapt(mi.generateItemStack(meta, 1)) });
/*  75 */         } catch (Exception ex) {
/*  76 */           CommandHelper.sendError(sender, "Could not get item: item is improperly configured and returned null.");
/*  77 */           if (ConfigManager.debugLevel > 0) {
/*  78 */             ex.printStackTrace();
/*     */           }
/*  80 */           return true;
/*     */         } 
/*     */       } 
/*  83 */       CommandHelper.sendSuccess(sender, "Item &b" + item + " &awas put in your inventory!");
/*     */     } else {
/*  85 */       CommandHelper.sendError(sender, "No Mythic Item loaded with the name " + item + ".");
/*     */     } 
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/*  92 */     if (args.length == 1) {
/*  93 */       return (List<String>)StringUtil.copyPartialMatches(args[0], ((MythicMobs)getPlugin()).getItemManager().getItemNames(), new ArrayList());
/*     */     }
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/* 100 */     return "mythicmobs.command.items.get";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 110 */     return "get";
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\items\GetCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */