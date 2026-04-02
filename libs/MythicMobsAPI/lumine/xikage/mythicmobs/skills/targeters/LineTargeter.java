/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "line", aliases = {}, description = "Targets points in a line from the caster to the target location")
/*    */ public class LineTargeter
/*    */   extends ILocationSelector
/*    */ {
/* 16 */   protected float radius = 1.0F;
/*    */   protected float radiusSq;
/*    */   
/*    */   public LineTargeter(MythicLineConfig mlc) {
/* 20 */     super(mlc);
/*    */     
/* 22 */     this.radius = mlc.getFloat(new String[] { "radius", "r" }, this.radius);
/* 23 */     this.fromOrigin = mlc.getBoolean(new String[] { "fromorigin", "fo" }, false);
/*    */     
/* 25 */     this.radiusSq = this.radius * this.radius;
/*    */   }
/*    */   protected boolean fromOrigin = false;
/*    */   
/*    */   public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
/*    */     AbstractLocation sl;
/* 31 */     HashSet<AbstractLocation> targets = new HashSet<>();
/*    */ 
/*    */ 
/*    */     
/* 35 */     if (this.fromOrigin == true) {
/* 36 */       sl = data.getOrigin().clone();
/*    */     } else {
/* 38 */       sl = data.getCaster().getLocation();
/*    */     } 
/*    */     
/* 41 */     double range = 1.0D;
/* 42 */     if (data.getLocationTargets() != null && data.getLocationTargets().size() > 0) {
/* 43 */       for (AbstractLocation l : data.getLocationTargets()) {
/* 44 */         double d = l.distanceSquared(sl);
/* 45 */         if (d > range) {
/* 46 */           range = d;
/*    */         }
/*    */       } 
/* 49 */     } else if (data.getEntityTargets() != null && data.getEntityTargets().size() > 0) {
/* 50 */       for (AbstractEntity l : data.getEntityTargets()) {
/* 51 */         double d = l.getLocation().distanceSquared(sl);
/* 52 */         if (d > range) {
/* 53 */           range = d;
/*    */         }
/*    */       } 
/*    */     } else {
/* 57 */       return targets;
/*    */     } 
/*    */     
/* 60 */     if (data.getLocationTargets() != null && data.getLocationTargets().size() > 0) {
/* 61 */       data.getLocationTargets().forEach(l -> {
/*    */             int c = (int)Math.ceil(paramAbstractLocation1.distance(l) / this.radius) - 1;
/*    */             
/*    */             if (c <= 0) {
/*    */               return;
/*    */             }
/*    */             
/*    */             AbstractVector v = l.toVector().subtract(paramAbstractLocation1.toVector()).normalize().multiply(this.radius);
/*    */             
/*    */             AbstractLocation l2 = paramAbstractLocation1.clone();
/*    */             
/*    */             for (int i = 0; i < c; i++) {
/*    */               l2.add(v);
/*    */               
/*    */               paramHashSet.add(l2.clone());
/*    */             } 
/*    */           });
/* 78 */     } else if (data.getEntityTargets() != null && data.getEntityTargets().size() > 0) {
/* 79 */       data.getEntityTargets().forEach(ee -> {
/*    */             AbstractLocation l = ee.getLocation();
/*    */             
/*    */             int c = (int)Math.ceil(paramAbstractLocation.distance(l) / this.radius) - 1;
/*    */             
/*    */             if (c <= 0) {
/*    */               return;
/*    */             }
/*    */             
/*    */             AbstractVector v = l.toVector().subtract(paramAbstractLocation.toVector()).normalize().multiply(this.radius);
/*    */             AbstractLocation l2 = paramAbstractLocation.clone();
/*    */             for (int i = 0; i < c; i++) {
/*    */               l2.add(v);
/*    */               paramHashSet.add(l2.clone());
/*    */             } 
/*    */           });
/*    */     } 
/* 96 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\LineTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */