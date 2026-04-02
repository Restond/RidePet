/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class MTPassenger
/*    */   extends IEntitySelector
/*    */ {
/*    */   public MTPassenger(MythicLineConfig mlc) {
/* 13 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 18 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 20 */     if (data.getCaster().getEntity().getPassenger() != null) {
/* 21 */       targets.add(data.getCaster().getEntity().getPassenger());
/*    */     }
/*    */     
/* 24 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MTPassenger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */