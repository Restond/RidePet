/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedInt;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ @MythicCondition(author = "Ashijin", name = "enchantingLevel", aliases = {"enchantLevel", "enchantmentLevel"}, version = "4.8", description = "Tests the target's enchanting level")
/*    */ public class EnchantingLevelCondition
/*    */   extends SkillCondition
/*    */   implements IEntityCondition
/*    */ {
/*    */   @MythicField(name = "level", aliases = {"l"}, description = "The level range to match")
/*    */   private RangedInt level;
/*    */   
/*    */   public EnchantingLevelCondition(String line, MythicLineConfig mlc) {
/* 21 */     super(line);
/*    */     
/* 23 */     String k = mlc.getString(new String[] { "level", "l" }, "0", new String[] { this.conditionVar });
/* 24 */     this.level = new RangedInt(k);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity e) {
/* 29 */     if (!e.isPlayer()) return false;
/*    */     
/* 31 */     int level = ((Player)e.getBukkitEntity()).getLevel();
/* 32 */     return this.level.equals(Integer.valueOf(level));
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\EnchantingLevelCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */