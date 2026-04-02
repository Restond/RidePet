/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "weather", description = "Changes the weather")
/*    */ public class WeatherMechanic
/*    */   extends SkillMechanic implements INoTargetSkill {
/*    */   int type;
/*    */   int duration;
/*    */   
/*    */   public WeatherMechanic(String skill, MythicLineConfig mlc) {
/* 16 */     super(skill, mlc);
/* 17 */     this.ASYNC_SAFE = false;
/*    */     
/* 19 */     String t = mlc.getString("type", "sunny");
/* 20 */     this.duration = mlc.getInteger("duration", 500);
/*    */     
/* 22 */     switch (t) { case "sunny": case "sun":
/*    */       case "clear":
/* 24 */         this.type = 0; return;
/*    */       case "rain":
/*    */       case "rainy":
/* 27 */         this.type = 1; return;
/*    */       case "stormy": case "storm":
/*    */       case "thunder":
/* 30 */         this.type = 2;
/*    */         return; }
/*    */     
/* 33 */     this.type = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 40 */     switch (this.type) {
/*    */       case 0:
/* 42 */         data.getCaster().getEntity().getWorld().setStorm(true);
/*    */         break;
/*    */       case 1:
/* 45 */         data.getCaster().getEntity().getWorld().setStorm(true);
/* 46 */         data.getCaster().getEntity().getWorld().setThundering(false);
/*    */         break;
/*    */       case 2:
/* 49 */         data.getCaster().getEntity().getWorld().setStorm(true);
/* 50 */         data.getCaster().getEntity().getWorld().setThundering(true);
/*    */         break;
/*    */     } 
/* 53 */     data.getCaster().getEntity().getWorld().setWeatherDuration(this.duration);
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\WeatherMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */