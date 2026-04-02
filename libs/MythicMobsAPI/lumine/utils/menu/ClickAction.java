/*    */ package lumine.utils.menu;
/*    */ 
/*    */ import io.lumine.utils.menu.Menu;
/*    */ import java.util.function.BiConsumer;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ClickAction
/*    */ {
/*    */   public static <T> BiConsumer<T, Player> openMenu(Menu<T> menu) {
/* 10 */     return (impl, player) -> paramMenu.open(player, impl);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\menu\ClickAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */