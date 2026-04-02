/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import net.milkbowl.vault.economy.Economy;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.RegisteredServiceProvider;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VaultSupport
/*    */ {
/*    */   public boolean enabled = false;
/*    */   public Economy economy;
/*    */   
/*    */   public VaultSupport() {
/* 18 */     RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
/*    */     
/* 20 */     if (economyProvider != null) {
/* 21 */       this.economy = (Economy)economyProvider.getProvider();
/*    */       
/* 23 */       if (this.economy != null) {
/* 24 */         this.enabled = true;
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void giveMoney(Player p, double amount) {
/*    */     try {
/* 31 */       this.economy.depositPlayer((OfflinePlayer)p, amount);
/* 32 */     } catch (Exception e) {
/* 33 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void giveMoney(AbstractPlayer player, double amount) {
/* 38 */     Player p = (Player)player.getBukkitEntity();
/*    */     try {
/* 40 */       this.economy.depositPlayer((OfflinePlayer)p, amount);
/* 41 */     } catch (Exception e) {
/* 42 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void takeMoney(AbstractPlayer player, double amount) {
/* 47 */     Player p = (Player)player.getBukkitEntity();
/*    */     try {
/* 49 */       this.economy.withdrawPlayer((OfflinePlayer)p, amount);
/* 50 */     } catch (Exception e) {
/* 51 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean hasMoney(AbstractPlayer player, double amount) {
/* 56 */     Player p = (Player)player.getBukkitEntity();
/*    */     try {
/* 58 */       return this.economy.has((OfflinePlayer)p, amount);
/* 59 */     } catch (Exception e) {
/* 60 */       e.printStackTrace();
/*    */       
/* 62 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\VaultSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */