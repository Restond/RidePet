/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.WitherSkull;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class ShootSkullMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   protected float yield;
/*    */   protected boolean playsound;
/*    */   
/*    */   public ShootSkullMechanic(String line, MythicLineConfig mlc) {
/* 26 */     super(line, mlc);
/* 27 */     this.ASYNC_SAFE = false;
/*    */     
/* 29 */     this.yield = mlc.getFloat("yield", 1.0F);
/* 30 */     this.yield = mlc.getFloat("strength", this.yield);
/* 31 */     this.yield = mlc.getFloat("y", this.yield);
/* 32 */     this.yield = mlc.getFloat("s", this.yield);
/*    */     
/* 34 */     this.playsound = mlc.getBoolean("playsound", false);
/* 35 */     this.playsound = mlc.getBoolean("ps", this.playsound);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 40 */     return castAtLocation(data, target.getLocation());
/*    */   }
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/*    */     WitherSkull fireball;
/* 45 */     Entity l = BukkitAdapter.adapt(data.getCaster().getEntity());
/* 46 */     Location t = BukkitAdapter.adapt(target);
/*    */ 
/*    */     
/* 49 */     if (l instanceof Creature && ((Creature)l).getTarget() == target) {
/* 50 */       fireball = (WitherSkull)((LivingEntity)l).launchProjectile(WitherSkull.class);
/*    */     } else {
/* 52 */       Vector facing = t.toVector().subtract(l.getLocation().toVector()).normalize();
/*    */       
/* 54 */       Location loc = l.getLocation().clone();
/*    */       
/* 56 */       double yaw = Math.toDegrees(Math.atan2(-facing.getX(), facing.getZ()));
/* 57 */       double pitch = Math.toDegrees(-Math.asin(facing.getY()));
/* 58 */       loc.setYaw((float)yaw);
/* 59 */       loc.setPitch((float)pitch);
/*    */       
/* 61 */       loc.add(facing.multiply(2));
/*    */       
/* 63 */       fireball = (WitherSkull)l.getLocation().getWorld().spawn(loc, WitherSkull.class);
/*    */     } 
/*    */     
/* 66 */     if (this.playsound == true) {
/* 67 */       l.getWorld().playEffect(l.getLocation(), Effect.GHAST_SHOOT, 0);
/*    */     }
/* 69 */     fireball.setBounce(false);
/* 70 */     fireball.setYield(this.yield);
/* 71 */     if (l instanceof LivingEntity) {
/* 72 */       fireball.setShooter((ProjectileSource)l);
/*    */     }
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ShootSkullMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */