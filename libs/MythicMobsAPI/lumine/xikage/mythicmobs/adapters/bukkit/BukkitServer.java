/*     */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractBossBar;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*     */ import io.lumine.xikage.mythicmobs.adapters.ServerInterface;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitBossBar;
/*     */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitWorld;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class BukkitServer
/*     */   implements ServerInterface
/*     */ {
/*     */   public List<AbstractWorld> getWorlds() {
/*  26 */     List<AbstractWorld> wl = new ArrayList<>();
/*     */     
/*  28 */     for (World w : Bukkit.getWorlds()) {
/*  29 */       wl.add(new BukkitWorld(w));
/*     */     }
/*  31 */     return wl;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispatchCommand(String s) {
/*  36 */     Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), s);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractPlayer getPlayer(UUID uuid) {
/*  41 */     Player player = Bukkit.getPlayer(uuid);
/*     */     
/*  43 */     if (player == null) {
/*  44 */       return null;
/*     */     }
/*  46 */     return BukkitAdapter.adapt(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPlayer getPlayer(String name) {
/*  52 */     Player player = Bukkit.getPlayerExact(name);
/*     */     
/*  54 */     if (player == null) {
/*  55 */       return null;
/*     */     }
/*  57 */     return BukkitAdapter.adapt(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEntity getEntity(UUID uuid) {
/*  63 */     Entity entity = Bukkit.getEntity(uuid);
/*     */     
/*  65 */     if (entity == null) {
/*  66 */       return null;
/*     */     }
/*  68 */     return BukkitAdapter.adapt(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractWorld getWorld(UUID uuid) {
/*  74 */     World world = Bukkit.getWorld(uuid);
/*     */     
/*  76 */     if (world == null) {
/*  77 */       return null;
/*     */     }
/*  79 */     return BukkitAdapter.adapt(world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractWorld getWorld(String name) {
/*  85 */     World world = Bukkit.getWorld(name);
/*     */     
/*  87 */     if (world == null) {
/*  88 */       return null;
/*     */     }
/*  90 */     return (AbstractWorld)new BukkitWorld(world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<AbstractPlayer> getOnlinePlayers() {
/*  96 */     List<AbstractPlayer> pl = new ArrayList<>();
/*     */     
/*  98 */     for (Player p : Bukkit.getServer().getOnlinePlayers()) {
/*  99 */       pl.add(BukkitAdapter.adapt(p));
/*     */     }
/* 101 */     return pl;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractLocation newLocation(AbstractWorld w, double x, double y, double z) {
/* 106 */     Location l = new Location(BukkitAdapter.adapt(w), x, y, z);
/*     */     
/* 108 */     return BukkitAdapter.adapt(l);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidBiome(Object o) {
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractBossBar createBossBar(String title, AbstractBossBar.BarColor color, AbstractBossBar.BarStyle style) {
/* 119 */     return (AbstractBossBar)new BukkitBossBar(title, color, style);
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */