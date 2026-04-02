/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "mounted", aliases = {}, description = "If the target entity is riding a mount/vehicle")
/*    */ public class MountedCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   public MountedCondition(String line, MythicLineConfig mlc) {
/* 13 */     super(line);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 18 */     return (e.getBukkitEntity().getVehicle() != null);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\MountedCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */