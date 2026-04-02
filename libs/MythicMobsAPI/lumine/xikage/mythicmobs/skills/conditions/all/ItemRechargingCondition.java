/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "itemRecharging", aliases = {}, description = "Checks if the target's weapon is recharging")
/*    */ public class ItemRechargingCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition {
/*    */   public ItemRechargingCondition(String line, MythicLineConfig mlc) {
/* 16 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 21 */     if (e.isPlayer()) {
/* 22 */       return (MythicMobs.inst().getVolatileCodeHandler().getItemRecharge((Player)e.asPlayer().getBukkitEntity()) != 1.0F);
/*    */     }
/* 24 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\ItemRechargingCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */