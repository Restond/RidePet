/*    */ package lumine.xikage.mythicmobs.commands.test;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.util.StringUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CastCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public CastCommand(Command<MythicMobs> parent) {
/* 22 */     super(parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 28 */     Player player = (Player)sender;
/* 29 */     LivingEntity target = MythicUtil.getTargetedEntity(player);
/*    */     
/* 31 */     String spell = args[0];
/*    */     
/* 33 */     List<Entity> targets = new ArrayList<>();
/* 34 */     targets.add(target);
/*    */     
/* 36 */     MythicMobs.inst().getAPIHelper().castSkill((Entity)player, spell, (Entity)player, player.getLocation(), targets, null, 1.0F);
/* 37 */     CommandHelper.sendSuccess(sender, "Trying to cast spell " + spell);
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 43 */     if (args.length == 1) {
/* 44 */       return (List<String>)StringUtil.copyPartialMatches(args[0], ((MythicMobs)getPlugin()).getSkillManager().getSkillNames(), new ArrayList());
/*    */     }
/* 46 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 51 */     return "mythicmobs.command.test.cast";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 56 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 61 */     return "cast";
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\test\CastCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */