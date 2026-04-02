/*    */ package lumine.xikage.mythicmobs.legacy;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ 
/*    */ public class LegacyMythicTimerSkill
/*    */ {
/*    */   public String skill;
/*    */   public int interval;
/*    */   
/*    */   public LegacyMythicTimerSkill(String skill, int interval) {
/* 11 */     if (skill.contains("'")) {
/* 12 */       String[] split = skill.split("'");
/* 13 */       if (split.length > 2) {
/* 14 */         skill = split[0] + "'" + SkillString.unparseMessageSpecialChars(split[1]) + "'" + split[2];
/*    */       } else {
/* 16 */         skill = split[0] + "'" + SkillString.unparseMessageSpecialChars(split[1]) + "'";
/*    */       } 
/*    */     } 
/*    */     
/* 20 */     this.skill = skill;
/* 21 */     this.interval = interval;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\LegacyMythicTimerSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */