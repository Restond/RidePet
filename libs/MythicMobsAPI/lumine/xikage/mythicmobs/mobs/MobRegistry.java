/*    */ package lumine.xikage.mythicmobs.mobs;
/*    */ 
/*    */ import io.lumine.utils.terminable.Terminable;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import java.util.Collection;
/*    */ import java.util.Optional;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ public class MobRegistry
/*    */   implements Terminable
/*    */ {
/* 13 */   private transient ConcurrentHashMap<UUID, ActiveMob> activeMobs = new ConcurrentHashMap<>();
/* 14 */   private ConcurrentHashMap<UUID, ActiveMob> persistentMobs = new ConcurrentHashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void loadSaved() {
/* 19 */     this.activeMobs.putAll(this.persistentMobs);
/* 20 */     this.activeMobs.values().removeIf(mob -> !mob.loadSaved());
/*    */   }
/*    */   
/*    */   public boolean terminate() {
/* 24 */     this.activeMobs.values().removeIf(mob -> mob.getType().getDespawns());
/* 25 */     this.activeMobs.values().forEach(mob -> mob.getVariables().unload());
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isActiveMob(UUID uuid) {
/* 35 */     if (uuid == null) {
/* 36 */       return false;
/*    */     }
/* 38 */     if (this.activeMobs.containsKey(uuid)) {
/* 39 */       if (((ActiveMob)this.activeMobs.get(uuid)).getType() != null) {
/* 40 */         return true;
/*    */       }
/* 42 */       this.activeMobs.remove(uuid);
/*    */     } 
/*    */     
/* 45 */     if (this.persistentMobs.containsKey(uuid)) {
/* 46 */       ActiveMob am = this.persistentMobs.get(uuid);
/* 47 */       this.activeMobs.put(uuid, am);
/* 48 */       am.loadSaved();
/* 49 */       return true;
/*    */     } 
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Optional<ActiveMob> getActiveMob(UUID uuid) {
/* 60 */     if (isActiveMob(uuid)) {
/* 61 */       return Optional.ofNullable(this.activeMobs.getOrDefault(uuid, null));
/*    */     }
/* 63 */     return Optional.empty();
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<ActiveMob> values() {
/* 68 */     return this.activeMobs.values();
/*    */   }
/*    */   
/*    */   public void put(UUID uuid, ActiveMob mob) {
/* 72 */     this.activeMobs.put(uuid, mob);
/* 73 */     if (!mob.getType().getDespawns()) {
/* 74 */       this.persistentMobs.put(uuid, mob);
/*    */     }
/*    */   }
/*    */   
/*    */   public void setInactive(UUID uuid) {
/* 79 */     this.activeMobs.remove(uuid);
/*    */   }
/*    */   
/*    */   public void removeMob(UUID uuid) {
/* 83 */     this.activeMobs.remove(uuid);
/* 84 */     this.persistentMobs.remove(uuid);
/*    */   }
/*    */   
/*    */   public ActiveMob get(UUID uniqueId) {
/* 88 */     if (isActiveMob(uniqueId)) {
/* 89 */       return this.activeMobs.get(uniqueId);
/*    */     }
/* 91 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\MobRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */