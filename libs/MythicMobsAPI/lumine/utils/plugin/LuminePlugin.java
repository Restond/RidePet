/*    */ package lumine.utils.plugin;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.TabCompleter;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.ServicePriority;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LuminePlugin
/*    */   extends JavaPlugin
/*    */ {
/*    */   public void onDisable() {}
/*    */   
/*    */   @Nonnull
/*    */   protected <T extends CommandExecutor & TabCompleter> void registerCommand(String cmd, T handler) {
/* 23 */     getCommand(cmd).setExecutor((CommandExecutor)handler);
/* 24 */     getCommand(cmd).setTabCompleter((TabCompleter)handler);
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   public <T extends Listener> void registerListener(@Nonnull T listener) {
/* 29 */     Preconditions.checkNotNull(listener, "listener");
/* 30 */     getServer().getPluginManager().registerEvents((Listener)listener, (Plugin)this);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public <T> T getService(@Nonnull Class<T> service) {
/* 35 */     return (T)getServer().getServicesManager().load(service);
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   public <T> T provideService(@Nonnull Class<T> clazz, @Nonnull T instance, @Nonnull ServicePriority priority) {
/* 40 */     getServer().getServicesManager().register(clazz, instance, (Plugin)this, priority);
/* 41 */     return instance;
/*    */   }
/*    */   
/*    */   @Nonnull
/*    */   public <T> T provideService(@Nonnull Class<T> clazz, @Nonnull T instance) {
/* 46 */     Preconditions.checkNotNull(clazz, "clazz");
/* 47 */     Preconditions.checkNotNull(instance, "instance");
/* 48 */     return provideService(clazz, instance, ServicePriority.Normal);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public <T> T getPlugin(@Nonnull String name, @Nonnull Class<T> pluginClass) {
/* 54 */     Preconditions.checkNotNull(name, "name");
/* 55 */     Preconditions.checkNotNull(pluginClass, "pluginClass");
/* 56 */     return (T)getServer().getPluginManager().getPlugin(name);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\plugin\LuminePlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */