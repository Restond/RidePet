/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.Patterns;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "stance", aliases = {}, description = "Checks the stance of the target mob")
/*    */ public class StanceCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition
/*    */ {
/*    */   @MythicField(name = "stance", aliases = {"s"}, description = "The stance to match")
/*    */   private String stance;
/*    */   @MythicField(name = "strict", aliases = {"str"}, description = "Whether to match exactly. Defaults to false.")
/*    */   private boolean mode;
/*    */   private boolean parse;
/*    */   
/*    */   public StanceCondition(String line, MythicLineConfig mlc) {
/* 25 */     super(line);
/* 26 */     this.stance = mlc.getString(new String[] { "stance", "s" }, "DEFAULT", new String[] { this.conditionVar });
/* 27 */     this.mode = mlc.getBoolean(new String[] { "strict", "str" }, false);
/* 28 */     this.parse = mlc.getBoolean(new String[] { "parse", "p" }, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 33 */     if (MythicMobs.inst().getMobManager().isActiveMob(entity)) {
/* 34 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(entity);
/* 35 */       if (this.parse) {
/* 36 */         Patterns.CompilePatterns();
/*    */       }
/* 38 */       if (this.mode) {
/* 39 */         return am.getStance().equals(this.stance);
/*    */       }
/* 41 */       return am.getStance().contains(this.stance);
/*    */     } 
/*    */     
/* 44 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\StanceCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */