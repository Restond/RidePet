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
/*    */ @MythicMechanic(author = "Ashijin", name = "barCreate", aliases = {"barAdd", "createBar"}, version = "4.8", description = "Creates a custom bossbar display")
/*    */ public class BarCreateMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill {
/*    */   protected String barName;
/*    */   protected PlaceholderString barDisplay;
/*    */   protected AbstractBossBar.BarColor barTimerColor;
/*    */   protected AbstractBossBar.BarStyle barTimerStyle;
/*    */   protected PlaceholderDouble value;
/*    */   
/*    */   public BarCreateMechanic(String skill, MythicLineConfig mlc) {
/* 25 */     super(skill, mlc);
/* 26 */     this.ASYNC_SAFE = false;
/*    */     
/* 28 */     this.barName = mlc.getString(new String[] { "name", "n" }, "infobar", new String[0]);
/* 29 */     this.barDisplay = mlc.getPlaceholderString(new String[] { "display", "d", "bartimerdisplay", "bartimertext" }, "<skill.var.aura-name>", new String[0]);
/* 30 */     this.value = mlc.getPlaceholderDouble(new String[] { "value", "v" }, 1.0D, new String[0]);
/*    */     
/* 32 */     String barTimerColor = mlc.getString(new String[] { "color", "c", "bartimercolor" }, "RED", new String[0]);
/* 33 */     String barTimerStyle = mlc.getString(new String[] { "style", "s", "bartimerstyle" }, "SOLID", new String[0]);
/*    */     
/*    */     try {
/* 36 */       this.barTimerColor = AbstractBossBar.BarColor.valueOf(barTimerColor);
/* 37 */     } catch (Exception ex) {
/* 38 */       this.barTimerColor = AbstractBossBar.BarColor.RED;
/*    */     } 
/*    */     
/*    */     try {
/* 42 */       this.barTimerStyle = AbstractBossBar.BarStyle.valueOf(barTimerStyle);
/* 43 */     } catch (Exception ex) {
/* 44 */       this.barTimerStyle = AbstractBossBar.BarStyle.SOLID;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 50 */     if (!(data.getCaster() instanceof ActiveMob)) {
/* 51 */       return false;
/*    */     }
/* 53 */     ActiveMob am = (ActiveMob)data.getCaster();
/* 54 */     AbstractBossBar bar = getPlugin().server().createBossBar(this.barDisplay.get((PlaceholderMeta)data), this.barTimerColor, this.barTimerStyle);
/* 55 */     bar.setProgress(this.value.get((PlaceholderMeta)data));
/*    */     
/* 57 */     am.addBar(this.barName, bar);
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\BarCreateMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */