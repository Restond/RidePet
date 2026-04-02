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
/*    */ @MythicTargeter(author = "Ashijin", name = "mount", aliases = {"vehicle"}, description = "Targets the caster's mount")
/*    */ public class MountTargeter
/*    */   extends IEntitySelector {
/*    */   public MountTargeter(MythicLineConfig mlc) {
/* 15 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 20 */     HashSet<AbstractEntity> targets = new HashSet<>();
/* 21 */     if (!(data.getCaster() instanceof ActiveMob)) {
/* 22 */       return targets;
/*    */     }
/* 24 */     ActiveMob am = (ActiveMob)data.getCaster();
/*    */     
/* 26 */     if (am.getMount().isPresent() && 
/* 27 */       !((ActiveMob)am.getMount().get()).isDead()) {
/* 28 */       targets.add(((ActiveMob)am.getMount().get()).getEntity());
/*    */     }
/*    */     
/* 31 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\MountTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */