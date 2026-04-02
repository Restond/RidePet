/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.utils.tasks.Scheduler;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class MobsNearOriginTargeter
/*    */   extends IEntitySelector
/*    */ {
/*    */   double radius;
/* 20 */   HashSet<MythicMob> mmTypes = new HashSet<>();
/* 21 */   HashSet<MythicEntity> meTypes = new HashSet<>();
/*    */   
/*    */   public MobsNearOriginTargeter(MythicLineConfig mlc) {
/* 24 */     super(mlc);
/* 25 */     this.radius = mlc.getDouble("radius", 5.0D);
/* 26 */     this.radius = mlc.getDouble("r", this.radius);
/*    */     
/* 28 */     String types = mlc.getString("types", null);
/* 29 */     types = mlc.getString("type", types);
/* 30 */     types = mlc.getString("t", types);
/*    */     
/* 32 */     String[] ss = types.split(",");
/*    */     
/* 34 */     io.lumine.xikage.mythicmobs.skills.targeters.MobsNearOriginTargeter THIS = this;
/*    */     
/* 36 */     Scheduler.runLaterSync(() -> { for (String s : paramArrayOfString) { MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(s); if (mm != null) { paramMobsNearOriginTargeter.mmTypes.add(mm); } else { MythicEntity me = MythicEntity.getMythicEntity(s); if (me != null) { paramMobsNearOriginTargeter.meTypes.add(me); } else { MythicLogger.errorTargeterConfig((SkillTargeter)this, paramMythicLineConfig, "The 'type' attribute must be a valid MythicMob or MythicEntity type."); }  }  }  }5L);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 57 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */ 
/*    */     
/* 60 */     for (AbstractEntity p : MythicMobs.inst().getEntityManager().getLivingEntities(data.getCaster().getEntity().getWorld())) {
/* 61 */       if (p.getUniqueId().equals(data.getCaster().getEntity().getUniqueId()) || 
/* 62 */         !p.getWorld().equals(data.getCaster().getEntity().getWorld()))
/*    */         continue; 
/* 64 */       if (data.getOrigin().distanceSquared(p.getLocation()) < Math.pow(this.radius, 2.0D)) {
/*    */         
/* 66 */         ActiveMob amx = MythicMobs.inst().getMobManager().getMythicMobInstance(p);
/*    */         
/* 68 */         if (amx != null) {
/* 69 */           if (this.mmTypes.contains(amx.getType())) {
/* 70 */             targets.add(p);
/* 71 */             amx = null;
/*    */           } 
/*    */           continue;
/*    */         } 
/* 75 */         for (MythicEntity me : this.meTypes) {
/* 76 */           if (me.compare(p.getBukkitEntity()) == true) {
/* 77 */             targets.add(p);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 86 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MobsNearOriginTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */