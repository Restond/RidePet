/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.gmail.revision9000.VEnchants.Enchants.EnchantPlus;
/*    */ import com.gmail.revision9000.VEnchants.Enchants.EnchantTypes;
/*    */ import com.gmail.revision9000.VEnchants.Utils.MainEnchants;
/*    */ import java.util.List;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantsPlusSupport
/*    */ {
/*    */   public ItemStack setEnchants(ItemStack stack, List<String> enchants) {
/* 14 */     if (enchants == null) return stack;
/*    */     
/* 16 */     for (String s : enchants) {
/* 17 */       if (s.contains(":")) {
/* 18 */         String[] part = s.split(":");
/*    */         
/* 20 */         if (part.length > 1) {
/* 21 */           stack = applyEnchant(stack, Integer.parseInt(part[1]), part[0]); continue;
/*    */         } 
/* 23 */         stack = applyEnchant(stack, 1, part[0]);
/*    */       } 
/*    */     } 
/*    */     
/* 27 */     return stack;
/*    */   }
/*    */   public ItemStack applyEnchant(ItemStack is, int level, String ench) {
/* 30 */     List<EnchantPlus> cel = EnchantTypes.getByClass(ench.toUpperCase());
/* 31 */     if (cel == null) return is; 
/* 32 */     for (EnchantPlus ce : cel) {
/* 33 */       MainEnchants.applyEnchant(is, level, ce);
/*    */     }
/*    */     
/* 36 */     return is;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\EnchantsPlusSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */