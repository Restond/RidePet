/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "enchantingExperience", aliases = {"enchantingExp", "enchantExperience", "enchantExp"}, version = "4.8", description = "Tests the target's enchanting experience")
/*    */ public class EnchantingExperienceCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition
/*    */ {
/*    */   @MythicField(name = "amount", aliases = {"amount"}, description = "The experience range to match")
/*    */   private RangedDouble amount;
/*    */   
/*    */   public EnchantingExperienceCondition(String line, MythicLineConfig mlc) {
/* 21 */     super(line);
/*    */     
/* 23 */     String k = mlc.getString(new String[] { "level", "l" }, "0", new String[] { this.conditionVar });
/* 24 */     this.amount = new RangedDouble(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 29 */     if (!e.isPlayer()) return false;
/*    */     
/* 31 */     float exp = ((Player)e.getBukkitEntity()).getExp();
/* 32 */     return this.amount.equals(Float.valueOf(exp));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\EnchantingExperienceCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */