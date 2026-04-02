/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class PlayersNearOriginTargeter
/*    */   extends IEntitySelector {
/*    */   double radius;
/*    */   
/*    */   public PlayersNearOriginTargeter(MythicLineConfig mlc) {
/* 16 */     super(mlc);
/* 17 */     this.radius = mlc.getDouble("radius", 5.0D);
/* 18 */     this.radius = mlc.getDouble("r", this.radius);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 23 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 25 */     for (AbstractPlayer p : MythicMobs.inst().getEntityManager().getPlayers(data.getCaster().getEntity().getWorld())) {
/* 26 */       if (data.getCaster().getLocation().getWorld().equals(p.getWorld()) && 
/* 27 */         data.getOrigin().distanceSquared(p.getLocation()) < Math.pow(this.radius, 2.0D)) {
/* 28 */         targets.add(p);
/*    */       }
/*    */     } 
/*    */     
/* 32 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\PlayersNearOriginTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */