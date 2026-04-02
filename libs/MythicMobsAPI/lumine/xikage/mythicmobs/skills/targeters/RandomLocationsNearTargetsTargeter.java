/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "randomLocationsNearTargets", aliases = {"randomLocationsNearTarget", "randomLocationsNearTargetEntities", "randomLocationsNearTargetLocations", "RLNT", "RLNTE", "RLNTL"}, description = "Targets random locations near the inherited targets")
/*    */ public class RandomLocationsNearTargetsTargeter
/*    */   extends ILocationSelector {
/*    */   int amount;
/*    */   int amountSq;
/*    */   int maxRadius;
/*    */   
/*    */   public RandomLocationsNearTargetsTargeter(MythicLineConfig mlc) {
/* 21 */     super(mlc);
/*    */     
/* 23 */     this.amount = mlc.getInteger(new String[] { "amount", "a" }, 5);
/* 24 */     this.maxRadius = mlc.getInteger(new String[] { "radius", "r", "maxradius", "maxr" }, 5);
/* 25 */     this.minRadius = mlc.getInteger(new String[] { "minradius", "minr" }, 0);
/* 26 */     this.spacing = mlc.getInteger(new String[] { "spacing", "s" }, 0);
/*    */     
/* 28 */     this.amountSq = this.amount * this.amount;
/* 29 */     this.minRadiusSq = this.minRadius * this.minRadius;
/* 30 */     this.spacingSq = this.spacing * this.spacing;
/*    */   }
/*    */   int minRadius; int minRadiusSq; int spacing; int spacingSq;
/*    */   
/*    */   public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
/* 35 */     HashSet<AbstractLocation> targets = new HashSet<>();
/*    */     
/* 37 */     if (data.getEntityTargets() != null) {
/* 38 */       for (AbstractEntity e : data.getEntityTargets()) {
/* 39 */         int i = 0, j = 0;
/* 40 */         while (i < this.amount && j < this.amountSq) {
/* 41 */           j++;
/* 42 */           MythicMobs.inst().getMobManager(); AbstractLocation l = MobManager.findSafeSpawnLocation(e.getLocation(), this.maxRadius, 1, 1, true, false);
/* 43 */           if (this.minRadius > 0 && 
/* 44 */             l.distanceSquared(e.getLocation()) < this.minRadiusSq)
/*    */             continue; 
/* 46 */           if (this.spacing > 0) {
/* 47 */             boolean bad = false;
/* 48 */             for (AbstractLocation l2 : targets) {
/* 49 */               if (l.distanceSquared(l2) < this.spacingSq) {
/* 50 */                 bad = true;
/*    */                 break;
/*    */               } 
/*    */             } 
/* 54 */             if (bad == true)
/*    */               continue; 
/*    */           } 
/* 57 */           targets.add(l);
/* 58 */           i++;
/*    */         } 
/*    */       } 
/* 61 */     } else if (data.getLocationTargets() != null) {
/* 62 */       for (AbstractLocation e : data.getLocationTargets()) {
/* 63 */         int i = 0, j = 0;
/* 64 */         while (i < this.amount && j < this.amountSq) {
/* 65 */           j++;
/* 66 */           MythicMobs.inst().getMobManager(); AbstractLocation l = MobManager.findSafeSpawnLocation(e, this.maxRadius, 1, 1, true, false);
/* 67 */           if (this.minRadius > 0 && 
/* 68 */             l.distanceSquared(e) < this.minRadiusSq)
/*    */             continue; 
/* 70 */           if (this.spacing > 0) {
/* 71 */             boolean bad = false;
/* 72 */             for (AbstractLocation l2 : targets) {
/* 73 */               if (l.distanceSquared(l2) < this.spacingSq) {
/* 74 */                 bad = true;
/*    */                 break;
/*    */               } 
/*    */             } 
/* 78 */             if (bad == true)
/*    */               continue; 
/*    */           } 
/* 81 */           targets.add(l);
/* 82 */           i++;
/*    */         } 
/*    */       } 
/*    */     } 
/* 86 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\RandomLocationsNearTargetsTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */