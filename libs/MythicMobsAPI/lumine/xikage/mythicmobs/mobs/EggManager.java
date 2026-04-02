/*    */ package lumine.xikage.mythicmobs.mobs;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.VolatileMaterial;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EggManager
/*    */ {
/*    */   public static boolean giveMythicEgg(MythicMob mm, Player player, int amount) {
/* 20 */     ItemStack egg = new ItemStack(VolatileMaterial.SPAWN_EGG, amount);
/* 21 */     ItemMeta eggMeta = egg.getItemMeta();
/*    */     
/* 23 */     String display = (mm.getDisplayName() != null) ? mm.getDisplayName().toString() : mm.getInternalName();
/*    */     
/* 25 */     eggMeta.setDisplayName(ChatColor.WHITE + "Spawn " + ChatColor.translateAlternateColorCodes('&', display));
/* 26 */     eggMeta.addEnchant(Enchantment.DURABILITY, 10, true);
/*    */     
/* 28 */     List<String> eggLore = new ArrayList<>();
/* 29 */     eggLore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "A Mythical Egg that can");
/* 30 */     eggLore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "be used to resurrect a");
/* 31 */     eggLore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + mm.getInternalName());
/*    */     
/* 33 */     eggMeta.setLore(eggLore);
/* 34 */     egg.setItemMeta(eggMeta);
/*    */     
/* 36 */     player.getInventory().addItem(new ItemStack[] { egg });
/*    */     
/* 38 */     return true;
/*    */   }
/*    */   
/*    */   public static MythicMob getMythicMobFromEgg(String s) {
/* 42 */     for (MythicMob MM : MythicMobs.inst().getMobManager().getMobTypes()) {
/* 43 */       String search = ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + MM.getInternalName();
/* 44 */       if (search.equals(s)) {
/* 45 */         return MM;
/*    */       }
/*    */     } 
/* 48 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\EggManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */