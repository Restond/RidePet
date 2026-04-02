/*    */ package lumine.xikage.mythicmobs.skills;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.AuraRegistry;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface SkillCaster
/*    */ {
/*    */   AbstractEntity getEntity();
/*    */   
/*    */   default AbstractLocation getLocation() {
/* 22 */     return getEntity().getLocation();
/*    */   }
/*    */   
/*    */   void setUsingDamageSkill(boolean paramBoolean);
/*    */   
/*    */   boolean isUsingDamageSkill();
/*    */   
/*    */   default int getLevel() {
/* 30 */     return 1;
/*    */   }
/*    */   
/*    */   default float getPower() {
/* 34 */     return 1.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default int getGlobalCooldown() {
/* 42 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void setGlobalCooldown(int ticks) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default AuraRegistry getAuraRegistry() {
/* 55 */     return MythicMobs.inst().getSkillManager().getAuraManager().getAuraRegistry(getEntity());
/*    */   }
/*    */   
/*    */   default void registerAura(String buffName, Aura.AuraTracker buff) {
/* 59 */     if (getAuraRegistry() != null) getAuraRegistry().registerAura(buffName, buff); 
/*    */   }
/*    */   
/*    */   default void unregisterAura(String buffName, Aura.AuraTracker buff) {
/* 63 */     if (getAuraRegistry() != null) getAuraRegistry().unregisterAura(buffName, buff); 
/*    */   }
/*    */   
/*    */   default boolean hasAura(String auraName) {
/* 67 */     return MythicMobs.inst().getSkillManager().getAuraManager().getHasAura(getEntity(), auraName);
/*    */   }
/*    */   
/*    */   default int getAuraStacks(String auraName) {
/* 71 */     return MythicMobs.inst().getSkillManager().getAuraManager().getAuraStacks(getEntity(), auraName);
/*    */   }
/*    */   
/*    */   default void addChild(AbstractEntity entity) {}
/*    */   
/*    */   default Collection<AbstractEntity> getChildren() {
/* 77 */     return Sets.newConcurrentHashSet();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\SkillCaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */