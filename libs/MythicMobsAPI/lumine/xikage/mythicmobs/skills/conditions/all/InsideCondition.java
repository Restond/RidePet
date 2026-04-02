/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "inside", aliases = {}, description = "Checks if the target has a block over their head")
/*    */ public class InsideCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition {
/*    */   public InsideCondition(String line, MythicLineConfig mlc) {
/* 16 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation target) {
/* 21 */     Location l = BukkitAdapter.adapt(target);
/* 22 */     return (l.getWorld().getHighestBlockYAt(l) > l.getY());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\InsideCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */