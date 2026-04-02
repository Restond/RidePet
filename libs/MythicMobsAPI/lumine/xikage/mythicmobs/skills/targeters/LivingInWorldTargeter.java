/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class LivingInWorldTargeter
/*    */   extends IEntitySelector {
/*    */   public LivingInWorldTargeter(MythicLineConfig mlc) {
/* 14 */     super(mlc);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 20 */     SkillCaster am = data.getCaster();
/* 21 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 23 */     for (AbstractEntity l : MythicMobs.inst().getEntityManager().getLivingEntities(am.getEntity().getWorld())) {
/* 24 */       if (!am.getLocation().getWorld().equals(l.getWorld()))
/* 25 */         continue;  targets.add(l);
/*    */     } 
/*    */     
/* 28 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\LivingInWorldTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */