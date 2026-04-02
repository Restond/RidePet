/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "setlevel", aliases = {"modifylevel"}, description = "Modifies the castering mob's level")
/*    */ public class SetLevelMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill
/*    */ {
/*    */   protected SetLevelAction action;
/*    */   protected PlaceholderInt level;
/*    */   
/*    */   public SetLevelMechanic(String skill, MythicLineConfig mlc) {
/* 26 */     super(skill, mlc);
/*    */     
/* 28 */     String strAction = mlc.getString(new String[] { "action", "a" }, "SET", new String[0]);
/*    */     
/*    */     try {
/* 31 */       this.action = SetLevelAction.valueOf(strAction.toUpperCase());
/* 32 */     } catch (Exception exception) {}
/*    */     
/* 34 */     this.level = PlaceholderInt.of(mlc.getString(new String[] { "level", "l" }, "1", new String[0]));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 40 */     if (!(data.getCaster() instanceof ActiveMob)) {
/* 41 */       return false;
/*    */     }
/* 43 */     ActiveMob am = (ActiveMob)data.getCaster();
/* 44 */     int level = this.level.get((PlaceholderMeta)data);
/*    */     
/* 46 */     if (this.action == SetLevelAction.SET) {
/* 47 */       am.setLevel(level);
/* 48 */     } else if (this.action == SetLevelAction.ADD) {
/* 49 */       am.setLevel(am.getLevel() + level);
/* 50 */     } else if (this.action == SetLevelAction.SUBTRACT) {
/* 51 */       am.setLevel(am.getLevel() - level);
/* 52 */     } else if (this.action == SetLevelAction.MULTIPLY) {
/* 53 */       am.setLevel(am.getLevel() * level);
/* 54 */     } else if (this.action == SetLevelAction.DIVIDE) {
/* 55 */       am.setLevel(am.getLevel() / level);
/*    */     } 
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SetLevelMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */