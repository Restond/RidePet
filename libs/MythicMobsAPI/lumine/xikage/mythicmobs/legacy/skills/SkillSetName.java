/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillSetName
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 12 */     String[] temp = skill.split("'");
/* 13 */     String name = temp[1];
/*    */     
/* 15 */     name = SkillString.parseMobString(name, l, target);
/*    */     
/* 17 */     l.setCustomName(name);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSetName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */