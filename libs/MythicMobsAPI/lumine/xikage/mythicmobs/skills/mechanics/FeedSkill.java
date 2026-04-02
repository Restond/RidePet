/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "feed", description = "Feeds the target entity")
/*    */ public class FeedSkill
/*    */   extends SkillMechanic implements ITargetedEntitySkill {
/*    */   protected static boolean noloop = false;
/*    */   protected PlaceholderInt amount;
/* 17 */   protected float saturation = 0.0F;
/*    */   protected float maxSaturation;
/*    */   protected boolean overfeed = false;
/*    */   
/*    */   public FeedSkill(String line, MythicLineConfig mlc) {
/* 22 */     super(line, mlc);
/*    */     
/* 24 */     this.amount = mlc.getPlaceholderInteger(new String[] { "amount", "a" }, 1, new String[0]);
/* 25 */     this.saturation = mlc.getFloat(new String[] { "saturation", "s" }, 0.0F);
/* 26 */     this.overfeed = mlc.getBoolean(new String[] { "overfeed", "of", "o" }, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 31 */     if (target.isDead() || !target.isPlayer()) return false;
/*    */     
/* 33 */     int currentFood = target.asPlayer().getFoodLevel();
/* 34 */     int food = currentFood + this.amount.get((PlaceholderMeta)data, target);
/* 35 */     float saturation = target.asPlayer().getFoodSaturation() + this.saturation;
/*    */     
/* 37 */     if (food >= 20) {
/* 38 */       if (this.overfeed == true) {
/* 39 */         target.asPlayer().setFoodLevel(food);
/* 40 */         if (saturation > food) saturation = food; 
/*    */       } else {
/* 42 */         target.asPlayer().setFoodLevel(20);
/* 43 */         if (saturation > 20.0F) saturation = 20.0F; 
/*    */       } 
/*    */     } else {
/* 46 */       target.asPlayer().setFoodLevel(food);
/*    */       
/* 48 */       if (!this.overfeed && 
/* 49 */         saturation > food) saturation = food;
/*    */     
/*    */     } 
/* 52 */     target.asPlayer().setFoodSaturation(saturation);
/* 53 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\FeedSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */