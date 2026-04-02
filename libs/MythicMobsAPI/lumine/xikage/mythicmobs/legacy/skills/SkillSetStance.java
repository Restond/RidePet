/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillSetStance
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 12 */     String[] base = skill.split(" ");
/* 13 */     String stance = base[1];
/*    */     
/* 15 */     MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l).setStance(stance);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSetStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */