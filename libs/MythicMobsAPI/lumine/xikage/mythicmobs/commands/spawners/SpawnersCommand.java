/*     */ package lumine.xikage.mythicmobs.commands.spawners;
/*     */ import io.lumine.utils.chat.ColorString;
/*     */ import io.lumine.utils.commands.Command;
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.commands.CommandHelper;
/*     */ import io.lumine.xikage.mythicmobs.commands.spawners.ActivateCommand;
/*     */ import io.lumine.xikage.mythicmobs.commands.spawners.AddConditionCommand;
/*     */ import io.lumine.xikage.mythicmobs.commands.spawners.CreateCommand;
/*     */ import io.lumine.xikage.mythicmobs.commands.spawners.PasteCommand;
/*     */ import io.lumine.xikage.mythicmobs.commands.spawners.ResetTimersCommand;
/*     */ import io.lumine.xikage.mythicmobs.commands.spawners.SetCommand;
/*     */ import io.lumine.xikage.mythicmobs.commands.spawners.UndoCommand;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ public class SpawnersCommand extends Command<MythicMobs> {
/*     */   public SpawnersCommand(Command<MythicMobs> parent) {
/*  17 */     super(parent);
/*     */     
/*  19 */     addSubCommands(new Command[] { (Command)new ActivateCommand(this), (Command)new AddConditionCommand(this), (Command)new CopyCommand(this), (Command)new CreateCommand(this), (Command)new CutCommand(this), (Command)new InfoCommand(this), (Command)new FindCommand(this), (Command)new MoveCommand(this), (Command)new PasteCommand(this), (Command)new RemoveCommand(this), (Command)new RemoveConditionCommand(this), (Command)new ResetTimersCommand(this), (Command)new SetCommand(this), (Command)new UndoCommand(this) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, String[] args) {
/*  40 */     int page = 1;
/*  41 */     if (args.length > 0 && args[0].equals("2")) {
/*  42 */       page = 2;
/*     */     }
/*     */     
/*  45 */     if (page == 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  58 */       String[] messages = { ColorString.get("  &7Arguments: &6[] &f= Required&7, &3<> &f= Optional"), "", ColorString.get("&e/mm s &fcreate &6[name] &6[mob] &3<w,x,y,z> &7► &7&oCreate a mob spawner."), ColorString.get("&e/mm s &fdelete &6[name] &7► &7&oRemove a mob spawner."), ColorString.get("&e/mm s &finfo &6[name] &7► &7&oShow info about a spawner."), ColorString.get("&e/mm s &ffind &3<radius> &7► &7&oList nearby spawners"), ColorString.get("&e/mm s &factivate &6[name] &7► &7&o Force-ticks a spawner."), ColorString.get("&e/mm s &fset &6[name] &6[attribute] &6[value] &7► &7&oModify spawner attributes."), ColorString.get("&e/mm s &faddcondition &6[name] &6[condition] &6[value] &7► &7&oAdds a condition to a spawner."), ColorString.get("&e/mm s &fremovecondition &6[name] &6[condition] &7► &7&oRemoves a condition from a spawner."), "", ColorString.get("&6&L ** Page 1 of 2") };
/*     */       
/*  60 */       CommandHelper.sendCommandMessage(sender, messages);
/*  61 */     } else if (page == 2) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  71 */       String[] messages = { ColorString.get("  &7Arguments: &6[] &f= Required&7, &3<> &f= Optional"), "", ColorString.get("&e/mm s &fmove &6[name] &7► &7&oMoves the spawner to the location you are looking at."), ColorString.get("&e/mm s &fcopy &6[name] &6[copyName] &7► &7&oTurns the block you're looking at into a copy of a mob spawner."), ColorString.get("&e/mm s &fcut &6[name] &7► &7&oCuts the given spawners and saves them to the clipboard."), ColorString.get("&e/mm s &fpaste &6[name] &7► &7&oPastes spawners in your clipboard based on your relative location."), ColorString.get("&e/mm s &fundo &7► &7&oUndo the last clipboard-related change you've done (clipboard must still have spawners in it for this to work)"), "", ColorString.get("&6&L ** Page 2 of 2") };
/*     */       
/*  73 */       CommandHelper.sendCommandMessage(sender, messages);
/*     */     } else {
/*  75 */       String[] messages = { ChatColor.RED + "Page not found." };
/*     */ 
/*     */       
/*  78 */       CommandHelper.sendCommandMessage(sender, messages);
/*     */     } 
/*     */     
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, String[] args) {
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPermissionNode() {
/*  91 */     return "mythicmobs.command.spawners";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isConsoleFriendly() {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 101 */     return "spawners";
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAliases() {
/* 106 */     return new String[] { "s", "sp" };
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\commands\spawners\SpawnersCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */