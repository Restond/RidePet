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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "effect:enderbeam", aliases = {"enderbeam"}, description = "Creates an endercrystal beam pointing at the target")
/*    */ public class EnderBeamMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*    */ {
/*    */   protected int duration;
/*    */   protected float yOffset;
/*    */   
/*    */   public EnderBeamMechanic(String skill, MythicLineConfig mlc) {
/* 27 */     super(skill, mlc);
/* 28 */     this.ASYNC_SAFE = false;
/*    */     
/* 30 */     this.duration = mlc.getInteger(new String[] { "duration", "d" }, 60);
/* 31 */     this.yOffset = mlc.getFloat(new String[] { "yoffset", "yo", "y" }, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 36 */     new Animator(this, BukkitAdapter.adapt(data.getCaster().getEntity()), BukkitAdapter.adapt(target), this.duration);
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 42 */     new Animator(this, BukkitAdapter.adapt(data.getCaster().getEntity()), BukkitAdapter.adapt(target), this.duration);
/* 43 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\EnderBeamMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */