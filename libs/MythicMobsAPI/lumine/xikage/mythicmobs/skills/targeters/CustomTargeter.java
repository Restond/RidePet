/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicTargeterLoadEvent;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.TriggerTargeter;
/*    */ import java.util.Optional;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ 
/*    */ public class CustomTargeter
/*    */   extends SkillTargeter
/*    */ {
/*    */   protected final String targeterName;
/*    */   protected final MythicLineConfig config;
/* 19 */   protected SkillTargeter targeter = null;
/*    */   protected boolean loaded = false;
/*    */   
/*    */   public CustomTargeter(String targeter, MythicLineConfig mlc) {
/* 23 */     super(mlc);
/* 24 */     this.targeterName = targeter;
/* 25 */     this.config = mlc;
/* 26 */     this.targeter = (SkillTargeter)new TriggerTargeter(mlc);
/*    */     
/* 28 */     MythicMobs.debug(1, "---- Loading CustomTargeter with name " + targeter);
/* 29 */     Schedulers.sync().run(() -> {
/*    */           MythicMobs.debug(3, "Attempting to Register CustomTargeter: " + paramString);
/*    */           MythicTargeterLoadEvent event = new MythicTargeterLoadEvent(this, paramString, this.config);
/*    */           Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*    */           if (event.getTargeter().isPresent()) {
/*    */             this.targeter = event.getTargeter().get();
/*    */             this.loaded = true;
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<SkillTargeter> getTargeter() {
/* 42 */     return Optional.ofNullable(this.targeter);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\CustomTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */