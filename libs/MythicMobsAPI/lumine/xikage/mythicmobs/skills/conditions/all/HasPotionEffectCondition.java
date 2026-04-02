/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
/*    */ import io.lumine.xikage.mythicmobs.util.types.RangedDouble;
/*    */ 
/*    */ @MythicCondition(author = "Joikd", name = "hasPotionEffect", aliases = {"hasPotion"}, description = "Tests if the target entity has a potion effect")
/*    */ public class HasPotionEffectCondition
/*    */   extends SkillCondition implements IEntityCondition {
/*    */   @MythicField(name = "type", aliases = {"t"}, description = "The potion effect type")
/*    */   private final String effectType;
/*    */   @MythicField(name = "level", aliases = {"lvl", "l"}, description = "An optional level range to match")
/* 18 */   private RangedDouble level = null;
/*    */   
/*    */   @MythicField(name = "duration", aliases = {"d"}, description = "An optional duration range to match")
/* 21 */   private RangedDouble duration = null;
/*    */   
/*    */   private final boolean checkAll;
/*    */ 
/*    */   
/*    */   public HasPotionEffectCondition(String line, MythicLineConfig mlc) {
/* 27 */     super(line);
/*    */     
/* 29 */     this.effectType = mlc.getString(new String[] { "type", "t" }, "ANY", new String[] { this.conditionVar }).toUpperCase();
/*    */     
/* 31 */     if (this.effectType.equals("ANY")) {
/* 32 */       this.checkAll = true;
/*    */     } else {
/* 34 */       this.checkAll = false;
/*    */     } 
/*    */     
/* 37 */     String strLevel = mlc.getString(new String[] { "level", "lvl", "l" }, null, new String[0]);
/* 38 */     if (strLevel != null) {
/* 39 */       this.level = new RangedDouble(strLevel);
/*    */     }
/*    */     
/* 42 */     String strDuration = mlc.getString(new String[] { "duration", "d" }, null, new String[0]);
/* 43 */     if (strDuration != null) {
/* 44 */       this.duration = new RangedDouble(strDuration);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean check(AbstractEntity entity) {
/* 50 */     if (this.checkAll) {
/* 51 */       return entity.hasPotionEffect();
/*    */     }
/* 53 */     MythicMobs.debug(1, "Checking if target has PotionEffect type = " + this.effectType + ", level = " + this.level + ", d = " + this.duration);
/* 54 */     MythicMobs.debug(1, "Returning " + entity.hasPotionEffect(this.effectType, this.level, this.duration));
/* 55 */     return entity.hasPotionEffect(this.effectType, this.level, this.duration);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\HasPotionEffectCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */