/*    */ package lumine.xikage.mythicmobs.api.bukkit.events;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.all.CustomCondition;
/*    */ import java.util.Optional;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MythicConditionLoadEvent
/*    */   extends Event
/*    */ {
/*    */   private final CustomCondition holder;
/*    */   private String name;
/*    */   private MythicLineConfig config;
/* 23 */   private SkillCondition condition = null;
/*    */   
/*    */   public MythicConditionLoadEvent(CustomCondition holder, String name, MythicLineConfig mlc) {
/* 26 */     this.holder = holder;
/* 27 */     this.name = name;
/* 28 */     this.config = mlc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CustomCondition getContainer() {
/* 37 */     return this.holder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getConditionName() {
/* 45 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MythicLineConfig getConfig() {
/* 54 */     return this.config;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void register(SkillCondition condition) {
/* 64 */     this.condition = condition;
/*    */   }
/*    */   
/*    */   public Optional<SkillCondition> getCondition() {
/* 68 */     return Optional.ofNullable(this.condition);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public HandlerList getHandlers() {
/* 78 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 82 */     return handlers;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\api\bukkit\events\MythicConditionLoadEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */