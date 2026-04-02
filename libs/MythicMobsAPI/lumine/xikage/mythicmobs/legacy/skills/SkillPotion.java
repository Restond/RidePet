/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillPotion
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 16 */     String[] base = skill.split(" ");
/* 17 */     String[] data = base[1].split(":");
/*    */     
/* 19 */     int radius = Integer.parseInt(data[0]);
/* 20 */     String pType = data[1];
/* 21 */     int pDuration = Integer.parseInt(data[2]);
/* 22 */     int pLevel = Integer.parseInt(data[3]) - 1;
/*    */     
/* 24 */     PotionEffect pe = new PotionEffect(PotionEffectType.getByName(pType), pDuration, pLevel);
/* 25 */     if (pe != null)
/* 26 */       if (radius > 0) {
/*    */         
/* 28 */         for (Player p : SkillHelper.getPlayersInRadius(l, radius))
/*    */         {
/* 30 */           p.addPotionEffect(pe);
/*    */         }
/*    */       } else {
/* 33 */         if (target == null)
/* 34 */           return;  target.addPotionEffect(pe);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */