/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import org.bukkit.entity.Mob;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "incombat", aliases = {}, description = "If the target mob is considered in combat")
/*    */ public class InCombatCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition {
/*    */   public InCombatCondition(String line, MythicLineConfig mlc) {
/* 17 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 22 */     if (MythicMobs.inst().getMobManager().isActiveMob(entity)) {
/* 23 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(entity);
/*    */       
/* 25 */       if (am.hasThreatTable())
/* 26 */         return am.getThreatTable().inCombat(); 
/* 27 */       if (((Mob)entity.getBukkitEntity()).getTarget() != null) {
/* 28 */         return true;
/*    */       }
/*    */     } 
/* 31 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\InCombatCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */