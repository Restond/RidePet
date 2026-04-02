/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "children", aliases = {"child", "summons"}, description = "Targets any child entities summoned by the caster")
/*    */ public class ChildrenTargeter
/*    */   extends IEntitySelector
/*    */ {
/*    */   public ChildrenTargeter(MythicLineConfig mlc) {
/* 15 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 20 */     HashSet<AbstractEntity> targets = new HashSet<>();
/* 21 */     targets.addAll(data.getCaster().getChildren());
/* 22 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\ChildrenTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */