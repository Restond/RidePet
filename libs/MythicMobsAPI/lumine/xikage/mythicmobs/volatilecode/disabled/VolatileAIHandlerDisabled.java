/*    */ package lumine.xikage.mythicmobs.volatilecode.disabled;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.volatilecode.handlers.VolatileAIHandler;
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.Creature;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VolatileAIHandlerDisabled
/*    */   implements VolatileAIHandler
/*    */ {
/*    */   public void setTarget(LivingEntity entity, LivingEntity target) {
/* 14 */     if (target == null)
/* 15 */       return;  if (entity instanceof Creature)
/* 16 */       ((Creature)entity).setTarget(target); 
/*    */   }
/*    */   
/*    */   public void addPathfinderGoals(LivingEntity entity, List<String> aiMods) {}
/*    */   
/*    */   public void addTargetGoals(LivingEntity entity, List<String> aiMods) {}
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\volatilecode\disabled\VolatileAIHandlerDisabled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */