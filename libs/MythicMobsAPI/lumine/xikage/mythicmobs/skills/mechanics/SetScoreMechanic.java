/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.ModifyScoreMechanic;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "setscore", description = "Sets a scoreboard value")
/*    */ public class SetScoreMechanic extends ModifyScoreMechanic {
/*    */   public SetScoreMechanic(String skill, MythicLineConfig mlc) {
/* 10 */     super(skill, mlc);
/* 11 */     this.action = ModifyScoreMechanic.ModifyScoreAction.SET;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetScoreMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */