/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class ConditionLevel extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/* 12 */     MythicMobs.inst().getMobManager(); int level = MobManager.getMythicMobLevel(e);
/*    */     
/* 14 */     if (MythicUtil.matchNumber(data, level)) {
/* 15 */       return true;
/*    */     }
/* 17 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */