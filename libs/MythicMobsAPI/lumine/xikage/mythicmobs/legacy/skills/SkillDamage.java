/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.EntityEffect;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ 
/*    */ 
/*    */ public class SkillDamage
/*    */ {
/*    */   public static boolean noloop = false;
/*    */   
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 19 */     String[] base = skill.split(" ");
/* 20 */     String[] data = base[1].split(":");
/*    */     
/* 22 */     int radius = Integer.parseInt(data[0]);
/* 23 */     double damage = Double.parseDouble(data[1]);
/* 24 */     boolean ignorearmor = (data.length > 2) ? Boolean.parseBoolean(data[2]) : false;
/*    */     
/* 26 */     if (radius > 0) {
/* 27 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/* 28 */         DoDamage(l, (LivingEntity)p, damage, ignorearmor);
/*    */       }
/*    */     }
/* 31 */     else if (target != null) {
/* 32 */       DoDamage(l, target, damage, ignorearmor);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void DoDamage(LivingEntity l, LivingEntity p, double damage, boolean ignorearmor) {
/* 38 */     if (p.isDead() || p.getHealth() <= 0.0D)
/* 39 */       return;  if (noloop == true)
/*    */       return; 
/* 41 */     noloop = true;
/*    */     
/* 43 */     EntityDamageByEntityEvent event = new EntityDamageByEntityEvent((Entity)l, (Entity)p, EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage);
/* 44 */     Bukkit.getServer().getPluginManager().callEvent((Event)event);
/* 45 */     noloop = false;
/*    */     
/* 47 */     if (event.isCancelled()) {
/*    */       return;
/*    */     }
/* 50 */     double edamage = event.getDamage();
/*    */     
/* 52 */     if (ignorearmor == true) {
/* 53 */       if (p.getHealth() - edamage < 1.0D) {
/* 54 */         p.setLastDamageCause((EntityDamageEvent)event);
/* 55 */         p.setHealth(0.0D);
/* 56 */         p.damage(1.0D, (Entity)l);
/*    */       } else {
/* 58 */         p.setHealth(p.getHealth() - edamage);
/* 59 */         p.playEffect(EntityEffect.HURT);
/*    */       } 
/*    */     } else {
/* 62 */       p.damage(edamage, (Entity)l);
/* 63 */       p.setLastDamageCause((EntityDamageEvent)event);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */