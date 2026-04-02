/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractVector;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
/*    */ import io.lumine.xikage.mythicmobs.util.RandomUtil;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "cone", aliases = {}, description = "Targets random points in a cone in front of the caster")
/*    */ public class ConeTargeter
/*    */   extends ILocationSelector
/*    */ {
/*    */   protected double angle;
/*    */   protected double range;
/*    */   protected int points;
/*    */   protected double rotation;
/*    */   protected double yOffset;
/*    */   protected double cos;
/*    */   protected double cosSq;
/*    */   
/*    */   public ConeTargeter(MythicLineConfig mlc) {
/* 26 */     super(mlc);
/*    */     
/* 28 */     this.angle = mlc.getDouble(new String[] { "angle", "a" }, 90.0D);
/* 29 */     this.range = mlc.getDouble(new String[] { "range", "r" }, 16.0D);
/* 30 */     this.points = mlc.getInteger(new String[] { "points", "p" }, (int)(this.angle * this.range / 10.0D));
/*    */     
/* 32 */     this.rotation = mlc.getDouble(new String[] { "rotation", "rot" }, 0.0D);
/* 33 */     this.yOffset = mlc.getDouble(new String[] { "yoffset", "yo", "y" }, 0.0D);
/*    */     
/* 35 */     this.cos = Math.cos(this.angle * Math.PI / 180.0D);
/* 36 */     this.cosSq = this.cos * this.cos;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
/* 42 */     HashSet<AbstractLocation> targets = new HashSet<>();
/* 43 */     AbstractLocation sourceLocation = data.getCaster().getLocation().add(0.0D, this.yOffset, 0.0D);
/* 44 */     AbstractVector dir = sourceLocation.getDirection();
/*    */     
/* 46 */     if (this.rotation > 0.0D) {
/* 47 */       dir.rotate((float)this.rotation);
/*    */     }
/* 49 */     dir.setY(0);
/*    */     
/* 51 */     for (int i = 0; i < this.points; i++) {
/* 52 */       AbstractVector vector = RandomUtil.getRandomVector().multiply(MythicMobs.r.nextDouble() * this.range);
/* 53 */       AbstractLocation location = sourceLocation.clone().add(vector);
/* 54 */       AbstractVector relative = location.clone().subtract(sourceLocation).toVector().setY(0);
/*    */       
/* 56 */       double dot = relative.getX() * dir.getX() + relative.getY() * dir.getY() + relative.getZ() * dir.getZ();
/* 57 */       double value = dot * dot / relative.lengthSquared();
/*    */       
/* 59 */       if (this.angle < 180.0D && dot > 0.0D && value >= this.cosSq) {
/*    */         
/* 61 */         targets.add(location);
/* 62 */       } else if (this.angle >= 180.0D && (dot > 0.0D || dot <= this.cosSq)) {
/*    */         
/* 64 */         targets.add(location);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 69 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\ConeTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */