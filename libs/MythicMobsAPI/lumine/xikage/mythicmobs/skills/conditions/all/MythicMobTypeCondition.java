/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "mythicMobType", aliases = {"mmType"}, description = "Checks the MythicMob type of the target mob")
/*    */ public class MythicMobTypeCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "type", aliases = {"types", "t"}, description = "A list of MythicMob types")
/* 18 */   private Set<String> types = new HashSet<>();
/*    */ 
/*    */   
/*    */   public MythicMobTypeCondition(String line, MythicLineConfig mlc) {
/* 22 */     super(line);
/*    */     
/* 24 */     String types = mlc.getString(new String[] { "types", "type", "t" }, this.conditionVar, new String[0]);
/* 25 */     String[] split = types.split(",");
/* 26 */     for (String s : split) this.types.add(s);
/*    */   
/*    */   }
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 31 */     if (MythicMobs.inst().getMobManager().isActiveMob(entity)) {
/* 32 */       ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(entity);
/* 33 */       return this.types.contains(am.getType().getInternalName());
/*    */     } 
/* 35 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\MythicMobTypeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */