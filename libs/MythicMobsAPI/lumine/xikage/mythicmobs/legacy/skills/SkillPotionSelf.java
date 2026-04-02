/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillPotionSelf
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 13 */     String[] base = skill.split(" ");
/* 14 */     String[] data = base[1].split(":");
/*    */     
/* 16 */     String pType = data[0];
/* 17 */     int pDuration = Integer.parseInt(data[1]);
/* 18 */     int pLevel = Integer.parseInt(data[2]) - 1;
/*    */     
/* 20 */     PotionEffect pe = new PotionEffect(PotionEffectType.getByName(pType), pDuration, pLevel);
/* 21 */     l.addPotionEffect(pe);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillPotionSelf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */