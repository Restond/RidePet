/*    */ package lumine.utils.prompts;
/*    */ 
/*    */ import com.comphenix.protocol.PacketType;
/*    */ import com.comphenix.protocol.ProtocolLibrary;
/*    */ import com.comphenix.protocol.ProtocolManager;
/*    */ import com.comphenix.protocol.events.PacketContainer;
/*    */ import com.comphenix.protocol.events.PacketListener;
/*    */ import com.comphenix.protocol.wrappers.BlockPosition;
/*    */ import com.comphenix.protocol.wrappers.nbt.NbtCompound;
/*    */ import com.comphenix.protocol.wrappers.nbt.NbtFactory;
/*    */ import io.lumine.utils.Players;
/*    */ import io.lumine.utils.plugin.LoaderUtils;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SignPrompt
/*    */ {
/* 28 */   private static final ProtocolManager PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager();
/*    */   
/*    */   public static void openPrompt(Player player, List<String> startLines, Predicate<List<String>> callback) {
/*    */     try {
/* 32 */       Location location = player.getLocation().clone();
/* 33 */       location.setY(255.0D);
/* 34 */       Players.sendBlockChange(player, location, Material.WALL_SIGN);
/*    */       
/* 36 */       BlockPosition position = new BlockPosition(location.toVector());
/*    */       
/* 38 */       PacketContainer openSign = new PacketContainer(PacketType.Play.Server.OPEN_SIGN_EDITOR);
/* 39 */       openSign.getBlockPositionModifier().write(0, position);
/* 40 */       PROTOCOL_MANAGER.sendServerPacket(player, openSign);
/*    */       
/* 42 */       PacketContainer writeToSign = new PacketContainer(PacketType.Play.Server.TILE_ENTITY_DATA);
/* 43 */       writeToSign.getBlockPositionModifier().write(0, position);
/* 44 */       writeToSign.getIntegers().write(0, Integer.valueOf(9));
/* 45 */       NbtCompound compound = NbtFactory.ofCompound("");
/*    */       
/* 47 */       for (int i = 0; i < 4; i++) {
/* 48 */         compound.put("Text" + (i + 1), "{\"text\":\"" + ((startLines.size() > i) ? startLines.get(i) : "") + "\"}");
/*    */       }
/*    */       
/* 51 */       compound.put("id", "minecraft:sign");
/* 52 */       compound.put("x", position.getX());
/* 53 */       compound.put("y", position.getY());
/* 54 */       compound.put("z", position.getZ());
/*    */       
/* 56 */       writeToSign.getNbtModifier().write(0, compound);
/* 57 */       PROTOCOL_MANAGER.sendServerPacket(player, writeToSign);
/*    */       
/* 59 */       PROTOCOL_MANAGER.addPacketListener((PacketListener)new Object((Plugin)LoaderUtils.getPlugin(), new PacketType[] { PacketType.Play.Client.UPDATE_SIGN }, player, callback, startLines, location));
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
/*    */     }
/* 85 */     catch (Exception e) {
/* 86 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\prompts\SignPrompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */