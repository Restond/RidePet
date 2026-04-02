/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Projectile;
/*    */ import org.bukkit.entity.ThrownPotion;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.bukkit.inventory.meta.PotionMeta;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ 
/*    */ public class SkillShootPotion
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 17 */     if (target == null)
/* 18 */       return;  String[] base = skill.split(" ");
/* 19 */     String[] data = base[1].split(":");
/*    */     
/* 21 */     String pType = data[0];
/* 22 */     float pDuration = Float.parseFloat(data[1]);
/* 23 */     int pLevel = Integer.parseInt(data[2]) - 1;
/* 24 */     float velocity = Float.parseFloat(data[3]);
/*    */     
/* 26 */     ItemStack potion = new ItemStack(Material.POTION);
/* 27 */     PotionMeta pm = (PotionMeta)potion.getItemMeta();
/* 28 */     pm.addCustomEffect(new PotionEffect(PotionEffectType.getByName(pType), (int)pDuration, pLevel), true);
/* 29 */     potion.setItemMeta((ItemMeta)pm);
/*    */     
/* 31 */     Projectile projectile = l.launchProjectile(ThrownPotion.class);
/* 32 */     ((ThrownPotion)projectile).setItem(potion);
/*    */     
/* 34 */     projectile.setVelocity(l.getLocation().getDirection().multiply(velocity));
/* 35 */     projectile.setBounce(false);
/* 36 */     projectile.setShooter((ProjectileSource)l);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillShootPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */