/*    */ package lumine.xikage.mythicmobs.legacy.conditions;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.legacy.conditions.SCondition;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ 
/*    */ public class ConditionScoreTarget extends SCondition {
/*    */   public boolean check(Location l, LivingEntity e, String data) {
/*    */     AbstractEntity target;
/*    */     int score;
/* 20 */     ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance((Entity)e);
/* 21 */     if (am == null) return true;
/*    */     
/* 23 */     Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
/*    */     
/* 25 */     String[] split = data.split(";");
/*    */     
/* 27 */     String obj = split[0];
/* 28 */     String val = split[1];
/*    */     
/* 30 */     Objective objective = scoreboard.getObjective(obj);
/*    */     
/* 32 */     if (objective == null) {
/* 33 */       objective = scoreboard.registerNewObjective(obj, "dummy");
/*    */     }
/*    */ 
/*    */     
/* 37 */     if (am.hasThreatTable()) {
/* 38 */       target = am.getThreatTable().getTopThreatHolder();
/*    */     } else {
/* 40 */       if (!(e instanceof Creature)) return true;
/*    */       
/* 42 */       Creature c = (Creature)e;
/*    */       
/* 44 */       if (c.getTarget() == null) return false;
/*    */       
/* 46 */       target = BukkitAdapter.adapt((Entity)c.getTarget());
/*    */     } 
/* 48 */     if (target == null) return true;
/*    */ 
/*    */ 
/*    */     
/* 52 */     if (target.isPlayer()) {
/* 53 */       score = objective.getScore(target.asPlayer().getName()).getScore();
/*    */     } else {
/* 55 */       score = objective.getScore(target.getUniqueId().toString()).getScore();
/*    */     } 
/*    */     
/* 58 */     if (MythicUtil.matchNumber(val, score)) {
/* 59 */       return true;
/*    */     }
/* 61 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\conditions\ConditionScoreTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */