/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCaster;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class VanillaTargeter
/*    */   extends IEntitySelector {
/*    */   private String selector;
/*    */   
/*    */   public VanillaTargeter(MythicLineConfig mlc, String selector) {
/* 16 */     super(mlc);
/* 17 */     selector = "@" + selector;
/* 18 */     MythicMobs.debug(3, "-- Loaded VANILLA Targeter: " + selector);
/* 19 */     this.selector = selector;
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
/* 24 */     SkillCaster am = data.getCaster();
/* 25 */     return (HashSet<AbstractEntity>)MythicMobs.inst().getVolatileCodeHandler().getEntitiesBySelector(am, this.selector);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\VanillaTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */