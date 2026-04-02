/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class MTPlayersOnServer
/*    */   extends IEntitySelector {
/*    */   public MTPlayersOnServer(MythicLineConfig mlc) {
/* 13 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 18 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 20 */     targets.addAll(MythicMobs.inst().server().getOnlinePlayers());
/* 21 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MTPlayersOnServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */