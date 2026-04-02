/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "self", aliases = {"caster", "boss", "mob"}, description = "Targets the caster")
/*    */ public class SelfTargeter
/*    */   extends IEntitySelector {
/*    */   public SelfTargeter(MythicLineConfig mlc) {
/* 14 */     super(mlc);
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 19 */     HashSet<AbstractEntity> targets = new HashSet<>();
/* 20 */     targets.add(data.getCaster().getEntity());
/*    */     
/* 22 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\SelfTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */