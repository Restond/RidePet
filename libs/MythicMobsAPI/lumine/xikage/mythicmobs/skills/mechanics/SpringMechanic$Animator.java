/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.ConfigManager;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.SpringMechanic;
/*    */ import org.bukkit.Material;
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
/*    */ class Animator
/*    */   implements Runnable
/*    */ {
/*    */   private AbstractLocation location;
/*    */   
/*    */   public Animator(AbstractLocation location) {
/* 69 */     this.location = location;
/* 70 */     Schedulers.sync().runLater(this, SpringMechanic.access$000(paramSpringMechanic));
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     try {
/* 76 */       BukkitAdapter.adapt(this.location).getBlock().setType(Material.AIR, true);
/* 77 */     } catch (Exception ex) {
/* 78 */       if (ConfigManager.debugLevel > 0)
/* 79 */         ex.printStackTrace(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SpringMechanic$Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */