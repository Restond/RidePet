/*    */ package lumine.utils.commands;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.lang.reflect.Field;
/*    */ import javax.annotation.Nonnull;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandMap;
/*    */ import org.bukkit.command.PluginCommand;
/*    */ import org.bukkit.command.TabCompleter;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CommandRegistrar
/*    */ {
/* 21 */   private static CommandMap commandMap = null;
/*    */   private static Constructor<?> commandConstructor;
/*    */   private static Field owningPluginField;
/*    */   
/*    */   static {
/*    */     try {
/* 27 */       commandConstructor = PluginCommand.class.getDeclaredConstructor(new Class[] { String.class, Plugin.class });
/* 28 */       commandConstructor.setAccessible(true);
/* 29 */     } catch (Exception e) {
/* 30 */       e.printStackTrace();
/*    */     } 
/*    */     try {
/* 33 */       owningPluginField = PluginCommand.class.getDeclaredField("owningPlugin");
/* 34 */       owningPluginField.setAccessible(true);
/* 35 */     } catch (Exception e) {
/* 36 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public static <T extends CommandExecutor> T registerCommand(@Nonnull Plugin plugin, @Nonnull T command, @Nonnull String... aliases) {
/* 50 */     Preconditions.checkArgument((aliases.length != 0), "No aliases");
/* 51 */     for (String alias : aliases) {
/* 52 */       PluginCommand cmd = Bukkit.getServer().getPluginCommand(alias);
/* 53 */       if (cmd == null) {
/*    */         try {
/* 55 */           cmd = (PluginCommand)commandConstructor.newInstance(new Object[] { alias, plugin });
/* 56 */         } catch (Exception ex) {
/* 57 */           throw new RuntimeException("Could not register command: " + alias);
/*    */         } 
/*    */ 
/*    */         
/* 61 */         if (commandMap == null) {
/*    */           try {
/* 63 */             PluginManager pluginManager = Bukkit.getServer().getPluginManager();
/* 64 */             Field commandMapField = pluginManager.getClass().getDeclaredField("commandMap");
/* 65 */             commandMapField.setAccessible(true);
/* 66 */             commandMap = (CommandMap)commandMapField.get(pluginManager);
/* 67 */           } catch (Exception ex) {
/* 68 */             throw new RuntimeException("Could not register command: " + alias);
/*    */           } 
/*    */         }
/*    */         
/* 72 */         commandMap.register(plugin.getDescription().getName(), (Command)cmd);
/*    */       } else {
/*    */         
/*    */         try {
/* 76 */           owningPluginField.set(cmd, plugin);
/* 77 */         } catch (Exception e) {
/* 78 */           e.printStackTrace();
/*    */         } 
/*    */       } 
/*    */       
/* 82 */       cmd.setExecutor((CommandExecutor)command);
/* 83 */       if (command instanceof TabCompleter) {
/* 84 */         cmd.setTabCompleter((TabCompleter)command);
/*    */       } else {
/* 86 */         cmd.setTabCompleter(null);
/*    */       } 
/*    */     } 
/* 89 */     return command;
/*    */   }
/*    */   
/*    */   private CommandRegistrar() {
/* 93 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\commands\CommandRegistrar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */