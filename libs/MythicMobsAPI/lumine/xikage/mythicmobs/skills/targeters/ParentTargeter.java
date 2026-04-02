/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "parent", aliases = {"summoner"}, description = "Targets the caster's parent/summoner")
/*    */ public class ParentTargeter
/*    */   extends IEntitySelector {
/*    */   public ParentTargeter(MythicLineConfig mlc) {
/* 15 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 20 */     HashSet<AbstractEntity> targets = new HashSet<>();
/*    */     
/* 22 */     if (!(data.getCaster() instanceof ActiveMob)) {
/* 23 */       return targets;
/*    */     }
/* 25 */     ActiveMob am = (ActiveMob)data.getCaster();
/* 26 */     if (am.getParent() != null && 
/* 27 */       !am.getParent().getEntity().isDead()) {
/* 28 */       targets.add(am.getParent().getEntity());
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\ParentTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */