/*    */ package lumine.utils.menu;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import io.lumine.utils.menu.Icon;
/*    */ import io.lumine.utils.menu.IconBuilder;
/*    */ import java.util.Map;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CosmeticIcon<T>
/*    */ {
/*    */   public static <T> Icon<T> create(Material material) {
/* 17 */     return IconBuilder.create().material(material).name(ChatColor.GOLD + "" + ChatColor.BOLD + " ").build();
/*    */   }
/*    */   
/*    */   public static <T> Map<Integer, Icon<T>> createMap(Material material, int... keys) {
/* 21 */     Map<Integer, Icon<T>> list = Maps.newHashMap();
/*    */     
/* 23 */     for (int i : keys) {
/* 24 */       list.put(Integer.valueOf(i), create(material));
/*    */     }
/* 26 */     return list;
/*    */   }
/*    */   
/*    */   private CosmeticIcon() {
/* 30 */     throw new UnsupportedOperationException("This class cannot be instantiated");
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumin\\utils\menu\CosmeticIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */