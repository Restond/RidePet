/*    */ package lumine.xikage.mythicmobs.skills.mechanics;
/*    */ 
/*    */ import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
/*    */ import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
/*    */ import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
/*    */ import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
/*    */ import io.lumine.xikage.mythicmobs.util.annotations.MythicMechanic;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @MythicMechanic(author = "Ashijin", name = "velocity", description = "Sets the velocity on the target entity")
/*    */ public class VelocityMechanic
/*    */   extends SkillMechanic
/*    */   implements ITargetedEntitySkill
/*    */ {
/*    */   protected float velocityX;
/*    */   protected float velocityY;
/*    */   protected float velocityZ;
/*    */   protected VelocityMode mode;
/*    */   
/*    */   public VelocityMechanic(String line, MythicLineConfig mlc) {
/* 28 */     super(line, mlc);
/*    */     
/* 30 */     this.velocityX = mlc.getFloat(new String[] { "velocityx", "vx", "x" }, 1.0F);
/* 31 */     this.velocityY = mlc.getFloat(new String[] { "velocityy", "vy", "y" }, 1.0F);
/* 32 */     this.velocityZ = mlc.getFloat(new String[] { "velocityz", "vz", "z" }, 1.0F);
/*    */     
/* 34 */     String strMode = mlc.getString(new String[] { "mode", "m" }, "SET", new String[0]);
/*    */     
/* 36 */     switch (strMode.toUpperCase()) {
/*    */       case "ADD":
/* 38 */         this.mode = VelocityMode.ADD;
/*    */         return;
/*    */       case "REMOVE":
/* 41 */         this.mode = VelocityMode.REMOVE;
/*    */         return;
/*    */       case "MULTIPLY":
/* 44 */         this.mode = VelocityMode.MULTIPLY;
/*    */         return;
/*    */       case "DIVIDE":
/* 47 */         this.mode = VelocityMode.DIVIDE;
/*    */         return;
/*    */     } 
/* 50 */     this.mode = VelocityMode.SET;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
/* 57 */     Entity e = target.getBukkitEntity();
/*    */     
/* 59 */     Vector v = e.getVelocity();
/*    */     
/* 61 */     if (this.mode.equals(VelocityMode.SET)) {
/* 62 */       v = new Vector(this.velocityX, this.velocityY, this.velocityZ);
/* 63 */     } else if (this.mode.equals(VelocityMode.ADD)) {
/* 64 */       v.setX(v.getX() + this.velocityX);
/* 65 */       v.setY(v.getY() + this.velocityY);
/* 66 */       v.setZ(v.getZ() + this.velocityZ);
/* 67 */     } else if (this.mode.equals(VelocityMode.MULTIPLY)) {
/* 68 */       v.setX(v.getX() * this.velocityX);
/* 69 */       v.setY(v.getY() * this.velocityY);
/* 70 */       v.setZ(v.getZ() * this.velocityZ);
/* 71 */     } else if (this.mode.equals(VelocityMode.REMOVE)) {
/* 72 */       v.setX(v.getX() - this.velocityX);
/* 73 */       v.setY(v.getY() - this.velocityY);
/* 74 */       v.setZ(v.getZ() - this.velocityZ);
/* 75 */     } else if (this.mode.equals(VelocityMode.DIVIDE)) {
/* 76 */       v.setX(v.getX() / this.velocityX);
/* 77 */       v.setY(v.getY() / this.velocityY);
/* 78 */       v.setZ(v.getZ() / this.velocityZ);
/*    */     } 
/*    */     
/* 81 */     if (v.length() > 4.0D) {
/* 82 */       v = v.normalize().multiply(4);
/*    */     }
/*    */     
/* 85 */     if (Double.isNaN(v.getX())) v.setX(0); 
/* 86 */     if (Double.isNaN(v.getY())) v.setY(0); 
/* 87 */     if (Double.isNaN(v.getZ())) v.setZ(0);
/*    */     
/* 89 */     e.setVelocity(v);
/*    */     
/* 91 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\JavaProject\RidePet\libs\MythicMobsAPI\!\lumine\xikage\mythicmobs\skills\mechanics\VelocityMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */