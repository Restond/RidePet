/*    */ package lumine.xikage.mythicmobs.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.metadata.MetadataValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillHelper
/*    */ {
/*    */   public static List<Player> getPlayersInRadius(LivingEntity l, int i) {
/* 17 */     List<Player> list = new ArrayList<>();
/*    */     
/* 19 */     for (Player p : l.getWorld().getPlayers()) {
/* 20 */       if (p.getWorld().equals(l.getWorld()) && 
/* 21 */         p.getGameMode() != GameMode.CREATIVE && 
/* 22 */         l.getLocation().distanceSquared(p.getLocation()) < Math.pow(i, 2.0D)) {
/* 23 */         list.add(p);
/*    */       }
/*    */     } 
/*    */     
/* 27 */     return list;
/*    */   }
/*    */   
/*    */   public static List<LivingEntity> getLivingEntitiesInRadius(LivingEntity l, int i) {
/* 31 */     List<LivingEntity> list = new ArrayList<>();
/*    */     
/* 33 */     for (LivingEntity p : l.getWorld().getLivingEntities()) {
/* 34 */       if (!p.getWorld().equals(l.getWorld()) || (
/* 35 */         p instanceof Player && (
/* 36 */         (Player)p).getGameMode() == GameMode.CREATIVE))
/*    */         continue; 
/* 38 */       if (!p.getUniqueId().equals(l.getUniqueId()) && 
/* 39 */         l.getLocation().distanceSquared(p.getLocation()) < Math.pow(i, 2.0D)) {
/* 40 */         list.add(p);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 45 */     return list;
/*    */   }
/*    */   
/*    */   public static boolean hasUsedSkill(String full, AbstractEntity abstractEntity) {
/* 49 */     List<MetadataValue> list = BukkitAdapter.adapt(abstractEntity).getMetadata(full);
/*    */     
/* 51 */     for (MetadataValue mv : list) {
/* 52 */       if (mv.asString().equals(full)) {
/* 53 */         return true;
/*    */       }
/*    */     } 
/* 56 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\SkillHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */