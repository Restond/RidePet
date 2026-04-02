/*    */ package lumine.utils.network.messaging.lilypad;
/*    */ 
/*    */ import io.lumine.utils.network.messaging.InstanceData;
/*    */ import io.lumine.utils.network.messaging.Messenger;
/*    */ import javax.annotation.Nonnull;
/*    */ import lilypad.client.connect.api.Connect;
/*    */ import org.bukkit.entity.Player;
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
/*    */ public interface LilyPad
/*    */   extends Messenger, InstanceData
/*    */ {
/*    */   @Nonnull
/*    */   Connect getConnect();
/*    */   
/*    */   void redirectPlayer(@Nonnull String paramString1, @Nonnull String paramString2);
/*    */   
/*    */   default void redirectPlayer(@Nonnull String serverId, @Nonnull Player player) {
/* 64 */     redirectPlayer(serverId, player.getName());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\network\messaging\lilypad\LilyPad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */