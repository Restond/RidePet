/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "livingInCone", aliases = {"entitiesInCone", "livingEntitiesInCone", "LEIC", "EIC"}, description = "Targets random points in a cone shape")
/*    */ public class LivingInConeTargeter
/*    */   extends IEntitySelector {
/*    */   protected double angle;
/*    */   protected double range;
/*    */   protected double rotation;
/*    */   
/*    */   public LivingInConeTargeter(MythicLineConfig mlc) {
/* 21 */     super(mlc);
/*    */     
/* 23 */     this.angle = mlc.getDouble(new String[] { "angle", "a" }, 90.0D);
/* 24 */     this.range = mlc.getDouble(new String[] { "range", "r" }, 16.0D);
/*    */     
/* 26 */     this.rotation = mlc.getDouble(new String[] { "rotation", "rot" }, 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 32 */     SkillCaster am = data.getCaster();
/* 33 */     HashSet<AbstractEntity> possible = new HashSet<>();
/*    */     
/* 35 */     for (AbstractEntity p : MythicMobs.inst().getEntityManager().getLivingEntities(am.getEntity().getWorld())) {
/* 36 */       if (am.getLocation().getWorld().equals(p.getWorld()) && 
/* 37 */         !p.getUniqueId().equals(am.getEntity().getUniqueId()) && 
/* 38 */         am.getEntity().getLocation().distanceSquared(p.getLocation()) < Math.pow(this.range, 2.0D)) {
/* 39 */         possible.add(p);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 44 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 46 */     AbstractVector dir = data.getCaster().getLocation().getDirection();
/*    */     
/* 48 */     if (this.rotation > 0.0D) {
/* 49 */       dir.rotate((float)this.rotation);
/*    */     }
/*    */     
/* 52 */     dir.setY(0);
/* 53 */     double cos = Math.cos(this.angle * Math.PI / 180.0D);
/* 54 */     double cosSq = cos * cos;
/*    */     
/* 56 */     possible.forEach(entity -> {
/*    */           AbstractVector relative = entity.getLocation().subtract(paramSkillMetadata.getCaster().getLocation()).toVector();
/*    */           
/*    */           relative.setY(0);
/*    */           double dot = relative.getX() * paramAbstractVector.getX() + relative.getY() * paramAbstractVector.getY() + relative.getZ() * paramAbstractVector.getZ();
/*    */           double value = dot * dot / relative.lengthSquared();
/*    */           if (this.angle < 180.0D && dot > 0.0D && value >= paramDouble) {
/*    */             paramHashSet.add(entity);
/*    */           } else if (this.angle >= 180.0D && (dot > 0.0D || dot <= paramDouble)) {
/*    */             paramHashSet.add(entity);
/*    */           } 
/*    */         });
/* 68 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\LivingInConeTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */