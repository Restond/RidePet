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
/*    */ public class MTPlayersNearTargetLocations
/*    */   extends IEntitySelector {
/*    */   double radius;
/*    */   
/*    */   public MTPlayersNearTargetLocations(MythicLineConfig mlc) {
/* 16 */     super(mlc);
/* 17 */     this.radius = mlc.getDouble(new String[] { "radius", "r" }, 5.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 22 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 24 */     if (data.getLocationTargets() == null) {
/* 25 */       return targets;
/*    */     }
/*    */     
/* 28 */     for (AbstractEntity p : MythicMobs.inst().getEntityManager().getPlayers(data.getCaster().getEntity().getWorld())) {
/* 29 */       if (!data.getCaster().getLocation().getWorld().equals(p.getWorld()) || 
/* 30 */         p.getUniqueId().equals(data.getCaster().getEntity().getUniqueId()))
/*    */         continue; 
/* 32 */       for (AbstractLocation l : data.getLocationTargets()) {
/* 33 */         if (l.distanceSquared(p.getLocation()) < Math.pow(this.radius, 2.0D)) {
/* 34 */           targets.add(p);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 39 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MTPlayersNearTargetLocations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */