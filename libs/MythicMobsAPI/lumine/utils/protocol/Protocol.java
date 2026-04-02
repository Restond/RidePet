/*     */ package lumine.utils.protocol;
/*     */ 
/*     */ import com.comphenix.protocol.PacketType;
/*     */ import com.comphenix.protocol.ProtocolLibrary;
/*     */ import com.comphenix.protocol.ProtocolManager;
/*     */ import com.comphenix.protocol.events.ListenerPriority;
/*     */ import com.comphenix.protocol.events.PacketContainer;
/*     */ import io.lumine.utils.events.functional.protocol.ProtocolSubscriptionBuilder;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import javax.annotation.Nonnull;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Protocol
/*     */ {
/*     */   @Nonnull
/*     */   public static ProtocolSubscriptionBuilder subscribe(@Nonnull PacketType... packets) {
/*  55 */     return ProtocolSubscriptionBuilder.newBuilder(packets);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static ProtocolSubscriptionBuilder subscribe(@Nonnull ListenerPriority priority, @Nonnull PacketType... packets) {
/*  67 */     return ProtocolSubscriptionBuilder.newBuilder(priority, packets);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public static ProtocolManager manager() {
/*  77 */     return ProtocolLibrary.getProtocolManager();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendPacket(@Nonnull Player player, @Nonnull PacketContainer packet) {
/*     */     try {
/*  88 */       manager().sendServerPacket(player, packet);
/*  89 */     } catch (InvocationTargetException e) {
/*  90 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void broadcastPacket(@Nonnull PacketContainer packet) {
/* 100 */     manager().broadcastServerPacket(packet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void broadcastPacket(@Nonnull Iterable<Player> players, @Nonnull PacketContainer packet) {
/* 110 */     for (Player player : players)
/* 111 */       sendPacket(player, packet); 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\protocol\Protocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */