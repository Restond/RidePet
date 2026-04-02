/*    */ package lumine.xikage.mythicmobs.api.bukkit.events;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.CustomTargeter;
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
/*    */ public class MythicTargeterLoadEvent
/*    */   extends Event
/*    */ {
/*    */   private final CustomTargeter holder;
/*    */   private String name;
/*    */   private MythicLineConfig config;
/* 23 */   private SkillTargeter targeter = null;
/*    */   
/*    */   public MythicTargeterLoadEvent(CustomTargeter holder, String name, MythicLineConfig mlc) {
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
/*    */   public CustomTargeter getContainer() {
/* 38 */     return this.holder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTargeterName() {
/* 46 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MythicLineConfig getConfig() {
/* 55 */     return this.config;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void register(SkillTargeter targeter) {
/* 65 */     this.targeter = targeter;
/*    */   }
/*    */   
/*    */   public Optional<SkillTargeter> getTargeter() {
/* 69 */     return Optional.ofNullable(this.targeter);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 76 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public HandlerList getHandlers() {
/* 79 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 83 */     return handlers;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\api\bukkit\events\MythicTargeterLoadEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */