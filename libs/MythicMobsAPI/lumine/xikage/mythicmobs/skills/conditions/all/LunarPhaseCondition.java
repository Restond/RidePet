/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "lunarPhase", aliases = {}, description = "Checks the target world's lunar phase")
/*    */ public class LunarPhaseCondition
/*    */   extends SkillCondition implements ILocationCondition {
/*    */   @MythicField(name = "phases", aliases = {"phase", "p"}, description = "A list of lunar phases")
/* 16 */   private Set<Integer> phases = new HashSet<>();
/*    */ 
/*    */   
/*    */   public LunarPhaseCondition(String line, MythicLineConfig mlc) {
/* 20 */     super(line);
/*    */     
/* 22 */     String phase = mlc.getString(new String[] { "phases", "phase", "p" }, "0", new String[] { this.conditionVar });
/* 23 */     String[] split = phase.split(",");
/*    */     
/* 25 */     for (String s : split) this.phases.add(Integer.valueOf(s));
/*    */   
/*    */   }
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 30 */     int phase = (int)(l.getWorld().getFullTime() / 24000L % 8L);
/* 31 */     return this.phases.contains(Integer.valueOf(phase));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\LunarPhaseCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */