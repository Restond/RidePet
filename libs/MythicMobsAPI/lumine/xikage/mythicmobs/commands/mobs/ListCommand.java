/*     */ package lumine.xikage.mythicmobs.commands.mobs;
/*     */ 
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*     */ import io.lumine.xikage.mythicmobs.mobs.MythicMobStack;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public ListCommand(Command<MythicMobs> parent) {
/*  19 */     super(parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  24 */     List<MythicMob> mobs = new ArrayList<>(MythicMobs.inst().getMobManager().getMobTypes());
/*  25 */     Collections.sort(mobs);
/*     */     
/*  27 */     if (args.length > 0) {
/*  28 */       sender.sendMessage(ChatColor.GOLD + "Mythic Mob Types found containing " + args[0] + ": ");
/*     */     } else {
/*  30 */       sender.sendMessage(ChatColor.GOLD + "Mobs Loaded (" + mobs.size() + "): ");
/*     */     } 
/*     */     
/*  33 */     int i = 0;
/*  34 */     String s = "";
/*  35 */     for (MythicMob mm : mobs) {
/*  36 */       if (args.length > 0) {
/*  37 */         if (mm.getInternalName().contains(args[0])) {
/*  38 */           String[] parts = mm.getInternalName().split(args[0]);
/*  39 */           if (parts.length == 2) {
/*  40 */             s = s + ChatColor.GREEN + parts[0] + ChatColor.DARK_GREEN + args[0] + ChatColor.GREEN + parts[1] + ChatColor.GRAY + ", ";
/*     */           }
/*  42 */           if (parts.length == 1) {
/*  43 */             s = s + ChatColor.GREEN + parts[0] + ChatColor.DARK_GREEN + args[0] + ChatColor.GRAY + ", ";
/*     */           }
/*  45 */           if (parts.length == 0) {
/*  46 */             s = s + ChatColor.GOLD + args[0] + ChatColor.GRAY + ", ";
/*     */           }
/*  48 */           i++;
/*     */         } 
/*     */       } else {
/*  51 */         s = s + ChatColor.GREEN + mm.getInternalName() + ChatColor.GRAY + ", ";
/*     */       } 
/*  53 */       if (i == 25) {
/*  54 */         i = 0;
/*  55 */         sender.sendMessage(s);
/*  56 */         s = "";
/*     */       } 
/*     */     } 
/*  59 */     List<MythicMobStack> stacks = new ArrayList<>(MythicMobs.inst().getMobManager().getMobStacks());
/*  60 */     Collections.sort(stacks);
/*  61 */     for (MythicMobStack el : stacks) {
/*  62 */       if (args.length > 0) {
/*  63 */         if (el.getName().contains(args[0])) {
/*  64 */           String[] parts = el.getName().split(args[0]);
/*  65 */           if (parts.length == 2) {
/*  66 */             s = s + ChatColor.GREEN + parts[0] + ChatColor.DARK_GREEN + args[0] + ChatColor.GREEN + parts[1] + ChatColor.GRAY + ", ";
/*     */           }
/*  68 */           if (parts.length == 1) {
/*  69 */             s = s + ChatColor.GREEN + parts[0] + ChatColor.DARK_GREEN + args[0] + ChatColor.GRAY + ", ";
/*     */           }
/*  71 */           if (parts.length == 0) {
/*  72 */             s = s + ChatColor.GOLD + args[0] + ChatColor.GRAY + ", ";
/*     */           }
/*  74 */           i++;
/*     */         } 
/*     */       } else {
/*  77 */         s = s + ChatColor.RED + el.getName() + ChatColor.GRAY + ", ";
/*     */       } 
/*  79 */       if (i == 25) {
/*  80 */         i = 0;
/*  81 */         sender.sendMessage(s);
/*  82 */         s = "";
/*     */       } 
/*     */     } 
/*  85 */     sender.sendMessage(s);
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/*  96 */     return "mythicmobs.command.mobs.list";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 106 */     return "list";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 111 */     return new String[] { "l" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\mobs\ListCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */