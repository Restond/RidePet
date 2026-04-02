/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class MTPlayerLocationsInRadius extends ILocationSelector {
/*    */   double radius;
/*    */   double yOffset;
/*    */   
/*    */   public MTPlayerLocationsInRadius(MythicLineConfig mlc) {
/* 17 */     super(mlc);
/*    */     
/* 19 */     this.radius = mlc.getDouble("radius", 5.0D);
/* 20 */     this.radius = mlc.getDouble("r", this.radius);
/*    */     
/* 22 */     this.yOffset = mlc.getDouble("yoffset", 0.0D);
/* 23 */     this.yOffset = mlc.getDouble("y", this.yOffset);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
/* 28 */     SkillCaster am = data.getCaster();
/* 29 */     HashSet<AbstractLocation> targets = new HashSet<>();
/*    */ 
/*    */     
/* 32 */     for (AbstractPlayer p : MythicMobs.inst().getEntityManager().getPlayers(am.getEntity().getWorld())) {
/* 33 */       if (am.getLocation().getWorld().equals(p.getWorld()) && 
/* 34 */         am.getEntity().getLocation().distanceSquared(p.getLocation()) < Math.pow(this.radius, 2.0D)) {
/* 35 */         AbstractLocation l = p.getLocation();
/* 36 */         l.add(0.0D, this.yOffset, 0.0D);
/* 37 */         targets.add(l);
/*    */       } 
/*    */     } 
/*    */     
/* 41 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MTPlayerLocationsInRadius.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */