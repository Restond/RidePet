/*     */ package lumine.xikage.mythicmobs.commands.utility;
/*     */ 
/*     */ import io.lumine.utils.chat.ColorString;
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*     */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GetTargetInfoCommand
/*     */   extends Command<MythicMobs>
/*     */ {
/*     */   public GetTargetInfoCommand(Command<MythicMobs> parent) {
/*  27 */     super(parent);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  33 */     Player p = (Player)sender;
/*     */     
/*  35 */     LivingEntity l = MythicUtil.getTargetedEntity(p);
/*     */     
/*  37 */     if (l == null) {
/*  38 */       CommandHelper.sendError(sender, "You must target a valid entity!");
/*  39 */       return true;
/*     */     } 
/*     */     
/*  42 */     sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Information About Target Mob:");
/*  43 */     sender.sendMessage(ChatColor.GOLD + "UUID: " + ChatColor.GRAY + l.getUniqueId());
/*     */     
/*  45 */     if (MythicMobs.inst().getMobManager().isActiveMob(l.getUniqueId())) {
/*  46 */       sender.sendMessage(ChatColor.GOLD + "Is Active MythicMob: " + ChatColor.GRAY + "TRUE");
/*     */     } else {
/*  48 */       sender.sendMessage(ChatColor.GOLD + "Is Active MythicMob: " + ChatColor.GRAY + "FALSE");
/*     */     } 
/*  50 */     if (MythicMobs.inst().getMobManager().isIgnoredEntity(l.getUniqueId())) {
/*  51 */       sender.sendMessage(ChatColor.GOLD + "Is In Void List: " + ChatColor.GRAY + "TRUE");
/*     */     } else {
/*  53 */       sender.sendMessage(ChatColor.GOLD + "Is In Void List: " + ChatColor.GRAY + "FALSE");
/*     */     } 
/*     */     
/*  56 */     if (MythicMobs.inst().getMobManager().isActiveMob(l.getUniqueId())) {
/*  57 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/*  58 */       am.remountSpawner();
/*     */       
/*  60 */       sender.sendMessage(ChatColor.GOLD + "EntityType: " + ChatColor.GRAY + am.getType().getEntityType());
/*  61 */       sender.sendMessage(ChatColor.GOLD + "MythicType: " + ChatColor.GRAY + am.getType().getInternalName());
/*  62 */       sender.sendMessage(ChatColor.GOLD + "Level: " + ChatColor.GRAY + am.getLevel());
/*  63 */       sender.sendMessage(ChatColor.GOLD + "TypeHealth: " + ChatColor.GRAY + am.getType().getBaseHealth());
/*  64 */       sender.sendMessage(ChatColor.GOLD + "+LevelHealth: " + ChatColor.GRAY + am.getType().getPerLevelHealth());
/*  65 */       sender.sendMessage(ChatColor.GOLD + "TargetHealth: " + ChatColor.GRAY + am.getEntity().getHealth());
/*  66 */       sender.sendMessage(ChatColor.GOLD + "TypeDamage: " + ChatColor.GRAY + am.getType().getBaseDamage());
/*  67 */       sender.sendMessage(ChatColor.GOLD + "TargetDamage: " + ChatColor.GRAY + am.getDamage());
/*  68 */       sender.sendMessage(ChatColor.GOLD + "+LevelPower: " + ChatColor.GRAY + am.getType().getPerLevelPower());
/*  69 */       sender.sendMessage(ChatColor.GOLD + "TargetPower: " + ChatColor.GRAY + am.getPower());
/*     */       
/*  71 */       sender.sendMessage(ChatColor.GOLD + "Current Stance: " + ChatColor.GRAY + am.getStance());
/*     */       
/*  73 */       if (am.getSpawner() != null) {
/*  74 */         sender.sendMessage(ChatColor.GOLD + "FromSpawner: " + ChatColor.AQUA + am.getSpawner().getInternalName());
/*     */       }
/*  76 */       if (am.getOwner().isPresent()) {
/*  77 */         Player player = Bukkit.getPlayer(am.getOwner().get());
/*     */         
/*  79 */         if (player == null) {
/*  80 */           sender.sendMessage(ColorString.get("&6Owner: &f" + ((UUID)am.getOwner().get()).toString()));
/*     */         } else {
/*  82 */           sender.sendMessage(ColorString.get("&6Owner: &f" + player.getName()));
/*     */         } 
/*     */       } 
/*  85 */       if (am.getParent() != null) {
/*  86 */         SkillCaster caster = am.getParent();
/*     */         
/*  88 */         if (caster instanceof ActiveMob) {
/*  89 */           sender.sendMessage(ColorString.get("&6Parent: &f" + ((ActiveMob)caster).getType().getInternalName() + " &7(" + caster.getEntity().getUniqueId() + ")"));
/*     */         } else {
/*  91 */           AbstractEntity ent = caster.getEntity();
/*     */           
/*  93 */           if (ent.isPlayer()) {
/*  94 */             sender.sendMessage(ColorString.get("&6Parent: &f" + ((Player)ent.getBukkitEntity()).getName()));
/*     */           } else {
/*  96 */             sender.sendMessage(ColorString.get("&6Parent: &f" + caster.getEntity().getUniqueId()));
/*     */           } 
/*     */         } 
/*     */       } 
/* 100 */       if (am.getFaction() != null) {
/* 101 */         sender.sendMessage(ColorString.get("&6Faction&7: " + am.getFaction()));
/*     */       }
/* 103 */       if (am.isDead()) {
/* 104 */         sender.sendMessage(ChatColor.GOLD + "Marked as Dead: " + ChatColor.GRAY + "TRUE");
/*     */       } else {
/* 106 */         sender.sendMessage(ChatColor.GOLD + "Marked as Dead: " + ChatColor.GRAY + "FALSE");
/*     */       } 
/* 108 */       if (am.getEntity().isValid()) {
/* 109 */         sender.sendMessage(ChatColor.GOLD + "Marked as Valid: " + ChatColor.GRAY + "TRUE");
/*     */       } else {
/* 111 */         sender.sendMessage(ChatColor.GOLD + "Marked as Valid: " + ChatColor.GRAY + "FALSE");
/*     */       } 
/* 113 */       if (am.hasThreatTable()) {
/* 114 */         sender.sendMessage(ChatColor.GOLD + "Using ThreatTable: " + ChatColor.GRAY + "TRUE");
/* 115 */         if (am.getThreatTable().inCombat()) {
/* 116 */           sender.sendMessage(ChatColor.GOLD + "Top Threat Target: " + ChatColor.GRAY + am.getThreatTable().getTopThreatHolder().getName() + " (" + am.getThreatTable().getTopTargetThreat() + ")");
/* 117 */           sender.sendMessage(ChatColor.GOLD + "Total Threat: " + ChatColor.GRAY + am.getThreatTable().getTotalThreat());
/* 118 */           sender.sendMessage(ChatColor.GOLD + "-- Threat Targets: ");
/*     */           
/* 120 */           for (AbstractEntity le : am.getThreatTable().getAllThreatTargets()) {
/* 121 */             sender.sendMessage(ChatColor.GOLD + "* " + le.getName() + " (" + am.getThreatTable().getThreat(le) + ")");
/*     */           }
/*     */         } else {
/* 124 */           sender.sendMessage(ChatColor.GOLD + "-- Mob Not In Combat");
/*     */         } 
/*     */       } else {
/* 127 */         sender.sendMessage(ChatColor.GOLD + "Using ThreatTable: " + ChatColor.GRAY + "FALSE");
/*     */       } 
/*     */     } 
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/* 135 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/* 140 */     return "mythicmobs.command.utilities.gettargetinfo";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 150 */     return "gettargetinfo";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 155 */     return new String[] { "gti" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\command\\utility\GetTargetInfoCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */