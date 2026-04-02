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
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "breakBlock", aliases = {"blockBreak"}, description = "Breaks the block at the target location")
/*    */ public class BreakBlockMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*    */ {
/*    */   public BreakBlockMechanic(String skill, MythicLineConfig mlc) {
/* 21 */     super(skill, mlc);
/* 22 */     this.ASYNC_SAFE = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 27 */     playEffect(target.getLocation());
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 33 */     playEffect(target);
/* 34 */     return true;
/*    */   }
/*    */   
/*    */   public void playEffect(AbstractLocation location) {
/* 38 */     Location l = BukkitAdapter.adapt(location);
/* 39 */     l.getBlock().breakNaturally();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BreakBlockMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */