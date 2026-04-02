/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.IMetaSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.Skill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Optional;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "randomskill", aliases = {"randommeta"}, description = "Executes a random metaskill")
/*    */ public class RandomSkillMechanic
/*    */   extends SkillMechanic
/*    */   implements IMetaSkill {
/* 17 */   protected ArrayList<String> skills = new ArrayList<>();
/*    */   
/*    */   public RandomSkillMechanic(String skill, MythicLineConfig mlc) {
/* 20 */     super(skill, mlc);
/* 21 */     this.target_creative = true;
/*    */     
/* 23 */     String s = mlc.getString("skills");
/* 24 */     s = mlc.getString("s", s);
/* 25 */     s = mlc.getString("metas", s);
/* 26 */     s = mlc.getString("m", s);
/*    */     
/* 28 */     String[] ss = s.split(",");
/*    */     
/* 30 */     for (String sx : ss) {
/* 31 */       this.skills.add(sx);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 39 */     ArrayList<String> sl = (ArrayList<String>)this.skills.clone();
/*    */     
/* 41 */     while (sl.size() > 0) {
/*    */       String skill;
/*    */       
/* 44 */       if (sl.size() > 1) {
/* 45 */         skill = sl.get(MythicMobs.r.nextInt(sl.size()));
/*    */       } else {
/* 47 */         skill = sl.get(0);
/*    */       } 
/*    */       
/* 50 */       Optional<Skill> maybeSkill = MythicMobs.inst().getSkillManager().getSkill(skill);
/*    */       
/* 52 */       if (!maybeSkill.isPresent()) {
/* 53 */         sl.remove(skill);
/*    */         continue;
/*    */       } 
/* 56 */       Skill ms = maybeSkill.get();
/* 57 */       if (ms.usable(data, null)) {
/* 58 */         ms.execute(data);
/* 59 */         return true;
/*    */       } 
/* 61 */       sl.remove(skill);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 66 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\RandomSkillMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */