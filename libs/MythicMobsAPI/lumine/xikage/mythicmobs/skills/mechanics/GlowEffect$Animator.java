/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.skills.mechanics.GlowEffect;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.inventivetalent.glow.GlowAPI;
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
/*    */ class Animator
/*    */ {
/*    */   public Animator(AbstractEntity entity) {
/* 47 */     Schedulers.sync().run(() -> GlowAPI.setGlowing(BukkitAdapter.adapt(paramAbstractEntity), GlowEffect.this.color, Bukkit.getOnlinePlayers()));
/*    */ 
/*    */ 
/*    */     
/* 51 */     Schedulers.sync().runLater(() -> GlowAPI.setGlowing(BukkitAdapter.adapt(paramAbstractEntity), false, Bukkit.getOnlinePlayers()), paramGlowEffect.duration);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\GlowEffect$Animator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */