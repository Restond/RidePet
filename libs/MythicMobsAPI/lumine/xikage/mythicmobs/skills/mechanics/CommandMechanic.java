/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "command", aliases = {"cmd"}, description = "Executes a command")
/*    */ public class CommandMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill, INoTargetSkill {
/*    */   protected PlaceholderString command;
/*    */   protected boolean asCaster = false;
/*    */   protected boolean asOP = false;
/*    */   
/*    */   public CommandMechanic(String skill, MythicLineConfig mlc) {
/* 26 */     super(skill, mlc);
/* 27 */     this.ASYNC_SAFE = false;
/*    */     
/* 29 */     this.command = PlaceholderString.of(mlc.getString(new String[] { "command", "cmd", "c" }));
/*    */     
/* 31 */     this.asCaster = mlc.getBoolean(new String[] { "ascaster", "caster", "ac", "sudo", "asmob" }, false);
/* 32 */     this.asOP = mlc.getBoolean(new String[] { "asop", "op" }, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 37 */     String parsedCommand = this.command.get((PlaceholderMeta)data, target);
/* 38 */     if (this.asCaster) {
/* 39 */       if (data.getCaster().getEntity().isPlayer()) {
/* 40 */         Player player = (Player)BukkitAdapter.adapt(data.getCaster().getEntity());
/* 41 */         if (this.asOP == true) {
/* 42 */           boolean op = player.isOp();
/* 43 */           player.setOp(true);
/* 44 */           Bukkit.getServer().dispatchCommand((CommandSender)player, parsedCommand);
/* 45 */           player.setOp(op);
/*    */         } else {
/* 47 */           Bukkit.getServer().dispatchCommand((CommandSender)player, parsedCommand);
/*    */         } 
/*    */       } else {
/* 50 */         MythicMobs.inst().server().dispatchCommand("minecraft:execute " + data.getCaster().getEntity().getUniqueId() + " ~ ~ ~ " + parsedCommand);
/*    */       } 
/*    */     } else {
/* 53 */       MythicMobs.inst().server().dispatchCommand(parsedCommand);
/*    */     } 
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 60 */     String parsedCommand = this.command.get((PlaceholderMeta)data);
/* 61 */     if (this.asCaster) {
/* 62 */       if (data.getCaster().getEntity().isPlayer()) {
/* 63 */         Player player = (Player)BukkitAdapter.adapt(data.getCaster().getEntity());
/* 64 */         if (this.asOP == true) {
/* 65 */           boolean op = player.isOp();
/* 66 */           player.setOp(true);
/* 67 */           player.performCommand(parsedCommand);
/* 68 */           player.setOp(op);
/*    */         } else {
/* 70 */           player.performCommand(parsedCommand);
/*    */         } 
/*    */       } else {
/* 73 */         MythicMobs.inst().server().dispatchCommand("minecraft:execute " + data.getCaster().getEntity().getUniqueId() + " ~ ~ ~ " + parsedCommand);
/*    */       } 
/*    */     } else {
/* 76 */       MythicMobs.inst().server().dispatchCommand(parsedCommand);
/*    */     } 
/* 78 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\CommandMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */