/*    */ package lumine.xikage.mythicmobs.skills.auras;
/*    */ 
/*    */ import io.lumine.utils.tasks.Scheduler;
/*    */ import io.lumine.utils.terminable.Terminable;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillManager;
/*    */ import io.lumine.xikage.mythicmobs.skills.auras.AuraRegistry;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AuraManager
/*    */ {
/*    */   private final MythicMobs core;
/*    */   private final SkillManager skillManager;
/* 19 */   private ConcurrentHashMap<UUID, AuraRegistry> auraRegistries = new ConcurrentHashMap<>();
/*    */   private Terminable garbageCollector;
/*    */   
/*    */   public AuraManager(MythicMobs core, SkillManager skillManager) {
/* 23 */     this.core = core;
/* 24 */     this.skillManager = skillManager;
/*    */     
/* 26 */     this.garbageCollector = (Terminable)Scheduler.runTaskRepeatingAsync(() -> this.auraRegistries.values().forEach(()), 20L, 100L);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void shutdown() {
/* 34 */     this.garbageCollector.terminate();
/*    */   }
/*    */   
/*    */   public AuraRegistry getAuraRegistry(AbstractEntity entity) {
/* 38 */     return getAuraRegistry(entity.getUniqueId());
/*    */   }
/*    */   
/*    */   public AuraRegistry getAuraRegistry(UUID uuid) {
/* 42 */     if (this.auraRegistries.containsKey(uuid)) {
/* 43 */       return this.auraRegistries.get(uuid);
/*    */     }
/*    */     
/* 46 */     AuraRegistry registry = new AuraRegistry(uuid);
/*    */     
/* 48 */     this.auraRegistries.put(uuid, registry);
/* 49 */     return registry;
/*    */   }
/*    */   
/*    */   public boolean getHasAura(AbstractEntity entity, String name) {
/* 53 */     if (!this.auraRegistries.containsKey(entity.getUniqueId())) {
/* 54 */       return false;
/*    */     }
/* 56 */     AuraRegistry registry = this.auraRegistries.get(entity.getUniqueId());
/* 57 */     return registry.hasAura(name);
/*    */   }
/*    */   
/*    */   public int getAuraStacks(AbstractEntity entity, String name) {
/* 61 */     if (!this.auraRegistries.containsKey(entity.getUniqueId())) {
/* 62 */       return 0;
/*    */     }
/* 64 */     AuraRegistry registry = this.auraRegistries.get(entity.getUniqueId());
/* 65 */     return registry.getStacks(name);
/*    */   }
/*    */   
/*    */   public void removeAuraStacks(AbstractEntity entity, String buffName, int a) {
/* 69 */     if (!this.auraRegistries.containsKey(entity.getUniqueId())) {
/*    */       return;
/*    */     }
/* 72 */     AuraRegistry registry = this.auraRegistries.get(entity.getUniqueId());
/* 73 */     registry.removeStack(buffName, a);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\auras\AuraManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */