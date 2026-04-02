/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "playersInRadius", aliases = {"PIR"}, description = "Targets the players in a radius around the caster")
/*    */ public class PlayersInRadiusTargeter
/*    */   extends IEntitySelector {
/*    */   private double radius;
/*    */   
/*    */   public PlayersInRadiusTargeter(MythicLineConfig mlc) {
/* 19 */     super(mlc);
/*    */     
/* 21 */     this.radius = mlc.getDouble(new String[] { "radius", "r" }, 5.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 26 */     SkillCaster am = data.getCaster();
/* 27 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 29 */     for (AbstractPlayer p : MythicMobs.inst().getEntityManager().getPlayers(am.getEntity().getWorld())) {
/* 30 */       if (p.getWorld().equals(am.getEntity().getWorld()) && 
/* 31 */         am.getEntity().getLocation().distanceSquared(p.getLocation()) < Math.pow(this.radius, 2.0D)) {
/* 32 */         targets.add(p);
/*    */       }
/*    */     } 
/*    */     
/* 36 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\PlayersInRadiusTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */