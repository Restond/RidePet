/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionBiome extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/*  9 */     String[] biomes = data.split(",");
/* 10 */     for (String b : biomes) {
/* 11 */       if (b.toLowerCase().equals(l.getBlock().getBiome().toString().toLowerCase())) {
/* 12 */         return true;
/*    */       }
/*    */     } 
/* 15 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */