/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.compatibility.CompatibilityManager;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectBleed
/*    */ {
/*    */   public static void DoEffect(LivingEntity target, String skill) {
/* 13 */     if (CompatibilityManager.EffectLib == null)
/*    */       return; 
/* 15 */     String[] data = skill.split(":");
/*    */     
/* 17 */     int ticks = 5;
/* 18 */     int interval = 20;
/*    */     
/* 20 */     if (data[0].matches("[0-9]*")) {
/* 21 */       ticks = Integer.parseInt(data[0]);
/*    */     }
/*    */     
/* 24 */     if (data.length > 1 && 
/* 25 */       data[1].matches("[0-9]*")) {
/* 26 */       interval = Integer.parseInt(data[1]);
/*    */     }
/*    */ 
/*    */     
/* 30 */     MythicMobs.debug(4, "Executing EffectLib effect BLEED");
/* 31 */     CompatibilityManager.EffectLib.doBleedEffect(target, ticks, interval);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectBleed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */