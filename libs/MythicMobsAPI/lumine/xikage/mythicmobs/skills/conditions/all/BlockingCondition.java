/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "blocking", aliases = {"isblocking"}, version = "4.4", description = "Tests if the target entity is blocking with a shield")
/*    */ public class BlockingCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition {
/*    */   public BlockingCondition(String line, MythicLineConfig mlc) {
/* 15 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 20 */     if (!e.isPlayer()) return false;
/*    */     
/* 22 */     return ((Player)e.getBukkitEntity()).isBlocking();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\BlockingCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */