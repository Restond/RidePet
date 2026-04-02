/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "barRemove", version = "4.8", description = "Removes a custom bossbar display")
/*    */ public class BarRemoveMechanic
/*    */   extends SkillMechanic implements INoTargetSkill {
/*    */   protected String barName;
/*    */   
/*    */   public BarRemoveMechanic(String skill, MythicLineConfig mlc) {
/* 16 */     super(skill, mlc);
/* 17 */     this.ASYNC_SAFE = false;
/*    */     
/* 19 */     this.barName = mlc.getString(new String[] { "name", "n" }, "infobar", new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 24 */     if (!(data.getCaster() instanceof ActiveMob)) {
/* 25 */       return false;
/*    */     }
/* 27 */     ActiveMob am = (ActiveMob)data.getCaster();
/* 28 */     am.removeBar(this.barName);
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BarRemoveMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */