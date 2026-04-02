/*    */ package lumine.xikage.mythicmobs.adapters.bukkit;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.damage.DamageMetadata;
/*    */ import java.util.Optional;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ 
/*    */ 
/*    */ public class BukkitTriggerMetadata
/*    */ {
/*    */   public static SkillMetadata apply(SkillMetadata meta, EntityDamageByEntityEvent event) {
/* 14 */     AbstractEntity damaged = BukkitAdapter.adapt(event.getEntity());
/*    */     
/* 16 */     Optional<Object> maybeData = damaged.getMetadata("skill-damage");
/*    */     
/* 18 */     meta.getVariables().putString("damage-amount", String.valueOf(event.getFinalDamage()));
/*    */     
/* 20 */     if (maybeData.isPresent()) {
/* 21 */       DamageMetadata data = (DamageMetadata)maybeData.get();
/* 22 */       meta.getVariables().putObject("damage-metadata", data);
/* 23 */       meta.getVariables().putString("damage-cause", "SKILL");
/* 24 */       meta.getVariables().putString("damage-type", (data.getElement() == null) ? "SKILL" : data.getElement());
/*    */     } else {
/* 26 */       meta.getVariables().putString("damage-cause", event.getCause().toString());
/*    */     } 
/*    */     
/* 29 */     return meta;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\adapters\bukkit\BukkitTriggerMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */