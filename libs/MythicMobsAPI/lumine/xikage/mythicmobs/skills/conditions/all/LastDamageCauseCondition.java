/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ 
/*    */ @MythicCondition(author = "jaylawl", name = "lastDamageCause", aliases = {}, version = "4.5", description = "Checks the target's last damage cause")
/*    */ public class LastDamageCauseCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition {
/*    */   @MythicField(name = "damagecause", aliases = {"cause", "c"}, description = "The damage cause to match")
/*    */   protected EntityDamageEvent.DamageCause cause;
/*    */   
/*    */   public LastDamageCauseCondition(String line, MythicLineConfig mlc) {
/* 20 */     super(line);
/* 21 */     String c = mlc.getString(new String[] { "damagecause", "cause", "c" }, "ENTITY_ATTACK", new String[] { this.conditionVar });
/*    */     try {
/* 23 */       this.cause = EntityDamageEvent.DamageCause.valueOf(c.toUpperCase());
/* 24 */     } catch (Exception ex) {
/* 25 */       this.cause = EntityDamageEvent.DamageCause.ENTITY_ATTACK;
/* 26 */       MythicLogger.errorConditionConfig(this, mlc, "Damage cause not found (or not supported by this version of MythicMobs");
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 31 */     if (e.isLiving()) {
/* 32 */       return this.cause.equals(e.getBukkitEntity().getLastDamageCause().getCause());
/*    */     }
/* 34 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\LastDamageCauseCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */