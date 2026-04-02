/*    */ package lumine.xikage.mythicmobs.drops.droppables;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.DropMetadata;
/*    */ import io.lumine.xikage.mythicmobs.drops.IIntangibleDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CommandDrop
/*    */   extends Drop
/*    */   implements IIntangibleDrop
/*    */ {
/*    */   protected PlaceholderString command;
/*    */   protected boolean asCaster = false;
/*    */   protected boolean asOP = false;
/*    */   
/*    */   public CommandDrop(String line, MythicLineConfig config) {
/* 26 */     super(line, config);
/*    */     
/* 28 */     this.command = PlaceholderString.of(config.getString(new String[] { "command", "cmd", "c" }));
/*    */     
/* 30 */     this.asCaster = config.getBoolean(new String[] { "ascaster", "caster", "ac", "sudo", "asmob" }, false);
/* 31 */     this.asOP = config.getBoolean(new String[] { "asop", "op" }, false);
/*    */   }
/*    */   
/*    */   public CommandDrop(String line, MythicLineConfig config, double amount) {
/* 35 */     super(line, config, amount);
/*    */     
/* 37 */     this.command = PlaceholderString.of(config.getString(new String[] { "command", "cmd", "c" }));
/*    */     
/* 39 */     this.asCaster = config.getBoolean(new String[] { "ascaster", "caster", "ac", "sudo", "asmob" }, false);
/* 40 */     this.asOP = config.getBoolean(new String[] { "asop", "op" }, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void giveDrop(AbstractPlayer target, DropMetadata data) {
/* 45 */     String parsedCommand = this.command.get((PlaceholderMeta)data, (AbstractEntity)target);
/*    */     
/* 47 */     parsedCommand = parsedCommand.replace("<drop.amount>", String.valueOf(data.getAmount()));
/*    */     
/* 49 */     if (this.asCaster) {
/* 50 */       if (data.getCaster().getEntity().isPlayer()) {
/* 51 */         Player player = (Player)BukkitAdapter.adapt(data.getCaster().getEntity());
/* 52 */         if (this.asOP == true) {
/* 53 */           boolean op = player.isOp();
/* 54 */           player.setOp(true);
/* 55 */           Bukkit.getServer().dispatchCommand((CommandSender)player, parsedCommand);
/* 56 */           player.setOp(op);
/*    */         } else {
/* 58 */           Bukkit.getServer().dispatchCommand((CommandSender)player, parsedCommand);
/*    */         } 
/*    */       } else {
/* 61 */         MythicMobs.inst().server().dispatchCommand("minecraft:execute " + data.getCaster().getEntity().getUniqueId() + " ~ ~ ~ " + parsedCommand);
/*    */       } 
/*    */     } else {
/* 64 */       MythicMobs.inst().server().dispatchCommand(parsedCommand);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\drops\droppables\CommandDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */