/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class LivingNearOriginTargeter
/*    */   extends IEntitySelector {
/*    */   double radius;
/*    */   
/*    */   public LivingNearOriginTargeter(MythicLineConfig mlc) {
/* 15 */     super(mlc);
/* 16 */     this.radius = mlc.getDouble("radius", 5.0D);
/* 17 */     this.radius = mlc.getDouble("r", this.radius);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 22 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 24 */     for (AbstractEntity p : MythicMobs.inst().getEntityManager().getLivingEntities(data.getCaster().getEntity().getWorld())) {
/* 25 */       if (data.getCaster().getLocation().getWorld().equals(p.getWorld()) && 
/* 26 */         !p.getUniqueId().equals(data.getCaster().getEntity().getUniqueId()) && 
/* 27 */         data.getOrigin().distanceSquared(p.getLocation()) < Math.pow(this.radius, 2.0D)) {
/* 28 */         targets.add(p);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 33 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\LivingNearOriginTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */