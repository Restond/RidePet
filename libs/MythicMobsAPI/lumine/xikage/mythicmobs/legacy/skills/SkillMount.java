/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ public class SkillMount
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 13 */     String[] base = skill.split(" ");
/*    */     
/* 15 */     String mount = base[1];
/*    */     
/* 17 */     if (MythicMobs.inst().getMobManager().getMythicMob(mount) != null) {
/* 18 */       MythicMobs.inst().getMobManager(); AbstractEntity mm = MythicMobs.inst().getMobManager().spawnMob(mount, l.getLocation(), MobManager.getMythicMobLevel(l)).getEntity();
/*    */       
/* 20 */       mm.setPassenger(BukkitAdapter.adapt((Entity)l));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillMount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */