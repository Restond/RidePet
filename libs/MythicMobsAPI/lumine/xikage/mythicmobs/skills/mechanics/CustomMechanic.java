/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.utils.Schedulers;
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import java.util.Optional;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomMechanic
/*    */   extends SkillMechanic
/*    */ {
/*    */   protected final String skillName;
/*    */   protected final MythicLineConfig config;
/* 19 */   protected SkillMechanic mechanic = null;
/*    */   protected boolean loaded = false;
/*    */   
/*    */   public CustomMechanic(String skill, String line, MythicLineConfig mlc) {
/* 23 */     super(line, mlc);
/* 24 */     this.skillName = skill;
/* 25 */     this.config = mlc;
/*    */     
/* 27 */     MythicMobs.debug(1, "---- Loading CustomMechanic with name " + skill);
/* 28 */     Schedulers.sync().run(() -> {
/*    */           MythicMobs.debug(3, "Attempting to Register CustomMechanic: " + paramString);
/*    */           MythicMechanicLoadEvent event = new MythicMechanicLoadEvent(this, paramString, this.config);
/*    */           Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*    */           if (event.getMechanic().isPresent()) {
/*    */             this.mechanic = event.getMechanic().get();
/*    */             this.ASYNC_SAFE = this.mechanic.isAsyncSafe();
/*    */             this.loaded = true;
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<SkillMechanic> getMechanic() {
/* 42 */     return Optional.ofNullable(this.mechanic);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\CustomMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */