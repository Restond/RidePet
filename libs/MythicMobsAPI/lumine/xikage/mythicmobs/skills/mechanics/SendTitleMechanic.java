/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.logging.MythicLogger;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class SendTitleMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill {
/*    */   protected PlaceholderString title;
/*    */   protected PlaceholderString subtitle;
/*    */   
/*    */   public SendTitleMechanic(String line, MythicLineConfig mlc) {
/* 23 */     super(line, mlc);
/* 24 */     this.target_creative = true;
/*    */     
/*    */     try {
/* 27 */       this.title = PlaceholderString.of(mlc.getString(new String[] { "title", "t" }));
/* 28 */     } catch (Exception ex) {
/* 29 */       MythicLogger.errorMechanicConfig(this, mlc, "The 'message' attribute is required.");
/* 30 */       this.title = PlaceholderString.of("INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD.");
/* 31 */       ex.printStackTrace();
/*    */     } 
/*    */     
/*    */     try {
/* 35 */       this.subtitle = PlaceholderString.of(mlc.getString(new String[] { "subtitle", "st" }));
/* 36 */     } catch (Exception ex) {
/* 37 */       MythicLogger.errorMechanicConfig(this, mlc, "The 'message' attribute is required.");
/* 38 */       this.subtitle = PlaceholderString.of("INCORRECTLY CONFIGURED. SEE CONSOLE ON RELOAD.");
/* 39 */       ex.printStackTrace();
/*    */     } 
/*    */     
/* 42 */     this.timeIn = mlc.getInteger("fadein", 1);
/* 43 */     this.timeIn = mlc.getInteger("fi", this.timeIn);
/*    */     
/* 45 */     this.timeSt = mlc.getInteger("duration", 1);
/* 46 */     this.timeSt = mlc.getInteger("d", this.timeSt);
/*    */     
/* 48 */     this.timeOt = mlc.getInteger("fadeout", 1);
/* 49 */     this.timeOt = mlc.getInteger("fo", this.timeOt);
/*    */   }
/*    */   protected int timeIn; protected int timeSt; protected int timeOt;
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 54 */     String t = this.title.get((PlaceholderMeta)data, target);
/* 55 */     String st = this.subtitle.get((PlaceholderMeta)data, target);
/*    */     
/* 57 */     Entity e = BukkitAdapter.adapt(target);
/* 58 */     if (e instanceof Player) {
/* 59 */       if (MythicMobs.inst().getMinecraftVersion() >= 12) {
/* 60 */         ((Player)e).sendTitle(t, st, this.timeIn, this.timeSt, this.timeOt);
/*    */       } else {
/* 62 */         MythicMobs.inst().getVolatileCodeHandler().sendTitleToPlayer((Player)e, t, st, this.timeIn, this.timeSt, this.timeOt);
/*    */       } 
/* 64 */       return true;
/*    */     } 
/* 66 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\SendTitleMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */