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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SmokeSwirlEffect
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*    */ {
/*    */   protected int duration;
/*    */   protected int interval;
/*    */   
/*    */   public SmokeSwirlEffect(String skill, MythicLineConfig mlc) {
/* 23 */     super(skill, mlc);
/*    */     
/* 25 */     this.duration = mlc.getInteger(new String[] { "duration", "d" }, 5);
/* 26 */     this.interval = mlc.getInteger(new String[] { "interval", "i" }, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 31 */     new Animator(this, BukkitAdapter.adapt(target), this.interval, this.duration);
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 37 */     new Animator(this, BukkitAdapter.adapt(target), this.interval, this.duration);
/* 38 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SmokeSwirlEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */