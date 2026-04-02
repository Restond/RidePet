/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import org.bukkit.Location;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EffectSound
/*    */ {
/*    */   public static void DoEffect(Location location, String ed) {
/* 14 */     String[] data = ed.split(":");
/*    */     
/* 16 */     String sound = data[0];
/* 17 */     float volume = (data.length > 1) ? Float.parseFloat(data[1]) : 1.0F;
/* 18 */     float pitch = (data.length > 2) ? Float.parseFloat(data[2]) : 1.0F;
/*    */     
/* 20 */     if (sound.equals("random.wood_click")) {
/* 21 */       sound = "random.wood click";
/* 22 */     } else if (sound.equals("mob.ghast.affectionate_scream")) {
/* 23 */       sound = "mob.ghast.affectionate scream";
/*    */     } 
/*    */     
/* 26 */     MythicMobs.debug(3, "---- Executing Sound Effect (Sound=" + sound + ",Volume=" + volume + ",Pitch=" + pitch + ")");
/*    */     
/* 28 */     MythicMobs.inst().getVolatileCodeHandler().PlaySoundAtLocation(BukkitAdapter.adapt(location), sound, volume, pitch);
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\EffectSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */