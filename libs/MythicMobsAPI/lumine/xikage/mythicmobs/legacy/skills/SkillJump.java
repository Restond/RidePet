/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillJump
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 11 */     String[] base = skill.split(" ");
/* 12 */     String[] data = base[1].split(":");
/*    */     
/* 14 */     double vStr = Double.parseDouble(data[0]) / 10.0D;
/*    */ 
/*    */     
/* 17 */     if (data.length > 1);
/*    */ 
/*    */ 
/*    */     
/* 21 */     if (target != null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 27 */     Vector v = l.getVelocity();
/*    */     
/* 29 */     v.setY(v.getY() + vStr);
/*    */     
/* 31 */     l.setVelocity(v);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */