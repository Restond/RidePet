/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "ring", aliases = {}, description = "Targets points in a ring around the caster")
/*    */ public class RingTargeter extends ILocationSelector {
/*    */   double radius;
/*    */   double yOffset;
/*    */   int points;
/*    */   
/*    */   public RingTargeter(MythicLineConfig mlc) {
/* 20 */     super(mlc);
/*    */     
/* 22 */     this.radius = mlc.getDouble("radius", 5.0D);
/* 23 */     this.radius = mlc.getDouble("r", this.radius);
/*    */     
/* 25 */     this.points = mlc.getInteger("points", 10);
/* 26 */     this.points = mlc.getInteger("p", this.points);
/*    */     
/* 28 */     this.yOffset = mlc.getDouble("yoffset", 0.0D);
/* 29 */     this.yOffset = mlc.getDouble("y", this.yOffset);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
/* 34 */     SkillCaster am = data.getCaster();
/* 35 */     HashSet<AbstractLocation> targets = new HashSet<>();
/*    */     
/* 37 */     AbstractLocation l = am.getEntity().getLocation();
/*    */ 
/*    */ 
/*    */     
/* 41 */     double bx = l.getX();
/* 42 */     double y = l.getY();
/* 43 */     double bz = l.getZ();
/* 44 */     AbstractWorld w = l.getWorld();
/*    */     
/* 46 */     if (this.points <= 0) this.points = 1; 
/* 47 */     float inc = (360 / this.points);
/*    */     
/*    */     double i;
/*    */     
/* 51 */     for (i = 0.0D; i < 360.0D; i += inc) {
/* 52 */       double angle = i * Math.PI / 180.0D;
/* 53 */       double x = (int)(bx + this.radius * Math.cos(angle));
/* 54 */       double z = (int)(bz + this.radius * Math.sin(angle));
/*    */       
/* 56 */       AbstractLocation l2 = MythicMobs.inst().server().newLocation(w, x, y + this.yOffset, z);
/* 57 */       targets.add(l2);
/*    */     } 
/*    */     
/* 60 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\RingTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */