/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractBossBar;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "barSet", version = "4.8", description = "Sets the display values on a custom bossbar")
/*    */ public class BarSetMechanic extends SkillMechanic implements INoTargetSkill {
/*    */   protected String barName;
/*    */   protected PlaceholderString barDisplay;
/*    */   protected PlaceholderDouble value;
/*    */   
/*    */   public BarSetMechanic(String skill, MythicLineConfig mlc) {
/* 21 */     super(skill, mlc);
/* 22 */     this.ASYNC_SAFE = false;
/*    */     
/* 24 */     this.barName = mlc.getString(new String[] { "name", "n" }, "infobar", new String[0]);
/* 25 */     this.barDisplay = mlc.getPlaceholderString(new String[] { "display", "d", "bartimerdisplay", "bartimertext" }, "<skill.var.aura-name>", new String[0]);
/* 26 */     this.value = mlc.getPlaceholderDouble(new String[] { "value", "v" }, 1.0D, new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 31 */     if (!(data.getCaster() instanceof ActiveMob)) {
/* 32 */       return false;
/*    */     }
/* 34 */     ActiveMob am = (ActiveMob)data.getCaster();
/* 35 */     AbstractBossBar bar = am.getBar(this.barName);
/*    */     
/* 37 */     if (bar != null) {
/* 38 */       bar.setTitle(this.barDisplay.get((PlaceholderMeta)data));
/* 39 */       bar.setProgress(this.value.get((PlaceholderMeta)data));
/*    */     } 
/* 41 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BarSetMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */