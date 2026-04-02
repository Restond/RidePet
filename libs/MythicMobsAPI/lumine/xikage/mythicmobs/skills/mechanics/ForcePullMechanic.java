/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "forcepull", description = "Teleports the target entity to the caster")
/*    */ public class ForcePullMechanic extends SkillMechanic implements ITargetedEntitySkill {
/*    */   protected float spread;
/*    */   
/*    */   public ForcePullMechanic(String skill, MythicLineConfig mlc) {
/* 18 */     super(skill, mlc);
/* 19 */     this.ASYNC_SAFE = false;
/*    */     
/* 21 */     this.spread = mlc.getFloat(new String[] { "spread", "s" }, 0.0F);
/* 22 */     this.spreadV = mlc.getFloat(new String[] { "vspread", "vs" }, this.spread);
/*    */   }
/*    */   
/*    */   protected float spreadV;
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 28 */     if (this.spread > 0.0F) {
/*    */ 
/*    */       
/* 31 */       if (this.sourceIsOrigin == true) {
/* 32 */         location = data.getCaster().getLocation();
/*    */       } else {
/* 34 */         location = data.getOrigin();
/*    */       } 
/*    */       
/* 37 */       MythicMobs.inst().getMobManager(); AbstractLocation location = MobManager.findSafeSpawnLocation(location, (int)this.spread, (int)this.spreadV, 1);
/*    */       
/* 39 */       target.teleport(location);
/*    */     }
/* 41 */     else if (this.sourceIsOrigin == true) {
/* 42 */       target.teleport(data.getOrigin());
/*    */     } else {
/* 44 */       target.teleport(data.getCaster().getLocation());
/*    */     } 
/*    */ 
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ForcePullMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */