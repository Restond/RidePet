/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.inventivetalent.glow.GlowAPI;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "effect:glow", aliases = {"glow", "e:glow"}, description = "Makes the taget entity glow. Requires GlowAPI")
/*    */ public class GlowEffect
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   protected GlowAPI.Color color;
/*    */   protected int duration;
/*    */   
/*    */   public GlowEffect(String line, MythicLineConfig mlc) {
/* 24 */     super(line, mlc);
/* 25 */     this.ASYNC_SAFE = false;
/*    */     
/* 27 */     String color = mlc.getString(new String[] { "color", "c" }, "WHITE", new String[0]);
/*    */     try {
/* 29 */       this.color = GlowAPI.Color.valueOf(color);
/* 30 */     } catch (Exception ex) {
/* 31 */       MythicLogger.errorMechanicConfig(this, mlc, "The GLOW Effect requires GlowAPI and the 'color' attribute must be a valid GlowAPI Color.");
/* 32 */     } catch (Error ex) {
/* 33 */       MythicLogger.errorMechanicConfig(this, mlc, "The GLOW Effect requires GlowAPI and the 'color' attribute must be a valid GlowAPI Color.");
/*    */     } 
/* 35 */     this.duration = mlc.getInteger("duration", 100);
/* 36 */     this.duration = mlc.getInteger("d", this.duration);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 41 */     new Animator(this, target);
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\GlowEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */