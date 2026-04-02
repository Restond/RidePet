/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import java.util.Collection;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConditionHasPotionEffect
/*    */   extends SCondition
/*    */ {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 16 */     String[] s = data.split(":");
/*    */     
/* 18 */     String type = s[0].toLowerCase();
/* 19 */     String lvl = null;
/*    */     
/* 21 */     if (s.length > 1) {
/* 22 */       lvl = s[1];
/*    */     }
/*    */     
/* 25 */     Collection<PotionEffect> pe = e.getActivePotionEffects();
/*    */     
/* 27 */     for (PotionEffect p : pe) {
/* 28 */       if (p.getType().toString().toLowerCase().equals(type)) {
/* 29 */         if (lvl == null) {
/* 30 */           return true;
/*    */         }
/* 32 */         if (MythicUtil.matchNumber(lvl, p.getAmplifier())) {
/* 33 */           return true;
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 39 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionHasPotionEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */