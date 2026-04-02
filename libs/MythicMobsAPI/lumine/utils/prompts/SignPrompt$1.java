/*    */ package lumine.utils.prompts;
/*    */ 
/*    */ import com.comphenix.protocol.PacketType;
/*    */ import com.comphenix.protocol.events.PacketAdapter;
/*    */ import com.comphenix.protocol.events.PacketContainer;
/*    */ import com.comphenix.protocol.events.PacketEvent;
/*    */ import com.comphenix.protocol.events.PacketListener;
/*    */ import io.lumine.utils.Players;
/*    */ import io.lumine.utils.prompts.SignPrompt;
/*    */ import io.lumine.utils.tasks.Scheduler;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
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
/*    */ final class null
/*    */   extends PacketAdapter
/*    */ {
/*    */   null(Plugin x0, PacketType[] x1) {
/* 59 */     super(x0, x1);
/*    */   }
/*    */   public void onPacketReceiving(PacketEvent event) {
/* 62 */     if (event.getPlayer().getUniqueId().equals(player.getUniqueId())) {
/* 63 */       PacketContainer container = event.getPacket();
/*    */       
/* 65 */       String[] newLines = (String[])container.getStringArrays().read(0);
/* 66 */       List<String> lines = new ArrayList<>(Arrays.asList(newLines));
/*    */       
/* 68 */       boolean pass = callback.test(lines);
/*    */       
/* 70 */       if (!pass)
/*    */       {
/* 72 */         Scheduler.runLaterSync(() -> { if (paramPlayer.isOnline()) SignPrompt.openPrompt(paramPlayer, paramList, paramPredicate);  }1L);
/*    */       }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 80 */       SignPrompt.access$000().removePacketListener((PacketListener)this);
/* 81 */       Players.sendBlockChange(player, location, Material.AIR);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\prompts\SignPrompt$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */