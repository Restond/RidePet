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
/*    */ public class LivingInRadiusTargeter
/*    */   extends IEntitySelector {
/*    */   private double radius;
/*    */   
/*    */   public LivingInRadiusTargeter(MythicLineConfig mlc) {
/* 16 */     super(mlc);
/* 17 */     this.radius = mlc.getDouble("radius", 5.0D);
/* 18 */     this.radius = mlc.getDouble("r", this.radius);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 23 */     SkillCaster am = data.getCaster();
/* 24 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 26 */     for (AbstractEntity p : MythicMobs.inst().getEntityManager().getLivingEntities(am.getEntity().getWorld())) {
/* 27 */       if (am.getLocation().getWorld().equals(p.getWorld()) && 
/* 28 */         !p.getUniqueId().equals(am.getEntity().getUniqueId()) && 
/* 29 */         am.getEntity().getLocation().distanceSquared(p.getLocation()) < Math.pow(this.radius, 2.0D)) {
/* 30 */         targets.add(p);
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 38 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\LivingInRadiusTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */