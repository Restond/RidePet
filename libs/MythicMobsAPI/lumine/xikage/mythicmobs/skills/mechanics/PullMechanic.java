/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "pull", description = "Pulls the target entity towards the caster")
/*    */ public class PullMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill {
/*    */   protected boolean toOrigin;
/*    */   protected PlaceholderDouble velocity;
/*    */   
/*    */   public PullMechanic(String line, MythicLineConfig mlc) {
/* 23 */     super(line, mlc);
/*    */     
/* 25 */     this.velocity = mlc.getPlaceholderDouble(new String[] { "velocity", "v" }, 1.0D, new String[0]);
/* 26 */     this.toOrigin = mlc.getBoolean(new String[] { "toorigin", "to" }, false);
/*    */   }
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/*    */     Location l;
/* 31 */     double velocity = this.velocity.get((PlaceholderMeta)data, target) / 10.0D;
/*    */ 
/*    */     
/* 34 */     if (this.toOrigin == true) {
/* 35 */       l = BukkitAdapter.adapt(data.getOrigin());
/*    */     } else {
/* 37 */       l = BukkitAdapter.adapt(data.getCaster().getEntity()).getLocation();
/*    */     } 
/* 39 */     Entity t = BukkitAdapter.adapt(target);
/*    */     
/* 41 */     double distance = l.distance(t.getLocation());
/* 42 */     double modxz = distance * 0.5D * velocity;
/* 43 */     double mody = distance * 0.34D * velocity;
/* 44 */     mody = (l.getY() - target.getLocation().getY() != 0.0D) ? (mody * Math.abs(l.getY() - target.getLocation().getY()) * 0.5D) : mody;
/*    */     
/* 46 */     Vector v = t.getLocation().toVector().subtract(l.toVector()).normalize().multiply(velocity);
/* 47 */     v.setX(v.getX() * -1.0D * modxz);
/* 48 */     v.setZ(v.getZ() * -1.0D * modxz);
/* 49 */     v.setY(v.getY() * -1.0D * mody);
/*    */     
/* 51 */     if (v.length() > 4.0D) {
/* 52 */       v = v.normalize().multiply(4);
/*    */     }
/*    */     
/* 55 */     if (Double.isNaN(v.getX())) v.setX(0); 
/* 56 */     if (Double.isNaN(v.getY())) v.setY(0); 
/* 57 */     if (Double.isNaN(v.getZ())) v.setZ(0);
/*    */     
/* 59 */     t.setVelocity(v);
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\PullMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */