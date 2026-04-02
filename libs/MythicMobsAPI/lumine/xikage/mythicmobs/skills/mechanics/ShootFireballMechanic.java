/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.SkillAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ 
/*    */ public class ShootFireballMechanic extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   protected float yield;
/*    */   protected float velocity;
/*    */   protected int fireticks;
/*    */   protected boolean incendiary;
/*    */   protected boolean smallfireball;
/*    */   protected boolean playsound;
/*    */   
/*    */   public ShootFireballMechanic(String line, MythicLineConfig mlc) {
/* 21 */     super(line, mlc);
/* 22 */     this.ASYNC_SAFE = false;
/*    */     
/* 24 */     this.yield = mlc.getFloat("yield", 1.0F);
/* 25 */     this.yield = mlc.getFloat("strength", this.yield);
/* 26 */     this.yield = mlc.getFloat("y", this.yield);
/* 27 */     this.yield = mlc.getFloat("s", this.yield);
/*    */     
/* 29 */     this.velocity = mlc.getFloat("velocity", 1.0F);
/* 30 */     this.velocity = mlc.getFloat("v", this.velocity);
/*    */     
/* 32 */     this.fireticks = mlc.getInteger("fireticks", 0);
/* 33 */     this.fireticks = mlc.getInteger("ft", this.fireticks);
/*    */     
/* 35 */     this.incendiary = mlc.getBoolean("incendiary", false);
/* 36 */     this.incendiary = mlc.getBoolean("i", this.incendiary);
/*    */     
/* 38 */     this.smallfireball = mlc.getBoolean(new String[] { "smallfireball", "small", "sml" }, false);
/*    */     
/* 40 */     this.playsound = mlc.getBoolean("playsound", false);
/* 41 */     this.playsound = mlc.getBoolean("ps", this.playsound);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 46 */     return castAtLocation(data, target.getLocation());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 51 */     SkillAdapter.get().shootFireball(data.getCaster(), target, this.velocity, this.yield, this.incendiary, this.fireticks, this.smallfireball, this.playsound);
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ShootFireballMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */