/*    */ package lumine.xikage.mythicmobs.api.bukkit.events;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.drops.Drop;
/*    */ import io.lumine.xikage.mythicmobs.drops.droppables.CustomDrop;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
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
/*    */ public class MythicDropLoadEvent
/*    */   extends Event
/*    */ {
/*    */   private final CustomDrop holder;
/*    */   private String name;
/*    */   private MythicLineConfig config;
/* 23 */   private Drop drop = null;
/*    */   
/*    */   public MythicDropLoadEvent(CustomDrop holder, String name, MythicLineConfig mlc) {
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
/*    */   public CustomDrop getContainer() {
/* 37 */     return this.holder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDropName() {
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
/*    */   public void register(Drop drop) {
/* 64 */     this.drop = drop;
/*    */   }
/*    */   
/*    */   public Optional<Drop> getDrop() {
/* 68 */     return Optional.ofNullable(this.drop);
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


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\api\bukkit\events\MythicDropLoadEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */