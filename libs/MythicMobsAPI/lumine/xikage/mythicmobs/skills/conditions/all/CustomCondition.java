/*    */ package lumine.xikage.mythicmobs.skills.conditions.all;
/*    */ 
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import java.util.Optional;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ 
/*    */ public class CustomCondition
/*    */   extends SkillCondition
/*    */ {
/*    */   protected final String conditionName;
/*    */   protected final MythicLineConfig config;
/* 18 */   protected SkillCondition condition = null;
/*    */   protected boolean loaded = false;
/*    */   
/*    */   public CustomCondition(String condition, String line, MythicLineConfig mlc) {
/* 22 */     super(line);
/* 23 */     this.conditionName = condition;
/* 24 */     this.config = mlc;
/*    */     
/* 26 */     MythicMobs.debug(1, "---- Loading CustomCondition with name " + condition);
/* 27 */     Schedulers.sync().runLater(() -> { MythicMobs.debug(3, "Attempting to Register CustomCondition: " + paramString); MythicConditionLoadEvent event = new MythicConditionLoadEvent(this, paramString, this.config); Bukkit.getServer().getPluginManager().callEvent((Event)event); if (event.getCondition().isPresent()) { this.condition = event.getCondition().get(); this.loaded = true; }  }0L);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Optional<SkillCondition> getCondition() {
/* 40 */     return Optional.ofNullable(this.condition);
/*    */   }
/*    */   
/*    */   public String getConditionArgument() {
/* 44 */     return this.conditionVar;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\conditions\all\CustomCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */