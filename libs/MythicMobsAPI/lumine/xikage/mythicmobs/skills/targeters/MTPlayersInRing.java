/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class MTPlayersInRing extends IEntitySelector {
/*    */   double min;
/*    */   double max;
/*    */   
/*    */   public MTPlayersInRing(MythicLineConfig mlc) {
/* 17 */     super(mlc);
/* 18 */     this.min = mlc.getDouble("min", 10.0D);
/* 19 */     this.min = mlc.getDouble("minrange", this.min);
/* 20 */     this.max = mlc.getDouble("max", 5.0D);
/* 21 */     this.max = mlc.getDouble("maxrange", this.max);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 26 */     SkillCaster am = data.getCaster();
/* 27 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 29 */     for (AbstractPlayer p : MythicMobs.inst().getEntityManager().getPlayers(am.getEntity().getWorld())) {
/* 30 */       if (am.getLocation().getWorld().equals(p.getWorld()) && 
/* 31 */         am.getEntity().getLocation().distanceSquared(p.getLocation()) < Math.pow(this.max, 2.0D) && 
/* 32 */         am.getEntity().getLocation().distanceSquared(p.getLocation()) > Math.pow(this.min, 2.0D)) {
/* 33 */         targets.add(p);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 38 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MTPlayersInRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */