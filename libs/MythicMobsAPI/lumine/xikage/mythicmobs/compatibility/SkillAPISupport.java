/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.sucy.skill.SkillAPI;
/*    */ import com.sucy.skill.api.enums.ExpSource;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillAPISupport
/*    */ {
/* 14 */   private SkillAPI skillapi = (SkillAPI)Bukkit.getPluginManager().getPlugin("SkillAPI");
/*    */ 
/*    */   
/*    */   public SkillAPI getSkillAPI() {
/* 18 */     return this.skillapi;
/*    */   }
/*    */   
/*    */   public void giveExp(Player player, int amount) {
/* 22 */     if (player == null)
/* 23 */       return;  if (amount == 0)
/*    */       return; 
/* 25 */     SkillAPI.getPlayerAccountData((OfflinePlayer)player).getActiveData().giveExp(amount, ExpSource.MOB);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\SkillAPISupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */