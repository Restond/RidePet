/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
/*    */ import java.util.ArrayList;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillActivateSpawner
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill) {
/* 15 */     String[] base = skill.split(" ");
/*    */     
/* 17 */     String name = base[1];
/*    */     
/* 19 */     if (name.startsWith("g:")) {
/* 20 */       String group = name.substring(2);
/*    */       
/* 22 */       ArrayList<MythicSpawner> msl = MythicMobs.inst().getSpawnerManager().getSpawnersByGroup(group);
/*    */       
/* 24 */       for (MythicSpawner ms : msl) {
/* 25 */         ms.ActivateSpawner();
/*    */       }
/*    */     } else {
/*    */       
/* 29 */       MythicSpawner ms = MythicMobs.inst().getSpawnerManager().getSpawnerByName(name);
/*    */       
/* 31 */       if (ms == null) {
/* 32 */         MythicMobs.debug(1, "A mob is configured to activate a spawner that doesn't exist (mob=" + l.getCustomName() + ", spawner=" + name + ")");
/*    */         
/*    */         return;
/*    */       } 
/* 36 */       ms.ActivateSpawner();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillActivateSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */