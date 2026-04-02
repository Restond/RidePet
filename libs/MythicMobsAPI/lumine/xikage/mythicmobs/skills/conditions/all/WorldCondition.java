/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "world", aliases = {}, description = "Checks the name of the target world.")
/*    */ public class WorldCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition {
/* 15 */   private Set<String> data = new HashSet<>();
/*    */   
/*    */   public WorldCondition(String line, MythicLineConfig mlc) {
/* 18 */     super(line);
/* 19 */     for (String s : this.conditionVar.split(",")) {
/* 20 */       this.data.add(s);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation l) {
/* 26 */     return this.data.contains(l.getWorld().getName());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\WorldCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */