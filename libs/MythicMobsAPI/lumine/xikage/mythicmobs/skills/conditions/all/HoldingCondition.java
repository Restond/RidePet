/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "holding", aliases = {}, description = "Checks if the target is holding a given material")
/*    */ public class HoldingCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "material", aliases = {"m"}, description = "The material type to match")
/*    */   private Material material;
/*    */   
/*    */   public HoldingCondition(String line, MythicLineConfig mlc) {
/* 20 */     super(line);
/*    */     
/* 22 */     String m = mlc.getString(new String[] { "material", "m", "type", "t" }, "DIRT", new String[] { this.conditionVar });
/*    */     
/*    */     try {
/* 25 */       this.material = Material.valueOf(m);
/* 26 */     } catch (IllegalArgumentException ex) {
/* 27 */       MythicLogger.errorConditionConfig(this, mlc, "'" + m + "' is not a valid material.");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 33 */     if (e.isLiving()) {
/* 34 */       return ((LivingEntity)e.getBukkitEntity()).getEquipment().getItemInHand().getType().equals(this.material);
/*    */     }
/* 36 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HoldingCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */