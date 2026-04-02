/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MobManager;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "teleport", aliases = {"tp"}, description = "Teleports to the target location")
/*    */ public class TeleportMechanic extends SkillMechanic implements ITargetedEntitySkill, ITargetedLocationSkill {
/*    */   protected double spreadH;
/*    */   
/*    */   public TeleportMechanic(String line, MythicLineConfig mlc) {
/* 20 */     super(line, mlc);
/* 21 */     this.ASYNC_SAFE = false;
/*    */     
/* 23 */     this.spreadH = mlc.getDouble("spreadh", 0.0D);
/* 24 */     this.spreadH = mlc.getDouble("sh", this.spreadH);
/*    */     
/* 26 */     this.spreadV = mlc.getDouble("spreadv", 0.0D);
/* 27 */     this.spreadV = mlc.getDouble("sv", this.spreadV);
/*    */   }
/*    */   protected double spreadV;
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 32 */     if (data.getCaster() instanceof ActiveMob) {
/* 33 */       ActiveMob am = (ActiveMob)data.getCaster();
/* 34 */       MythicMobs.inst().getMobManager(); am.getEntity().teleport(MobManager.findSafeSpawnLocation(target.getLocation(), (int)this.spreadH, (int)this.spreadV, am.getType().getMythicEntity().getHeight() + 1));
/*    */     } else {
/* 36 */       MythicMobs.inst().getMobManager(); data.getCaster().getEntity().teleport(MobManager.findSafeSpawnLocation(target.getLocation(), (int)this.spreadH, (int)this.spreadV, 2));
/*    */     } 
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean castAtLocation(SkillMetadata data, AbstractLocation target) {
/* 44 */     if (this.spreadH > 0.0D || this.spreadV > 0.0D) {
/* 45 */       if (data.getCaster() instanceof ActiveMob) {
/* 46 */         ActiveMob am = (ActiveMob)data.getCaster();
/* 47 */         MythicMobs.inst().getMobManager(); target = MobManager.findSafeSpawnLocation(target, (int)this.spreadH, (int)this.spreadV, am.getType().getMythicEntity().getHeight() + 1);
/*    */       } else {
/* 49 */         MythicMobs.inst().getMobManager(); target = MobManager.findSafeSpawnLocation(target, (int)this.spreadH, (int)this.spreadV, 2);
/*    */       } 
/*    */     }
/*    */     
/* 53 */     data.getCaster().getEntity().teleport(target);
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\TeleportMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */