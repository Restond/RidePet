/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "gcd", aliases = {"globalcooldown", "setgcd", "setglobalcooldown"}, description = "Triggers the global cooldown for the caster")
/*    */ public class GCDMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill {
/*    */   protected int ticks;
/*    */   
/*    */   public GCDMechanic(String skill, MythicLineConfig mlc) {
/* 16 */     super(skill, mlc);
/*    */     
/* 18 */     this.ticks = mlc.getInteger(new String[] { "ticks", "t" }, 20);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 23 */     data.getCaster().setGlobalCooldown(this.ticks);
/* 24 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\GCDMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */