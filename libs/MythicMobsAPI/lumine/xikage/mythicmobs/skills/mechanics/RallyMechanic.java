/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.utils.tasks.Scheduler;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.entities.MythicEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "rally", aliases = {"callforhelp"}, description = "Calls for nearby entities to attack the target")
/*    */ public class RallyMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   protected double hRadius;
/*    */   protected double vRadius;
/*    */   protected boolean overwriteTarget;
/* 29 */   HashSet<MythicMob> mmTypes = new HashSet<>();
/* 30 */   HashSet<MythicEntity> meTypes = new HashSet<>();
/*    */   
/*    */   public RallyMechanic(String line, MythicLineConfig mlc) {
/* 33 */     super(line, mlc);
/* 34 */     this.ASYNC_SAFE = false;
/*    */     
/* 36 */     this.hRadius = mlc.getDouble("radius", 10.0D);
/* 37 */     this.vRadius = mlc.getDouble("radius", 10.0D);
/* 38 */     this.hRadius = mlc.getDouble("r", this.hRadius);
/* 39 */     this.vRadius = mlc.getDouble("r", this.vRadius);
/* 40 */     this.hRadius = mlc.getDouble("hradius", this.hRadius);
/* 41 */     this.vRadius = mlc.getDouble("vradius", this.vRadius);
/*    */     
/* 43 */     this.overwriteTarget = mlc.getBoolean(new String[] { "overwritetarget, ot" }, true);
/*    */     
/* 45 */     String typeString = mlc.getString(new String[] { "types", "type", "t" }, "", new String[0]);
/* 46 */     String[] types = typeString.split(",");
/* 47 */     io.lumine.xikage.mythicmobs.skills.mechanics.RallyMechanic THIS = this;
/*    */     
/* 49 */     Scheduler.runLaterSync(() -> { for (String s : paramArrayOfString) { MythicMob mm = MythicMobs.inst().getMobManager().getMythicMob(s); if (mm != null) { paramRallyMechanic.mmTypes.add(mm); } else { MythicEntity me = MythicEntity.getMythicEntity(s); if (me != null) { paramRallyMechanic.meTypes.add(me); } else { MythicLogger.errorMechanicConfig(this, paramMythicLineConfig, "The 'type' attribute must be a valid MythicMob or MythicEntity type."); }  }  }  }5L);
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
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 71 */     List<Entity> moblist = data.getCaster().getEntity().getBukkitEntity().getNearbyEntities(this.hRadius, this.vRadius, this.hRadius);
/*    */ 
/*    */     
/* 74 */     for (Entity e : moblist) {
/* 75 */       if (e instanceof LivingEntity) {
/* 76 */         ActiveMob amx = MythicMobs.inst().getMobManager().getMythicMobInstance(BukkitAdapter.adapt(e));
/*    */         
/* 78 */         if (amx != null && 
/* 79 */           this.mmTypes.contains(amx.getType())) {
/* 80 */           if (!this.overwriteTarget && 
/* 81 */             amx.hasTarget() == true)
/*    */             continue; 
/* 83 */           amx.setTarget(target);
/*    */           
/*    */           continue;
/*    */         } 
/* 87 */         for (MythicEntity me : this.meTypes) {
/* 88 */           if (me.compare(e) == true) {
/* 89 */             MythicMobs.inst().getVolatileCodeHandler().getAIHandler().setTarget((LivingEntity)e, (LivingEntity)BukkitAdapter.adapt(target));
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 95 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\RallyMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */