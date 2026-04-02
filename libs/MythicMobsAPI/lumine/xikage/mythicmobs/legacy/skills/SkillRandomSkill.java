/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.LegacyMythicSkill;
/*    */ import io.lumine.xikage.mythicmobs.legacy.LegacySkillHandler;
/*    */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*    */ import java.lang.reflect.Array;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class SkillRandomSkill
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target, SkillTrigger trigger) {
/* 19 */     String[] base = skill.split(" ");
/* 20 */     String[] metaskills = base[1].split(",");
/*    */     
/* 22 */     int num = Array.getLength(metaskills);
/*    */     
/* 24 */     String mskill = metaskills[MythicMobs.r.nextInt(num)];
/*    */     
/* 26 */     LegacyMythicSkill ms = SkillSkill.getMetaSkill(mskill);
/*    */     
/* 28 */     if (ms != null)
/*    */     {
/* 30 */       if (SkillSkill.onCooldown(l, mskill) == true) {
/* 31 */         String skillsNew = "randomskill ";
/*    */         
/* 33 */         for (String mN : metaskills) {
/* 34 */           if (mN != mskill) {
/* 35 */             skillsNew = skillsNew + mN + ",";
/*    */           }
/*    */         } 
/* 38 */         skillsNew = skillsNew + " >0 1";
/* 39 */         ExecuteSkill(l, skillsNew, target, trigger);
/*    */       } else {
/* 41 */         LegacySkillHandler.ExecuteMetaSkills(ms.skills, (Entity)l, target, trigger);
/* 42 */         SkillSkill.setCooldown(l, mskill, ms.cooldown);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillRandomSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */