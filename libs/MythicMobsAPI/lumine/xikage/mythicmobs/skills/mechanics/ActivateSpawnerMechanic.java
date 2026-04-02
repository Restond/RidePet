/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderMeta;
/*    */ import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "activatespawner", aliases = {"as"}, description = "Activate a Mythic Spawner")
/*    */ public class ActivateSpawnerMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill {
/*    */   protected PlaceholderString search;
/*    */   
/*    */   public ActivateSpawnerMechanic(String skill, MythicLineConfig mlc) {
/* 20 */     super(skill, mlc);
/* 21 */     this.ASYNC_SAFE = false;
/*    */     
/* 23 */     this.search = mlc.getPlaceholderString(new String[] { "spawners", "spawner", "s" }, "", new String[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 28 */     ArrayList<MythicSpawner> msl = getPlugin().getSpawnerManager().getSpawnersByString(data.getCaster().getEntity().getLocation(), this.search.get((PlaceholderMeta)data));
/*    */     
/* 30 */     for (MythicSpawner ms : msl) {
/* 31 */       ms.ActivateSpawner();
/*    */     }
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\ActivateSpawnerMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */