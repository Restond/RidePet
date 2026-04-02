/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillGCD
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 12 */     String[] base = skill.split(" ");
/* 13 */     int ticks = 20;
/*    */     
/*    */     try {
/* 16 */       ticks = Integer.parseInt(base[1]);
/* 17 */     } catch (Exception exception) {}
/*    */     
/* 19 */     MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l).setGlobalCooldown(ticks);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillGCD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */