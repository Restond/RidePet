/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.IDummySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "delay", description = "Delays the execution of the next mechanic")
/*    */ public class DelaySkill
/*    */   extends SkillMechanic implements IDummySkill {
/*    */   protected int ticks;
/*    */   
/*    */   public DelaySkill(String line, MythicLineConfig mlc) {
/* 15 */     super(line, mlc);
/*    */     
/* 17 */     String[] split = line.split(" ");
/*    */     
/*    */     try {
/* 20 */       this.ticks = Integer.parseInt(split[1]);
/* 21 */     } catch (Exception ex) {
/* 22 */       this.ticks = 0;
/* 23 */       MythicLogger.error("A delay is incorrectly configured: second argument must be an integer.");
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getTicks() {
/* 28 */     return this.ticks;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\DelaySkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */