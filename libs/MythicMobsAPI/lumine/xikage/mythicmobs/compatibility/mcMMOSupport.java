/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.gmail.nossr50.api.ExperienceAPI;
/*    */ import com.gmail.nossr50.mcMMO;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class mcMMOSupport
/*    */ {
/* 15 */   private mcMMO mcMMO = (mcMMO)Bukkit.getPluginManager().getPlugin("mcMMO");
/*    */ 
/*    */   
/*    */   public mcMMO getmcMMO() {
/* 19 */     return this.mcMMO;
/*    */   }
/*    */   
/*    */   public void giveExp(Player player, float amount, String type) {
/* 23 */     if (player == null)
/* 24 */       return;  if (amount == 0.0F)
/*    */       return; 
/* 26 */     ExperienceAPI.addRawXP(player, type, amount);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\mcMMOSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */