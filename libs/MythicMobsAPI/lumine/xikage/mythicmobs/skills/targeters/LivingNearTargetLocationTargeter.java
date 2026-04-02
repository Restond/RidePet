/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class LivingNearTargetLocationTargeter
/*    */   extends IEntitySelector {
/*    */   double radius;
/*    */   
/*    */   public LivingNearTargetLocationTargeter(MythicLineConfig mlc) {
/* 16 */     super(mlc);
/* 17 */     this.radius = mlc.getDouble(new String[] { "radius", "r" }, 5.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 22 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 24 */     for (AbstractEntity p : MythicMobs.inst().getEntityManager().getLivingEntities(data.getCaster().getEntity().getWorld())) {
/* 25 */       if (p.getUniqueId().equals(data.getCaster().getEntity().getUniqueId()))
/*    */         continue; 
/* 27 */       for (AbstractLocation l : data.getLocationTargets()) {
/* 28 */         if (l.distanceSquared(p.getLocation()) < Math.pow(this.radius, 2.0D)) {
/* 29 */           targets.add(p);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 34 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\LivingNearTargetLocationTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */