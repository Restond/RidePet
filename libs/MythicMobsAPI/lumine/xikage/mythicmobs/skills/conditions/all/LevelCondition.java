/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ICasterCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.MythicUtil;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "level", aliases = {}, description = "Checks the target MythicMob's level")
/*    */ public class LevelCondition
/*    */   extends SkillCondition implements ICasterCondition, IEntityCondition {
/*    */   @MythicField(name = "level", aliases = {"l"}, description = "The level range to match")
/*    */   private String data;
/*    */   
/*    */   public LevelCondition(String line, MythicLineConfig config) {
/* 22 */     super(line);
/* 23 */     this.data = config.getString(new String[] { "level", "l" }, this.conditionVar, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 28 */     if (MythicMobs.inst().getMobManager().isActiveMob(target)) {
/* 29 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(target);
/*    */       
/* 31 */       if (MythicUtil.matchNumber(this.data, am.getLevel())) {
/* 32 */         return true;
/*    */       }
/* 34 */       return false;
/*    */     } 
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(SkillCaster caster) {
/* 42 */     if (MythicUtil.matchNumber(this.data, caster.getLevel())) {
/* 43 */       return true;
/*    */     }
/* 45 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\LevelCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */