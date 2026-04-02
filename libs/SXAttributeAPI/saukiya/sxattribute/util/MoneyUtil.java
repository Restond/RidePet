/*    */ package saukiya.sxattribute.util;
/*    */ 
/*    */ import net.milkbowl.vault.economy.Economy;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.plugin.RegisteredServiceProvider;
/*    */ 
/*    */ public class MoneyUtil {
/*  9 */   private static Economy economy = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setup() throws NullPointerException {
/* 17 */     RegisteredServiceProvider<Economy> registeredServiceProvider = Bukkit.getServicesManager().getRegistration(Economy.class);
/* 18 */     if (registeredServiceProvider == null) throw new NullPointerException(); 
/* 19 */     economy = (Economy)registeredServiceProvider.getProvider();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static double get(OfflinePlayer player) {
/* 29 */     return economy.getBalance(player);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean has(OfflinePlayer player, double money) {
/* 40 */     return (money <= get(player));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void give(OfflinePlayer player, double money) {
/* 50 */     economy.depositPlayer(player, money);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void take(OfflinePlayer player, double money) {
/* 60 */     economy.withdrawPlayer(player, money);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribut\\util\MoneyUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */