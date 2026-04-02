/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import java.util.Scanner;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGuardSupport
/*    */ {
/* 25 */   private Plugin instance = null;
/* 26 */   private int pluginVersion = 0;
/*    */   private WorldGuardAdapter adapter;
/*    */   
/*    */   public WorldGuardSupport() {
/* 30 */     this.instance = Bukkit.getPluginManager().getPlugin("WorldGuard");
/*    */     
/* 32 */     Scanner s = (new Scanner(this.instance.getResource("plugin.yml"))).useDelimiter("\\A");
/*    */     
/*    */     try {
/* 35 */       while (s.hasNext()) {
/* 36 */         String s1 = s.nextLine();
/* 37 */         if (s1.startsWith("version")) {
/* 38 */           this.pluginVersion = Integer.parseInt(s1.substring(10, 11));
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } finally {
/* 43 */       s.close();
/*    */     } 
/*    */     
/* 46 */     if (this.pluginVersion < 7) {
/*    */       try {
/* 48 */         this.adapter = (WorldGuardAdapter)new WorldGuardSix(this, this.instance);
/* 49 */       } catch (Exception ex) {
/* 50 */         throw new IllegalArgumentException("Invalid WorldGuard version");
/*    */       } 
/*    */     } else {
/* 53 */       this.adapter = (WorldGuardAdapter)new WorldGuardSeven(this, this.instance);
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isLocationInRegions(AbstractLocation loc, String region) {
/* 58 */     return this.adapter.isLocationInRegions(loc, region);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean LocationAllowsMobSpawning(Location l) {
/* 99 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\WorldGuardSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */