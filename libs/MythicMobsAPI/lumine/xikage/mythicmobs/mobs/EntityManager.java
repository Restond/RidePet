/*    */ package lumine.xikage.mythicmobs.mobs;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractPlayer;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityManager
/*    */   implements Listener
/*    */ {
/* 29 */   private Set<AbstractPlayer> cachedAllPlayers = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*    */   private ConcurrentHashMap<AbstractWorld, List<AbstractEntity>> cachedLivingEntities;
/*    */   private ConcurrentHashMap<AbstractWorld, List<AbstractPlayer>> cachedPlayers;
/* 32 */   private Set<Player> onlinePlayers = Collections.newSetFromMap(new ConcurrentHashMap<>()); public Set<Player> getOnlinePlayers() { return this.onlinePlayers; }
/*    */   
/*    */   public EntityManager() {
/* 35 */     this.cachedPlayers = new ConcurrentHashMap<>();
/* 36 */     this.cachedLivingEntities = new ConcurrentHashMap<>();
/*    */     
/* 38 */     refreshCaches();
/*    */   }
/*    */   
/*    */   public synchronized void refreshCaches() {
/* 42 */     this.cachedAllPlayers.clear();
/* 43 */     this.cachedPlayers.clear();
/* 44 */     this.cachedLivingEntities.clear();
/*    */     
/* 46 */     for (AbstractWorld world : MythicMobs.inst().server().getWorlds()) {
/* 47 */       this.cachedAllPlayers.addAll(world.getPlayers());
/* 48 */       this.cachedPlayers.put(world, world.getPlayers());
/* 49 */       this.cachedLivingEntities.put(world, world.getLivingEntities());
/*    */     } 
/*    */   }
/*    */   
/*    */   public synchronized List<AbstractEntity> getLivingEntities(AbstractWorld world) {
/* 54 */     return this.cachedLivingEntities.get(world);
/*    */   }
/*    */   
/*    */   public synchronized Collection<AbstractPlayer> getPlayers() {
/* 58 */     return this.cachedAllPlayers;
/*    */   }
/*    */   
/*    */   public synchronized List<AbstractPlayer> getPlayers(AbstractWorld world) {
/* 62 */     return this.cachedPlayers.get(world);
/*    */   }
/*    */   
/*    */   public synchronized void registerMob(AbstractWorld world, AbstractEntity entity) {
/* 66 */     ((List<AbstractEntity>)this.cachedLivingEntities.get(world)).add(entity);
/*    */   }
/*    */   
/*    */   public Set<AbstractPlayer> getPlayersInRangeSq(AbstractLocation location, int rangeSq) {
/* 70 */     Set<AbstractPlayer> inRange = new HashSet<>();
/*    */     
/*    */     try {
/* 73 */       if (!this.cachedPlayers.containsKey(location.getWorld())) {
/* 74 */         return Collections.emptySet();
/*    */       }
/* 76 */       ((List)this.cachedPlayers.get(location.getWorld())).stream().forEach(player -> {
/*    */             if (player.getWorld().equals(paramAbstractLocation.getWorld()) && player.getLocation().distanceSquared(paramAbstractLocation) <= paramInt) {
/*    */               paramSet.add(player);
/*    */             }
/*    */           });
/*    */       
/* 82 */       return inRange;
/* 83 */     } catch (Exception ex) {
/* 84 */       return Collections.emptySet();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\mobs\EntityManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */