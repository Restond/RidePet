/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.Effect;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "effect:flames", aliases = {"flames", "e:flames"}, description = "Creates a flame effect at the target location")
/*    */ public class FlamesEffect
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   public FlamesEffect(String skill, MythicLineConfig mlc) {
/* 19 */     super(skill, mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 24 */     playEffect(target);
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 30 */     playEffect(target.getLocation());
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   protected void playEffect(AbstractLocation l) {
/* 35 */     BukkitAdapter.adapt(l.getWorld()).playEffect(BukkitAdapter.adapt(l), Effect.MOBSPAWNER_FLAMES, 0);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\FlamesEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */