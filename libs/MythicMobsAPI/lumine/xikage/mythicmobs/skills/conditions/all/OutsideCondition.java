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
/*    */ @MythicCondition(author = "Ashijin", name = "outside", aliases = {}, description = "If the target has open sky above them")
/*    */ public class OutsideCondition
/*    */   extends SkillCondition
/*    */   implements ILocationCondition {
/*    */   public OutsideCondition(String line, MythicLineConfig mlc) {
/* 16 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractLocation target) {
/* 21 */     Location l = BukkitAdapter.adapt(target);
/* 22 */     return (l.getWorld().getHighestBlockYAt(l) <= l.getY());
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\OutsideCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */