/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class MTNearestPlayer
/*    */   extends IEntitySelector {
/*    */   double radius;
/*    */   
/*    */   public MTNearestPlayer(MythicLineConfig mlc) {
/* 17 */     super(mlc);
/* 18 */     this.radius = mlc.getDouble("radius", 5.0D);
/* 19 */     this.radius = mlc.getDouble("r", this.radius);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 24 */     SkillCaster am = data.getCaster();
/* 25 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 27 */     AbstractPlayer nearest = null;
/*    */     
/* 29 */     for (AbstractPlayer p : MythicMobs.inst().getEntityManager().getPlayers(am.getEntity().getWorld())) {
/* 30 */       if (!p.getUniqueId().equals(am.getEntity().getUniqueId()) && 
/* 31 */         p.getWorld().equals(am.getEntity().getWorld()) && 
/* 32 */         am.getEntity().getLocation().distanceSquared(p.getLocation()) < Math.pow(this.radius, 2.0D) && (
/* 33 */         nearest == null || am.getEntity().getLocation().distanceSquared(nearest.getLocation()) > am.getEntity().getLocation().distanceSquared(p.getLocation()))) {
/* 34 */         nearest = p;
/*    */       }
/*    */     } 
/*    */     
/* 38 */     if (nearest != null) {
/* 39 */       targets.add(nearest);
/*    */     }
/*    */     
/* 42 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MTNearestPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */