/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import me.clip.placeholderapi.PlaceholderAPI;
/*    */ import me.clip.placeholderapi.external.EZPlaceholderHook;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class PlaceholderAPISupport
/*    */   extends EZPlaceholderHook {
/*    */   private final MythicMobs core;
/*    */   
/*    */   public PlaceholderAPISupport(MythicMobs core) {
/* 15 */     super((Plugin)core, "mythicmobs");
/*    */     
/* 17 */     this.core = core;
/*    */   }
/*    */   
/*    */   public String parse(String string) {
/* 21 */     return PlaceholderAPI.setPlaceholders(null, string);
/*    */   }
/*    */   
/*    */   public String parse(String string, AbstractPlayer player) {
/* 25 */     return PlaceholderAPI.setPlaceholders((Player)player.getBukkitEntity(), string);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String onPlaceholderRequest(Player player, String identifier) {
/* 33 */     if (player == null) {
/* 34 */       return "";
/*    */     }
/*    */     
/* 37 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\PlaceholderAPISupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */