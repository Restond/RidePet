/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class MTMobsInRadius extends IEntitySelector {
/*    */   private double radius;
/*    */   private double radiusSq;
/* 19 */   private HashSet<MythicMob> mmTypes = new HashSet<>();
/* 20 */   private HashSet<MythicEntity> meTypes = new HashSet<>();
/*    */   
/*    */   public MTMobsInRadius(MythicLineConfig mlc) {
/* 23 */     super(mlc);
/*    */     
/* 25 */     this.radius = mlc.getDouble(new String[] { "radius", "r" }, 5.0D);
/* 26 */     this.radiusSq = Math.pow(this.radius, 2.0D);
/*    */     
/* 28 */     String types = mlc.getString(new String[] { "types", "type", "t" }, "", new String[0]);
/* 29 */     String[] ss = types.split(",");
/*    */     
/* 31 */     io.lumine.xikage.mythicmobs.skills.targeters.MTMobsInRadius THIS = this;
/*    */     
/* 33 */     Schedulers.sync().runLater(() -> { for (String s : paramArrayOfString) { MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(s); if (mm != null) { paramMTMobsInRadius.mmTypes.add(mm); } else { MythicEntity me = MythicEntity.getMythicEntity(s); if (me != null) { paramMTMobsInRadius.meTypes.add(me); } else { MythicLogger.errorTargeterConfig((SkillTargeter)this, paramMythicLineConfig, "The 'type' attribute must be a valid MythicMob or MythicEntity type."); }  }  }  MythicMobs.debug(3, "@MIR targeter loaded targeting " + (paramMTMobsInRadius.meTypes.size() + paramMTMobsInRadius.mmTypes.size()) + " types"); }5L);
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
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 55 */     SkillCaster am = data.getCaster();
/* 56 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */ 
/*    */     
/* 59 */     for (AbstractEntity p : MythicMobs.inst().getEntityManager().getLivingEntities(am.getEntity().getWorld())) {
/* 60 */       if (p.getUniqueId().equals(am.getEntity().getUniqueId())) {
/*    */         continue;
/*    */       }
/* 63 */       if (!p.getWorld().equals(am.getEntity().getWorld())) {
/*    */         continue;
/*    */       }
/* 66 */       if (am.getEntity().getLocation().distanceSquared(p.getLocation()) <= this.radiusSq) {
/*    */         
/* 68 */         ActiveMob amx = getPlugin().getMobManager().getMythicMobInstance(p);
/*    */         
/* 70 */         if (amx != null) {
/* 71 */           if (this.mmTypes.contains(amx.getType())) {
/* 72 */             targets.add(p);
/* 73 */             amx = null;
/*    */           } 
/*    */           continue;
/*    */         } 
/* 77 */         for (MythicEntity me : this.meTypes) {
/* 78 */           if (me.compare(p.getBukkitEntity()) == true) {
/* 79 */             targets.add(p);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 86 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MTMobsInRadius.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */