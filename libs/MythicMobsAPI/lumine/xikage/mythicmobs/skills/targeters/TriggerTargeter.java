/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "trigger", aliases = {}, description = "Targets the entity that triggered the skill")
/*    */ public class TriggerTargeter
/*    */   extends IEntitySelector {
/*    */   public TriggerTargeter(MythicLineConfig mlc) {
/* 14 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 19 */     HashSet<AbstractEntity> targets = new HashSet<>();
/* 20 */     targets.add(data.getTrigger());
/*    */     
/* 22 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\TriggerTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */