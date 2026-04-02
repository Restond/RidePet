/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.legacy.LegacyMythicSkill;
/*    */ import io.lumine.xikage.mythicmobs.legacy.LegacySkillHandler;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class SkillSkill
/*    */ {
/* 19 */   public static Long cooldownTimer = Long.valueOf(0L);
/*    */   
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target, SkillTrigger trigger) {
/* 22 */     String[] base = skill.split(" ");
/*    */     
/* 24 */     LegacyMythicSkill ms = getMetaSkill(base[1]);
/*    */     
/* 26 */     if (ms != null) {
/* 27 */       if (onCooldown(l, base[1]))
/* 28 */         return;  if (ms.hasConditions() && 
/* 29 */         !checkConditions(l, ms.conditions))
/*    */         return; 
/* 31 */       setCooldown(l, base[1], ms.cooldown);
/* 32 */       LegacySkillHandler.ExecuteMetaSkills(ms.skills, (Entity)l, target, trigger);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static LegacyMythicSkill getMetaSkill(String s) {
/* 37 */     for (LegacyMythicSkill ms : MythicMobs.inst().getSkillManager().getLegacySkills()) {
/* 38 */       if (ms.SkillName.equals(s)) {
/* 39 */         return ms;
/*    */       }
/*    */     } 
/* 42 */     return null;
/*    */   }
/*    */   public static boolean onCooldown(LivingEntity l, String skill) {
/* 45 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/* 46 */     if (am == null) return true;
/*    */     
/* 48 */     Long next = (Long)am.cooldowns.get(skill + l.getEntityId());
/* 49 */     if (next != null && 
/* 50 */       next.longValue() >= cooldownTimer.longValue()) {
/* 51 */       return true;
/*    */     }
/*    */ 
/*    */     
/* 55 */     return false;
/*    */   }
/*    */   public static void setCooldown(LivingEntity l, String skill, double cooldown) {
/* 58 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l);
/* 59 */     if (am == null)
/*    */       return; 
/* 61 */     if (cooldown > 0.0D) {
/* 62 */       am.cooldowns.put(skill + l.getEntityId(), Long.valueOf((long)(cooldownTimer.longValue() + cooldown * 20.0D - 2.0D)));
/*    */     } else {
/* 64 */       am.cooldowns.remove(skill + l.getEntityId());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean checkConditions(LivingEntity l, List<String> conditions) {
/* 71 */     for (String strCondition : conditions) {
/*    */       
/* 73 */       String[] split = strCondition.split(" ");
/* 74 */       String conditionData = null;
/*    */       
/* 76 */       if (split.length > 1) {
/* 77 */         conditionData = split[1];
/*    */       }
/*    */       
/* 80 */       if (!SCondition.getSpawningConditionByName(split[0]).check(l.getLocation(), l, conditionData)) return false;
/*    */     
/*    */     } 
/* 83 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */