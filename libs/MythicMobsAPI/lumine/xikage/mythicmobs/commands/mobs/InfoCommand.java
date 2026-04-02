/*    */ package lumine.xikage.mythicmobs.commands.mobs;
/*    */ 
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.util.StringUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InfoCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public InfoCommand(Command<MythicMobs> parent) {
/* 20 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 25 */     if (((MythicMobs)getPlugin()).getMobManager().getMythicMob(args[0]) != null) {
/* 26 */       MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(args[0]);
/* 27 */       sender.sendMessage(ColorString.get("&e&lInformation for &a" + mm.getInternalName() + "&7:"));
/* 28 */       sender.sendMessage(ChatColor.GOLD + "Mob ID: " + ChatColor.GRAY + mm.hashCode());
/* 29 */       sender.sendMessage(ChatColor.GOLD + "Display Name: " + ChatColor.GRAY + mm.getDisplayName());
/* 30 */       sender.sendMessage(ChatColor.GOLD + "EntityType: " + ChatColor.GRAY + mm.getEntityType());
/* 31 */       sender.sendMessage(ChatColor.GOLD + "Damage: " + ChatColor.GRAY + mm.getBaseDamage() + " (+" + mm.getPerLevelDamage() + " per level)");
/* 32 */       sender.sendMessage(ChatColor.GOLD + "Health: " + ChatColor.GRAY + mm.getHealth() + " (+" + mm.getPerLevelHealth() + " per level)");
/* 33 */       sender.sendMessage(ChatColor.GOLD + "Armor: " + ChatColor.GRAY + mm.getBaseArmor() + " (+" + mm.getPerLevelArmor() + " per level)");
/* 34 */       sender.sendMessage(ChatColor.GOLD + "Power per Level: +" + ChatColor.GRAY + mm.getPerLevelPower());
/* 35 */       sender.sendMessage(ChatColor.GOLD + "Located in File: " + ChatColor.GRAY + mm.getFile());
/*    */     } else {
/*    */       
/* 38 */       CommandHelper.sendError(sender, "No Mythic Mob loaded with the name " + args[0] + "");
/*    */     } 
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 45 */     if (args.length == 1) {
/* 46 */       return (List<String>)StringUtil.copyPartialMatches(args[0], ((MythicMobs)getPlugin()).getMobManager().getMobNames(), new ArrayList());
/*    */     }
/* 48 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 53 */     return "mythicmobs.command.mobs.info";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 63 */     return "info";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 68 */     return new String[] { "i" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\mobs\InfoCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */