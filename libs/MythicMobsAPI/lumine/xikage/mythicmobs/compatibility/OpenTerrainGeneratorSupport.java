/*    */ package lumine.xikage.mythicmobs.compatibility;
/*    */ 
/*    */ import com.khorn.terraincontrol.TerrainControl;
/*    */ import io.lumine.utils.events.Events;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillCondition;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OpenTerrainGeneratorSupport
/*    */   implements Listener
/*    */ {
/*    */   private Events.Handler loader;
/*    */   
/*    */   public OpenTerrainGeneratorSupport() {
/* 24 */     this
/* 25 */       .loader = Events.subscribe(MythicConditionLoadEvent.class).handler(event -> {
/*    */           switch (event.getConditionName()) {
/*    */             case "OTGBIOME":
/*    */             case "OPENTERRAINGENERATORBIOME":
/*    */             case "TCBIOME":
/*    */             case "TERRAINCONTROLBIOME":
/*    */               event.register((SkillCondition)new OTGBiomeCondition(this, event.getConditionName(), event.getConfig()));
/*    */               break;
/*    */           } 
/*    */         });
/*    */   } public String getBiome(AbstractLocation location) {
/* 36 */     return TerrainControl.getWorld(location.getWorld().getName()).getBiome(location.getBlockX(), location.getBlockZ()).getName();
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\compatibility\OpenTerrainGeneratorSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */