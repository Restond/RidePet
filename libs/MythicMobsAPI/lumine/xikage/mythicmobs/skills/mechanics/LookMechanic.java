/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "look", description = "Forces the caster to look at the target location")
/*    */ public class LookMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   protected boolean headOnly;
/*    */   protected boolean immediately;
/*    */   
/*    */   public LookMechanic(String line, MythicLineConfig mlc) {
/* 20 */     super(line, mlc);
/*    */     
/* 22 */     this.headOnly = mlc.getBoolean(new String[] { "headonly", "ho" }, false);
/* 23 */     this.immediately = mlc.getBoolean(new String[] { "immediately", "immediate", "i" }, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 28 */     MythicMobs.inst().getVolatileCodeHandler().lookAtEntity(data.getCaster().getEntity(), target, this.headOnly, this.immediately);
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 34 */     MythicMobs.inst().getVolatileCodeHandler().lookAtLocation(data.getCaster().getEntity(), target, this.headOnly, this.immediately);
/* 35 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\LookMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */