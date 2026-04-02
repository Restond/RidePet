/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillThrow
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 14 */     String[] base = skill.split(" ");
/* 15 */     String[] data = base[1].split(":");
/*    */     
/* 17 */     int radius = Integer.parseInt(data[0]);
/* 18 */     float strength = Float.parseFloat(data[1]) / 10.0F;
/* 19 */     float strengthY = Float.parseFloat(data[2]) / 10.0F;
/*    */ 
/*    */     
/* 22 */     if (radius > 0) {
/*    */       
/* 24 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/* 25 */         Vector V = p.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(strength);
/*    */         
/* 27 */         if (strength == 0.0F) {
/* 28 */           V.setY(strengthY);
/*    */         } else {
/* 30 */           V.setY(strengthY + V.getY());
/*    */         } 
/*    */         
/* 33 */         p.setVelocity(V);
/*    */       } 
/*    */     } else {
/* 36 */       if (target == null)
/* 37 */         return;  Vector V = target.getLocation().toVector().subtract(l.getLocation().toVector()).normalize().multiply(strength);
/*    */       
/* 39 */       if (strength == 0.0F) {
/* 40 */         V.setY(strengthY);
/*    */       } else {
/* 42 */         V.setY(strengthY + V.getY());
/*    */       } 
/*    */       
/* 45 */       target.setVelocity(V);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillThrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */