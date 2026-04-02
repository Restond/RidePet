/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
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
/*    */ @MythicMechanic(author = "Ashijin", name = "effect:lightning", aliases = {"e:lightning"}, description = "Causes a lightning strike effect at the target location")
/*    */ public class LightningEffect
/*    */   extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   protected boolean localized;
/* 18 */   protected double localizedRadius = 128.0D;
/*    */   
/*    */   public LightningEffect(String skill, MythicLineConfig mlc) {
/* 21 */     super(skill, mlc);
/* 22 */     this.ASYNC_SAFE = false;
/*    */     
/* 24 */     this.localized = mlc.getBoolean(new String[] { "localized", "l" }, false);
/* 25 */     this.localizedRadius = mlc.getDouble(new String[] { "localizedradius", "lr", "r" }, 128.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 30 */     if (this.localized) {
/* 31 */       MythicMobs.inst().getVolatileCodeHandler().playLocalizedLightningEffect(target, this.localizedRadius);
/*    */     } else {
/* 33 */       SkillAdapter.get().strikeLightningEffect(target);
/*    */     } 
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 40 */     return castAtLocation(data, target.getLocation());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\LightningEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */