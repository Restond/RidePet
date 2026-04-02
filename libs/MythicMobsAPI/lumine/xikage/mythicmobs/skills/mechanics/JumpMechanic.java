/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "jump", description = "Causes the caster to jump")
/*    */ public class JumpMechanic
/*    */   extends SkillMechanic
/*    */   implements INoTargetSkill
/*    */ {
/*    */   protected static boolean noloop = false;
/* 18 */   protected float velocity = 1.0F;
/*    */   
/*    */   public JumpMechanic(String line, MythicLineConfig mlc) {
/* 21 */     super(line, mlc);
/*    */     
/* 23 */     this.velocity = (float)mlc.getDouble("velocity", 1.0D);
/* 24 */     this.velocity = (float)mlc.getDouble("v", this.velocity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cast(SkillMetadata data) {
/* 29 */     Entity e = BukkitAdapter.adapt(data.getCaster().getEntity());
/*    */     
/* 31 */     Vector v = e.getVelocity();
/*    */     
/* 33 */     v.setY(v.getY() + this.velocity);
/*    */     
/* 35 */     if (v.length() > 4.0D) {
/* 36 */       v = v.normalize().multiply(4);
/*    */     }
/*    */     
/* 39 */     if (Double.isNaN(v.getX())) v.setX(0); 
/* 40 */     if (Double.isNaN(v.getY())) v.setY(0); 
/* 41 */     if (Double.isNaN(v.getZ())) v.setZ(0);
/*    */     
/* 43 */     e.setVelocity(v);
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\JumpMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */