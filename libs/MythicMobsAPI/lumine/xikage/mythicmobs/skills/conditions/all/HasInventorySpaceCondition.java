/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "hasInventorySpace", aliases = {}, description = "If the target has empty inventory space")
/*    */ public class HasInventorySpaceCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition {
/*    */   public HasInventorySpaceCondition(String line, MythicLineConfig mlc) {
/* 15 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity target) {
/* 20 */     if (!target.isPlayer()) return false;
/*    */     
/* 22 */     return (((Player)target.getBukkitEntity()).getInventory().firstEmpty() > 0);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HasInventorySpaceCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */