/*    */ package lumine.xikage.mythicmobs.adapters.bukkit.listeners;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.listeners.ChunkListeners;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
/*    */ import io.lumine.xikage.mythicmobs.skills.TriggeredSkill;
/*    */ import org.bukkit.Chunk;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
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
/*    */ 
/*    */ 
/*    */ class ChunkLoader
/*    */   implements Runnable
/*    */ {
/*    */   private Chunk c;
/*    */   
/*    */   public ChunkLoader(Chunk c) {
/* 40 */     this.c = c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 45 */     if (!this.c.isLoaded())
/*    */       return; 
/* 47 */     Entity[] el = this.c.getEntities();
/* 48 */     if (el.length == 0)
/*    */       return; 
/* 50 */     for (Entity ee : el) {
/* 51 */       if (ee instanceof LivingEntity) {
/* 52 */         LivingEntity l = (LivingEntity)ee;
/*    */         
/* 54 */         if (MythicMobs.inst().getMobManager().isActiveMob(ee.getUniqueId()))
/* 55 */         { if (l.getRemoveWhenFarAway() == true) {
/* 56 */             ee.remove();
/*    */           }
/*    */           else {
/*    */             
/* 60 */             ActiveMob am = MythicMobs.inst().getMobManager().getMythicMobInstance(ee);
/*    */             
/* 62 */             if (am == null || am.getType() == null) {
/* 63 */               ee.remove();
/*    */ 
/*    */             
/*    */             }
/* 67 */             else if (am.getType().getDespawns() == true) {
/* 68 */               am.setDespawned();
/* 69 */               MythicMobs.inst().getMobManager().unregisterActiveMob(am);
/* 70 */               ee.remove();
/*    */             } else {
/*    */               
/* 73 */               am.getType().applyMobVolatileOptions(am);
/*    */             } 
/*    */           }  }
/* 76 */         else { ActiveMob am = MythicMobs.inst().getMobManager().registerActiveMob(BukkitAdapter.adapt((Entity)l));
/*    */           
/* 78 */           if (am != null)
/*    */           {
/* 80 */             new TriggeredSkill(SkillTrigger.SPAWN, am, null, new org.apache.commons.lang3.tuple.Pair[0]);
/*    */           } }
/*    */       
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\listeners\ChunkListeners$ChunkLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */