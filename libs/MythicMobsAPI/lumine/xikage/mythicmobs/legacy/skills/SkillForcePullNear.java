/*    */ package lumine.xikage.mythicmobs.legacy.skills;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.MythicMobs;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillHelper;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillForcePullNear
/*    */ {
/*    */   public static void ExecuteSkill(LivingEntity l, String skill, LivingEntity target) {
/* 16 */     if (target == null)
/* 17 */       return;  String[] base = skill.split(" ");
/* 18 */     String[] data = base[1].split(":");
/*    */     
/* 20 */     int radius = Integer.parseInt(data[0]);
/* 21 */     double new_radius_xz = Double.parseDouble(data[1]);
/* 22 */     double new_radius_y = Double.parseDouble(data[2]);
/*    */ 
/*    */ 
/*    */     
/* 26 */     if (radius > 0) {
/*    */       
/* 28 */       for (Player p : SkillHelper.getPlayersInRadius(l, radius)) {
/*    */         
/* 30 */         Location Loc = l.getLocation();
/*    */         
/* 32 */         double new_x = Loc.getX();
/* 33 */         double new_z = Loc.getZ();
/* 34 */         double new_y = Loc.getY();
/*    */         
/* 36 */         new_x = (MythicMobs.r.nextInt(2) == 1) ? (new_x + MythicMobs.r.nextInt((int)new_radius_xz + 1)) : (new_x - MythicMobs.r.nextInt((int)new_radius_xz + 1));
/* 37 */         new_z = (MythicMobs.r.nextInt(2) == 1) ? (new_z + MythicMobs.r.nextInt((int)new_radius_xz + 1)) : (new_z - MythicMobs.r.nextInt((int)new_radius_xz + 1));
/* 38 */         new_y = (MythicMobs.r.nextInt(2) == 1) ? (new_y + MythicMobs.r.nextInt((int)new_radius_y + 1)) : (new_y - MythicMobs.r.nextInt((int)new_radius_y + 1));
/*    */         
/* 40 */         Loc.setX(new_x);
/* 41 */         Loc.setZ(new_z);
/* 42 */         Loc.setY(new_y);
/*    */         
/* 44 */         p.teleport(Loc);
/*    */       } 
/*    */     } else {
/* 47 */       Location Loc = l.getLocation();
/*    */       
/* 49 */       double new_x = Loc.getX();
/* 50 */       double new_z = Loc.getZ();
/* 51 */       double new_y = Loc.getY();
/*    */       
/* 53 */       new_x = (MythicMobs.r.nextInt(2) == 1) ? (new_x + MythicMobs.r.nextInt((int)new_radius_xz + 1)) : (new_x - MythicMobs.r.nextInt((int)new_radius_xz + 1));
/* 54 */       new_z = (MythicMobs.r.nextInt(2) == 1) ? (new_z + MythicMobs.r.nextInt((int)new_radius_xz + 1)) : (new_z - MythicMobs.r.nextInt((int)new_radius_xz + 1));
/* 55 */       new_y = (MythicMobs.r.nextInt(2) == 1) ? (new_y + MythicMobs.r.nextInt((int)new_radius_y + 1)) : (new_y - MythicMobs.r.nextInt((int)new_radius_y + 1));
/*    */       
/* 57 */       Loc.setX(new_x);
/* 58 */       Loc.setZ(new_z);
/* 59 */       Loc.setY(new_y);
/*    */       
/* 61 */       target.teleport(Loc);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\legacy\skills\SkillForcePullNear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */