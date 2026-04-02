/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractGameMode;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "setgamemode", description = "Sets the gamemode of the target player")
/*    */ public class SetGameModeMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/* 17 */   protected AbstractGameMode mode = AbstractGameMode.SURVIVAL;
/*    */   
/*    */   public SetGameModeMechanic(String skill, MythicLineConfig mlc) {
/* 20 */     super(skill, mlc);
/*    */     
/* 22 */     String strMode = mlc.getString(new String[] { "mode", "m" }, "SURVIVAL", new String[0]);
/*    */     
/*    */     try {
/* 25 */       this.mode = AbstractGameMode.valueOf(strMode.toUpperCase());
/* 26 */     } catch (Exception ex) {
/* 27 */       MythicLogger.errorMechanicConfig(this, mlc, "'" + strMode + "' is not a valid GameMode");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 33 */     if (!target.isPlayer()) {
/* 34 */       return false;
/*    */     }
/* 36 */     target.asPlayer().setGameMode(this.mode);
/* 37 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetGameModeMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */