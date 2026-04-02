/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class ThreatTableTargeter
/*    */   extends IEntitySelector {
/*    */   public ThreatTableTargeter(MythicLineConfig mlc) {
/* 15 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 20 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 22 */     if (!(data.getCaster() instanceof ActiveMob)) {
/* 23 */       return targets;
/*    */     }
/*    */     
/* 26 */     ActiveMob am = (ActiveMob)data.getCaster();
/* 27 */     if (am.hasThreatTable()) {
/* 28 */       for (AbstractEntity le : am.getThreatTable().getAllThreatTargets()) {
/* 29 */         targets.add(le);
/*    */       }
/*    */     } else {
/* 32 */       MythicLogger.errorTargeterConfig((SkillTargeter)this, this.config, "ThreatTable targeters are only available on mobs with Options.UseThreatTable set to True!.");
/*    */     } 
/*    */     
/* 35 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\ThreatTableTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */