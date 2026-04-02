/*    */ package saukiya.sxattribute.data.condition;
/*    */ import github.saukiya.sxattribute.data.condition.ConditionMap;
/*    */ import github.saukiya.sxattribute.data.condition.SubCondition;
/*    */ import github.saukiya.sxattribute.data.condition.sub.AttackSpeedCondition;
/*    */ import github.saukiya.sxattribute.data.condition.sub.DurabilityCondition;
/*    */ import github.saukiya.sxattribute.data.condition.sub.ExpiryTimeCondition;
/*    */ import github.saukiya.sxattribute.data.condition.sub.HandCondition;
/*    */ import github.saukiya.sxattribute.data.condition.sub.MainHandCondition;
/*    */ import github.saukiya.sxattribute.data.condition.sub.OffHandCondition;
/*    */ import github.saukiya.sxattribute.data.condition.sub.RoleCondition;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class SXConditionManager {
/* 14 */   private final ConditionMap conditionMap = SubCondition.conditionMap; public ConditionMap getConditionMap() { return this.conditionMap; }
/*    */ 
/*    */   
/*    */   public SXConditionManager(JavaPlugin plugin) {
/* 18 */     (new MainHandCondition()).registerCondition(plugin);
/* 19 */     (new OffHandCondition()).registerCondition(plugin);
/* 20 */     (new HandCondition()).registerCondition(plugin);
/* 21 */     (new LimitLevelCondition()).registerCondition(plugin);
/* 22 */     (new RoleCondition()).registerCondition(plugin);
/* 23 */     (new ExpiryTimeCondition()).registerCondition(plugin);
/* 24 */     (new AttackSpeedCondition()).registerCondition(plugin);
/* 25 */     (new DurabilityCondition()).registerCondition(plugin);
/*    */   }
/*    */   
/*    */   public void onConditionEnable() {
/* 29 */     this.conditionMap.values().forEach(SubCondition::onEnable);
/*    */   }
/*    */   
/*    */   public void onConditionDisable() {
/* 33 */     this.conditionMap.values().forEach(SubCondition::onDisable);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\SXAttributeAPI\!\saukiya\sxattribute\data\condition\SXConditionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */