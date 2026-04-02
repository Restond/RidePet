/*     */ package lumine.xikage.mythicmobs.skills.targeters;
/*     */ 
/*     */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*     */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*     */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*     */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*     */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*     */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*     */ import java.util.HashSet;
/*     */ 
/*     */ @MythicTargeter(author = "Ashijin", name = "livingInLine", aliases = {"entitiesInLine", "livingEntitiesInLine", "LEIL", "EIL"}, description = "Targets random points in a cone shape")
/*     */ public class LivingInLineTargeter
/*     */   extends IEntitySelector {
/*  17 */   protected float radius = 1.0F;
/*     */   protected float radiusSq;
/*     */   
/*     */   public LivingInLineTargeter(MythicLineConfig mlc) {
/*  21 */     super(mlc);
/*     */     
/*  23 */     this.radius = mlc.getFloat(new String[] { "radius", "r" }, this.radius);
/*  24 */     this.fromOrigin = mlc.getBoolean(new String[] { "fromorigin", "fo" }, false);
/*     */     
/*  26 */     this.radiusSq = this.radius * this.radius;
/*     */   }
/*     */   protected boolean fromOrigin = false;
/*     */   
/*     */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/*     */     AbstractLocation sl;
/*  32 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*     */ 
/*     */ 
/*     */     
/*  36 */     if (this.fromOrigin == true) {
/*  37 */       sl = data.getOrigin().clone();
/*     */     } else {
/*  39 */       sl = data.getCaster().getLocation();
/*     */     } 
/*     */     
/*  42 */     double range = 1.0D;
/*  43 */     if (data.getLocationTargets() != null && data.getLocationTargets().size() > 0) {
/*  44 */       for (AbstractLocation l : data.getLocationTargets()) {
/*  45 */         double d = l.distanceSquared(sl);
/*  46 */         if (d > range) {
/*  47 */           range = d;
/*     */         }
/*     */       } 
/*  50 */     } else if (data.getEntityTargets() != null && data.getEntityTargets().size() > 0) {
/*  51 */       for (AbstractEntity l : data.getEntityTargets()) {
/*  52 */         double d = l.getLocation().distanceSquared(sl);
/*  53 */         if (d > range) {
/*  54 */           range = d;
/*     */         }
/*     */       } 
/*     */     } else {
/*  58 */       return targets;
/*     */     } 
/*     */     
/*  61 */     SkillCaster am = data.getCaster();
/*  62 */     HashSet<AbstractEntity> possible = new HashSet<>();
/*     */     
/*  64 */     for (AbstractEntity p : MythicMobs.inst().getEntityManager().getLivingEntities(am.getEntity().getWorld())) {
/*  65 */       if (am.getLocation().getWorld().equals(p.getWorld()) && 
/*  66 */         !p.getUniqueId().equals(am.getEntity().getUniqueId()))
/*     */       {
/*  68 */         if (am.getEntity().getLocation().distanceSquared(p.getLocation()) <= range)
/*     */         {
/*  70 */           possible.add(p);
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/*  75 */     if (data.getLocationTargets() != null && data.getLocationTargets().size() > 0) {
/*  76 */       data.getLocationTargets().forEach(l -> {
/*     */             int c = (int)Math.ceil(paramAbstractLocation1.distance(l) / this.radius) - 1;
/*     */ 
/*     */             
/*     */             if (c <= 0) {
/*     */               return;
/*     */             }
/*     */             
/*     */             AbstractVector v = l.toVector().subtract(paramAbstractLocation1.toVector()).normalize().multiply(this.radius);
/*     */             
/*     */             AbstractLocation l2 = paramAbstractLocation1.clone();
/*     */             
/*     */             for (int i = 0; i < c; i++) {
/*     */               l2.add(v);
/*     */               
/*     */               for (AbstractEntity e : paramHashSet1) {
/*     */                 if (e.getLocation().distanceSquared(l2) <= this.radiusSq) {
/*     */                   paramHashSet2.add(e);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           });
/*  98 */     } else if (data.getEntityTargets() != null && data.getEntityTargets().size() > 0) {
/*  99 */       data.getEntityTargets().forEach(ee -> {
/*     */             AbstractLocation l = ee.getLocation();
/*     */             
/*     */             int c = (int)Math.ceil(paramAbstractLocation.distance(l) / this.radius) - 1;
/*     */             
/*     */             if (c <= 0) {
/*     */               return;
/*     */             }
/*     */             
/*     */             AbstractVector v = l.toVector().subtract(paramAbstractLocation.toVector()).normalize().multiply(this.radius);
/*     */             
/*     */             AbstractLocation l2 = paramAbstractLocation.clone();
/*     */             for (int i = 0; i < c; i++) {
/*     */               l2.add(v);
/*     */               for (AbstractEntity e : paramHashSet1) {
/*     */                 if (e.getLocation().distanceSquared(l2) <= this.radiusSq) {
/*     */                   paramHashSet2.add(e);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           });
/*     */     } 
/* 121 */     return targets;
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\LivingInLineTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */