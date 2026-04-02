/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.LegacyMythicSkill;
/*    */ import io.lumine.xikage.mythicmobs.legacy.LegacySkillHandler;
/*    */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ @Deprecated
/*    */ public class SkillSkillRadiusAll {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, SkillTrigger trigger) {
/* 13 */     String[] base = skill.split(" ");
/* 14 */     String[] split = base[1].split(":");
/*    */     
/* 16 */     int radius = Integer.parseInt(split[0]);
/* 17 */     String name = split[1];
/*    */     
/* 19 */     LegacyMythicSkill ms = SkillSkill.getMetaSkill(name);
/*    */     
/* 21 */     if (ms != null) {
/* 22 */       if (SkillSkill.onCooldown(l, base[1]))
/* 23 */         return;  if (ms.hasConditions() && 
/* 24 */         !SkillSkill.checkConditions(l, ms.conditions))
/*    */         return; 
/* 26 */       SkillSkill.setCooldown(l, base[1], ms.cooldown);
/*    */       
/* 28 */       for (Entity p : l.getNearbyEntities(radius, radius, radius)) {
/* 29 */         if (p instanceof LivingEntity)
/* 30 */           LegacySkillHandler.ExecuteMetaSkills(ms.skills, (Entity)l, (LivingEntity)p, trigger); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSkillRadiusAll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */