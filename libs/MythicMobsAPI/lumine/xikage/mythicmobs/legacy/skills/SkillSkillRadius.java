/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ import io.lumine.xikage.mythicmobs.legacy.LegacyMythicSkill;
/*    */ import io.lumine.xikage.mythicmobs.legacy.LegacySkillHandler;
/*    */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @Deprecated
/*    */ public class SkillSkillRadius {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, SkillTrigger trigger) {
/* 14 */     String[] base = skill.split(" ");
/* 15 */     String[] split = base[1].split(":");
/*    */     
/* 17 */     int radius = Integer.parseInt(split[0]);
/* 18 */     String name = split[1];
/*    */     
/* 20 */     LegacyMythicSkill ms = SkillSkill.getMetaSkill(name);
/*    */     
/* 22 */     if (ms != null) {
/* 23 */       if (SkillSkill.onCooldown(l, base[1]))
/* 24 */         return;  if (ms.hasConditions() && 
/* 25 */         !SkillSkill.checkConditions(l, ms.conditions))
/*    */         return; 
/* 27 */       SkillSkill.setCooldown(l, base[1], ms.cooldown);
/*    */       
/* 29 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius))
/* 30 */         LegacySkillHandler.ExecuteMetaSkills(ms.skills, (Entity)l, (LivingEntity)p, trigger); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSkillRadius.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */