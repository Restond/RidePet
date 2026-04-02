/*    */ package lumine.xikage.mythicmobs.api.bukkit.events;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MythicMobDespawnEvent
/*    */   extends Event
/*    */ {
/*    */   private final ActiveMob mob;
/* 19 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public MythicMobDespawnEvent(ActiveMob am) {
/* 22 */     this.mob = am;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ActiveMob getMob() {
/* 30 */     return this.mob;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity getEntity() {
/* 38 */     return BukkitAdapter.adapt(this.mob.getEntity());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MythicMob getMobType() {
/* 46 */     return this.mob.getType();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HandlerList getHandlers() {
/* 52 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 56 */     return handlers;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\api\bukkit\events\MythicMobDespawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */