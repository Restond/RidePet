/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "leap", description = "Causes the caster to leap to the target location")
/*    */ public class LeapMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   protected double velocity;
/*    */   protected double noise;
/*    */   
/*    */   public LeapMechanic(String line, MythicLineConfig mlc) {
/* 25 */     super(line, mlc);
/*    */     
/* 27 */     this.velocity = mlc.getDouble("v", 100.0D);
/* 28 */     this.velocity = mlc.getDouble("velocity", this.velocity);
/* 29 */     this.velocity /= 10.0D;
/*    */     
/* 31 */     this.noise = mlc.getDouble("noise", 1.0D);
/* 32 */     this.noise = mlc.getDouble("noise", this.noise);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 37 */     Leap(data.getCaster(), target.getLocation(), data.getPower());
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 43 */     Leap(data.getCaster(), target, data.getPower());
/* 44 */     return false;
/*    */   }
/*    */   
/*    */   protected void Leap(SkillCaster am, AbstractLocation location, float power) {
/* 48 */     int gravity = 20;
/*    */ 
/*    */     
/* 51 */     double velocity = this.velocity * (1.0D + power * 0.1D);
/*    */     
/* 53 */     Location from = BukkitAdapter.adapt(am.getEntity().getLocation());
/* 54 */     Location to = BukkitAdapter.adapt(location);
/*    */     
/* 56 */     if (!from.getWorld().equals(to.getWorld()))
/*    */       return; 
/* 58 */     Vector v = to.clone().subtract(from).toVector();
/* 59 */     Double elevation = Double.valueOf(v.getY());
/*    */     
/* 61 */     Double launchAngle = MythicUtil.calculateLaunchAngle(from, to, velocity, elevation.doubleValue(), 20.0D);
/* 62 */     Double distance = Double.valueOf(Math.sqrt(Math.pow(v.getX(), 2.0D) + Math.pow(v.getZ(), 2.0D)));
/*    */     
/* 64 */     if (distance.doubleValue() == 0.0D)
/*    */       return; 
/* 66 */     if (launchAngle == null) {
/* 67 */       launchAngle = Double.valueOf(Math.atan((40.0D * elevation.doubleValue() + Math.pow(velocity, 2.0D)) / (40.0D * elevation.doubleValue() + 2.0D * Math.pow(velocity, 2.0D))));
/*    */     }
/*    */     
/* 70 */     Double hangtime = Double.valueOf(MythicUtil.calculateHangtime(launchAngle.doubleValue(), velocity, elevation.doubleValue(), 20.0D));
/*    */     
/* 72 */     v.setY(Math.tan(launchAngle.doubleValue()) * distance.doubleValue());
/* 73 */     v = MythicUtil.normalizeVector(v);
/*    */     
/* 75 */     Vector noise = Vector.getRandom();
/* 76 */     noise = noise.multiply(this.noise / 10.0D);
/* 77 */     v.add(noise);
/*    */     
/* 79 */     velocity = velocity + 1.188D * Math.pow(hangtime.doubleValue(), 2.0D) + (MythicMobs.r.nextDouble() - 0.8D) / 2.0D;
/* 80 */     v = v.multiply(velocity / 20.0D);
/*    */     
/* 82 */     if (v.length() > 4.0D) {
/* 83 */       v = v.normalize().multiply(4);
/*    */     }
/*    */     
/* 86 */     if (Double.isNaN(v.getX())) v.setX(0); 
/* 87 */     if (Double.isNaN(v.getY())) v.setY(0); 
/* 88 */     if (Double.isNaN(v.getZ())) v.setZ(0);
/*    */     
/* 90 */     am.getEntity().getBukkitEntity().setVelocity(v);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\LeapMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */