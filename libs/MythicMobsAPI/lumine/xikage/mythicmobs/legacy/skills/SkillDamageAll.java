/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.legacy.skills.SkillDamage;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import java.lang.reflect.Array;
/*    */ import org.bukkit.EntityEffect;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillDamageAll
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 16 */     String[] base = skill.split(" ");
/* 17 */     String[] data = base[1].split(":");
/*    */     
/* 19 */     int radius = Integer.parseInt(data[0]);
/* 20 */     double damage = Double.parseDouble(data[1]);
/* 21 */     boolean ignorearmor = (Array.getLength(data) > 2) ? Boolean.parseBoolean(data[2]) : false;
/*    */     
/* 23 */     if (radius > 0) {
/* 24 */       for (LivingEntity p : SkillHelper.getLivingEntitiesInRadius(l, radius)) {
/* 25 */         if (p.isDead() || p.getHealth() <= 0.0D)
/* 26 */           continue;  if (ignorearmor == true) {
/* 27 */           if (p.getHealth() - damage < 1.0D) {
/* 28 */             p.setHealth(0.0D);
/* 29 */             p.damage(1.0D); continue;
/*    */           } 
/* 31 */           p.setHealth(p.getHealth() - damage);
/* 32 */           p.playEffect(EntityEffect.HURT);
/*    */           continue;
/*    */         } 
/* 35 */         p.damage(damage);
/*    */       } 
/*    */     } else {
/*    */       
/* 39 */       SkillDamage.DoDamage(l, target, damage, ignorearmor);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillDamageAll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */