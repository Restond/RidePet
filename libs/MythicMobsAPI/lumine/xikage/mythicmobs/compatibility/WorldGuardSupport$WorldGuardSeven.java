/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.sk89q.worldedit.bukkit.BukkitAdapter;
/*    */ import com.sk89q.worldguard.WorldGuard;
/*    */ import com.sk89q.worldguard.internal.platform.WorldGuardPlatform;
/*    */ import com.sk89q.worldguard.protection.managers.RegionManager;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.WorldGuardSupport;
/*    */ import java.util.List;
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
/*    */ 
/*    */ public class WorldGuardSeven
/*    */   implements WorldGuardSupport.WorldGuardAdapter
/*    */ {
/* 70 */   private WorldGuardPlatform worldguard = WorldGuard.getInstance().getPlatform();
/*    */   
/*    */   public WorldGuardSeven(Plugin worldguard) {}
/*    */   
/*    */   public boolean isLocationInRegions(AbstractLocation loc, String region) {
/* 75 */     Location l = BukkitAdapter.adapt(loc);
/*    */ 
/*    */     
/* 78 */     RegionManager regionManager = this.worldguard.getRegionContainer().get(BukkitAdapter.adapt(l.getWorld()));
/* 79 */     List<String> set = regionManager.getApplicableRegionsIDs(BukkitAdapter.asBlockVector(l));
/* 80 */     String[] regions = region.split(",");
/*    */     
/* 82 */     for (String str : set) {
/* 83 */       for (String r : regions) {
/*    */         
/* 85 */         if (r.equals(str)) {
/* 86 */           return true;
/*    */         }
/*    */       } 
/*    */     } 
/* 90 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\WorldGuardSupport$WorldGuardSeven.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */