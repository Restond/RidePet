/*    */ package lumine.xikage.mythicmobs.legacy;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillString;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ @Deprecated
/*    */ public class LegacyMythicSkill
/*    */ {
/*    */   public List<String> skills;
/*    */   public List<String> conditions;
/*    */   private boolean hasConditions = false;
/*    */   public String SkillName;
/*    */   public String file;
/*    */   public double cooldown;
/*    */   
/*    */   @Deprecated
/*    */   public LegacyMythicSkill(String SkillName, String file, List<String> skills, double cooldown, List<String> conditions) {
/* 19 */     this.SkillName = SkillName;
/* 20 */     this.file = file;
/*    */     
/* 22 */     List<String> skillsP = new ArrayList<>();
/* 23 */     for (String skill : skills) {
/* 24 */       if (skill.contains("'")) {
/* 25 */         String[] split = skill.split("'");
/* 26 */         if (split.length > 2) {
/* 27 */           skill = split[0] + "'" + SkillString.unparseMessageSpecialChars(split[1]) + "'" + split[2];
/*    */         } else {
/* 29 */           skill = split[0] + "'" + SkillString.unparseMessageSpecialChars(split[1]) + "'";
/*    */         } 
/*    */       } 
/* 32 */       if (skill.contains("\"")) {
/* 33 */         String[] split = skill.split("\"");
/* 34 */         if (split.length > 2) {
/* 35 */           skill = split[0] + "\"" + SkillString.unparseMessageSpecialChars(split[1]) + "\"" + split[2];
/*    */         } else {
/* 37 */           skill = split[0] + "\"" + SkillString.unparseMessageSpecialChars(split[1]) + "\"";
/*    */         } 
/*    */       } 
/* 40 */       skillsP.add(skill);
/*    */     } 
/*    */     
/* 43 */     this.skills = skillsP;
/* 44 */     this.cooldown = cooldown;
/* 45 */     this.conditions = conditions;
/* 46 */     if (conditions.size() > 0) this.hasConditions = true; 
/*    */   }
/*    */   public boolean hasConditions() {
/* 49 */     return this.hasConditions;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\LegacyMythicSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */