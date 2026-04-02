/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "explosion", aliases = {"explode"}, description = "Causes an explosion at the target location")
/*    */ public class ExplosionMechanic extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   protected float yield;
/*    */   protected boolean blockdamage;
/*    */   protected boolean fire;
/*    */   
/*    */   public ExplosionMechanic(String line, MythicLineConfig mlc) {
/* 19 */     super(line, mlc);
/* 20 */     this.ASYNC_SAFE = false;
/*    */     
/* 22 */     this.yield = (float)mlc.getDouble(new String[] { "yield", "y" }, 0.01337D);
/* 23 */     this.blockdamage = mlc.getBoolean(new String[] { "blockdamage", "bd" }, false);
/* 24 */     this.fire = mlc.getBoolean(new String[] { "fire", "ft", "f" }, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 29 */     target.getLocation().getWorld().createExplosion(target.getLocation(), this.yield, this.fire, this.blockdamage);
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 35 */     target.getWorld().createExplosion(target, this.yield, this.fire, this.blockdamage);
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ExplosionMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */