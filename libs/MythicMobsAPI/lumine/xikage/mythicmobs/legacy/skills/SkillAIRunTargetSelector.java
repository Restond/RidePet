/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillAIRunTargetSelector
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 15 */     String[] temp = skill.split("'");
/* 16 */     String msg = SkillString.parseMessageSpecialChars(temp[1]);
/*    */     
/* 18 */     ArrayList<String> goal = new ArrayList<>();
/*    */     
/* 20 */     goal.add(msg);
/*    */     
/* 22 */     MythicMobs.inst().getVolatileCodeHandler().getAIHandler().addTargetGoals(l, goal);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillAIRunTargetSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */