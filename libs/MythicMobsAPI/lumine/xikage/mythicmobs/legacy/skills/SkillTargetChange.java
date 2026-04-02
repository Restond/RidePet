/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class SkillTargetChange
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 16 */     String[] base = skill.split(" ");
/* 17 */     String[] data = base[1].split(":");
/*    */     
/* 19 */     int radius = Integer.parseInt(data[0]);
/*    */     
/* 21 */     if (radius > 0) {
/* 22 */       List<Player> players = SkillHelper.getPlayersInRadius(l, radius);
/*    */       
/* 24 */       if (players.size() == 0)
/*    */         return; 
/* 26 */       Player newtarget = players.get(MythicMobs.r.nextInt(players.size()));
/*    */       
/* 28 */       MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)l).setTarget((AbstractEntity)BukkitAdapter.adapt(newtarget));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillTargetChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */