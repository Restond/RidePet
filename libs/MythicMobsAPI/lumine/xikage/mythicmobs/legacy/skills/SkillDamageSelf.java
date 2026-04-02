/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import java.lang.reflect.Array;
/*    */ import org.bukkit.EntityEffect;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillDamageSelf
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 14 */     String[] base = skill.split(" ");
/* 15 */     String[] data = base[1].split(":");
/*    */     
/* 17 */     double damage = Double.parseDouble(data[0]);
/* 18 */     boolean ignorearmor = (Array.getLength(data) > 1) ? Boolean.parseBoolean(data[1]) : false;
/*    */     
/* 20 */     if (ignorearmor == true) {
/* 21 */       if (l.getHealth() - damage < 1.0D) {
/* 22 */         l.setHealth(0.0D);
/* 23 */         l.damage(1.0D);
/*    */       } else {
/* 25 */         l.setHealth(l.getHealth() - damage);
/* 26 */         l.playEffect(EntityEffect.HURT);
/*    */       } 
/*    */     } else {
/* 29 */       l.damage(damage);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillDamageSelf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */