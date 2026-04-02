/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "closeInventory", description = "Closes the target player's inventory")
/*    */ public class CloseInventoryMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   public CloseInventoryMechanic(String skill, MythicLineConfig mlc) {
/* 17 */     super(skill, mlc);
/* 18 */     this.ASYNC_SAFE = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 23 */     if (target.isPlayer()) {
/* 24 */       ((Player)target.asPlayer().getBukkitEntity()).closeInventory();
/*    */     }
/* 26 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\CloseInventoryMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */