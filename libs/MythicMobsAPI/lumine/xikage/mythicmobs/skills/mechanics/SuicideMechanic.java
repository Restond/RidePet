/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "suicide", description = "Kills the caster")
/*    */ public class SuicideMechanic
/*    */   extends SkillMechanic implements INoTargetSkill {
/*    */   protected String search;
/*    */   
/*    */   public SuicideMechanic(String skill, MythicLineConfig mlc) {
/* 15 */     super(skill, mlc);
/* 16 */     this.ASYNC_SAFE = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 21 */     if (!data.getCaster().getEntity().isLiving()) {
/* 22 */       data.getCaster().getEntity().remove();
/*    */     } else {
/* 24 */       data.getCaster().getEntity().setHealth(1.0D);
/* 25 */       data.getCaster().getEntity().damage(999.0F);
/*    */     } 
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SuicideMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */