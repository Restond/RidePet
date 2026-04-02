/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.skills.IParentSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.AuraMechanic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Tracker
/*    */   extends Aura.AuraTracker
/*    */   implements IParentSkill, Runnable
/*    */ {
/*    */   public Tracker(SkillMetadata data, AbstractEntity entity) {
/* 34 */     super((Aura)paramAuraMechanic, entity, data);
/* 35 */     start();
/*    */   }
/*    */   
/*    */   public Tracker(SkillMetadata data, AbstractLocation location) {
/* 39 */     super((Aura)paramAuraMechanic, location, data);
/* 40 */     start();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\AuraMechanic$Tracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */