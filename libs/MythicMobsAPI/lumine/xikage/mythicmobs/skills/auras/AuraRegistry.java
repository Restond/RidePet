/*     */ package lumine.xikage.mythicmobs.skills.auras;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import io.lumine.xikage.mythicmobs.skills.auras.Aura;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AuraRegistry
/*     */ {
/*     */   private final UUID holder;
/*  16 */   private Map<String, Queue<Aura.AuraTracker>> auras = Maps.newConcurrentMap();
/*  17 */   private long lastAction = System.currentTimeMillis();
/*     */   
/*     */   public AuraRegistry(UUID uuid) {
/*  20 */     this.holder = uuid;
/*     */   }
/*     */   
/*     */   public void registerAura(String name, Aura.AuraTracker buff) {
/*  24 */     this.auras.compute(name, (key, value) -> {
/*     */           if (value == null) {
/*     */             value = new LinkedList();
/*     */           }
/*     */           value.add(paramAuraTracker);
/*     */           return value;
/*     */         });
/*     */   }
/*     */   
/*     */   public boolean mergeAura(String name, Aura.AuraTracker buff, boolean sameCasterOnly) {
/*  34 */     boolean merged = false;
/*     */     
/*  36 */     if (!this.auras.containsKey(name)) {
/*  37 */       this.auras.put(name, new LinkedList<>());
/*     */     }
/*  39 */     Queue<Aura.AuraTracker> value = this.auras.get(name);
/*     */     
/*  41 */     Aura.AuraTracker find = null;
/*  42 */     if (value.size() > 0) {
/*  43 */       if (sameCasterOnly) {
/*  44 */         for (Aura.AuraTracker t : value) {
/*  45 */           if (t.getCasterUUID().equals(buff.getCasterUUID())) {
/*  46 */             find = t;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } else {
/*  51 */         find = value.peek();
/*     */       } 
/*     */     }
/*  54 */     if (find != null) {
/*  55 */       merged = true;
/*  56 */       find.merge(buff);
/*     */     } else {
/*  58 */       value.add(buff);
/*     */     } 
/*     */     
/*  61 */     return merged;
/*     */   }
/*     */   
/*     */   public void unregisterAura(String name, Aura.AuraTracker buff) {
/*  65 */     if (this.auras.containsKey(name)) {
/*  66 */       ((Queue)this.auras.get(name)).remove(buff);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasAura(String name) {
/*  71 */     if (this.auras.containsKey(name)) {
/*  72 */       return (((Queue)this.auras.get(name)).size() > 0);
/*     */     }
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStacks(String name) {
/*  79 */     if (this.auras.containsKey(name)) {
/*  80 */       Queue<Aura.AuraTracker> q = this.auras.get(name);
/*     */       
/*  82 */       if (q.size() > 0) {
/*  83 */         return q.stream().mapToInt(Aura.AuraTracker::getStacks).sum();
/*     */       }
/*     */     } 
/*  86 */     return 0;
/*     */   }
/*     */   
/*     */   public void removeStack(String buffName, int a) {
/*  90 */     if (this.auras.containsKey(buffName)) {
/*  91 */       Queue<Aura.AuraTracker> queue = this.auras.get(buffName);
/*     */       
/*  93 */       if (queue.size() <= a) {
/*  94 */         queue.forEach(tracker -> tracker.terminateFromRegistry());
/*  95 */         queue.clear();
/*     */       } else {
/*  97 */         for (int i = 0; i < a; i++) {
/*  98 */           Aura.AuraTracker tracker = queue.poll();
/*  99 */           tracker.terminateFromRegistry();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeAll(String buffName) {
/* 106 */     if (this.auras.containsKey(buffName)) {
/* 107 */       Queue<Aura.AuraTracker> queue = this.auras.get(buffName);
/* 108 */       queue.forEach(tracker -> tracker.terminateFromRegistry());
/*     */ 
/*     */       
/* 111 */       queue.clear();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\auras\AuraRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */