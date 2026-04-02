/*    */ package lumine.xikage.mythicmobs.commands.mobs;
/*    */ 
/*    */ import io.lumine.utils.commands.Command;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.util.StringUtil;
/*    */ 
/*    */ 
/*    */ public class KillTypeCommand
/*    */   extends Command<MythicMobs>
/*    */ {
/*    */   public KillTypeCommand(Command<MythicMobs> parent) {
/* 20 */     super(parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender sender, String[] args) {
/* 25 */     String s = ChatColor.GREEN + "Mobs Killed: ";
/*    */     
/* 27 */     String type = args[0];
/*    */     
/* 29 */     for (LivingEntity l : MythicMobs.inst().getMobManager().getAllMythicEntities()) {
/* 30 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/*    */       
/* 32 */       if (am != null && 
/* 33 */         am.getType().getInternalName().contains(type)) {
/* 34 */         am.setDespawned();
/* 35 */         MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/* 36 */         l.remove();
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 41 */     for (MythicMob mm : MythicMobs.inst().getMobManager().getMobTypes()) {
/* 42 */       if (mm.getInternalName().contains(type)) {
/* 43 */         String[] parts = mm.getInternalName().split(type);
/* 44 */         if (parts.length == 2)
/* 45 */           s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + type + ChatColor.RED + parts[1] + ChatColor.GRAY + ", "; 
/* 46 */         if (parts.length == 1)
/* 47 */           s = s + ChatColor.RED + parts[0] + ChatColor.DARK_RED + type + ChatColor.GRAY + ", "; 
/* 48 */         if (parts.length == 0) {
/* 49 */           s = s + ChatColor.DARK_RED + type + ChatColor.GRAY + ", ";
/*    */         }
/*    */       } 
/*    */     } 
/* 53 */     sender.sendMessage(s);
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 59 */     if (args.length == 1) {
/* 60 */       return (List<String>)StringUtil.copyPartialMatches(args[0], ((MythicMobs)getPlugin()).getMobManager().getMobNames(), new ArrayList());
/*    */     }
/* 62 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPermissionNode() {
/* 67 */     return "mythicmobs.command.mobs.kill";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConsoleFriendly() {
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 77 */     return "kill";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getAliases() {
/* 82 */     return new String[] { "k" };
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\mobs\KillTypeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */