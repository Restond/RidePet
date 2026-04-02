/*    */ package lumine.xikage.mythicmobs.api.bukkit.events;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.mobs.MythicMob;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MythicMobSpawnEvent
/*    */   extends Event
/*    */ {
/*    */   private MythicMob type;
/*    */   private Entity entity;
/*    */   private int level;
/*    */   private boolean cancelled = false;
/* 24 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public MythicMobSpawnEvent(MythicMob type, Entity entity, int level) {
/* 27 */     this.type = type;
/* 28 */     this.entity = entity;
/* 29 */     this.level = level;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MythicMob getMobType() {
/* 37 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMobLevel() {
/* 45 */     return this.level;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMobLevel(int level) {
/* 53 */     this.level = level;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Location getLocation() {
/* 61 */     return this.entity.getLocation();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Entity getEntity() {
/* 69 */     return this.entity;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public LivingEntity getLivingEntity() {
/* 74 */     if (BukkitAdapter.adapt(this.entity) instanceof LivingEntity) {
/* 75 */       return (LivingEntity)BukkitAdapter.adapt(this.entity);
/*    */     }
/* 77 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCancelled() {
/* 84 */     this.cancelled = true;
/*    */   }
/*    */   
/*    */   public boolean isCancelled() {
/* 88 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 92 */     return handlers;
/*    */   }
/*    */   public static HandlerList getHandlerList() {
/* 95 */     return handlers;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\api\bukkit\events\MythicMobSpawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */