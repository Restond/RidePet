/*    */ package lumine.xikage.mythicmobs.skills.targeters;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicTargeter;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ @MythicTargeter(author = "Ashijin", name = "spawners", aliases = {}, description = "Targets the location of specified mob spawners")
/*    */ public class SpawnersTargeter
/*    */   extends ILocationSelector {
/*    */   protected String spawnerString;
/*    */   
/*    */   public SpawnersTargeter(MythicLineConfig mlc) {
/* 18 */     super(mlc);
/*    */     
/* 20 */     this.spawnerString = mlc.getString(new String[] { "spawners", "spawner", "s" });
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
/* 25 */     HashSet<AbstractLocation> targets = new HashSet<>();
/*    */     
/* 27 */     for (MythicSpawner ms : MythicMobs.inst().getSpawnerManager().getSpawnersByString(this.spawnerString)) {
/* 28 */       targets.add(ms.getLocation());
/*    */     }
/*    */     
/* 31 */     return targets;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\targeters\SpawnersTargeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */