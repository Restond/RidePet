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
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "lightning", description = "Strikes lightning at the target location")
/*    */ public class LightningMechanic
/*    */   extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   protected float damage;
/*    */   
/*    */   public LightningMechanic(String line, MythicLineConfig mlc) {
/* 19 */     super(line, mlc);
/* 20 */     this.ASYNC_SAFE = false;
/*    */     
/* 22 */     this.damage = (float)mlc.getDouble(new String[] { "damage", "d" }, 0.01337D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 27 */     if (this.damage == 0.01337D) {
/* 28 */       SkillAdapter.get().strikeLightning(target.getLocation());
/*    */     } else {
/* 30 */       SkillAdapter.get().strikeLightningEffect(target.getLocation());
/* 31 */       target.damage(this.damage);
/*    */     } 
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 38 */     SkillAdapter.get().strikeLightning(target);
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\LightningMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */