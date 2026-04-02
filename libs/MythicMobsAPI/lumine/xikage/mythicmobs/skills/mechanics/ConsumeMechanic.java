/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.damage.DamagingMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "consume", description = "Deals damage to the target and heals the caster")
/*    */ public class ConsumeMechanic
/*    */   extends DamagingMechanic implements ITargetedEntitySkill {
/*    */   protected PlaceholderFloat damage;
/*    */   protected PlaceholderFloat heal;
/*    */   
/*    */   public ConsumeMechanic(String line, MythicLineConfig mlc) {
/* 20 */     super(line, mlc);
/* 21 */     this.ASYNC_SAFE = false;
/*    */     
/* 23 */     this.damage = mlc.getPlaceholderFloat(new String[] { "damage", "dmg", "d" }, 1.0F, new String[0]);
/* 24 */     this.heal = mlc.getPlaceholderFloat(new String[] { "heal", "h" }, 1.0F, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 29 */     if (target.isDead() || target.getHealth() <= 0.0D) return false;
/*    */     
/* 31 */     LivingEntity consumer = (LivingEntity)data.getCaster().getEntity().getBukkitEntity();
/*    */     
/* 33 */     doDamage(data.getCaster(), target, (this.damage.get((PlaceholderMeta)data, target) * data.getPower()));
/*    */     
/* 35 */     float modheal = this.heal.get((PlaceholderMeta)data, target) * data.getPower();
/*    */     
/* 37 */     if (consumer.getHealth() + modheal >= consumer.getMaxHealth()) {
/* 38 */       consumer.setHealth(consumer.getMaxHealth());
/*    */     } else {
/* 40 */       consumer.setHealth(consumer.getHealth() + modheal);
/*    */     } 
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ConsumeMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */