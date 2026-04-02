/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.utils.tasks.Scheduler;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.Optional;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ @MythicMechanic(author = "BerndiVader", version = "4.8", name = "bloodyScreen", aliases = {"effect:bloodyScreen", "e:bloodyScreen", "redScreen", "effect:redScreen", "e:redScreen"}, description = "Causes the player's screen to be covered in blood")
/*    */ public class BloodyScreenEffect
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/* 19 */   private static final AbstractLocation LOCATION = new AbstractLocation(null, 99999.0D, 0.0D, 99999.0D);
/*    */   
/*    */   @MythicField(name = "duration", aliases = {"d"}, version = "4.8", description = "How long the effect should last")
/*    */   private int duration;
/*    */   @MythicField(name = "cancel", aliases = {"c"}, version = "4.8", description = "If true, will cancel any existing effects immediately")
/*    */   private boolean cancel;
/*    */   
/*    */   public BloodyScreenEffect(String skill, MythicLineConfig mlc) {
/* 27 */     super(skill, mlc);
/* 28 */     this.ASYNC_SAFE = false;
/*    */     
/* 30 */     this.duration = mlc.getInteger(new String[] { "duration", "d" }, 0);
/* 31 */     this.cancel = mlc.getBoolean(new String[] { "cancel", "c" }, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 36 */     if (!target.isPlayer()) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     Optional<Object> maybeTask = target.getMetadata("bloodyscreeneffect");
/*    */     
/* 42 */     if (maybeTask.isPresent()) {
/* 43 */       CompletableFuture<Void> task = (CompletableFuture<Void>)maybeTask.get();
/* 44 */       task.cancel(true);
/*    */     } 
/*    */     
/* 47 */     if (this.cancel) {
/* 48 */       getPlugin().getVolatileCodeHandler().getEntityHandler().setPlayerWorldBorder(target.asPlayer(), null, -1);
/* 49 */       return true;
/* 50 */     }  if (this.duration == 0) {
/* 51 */       getPlugin().getVolatileCodeHandler().getEntityHandler().setPlayerWorldBorder(target.asPlayer(), LOCATION, 1);
/*    */     } else {
/* 53 */       getPlugin().getVolatileCodeHandler().getEntityHandler().setPlayerWorldBorder(target.asPlayer(), LOCATION, 1);
/*    */       
/* 55 */       target.setMetadata("bloodyscreeneffect", Scheduler.runLaterSync(() -> { if (paramAbstractEntity.asPlayer().isOnline()) getPlugin().getVolatileCodeHandler().getEntityHandler().setPlayerWorldBorder(paramAbstractEntity.asPlayer(), null, -1);  }this.duration));
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BloodyScreenEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */