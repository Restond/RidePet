/*    */ package lumine.xikage.mythicmobs.commands.skills;
/*    */ 
/*    */ import io.lumine.utils.chat.ColorString;
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.util.StringUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InfoCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public InfoCommand(Command<MythicMobs> parent) {
/* 22 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 27 */     if (((MythicMobs)getPlugin()).getSkillManager().getSkill(args[0]).isPresent()) {
/* 28 */       Skill mm = ((MythicMobs)getPlugin()).getSkillManager().getSkill(args[0]).get();
/* 29 */       sender.sendMessage(ColorString.get("&e&lInformation for &a" + mm.getInternalName() + "&7:"));
/* 30 */       sender.sendMessage(ColorString.get("&6Skill ID: &7" + mm.hashCode()));
/* 31 */       sender.sendMessage(ColorString.get("&6Located in File: &7" + mm.getFile()));
/*    */     } else {
/* 33 */       CommandHelper.sendError(sender, "No Mythic Skill loaded with the name " + args[0] + "");
/*    */     } 
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 40 */     if (args.length == 1) {
/* 41 */       return (List<String>)StringUtil.copyPartialMatches(args[0], (Iterable)((MythicMobs)getPlugin()).getSkillManager().getSkills().stream().map(Skill::getInternalName).collect(Collectors.toList()), new ArrayList());
/*    */     }
/* 43 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 48 */     return "mythicmobs.command.skills.info";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 58 */     return "info";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 63 */     return new String[] { "i" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\skills\InfoCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */