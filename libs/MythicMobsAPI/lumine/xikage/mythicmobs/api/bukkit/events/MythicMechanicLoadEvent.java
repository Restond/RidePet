/*    */ package lumine.xikage.mythicmobs.api.bukkit.events;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.CustomMechanic;
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
/*    */ public class MythicMechanicLoadEvent
/*    */   extends Event
/*    */ {
/*    */   private final CustomMechanic holder;
/*    */   private String name;
/*    */   private MythicLineConfig config;
/* 23 */   private SkillMechanic mechanic = null;
/*    */   
/*    */   public MythicMechanicLoadEvent(CustomMechanic holder, String name, MythicLineConfig mlc) {
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
/*    */   
/*    */   public CustomMechanic getContainer() {
/* 38 */     return this.holder;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public CustomMechanic getHolder() {
/* 43 */     return this.holder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMechanicName() {
/* 51 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MythicLineConfig getConfig() {
/* 60 */     return this.config;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void register(SkillMechanic mechanic) {
/* 70 */     this.mechanic = mechanic;
/*    */   }
/*    */   
/*    */   public Optional<SkillMechanic> getMechanic() {
/* 74 */     return Optional.ofNullable(this.mechanic);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 81 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public HandlerList getHandlers() {
/* 84 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 88 */     return handlers;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\api\bukkit\events\MythicMechanicLoadEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */